package com.gp.dao;

import com.gp.model.ProductDto;

import java.math.BigDecimal;
import java.util.Map;

public interface ProductDao {


   //method to get all products info (for adding an order for ex)
    Map<String, ProductDto> getAllProductsInfo();
}
