package com.fpoly.kickshop.service;

import com.fpoly.kickshop.dto.*;
import com.fpoly.kickshop.model.*;
import com.fpoly.kickshop.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private GalleryRepository galleryRepository;
    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Autowired
    private ProductSizeRepository productSizeRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Autowired
    public ProductService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<AllProductsDTO> GetallProductDTO() {
        String sql = "SELECT p.ID AS ProductID, p.Name, p.Price, p.Brand, p.Description, " +
                "pd.ID AS ProductDetailID, pd.Color, pd.Is_default AS IsDefaultDetail, " +
                "ps.ID AS ProductSizeID, ps.Size, ps.Stock, " +
                "g.ID AS GalleryID, g.Image AS GalleryImage, g.Is_default AS IsDefaultImage, " +
                "gc.ID AS GenderCategoryID, gc.name AS GenderCategoryName, " +
                "sc.ID AS ShoeCategoryID, sc.name AS ShoeCategoryName " +
                "FROM Product p " +
                "JOIN Product_detail pd ON p.ID = pd.Product_ID " +
                "JOIN Product_size ps ON pd.ID = ps.Product_detail_ID " +
                "JOIN Gallery g ON pd.ID = g.Product_detail_ID " +
                "JOIN Gender_Category gc ON p.Gender_Category_ID = gc.ID " +  // Sửa FK
                "JOIN Shoes_Category sc ON p.Shoes_Category_ID = sc.ID";      // Sửa FK


        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            AllProductsDTO dto = new AllProductsDTO();
            dto.setProductId(rs.getInt("ProductID"));
            dto.setName(rs.getString("Name"));
            dto.setPrice(rs.getDouble("Price"));
            dto.setBrand(rs.getString("Brand"));
            dto.setDescription(rs.getString("Description"));
            dto.setProductDetailId(rs.getInt("ProductDetailID"));
            dto.setColor(rs.getString("Color"));
            dto.setIsDefaultDetail(rs.getBoolean("IsDefaultDetail"));
            dto.setProductSizeId(rs.getInt("ProductSizeID"));
            dto.setSize(rs.getString("Size"));
            dto.setStock(rs.getInt("Stock"));
            dto.setGalleryId(rs.getInt("GalleryID"));
            dto.setGalleryImage(rs.getString("GalleryImage"));
            dto.setIsDefaultImage(rs.getBoolean("IsDefaultImage"));

            // Thêm Gender Category
            dto.setGenderCategoryId(rs.getInt("GenderCategoryID"));
            dto.setGenderCategoryName(rs.getString("GenderCategoryName"));

            // Thêm Shoe Category
            dto.setShoeCategoryId(rs.getInt("ShoeCategoryID"));
            dto.setShoeCategoryName(rs.getString("ShoeCategoryName"));

            return dto;
        });

    }
}
