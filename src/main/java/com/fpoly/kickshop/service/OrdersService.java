package com.fpoly.kickshop.service;

import com.fpoly.kickshop.dto.OrderDTO;
import com.fpoly.kickshop.model.*;
import com.fpoly.kickshop.model.Employee;
import com.fpoly.kickshop.model.Orders;
import com.fpoly.kickshop.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdersService {
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
    private OrdersDetailRepository ordersDetailRepository;

    @Autowired
    private ProductRepository productRepository;


//    public OrdersService(OrdersRepository orderRepository) {
//        this.ordersRepository = orderRepository;
//    }

    public OrdersService(OrdersRepository ordersRepository, CustomerRepository customerRepository,
                        EmployeeRepository employeeRepository, PaymentRepository paymentRepository,
                         CouponRepository couponRepository, ProductRepository productRepository) {
        this.ordersRepository = ordersRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.paymentRepository = paymentRepository;
        this.couponRepository = couponRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Orders createOrder(OrderDTO request) {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Payment payment = paymentRepository.findById(request.getPaymentId())
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        Coupon coupon = null;
        if (request.getCouponId() != null) {
            coupon = couponRepository.findById(request.getCouponId())
                    .orElseThrow(() -> new RuntimeException("Coupon not found"));
        }

        Orders order = new Orders();
        order.setCustomer(customer);
        order.setEmployee(employee);
        order.setPayment(payment);
        order.setCoupon(coupon);
        order.setOrderDate(request.getOrderDate());
        order.setOrderStatus(request.getOrderStatus());
        order.setShippingAddress(request.getShippingAddress());
        order.setTotalAmount(request.getTotalAmount());

        if (request.getOrderDetails() != null) {
            List<OrderDetail> orderDetailList = request.getOrderDetails().stream().map(odRequest -> {
                OrderDetail orderDetail = new OrderDetail();
                Product product = productRepository.findById(odRequest.getProductId())
                        .orElseThrow(() -> new RuntimeException("Product not found"));

                orderDetail.setOrder(order);
                orderDetail.setProduct(product);
                orderDetail.setImage(odRequest.getImage());
                orderDetail.setPrice(odRequest.getPrice());
                orderDetail.setSize(odRequest.getSize());
                orderDetail.setColor(odRequest.getColor());
                orderDetail.setQuantity(odRequest.getQuantity());

                return orderDetail;
            }).collect(Collectors.toList());
            order.setOrderDetails(orderDetailList);
        }

        return ordersRepository.save(order);
    }



    public Orders saveOrder(Orders order) {
        return ordersRepository.save(order);
    }



}
