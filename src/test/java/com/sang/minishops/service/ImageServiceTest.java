package com.sang.minishops.service;

import com.sang.minishops.entity.Image;
import com.sang.minishops.repository.ImageRepository;
import com.sang.minishops.service.imp.ImageServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ImageServiceTest {
    @Mock
    ImageRepository imageRepository;

    ImageService imageService;
    @BeforeEach
    void setUp() {
      imageRepository=mock(ImageRepository.class);
      imageService= new ImageServiceImp(imageRepository);
    }

    @Test
    void save() {
        Image image = new Image();

        imageService.save(image);

        Mockito.verify(imageRepository).save(image);
    }

    @Test
    void deleteById() {
        int id =1;

        imageService.deleteById(id);

        Mockito.verify(imageRepository).deleteById(id);

    }
}