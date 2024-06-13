# Acceso a la Aplicación y Perfiles de Prueba

**URL de acceso a la aplicación:** [https://tfm-icl.netlify.app](https://tfm-icl.netlify.app)

## Perfiles de prueba

1. **Administrador**
   - Correo: admin@chiclana.com
   - Contraseña: contrasena

2. **Negocio**
   - Correo: negocio2@chiclana.com
   - Contraseña: contrasena
   - Alternativas:
     - negocio3@chiclana.com
     - negocio4@chiclana.com
     - Todos utilizan la misma contraseña: contrasena

3. **Ciudadano**
   - Correo: usuario01@chiclana.com
   - Contraseña: contrasena
   - Alternativas:
     - usuario02@chiclana.com
     - usuario03@chiclana.com
     - Todos utilizan la misma contraseña: contrasena

# Despliegue del Proyecto

## Despliegue en Local

Para desplegar el proyecto en un entorno local, es necesario configurar tanto el backend como el frontend. A continuación, se detallan los pasos para cada uno:

### Backend

1. **Prerequisitos:**
   - Java Development Kit (JDK) instalado.
   - Eclipse u otro IDE similar instalado.
   - Maven instalado.
   - PostgreSQL instalado y configurado.

2. **Configuración de la base de datos:**
   - Crear una base de datos en PostgreSQL.
   - Configurar las credenciales de acceso a la base de datos en nuestro proyecto.
   - Ejecutar la migración de la base de datos desde el archivo `resources/db/migrations/script_creacion_bd.sql`.

3. **Configuración de las variables de entorno:**
   - Configurar las variables de entorno en el archivo `.env` con las credenciales de acceso a la base de datos, correo y URL del frontend.

4. **Clonación del repositorio:**
   - Clonar el repositorio del proyecto desde GitHub: [https://github.com/rvidalo/TFM-icl](https://github.com/rvidalo/TFM-icl).

5. **Compilación y ejecución del backend:**
   - Abrir el proyecto en Eclipse.
   - Ejecutar `mvn clean install` para compilar el proyecto y descargar las dependencias necesarias.
   - Ejecutar la aplicación desde Eclipse o usando `mvn spring-boot:run`.

### Frontend

1. **Prerequisitos:**
   - Node.js y npm instalados.
   - Visual Studio Code u otro IDE similar instalado.

2. **Configuración de las variables de entorno:**
   - Configurar la variable de entorno con la URL de la API del backend en el archivo `environment.ts`.

3. **Clonación del repositorio:**
   - Clonar el repositorio del proyecto desde GitHub: [https://github.com/rvidalo/TFM-icl](https://github.com/rvidalo/TFM-icl).

4. **Instalación de dependencias:**
   - Navegar al directorio del proyecto frontend y ejecutar `npm install` para instalar las dependencias.

5. **Ejecución del frontend:**
   - Ejecutar `ng serve` para iniciar el servidor de desarrollo de Angular.
   - Acceder a la aplicación desde el navegador en [http://localhost:4200](http://localhost:4200).

## Despliegue en Servidor

El despliegue en el servidor se ha realizado utilizando Netlify para el frontend y Render para el backend y la base de datos PostgreSQL. A continuación, se describen los pasos para cada uno:

### Backend

1. **Configuración de la base de datos:**
   - Crear una cuenta en Render.
   - Crear una base de datos PostgreSQL en Render.
   - Conectarse a ella y ejecutar la migración de la base de datos desde el archivo `resources/db/migrations/script_creacion_bd.sql`.

2. **Creación del servicio en Render:**
   - Crear un nuevo servicio web y seleccionar el repositorio del backend desde GitHub.
   - Configurar las variables de entorno necesarias, incluyendo las credenciales de la base de datos, credenciales del correo y URL del frontend.
   - Desplegar el servicio; Render se encargará de construir y ejecutar la aplicación.

3. **Mantener el servicio activo:**
   - Debido a las limitaciones de la versión gratuita de Render, el servicio puede entrar en estado de suspensión tras 15 minutos de inactividad.
   - Configurar UptimeRobot para realizar una consulta a la API cada 5 minutos, asegurando que el servicio permanezca operativo.

### Frontend

1. **Despliegue en Netlify:**
   - Crear una cuenta en Netlify.
   - Crear un nuevo sitio desde Git y seleccionar el repositorio del frontend.
   - Configurar el comando de construcción como `ng build` y el directorio de salida como `dist/`.
   - Desplegar el sitio; Netlify se encargará de la construcción y del despliegue de la aplicación.

2. **Configuración del dominio:**
   - Configurar el dominio personalizado a [https://tfm-icl.netlify.app](https://tfm-icl.netlify.app), que será la URL de acceso a la aplicación.
