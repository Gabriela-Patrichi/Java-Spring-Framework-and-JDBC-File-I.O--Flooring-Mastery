package dao;

import com.gp.dao.TaxDao;
import com.gp.model.TaxDto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class TaxDaoFileCollectionStubImpl implements TaxDao {

    //hardcode an object as stubs will not read from file
    TaxDto testTaxDto = new TaxDto("TX", BigDecimal.valueOf(4.45));


    @Override
    public Map<String, BigDecimal> getAllTaxInfo() {
        //map to hold the collection
        Map<String, BigDecimal> returnAllTaxInfo = new HashMap<>();
        //add to map the hard-coded Tax data
        returnAllTaxInfo.put(testTaxDto.getStateAbbreviation(), testTaxDto.getTaxRate());
        // return collection
        return returnAllTaxInfo;
    }
}
