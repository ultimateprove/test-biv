package com.testbiv.exeption;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;


@Provider
public class ExceptionHandler implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        if (exception instanceof NotFoundException) {
            // Обработка ошибок NOT_FOUND
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse(Response.Status.NOT_FOUND.getStatusCode(),
                            "Не найдено"))
                    .build();
        } else {
            // Обработка других исключений
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Произошла ошибка: " + exception.getMessage())
                    .build();
        }
    }
}
