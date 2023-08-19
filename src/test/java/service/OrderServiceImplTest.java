package service;

import com.gp.dao.OrderDao;
import com.gp.dao.TaxDao;
import com.gp.model.OrderDto;
import com.gp.model.ProductDto;
import com.gp.model.TaxDto;
import com.gp.service.OrderService;
import com.gp.service.OrderServiceImpl;
import com.gp.service.TaxService;
import com.gp.service.TaxServiceImpl;
import dao.OrderDaoFileCollectionStubImpl;
import dao.TaxDaoFileCollectionStubImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderServiceImplTest {

    @Test
    public void readAllOrders() throws IOException {
        // Arrange -  the service layer will interact wth the taxDao Stub and not the actual Dao layer
        OrderDao orderDaoStub = new OrderDaoFileCollectionStubImpl();
        OrderService orderService = new OrderServiceImpl(orderDaoStub);

        //Actual data
        List<OrderDto> actualData = orderDaoStub.readOrdersFile(LocalDate.parse("2023-12-12"), false);

        //Expected
        List<OrderDto> expectedData = new ArrayList<>(actualData);

        //Assert
        Assertions.assertEquals(actualData.size(), expectedData.size());

    }
}
