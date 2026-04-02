package com.fletes.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<EntityNotFoundException> {

    @Override
    public Response toResponse(EntityNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), Response.Status.NOT_FOUND.getStatusCode());
        return Response.status(Response.Status.NOT_FOUND).entity(errorResponse).build();
    }
}
