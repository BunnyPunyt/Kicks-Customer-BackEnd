package com.fpoly.kickshop.service;

import com.fpoly.kickshop.dto.ProductSizeDTO;
import com.fpoly.kickshop.model.ProductSize;
import com.fpoly.kickshop.repository.ProductSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeService {
    @Autowired
    private ProductSizeRepository productSizeRepository;

    public List<ProductSizeDTO> getSizeByProductDetail(int id) {
        List<ProductSize> productSizeList = productSizeRepository.findAllByProductDetail_Id(id);
        return productSizeList.stream().map(size ->
                new ProductSizeDTO(size.getId(),
                        id,
                        size.getSize(),
                        size.getStock())).toList();
    }
}
