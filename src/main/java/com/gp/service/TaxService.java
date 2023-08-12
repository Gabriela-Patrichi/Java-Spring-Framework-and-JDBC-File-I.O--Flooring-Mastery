package com.gp.service;

import com.gp.model.TaxDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface TaxService {

   Map<String, BigDecimal> getAllTaxInfo();

}
