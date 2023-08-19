package com.gp.model;

import java.math.BigDecimal;

public class ProductDto {
    private String productType; //this will be the key <String, ProductDto>, user will select a product by choosing the product name - in the requirements
    private BigDecimal costPerSquareFoot;
    private BigDecimal laborCostPerSquareFoot;

    public ProductDto(String productType, BigDecimal costPerSquareFoot, BigDecimal laborCostPerSquareFoot) {
        this.productType = productType;
        this.costPerSquareFoot = costPerSquareFoot;
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    @Override
    public String toString() {
        return
                productType + "," +
                        costPerSquareFoot + "," +
                        laborCostPerSquareFoot;
    }
}
