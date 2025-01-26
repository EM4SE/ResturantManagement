package com.hndse.resturant.repos;

import com.hndse.resturant.dtos.request.OrderRequestDto;
import com.hndse.resturant.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.orderStatus = :status WHERE o.id = :orderId")
    void updateOrderStatus(@Param("orderId") Integer orderId, @Param("status") String status );
}
