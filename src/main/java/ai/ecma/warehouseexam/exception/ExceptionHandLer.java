package ai.ecma.warehouseexam.exception;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandLer {

    @ExceptionHandler(value = {RestException.class})
    public HttpEntity<?> handling(RestException ex) {

        ApiResult<ErrorData> errorDataApiResult = ApiResult.errorResponse(ex.getMessage());
        return ResponseEntity
                .status(ex.getStatus())
                .body(errorDataApiResult);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public HttpEntity<?> handling(MethodArgumentNotValidException ex) {
        ex.printStackTrace();
        List<ErrorData> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String[] codes = error.getCodes();
            assert codes != null;
            String code = codes[codes.length - 1];
            String errorMessage = error.getDefaultMessage();
            FieldError fieldError = (FieldError) error;
            errors.add(new ErrorData(errorMessage, HttpStatus.BAD_REQUEST.value(), fieldError.getField()));
        });
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

}
