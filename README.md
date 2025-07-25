<div align="center">
  <h1 align="center"><a href="https://github.com/ASJordi/codigos-postales-mx">Códigos Postales de México</a></h1>
</div>

## About :computer:

Programa que permite leer, procesar y filtrar de acuerdo a múltiples criterios todos los códigos postales e información relacionada con ellos de México. La información es obtenida de la base de datos del Servicio Postal Mexicano (SEPOMEX) disponible en su [página oficial](https://www.correosdemexico.gob.mx/SSLServicios/ConsultaCP/CodigoPostal_Exportar.aspx). 

Los datos se encuentran en formato CSV, ya han sido limpiados y se están listos para ser procesados, tanto de forma general como por estado. La descripción para cada campo se encuentra disponible en el siguiente [enlace](https://www.correosdemexico.gob.mx/SSLServicios/ConsultaCP/imagenes/Descrip.pdf)

## Características :sparkles:

- Leer los datos directamente desde múltiples archivos CSV.
- Procesar y agrupar los datos en una colección.
- Filtrar los códigos postales de acuerdo a múltiples criterios.
- Permite guardar toda la información en una base de datos usando batch processing.
- Intercambiar entre el origen de datos desde el que se obtiene la información.

## Arquitectura :building_construction:

El proyecto sigue una arquitectura limpia y organizada por capas, aplicando principios de diseño de software para mejorar su mantenibilidad y escalabilidad.

- **Capas de la Aplicación:**
  - `model`: Contiene las entidades de la aplicación, como la clase `PostalCode`.
  - `persistence`: Encargada de la persistencia de datos. Se divide en:
    - `repository`: Define las interfaces para el acceso a datos (`IDataReader`, `IDataManager`, `IRepository`).
    - `file`: Implementaciones para leer datos desde archivos CSV (`PostalCodeDataLoader`).
    - `db`: Implementaciones para la interacción con la base de datos (`PostalCodeRepository`, `ConnectionDatabase`).
  - `service`: Contiene la lógica de negocio, como la clase `Analyzer`.
  - `util`: Clases de utilidad, como `DatabaseUtil`.

- **Patrones de Diseño Aplicados:**
  - **Strategy Pattern:** La clase `Analyzer` utiliza este patrón para desacoplar la lógica de análisis de la fuente de datos, permitiendo cambiar fácilmente entre la carga desde archivos o desde una base de datos.
  - **Specification Pattern:** Se utiliza para crear consultas y filtros de datos de una manera flexible y reutilizable, evitando la necesidad de múltiples métodos de búsqueda en la clase `Analyzer`.

## Tecnologías :gear:

- Java 21
- Lombok
- Jackson Databind

## Instalación :floppy_disk:

1. Clonar el repositorio.
2. Abrir el proyecto en un IDE.
3. Instalar las dependencias necesarias.
4. Ejecutar el programa.

## License :page_facing_up:

Distributed under the MIT License. See `LICENSE` for more information.

## Contacto :email:

Jordi Ayala - [@ASJordi](https://x.com/ASJordi)

Link del proyecto: [https://github.com/ASJordi/codigos-postales-mx](https://github.com/ASJordi/codigos-postales-mx)