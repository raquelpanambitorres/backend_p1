# Base de Datos: Sistema de Gestión de Alquiler de Camiones

## Modelo Entidad-Relación

Actualmente el sistema cuenta con una única entidad inicial que representa a los **Camiones**.

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

## Relaciones

_En esta fase inicial, no hay relaciones establecidas. Próximamente se pueden añadir tablas como `cliente` o `reserva`._
