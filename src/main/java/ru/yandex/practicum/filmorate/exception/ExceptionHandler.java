package ru.yandex.practicum.filmorate.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @Data
    @AllArgsConstructor
    public static class ErrorResponse {
        private final String error;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(final Exception x) {
        return new ErrorResponse(String.format("общее исключение", x.getMessage()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerObjectNotFound(final ObjectNotFoundException x) {
        return new ErrorResponse(String.format("обьект не найден", x.getMessage()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerValidationError(final ValidationException x) {
        return new ErrorResponse(String.format("ошибка валидации", x.getMessage()));
    }
}