package com.fpoly.kickshop.repository;

import com.fpoly.kickshop.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<Customer> findById(Integer id);
//    Optional<Customer> findByVerificationToken(String token);
}
