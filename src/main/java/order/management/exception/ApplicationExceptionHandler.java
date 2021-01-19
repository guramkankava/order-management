package order.management.exception;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import order.management.payload.ExceptionPayload;

@Slf4j
@AllArgsConstructor
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    private ResourceBundleMessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getBindingResult().hasGlobalErrors() ? ex.getBindingResult().getGlobalError().getDefaultMessage() : ex.getBindingResult().getFieldError().getDefaultMessage();
        ExceptionPayload exceptionPayload = ExceptionPayload.builder()
            .status(status.toString())
            .message(message).build();

        return ResponseEntity.badRequest().body(exceptionPayload);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> handleApiException(ApiException apiException) {
        log.error(apiException.getMessage());
        return ResponseEntity.
                status(apiException.getStatus()).
                body(ApiException.
                        builder().
                        status(apiException.getStatus()).
                        message(messageSource.getMessage(apiException.getMessageCode(), apiException.getParams(), LocaleContextHolder.getLocale())).
                        build());
    }

}
