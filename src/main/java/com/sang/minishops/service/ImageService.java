package com.sang.minishops.service;

import com.sang.minishops.entity.Image;

public interface ImageService {
    void save(Image image);
    void deleteById(int id);
}
