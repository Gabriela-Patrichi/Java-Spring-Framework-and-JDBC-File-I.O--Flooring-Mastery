package com.gp.dao;

import com.gp.model.TaxDto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class TaxDaoFileCollectionImpl implements TaxDao {

    //store the Tax info - state(k) and tax rate(v)
    Map<String, BigDecimal> taxFileData = new HashMap<>();

    //constructor which will read from file, collect data,
    // create TaxDto and store it in the  Map<String,BigDecimal> collection
    public TaxDaoFileCollectionImpl() throws IOException {

        //1. create a File Object
        File myFile = new File("Taxes.txt");
        myFile.createNewFile(); // IOException

        //2.create a FileReader object - IOException
        FileReader fr = new FileReader(myFile);

        //3. create a BufferReader object
        BufferedReader br = new BufferedReader(fr);

        String line = br.readLine(); //store each line of the BufferReader in String
        //4. read line by line

        while (line != null) {

            //tokenize values, using  < , > as delimiter
            StringTokenizer st = new StringTokenizer(line, ",");

            //stateAbbreviation
            String taxState = st.nextToken();

            //stateName - not required in the scenario
            String taxName = st.nextToken();

            //taxRate
            String taxRateString = st.nextToken();
            //convert String to BigDecimal using BigDecimal constructor
            BigDecimal taxRate = new BigDecimal(taxRateString);

            // BigDecimal taxRate =BigDecimal.valueOf(Double.valueOf(taxRateString));

            //create new TaxDto object using the read values
            TaxDto myTaxDto = new TaxDto(taxState, taxRate);

            //add tax object to collection
            taxFileData.put(taxState, taxRate);

        }

    }

    @Override
    public Map<String, BigDecimal> getAllTaxInfo() {
        //make a copy of the taxFileData map to return
        Map<String, BigDecimal> returnedAllTaxInfo = new HashMap<>(taxFileData);
        return returnedAllTaxInfo; //return the collection
    }

}
/*
State,StateName,TaxRate
TX,Texas,4.45
WA,Washington,9.25
KY,Kentucky,6.00
CA,Calfornia,25.00
* */