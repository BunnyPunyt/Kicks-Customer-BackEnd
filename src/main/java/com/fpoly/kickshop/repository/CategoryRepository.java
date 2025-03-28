package com.fpoly.kickshop.repository;


import com.fpoly.kickshop.model.ShoesCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<ShoesCategory, Integer> {

}
