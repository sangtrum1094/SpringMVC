package com.sang.minishops.service.imp;

import com.sang.minishops.entity.Image;
import com.sang.minishops.repository.ImageRepository;
import com.sang.minishops.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageServiceImp implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public void save(Image image) {
        imageRepository.save(image);
    }

    @Override
    public void deleteById(int id) {
        imageRepository.deleteById(id);
    }
}
