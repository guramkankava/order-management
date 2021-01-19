package order.management.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import order.management.configurations.MockMvcTestContext;
import order.management.exception.ApplicationExceptionHandler;
import order.management.mapper.OrderPayloadMapper;
import order.management.model.Order;
import order.management.model.OrderStatus;
import order.management.model.OrderType;
import order.management.payload.ExceptionPayload;
import order.management.payload.OrderPayload;
import order.management.service.OrderService;

@AutoConfigureDataMongo
@ContextConfiguration(classes = {MockMvcTestContext.class})
@WebMvcTest(controllers = {OrderController.class})
public class OrderControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OrderService orderService;

    @Mock
    private OrderPayloadMapper orderMapper;

    @InjectMocks
    private OrderController orderController;

    @Autowired
    private ApplicationExceptionHandler exceptionHandler;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void init() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.orderController).setControllerAdvice(this.exceptionHandler).build();
    }

    @Test
    public void withValidOrderPayloadTest() throws Exception {
        Order order = this.toOrder(10D, 0.5, "599559955", null, null);

        when(this.orderMapper.toModel(Mockito.any())).thenReturn(order);

        order.setId(UUID.randomUUID().toString());
        order.setType(OrderType.MOBILE_TOPUP);
        order.setStatus(OrderStatus.NOT_SYNCHRONIZED);

        when(this.orderService.placeOrder(Mockito.any())).thenReturn(order);

        OrderPayload payload = this.toPayload(order);

        payload = this.toPayload(order);

        when(this.orderMapper.toPayload(Mockito.any())).thenReturn(payload);

        String body = this.toJson(payload);
        
        String responseBody = this.mockMvc.perform(
            post("/orders/mobile").
            contentType(MediaType.APPLICATION_JSON_VALUE).
            content(body)
        ).
        andExpect(status().is2xxSuccessful()).
        andReturn().
        getResponse().
        getContentAsString();
        payload = (OrderPayload) this.toObject(responseBody, OrderPayload.class);
        assertNotNull(payload.getId(), "Id should not be null");
        verify(this.orderMapper, times(1)).toModel(Mockito.any());
        verify(this.orderService, times(1)).placeOrder(Mockito.any());
        verify(this.orderMapper, times(1)).toPayload(Mockito.any());
    }

    @Test
    public void withInvalidOrderPayloadTest() throws Exception {
        Order order = this.toOrder(10D, 0.1, "599559955", null, null);
        OrderPayload payload = this.toPayload(order);
        String body = this.toJson(payload);

        String response = this.mockMvc.perform(
            post("/orders/mobile").
            contentType(MediaType.APPLICATION_JSON_VALUE).
            content(body)
        ).
        andExpect(status().isBadRequest()).
        andReturn().
        getResponse().
        getContentAsString();
        ExceptionPayload exceptionPayload = (ExceptionPayload) this.toObject(response, ExceptionPayload.class);
        assertEquals(exceptionPayload.getMessage(), "Min commision is 0.50 GEL or 1 percent of amount");
    }

    private Order toOrder(Double amount, Double commision, String phonenumber, String personalnumber, String accountnumber) {
        Order order = Order.builder().amount(amount).
        commission(commision).
        phoneNumber(phonenumber).
        personalNumber(personalnumber).
        accountNumber(accountnumber).
        build();
        return order;
    }

    private OrderPayload toPayload(Order order) {
        OrderPayload payload = OrderPayload.builder().
            id(order.getId()).
            type(order.getType()).
            status(order.getStatus()).
            amount(order.getAmount()).
            commission(order.getCommission()).
            phoneNumber(order.getPhoneNumber()).
            personalNumber(order.getPersonalNumber()).
            accountNumber(order.getAccountNumber()).
        build();
        return payload;
    }

    private String toJson(OrderPayload payload) throws JsonProcessingException {
        return this.objectMapper.writeValueAsString(payload);
    }

    private Object toObject(String json, Class<?> clazz) throws JsonProcessingException {
        return this.objectMapper.readValue(json, clazz);
    }
}