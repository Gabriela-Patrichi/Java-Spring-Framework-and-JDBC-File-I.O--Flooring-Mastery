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

    OrderDao orderDao;

    public OrderServiceImpl() throws IOException {
        orderDao = new OrderDaoFileCollectionImpl();
    }


    @Override
    public List<OrderDto> readOrdersFile(LocalDate ordersDate, boolean shouldCreate) throws IOException {
        return orderDao.readOrdersFile(ordersDate, shouldCreate);
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
    public OrderDto retrieveOrder(LocalDate orderDate, int orderNumber) throws IOException {

        return orderDao.retrieveOrder(orderDate, orderNumber);
    }

    @Override
    public OrderDto updateOrder(OrderDto updatedOrder) throws IOException {

        return orderDao.updateOrder(updatedOrder);
    }

    @Override
    public void removeOrder(LocalDate orderDate, Integer orderNumber) throws IOException {
        orderDao.removeOrder(orderDate, orderNumber);
    }

    @Override
    public boolean writeToFile() throws IOException { //propagate exception
        return orderDao.writeToFile();
    }


}
