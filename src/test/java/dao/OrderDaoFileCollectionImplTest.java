package dao;

import com.gp.dao.OrderDaoFileCollectionImpl;
import com.gp.dao.ProductDaoFileCollectionImpl;
import com.gp.dao.TaxDaoFileCollectionImpl;
import com.gp.model.OrderDto;
import com.gp.model.ProductDto;
import com.gp.model.TaxDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class OrderDaoFileCollectionImplTest {


    @Test
    public void testReadOrdersFile() throws IOException {
        //Arrange
        OrderDaoFileCollectionImpl orderDao = new OrderDaoFileCollectionImpl();

        //Actual output
        //this will return 1 OrderDto
        List<OrderDto> actualData = orderDao.readOrdersFile("TestOrders_2023-12-12.txt");
        //add another Object
        actualData.add(new OrderDto(1,"Dummy Values",new TaxDto("WA", BigDecimal.valueOf(9.25)),new ProductDto("Tile",BigDecimal.valueOf(3.50), BigDecimal.valueOf(4.15)),
                BigDecimal.valueOf(104), BigDecimal.valueOf(364.00), BigDecimal.valueOf(431.60), BigDecimal.valueOf(73.593000), BigDecimal.valueOf(869.193000)));

        //expected Output
        List<OrderDto> expectedData = new ArrayList<>(actualData);

        //Assert - should now be 2 OrderDto inside each collection
        Assertions.assertEquals(actualData.size(), expectedData.size());

    }

    @Test
    public void removeOrder() throws IOException {
        OrderDaoFileCollectionImpl orderDao= new OrderDaoFileCollectionImpl();

        //Actualoutput
        List<OrderDto> actualData = orderDao.readOrdersFile("TestOrders_2023-12-12.txt");
        orderDao.removeOrder("TestOrders_2023_12-12.txt",1);

        //expected Output
        List<OrderDto> expectedData = new ArrayList<>(actualData);

        //Assert - should now be 2 OrderDto inside each collection
        Assertions.assertEquals(actualData.size(), expectedData.size());

    }

}
