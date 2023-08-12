package com.gp.view;

import com.gp.model.ProductDto;
import com.gp.model.TaxDto;
import com.gp.service.ProductService;
import com.gp.service.ProductServiceImpl;
import com.gp.service.TaxService;
import com.gp.service.TaxServiceImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FlooringMasteryView {

    public void runMenu(){

        /*  // TAX SERVICE methods testing
        TaxService taxService=null;
        System.out.println("your tax info is as following:");
        try {
            taxService = new TaxServiceImpl();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("your tax info is as following:");

        Map<String, BigDecimal> showTaxInfo =taxService.getAllTaxInfo();
        if (showTaxInfo.isEmpty()){
            System.out.println("There are no entries in the Taxes information file.");
        } else {
            // get the Set of keys from the map
            Set<String> keys = showTaxInfo.keySet();

            // print the keys to the screen
            for (String k : keys) {
                System.out.println ( k + " " + showTaxInfo.get(k));
            }
        }
        */


        //PRODUCT TESTING
        ProductService productService;
        System.out.println("your products' info is as following:");

        try {
            productService= new ProductServiceImpl();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Assumin data has been read from the file, info is as following:");

        Map<String, ProductDto> showProductInfo =productService.getAllProductsInfo();
        if (showProductInfo.isEmpty()){
            System.out.println("There are no entries in the Products information file.");
        } else {
            // get the Set of keys from the map
            Set<String> keys = showProductInfo.keySet();
            // print the keys to the screen
            for (String k : keys) {
                System.out.println ( k + " " + showProductInfo.get(k));
            }
        }
    }
}
