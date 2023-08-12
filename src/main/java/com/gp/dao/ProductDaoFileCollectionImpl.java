package com.gp.dao;

import com.gp.model.ProductDto;

import java.util.Map;

public class ProductDaoFileCollectionImpl implements ProductDao{

    //constructor which will read from file , create ProductDto and store it in a Map<String,ProductDto>


    //Map <String, ProductDto> --- productType(in ProductDto) - (k), object itself (v)
    public ProductDaoFileCollectionImpl(){

    }

    @Override
    public Map<String, ProductDto> getAllProductsInfo() {
        return null;
    }

}
