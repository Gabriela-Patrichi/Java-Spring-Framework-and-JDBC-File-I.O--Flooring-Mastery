package com.gp.dao;

import com.gp.model.ProductDto;
import com.gp.model.TaxDto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class ProductDaoFileCollectionImpl implements ProductDao {

    //constructor which will read from file , create ProductDto and store it in a Map<String,ProductDto>
    //Map <String, ProductDto> --- productType(in ProductDto) - (k), object itself (v)
    Map<String, ProductDto> productFileData = new HashMap<>();

    public ProductDaoFileCollectionImpl() throws IOException {

        //1. create a File Object
        File myFile = new File("Products.txt");
        myFile.createNewFile(); // IOException

        //2.create a FileReader object - IOException
        FileReader fr = new FileReader(myFile);

        //3. create a BufferReader object
        BufferedReader br = new BufferedReader(fr);

        String line = null; //store each line of the BufferReader in String
        //4. read line by line
        while ((line = br.readLine()) != null) {

            //tokenize values, using  < , > as delimiter
            StringTokenizer st = new StringTokenizer(line, ",");

            //productType
            String productType = st.nextToken();

            //costPerSquareFoot
            String cost = st.nextToken();
            //convert String to BigDecimal using BigDecimal constructor
            BigDecimal costPerSquareFoot = new BigDecimal(cost);

            //laborCostPerSquareFoot
            String laborCost = st.nextToken();
            BigDecimal laborCostPerSquareFoot = new BigDecimal(laborCost);

            //create new ProductDto object using the read values
            ProductDto myProductDto = new ProductDto(productType, costPerSquareFoot, laborCostPerSquareFoot);

            //add tax object to collection
            productFileData.put(productType, myProductDto);
        }
    }


    @Override
    public Map<String, ProductDto> getAllProductsInfo() {
        Map<String, ProductDto> returnedProductData = new HashMap<>(productFileData);
        return returnedProductData;
    }


    //FOR UNIT TESTING PURPOSES
    public ProductDaoFileCollectionImpl(String testFileName) throws IOException {

        //1. create a File Object
        File myFile = new File(testFileName);
        myFile.createNewFile(); // IOException

        //2.create a FileReader object - IOException
        FileReader fr = new FileReader(myFile);

        //3. create a BufferReader object
        BufferedReader br = new BufferedReader(fr);

        String line = null; //store each line of the BufferReader in String
        //4. read line by line
        while ((line = br.readLine()) != null) {

            //tokenize values, using  < , > as delimiter
            StringTokenizer st = new StringTokenizer(line, ",");

            //productType
            String productType = st.nextToken();

            //costPerSquareFoot
            String cost = st.nextToken();
            //convert String to BigDecimal using BigDecimal constructor
            BigDecimal costPerSquareFoot = new BigDecimal(cost);

            //laborCostPerSquareFoot
            String laborCost = st.nextToken();
            BigDecimal laborCostPerSquareFoot = new BigDecimal(laborCost);

            //create new ProductDto object using the read values
            ProductDto myProductDto = new ProductDto(productType, costPerSquareFoot, laborCostPerSquareFoot);

            //add tax object to collection
            productFileData.put(productType, myProductDto);
        }
    }
}
