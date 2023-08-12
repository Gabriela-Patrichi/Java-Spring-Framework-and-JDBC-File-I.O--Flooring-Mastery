package com.gp.service;

import com.gp.dao.OrderDao;
import com.gp.model.OrderDto;

import java.time.LocalDate;
import java.util.List;

public interface OrderService extends OrderDao{

    List<OrderDto> getAlLOrdersByDate(LocalDate orderDate);
    OrderDto addNewOrder(OrderDto newOrder);
    OrderDto retrieveOrder(LocalDate orderDate, Integer orderNumber);
    OrderDto updateOrder (OrderDto updateOrder);
    void removeOrder(LocalDate orderDate, Integer orderNumber);
    boolean writeToFile();

}

