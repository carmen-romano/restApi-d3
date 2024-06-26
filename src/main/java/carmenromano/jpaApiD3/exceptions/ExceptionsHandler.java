package carmenromano.jpaApiD3.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessagePayload handleBadRequest(BadRequestException ex) {
        return new ErrorMessagePayload(ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessagePayload handleNotFound(NotFoundException ex) {
        return new ErrorMessagePayload(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessagePayload handleGenericErrors(Exception ex) {
        ex.printStackTrace();
        return new ErrorMessagePayload("Errore server!");
    }
}
