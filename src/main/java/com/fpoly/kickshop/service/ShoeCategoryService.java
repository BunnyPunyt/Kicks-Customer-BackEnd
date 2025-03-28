package com.fpoly.kickshop.service;

import com.fpoly.kickshop.dto.ShoeCategoryDTO;
import com.fpoly.kickshop.repository.ShoeCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoeCategoryService {
    @Autowired
    private ShoeCategoryRepository shoeCategoryRepository;

    public List<ShoeCategoryDTO> getShoeCategories() {
        return shoeCategoryRepository.findAll()
                .stream()
                .map(sc -> new ShoeCategoryDTO(sc.getId(), sc.getName()))
                .collect(Collectors.toList());
    }
}
