package order.management.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import order.management.exception.ApiException;
import order.management.mapper.OrderPayloadMapper;
import order.management.model.Order;
import order.management.model.OrderType;
import order.management.payload.OrderPayload;
import order.management.service.OrderService;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/orders")
@RestController
public class OrderController {

    private final OrderService orderService;

    private final OrderPayloadMapper orderMapper;

    @GetMapping
    public OrderPayload get() {
        log.info("Get reached");
        OrderPayload op = new OrderPayload();
        op.setPersonalNumber("599515433");
        return op;
    }

    @PostMapping(path = "/mobile")
    public OrderPayload placePhoneBalanceTopuOrder(@Valid  @RequestBody OrderPayload orderPayload) {
        if(orderPayload.isPhoneNumberBlank()) {
            throw new ApiException("Phone number is a mandatory field", HttpStatus.BAD_REQUEST, "mandatory.field", new String [] {"phoneNumber"});
        }
        orderPayload.setType(OrderType.MOBILE_TOPUP);
        Order order = orderMapper.toModel(orderPayload);
        order = orderService.placeOrder(order);
        return orderMapper.toPayload(order);
    }

    @PostMapping(path = "/charity")
    public OrderPayload placeCharityOrder(@Valid  @RequestBody OrderPayload orderPayload) {
        if(orderPayload.isPhoneNumberBlank()) {
            throw new ApiException("Phone number is a mandatory field", HttpStatus.BAD_REQUEST, "mandatory.field", new String [] {"phoneNumber"});
        }
        if(orderPayload.isPersonalNumberBlank()) {
            throw new ApiException("Personal number is a mandatory field", HttpStatus.BAD_REQUEST, "mandatory.field", new String [] {"personalNumber"});
        }
        orderPayload.setType(OrderType.CHARITY);
        Order order = orderMapper.toModel(orderPayload);
        order =  orderService.placeOrder(order);
        return orderMapper.toPayload(order);
    }

    @PostMapping(path = "/utility")
    public OrderPayload placeUtilityOrder(@Valid  @RequestBody OrderPayload orderPayload) {
        if(orderPayload.isPhoneNumberBlank()) {
            throw new ApiException("Phone number is a mandatory field", HttpStatus.BAD_REQUEST, "mandatory.field", new String [] {"phoneNumber"});
        }
        if(orderPayload.isPersonalNumberBlank()) {
            throw new ApiException("Personal number is a mandatory field", HttpStatus.BAD_REQUEST, "mandatory.field", new String [] {"personalNumber"});
        }
        orderPayload.setType(OrderType.UTILITY_TOPUP);
        Order order = orderMapper.toModel(orderPayload);
        order =  orderService.placeOrder(order);
        return orderMapper.toPayload(order);
    }

    @PostMapping(path = "/bank")
    public OrderPayload placeBankOrder(@Valid  @RequestBody OrderPayload orderPayload) {
        if(orderPayload.isPhoneNumberBlank()) {
            throw new ApiException("Phone number is a mandatory field", HttpStatus.BAD_REQUEST, "mandatory.field", new String [] {"phoneNumber"});
        }
        if(orderPayload.isPersonalNumberBlank()) {
            throw new ApiException("Personal number is a mandatory field", HttpStatus.BAD_REQUEST, "mandatory.field", new String [] {"personalNumber"});
        }
        if(orderPayload.isAccountNumberBlank()) {
            throw new ApiException("Account number is a mandatory field", HttpStatus.BAD_REQUEST, "mandatory.field", new String [] {"accountNumber"});
        }
        orderPayload.setType(OrderType.BANK);
        Order order = orderMapper.toModel(orderPayload);
        order =  orderService.placeOrder(order);
        return orderMapper.toPayload(order);
    }
}
