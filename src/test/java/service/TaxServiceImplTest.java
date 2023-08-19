package service;

import com.gp.dao.TaxDao;
import com.gp.dao.TaxDaoFileCollectionImpl;
import com.gp.service.TaxService;
import com.gp.service.TaxServiceImpl;
import dao.TaxDaoFileCollectionStubImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class TaxServiceImplTest {

    @Test
    public void getAllTaxInfo() {

        // Arrange -  the service layer will interact wth the taxDao Stub and not the actual Dao layer
        TaxDao taxDaoStub = new TaxDaoFileCollectionStubImpl();
        TaxService taxService;
        taxService = new TaxServiceImpl(taxDaoStub);

        //Actual data
        Map<String, BigDecimal> actualData = taxService.getAllTaxInfo();

        //Expected
        Map<String, BigDecimal> expectedData = new HashMap<>(actualData);
        expectedData.put("TX", BigDecimal.valueOf(4.45));

        //Assert
        Assertions.assertEquals(actualData.size(), expectedData.size());
    }
}
