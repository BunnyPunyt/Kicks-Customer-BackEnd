package com.fpoly.kickshop.repository;

import com.fpoly.kickshop.model.ShoesCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoeCategoryRepository extends JpaRepository<ShoesCategory, Integer> {
    List<ShoesCategory> findAll();
}
