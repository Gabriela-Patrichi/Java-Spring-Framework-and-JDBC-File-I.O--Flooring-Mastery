package dao;

import com.gp.dao.OrderDao;
import com.gp.model.OrderDto;
import com.gp.model.ProductDto;
import com.gp.model.TaxDto;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDaoFileCollectionStubImpl implements OrderDao {

    OrderDto testOrderDto = new OrderDto(1,"Mary Jane", new TaxDto("WA", BigDecimal.valueOf(9.25)),
                                    new ProductDto("Tile", BigDecimal.valueOf(3.50), BigDecimal.valueOf(4.15)),
                                    BigDecimal.valueOf(104), BigDecimal.valueOf(364.00),BigDecimal.valueOf(431.60),BigDecimal.valueOf(73.593000),BigDecimal.valueOf(869.193000));

    @Override
    public List<OrderDto> readOrdersFile(LocalDate ordersDate, boolean shouldCreate) throws IOException {

        List<OrderDto> returnAllOrdersInfo= new ArrayList<OrderDto>();
        //add to the map the hard-coded ProductDto, with its productType as key
        returnAllOrdersInfo.add(testOrderDto);
        //return collection
        return returnAllOrdersInfo;
    }

    @Override
    public List<OrderDto> getAlLOrdersByDate(LocalDate orderDate) throws IOException {
        return null;
    }

    @Override
    public OrderDto addNewOrder(OrderDto newOrder) throws IOException {
        return null;
    }

    @Override
    public OrderDto retrieveOrder(LocalDate orderDate, int orderNumber) throws IOException {
        return null;
    }

    @Override
    public OrderDto updateOrder(OrderDto updatedOrder) throws IOException {
        return null;
    }

    @Override
    public void removeOrder(LocalDate orderDate, Integer orderNumber) throws IOException {

    }

    @Override
    public boolean writeToFile() throws IOException {
        return false;
    }


}
