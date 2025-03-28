package com.fpoly.kickshop.repository;

import com.fpoly.kickshop.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersDetailRepository extends JpaRepository<OrderDetail, Integer> {
}
