package com.sang.minishops.service.imp;

import com.sang.minishops.repository.ImageRepository;
import com.sang.minishops.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImp implements ImageService {
    @Autowired
    ImageRepository imageRepository;
    @Override
    public void deleteById(int id) {
      imageRepository.deleteById(id);
    }
}
