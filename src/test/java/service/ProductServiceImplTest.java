package service;

import com.gp.dao.ProductDao;
import com.gp.dao.ProductDaoFileCollectionImpl;
import com.gp.dao.TaxDao;
import com.gp.model.ProductDto;
import com.gp.service.ProductService;
import com.gp.service.ProductServiceImpl;
import com.gp.service.TaxService;
import dao.ProductDaoFileCollectionStubImpl;
import dao.TaxDaoFileCollectionStubImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ProductServiceImplTest {

    @Test
    public void getAllTaxInfo() {
        // Arrange -  the service layer will interact wth the productDao Stub and not the actual Dao layer
        ProductDao productDaoStub = new ProductDaoFileCollectionStubImpl();
        ProductService productService = new ProductServiceImpl(productDaoStub);

        //Actual data
        Map<String, ProductDto> actualData = productService.getAllProductsInfo();
        //Expected
        Map<String, ProductDto> expectedData = new HashMap<>(actualData);
        expectedData.put("Carpet", new ProductDto("Carpet", BigDecimal.valueOf(2.25), BigDecimal.valueOf(2.10)));

        //Assert
        Assertions.assertEquals(actualData.size(), expectedData.size());

    }
}
