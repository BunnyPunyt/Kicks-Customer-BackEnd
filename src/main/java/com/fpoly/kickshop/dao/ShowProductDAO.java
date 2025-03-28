package com.fpoly.kickshop.kickshop.dao;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowProductDAO extends JpaRepository<com.fpoly.kickshop.model.Product, Integer>{


    @Query("SELECT DISTINCT p.brand FROM Product p WHERE p.brand IS NOT NULL")
    List<String> findAllBrands();
}
