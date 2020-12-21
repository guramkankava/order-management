package order.management.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import order.management.model.Order;
import order.management.model.OrderStatus;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    Optional<Set<Order>> findByStatus(OrderStatus status);
    Optional<Set<Order>> findByStatus(OrderStatus status, Sort sort);

}
