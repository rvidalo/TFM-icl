URL de acceso a la aplicación: https://tfm-icl.netlify.app

Perfiles de prueba:

1. Administrador
	•	Correo: admin@chiclana.com
	•	Contraseña: contrasena

2. Negocio
	•	Correo: negocio2@chiclana.com
	•	Contraseña: contrasena
	•	Alternativas:
		o	negocio3@chiclana.com
		o	negocio4@chiclana.com
		o	Todos utilizan la misma contraseña: contrasena
		
3. Ciudadano
	•	Correo: usuario01@chiclana.com
	•	Contraseña: contrasena
	•	Alternativas:
		o	usuario02@chiclana.com
		o	usuario03@chiclana.com
		o	Todos utilizan la misma contraseña: contrasena


Despliegue en local

Para desplegar el proyecto en un entorno local, es necesario configurar tanto el backend como el frontend. A continuación, se detallan los pasos para cada uno:

	Backend

	1.	Prerequisitos:
		o	Java Development Kit (JDK) instalado.
		o	Eclipse u otro IDE similar instalado.
		o	Maven instalado.
		o	PostgreSQL instalado y configurado.

	2.	Configuración de la base de datos:
		o	Crear una base de datos en PostgreSQL.
		o	Configurar las credenciales de acceso a la base de datos en nuestro proyecto.
		o	Ejecutar la migración de la base de datos para la creación de la misma e introducir datos de prueba desde el fichero resources/db/migrations/script_creacion_bd.sql 

	3.	Configuración de las variables de entorno
		o	Configurar las variables de entorno en el archivo  .env. Credenciales de acceso a la base de datos, credenciales del correo y url del frontend.
		o	Estas variables se usarán en el application.properties del proyecto Spring Boot.

	4.	Clonación del repositorio:
		o	Clonar el repositorio del proyecto desde github. https://github.com/rvidalo/TFM-icl

	5.	Compilación y ejecución del backend:
		o	Abrir el proyecto en Eclipse.
		o	Ejecutar mvn clean install para compilar el proyecto y descargar las dependencias necesarias.
		o	Ejecutar la aplicación desde Eclipse o usando mvn spring-boot:run.


	Frontend

	1.	Prerequisitos:
		o	Node.js y npm instalados.
		o	Visual Studio Code u otro IDE similar instalado.

	2.	Configuración de las variables de entorno
		o	Configurar la variable de entorno con la url de la api del backend en el archivo  environment.ts. 

	3.	Clonación del repositorio:
		o	Clonar el repositorio del proyecto desde github. https://github.com/rvidalo/TFM-icl

	4.	Instalación de dependencias:
		o	Navegar al directorio del proyecto frontend y ejecutar npm install para instalar las dependencias.

	5.	Ejecución del frontend:
		o	Ejecutar ng serve para iniciar el servidor de desarrollo de Angular.
		o	Acceder a la aplicación desde el navegador en http://localhost:4200.


Despliegue en servidor

El despliegue en el servidor se ha realizado utilizando Netlify para el frontend y Render para el backend y la base de datos PostgreSQL. A continuación, se describen los pasos para cada uno:

	Backend

	1.	Configuración de la base de datos:
		o	Crear una cuenta en Render.
		o	Crear una base de datos PostgreSQL en Render.
		o	Conectarnos a ella y Ejecutar la migración de la base de datos para la creación de la misma e introducir datos de prueba desde el fichero resources/db/migrations/script_creacion_bd.sql 

	2.	Creación del servicio en Render:
		o	Crear un nuevo servicio web y seleccionar el repositorio del backend desde GitHub.
		o	Configurar las variables de entorno necesarias, incluyendo las credenciales de la base de datos, credenciales del correo y url del frontend.
		o	Desplegar el servicio, Render se encargará de construir y ejecutar la aplicación.
	 
	3.	Mantener el servicio activo:
		o	Debido a las limitaciones de la versión gratuita de Render, el servicio puede entrar en estado de suspensión tras 15 minutos de inactividad.
		o	Configurar UptimeRobot para realizar una consulta a la API cada 5 minutos, asegurando que el servicio permanezca operativo.
 

	Frontend

	1.	Despliegue en Netlify:
		o	Crear una cuenta en Netlify.
		o	Crear un nuevo sitio desde Git y seleccionar el repositorio del frontend.
		o	Configurar el comando de construcción como ng build y el directorio de salida como dist/.
		o	Desplegar el sitio. Netlify se encargará de la construcción y del despliegue de la aplicación.

	2.	Configuración del dominio:
		o	Configurar el dominio personalizado a https://tfm-icl.netlify.app que será la url de acceso a la aplicación.
