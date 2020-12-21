package order.management.exception;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonIgnoreProperties(value = {"cause", "stackTrace", "messageCode", "params", "suppressed", "localizedMessage"})
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class ApiException extends RuntimeException {

    private String message;

    private HttpStatus status;

    private String messageCode;

    private Object[] params;
}
