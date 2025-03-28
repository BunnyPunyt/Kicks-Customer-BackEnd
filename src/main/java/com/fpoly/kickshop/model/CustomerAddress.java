package com.fpoly.kickshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Customer_address")
@Data
public class CustomerAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    private Customer customer;

    @Column(length = 100)
    private String city;

    @Column(length = 100)
    private String district;

    @Column(length = 100)
    private String ward;

    @Column(length = 255)
    private String street;

    @Column(name = "is_default", nullable = false)
    private Boolean isDefault = false;

    public CustomerAddress() {
    }

    public CustomerAddress(Integer id, Customer customer, String city, String ward, String district, String street, Boolean isDefault) {
        this.id = id;
        this.customer = customer;
        this.city = city;
        this.ward = ward;
        this.district = district;
        this.street = street;
        this.isDefault = isDefault;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean aDefault) {
        isDefault = aDefault;
    }
}
