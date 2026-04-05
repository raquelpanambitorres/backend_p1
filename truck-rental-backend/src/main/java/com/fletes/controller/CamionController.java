package com.fletes.controller;

import java.util.List;

import com.fletes.dto.CamionRequestDTO;
import com.fletes.dto.CamionResponseDTO;
import com.fletes.service.CamionService;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/camiones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CamionController {

    @Inject
    CamionService camionService;

    @POST
    public Response create(@Valid CamionRequestDTO dto) {
        CamionResponseDTO response = camionService.create(dto);
        return Response.status(Response.Status.CREATED).entity(response).build();
    }

    @GET
    public Response getAll(
        @QueryParam("fechaDesde") String fechaDesde,
        @QueryParam("fechaHasta") String fechaHasta,
        @QueryParam("capacidadCargaKg") Float capacidadCargaKg
    ) {
        List<CamionResponseDTO> camiones = camionService.getAll(fechaDesde, fechaHasta, capacidadCargaKg);
        return Response.ok(camiones).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Integer id) {
        CamionResponseDTO response = camionService.getById(id);
        return Response.ok(response).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, @Valid CamionRequestDTO dto) {
        CamionResponseDTO response = camionService.update(id, dto);
        return Response.ok(response).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        camionService.delete(id);
        return Response.noContent().build();
    }
}
