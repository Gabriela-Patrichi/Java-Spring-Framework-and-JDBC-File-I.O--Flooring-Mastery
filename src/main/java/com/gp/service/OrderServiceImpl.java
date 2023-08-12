package com.gp.service;

import com.gp.dao.OrderDao;
import com.gp.model.OrderDto;
import com.gp.model.ProductDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService{

    //these will be required when adding a new order , or when editing a new order
   // TaxService taxService = new TaxServiceImpl();
    //ProductService productService = new ProducServiceImpl();

    @Override
    public List<OrderDto> getAlLOrdersByDate(LocalDate orderDate) {
        return null;
    }



    @Override
    public OrderDto addNewOrder(OrderDto newOrder) {
        // here use taxService.getAllTaxInfo();
        // the TaxService will reach out to the TaxDoa and call getAllTaxInfo();

        return null;
    }

    @Override
    public OrderDto retrieveOrder(LocalDate orderDate, Integer orderNumber) {
        return null;
    }

    @Override
    public OrderDto updateOrder(OrderDto updateOrder) {
        return null;
    }

    @Override
    public void removeOrder(LocalDate orderDate, Integer orderNumber) {

    }

    @Override
    public boolean writeToFile() {
        return false;
    }
}
