package order.management.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import order.management.model.Order;
import order.management.model.OrderStatus;
import order.management.payload.OrderPayload;
import order.management.repository.OrderRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderSynchronizationService {

     private static final long HALF_HOURE = ((1000 * 60) * 30);
//    private static final long HALF_HOURE = ((1000 * 60) * 2);

    private final OrderRepository orderRepository;

    @Scheduled(fixedDelay = HALF_HOURE, initialDelay = 1000 * 10)
    public void initOrderSynchronization() {
        synchronizeOrders();
    }

    private void synchronizeOrders() {
        Set<Order> ordersAccumulator = getUnsynchronizedOrders();
        int status = send(ordersAccumulator);
        if (status == 200) {
            ordersAccumulator.forEach(synchOrder -> {
                synchOrder.setStatus(OrderStatus.SYNCHRONIZED);
            });
            orderRepository.saveAll(ordersAccumulator);
            log.info("Successfuly synchronized orders");
        } else {
            log.error("Data synchronization failed");
        }
    }

    private Set<Order> getUnsynchronizedOrders() {
        Optional<Set<Order>> ordersOptional = orderRepository.findByStatus(OrderStatus.NOT_SYNCHRONIZED, Sort.by(Direction.DESC, "commission"));

        if (ordersOptional.isEmpty()) {
            log.info("Unsynchronized orders not found");
            return Set.of();
        }
        Set<Order> ordersCommissionDesc = ordersOptional.get();

        Set<Order> ordersAccumulator = new HashSet<>();
        int payloadSizeAccumulator = 0;

        for (Order order : ordersCommissionDesc) {
            if ((payloadSizeAccumulator + order.getPayloadSize()) < OrderPayload.PAYLOAD_LIMIT) {
                ordersAccumulator.add(order);
                payloadSizeAccumulator += order.getPayloadSize();
                continue;
            }
            break;
        }
        return ordersAccumulator;
    }

    private int send(Set<Order> ordersAcumulator) {
        System.out.println("Sending orders");
        ordersAcumulator.forEach(System.out::println);
        System.out.println("Send success");
        return 200;
    }
}
