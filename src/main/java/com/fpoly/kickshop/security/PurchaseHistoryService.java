package com.fpoly.kickshop.security;

import com.fpoly.kickshop.dto.OrderDTO;
import com.fpoly.kickshop.model.Orders;
import com.fpoly.kickshop.repository.OrdersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class PurchaseHistoryService {
    @Autowired
    private OrdersRepository ordersRepository;

    public List<OrderDTO> getOrdersByCustomerId(Integer customerId) {
        List<Orders> orders = ordersRepository.findByCustomerId(customerId);
        return orders.stream()
                .map(order -> new OrderDTO(order))
                .collect(Collectors.toList());
    }
}
