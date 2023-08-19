package com.gp.model;

import java.math.BigDecimal;


//is this still needed then ??

public class TaxDto {

    //   private int taxId; - no longer needed , will take stateAbbreviation as key instead (only 1 state on each order)
    private String stateAbbreviation; // Primary key
    private String stateName;
    private BigDecimal taxRate;


    //constructor - only need these var for the OrderDto
    public TaxDto(String stateAbbreviation, BigDecimal taxRate) {
        this.stateAbbreviation = stateAbbreviation;
        this.taxRate = taxRate;
    }

    //getters and setters for the Map
    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    public void setStateAbbreviation(String stateAbbreviation) {
        this.stateAbbreviation = stateAbbreviation;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    @Override
    public String toString() {
        return stateAbbreviation +
                "," + taxRate;
    }
}
