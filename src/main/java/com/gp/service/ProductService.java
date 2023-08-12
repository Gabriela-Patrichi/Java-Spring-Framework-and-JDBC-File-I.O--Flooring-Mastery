package com.gp.service;

import com.gp.model.ProductDto;

import java.util.Map;

public interface ProductService {

    Map<String, ProductDto> getAllProductsInfo();
}
