package order.management.payload;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import order.management.model.OrderStatus;
import order.management.model.OrderType;
import order.management.validation.Commission;
import order.management.validation.StartsWith;

@JsonIgnoreProperties(value = {"phoneNumberBlank", "personalNumberBlank", "accountNumberBlank" })
@Commission
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderPayload {

    public static final Integer PAYLOAD_LIMIT = 1000;

    private String id;

    @NotNull
    @Max(100)
    @Min(1)
    protected Double amount;

    @NotNull
    protected Double commission;

    @Pattern(regexp = "[0-9]+", message = "only numbers allowed")
    @StartsWith(prefix = "5")
    @Size(min = 9, max = 9)
    private String phoneNumber;

    @Pattern(regexp = "[0-9]+", message = "only numbers allowed")
    @Size(min = 11, max = 11)
    private String personalNumber;

    @Pattern(regexp = "GE*[0-9]{2}[a-zA-Z]{2}[0-9]{16}", message = "Account number format violated GE00XX0000000000000000")
    private String accountNumber;

    private OrderType type;

    private OrderStatus status;

    private Integer payloadSize;

    public boolean isPhoneNumberBlank() {
        return StringUtils.isBlank(this.getPhoneNumber());
    }
    public boolean isPersonalNumberBlank() {
        return StringUtils.isBlank(this.getPersonalNumber());
    }
    public boolean isAccountNumberBlank() {
        return StringUtils.isBlank(this.getAccountNumber());
    }

}
