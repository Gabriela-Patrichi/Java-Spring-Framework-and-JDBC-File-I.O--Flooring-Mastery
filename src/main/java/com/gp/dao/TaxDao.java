package com.gp.dao;

import com.gp.model.TaxDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface TaxDao {
    Map<String, BigDecimal> getAllTaxInfo();

}
