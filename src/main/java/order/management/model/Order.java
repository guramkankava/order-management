package order.management.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "orders")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {

    @Id
    private String id;

    private Double amount;
    private Double commission;
    private String personalNumber;
    private String phoneNumber;
    private String accountNumber;

    private OrderType type;
    private OrderStatus status;
    private Integer payloadSize;

}
