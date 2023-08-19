package dao;

import com.gp.dao.ProductDaoFileCollectionImpl;
import com.gp.model.ProductDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ProductDaoFileCollectionImplTest {

    @Test
    public void testGetAllProductsInfo() throws IOException {

        //Arrange
        ProductDaoFileCollectionImpl productDao=new ProductDaoFileCollectionImpl("TestProducts.txt");

        //Actual Output of the method (returns a map)
        Map<String, ProductDto> actualProductsData = productDao.getAllProductsInfo();

        //Expected Output
        Map<String,ProductDto> expectedProductData = new HashMap<>(actualProductsData);
        expectedProductData.put("Carpet", new ProductDto("Carpet", BigDecimal.valueOf(2.25), BigDecimal.valueOf(2.10)));

        //Assertions
        Assertions.assertEquals(actualProductsData.size(), expectedProductData.size());
    }
}
