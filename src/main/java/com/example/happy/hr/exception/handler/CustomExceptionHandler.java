package com.example.happy.hr.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.Map;

/*  Обработчик исключений   */

@ControllerAdvice   //  Аннотация говорит, что этот класс будет ловить исключения, но только те, которые указаны в аннотациях над его методами
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    /*  Это исключение может быть выброшено, если ,например, запросить сущность с несуществующим id */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException e, WebRequest req) {
        log.info("Handling EntityNotFoundException");
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", System.currentTimeMillis());
        body.put("status", 404);
        body.put("message", "Not found");
        return ResponseEntity.status(404).body(body);
    }

    /*  Исключения возникают при нарушении ограничений в бд
        Например, в столбце таблицы только положительные числа) и невалидных полях в dto    */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e, WebRequest req) {
        log.info("Handling ConstraintViolationException");
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", System.currentTimeMillis());
        body.put("status", 400);
        body.put("message", "Bad request");
        return ResponseEntity.status(400).body(body);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException e, WebRequest req) {
        log.info("Handling ValidationException");
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", System.currentTimeMillis());
        body.put("status", 400);
        body.put("message", "Bad request");
        return ResponseEntity.status(400).body(body);
    }

}
