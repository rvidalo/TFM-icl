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
    contrasena VARCHAR(100) NOT NULL,
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
    contrasena VARCHAR(100) NOT NULL,
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

--Migracion de datos
INSERT INTO icl.tipo_negocio (descripcion) VALUES
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

INSERT INTO negocios (cif, email, nombre, tipo, direccion, valor_total, contrasena, estado)
VALUES
    ('CIF1234', 'negocio1@chiclana.com', 'Supermercado El Mercado', 1, 'Calle Mayor 123', 300, 'contraseña1', 1),
    ('CIF5678', 'negocio2@chiclana.com', 'Zapatería La Rápida', 2, 'Avenida del Zapato 456', 300, 'contraseña2', 2),
    ('CIF91011', 'negocio3@chiclana.com', 'Deportes Chiclana', 3, 'Calle del Deporte 789', 300, 'contraseña3', 2),
    ('CIF121314', 'negocio4@chiclana.com', 'Electrohogar Tech', 4, 'Avenida de los Electrónicos 1011', 300, 'contraseña4', 1),
    ('CIF151617', 'negocio5@chiclana.com', 'Hogar Dulce Hogar', 5, 'Calle de las Casas 1213', 300, 'contraseña5', 2),
    ('CIF181920', 'negocio6@chiclana.com', 'Joyas Relucientes', 6, 'Plaza de los Diamantes 1415', 300, 'contraseña6', 2),
    ('CIF212223', 'negocio7@chiclana.com', 'Librería Páginas Doradas', 7, 'Paseo de los Libros 1617', 300, 'contraseña7', 1),
    ('CIF242526', 'negocio8@chiclana.com', 'Moda Chic', 8, 'Calle de la Moda 1819', 300, 'contraseña8', 2),
    ('CIF272829', 'negocio9@chiclana.com', 'Visión Perfecta', 9, 'Avenida de las Gafas 2021', 300, 'contraseña9', 2),
    ('CIF303132', 'negocio10@chiclana.com', 'Bar Restaurante La Parrilla', 10, 'Calle de los Sabores 2223', 300, 'contraseña10', 1),
    ('CIF333435', 'negocio11@chiclana.com', 'Panadería La Espiga', 1, 'Calle del Pan 2425', 300, 'contraseña11', 2),
    ('CIF363738', 'negocio12@chiclana.com', 'Tienda de Calzado La Comodidad', 2, 'Plaza del Zapato 2627', 300, 'contraseña12', 2),
    ('CIF394041', 'negocio13@chiclana.com', 'Deportes Romero', 3, 'Avenida del Fitness 2829', 300, 'contraseña13', 1),
    ('CIF424344', 'negocio14@chiclana.com', 'Electrochic', 4, 'Calle de los Electrónicos 3031', 300, 'contraseña14', 2),
    ('CIF454647', 'negocio15@chiclana.com', 'Casa del Hogar Chiclana', 5, 'Calle de los Hogares 3233', 300, 'contraseña15', 2),
    ('CIF484950', 'negocio16@chiclana.com', 'Joyería Lujo y Elegancia', 6, 'Plaza de los Rubíes 3435', 300, 'contraseña16', 1),
    ('CIF515253', 'negocio17@chiclana.com', 'Libros a tu Gusto', 7, 'Calle de las Letras 3637', 300, 'contraseña17', 2),
    ('CIF545556', 'negocio18@chiclana.com', 'Tendencias Fashion', 8, 'Avenida de la Moda 3839', 300, 'contraseña18', 2),
    ('CIF575859', 'negocio19@chiclana.com', 'Gafas Chic', 9, 'Calle de las Gafas 4041', 300, 'contraseña19', 1),
    ('CIF606162', 'negocio20@chiclana.com', 'Cafetería La Esquina', 10, 'Plaza de los Cafés 4243', 300, 'contraseña20', 2),
    ('CIF636465', 'negocio21@chiclana.com', 'Frutas y Verduras Fresh', 1, 'Avenida de las Frutas 4445', 300, 'contraseña21', 2),
    ('CIF666768', 'negocio22@chiclana.com', 'Zapatos Bernal', 2, 'Calle de los Zapatos 4647', 300, 'contraseña22', 1),
    ('CIF697071', 'negocio23@chiclana.com', 'Gimnasio MaxFit', 3, 'Plaza del Deporte 4849', 300, 'contraseña23', 2),
    ('CIF727374', 'negocio24@chiclana.com', 'Electrodomésticos ElectroMax', 4, 'Calle de los Electrodomésticos 5051', 300, 'contraseña24', 2),
    ('CIF757677', 'negocio25@chiclana.com', 'Decoración Chiclana', 5, 'Avenida de la Decoración 5253', 300, 'contraseña25', 1),
    ('CIF787980', 'negocio26@chiclana.com', 'Bijouterie Brillante', 6, 'Plaza de las Joyas 5455', 300, 'contraseña26', 2),
    ('CIF808182', 'negocio27@chiclana.com', 'Librería Bestsellers', 7, 'Calle de los Bestsellers 5657', 300, 'contraseña27', 2),
    ('CIF838485', 'negocio28@chiclana.com', 'Ropa para Todos', 8, 'Avenida de la Ropa 5859', 300, 'contraseña28', 1),
    ('CIF868788', 'negocio29@chiclana.com', 'Gafas de Sol Chiclana', 9, 'Calle del Sol 6061', 300, 'contraseña29', 2),
    ('CIF898990', 'negocio30@chiclana.com', 'Bar El Encuentro', 10, 'Plaza del Encuentro 6263', 300, 'contraseña30', 2),
    ('CIF919293', 'negocio31@chiclana.com', 'Supermercado Fresco', 1, 'Avenida del Fresco 6465', 300, 'contraseña31', 1),
    ('CIF949596', 'negocio32@chiclana.com', 'Calzados Juveniles', 2, 'Calle de la Juventud 6667', 300, 'contraseña32', 2),
    ('CIF979899', 'negocio33@chiclana.com', 'Gimnasio SportFit', 3, 'Plaza del Fitness 6869', 300, 'contraseña33', 3),
    ('CIF100101102', 'negocio34@chiclana.com', 'Electrohogar ElectroLux', 4, 'Avenida de la Tecnología 7071', 300, 'contraseña34', 1),
    ('CIF103104105', 'negocio35@chiclana.com', 'Muebles y Decoración Chiclana', 5, 'Calle de la Decoración 7273', 300, 'contraseña35', 2),
    ('CIF106107108', 'negocio36@chiclana.com', 'Joyería Diamante', 6, 'Plaza de las Joyas 7475', 300, 'contraseña36', 2),
    ('CIF109110111', 'negocio37@chiclana.com', 'Librería Leyendas', 7, 'Avenida de las Letras 7677', 300, 'contraseña37', 1),
    ('CIF112113114', 'negocio38@chiclana.com', 'Ropa Elegante', 8, 'Calle de la Elegancia 7879', 300, 'contraseña38', 2),
    ('CIF115116117', 'negocio39@chiclana.com', 'Óptica Chic', 9, 'Plaza de las Gafas 8081', 300, 'contraseña39', 2),
    ('CIF118119120', 'negocio40@chiclana.com', 'Cafetería del Parque', 10, 'Avenida del Parque 8283', 300, 'contraseña40', 1),
    ('CIF121122123', 'negocio41@chiclana.com', 'Pescadería La Mar', 1, 'Calle del Pescado 8485', 300, 'contraseña41', 2),
    ('CIF124125126', 'negocio42@chiclana.com', 'Zapatos de Fiesta', 2, 'Plaza de la Fiesta 8687', 300, 'contraseña42', 2),
    ('CIF127128129', 'negocio43@chiclana.com', 'Gimnasio Fitness World', 3, 'Avenida del Fitness 8891', 300, 'contraseña43', 1),
    ('CIF130131132', 'negocio44@chiclana.com', 'Electrodomésticos CasaModerna', 4, 'Calle de la Modernidad 9093', 300, 'contraseña44', 2),
    ('CIF133134135', 'negocio45@chiclana.com', 'Muebles Modernos', 5, 'Plaza de la Modernidad 9295', 300, 'contraseña45', 3),
    ('CIF136137138', 'negocio46@chiclana.com', 'Joyería Perla', 6, 'Avenida de las Perlas 9497', 300, 'contraseña46', 1),
    ('CIF139140141', 'negocio47@chiclana.com', 'Librería Páginas Infinitas', 7, 'Calle de los Libros 9699', 300, 'contraseña47', 2),
    ('CIF142143144', 'negocio48@chiclana.com', 'Ropa Casual', 8, 'Plaza de la Moda Casual 98101', 300, 'contraseña48', 3),
    ('CIF145146147', 'negocio49@chiclana.com', 'Óptica Visión Claro', 9, 'Avenida de la Vista 102103', 300, 'contraseña49', 1),
    ('CIF148149150', 'negocio50@chiclana.com', 'Cafetería Central', 10, 'Calle del Café 104105', 300, 'contraseña50', 2);
