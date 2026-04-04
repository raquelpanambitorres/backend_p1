package com.fletes.controller;

import com.fletes.dto.ReservaRequestDTO;
import com.fletes.dto.ReservaResponseDTO;
import com.fletes.service.ReservaService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api/reservas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReservaController {

    @Inject
    ReservaService reservaService;

    @POST
    public Response create(@Valid ReservaRequestDTO dto) {
        ReservaResponseDTO response = reservaService.create(dto);
        return Response.status(Response.Status.CREATED).entity(response).build();
    }

    @GET
    public Response getAll() {
        List<ReservaResponseDTO> reservas = reservaService.getAll();
        return Response.ok(reservas).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Integer id) {
        ReservaResponseDTO response = reservaService.getById(id);
        return Response.ok(response).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        reservaService.delete(id);
        return Response.noContent().build();
    }
}