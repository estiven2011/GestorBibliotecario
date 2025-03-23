# GestorBibliotecario
Este proyecto es una aplicación web desarrollada en Java utilizando Servlets. Simula un sistema de gestión bibliotecaria que permite registrar y consultar libros de forma dinámica, ademas permite la consulta de los autores. Toda la información se almacena en memoria utilizando colecciones (ArrayList), sin necesidad de conexión a una base de datos.

## Estructura del Proyecto

- *dao/*  
  Contiene las clases AutorDAO y LibroDAO, que funcionan como modelos de datos para representar autores y libros respectivamente.


- *servlet/*  
  Contiene los servlets AutorSrv y LibroSrv, que controlan la lógica de negocio, exponen servicios REST y gestionan las operaciones sobre los datos, en este caso especifico solo peticiones GET.


## Funcionalidad Principal

### AutorSrv (/autores)
- Método GET: Retorna una lista de autores en formato JSON, incluyendo los títulos de los libros que ha escrito cada autor.


### LibroSrv (/libros)
- Método GET: Retorna la lista de libros en formato JSON, incluyendo el nombre completo del autor de cada libro.
- Permite filtrar los libros por género (?genero=...) y por disponibilidad (?disponible=true/false).


### Requisitos

- Java JDK 8 o superior
- Apache Tomcat instalado y configurado
-  La estructura del proyecto debe estar organizada en paquetes (dao y servlet)
- Maven si estas usando pom.xlm
- Gson como dependencia para trabajar con JSON, en mi caso use esta:
```xml
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.12.1</version>
</dependency> 
``` 

## Cómo Ejecutar el Proyecto

1. Abre el proyecto en IntelliJ IDEA (o tu IDE favorito) como administrador.
2. Asegúrate de tener Tomcat instalado y configurado correctamente.
3. Ejecuta el servidor y accede a los endpoints usando una herramienta como Postman.

### Ejemplos de Endpoints

Los endpoints siguen esta estructura general:

```
http://{HOST}:{PUERTO}/{NOMBRE_DEL_PROYECTO}/{RUTA_DEL_SERVLET}
```

> Por ejemplo, si corres el proyecto localmente con:
> - `HOST`: `localhost`
> - `PUERTO`: `8085`
> - `NOMBRE_DEL_PROYECTO`: `gestorBibliotecario`

Entonces los endpoints disponibles son:

---

### 🔎 Autores

```http
GET http://localhost:8085/gestorBibliotecario/autores
```

✅ Retorna todos los autores registrados en formato JSON, incluyendo los títulos de los libros que ha escrito cada autor.

---

### 📘 Libros

```http
GET http://localhost:8085/gestorBibliotecario/libros
```

✅ Retorna todos los libros registrados en formato JSON, incluyendo el nombre completo del autor de cada libro.

#### 🔍 Filtros disponibles:
- Filtrar por género: 
  ```http
  GET http://localhost:8085/gestorBibliotecario/libros?genero=Novela
  ```
- Filtrar por disponibilidad:
  ```http
  GET http://localhost:8085/gestorBibliotecario/libros?disponible=true
  ```

- Filtrar por ambos:
  ```http
  GET http://localhost:8085/gestorBibliotecario/libros?genero=novela&disponible=true
  ```

---

⚠️ **Recuerda:** Si cambias el nombre del proyecto, el puerto o el servidor, debes actualizar las URLs correspondientes.

## Uso de Git

Durante el desarrollo del proyecto, se utilizaron los siguientes comandos de Git:

- `git config`: Configura el nombre y el correo del usuario.
- `git init`: Inicializa un repositorio Git local.
- `git remote add origin`: Enlaza el repositorio local con uno remoto (por ejemplo, en GitHub).
- `git add .`: Agrega todos los archivos al área de preparación (staging).
- `git commit -m "mensaje"`: Registra los cambios realizados con un mensaje descriptivo.
- `git push`: Envía los commits al repositorio remoto.
- `git checkout`: Realizar el cambio de rama
- `git checkout -b <nombre de la rama>`: Creamos una nueva rama y nos pasamos a ella automaticamente
- `git pull`: Trae y sincroniza los cambios desde el repositorio remoto.
- `git pull`: Muestra un listado de las ramas que tenemos y en cual estamos trabajando
- `git log`: Muestra el historial de commits realizados en el repositorio.
- `git status`: Muestra el estado actual del repositorio, indicando qué archivos han sido modificados, cuáles están en el área de preparación (staging) y cuáles no están siendo seguidos (untracked).





## Autor
Desarrollado por Juan Estiven Posada Rúa