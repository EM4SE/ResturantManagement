package com.hndse.resturant.repos;

import com.hndse.resturant.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    boolean existsById(int id);
    List<OrderItem> findByOrderId(Integer orderId);
}
