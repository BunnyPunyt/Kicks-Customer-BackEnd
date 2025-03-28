package com.fpoly.kickshop.repository;

import com.fpoly.kickshop.model.ProductSize;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, Integer> {
    List<ProductSize> findAllByProductDetail_Id(int productDetailId);
    //ProductSize findBy
}
