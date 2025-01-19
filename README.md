  # Literalura - Aplicación de Búsqueda Literaria

Bienvenido a **Literalura**, una aplicación diseñada para realizar búsquedas literarias mediante una API. Puedes buscar libros por título, explorar libros por idioma, obtener información sobre autores y mucho más.

## 🚀 Finalidad de la Aplicación

Literalura te permite explorar una base de datos de libros y autores a través de una sencilla interfaz en la línea de comandos. La aplicación permite:

- **Buscar libros por título**.
- **Mostrar todos los libros buscados**.
- **Mostrar autores registrados**.
- **Mostrar autores registrados vivos en un determinado año**.
- **Buscar libros registrados por idioma**.

Esta aplicación fue desarrollada para facilitar la consulta de libros y autores a través de una API robusta y fácil de usar.

---

## 📥 Instalación

### Requisitos previos

Para ejecutar la aplicación en tu máquina local, necesitarás tener lo siguiente instalado:

- **Java 11 o superior**.
- **Maven** (para la gestión de dependencias).
- **IDE o editor de texto** (como IntelliJ IDEA, Eclipse, Visual Studio Code).

### Pasos para la instalación

1. **Clona el repositorio:**

   ```bash
   git clone https://github.com/tu-usuario/literalura.git
   
  Entra en el directorio del proyecto:
    cd literalura

  Construye el proyecto utilizando Maven:
    mvn clean install

  Ejecuta la aplicación:
    mvn spring-boot:run

## 📝 Interacción con la Aplicación
Una vez que la aplicación esté en ejecución, verás el siguiente menú interactivo en la consola:

  Bienvenido a Literalura.
  Este programa está diseñado para búsquedas literarias mediante una API.
  --------------------------------------------
  MENÚ:
  1 - Buscar libro por título.
  2 - Mostrar libros buscados.
  3 - Mostrar autores registrados.
  4 - Mostrar autores registrados vivos en determinado año.
  5 - Buscar libros registrados por idioma.

  0 - Salir.
  --------------------------------------------

Ingrese el número de la opción que desea escoger:
  1. Buscar libro por título
  Al seleccionar la opción 1, podrás ingresar el título del libro que deseas buscar. Si el libro está registrado en la base de datos, se mostrará la información del libro.

  2. Mostrar libros buscados
  La opción 2 muestra todos los libros que has buscado previamente durante la sesión.

  3. Mostrar autores registrados
  Al seleccionar la opción 3, la aplicación te mostrará todos los autores registrados en la base de datos, junto con su fecha de nacimiento y defunción, y los títulos de los libros asociados a ellos.

  4. Mostrar autores registrados vivos en determinado año
  Selecciona la opción 4 para ingresar un año específico y ver a los autores que estaban vivos en ese año. La aplicación verificará la fecha de nacimiento y defunción de cada autor y solo mostrará a aquellos cuya fecha de nacimiento y defunción cumplan con el criterio.

  5. Buscar libros registrados por idioma
  La opción 5 te permite buscar los libros registrados en un idioma específico. Solo se mostrarán aquellos libros que estén registrados con el idioma proporcionado.

## ⚙️ Estructura del Proyecto
  Paquetes y Clases Principales
  com.alura.literalura: Contiene las clases principales de la aplicación, incluyendo la configuración de Spring Boot, las entidades de dominio (como Libro, Autor), y las interfaces de repositorios para acceder a la base de datos.

  com.alura.literalura.repository: Repositorios de la base de datos, implementados usando Spring Data JPA.

  com.alura.literalura.service: Lógica de negocio que maneja las operaciones de búsqueda y consulta de libros y autores.

  com.alura.literalura.controller: Controladores que gestionan las solicitudes del usuario y la interacción con la API.

El proyecto se organiza de la siguiente manera:

src/
 ├── main/
 │    ├── java/
 │    │    ├── com.aluraliteralura/
 │    │    │    ├── dto/
 │    │    │    ├── model/
 │    │    │    ├── principal/
 │    │    │    ├── repository/
 │    │    │    └── LiteraluraApplication.java
 │    │    └── resources/
 │    │         ├── application.properties
 │    └── test/
 ├── pom.xml (si usas Maven)

## 🐞 Problemas conocidos
  Manejo de fechas nulas: La aplicación maneja correctamente autores con fechas de nacimiento y defunción nulas, utilizando reglas de negocios específicas para determinar si un autor está vivo.

  Optimización de la búsqueda: Para mejorar la performance en búsquedas grandes, es recomendable ajustar los índices en la base de datos si la aplicación se implementa a gran escala.

## 🔧 Contribuciones
  Si deseas contribuir a este proyecto, sigue estos pasos:

  Forkea el repositorio.
  Crea una nueva rama para tus cambios.
  Realiza tus cambios.
  Realiza un pull request explicando las modificaciones realizadas.
## 📄 Licencia
  Este proyecto está bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.

## 🤝 Agradecimientos
  Gracias por usar Literalura. Esperamos que encuentres útil esta aplicación para tus búsquedas literarias.


