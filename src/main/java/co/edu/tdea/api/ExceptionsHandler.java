package co.edu.tdea.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler({RuntimeException.class, Exception.class})
    public ResponseEntity<Map<String, String>> handlerException(RuntimeException e){
        Map<String, String> response = new HashMap<>();
        response.put("error", e.getMessage());
        return ResponseEntity.internalServerError()
                .body(response);
    }
}
