
create schema bibliotecabd;
use bibliotecabd;

CREATE TABLE LIBRO (
  LIBRO_ID int PRIMARY KEY AUTO_INCREMENT ,
  TITULO varchar(50),
  AUTOR varchar(50),
  EDITORIAL varchar(20),
  IDIOMA varchar(20),
  DESCRIPCION varchar(100),
  MULTA decimal(5,2) CHECK (MULTA >= 0),
  STOCK_ACTUAL INT,
  CANTIDAD_PRESTADO INT
  
) ;

CREATE TABLE CLIENTE(
CLIENTE_ID int PRIMARY KEY AUTO_INCREMENT,
DNI char(8) UNIQUE,
NOMBRE VARCHAR(15),
APELLIDO_PA VARCHAR(20),
APELLIDO_MA VARCHAR(20),
DIRECCION VARCHAR(50),
TELEFONO INT,
EMAIL VARCHAR(50)
) ;

CREATE TABLE PRESTAMO(
PRESTAMO_ID int PRIMARY KEY AUTO_INCREMENT,
FECHA_PRESTAMO DATE,
FECHA_DEVOLUCION DATE,
CLIENTE_ID INT,
CANTIDAD INT,
ESTADO VARCHAR(20) DEFAULT 'Pendiente',
MULTA_TOTAL DECIMAL(10,5),
FOREIGN KEY (CLIENTE_ID) REFERENCES CLIENTE(CLIENTE_ID)
) ;
CREATE TABLE DETALLE_PRESTAMO (
    DETALLE_ID INT AUTO_INCREMENT PRIMARY KEY,
    LIBRO_ID INT,
    PRESTAMO_Id INT,
    ESTADO VARCHAR(20),
    MULTARETRASO DECIMAL(10, 2),
    FOREIGN KEY (LIBRO_ID) REFERENCES LIBRO(LIBRO_ID),
    FOREIGN KEY (PRESTAMO_Id) REFERENCES PRESTAMO(PRESTAMO_Id)
);


insert into Libro(TITULO, AUTOR, EDITORIAL, IDIOMA, DESCRIPCION, multa, stock_Actual, cantidad_prestado)
	values('Cien Años de Soledad', 'Gabriel García Márquez', 'Sudamericana', 'Español', 'Una obra clásica de realismo mágico.', 1.50, 10, 3),
('Don Quijote de la Mancha', 'Miguel de Cervantes', 'Francisco de Robles', 'Español', 'Un caballero y su fiel escudero en sus aventuras.', 1.00, 8, 2),
('1984', 'George Orwell', 'Secker & Warburg', 'Inglés', 'Distopía que critica el autoritarismo.', 2.00, 12, 5),
('El Principito', 'Antoine de Saint-Exupéry', 'Gallimard', 'Español', 'Historia poética y filosófica.', 0.75, 15, 4),
('Orgullo y Prejuicio', 'Jane Austen', 'Thomas Egerton', 'Inglés', 'Una novela romántica clásica.', 1.25, 6, 1),
('Harry Potter y la Piedra Filosofal', 'J.K. Rowling', 'Salamandra', 'Español', 'Primera entrega de la serie mágica.', 2.50, 20, 8),
('Los Juegos del Hambre', 'Suzanne Collins', 'Scholastic', 'Español', 'Distopía juvenil de supervivencia.', 1.50, 14, 6),
('Crónica de una Muerte Anunciada', 'Gabriel García Márquez', 'Sudamericana', 'Español', 'Historia de un asesinato anunciado.', 1.20, 7, 3),
('Fahrenheit 451', 'Ray Bradbury', 'Ballantine Books', 'Español', 'Censura y quema de libros en una sociedad futura.', 1.80, 9, 5),
('El Señor de los Anillos', 'J.R.R. Tolkien', 'George Allen & Unwin', 'Español', 'Una épica fantasía en la Tierra Media.', 3.00, 5, 2),
('La Odisea', 'Homero', 'Clásicos Griegos', 'Español', 'Viajes y aventuras del héroe Odiseo.', 0.50, 10, 1),
('Hamlet', 'William Shakespeare', 'Clásicos Ingleses', 'Inglés', 'La tragedia del príncipe de Dinamarca.', 1.75, 6, 3),
('El Alquimista', 'Paulo Coelho', 'Planeta', 'Español', 'Un viaje espiritual y de autodescubrimiento.', 1.30, 8, 4),
('Drácula', 'Bram Stoker', 'Archibald Constable', 'Español', 'La novela gótica del vampiro más famoso.', 2.10, 4, 2),
('Matar a un Ruiseñor', 'Harper Lee', 'J.B. Lippincott', 'Español', 'La lucha por la justicia en el sur de EE.UU.', 1.90, 9, 4);

    
insert into cliente(DNI, NOMBRE, APELLIDO_PA, APELLIDO_MA, DIRECCION, TELEFONO, EMAIL)
	values ('12345678', 'Juan', 'Pérez', 'García', 'Av. Siempre Viva 123', 987654321, 'juan.perez@gmail.com'),
('87654321', 'María', 'López', 'Martínez', 'Calle Luna 456', 976543210, 'maria.lopez@hotmail.com'),
('56781234', 'Carlos', 'Sánchez', 'Hernández', 'Jirón Sol 789', 987321456, 'carlos.sanchez@yahoo.com'),
('43218765', 'Ana', 'Ramírez', 'Flores', 'Av. Estrella 321', 956874123, 'ana.ramirez@outlook.com'),
('11223344', 'Luis', 'Torres', 'Velásquez', 'Calle Oro 654', 965478321, 'luis.torres@gmail.com'),
('99887766', 'Lucía', 'Morales', 'Reyes', 'Jirón Plata 876', 912345678, 'lucia.morales@gmail.com'),
('33445566', 'Pedro', 'Gómez', 'Castro', 'Av. Sol 543', 923456789, 'pedro.gomez@yahoo.com'),
('55667788', 'Elena', 'Cruz', 'Domínguez', 'Calle Verde 111', 945612378, 'elena.cruz@outlook.com'),
('77889900', 'Roberto', 'Fernández', 'Pineda', 'Av. Mar 222', 967123987, 'roberto.fernandez@gmail.com'),
('22334455', 'Claudia', 'Martínez', 'Rojas', 'Jirón Luna 777', 976543890, 'claudia.martinez@gmail.com'),
('11224433', 'Francisco', 'Jiménez', 'Soto', 'Calle Río 888', 985123765, 'francisco.jimenez@hotmail.com'),
('99882211', 'Gabriela', 'Vargas', 'Flores', 'Av. Bosque 999', 923876543, 'gabriela.vargas@gmail.com'),
('66778899', 'Fernando', 'Peña', 'López', 'Jirón Coral 123', 987654312, 'fernando.pena@outlook.com'),
('44556677', 'Valeria', 'Pérez', 'Montes', 'Calle Azul 456', 965478213, 'valeria.perez@gmail.com'),
('55667744', 'Hugo', 'Ramón', 'Salas', 'Av. Andes 789', 912345789, 'hugo.ramon@yahoo.com');


INSERT INTO PRESTAMO (FECHA_PRESTAMO, FECHA_DEVOLUCION, CLIENTE_ID, CANTIDAD, ESTADO, MULTA_TOTAL)
VALUES
('2024-01-01', '2024-01-15', 1, 2, 'Pendiente', 0.00),
('2024-02-05', '2024-02-20', 2, 1, 'Pendiente', 0.00),
('2024-03-10', '2024-03-25', 3, 3, 'Devuelto', 1.50),
('2024-04-12', '2024-04-26', 4, 1, 'Pendiente', 0.00),
('2024-05-01', '2024-05-15', 5, 2, 'Devuelto', 0.50),
('2024-06-18', '2024-07-02', 6, 4, 'Prestado', 2.00),
('2024-07-22', '2024-08-05', 7, 3, 'Pendiente', 1.20),
('2024-09-14', '2024-09-28', 8, 2, 'Pendiente', 0.00),
('2024-10-01', '2024-10-15', 9, 5, 'Devuelto', 2.50),
('2024-11-11', '2024-11-25', 10, 2, 'Pendiente', 0.00);


INSERT INTO DETALLE_PRESTAMO (LIBRO_ID, PRESTAMO_ID)
VALUES
(1, 1), (2, 1), (3, 2), (4, 3), (5, 3), (6, 3),
(7, 4), (8, 5), (9, 5), (10, 6), (11, 6), (12, 6),
(13, 7), (14, 8), (15, 9), (1, 10);
    
    