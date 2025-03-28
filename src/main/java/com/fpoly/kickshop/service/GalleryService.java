package com.fpoly.kickshop.service;

import com.fpoly.kickshop.model.Gallery;
import com.fpoly.kickshop.repository.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GalleryService {
    @Autowired
    private GalleryRepository galleryRepository;

    public Gallery getGalleryDefaultByProductDetailId(int id) {
        return galleryRepository.findGalleriesByIsDefaultTrueAndProductDetail_Id(id);
    }
    public List<Gallery> getAllGallery(int id) {
        List<Gallery> gallery = galleryRepository.findAllByProductDetail_Id(id);
        return gallery.stream().toList();
    }
}
