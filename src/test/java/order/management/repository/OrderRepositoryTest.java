package order.management.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;

import order.management.model.Order;

@ContextConfiguration
@DataMongoTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void test() {
        assertNotNull(this.orderRepository);
        Order order = Order.builder().
                phoneNumber("599515433").
                amount(1D).
                commission(0.5).
                build();
        order = this.orderRepository.save(order);
        assertNotNull(order.getId());
    }

}

