package com.fpoly.kickshop.repository;

import com.fpoly.kickshop.model.GenderCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenderCategoryRepository extends JpaRepository<GenderCategory, Integer> {
    List<GenderCategory> findAll();
}
