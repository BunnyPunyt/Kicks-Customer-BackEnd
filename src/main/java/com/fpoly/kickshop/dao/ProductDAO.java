package com.fpoly.kickshop.dao;

import com.fpoly.kickshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product, Integer> {
    @Override
    List<Product> findAll();

}
