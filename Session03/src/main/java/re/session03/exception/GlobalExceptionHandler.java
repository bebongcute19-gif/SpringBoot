package re.session03.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.session03.response.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleNotFound(ResourceNotFoundException e) {
        return ResponseEntity.status(404)
                .body(new ApiResponse<>(false, e.getMessage(), null));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<?>> handleBadRequest(BadRequestException e) {
        return ResponseEntity.status(400)
                .body(new ApiResponse<>(false, e.getMessage(), null));
    }
}
