package com.gp.dao;


import com.gp.model.OrderDto;

import java.time.LocalDate;
import java.util.List;

public interface OrderDao {

    List<OrderDto> getAlLOrdersByDate(LocalDate orderDate);
    OrderDto addNewOrder(OrderDto newOrder);
    OrderDto retrieveOrder(LocalDate orderDate, Integer orderNumber);
    OrderDto updateOrder (OrderDto updateOrder);
    void removeOrder(LocalDate orderDate, Integer orderNumber);
    boolean writeToFile();

}
