package order.management;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import order.management.model.Order;
import order.management.model.OrderType;
import order.management.repository.OrderRepository;
import order.management.service.OrderService;

@Slf4j
@SpringBootApplication
@AllArgsConstructor
public class OrderManagementApplication implements CommandLineRunner {

    private OrderRepository orderRepository;
    private OrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run(OrderManagementApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        orderRepository.deleteAll();
        log.info("Orders deleted");
        Order mobileOrder1 = new Order();
        mobileOrder1.setAmount(1D);
        mobileOrder1.setCommission(0.5);
        mobileOrder1.setPhoneNumber("599515433");
        mobileOrder1.setType(OrderType.MOBILE_TOPUP);
        orderService.placeOrder(mobileOrder1);
        Order mobileOrder2 = new Order();
        mobileOrder2.setAmount(10D);
        mobileOrder2.setCommission(0.5);
        mobileOrder2.setPhoneNumber("599515433");
        mobileOrder2.setType(OrderType.MOBILE_TOPUP);
        orderService.placeOrder(mobileOrder2);

        Order utilityOrder1 = new Order();
        utilityOrder1.setAmount(60D);
        utilityOrder1.setCommission(0.6);
        utilityOrder1.setPhoneNumber("599515433");
        utilityOrder1.setPersonalNumber("01008023096");
        utilityOrder1.setType(OrderType.UTILITY_TOPUP);
        orderService.placeOrder(utilityOrder1);
        Order utilityOrder2 = new Order();
        utilityOrder2.setAmount(70D);
        utilityOrder2.setCommission(0.7);
        utilityOrder2.setPhoneNumber("599515433");
        utilityOrder2.setPersonalNumber("01008023096");
        utilityOrder2.setType(OrderType.UTILITY_TOPUP);
        orderService.placeOrder(utilityOrder2);

        Order charityOrder1 = new Order();
        charityOrder1.setAmount(60D);
        charityOrder1.setCommission(0.6);
        charityOrder1.setPhoneNumber("599515433");
        charityOrder1.setPersonalNumber("01008023096");
        charityOrder1.setType(OrderType.CHARITY);
        orderService.placeOrder(charityOrder1);
        Order charityOrder2 = new Order();
        charityOrder2.setAmount(100D);
        charityOrder2.setCommission(1D);
        charityOrder2.setPhoneNumber("599515433");
        charityOrder2.setPersonalNumber("01008023096");
        charityOrder2.setType(OrderType.CHARITY);
        orderService.placeOrder(charityOrder2);

        Order bankOrder1 = new Order();
        bankOrder1.setAmount(100D);
        bankOrder1.setCommission(1D);
        bankOrder1.setPhoneNumber("599515433");
        bankOrder1.setPersonalNumber("01008023096");
        bankOrder1.setAccountNumber("GE00XX0000000000000000");
        bankOrder1.setType(OrderType.BANK);
        orderService.placeOrder(bankOrder1);
        Order bankOrder2 = new Order();
        bankOrder2.setAmount(75D);
        bankOrder2.setCommission(0.75);
        bankOrder2.setPhoneNumber("599515433");
        bankOrder2.setPersonalNumber("01008023096");
        bankOrder2.setAccountNumber("GE00XX0000000000000000");
        bankOrder2.setType(OrderType.BANK);
        orderService.placeOrder(bankOrder2);
        Order bankOrder3 = new Order();
        bankOrder3.setAmount(88D);
        bankOrder3.setCommission(0.88D);
        bankOrder3.setPhoneNumber("599515433");
        bankOrder3.setPersonalNumber("01008023096");
        bankOrder3.setAccountNumber("GE00XX0000000000000000");
        bankOrder3.setType(OrderType.BANK);
        orderService.placeOrder(bankOrder3);
        log.info("Orders created");
    }

}
