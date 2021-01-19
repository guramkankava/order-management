package order.management.service;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import order.management.model.Order;
import order.management.model.OrderStatus;
import order.management.repository.OrderRepository;

@Service
@Slf4j
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Override
    public Order placeOrder(Order order) {
        order.setStatus(OrderStatus.NOT_SYNCHRONIZED);
        order = orderRepository.save(order);
        log.info("New order {} saved", order.getId());
        return order;
    }

}
