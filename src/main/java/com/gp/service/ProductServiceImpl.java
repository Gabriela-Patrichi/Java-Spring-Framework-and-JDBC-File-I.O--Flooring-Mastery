package com.gp.service;

import com.gp.dao.ProductDao;
import com.gp.dao.ProductDaoFileCollectionImpl;
import com.gp.dao.TaxDao;
import com.gp.model.ProductDto;

import java.io.IOException;
import java.util.Map;

public class ProductServiceImpl implements ProductService{

    ProductDao productDao; //dependent of the ProductDAO

    public ProductServiceImpl() throws IOException {
        productDao = new ProductDaoFileCollectionImpl();
    }

    @Override
    public Map<String, ProductDto> getAllProductsInfo() {
        return productDao.getAllProductsInfo();
    }
}
