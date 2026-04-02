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
- **Descripción:** Obtiene únicamente los camiones que cuenten con su estado activo (`estado = true`).
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

## Casos Comunes de Error (Códigos Status Globales)
- `400 Bad Request` -> Un `ErrorResponse` en caso de fallo de requerimientos básicos de payload.
- `404 Not Found` -> Se puede recibir este valor debido a que la entidad con el `id` brindado no esta en el sistema, con la estructura proporcionada:
  ```json
  {
    "message": "Camión no encontrado con ID 5",
    "status": 404
  }
  ```
