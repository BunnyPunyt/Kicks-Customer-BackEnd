package com.fpoly.kickshop.repository;

import com.fpoly.kickshop.model.Gallery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Integer> {
    List<Gallery> findAll();

    @Query("SELECT g.image FROM Gallery g " +
            "INNER JOIN ProductDetail pd ON g.productDetail.id = pd.id " +
            "INNER JOIN Product p ON pd.product.id = p.id " +
            "WHERE g.isDefault = true " +
            "and pd.isDefault = true " +
            "and p.id = :productId")
    String findImageByProductId(@Param("productId") Integer id);
    Gallery findGalleriesByIsDefaultTrueAndProductDetail_Id(int productDetailId);

    List<Gallery> findAllByProductDetail_Id(int productDetailId);
}
