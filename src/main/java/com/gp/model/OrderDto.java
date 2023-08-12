package com.gp.model;

import com.gp.dao.TaxDao;

import java.math.BigDecimal;
import java.util.Map;

public class OrderDto {
    private Integer orderNumber;
    private String customerName;
    private TaxDto taxDetails; // DTO
    private ProductDto productDetails;
    private BigDecimal area;
    private BigDecimal materialCost; // = area * costPerSquareFoot (ProductDto.getCostPerSquareFoot())
    private BigDecimal laborCost; //area * laborCostPerSquareFoot (ProductDto.getLaborCostPerSquareFoot())
    private BigDecimal tax; // materialCost + laborCost) * (taxRate/100) (TaxDto.getTaxRate())
    private BigDecimal total; // materialCost + laborCost + tax)


    //public Constructor
    public OrderDto(Integer orderNumber, String customerName, TaxDto taxDetails, ProductDto productDetails, BigDecimal area, BigDecimal materialCost, BigDecimal laborCost, BigDecimal tax, BigDecimal total) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.taxDetails = taxDetails;
        this.productDetails = productDetails;
        this.area = area;
        this.materialCost = materialCost;
        this.laborCost = laborCost;
        this.tax = tax;
        this.total = total;
    }

    // getters and setters to access the private variables

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public TaxDto getTaxDetails() {
        return taxDetails;
    }

    public void setTaxDetails(TaxDto taxDetails) {
        this.taxDetails = taxDetails;
    }

    public ProductDto getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDto productDetails) {
        this.productDetails = productDetails;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "orderNumber=" + orderNumber +
                ", customerName='" + customerName + '\'' +
                ", taxDetails=" + taxDetails +
                ", productDetails=" + productDetails +
                ", area=" + area +
                ", materialCost=" + materialCost +
                ", laborCost=" + laborCost +
                ", tax=" + tax +
                ", total=" + total +
                '}';
    }
}
