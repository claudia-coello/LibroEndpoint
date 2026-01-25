# API REST - Gestion de libros

###Autor: Claudia Coello

Desarrollado con: Spring Boot.

Proyecto que implementa una API REST para la gestion de un catálogo de libros. 

Operaciones: Crear, buscar, actualizar, eliminar y leer.

## Como ejecutar el proyecto
### En local:
1. Clonar el repositorio.
2. Abrir el proyecto en el IDE de su preferencia.
3. Ejecutar: DemoApplication, clase principal.
4. Buscar en un navegador: http://localhost:8080

    Sí tiene inconvenientes con el puerto 8080, abrir application.properties.
   ![img.png](img.png)

   Ubicar la línea "server.port=8080" y cambiar el número por 8081.

    Buscar en el navegador: http://localhost:8081

### En la nube:
1. Ir al sitio: https://libroendpoint.onrender.com
2. Si tiene problemas contacte con el autor.

## Endpoints
### Buscar libros

**GET**
- Todos: '/libros'
- Por ID: '/libros/{id}'
- Por título: '/libros/{titulo}'

### Crear libros(uno o varios)
**POST**

'/libros' + JSON

***IMPORTANTE: No colocar id en el JSON***

{

    "autor": "STRING",
    "fechaPublicacion": "YY-MM-DD",
    "stock": INT,
    "titulo": "STRING"
}

### Eliminar libro
**DELETE**
'/libros/{id}'

### Actualizar libro
**PATCH**
'/libros/{id}' + JSON

***IMPORTANTE: No colocar id en el JSON***

### Body JSON, estructura general
{

    "autor": "STRING",
    "fechaPublicacion": "YY-MM-DD",
    "id": INT,
    "stock": INT,
    "titulo": "STRING"
}

## Respuestas
- 200: Ok
- 201: Created
- 204: No Content
- 400: Bad Request
- 404: Not Found