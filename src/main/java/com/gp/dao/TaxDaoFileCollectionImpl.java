package com.gp.dao;

import java.math.BigDecimal;
import java.util.Map;

public class TaxDaoFileCollectionImpl implements TaxDao {

    //constructor which will read from file , create TaxDto and store it in a Map<String,BigDecimal>

  // Map<String, BigDecimal> taxDetails; //store the Tax info - state(k) and tax rate(v)

    public TaxDaoFileCollectionImpl(){

    }


    @Override
    public Map<String, BigDecimal> getAllTaxInfo() {
        return null;
    }
}
