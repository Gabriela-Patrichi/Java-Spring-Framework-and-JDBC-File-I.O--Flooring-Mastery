package com.gp.service;

import com.gp.dao.OrderDao;
import com.gp.model.OrderDto;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface OrderService extends OrderDao{

    List<OrderDto> getAlLOrdersByDate(LocalDate orderDate) throws IOException;
    OrderDto addNewOrder(OrderDto newOrder) throws IOException;
    OrderDto retrieveOrder(LocalDate orderDate, int orderNumber) throws IOException;
    OrderDto updateOrder (OrderDto updateOrder);
    void removeOrder(LocalDate orderDate, Integer orderNumber);
    boolean writeToFile() throws IOException;

}

