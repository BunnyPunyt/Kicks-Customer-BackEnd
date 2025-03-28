package com.fpoly.kickshop.controller;

import com.fpoly.kickshop.dto.OrderDTO;
import com.fpoly.kickshop.security.PurchaseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PurchaseHistoryController {
    @Autowired
    private PurchaseHistoryService purchaseHistoryService;

    @GetMapping("/purchase-history/{customerId}")
    public ResponseEntity<List<OrderDTO>> getPurchaseHistory(@PathVariable Integer customerId) {
        List<OrderDTO> history = purchaseHistoryService.getOrdersByCustomerId(customerId);
        return ResponseEntity.ok(history);
    }
}
