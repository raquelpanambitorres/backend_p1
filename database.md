# Base de Datos: Sistema de Gestión de Alquiler de Camiones

## Modelo Entidad-Relación

El sistema cuenta con tres entidades principales: **Camiones**, **Clientes** y **Reservas**. Las reservas relacionan clientes con camiones para periodos específicos de alquiler.

## Definición de Tablas

### Tabla `camion`

Almacena la información de los camiones disponibles para alquiler o flete. Incorpora eliminación lógica a través del campo `estado`.

| Columna | Tipo de Dato | Llave | Modificadores | Descripción |
|---------|--------------|------|---------------|-------------|
| `id` | `SERIAL` / `INT` | PK | `NOT NULL`, Auto-Incremental | Identificador único del camión |
| `nombre` | `VARCHAR(50)` | | `NOT NULL` | Nombre o identificación del camión |
| `capacidad_carga_kg` | `REAL` / `FLOAT` | | `NOT NULL` | Capacidad máxima de carga del camión en kilogramos |
| `estado` | `BOOLEAN` | | `NOT NULL`, `DEFAULT TRUE` | Estado lógico para soft-delete (true: Activo, false: Eliminado) |
| `fecha_alta` | `TIMESTAMP` | | `DEFAULT CURRENT_TIMESTAMP` | Fecha de registro del camión en el sistema |
| `fecha_ultima_modificacion` | `TIMESTAMP` | | `DEFAULT CURRENT_TIMESTAMP` | Fecha de la última edición de los datos |

### Tabla `cliente`

Almacena la información de los clientes que pueden realizar reservas de camiones.

| Columna | Tipo de Dato | Llave | Modificadores | Descripción |
|---------|--------------|------|---------------|-------------|
| `id` | `SERIAL` / `INT` | PK | `NOT NULL`, Auto-Incremental | Identificador único del cliente |
| `nombre` | `VARCHAR(50)` | | `NOT NULL` | Nombre del cliente |
| `apellido` | `VARCHAR(50)` | | `NOT NULL` | Apellido del cliente |
| `telefono` | `VARCHAR(50)` | | `NOT NULL` | Número de teléfono del cliente |
| `direccion` | `VARCHAR(50)` | | `NOT NULL` | Dirección del cliente |

### Tabla `reserva`

Registra las reservas de camiones realizadas por clientes. Incluye eliminación lógica y gestión automática de disponibilidad de camiones.

| Columna | Tipo de Dato | Llave | Modificadores | Descripción |
|---------|--------------|------|---------------|-------------|
| `id` | `SERIAL` / `INT` | PK | `NOT NULL`, Auto-Incremental | Identificador único de la reserva |
| `fecha_reserva` | `TIMESTAMP` | | `DEFAULT CURRENT_TIMESTAMP` | Fecha y hora en que se realizó la reserva |
| `id_cliente` | `INT` | FK | `NOT NULL` | Referencia al cliente que realiza la reserva |
| `id_camion` | `INT` | FK | `NOT NULL` | Referencia al camión reservado |
| `fecha_desde` | `TIMESTAMP` | | `NOT NULL` | Fecha y hora de inicio de la reserva |
| `fecha_hasta` | `TIMESTAMP` | | `NOT NULL` | Fecha y hora de fin de la reserva |
| `lugar_desde` | `VARCHAR(50)` | | `NOT NULL` | Lugar de origen del flete |
| `lugar_hasta` | `VARCHAR(50)` | | `NOT NULL` | Lugar de destino del flete |
| `peso_carga_kg` | `REAL` / `FLOAT` | | `NOT NULL` | Peso de la carga a transportar en kilogramos |
| `estado` | `BOOLEAN` | | `NOT NULL`, `DEFAULT TRUE` | Estado de la reserva (true: Activa, false: Completada/Cancelada) |

## Relaciones

- **Cliente - Reserva**: Un cliente puede tener múltiples reservas (1:N). La tabla `reserva` referencia `cliente.id` a través de `id_cliente`.
- **Camion - Reserva**: Un camión puede tener múltiples reservas en diferentes periodos (1:N). La tabla `reserva` referencia `camion.id` a través de `id_camion`.
- Las claves foráneas están configuradas con restricciones de integridad referencial para mantener la consistencia de datos.
