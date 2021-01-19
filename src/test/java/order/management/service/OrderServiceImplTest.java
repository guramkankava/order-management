package order.management.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import order.management.OrderManagementApplication;
import order.management.model.Order;
import order.management.model.OrderStatus;
import order.management.repository.OrderRepository;

@AutoConfigureDataMongo
@ContextConfiguration(classes = OrderManagementApplication.class )
@SpringBootTest(properties = {"scheduling.enabled=false", "commandline.runner=false"})
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void placeOrderTest() {
        Order order = Order.builder().id("1a-1").build();
        when(this.orderRepository.save(Mockito.any())).thenReturn(order);
        order = this.orderService.placeOrder(order);
        assertEquals(order.getStatus(), OrderStatus.NOT_SYNCHRONIZED);
        verify(this.orderRepository, times(1)).save(Mockito.any());
    }

}
