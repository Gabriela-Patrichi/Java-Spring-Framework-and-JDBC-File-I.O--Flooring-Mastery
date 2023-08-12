package com.gp.dao;

import com.gp.model.OrderDto;
import com.gp.model.ProductDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class OrderDaoFileCollectionImpl implements OrderDao{

    //constructor which will read from file

    public OrderDaoFileCollectionImpl(){

    }

    @Override
    public List<OrderDto> getAlLOrdersByDate(LocalDate orderDate) {
        return null;
    }

    @Override
    public OrderDto addNewOrder(OrderDto newOrder) {

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
