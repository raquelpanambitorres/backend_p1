package com.fletes.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class IllegalArgumentExceptionHandler implements ExceptionMapper<IllegalArgumentException> {

    @Override
    public Response toResponse(IllegalArgumentException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), Response.Status.BAD_REQUEST.getStatusCode());
        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }
}