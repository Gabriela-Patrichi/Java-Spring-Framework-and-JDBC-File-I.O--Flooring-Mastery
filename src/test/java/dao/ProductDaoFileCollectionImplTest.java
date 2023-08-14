package dao;

import com.gp.dao.ProductDaoFileCollectionImpl;
import com.gp.model.ProductDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
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

        //Assertions
        Assertions.assertEquals(actualProductsData.size(), expectedProductData.size());
    }

}
