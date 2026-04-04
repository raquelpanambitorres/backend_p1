# Querys a ejecutar para tener la estructura de la base de datos
## CreateCamiones
CREATE TABLE camion (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    capacidad_carga_kg REAL NOT NULL,
    estado BOOLEAN NOT NULL DEFAULT TRUE,
    fecha_alta TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_ultima_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

## Insert Camiones
INSERT INTO public.camion
(id, nombre, capacidad_carga_kg, estado, fecha_alta, fecha_ultima_modificacion)
VALUES(2, 'Camión Toyota', 45000.0, true, '2026-04-02 17:41:35.684', '2026-04-02 17:41:35.684');

INSERT INTO public.camion
(id, nombre, capacidad_carga_kg, estado, fecha_alta, fecha_ultima_modificacion)
VALUES(1, 'Camión Toyota rojo', 45000.0, false, '2026-04-02 17:38:15.200', '2026-04-02 17:46:11.550');

## CreateClientes
CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    telefono VARCHAR(50) NOT NULL,
    direccion VARCHAR(50) NOT NULL
);

## Insert Clientes
INSERT INTO public.cliente
(id, nombre, apellido, telefono, direccion)
VALUES(1, 'Juan', 'Pérez', '+54 9 11 1234 5678', 'Av. Siempre Viva 742');

INSERT INTO public.cliente
(id, nombre, apellido, telefono, direccion)
VALUES(2, 'María', 'González', '341 400 1234', 'Calle Falsa 123');

## CreateReservas
CREATE TABLE reserva (
    id SERIAL PRIMARY KEY,
    fecha_reserva TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_cliente INT NOT NULL REFERENCES cliente(id),
    id_camion INT NOT NULL REFERENCES camion(id),
    fecha_desde TIMESTAMP NOT NULL,
    fecha_hasta TIMESTAMP NOT NULL,
    lugar_desde VARCHAR(50) NOT NULL,
    lugar_hasta VARCHAR(50) NOT NULL,
    peso_carga_kg REAL NOT NULL,
    estado BOOLEAN NOT NULL DEFAULT TRUE
);

## Insert Reservas
INSERT INTO public.reserva
(id, fecha_reserva, id_cliente, id_camion, fecha_desde, fecha_hasta, lugar_desde, lugar_hasta, peso_carga_kg, estado)
VALUES(1, '2026-04-04 18:00:00.000', 1, 2, '2026-04-10 08:00:00.000', '2026-04-11 18:00:00.000', 'Buenos Aires', 'Rosario', 12000.0, true);