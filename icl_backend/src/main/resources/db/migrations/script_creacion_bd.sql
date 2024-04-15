-- Eliminar tablas
-- DROP TABLE IF EXISTS canjeados;
-- DROP TABLE IF EXISTS vales;
-- DROP TABLE IF EXISTS negocios;
-- DROP TABLE IF EXISTS usuarios;
-- DROP TABLE IF EXISTS tipo_negocio;

-- Tabla usuarios
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    documento VARCHAR(100) UNIQUE NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    contraseña VARCHAR(100) NOT NULL,
    admin BOOLEAN NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE tipo_negocio (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(100) NOT NULL
);

-- Tabla negocios
CREATE TABLE negocios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cif VARCHAR(100) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    tipo INT NOT NULL,
    FOREIGN KEY (tipo) REFERENCES tipo_negocio(id),
    direccion VARCHAR(100) NOT NULL,
    valor_total DECIMAL(5,2) NOT NULL,
    contraseña VARCHAR(100) NOT NULL,
    estado INT NOT NULL CHECK (estado IN (1, 2, 3)),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla vales
CREATE TABLE vales (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario INT NOT NULL,
    FOREIGN KEY (usuario) REFERENCES usuarios(id),
    fecha_limite DATE NOT NULL,
    valor_total DECIMAL(5,2) NOT NULL,
    lote INT NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla canjeados
CREATE TABLE canjeados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vale INT NOT NULL,
    FOREIGN KEY (vale) REFERENCES vales(id),
    negocio INT NOT NULL,
    FOREIGN KEY (negocio) REFERENCES negocios(id),
    usuario INT NOT NULL,
    FOREIGN KEY (usuario) REFERENCES usuarios(id),
    total DECIMAL(5,2) NOT NULL,
    descuento DECIMAL(5,2) NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
