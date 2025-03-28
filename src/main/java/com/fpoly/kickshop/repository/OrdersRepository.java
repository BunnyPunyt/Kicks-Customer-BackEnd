package com.fpoly.kickshop.repository;

import com.fpoly.kickshop.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    @Query("SELECT o FROM Orders o LEFT JOIN FETCH o.orderDetails WHERE o.customer.id = :customerId")
    List<Orders> findByCustomerId(@Param("customerId") Integer customerId);
}
