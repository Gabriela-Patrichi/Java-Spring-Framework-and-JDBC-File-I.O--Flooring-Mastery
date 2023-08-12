package com.gp.service;

import com.gp.dao.TaxDao;
import com.gp.dao.TaxDaoFileCollectionImpl;
import com.gp.model.TaxDto;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TaxServiceImpl implements TaxService{

    TaxDao taxDao=null; // TaxServiceImpl is dependant on TaxDaoFileCollectionImpl
    //constructor
    public  TaxServiceImpl() throws IOException {
        taxDao = new TaxDaoFileCollectionImpl();
    }

    @Override
    public Map<String, BigDecimal> getAllTaxInfo() {
        return taxDao.getAllTaxInfo();
    }

}
