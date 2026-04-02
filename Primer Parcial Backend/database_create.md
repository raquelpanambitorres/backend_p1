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