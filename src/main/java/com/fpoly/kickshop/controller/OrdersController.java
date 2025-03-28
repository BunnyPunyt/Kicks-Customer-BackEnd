package com.fpoly.kickshop.controller;

import com.fpoly.kickshop.dto.OrderDTO;
import com.fpoly.kickshop.model.*;
import com.fpoly.kickshop.repository.*;
import com.fpoly.kickshop.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private ProductRepository productRepository;

    public OrdersController(OrdersService orderService) {
        this.ordersService = orderService;
    }

//    @PostMapping()
//    public ResponseEntity<?> createOrder(@RequestBody Orders request) {
//
//        System.out.println("Request: "+ request.getShippingAddress());
//        Customer customer = customerRepository.findById(request.getCustomer().getId())
//                .orElseThrow(() -> new RuntimeException("Customer not found"));
//
//        Employee employee = employeeRepository.findById(request.getEmployee().getId())
//                .orElseThrow(() -> new RuntimeException("Employee not found"));
//
//        Payment payment = paymentRepository.findById(request.getPayment().getId())
//                .orElseThrow(() -> new RuntimeException("Payment not found"));
//
//        Coupon coupon = couponRepository.findById(request.getCoupon().getId())
//                .orElseThrow(() -> new RuntimeException("Coupon not found"));
//
//
//        Orders order = new Orders();
//        order.setCustomer(customer);
//        order.setEmployee(employee);
//        order.setPayment(payment);
//        order.setCoupon(coupon);
//        order.setOrderDate(request.getOrderDate());
//        order.setOrderStatus(request.getOrderStatus());
//        order.setShippingAddress(request.getShippingAddress());
//        order.setTotalAmount(request.getTotalAmount());
//
//        // Xử lý orderDetails
//        if (request.getOrderDetails() != null) {
//            List<OrderDetail> orderDetailList = request.getOrderDetails().stream().map(odRequest -> {
//                OrderDetail orderDetail = new OrderDetail();
//                Product product = productRepository.findById(odRequest.getProduct().getId())
//                        .orElseThrow(() -> new RuntimeException("Product not found"));
//
//                orderDetail.setOrder(order);
//                orderDetail.setProduct(product);
//                orderDetail.setPrice(odRequest.getPrice());
//                orderDetail.setSize(odRequest.getSize());
//                orderDetail.setColor(odRequest.getColor());
//                orderDetail.setQuantity(odRequest.getQuantity());
//
//                return orderDetail;
//            }).toList();
//            order.setOrderDetails(orderDetailList);
//        }
//
//        ordersRepository.save(order);
//        return ResponseEntity.ok(order);
//    }


    @PostMapping()
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO orderDTO) {
        Orders savedOrder = ordersService.createOrder(orderDTO);
        return ResponseEntity.ok(savedOrder);
    }
}
