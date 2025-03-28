package com.fpoly.kickshop.api;

import com.fpoly.kickshop.dto.*;
import com.fpoly.kickshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class productApi {
    @Autowired
    private ProductService productService;

    @GetMapping("/showProduct2")
    public List<AllProductsDTO> reproductivelyDOS() {
        return productService.GetallProductDTO();
    }
}
