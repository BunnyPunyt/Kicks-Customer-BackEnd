package com.fpoly.kickshop.service;

import com.fpoly.kickshop.dto.GenderCategoryDTO;
import com.fpoly.kickshop.repository.GenderCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenderCategoryService {
    @Autowired
    private GenderCategoryRepository genderCategoryRepository;

    public List<GenderCategoryDTO> getAllGenderCategories() {
        return genderCategoryRepository.findAll()
                .stream()
                .map(gc -> new GenderCategoryDTO(gc.getId(), gc.getName()))
                .collect(Collectors.toList());
    }
}
