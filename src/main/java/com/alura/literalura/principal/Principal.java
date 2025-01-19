package com.alura.literalura.principal;

import com.alura.literalura.dto.AutorData;
import com.alura.literalura.model.*;
import com.alura.literalura.repository.LibrosRepository;
import com.alura.literalura.service.ConsumoApi;
import com.alura.literalura.service.ConvertidorDatos;

import java.util.*;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoApi consumoAPI = new ConsumoApi();
    private ConvertidorDatos conversor = new ConvertidorDatos();
    private Scanner teclado = new Scanner(System.in);

    private LibrosRepository librosRepository;

    public Principal(LibrosRepository librosRepository) {
        this.librosRepository = librosRepository;
    }


    public void muestraMenu() {

        var opcion = -1;
        System.out.println("\n_________________Bienvenido a Literalura.__________________\n");
        System.out.println("Este programa esta diseñado para búsquedas literarias mediante una API.");
        while (opcion != 0) {
            var menu = """
                    --------------------------------------------
                    MENÚ:
                    1 - Buscar libro por título.
                    2 - Mostrar libros buscados.
                    3 - Mostrar autores registrados.
                    4 - Mostrar autores registrados vivos en determinado año.
                    5 - Buscar libros registrados por idioma.
                    
                    0 - Salir.
                    --------------------------------------------
                    """;
            System.out.println(menu);
            System.out.println("Ingrese el número de la opción que desea escoger: ");

            try {
                opcion = Integer.parseInt(teclado.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida. Por favor ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    mostrarLibrosBuscados();
                    break;
                case 3:
                    mostrarAutoresRegistrados();
                    break;
                case 4:
                    mostrarAutoresVivosEnXAnio();
                    break;
                case 5:
                    buscarLibrosPorIdioma();
                    break;

                case 0:
                    System.out.println("Cerrando aplicacion.");
                    break;
                default:
                    System.out.println("Opcion invalida");
                    System.out.println("ingrese el numero correspondiente a la opcion.");
            }
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Escribe el nombre del libro que desea buscar: ");
        String tituloLibro = teclado.nextLine();
        String urlConsulta = URL_BASE + "?search=" + tituloLibro.replace(" ", "+");
        String json = consumoAPI.llamadaApi(urlConsulta);
        Datos datosBusqueda = conversor.obtenerDatos(json, Datos.class);

        Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();

        if (libroBuscado.isPresent()) {
            System.out.println("Libro encontrado");
            Libro libro = new Libro(libroBuscado.get());
            System.out.println(libro);

            Optional<Libro> libroExistente = librosRepository.findByTituloAndAutorNombre(libro.getTitulo(), libro.getAutor().getNombre());

            if (libroExistente.isPresent()) {
                System.out.println("El libro ya está registrado en la base de datos.");
            } else {
                librosRepository.save(libro);
                System.out.println("Libro guardado en la base de datos.");
            }
        } else {
            System.out.println("Libro no encontrado");
        }
    }

    private void mostrarLibrosBuscados() {
        List<Libro> libros = librosRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println("No se han registrado libros.");
        } else {
            libros.stream()
                    .sorted(Comparator.comparing(Libro::getTitulo))
                    .forEach(System.out::println);
        }
    }

    private void mostrarAutoresRegistrados() {
        System.out.println("\nLista de Autores Registrados:\n");
        List<Libro> libros = librosRepository.findAll();
        Map<String, AutorData> autoresMap = new LinkedHashMap<>();

        for (Libro libro : libros) {
            Autor autor = libro.getAutor();
            String claveAutor = autor.getNombre();
            if (!autoresMap.containsKey(claveAutor)) {
                autoresMap.put(claveAutor, new AutorData(autor.getNombre(), autor.getFechaNacimiento(), autor.getFechaDefuncion()));
            }
            autoresMap.get(claveAutor).agregarLibro(libro.getTitulo());
        }
        for (AutorData datosAutor : autoresMap.values()) {
            System.out.println("Autor: " + datosAutor.getNombre());
            System.out.println("Fecha de Nacimiento: " + (datosAutor.getFechaNacimiento() == 0 ? "N/A" : datosAutor.getFechaNacimiento()));
            System.out.println("Fecha de Defunción: " + (datosAutor.getFechaDefuncion() == 0 ? "N/A" : datosAutor.getFechaDefuncion()));
            System.out.println("Título del Libro: " + String.join(", ", datosAutor.getLibros()));
            System.out.println("--------------------------------------------");
        }
    }

    private void mostrarAutoresVivosEnXAnio() {
        System.out.print("Ingrese el año para verificar los autores vivos: ");
        int anioConsulta;
        Map<String, AutorData> autoresMap = new LinkedHashMap<>();

        try {
            anioConsulta = Integer.parseInt(teclado.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, ingrese un número.");
            return;
        }

        List<Libro> librosVivos = librosRepository.findAutoresVivosEnAnio(anioConsulta);

        if (librosVivos.isEmpty()) {
            System.out.println("No se encontraron autores registrados en el año " + anioConsulta + ".");
        } else {
            System.out.println("\nAutores vivos en el año " + anioConsulta + ":\n");
            for (Libro libro : librosVivos) {
                Autor autor = libro.getAutor();
                String claveAutor = autor.getNombre();
                if (!autoresMap.containsKey(claveAutor)) {
                    autoresMap.put(claveAutor, new AutorData(autor.getNombre(), autor.getFechaNacimiento(), autor.getFechaDefuncion()));
                }
                autoresMap.get(claveAutor).agregarLibro(libro.getTitulo());
            }
            for (AutorData datosAutor : autoresMap.values()) {
                System.out.println("Autor: " + datosAutor.getNombre());
                System.out.println("Fecha de Nacimiento: " + (datosAutor.getFechaNacimiento() == 0 ? "N/A" : datosAutor.getFechaNacimiento()));
                System.out.println("Fecha de Defunción: " + (datosAutor.getFechaDefuncion() == 0 ? "N/A" : datosAutor.getFechaDefuncion()));
                System.out.println("Título del Libro: " + String.join(", ", datosAutor.getLibros()));
                System.out.println("--------------------------------------------");
            }
        }
    }

    private void buscarLibrosPorIdioma() {
        System.out.println("Ingrese el idioma para filtrar libros: ");
        System.out.println("""
                Español - es
                Inglés - en
                Francés - fr
                Portugués - pt 
                """);
        System.out.println("Ingrese solo la abreviación, ejemplo si es español: es");
        String idioma = teclado.nextLine().toLowerCase().trim();

        List<Libro> librosPorIdioma = librosRepository.findByIdioma(idioma);

        if (librosPorIdioma.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma: " + idioma);
        } else {
            System.out.println("Libros en el idioma: " + idioma + "\n");
            for (Libro libro : librosPorIdioma) {
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Autor: " + libro.getAutor().getNombre());
                System.out.println("Idioma: " + libro.getIdioma());
                System.out.println("Número de Descargas: " + libro.getNumeroDescargas());
                System.out.println("--------------------------------------------");
            }
        }
    }
}

