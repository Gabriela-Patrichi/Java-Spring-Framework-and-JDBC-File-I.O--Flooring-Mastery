package com.gp.service;

import com.gp.dao.OrderDao;
import com.gp.dao.OrderDaoFileCollectionImpl;
import com.gp.model.OrderDto;
import com.gp.model.ProductDto;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    //these will be required when adding a new order , or when editing a new order
    //service to service communication
    // TaxService taxService = new TaxServiceImpl();
    //ProductService productService = new ProducServiceImpl();

    OrderDao orderDao;

    public OrderServiceImpl() throws IOException {
        // LocalDate orderFileName;
        orderDao = new OrderDaoFileCollectionImpl();
    }

    @Override
    public List<OrderDto> getAlLOrdersByDate(LocalDate orderDate) throws IOException { //propagate exception
        List<OrderDto> returnedOrders = orderDao.getAlLOrdersByDate(orderDate);
        return returnedOrders;
    }

    @Override
    public OrderDto addNewOrder(OrderDto newOrder) throws IOException { //propagate exception
        return orderDao.addNewOrder(newOrder);
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
    public boolean writeToFile() throws IOException { //propagate exception
        return orderDao.writeToFile();
    }
}
