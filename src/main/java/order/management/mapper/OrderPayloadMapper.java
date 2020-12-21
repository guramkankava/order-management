package order.management.mapper;

import org.mapstruct.Mapper;

import order.management.model.Order;
import order.management.payload.OrderPayload;

@Mapper(componentModel = "spring")
public interface OrderPayloadMapper {

    Order toModel(OrderPayload orderPayload);
    OrderPayload toPayload(Order order);

}
