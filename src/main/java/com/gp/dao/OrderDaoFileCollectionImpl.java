package com.gp.dao;

import com.gp.model.OrderDto;
import com.gp.model.ProductDto;
import com.gp.model.TaxDto;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class OrderDaoFileCollectionImpl implements OrderDao {

    //will store the orders in a map , with the date as key value
    Map<LocalDate, OrderDto> orderFileData = new HashMap<>();

    //store orders from same date in an array list
    List<OrderDto> ordersOnSameDate = new ArrayList<OrderDto>();

    //constructor (which will read from file) - REFACTORED - readOrdersFile()

    /* public OrderDaoFileCollectionImpl() throws IOException {

        //  LocalDate orderDate = LocalDate.now();
        //  String orderFileName = "Orders_" + orderDate;

        LocalDate orderDateFileName= LocalDate.now();
        //1. create a File Object
        File myFile = new File(String.valueOf(orderDateFileName)); //convert LocalDate to String value
        //myFile.createNewFile(); // IOException

        //2. FileReader Object
        FileReader fr = new FileReader(myFile);

        //3. create a BufferReader object
        BufferedReader br = new BufferedReader(fr);

        //4. read line by line
        String line = null;
        while ((line = br.readLine()) != null) {
            //tokenize values, using  < , > as delimiter
            StringTokenizer st = new StringTokenizer(line, ",");

           // System.out.println(line); - testing purposes

            //orderNumber
            String orderNumberString = st.nextToken();
            Integer orderNumber = Integer.parseInt(orderNumberString);

            //customerName
            String customerName = st.nextToken();

            //state
            String state = st.nextToken();

            //tax rate
            String taxRateString = st.nextToken();
            //convert String to BigDecimal using BigDecimal constructor
            BigDecimal taxRate = new BigDecimal(taxRateString);

            //productType
            String productType = st.nextToken();

            //area -big decimal
            String areaString = st.nextToken();
            BigDecimal area = new BigDecimal(areaString);

            //costPerSquareFoot
            String costPerSquareFootString = st.nextToken();
            BigDecimal costPerSquareFoot = new BigDecimal(costPerSquareFootString);

            //laborCostPerSquareFoot
            String laborCostPerSquareFootString = st.nextToken();
            BigDecimal laborCostPerSquareFoot = new BigDecimal(laborCostPerSquareFootString);

            //materialCost
            String materialCostString = st.nextToken();
            BigDecimal materialCost = new BigDecimal(materialCostString);

            //laborCost
            String laborCostString = st.nextToken();
            BigDecimal laborCost = new BigDecimal(laborCostString);

            //tax
            String taxString = st.nextToken();
            BigDecimal tax = new BigDecimal(taxString);

            //total
            String totalString = st.nextToken();
            BigDecimal total = new BigDecimal(totalString);

            //create new OrderDto object using the read values
            OrderDto myOrderDto = new OrderDto(orderNumber, customerName, new TaxDto(state, taxRate),
                    new ProductDto(productType, costPerSquareFoot, laborCostPerSquareFoot), area, materialCost, laborCost, tax, total);

            //set the date of the order with the date of the file
            myOrderDto.setOrderDate(orderDateFileName);

            //add Order DTO to collection
            ordersOnSameDate.add(myOrderDto);
            orderFileData.put(orderDateFileName, myOrderDto);

           // orderFileData.put(orderFileName, myOrderDto);- will replace with collection of DTOs
        }
      //  orderFileData.put(orderDateFileName, ordersOnSameDate);
    }
    */

    //readOrders from file taking in order's date and boolean shouldCreate value,
    // in order to later differentiate between reading and adding orders
    // only when adding a new order should the file be created (no need to create a new file if only reading )

    private List<OrderDto> readOrdersFile(LocalDate ordersDate, boolean shouldCreate) throws IOException {
        String orderFileName = "Orders_" + String.valueOf(ordersDate) + ".txt"; //convert LocalDate to String value

        File myFile = new File(orderFileName);
        // if file does not exist, and shouldCreate is true, then create file
        if (!myFile.exists() && shouldCreate) {
            myFile.createNewFile(); // if file does not exist, and shouldCreate is true, then create file
        }

        //2. FileReader Object
        FileReader fr = new FileReader(myFile);
        //3. create a BufferReader object
        BufferedReader br = new BufferedReader(fr);

        //4. read line by line
        String line = null;
        while ((line = br.readLine()) != null) {
            //tokenize values, using  < , > as delimiter
            StringTokenizer st = new StringTokenizer(line, ",");

            //orderNumber
            String orderNumberString = st.nextToken();
            Integer orderNumber = Integer.parseInt(orderNumberString);

            //customerName
            String customerName = st.nextToken();

            //state
            String state = st.nextToken();

            //tax rate
            String taxRateString = st.nextToken();
            //convert String to BigDecimal using BigDecimal constructor
            BigDecimal taxRate = new BigDecimal(taxRateString);

            //productType
            String productType = st.nextToken();

            //area -big decimal
            String areaString = st.nextToken();
            BigDecimal area = new BigDecimal(areaString);

            //costPerSquareFoot
            String costPerSquareFootString = st.nextToken();
            BigDecimal costPerSquareFoot = new BigDecimal(costPerSquareFootString);

            //laborCostPerSquareFoot
            String laborCostPerSquareFootString = st.nextToken();
            BigDecimal laborCostPerSquareFoot = new BigDecimal(laborCostPerSquareFootString);

            //materialCost
            String materialCostString = st.nextToken();
            BigDecimal materialCost = new BigDecimal(materialCostString);

            //laborCost
            String laborCostString = st.nextToken();
            BigDecimal laborCost = new BigDecimal(laborCostString);

            //tax
            String taxString = st.nextToken();
            BigDecimal tax = new BigDecimal(taxString);

            //total
            String totalString = st.nextToken();
            BigDecimal total = new BigDecimal(totalString);

            //create new OrderDto object using the read values
            OrderDto myOrderDto = new OrderDto(orderNumber, customerName, new TaxDto(state, taxRate),
                    new ProductDto(productType, costPerSquareFoot, laborCostPerSquareFoot), area, materialCost, laborCost, tax, total);

            //set the date of the order with the date of the file
            myOrderDto.setOrderDate(ordersDate);

            //add Order DTO to collection
            ordersOnSameDate.add(myOrderDto);
            //add to the map the List of OrderDtos (on the same date), with the orderDate as key value
            //orderFileData.put(ordersDate, ordersOnSameDate);
        }

        return ordersOnSameDate;
    }

    @Override
    public List<OrderDto> getAlLOrdersByDate(LocalDate orderDate) throws IOException { //- propagate exception to View layer
        //call readOrders method and pass shouldCreate as false
        readOrdersFile(orderDate, false);
        // store the collection in an array to return
        List<OrderDto> returnedOrders = new ArrayList<>(ordersOnSameDate);
        return returnedOrders;
    }

    @Override
    public OrderDto addNewOrder(OrderDto newOrder) throws IOException { //IOException from readOrdersFile()

        //first, read orders from file - read data and put together the array collection of Order DTOs
        //set shouldCreate value to true, so that if it is the first order the file will be created
        readOrdersFile(newOrder.getOrderDate(), true);

        //consider the orderId - which automatically increments
        int orderNumber = 0;
        if (ordersOnSameDate.size() == 0) { //if list is empty, start order number at 1
            orderNumber = 1;
        } else { //else increment the last entered order number by 1
            orderNumber = ordersOnSameDate.get(ordersOnSameDate.size() - 1).getOrderNumber() + 1;
        }

        //set the orderNumber
        newOrder.setOrderNumber(orderNumber);
        ordersOnSameDate.add(newOrder); //add the Order to the collection

        //save the order to File, because if the user wants to perform other operations on orders of a different date,
        //this collection will be replaced with other values, and so the data of this newly added Order will be lost
        writeToFile();

        return newOrder; //return the OrderDto
    }

    @Override
    public OrderDto retrieveOrder(LocalDate orderDate, int orderNumber) throws IOException {
        //instantiate an order DTO, to hold the return value object
        OrderDto returnedOrderDto=null;

        //call readOrdersFile() , with orderDate and shouldCreate false
        readOrdersFile(orderDate, false);

        //traverse the collection of the same date orders and search for the order number
        for(int i=0; i< ordersOnSameDate.size();i++){
            if (ordersOnSameDate.get(i).getOrderNumber()==orderNumber){ //if order numbers match
                returnedOrderDto= ordersOnSameDate.get(i);
            }
        }
        return returnedOrderDto;
    }

    @Override
    public OrderDto updateOrder(OrderDto updatedOrder) throws IOException {

        //traverse the collection to search for the required OrderDto (identifiable by the unique order number)
        //when found, replace the object with the updated one
        for(int i=0; i< ordersOnSameDate.size(); i++){
            if(ordersOnSameDate.get(i).getOrderNumber() == updatedOrder.getOrderNumber()){
                updatedOrder=ordersOnSameDate.get(i);
//                ordersOnSameDate.set(i,updatedOrder);
            }
        }
        //save to file
        writeToFile();
        return updatedOrder;
    }


    @Override
    public void removeOrder(LocalDate orderDate, Integer orderNumber) {

    }

    @Override
    public boolean writeToFile() throws IOException {

        //String to compose the file name
        String fileName = "Orders_" + ordersOnSameDate.get(0).getOrderDate() + ".txt";

        //FileWriter
        FileWriter fw = new FileWriter(fileName); //throws IOException

        //loop through the orders array collection and write each object to file
        for (OrderDto order : ordersOnSameDate) {
            fw.write((order.toString() + "\n").toCharArray());
            fw.flush(); //flush data
        }
        return true;
    }
}

/*
OrderNumber – Integer
CustomerName – String
State – String
TaxRate – BigDecimal
ProductType – String
Area – BigDecimal
CostPerSquareFoot – BigDecimal
LaborCostPerSquareFoot – BigDecimal
MaterialCost – BigDecimal
LaborCost – BigDecimal
Tax – BigDecimal
Total – BigDecimal

*
1,Ada Lovelace,CA,25.00,Tile,249.00,3.50,4.15,871.50,1033.35,476.21,2381.06
*/