package com.fpoly.kickshop.repository;

import com.fpoly.kickshop.model.ProductDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> {
//    @Query("SELECT d.id, p.name, p.description," +
//            " p.category.name, p.brand, s.stock, p.price, s.stock " +
//            "FROM ProductDetail d " +
//            "inner join Product p on p.id = d.product.id " +
//            "inner join ProductSize s on s.productDetail.id = d.id " +
//            "where p.id = :productId")
//    List<ProductDetailDTO> findByProductId(@Param("productId") Integer id);

    ProductDetail findProductDetailsByIsDefaultTrueAndProduct_Id(Integer id);
    ProductDetail findProductDetailsById(Integer id);
    List<ProductDetail> findProductDetailsByProduct_Id(Integer id);
    //Optional<ProductDetail> findById(Integer id)

}
