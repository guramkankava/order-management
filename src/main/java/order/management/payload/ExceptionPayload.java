package order.management.payload;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class ExceptionPayload {

    private String status;
    private String message;
}
