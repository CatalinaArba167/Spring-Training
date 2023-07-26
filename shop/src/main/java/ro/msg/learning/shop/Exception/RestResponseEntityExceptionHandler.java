package ro.msg.learning.shop.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(
            RuntimeException ex, WebRequest wr) {
        return new ResponseEntity<Object>(
                "Not found message here: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({AlreadyExistsException.class})
    public ResponseEntity<Object> handleAlreadyExists(
            RuntimeException ex, WebRequest wr) {
        return new ResponseEntity<Object>(
                "Already exists message here: " + ex.getMessage(), HttpStatus.CONFLICT);
    }
}