package com.fpoly.kickshop.dto;

import com.fpoly.kickshop.model.Orders;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data

public class OrderDTO {
    private Integer id;
    private Integer customerId;
    private Integer employeeId;
    private Integer paymentId;
    private Integer couponId;
    private String shippingAddress;
    private Float totalAmount;
    private Date orderDate;
    private String orderStatus;
    private List<OrderDetailDTO> orderDetails;

    public OrderDTO(Orders order) {
        this.id = order.getId();
        this.customerId = order.getCustomer() != null ? order.getCustomer().getId() : null;
        this.employeeId = order.getEmployee() != null ? order.getEmployee().getId() : null;
        this.paymentId = order.getPayment() != null ? order.getPayment().getId() : null;
        this.couponId = order.getCoupon() != null ? order.getCoupon().getId() : null;
        this.shippingAddress = order.getShippingAddress();
        this.totalAmount = order.getTotalAmount();
        this.orderDate = order.getOrderDate();
        this.orderStatus = order.getOrderStatus();
        this.orderDetails = order.getOrderDetails() != null
                ? order.getOrderDetails().stream().map(OrderDetailDTO::new).collect(Collectors.toList())
                : null;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public OrderDTO(Integer customerId, Integer employeeId, Integer paymentId, Integer couponId, String shippingAddress, Float totalAmount, Date orderDate, String orderStatus, List<OrderDetailDTO> orderDetails) {
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.paymentId = paymentId;
        this.couponId = couponId;
        this.shippingAddress = shippingAddress;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.orderDetails = orderDetails;
    }

    public OrderDTO() {
    }
}
