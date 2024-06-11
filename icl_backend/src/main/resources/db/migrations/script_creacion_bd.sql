-- Eliminar tablas
-- DROP TABLE IF EXISTS canjeados;
-- DROP TABLE IF EXISTS vales;
-- DROP TABLE IF EXISTS negocios;
-- DROP TABLE IF EXISTS usuarios;
-- DROP TABLE IF EXISTS tipo_negocio;

CREATE TABLE tipo_negocio (
  id SERIAL PRIMARY KEY,
  descripcion VARCHAR(100) NOT NULL
);

CREATE TABLE usuarios (
  id SERIAL PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  apellidos VARCHAR(100) NOT NULL,
  documento VARCHAR(100) NOT NULL UNIQUE,
  email VARCHAR(100) NOT NULL UNIQUE,
  contrasena VARCHAR(100) NOT NULL,
  admin BOOLEAN NOT NULL,
  fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE negocios (
  id SERIAL PRIMARY KEY,
  cif VARCHAR(100) NOT NULL UNIQUE,
  email VARCHAR(100) NOT NULL UNIQUE,
  nombre VARCHAR(100) NOT NULL,
  tipo INT NOT NULL,
  direccion VARCHAR(100) NOT NULL,
  valor_total DECIMAL(6,2) NOT NULL,
  contrasena VARCHAR(100) NOT NULL,
  estado INT NOT NULL,
  fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_tipo FOREIGN KEY (tipo) REFERENCES tipo_negocio (id),
  CONSTRAINT chk_estado CHECK (estado IN (1, 2, 3))
);

CREATE TABLE vales (
  id SERIAL PRIMARY KEY,
  usuario INT NOT NULL,
  fecha_limite DATE NOT NULL,
  valor_total DECIMAL(5,2) NOT NULL,
  qr VARCHAR(20) NOT NULL,
  fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_usuario FOREIGN KEY (usuario) REFERENCES usuarios (id),
  UNIQUE (usuario)
);

CREATE TABLE canjeados (
  id SERIAL PRIMARY KEY,
  vale INT NOT NULL,
  negocio INT NOT NULL,
  total DECIMAL(5,2) NOT NULL,
  descuento DECIMAL(5,2) NOT NULL,
  fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_vale FOREIGN KEY (vale) REFERENCES vales (id),
  CONSTRAINT fk_negocio FOREIGN KEY (negocio) REFERENCES negocios (id)
);


INSERT INTO tipo_negocio (descripcion) VALUES
	 ('Alimentación'),
	 ('Calzado'),
	 ('Deportes'),
	 ('Informática y electrodomésticos'),
	 ('Hogar'),
	 ('Joyería'),
	 ('Librería'),
	 ('Moda y complementos'),
	 ('Óptica'),
	 ('Restaurante y cafetería');

INSERT INTO usuarios (nombre,apellidos,documento,email,contrasena,admin,fecha_registro) VALUES
	 ('Admin1','Admin1Apellido','12345678A','admin1@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','TRUE','2024-05-17 18:48:00'),
	 ('Admin2','Admin2Apellido','87654321Z','admin2@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','TRUE','2024-05-17 18:48:00'),
	 ('Juan','García Rodríguez','12345678B','usuario1@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Ana','González López','23456789C','usuario2@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Manuel','Martínez Sánchez','34567890D','usuario3@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('María','Fernández Martín','45678901E','usuario4@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('David','López Pérez','56789012F','usuario5@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Carmen','Sánchez Gómez','67890123G','usuario6@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Luis','Martín Jiménez','78901234H','usuario7@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Laura','Pérez Moreno','89012345I','usuario8@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00');
INSERT INTO usuarios (nombre,apellidos,documento,email,contrasena,admin,fecha_registro) VALUES
	 ('Antonio','Gómez Álvarez','90123456J','usuario9@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Isabel','Jiménez Ruiz','01234567K','usuario10@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Francisco','Rodríguez Sánchez','12345678L','usuario11@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Lucía','González Pérez','23456789M','usuario12@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Javier','Martínez Gómez','34567890N','usuario13@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Sara','Fernández Martín','45678901O','usuario14@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Pablo','López López','56789012P','usuario15@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Elena','Sánchez Sánchez','67890123Q','usuario16@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Daniel','Martín Jiménez','78901234R','usuario17@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Cristina','Pérez Gómez','89012345S','usuario18@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00');
INSERT INTO usuarios (nombre,apellidos,documento,email,contrasena,admin,fecha_registro) VALUES
	 ('José','Gómez Pérez','90123456T','usuario19@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Marina','Martínez Martín','01234567U','usuario20@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Miguel','López Rodríguez','12345678V','usuario21@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Natalia','García López','23456789W','usuario22@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Carlos','Martínez Sánchez','34567890X','usuario23@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Paula','Fernández Gómez','45678901Y','usuario24@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Alberto','Gómez Pérez','56789012Z','usuario25@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Marta','Sánchez Martín','67890123A','usuario26@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Pedro','Martínez Gómez','78901234B','usuario27@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Elena','García Sánchez','89012345C','usuario28@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00');
INSERT INTO usuarios (nombre,apellidos,documento,email,contrasena,admin,fecha_registro) VALUES
	 ('Juan','Fernández Martínez','90123456D','usuario29@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Rosa','López López','01234567E','usuario30@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Alejandro','García Pérez','12345678F','usuario31@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Sofía','Martínez Sánchez','23456789G','usuario32@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Jorge','González Martín','34567890H','usuario33@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Cristina','Sánchez Gómez','45678901I','usuario34@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Daniel','López Rodríguez','56789012J','usuario35@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('María','Martínez López','67890123K','usuario36@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Alejandro','Gómez Martín','78901234L','usuario37@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Lucía','Fernández Gómez','89012345M','usuario38@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00');
INSERT INTO usuarios (nombre,apellidos,documento,email,contrasena,admin,fecha_registro) VALUES
	 ('Javier','Martín Martínez','90123456N','usuario39@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Nerea','García Sánchez','01234567O','usuario40@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Adrián','López Rodríguez','12345678P','usuario41@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Sara','Martínez Gómez','23456789Q','usuario42@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('José','González Pérez','34567890R','usuario43@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Elena','Sánchez Martínez','45678901S','usuario44@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('David','López Sánchez','56789012T','usuario45@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Laura','Martínez López','67890123U','usuario46@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Alejandro','Gómez Martínez','78901234V','usuario47@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Lucía','García Gómez','89012345W','usuario48@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00');
INSERT INTO usuarios (nombre,apellidos,documento,email,contrasena,admin,fecha_registro) VALUES
	 ('Sergio','Rodríguez Sánchez','90123456X','usuario49@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('María','Fernández Martínez','01234567Y','usuario50@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Carlos','Gómez Pérez','12345678Z','usuario51@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Sofía','Martín Gómez','23456789AA','usuario52@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Javier','García Sánchez','34567890AB','usuario53@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Elena','López López','45678901AC','usuario54@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('David','Martínez Martínez','56789012AD','usuario55@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('María','García Martín','67890123AE','usuario56@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Laura','Sánchez Gómez','78901234AF','usuario57@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Pablo','Martínez Sánchez','89012345AG','usuario58@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00');
INSERT INTO usuarios (nombre,apellidos,documento,email,contrasena,admin,fecha_registro) VALUES
	 ('Lucía','Gómez Pérez','90123456AH','usuario59@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Daniel','López Martínez','01234567AI','usuario60@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Cristina','García López','12345678AJ','usuario61@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Manuel','Martínez Rodríguez','23456789AK','usuario62@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Sara','Gómez Gómez','34567890AL','usuario63@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Juan','Fernández Pérez','45678901AM','usuario64@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Laura','Martín Martínez','56789012AN','usuario65@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Alejandro','García Sánchez','67890123AO','usuario66@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('María','Gómez López','78901234AP','usuario67@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Elena','Martínez Gómez','89012345AQ','usuario68@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00');
INSERT INTO usuarios (nombre,apellidos,documento,email,contrasena,admin,fecha_registro) VALUES
	 ('David','García Martín','90123456AR','usuario69@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Ana','López Martínez','01234567AS','usuario70@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Javier','Martín López','12345678AT','usuario71@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Sara','Gómez Martínez','23456789AU','usuario72@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Daniel','García Gómez','34567890AV','usuario73@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Lucía','Martínez Sánchez','45678901AW','usuario74@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('José','López García','56789012AX','usuario75@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('María','García Pérez','67890123AY','usuario76@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Manuel','Martínez Martín','78901234AZ','usuario77@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Elena','López Gómez','89012345BA','usuario78@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00');
INSERT INTO usuarios (nombre,apellidos,documento,email,contrasena,admin,fecha_registro) VALUES
	 ('Antonio','García Martínez','90123456BB','usuario79@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Laura','Martín López','01234567BC','usuario80@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Juan','Gómez Sánchez','12345678BD','usuario81@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Lucía','García Martín','23456789BE','usuario82@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('David','Martínez Pérez','34567890BF','usuario83@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Elena','Gómez Gómez','45678901BG','usuario84@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Javier','López Martínez','56789012BH','usuario85@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('María','Martín Gómez','67890123BI','usuario86@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Cristina','García Sánchez','78901234BJ','usuario87@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Daniel','Martínez López','89012345BK','usuario88@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00');
INSERT INTO usuarios (nombre,apellidos,documento,email,contrasena,admin,fecha_registro) VALUES
	 ('Sara','López Martínez','90123456BL','usuario89@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('David','García Gómez','01234567BM','usuario90@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Laura','Martín Sánchez','12345678BN','usuario91@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Lucas','Gómez López','23456789BO','usuario92@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Ana','García Martínez','34567890BP','usuario93@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Pablo','Martínez Pérez','45678901BQ','usuario94@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('María','Gómez Gómez','56789012BR','usuario95@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Sara','López Martinez','67890123BS','usuario96@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Javier','Martínez López','78901234BT','usuario97@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Lucía','García Martín','89012345BU','usuario98@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00');
INSERT INTO usuarios (nombre,apellidos,documento,email,contrasena,admin,fecha_registro) VALUES
	 ('Daniel','Gómez Sánchez','90123456BV','usuario99@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Ana','Martínez Martínez','01234567BW','usuario100@chiclana.com','$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK','FALSE','2024-05-17 18:48:00'),
	 ('Ricardo','Vidal Leza','76086756L','ricardovidalortiz@gmail.com','$2a$10$eoI1UzmaZBNvrh82oXn/8ed83qgc8rVR/qTLAQXzVBRpxhq96nW96','FALSE','2024-06-09 14:27:32');


INSERT INTO negocios (cif,email,nombre,tipo,direccion,valor_total,contrasena,estado,fecha_registro) VALUES
	 ('CIF1234','negocio1@chiclana.com','Supermercado El Mercado',1,'Calle Mayor 123',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',1,'2024-05-17 18:48:00'),
	 ('CIF5678','negocio2@chiclana.com','Zapatería La Rápida',2,'Avenida del Zapato 16',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',2,'2024-05-17 18:48:00'),
	 ('CIF91011','negocio3@chiclana.com','Deportes Chiclana',3,'Calle del Deporte 789',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',2,'2024-05-17 18:48:00'),
	 ('CIF121314','negocio4@chiclana.com','Electrohogar Tech',4,'Avenida de los Electrónicos 1011',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',1,'2024-05-17 18:48:00'),
	 ('CIF151617','negocio5@chiclana.com','Hogar Dulce Hogar',5,'Calle de las Casas 1213',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',2,'2024-05-17 18:48:00'),
	 ('CIF181920','negocio6@chiclana.com','Joyas Relucientes',6,'Plaza de los Diamantes 1415',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',2,'2024-05-17 18:48:00'),
	 ('CIF212223','negocio7@chiclana.com','Librería Páginas Doradas',7,'Paseo de los Libros 1617',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',1,'2024-05-17 18:48:00'),
	 ('CIF242526','negocio8@chiclana.com','Moda Chic',8,'Calle de la Moda 1819',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',2,'2024-05-17 18:48:00'),
	 ('CIF272829','negocio9@chiclana.com','Visión Perfecta',9,'Avenida de las Gafas 2021',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',2,'2024-05-17 18:48:00'),
	 ('CIF303132','negocio10@chiclana.com','Bar Restaurante La Parrilla',10,'Calle de los Sabores 2223',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',1,'2024-05-17 18:48:00');
INSERT INTO negocios (cif,email,nombre,tipo,direccion,valor_total,contrasena,estado,fecha_registro) VALUES
	 ('CIF333435','negocio11@chiclana.com','Panadería La Espiga',1,'Calle del Pan 2425',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',2,'2024-05-17 18:48:00'),
	 ('CIF363738','negocio12@chiclana.com','Tienda de Calzado La Comodidad',2,'Plaza del Zapato 2627',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',2,'2024-05-17 18:48:00'),
	 ('CIF394041','negocio13@chiclana.com','Deportes Romero',3,'Avenida del Fitness 2829',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',1,'2024-05-17 18:48:00'),
	 ('CIF424344','negocio14@chiclana.com','Electrochic',4,'Calle de los Electrónicos 3031',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',2,'2024-05-17 18:48:00'),
	 ('CIF454647','negocio15@chiclana.com','Casa del Hogar Chiclana',5,'Calle de los Hogares 3233',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',2,'2024-05-17 18:48:00'),
	 ('CIF484950','negocio16@chiclana.com','Joyería Lujo y Elegancia',6,'Plaza de los Rubíes 3435',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',1,'2024-05-17 18:48:00'),
	 ('CIF515253','negocio17@chiclana.com','Libros a tu Gusto',7,'Calle de las Letras 3637',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',2,'2024-05-17 18:48:00'),
	 ('CIF545556','negocio18@chiclana.com','Tendencias Fashion',8,'Avenida de la Moda 3839',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',2,'2024-05-17 18:48:00'),
	 ('CIF575859','negocio19@chiclana.com','Gafas Chic',9,'Calle de las Gafas 4041',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',1,'2024-05-17 18:48:00'),
	 ('CIF606162','negocio20@chiclana.com','Cafetería La Esquina',10,'Plaza de los Cafés 4243',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',2,'2024-05-17 18:48:00');
INSERT INTO negocios (cif,email,nombre,tipo,direccion,valor_total,contrasena,estado,fecha_registro) VALUES
	 ('CIF636465','negocio21@chiclana.com','Frutas y Verduras Fresh',1,'Avenida de las Frutas 4445',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',2,'2024-05-17 18:48:00'),
	 ('CIF666768','negocio22@chiclana.com','Zapatos Bernal',2,'Calle de los Zapatos 4647',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',1,'2024-05-17 18:48:00'),
	 ('CIF697071','negocio23@chiclana.com','Gimnasio MaxFit',3,'Plaza del Deporte 4849',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',2,'2024-05-17 18:48:00'),
	 ('CIF727374','negocio24@chiclana.com','Electrodomésticos ElectroMax',4,'Calle de los Electrodomésticos 5051',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',2,'2024-05-17 18:48:00'),
	 ('CIF757677','negocio25@chiclana.com','Decoración Chiclana',5,'Avenida de la Decoración 5253',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',1,'2024-05-17 18:48:00'),
	 ('CIF787980','negocio26@chiclana.com','Bijouterie Brillante',6,'Plaza de las Joyas 5455',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',2,'2024-05-17 18:48:00'),
	 ('CIF808182','negocio27@chiclana.com','Librería Bestsellers',7,'Calle de los Bestsellers 5657',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',2,'2024-05-17 18:48:00'),
	 ('CIF838485','negocio28@chiclana.com','Ropa para Todos',8,'Avenida de la Ropa 5859',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',1,'2024-05-17 18:48:00'),
	 ('CIF868788','negocio29@chiclana.com','Gafas de Sol Chiclana',9,'Calle del Sol 6061',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',2,'2024-05-17 18:48:00'),
	 ('CIF898990','negocio30@chiclana.com','Bar El Encuentro',10,'Plaza del Encuentro 6263',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',2,'2024-05-17 18:48:00');
INSERT INTO negocios (cif,email,nombre,tipo,direccion,valor_total,contrasena,estado,fecha_registro) VALUES
	 ('CIF919293','negocio31@chiclana.com','Supermercado Fresco',1,'Avenida del Fresco 6465',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',1,'2024-05-17 18:48:00'),
	 ('CIF949596','negocio32@chiclana.com','Calzados Juveniles',2,'Calle de la Juventud 6667',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',2,'2024-05-17 18:48:00'),
	 ('CIF979899','negocio33@chiclana.com','Gimnasio SportFit',3,'Plaza del Fitness 6869',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',3,'2024-05-17 18:48:00'),
	 ('CIF100101102','negocio34@chiclana.com','Electrohogar ElectroLux',4,'Avenida de la Tecnología 7071',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',1,'2024-05-17 18:48:00'),
	 ('CIF103104105','negocio35@chiclana.com','Muebles y Decoración Chiclana',5,'Calle de la Decoración 7273',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',2,'2024-05-17 18:48:00'),
	 ('CIF106107108','negocio36@chiclana.com','Joyería Diamante',6,'Plaza de las Joyas 7475',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',2,'2024-05-17 18:48:00'),
	 ('CIF109110111','negocio37@chiclana.com','Librería Leyendas',7,'Avenida de las Letras 7677',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',1,'2024-05-17 18:48:00'),
	 ('CIF112113114','negocio38@chiclana.com','Ropa Elegante',8,'Calle de la Elegancia 7879',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',2,'2024-05-17 18:48:00'),
	 ('CIF115116117','negocio39@chiclana.com','Óptica Chic',9,'Plaza de las Gafas 8081',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',2,'2024-05-17 18:48:00'),
	 ('CIF118119120','negocio40@chiclana.com','Cafetería del Parque',10,'Avenida del Parque 8283',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',1,'2024-05-17 18:48:00');
INSERT INTO negocios (cif,email,nombre,tipo,direccion,valor_total,contrasena,estado,fecha_registro) VALUES
	 ('CIF121122123','negocio41@chiclana.com','Pescadería La Mar',1,'Calle del Pescado 8485',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',2,'2024-05-17 18:48:00'),
	 ('CIF124125126','negocio42@chiclana.com','Zapatos de Fiesta',2,'Plaza de la Fiesta 8687',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',2,'2024-05-17 18:48:00'),
	 ('CIF127128129','negocio43@chiclana.com','Gimnasio Fitness World',3,'Avenida del Fitness 8891',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',1,'2024-05-17 18:48:00'),
	 ('CIF130131132','negocio44@chiclana.com','Electrodomésticos CasaModerna',4,'Calle de la Modernidad 9093',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',2,'2024-05-17 18:48:00'),
	 ('CIF133134135','negocio45@chiclana.com','Muebles Modernos',5,'Plaza de la Modernidad 9295',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',3,'2024-05-17 18:48:00'),
	 ('CIF136137138','negocio46@chiclana.com','Joyería Perla',6,'Avenida de las Perlas 9497',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',1,'2024-05-17 18:48:00'),
	 ('CIF139140141','negocio47@chiclana.com','Librería Páginas Infinitas',7,'Calle de los Libros 9699',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',2,'2024-05-17 18:48:00'),
	 ('CIF142143144','negocio48@chiclana.com','Ropa Casual',8,'Plaza de la Moda Casual 98101',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',3,'2024-05-17 18:48:00'),
	 ('CIF145146147','negocio49@chiclana.com','Óptica Visión Claro',9,'Avenida de la Vista 102103',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',1,'2024-05-17 18:48:00'),
	 ('CIF148149150','negocio50@chiclana.com','Cafetería Central',10,'Calle del Café 104105',1000.00,'$2a$10$Tf1M8Nj7zCypPeN2D9bv.uMzONi/ZY9p6X8oBDFwczCmKNCe3rxGK',2,'2024-05-17 18:48:00');


INSERT INTO vales (usuario,fecha_limite,valor_total,qr,fecha_registro) VALUES
	 (3,'2024-07-05',50.00,'R3wYl4v2t9KU5MSoI1Fc','2024-05-17 18:48:00'),
	 (4,'2024-07-05',50.00,'hB5p2wXe8cN3jGdYv0Qz','2024-05-17 18:48:00'),
	 (5,'2024-07-05',50.00,'fU6oYhP1sL9nZaVbK8gD','2024-05-17 18:48:00'),
	 (6,'2024-07-05',50.00,'iJ7wFhN3zQv6oRmBx1Vc','2024-05-17 18:48:00'),
	 (7,'2024-07-05',50.00,'dA4uRt9bYfCv0eI3nXsO','2024-05-17 18:48:00'),
	 (8,'2024-07-05',50.00,'lZ8sNtV1rE2mPqK3uIyF','2024-05-17 18:48:00'),
	 (9,'2024-07-05',50.00,'oG2dRfQ1jU8iWk5sY6hT','2024-05-17 18:48:00'),
	 (10,'2024-07-05',50.00,'aB7cNv3mZkLqXpJs9HdW','2024-05-17 18:48:00'),
	 (11,'2024-07-05',50.00,'vT6gDhN0yFqJmKzL8sVx','2024-05-17 18:48:00'),
	 (12,'2024-07-05',50.00,'nY4bUo2sCfWjRt7lMv1a','2024-05-17 18:48:00');
INSERT INTO vales (usuario,fecha_limite,valor_total,qr,fecha_registro) VALUES
	 (13,'2024-07-05',50.00,'eH5jQb4mYvNlKp3gWuXo','2024-05-17 18:48:00'),
	 (14,'2024-07-05',50.00,'uI9wGh5cDfRvTnYmKq2J','2024-05-17 18:48:00'),
	 (15,'2024-07-05',50.00,'mZ8lNp6rTjQbFs3WvKaE','2024-05-17 18:48:00'),
	 (16,'2024-07-05',50.00,'pX7sNt2kLrYjHv4cA9uW','2024-05-17 18:48:00'),
	 (17,'2024-07-05',50.00,'rE3mVb5fDqKlPn9cWzSx','2024-05-17 18:48:00'),
	 (18,'2024-07-05',50.00,'cO1vNz6kYgWbMl9sXtFj','2024-05-17 18:48:00'),
	 (19,'2024-07-05',50.00,'gD0hFv2nCzQxUw5lJmRa','2024-05-17 18:48:00'),
	 (20,'2024-07-05',50.00,'jK4sTn3vMpBcYf9qRiWl','2024-05-17 18:48:00'),
	 (21,'2024-07-05',50.00,'bU7iHk5xGzVqNs8mDwFj','2024-05-17 18:48:00'),
	 (22,'2024-07-05',50.00,'yF6dWs1oGvRmZk3cNtLq','2024-05-17 18:48:00');
INSERT INTO vales (usuario,fecha_limite,valor_total,qr,fecha_registro) VALUES
	 (23,'2024-07-05',50.00,'qP9vIc3sMkZxTfRjYl7n','2024-05-17 18:48:00'),
	 (24,'2024-07-05',50.00,'kX8bJw4uSrVnGt0hDyQa','2024-05-17 18:48:00'),
	 (25,'2024-07-05',50.00,'wE2gHf6vQjMnYz1sPcAq','2024-05-17 18:48:00'),
	 (26,'2024-07-05',50.00,'tL7pKm5yXnGcVfWqRiZb','2024-05-17 18:48:00'),
	 (27,'2024-07-05',50.00,'xN4cVb0sGkYjTm2qLrFw','2024-05-17 18:48:00'),
	 (28,'2024-07-05',50.00,'zS9uMq3rWtXkDf7nYbHl','2024-05-17 18:48:00'),
	 (29,'2024-07-05',50.00,'sA1vCz6fRqNmPb9jKwGt','2024-05-17 18:48:00'),
	 (30,'2024-07-05',50.00,'fD3rYx6sTnJgWmBvNqZl','2024-05-17 18:48:00'),
	 (31,'2024-07-05',50.00,'qL5xVj3pUkMgDwNcRzTs','2024-05-17 18:48:00'),
	 (32,'2024-07-05',50.00,'tR7kWm4dNfXbJc2qVzYh','2024-05-17 18:48:00');
INSERT INTO vales (usuario,fecha_limite,valor_total,qr,fecha_registro) VALUES
	 (33,'2024-07-05',50.00,'mF9jWv6oQs2rDaZgNpYl','2024-05-17 18:48:00'),
	 (34,'2024-07-05',50.00,'pH0sVf4cLqXwGy8mKzNr','2024-05-17 18:48:00'),
	 (35,'2024-07-05',50.00,'vI6yDs2qFaGwRt9nCpXl','2024-05-17 18:48:00'),
	 (36,'2024-07-05',50.00,'zB1nVt3qMlAxEy8sKuGf','2024-05-17 18:48:00'),
	 (37,'2024-07-05',50.00,'eU4cZv7mWnQxLr8kYgJs','2024-05-17 18:48:00'),
	 (38,'2024-07-05',50.00,'iY9kNf6tRsBcVzXjWqPl','2024-05-17 18:48:00'),
	 (39,'2024-07-05',50.00,'rX2sZn7gQkVcJt4yPwHb','2024-05-17 18:48:00'),
	 (40,'2024-07-05',50.00,'gA5vFr3cYqMlBn8sWdTp','2024-05-17 18:48:00'),
	 (41,'2024-07-05',50.00,'dP1jMx7yFtNwRq2lKbGs','2024-05-17 18:48:00'),
	 (42,'2024-07-05',50.00,'nH3wKc9gJbVdYm1sFzRl','2024-05-17 18:48:00');
INSERT INTO vales (usuario,fecha_limite,valor_total,qr,fecha_registro) VALUES
	 (43,'2024-07-05',50.00,'cL7vBd4qXrWfNs6tYjZp','2024-05-17 18:48:00'),
	 (44,'2024-07-05',50.00,'lM0jKp2rGtDzWn7qYxVs','2024-05-17 18:48:00'),
	 (45,'2024-07-05',50.00,'uR4kIg9wYbNqFm3vDpJl','2024-05-17 18:48:00'),
	 (46,'2024-07-05',50.00,'sF1nGj4tQwBmLx7cVrDz','2024-05-17 18:48:00'),
	 (47,'2024-07-05',50.00,'aX8cMv6qTfGjBn1dRzWs','2024-05-17 18:48:00'),
	 (48,'2024-07-05',50.00,'wQ5lFp9sJmKbDv0yZgNc','2024-05-17 18:48:00'),
	 (49,'2024-07-05',50.00,'yG7sLw4qVbMfRd3kXnJt','2024-05-17 18:48:00'),
	 (50,'2024-07-05',50.00,'bD2nWs5vHmKjTc7rYgQl','2024-05-17 18:48:00'),
	 (51,'2024-07-05',50.00,'oE9mVx3qUgDcZw1yFnPb','2024-05-17 18:48:00'),
	 (52,'2024-07-05',50.00,'hJ6rXs0wNpLzVf8yGcTm','2024-05-17 18:48:00');
INSERT INTO vales (usuario,fecha_limite,valor_total,qr,fecha_registro) VALUES
	 (98,'2024-06-18',50.00,'td8uebDsUQ6sJSbRzeD4','2024-05-18 11:41:44'),
	 (101,'2024-06-20',50.00,'fbAy2+lV+aOcoKtfRNyO','2024-05-20 19:17:29'),
	 (103,'2024-07-09',50.00,'s8+XddBf1/V31oYVw1U0','2024-06-09 14:29:56');


INSERT INTO canjeados (vale,negocio,total,descuento,fecha_registro) VALUES
	 (2,2,22.00,11.00,'2024-05-13 20:11:18'),
	 (2,2,22.10,11.05,'2024-05-13 20:14:25'),
	 (2,2,13.00,6.50,'2024-05-13 20:18:14'),
	 (2,2,3.00,1.50,'2024-05-13 20:19:27'),
	 (1,2,2.00,1.00,'2024-05-13 20:26:00'),
	 (3,2,12.00,6.00,'2024-05-18 12:06:37'),
	 (3,2,11.50,5.75,'2024-05-21 20:02:11'),
	 (3,2,3.25,1.62,'2024-05-21 20:09:54'),
	 (1,2,21.00,10.50,'2024-05-22 14:11:30'),
	 (1,2,23.00,11.50,'2024-05-22 14:12:10');
INSERT INTO canjeados (vale,negocio,total,descuento,fecha_registro) VALUES
	 (1,2,100.00,27.00,'2024-05-22 14:12:46'),
	 (1,2,10.00,0.00,'2024-05-22 14:16:40'),
	 (2,2,40.00,19.95,'2024-05-22 14:31:51'),
	 (3,2,2.50,1.25,'2024-05-22 19:52:13'),
	 (56,2,42.00,21.00,'2024-06-09 14:33:16');
