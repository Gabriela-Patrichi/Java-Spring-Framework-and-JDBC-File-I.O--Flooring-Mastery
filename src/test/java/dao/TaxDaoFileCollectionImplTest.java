package dao;

import com.gp.dao.ProductDaoFileCollectionImpl;
import com.gp.dao.TaxDaoFileCollectionImpl;
import com.gp.model.ProductDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class TaxDaoFileCollectionImplTest {

    @Test
    public void testGetAllTaxInfo() throws IOException {

        //Arrange
        TaxDaoFileCollectionImpl taxDao=new TaxDaoFileCollectionImpl("TestTaxes.txt");

        //Actual Output of the method
        Map<String, BigDecimal> actualTaxesData = taxDao.getAllTaxInfo();

        //Expected Output
        Map<String,BigDecimal> expectedTaxesData = new HashMap<>(actualTaxesData);

        //Assertions
        Assertions.assertEquals(actualTaxesData.isEmpty(),expectedTaxesData.isEmpty());
    }

}
