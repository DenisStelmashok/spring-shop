package com.stelmashok.service;

import com.stelmashok.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAll();

    void addToUserBucket(Long productId, String username);

}
