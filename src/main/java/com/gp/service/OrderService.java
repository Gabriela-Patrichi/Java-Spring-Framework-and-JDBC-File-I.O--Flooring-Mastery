package com.gp.service;

import com.gp.dao.OrderDao;
import com.gp.model.OrderDto;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface OrderService extends OrderDao{

    public List<OrderDto> readOrdersFile(LocalDate ordersDate, boolean shouldCreate) throws IOException;
    List<OrderDto> getAlLOrdersByDate(LocalDate orderDate) throws IOException;
    OrderDto addNewOrder(OrderDto newOrder) throws IOException;
    OrderDto retrieveOrder(LocalDate orderDate, int orderNumber) throws IOException;
    OrderDto updateOrder (OrderDto updatedOrder) throws IOException;
    void removeOrder(LocalDate orderDate, Integer orderNumber) throws IOException;
    boolean writeToFile() throws IOException;

}

