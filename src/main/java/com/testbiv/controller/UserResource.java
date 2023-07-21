package com.testbiv.controller;


import com.testbiv.models.User;
import com.testbiv.models.UserRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

@Path("/users")

public class UserResource {

    @Inject
    UserRepository userRepository;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers()
    {
        return Response.ok(userRepository.listAll()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") Long id)
    {
        User user = userRepository.findById(id);
        if (user != null) {
            return Response.ok(user).build();
        } else {
            throw new NotFoundException();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addUser(@Valid User newUser)
    {
        userRepository.persist(newUser);
        return Response.ok(newUser).status(Response.Status.CREATED).build();
    }


    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateUser(@PathParam("id") Long id, @Valid User updatedUser)
    {
        User user = userRepository.findById(id);
        if (user != null) {
            userRepository.getEntityManager().merge(updatedUser);
            return Response.ok(updatedUser).build();
        } else {
            throw new NotFoundException();
        }
    }


    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response deleteUser(@PathParam("id") Long id)
    {
        User user = userRepository.findById(id);
        if (user != null) {
            userRepository.delete(user);
            return Response.noContent().build();
        } else {
            throw new NotFoundException();
        }
    }

}
