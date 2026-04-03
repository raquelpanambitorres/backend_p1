package com.fletes.controller;

import com.fletes.dto.ClienteRequestDTO;
import com.fletes.dto.ClienteResponseDTO;
import com.fletes.service.ClienteService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteController {

    @Inject
    ClienteService clienteService;

    @POST
    public Response create(@Valid ClienteRequestDTO dto) {
        ClienteResponseDTO response = clienteService.create(dto);
        return Response.status(Response.Status.CREATED).entity(response).build();
    }

    @GET
    public Response getAll() {
        List<ClienteResponseDTO> clientes = clienteService.getAll();
        return Response.ok(clientes).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Integer id) {
        ClienteResponseDTO response = clienteService.getById(id);
        return Response.ok(response).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, @Valid ClienteRequestDTO dto) {
        ClienteResponseDTO response = clienteService.update(id, dto);
        return Response.ok(response).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        clienteService.delete(id);
        return Response.noContent().build();
    }
}