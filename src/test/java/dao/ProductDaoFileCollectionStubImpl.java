package dao;

import com.gp.dao.ProductDao;
import com.gp.model.ProductDto;
import com.gp.model.TaxDto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ProductDaoFileCollectionStubImpl implements ProductDao {


    //hardcode an object as stubs will not read from file
    ProductDto testProductDto = new ProductDto("Carpet", BigDecimal.valueOf(2.25), BigDecimal.valueOf(2.10));


    @Override
    public Map<String, ProductDto> getAllProductsInfo() {

        //map to hold the collection
        Map<String,ProductDto> returnAllProductsInfo= new HashMap<String,ProductDto>();
        //add to the map the hard-coded ProductDto, with its productType as key
        returnAllProductsInfo.put(testProductDto.getProductType(), testProductDto);
        //return collection
        return returnAllProductsInfo;
    }
}
