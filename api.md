# API Documentación - Camiones REST API

URL Base: `/api/camiones`

## Estructura de DTOs

### `CamionRequestDTO` (Payload para Creación y Actualización)
```json
{
  "nombre": "Camión Volvo FMX",  // Requerido, máximo 50 caracteres
  "capacidadCargaKg": 15000.50 // Requerido, debe ser mayor a 0
}
```

### `CamionResponseDTO` (Respuesta de la API)
```json
{
  "id": 1,
  "nombre": "Camión Volvo FMX",
  "capacidadCargaKg": 15000.50,
  "estado": true,
  "fechaAlta": "2026-04-02T18:00:00Z",
  "fechaUltimaModificacion": "2026-04-02T18:00:00Z"
}
```

## Endpoints Disponibles

### 1. Crear un Camión
- **Método HTTP:** `POST`
- **Ruta:** `/api/camiones`
- **Códigos de Estado:**
  - `201 Created`: Camión creado satisfactoriamente.
  - `400 Bad Request`: Si el payload es incorrecto o falla la validación.
- **Request (Ejemplo):**
  ```json
  {
    "nombre": "Camión Scania",
    "capacidadCargaKg": 25000.0
  }
  ```
- **Response (Ejemplo):** Retorna `CamionResponseDTO` con ID y fechas generadas.

### 2. Obtener Lista de Camiones
- **Método HTTP:** `GET`
- **Ruta:** `/api/camiones`
- **Descripción:** Obtiene únicamente los camiones que cuenten con su estado activo (`estado = true`). Además permite filtrar por disponibilidad en un rango de fechas y por capacidad de carga.
- **Parámetros de consulta:**
  - `fechaDesde` (opcional): Fecha de inicio del período de reserva. Formato `YYYY-MM-DD HH:MM:SS`.
  - `fechaHasta` (opcional): Fecha de fin del período de reserva. Formato `YYYY-MM-DD HH:MM:SS`.
  - `capacidadCargaKg` (opcional): Capacidad mínima de carga requerida en kilogramos.
- **Validaciones:**
  - Si se proporcionan `fechaDesde` y `fechaHasta`, `fechaDesde` no puede ser posterior a `fechaHasta`.
  - Si se proporciona `fechaDesde`, no puede ser anterior a la fecha actual.
- **Códigos de Estado:** `200 OK`
- **Response (Ejemplo):**
  ```json
  [
    {
      "id": 1,
      "nombre": "Camión Scania",
      "capacidadCargaKg": 25000.0,
      "estado": true,
      "fechaAlta": "2026-04-02T15:00:00.000",
      "fechaUltimaModificacion": "2026-04-02T15:00:00.000"
    }
  ]
  ```

### 3. Obtener un Camión por ID
- **Método HTTP:** `GET`
- **Ruta:** `/api/camiones/{id}`
- **Códigos de Estado:**
  - `200 OK`: Camión encontrado.
  - `404 Not Found`: Si el ID no existe en la base de datos.
- **Response (Ejemplo):** Retorna un único `CamionResponseDTO`.

### 4. Actualizar Datos de un Camión
- **Método HTTP:** `PUT`
- **Ruta:** `/api/camiones/{id}`
- **Códigos de Estado:**
  - `200 OK`: Actualización exitosa.
  - `400 Bad Request`: Fallos de validación en DTO.
  - `404 Not Found`: Camión inexistente.
- **Request (Ejemplo):** (Misma estructura que POST).
- **Response (Ejemplo):** Retorna `CamionResponseDTO` con campos actualizados y nuevo timestamp de modificación.

### 5. Eliminar un Camión (Eliminación Lógica)
- **Método HTTP:** `DELETE`
- **Ruta:** `/api/camiones/{id}`
- **Descripción:** Cambia el flag `estado` a `false`. No elimina la fila de la DB.
- **Códigos de Estado:**
  - `204 No Content`: Eliminado correctamente.
  - `404 Not Found`: El ID suministrado ya fue eliminado o no existe.

# API Documentación - Clientes REST API

URL Base: `/api/clientes`

## Estructura de DTOs

### `ClienteRequestDTO` (Payload para Creación y Actualización)
```json
{
  "nombre": "Juan",
  "apellido": "Pérez",
  "telefono": "+54 9 11 1234 5678",
  "direccion": "Av. Siempre Viva 742"
}
```

### `ClienteResponseDTO` (Respuesta de la API)
```json
{
  "id": 1,
  "nombre": "Juan",
  "apellido": "Pérez",
  "telefono": "+54 9 11 1234 5678",
  "direccion": "Av. Siempre Viva 742"
}
```

## Endpoints Disponibles

### 1. Crear un Cliente
- **Método HTTP:** `POST`
- **Ruta:** `/api/clientes`
- **Códigos de Estado:**
  - `201 Created`: Cliente creado satisfactoriamente.
  - `400 Bad Request`: Si el payload es incorrecto o falla la validación.
- **Request (Ejemplo):**
  ```json
  {
    "nombre": "María",
    "apellido": "González",
    "telefono": "341 400 1234",
    "direccion": "Calle Falsa 123"
  }
  ```
- **Response (Ejemplo):** Retorna `ClienteResponseDTO` con ID generado.

### 2. Obtener Lista de Clientes
- **Método HTTP:** `GET`
- **Ruta:** `/api/clientes`
- **Descripción:** Devuelve todos los clientes registrados.
- **Códigos de Estado:** `200 OK`
- **Response (Ejemplo):**
  ```json
  [
    {
      "id": 1,
      "nombre": "María",
      "apellido": "González",
      "telefono": "341 400 1234",
      "direccion": "Calle Falsa 123"
    }
  ]
  ```

### 3. Obtener un Cliente por ID
- **Método HTTP:** `GET`
- **Ruta:** `/api/clientes/{id}`
- **Códigos de Estado:**
  - `200 OK`: Cliente encontrado.
  - `404 Not Found`: Si el ID no existe.
- **Response (Ejemplo):** Retorna un único `ClienteResponseDTO`.

### 4. Actualizar Datos de un Cliente
- **Método HTTP:** `PUT`
- **Ruta:** `/api/clientes/{id}`
- **Códigos de Estado:**
  - `200 OK`: Actualización exitosa.
  - `400 Bad Request`: Fallos de validación en DTO.
  - `404 Not Found`: Cliente inexistente.
- **Request (Ejemplo):** (Misma estructura que `POST`).
- **Response (Ejemplo):** Retorna `ClienteResponseDTO` con los campos actualizados.

### 5. Eliminar un Cliente
- **Método HTTP:** `DELETE`
- **Ruta:** `/api/clientes/{id}`
- **Descripción:** Elimina el cliente registrado de la base de datos.
- **Códigos de Estado:**
  - `204 No Content`: Eliminado correctamente.
  - `404 Not Found`: El ID suministrado no existe.

# API Documentación - Reservas REST API

URL Base: `/api/reservas`

## Estructura de DTOs

### `ReservaRequestDTO` (Payload para Creación)
```json
{
  "idCliente": 1,
  "idCamion": 2,
  "fechaDesde": "2026-04-10 08:00:00",
  "fechaHasta": "2026-04-11 18:00:00",
  "lugarDesde": "Buenos Aires",
  "lugarHasta": "Rosario",
  "pesoCargaKg": 12000.0
}
```
- `idCliente`: Obligatorio.
- `idCamion`: Opcional. Si no se provee, el sistema asigna un camión disponible con capacidad suficiente.
- `fechaDesde`: Obligatorio.
- `fechaHasta`: Obligatorio.
- `lugarDesde`: Obligatorio, máximo 50 caracteres.
- `lugarHasta`: Obligatorio, máximo 50 caracteres.
- `pesoCargaKg`: Obligatorio, mayor a 0.

#### `ReservaResponseDTO` (Respuesta de la API)
```json
{
  "id": 1,
  "fechaReserva": "2026-04-04T18:00:00.000Z",
  "idCliente": 1,
  "idCamion": 2,
  "fechaDesde": "2026-04-10T08:00:00.000Z",
  "fechaHasta": "2026-04-11T18:00:00.000Z",
  "lugarDesde": "Buenos Aires",
  "lugarHasta": "Rosario",
  "pesoCargaKg": 12000.0,
  "estado": true
}
```

## Endpoints Disponibles

### 1. Crear una Reserva
- **Método HTTP:** `POST`
- **Ruta:** `/api/reservas`
- **Códigos de Estado:**
  - `201 Created`: Reserva creada satisfactoriamente.
  - `400 Bad Request`: Validaciones de DTO fallaron o fechas inválidas.
  - `404 Not Found`: Cliente o camión no encontrado, o no hay camión disponible con la capacidad solicitada.
- **Request (Ejemplo):**
  ```json
  {
    "idCliente": 1,
    "idCamion": 2,
    "fechaDesde": "2026-04-10 08:00:00",
    "fechaHasta": "2026-04-11 18:00:00",
    "lugarDesde": "Buenos Aires",
    "lugarHasta": "Rosario",
    "pesoCargaKg": 12000.0
  }
  ```
- **Response (Ejemplo):** Retorna `ReservaResponseDTO` con ID y estado asignado.

### 2. Obtener Lista de Reservas Activas
- **Método HTTP:** `GET`
- **Ruta:** `/api/reservas`
- **Descripción:** Devuelve sólo las reservas con `estado = true`.
- **Códigos de Estado:** `200 OK`
- **Response (Ejemplo):**
  ```json
  [
    {
      "id": 1,
      "fechaReserva": "2026-04-04T18:00:00.000Z",
      "idCliente": 1,
      "idCamion": 2,
      "fechaDesde": "2026-04-10T08:00:00.000Z",
      "fechaHasta": "2026-04-11T18:00:00.000Z",
      "lugarDesde": "Buenos Aires",
      "lugarHasta": "Rosario",
      "pesoCargaKg": 12000.0,
      "estado": true
    }
  ]
  ```

### 3. Obtener una Reserva por ID
- **Método HTTP:** `GET`
- **Ruta:** `/api/reservas/{id}`
- **Códigos de Estado:**
  - `200 OK`: Reserva encontrada.
  - `404 Not Found`: Si el ID no existe.
- **Response (Ejemplo):** Retorna un único `ReservaResponseDTO`.

### 4. Eliminar una Reserva
- **Método HTTP:** `DELETE`
- **Ruta:** `/api/reservas/{id}`
- **Descripción:** Elimina la reserva y, si corresponde, libera el camión asociado.
- **Códigos de Estado:**
  - `204 No Content`: Eliminado correctamente.
  - `404 Not Found`: Si el ID no existe.

## Casos Comunes de Error (Códigos Status Globales)
- `400 Bad Request` -> Un `ErrorResponse` en caso de fallo de requerimientos básicos de payload.
- `404 Not Found` -> Se puede recibir este valor debido a que la entidad con el `id` brindado no está en el sistema, con la estructura proporcionada:
  ```json
  {
    "message": "Camión no encontrado con ID 5",
    "status": 404
  }
  ```
