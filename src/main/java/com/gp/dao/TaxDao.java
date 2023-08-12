package com.gp.dao;

import java.math.BigDecimal;
import java.util.Map;

public interface TaxDao {

    Map<String, BigDecimal> getAllTaxInfo();

}
