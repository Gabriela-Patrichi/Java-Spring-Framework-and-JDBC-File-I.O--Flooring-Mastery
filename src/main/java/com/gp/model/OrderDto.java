package com.gp.model;

import com.gp.dao.TaxDao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class OrderDto {
    private int orderNumber;
    private String customerName;
    private TaxDto taxDetails; // DTO
    private ProductDto productDetails; // DTO
    private BigDecimal area;
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal tax;
    private BigDecimal total;
    private LocalDate orderDate;

    //public Constructor
    public OrderDto(int orderNumber, String customerName, TaxDto taxDetails, ProductDto productDetails, BigDecimal area, BigDecimal materialCost, BigDecimal laborCost, BigDecimal tax, BigDecimal total) {
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
    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
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


    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return  orderNumber +
                "," + customerName +
                "," + taxDetails +
                "," + productDetails +
                "," + area +
                "," + materialCost +
                "," + laborCost +
                "," + tax +
                "," + total;
    }
}
