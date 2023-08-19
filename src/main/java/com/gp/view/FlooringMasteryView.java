package com.gp.view;

import com.gp.model.OrderDto;
import com.gp.model.ProductDto;
import com.gp.model.TaxDto;
import com.gp.service.*;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.*;

public class FlooringMasteryView {

    static Scanner input = new Scanner(System.in);

    public void runMenu() {

        ProductService productService = null;
        try {
            productService = new ProductServiceImpl();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        TaxService taxService = null;
        try {
            taxService = new TaxServiceImpl();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        OrderService orderService = null;
        try { //deal with the propagated exception
            orderService = new OrderServiceImpl();
        } catch (IOException e) {
            System.out.println("There was an error in reading the file. Please try again.");
        }

        //Maps of tax and product info has been declared here to be able to access them in 2 distinct functionalities(addOrder, updateOder)
        //call getAllTaxInfo() from TaxService , which will return a map
        Map<String, BigDecimal> taxInfoCollection = taxService.getAllTaxInfo();
        // get the Set of keys from the map
        Set<String> keys = taxInfoCollection.keySet();

        //similarly for products
        Map<String, ProductDto> productInfoMap = productService.getAllProductsInfo();
        // get the Set of keys from the map
        Set<String> productKeys = productInfoMap.keySet();

        char continueOption = 'n';
        do {
            System.out.println(" * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
            System.out.println("*");
            System.out.println("* <<Flooring Program>>");
            System.out.println("* 1. Display Orders");
            System.out.println("* 2. Add an Order");
            System.out.println("* 3. Edit an Order");
            System.out.println("* 4. Remove an Order");
            System.out.println("* 5. Export All Data"); //no longer needed
            System.out.println("* 6. Quit");
            System.out.println("*");
            System.out.println(" * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");

            System.out.print("Please select your option:");
            try {
                int userOption = input.nextInt();

                switch (userOption) {
                    case 1:
                        System.out.println("Enter the date for which you wish to display the orders: (YYYY-MM-DD)");
                        input.nextLine();
                        String ordersDateString = input.nextLine();
                        LocalDate ordersDate = LocalDate.parse(ordersDateString);
                        try {
                            List<OrderDto> printOrders = orderService.getAlLOrdersByDate(ordersDate);
                            if (printOrders.isEmpty()) {
                                System.out.println("Sorry, there are no existing orders for that date.");
                                break;
                            } else {
                                System.out.println("Order(s) retrieved:");
                                //print out order's values one by one, on separate lines, commenting what they represent, to enrich the user experience
                                for (OrderDto order : printOrders) {
                                    System.out.println("\nOrder number: " + order.getOrderNumber() + "\nCustomer name: " + order.getCustomerName()
                                            + "\nState: " + order.getTaxDetails().getStateAbbreviation()
                                            + "\nTax rate: " + order.getTaxDetails().getTaxRate()
                                            + "\nProduct type: " + order.getProductDetails().getProductType() + "\nArea: " + order.getArea()
                                            + "\nCost per square foot: " + order.getProductDetails().getCostPerSquareFoot()
                                            + "\nLabor cost per square foot: " + order.getProductDetails().getLaborCostPerSquareFoot()
                                            + "\nMaterial cost: " + order.getMaterialCost() + "\nLabor cost: " + order.getLaborCost()
                                            + "\nTax: " + order.getTax() + "\nTotal: " + order.getTotal()
                                            + "\n--------------------------------------------");
                                }
                                //make sure collection of OrderDto is empty
                                List<OrderDto> collectionToClear = orderService.readOrdersFile(ordersDate, false);
                                //clear the collection before returning to main menu
                                collectionToClear.clear();
                                runMenu();
                            }
                        } catch (IOException e) { //if file does not exist
                            System.out.println("Sorry, there are no existing orders for that date.");
                            break;
                        }

                    case 2:
                        //tax and product objects, which make part of the order object
                        TaxDto taxDetails = null;
                        ProductDto productDetails = null;
                        OrderDto myOrderDto = null;

                        System.out.println("Let's enter the new order details!");
                        System.out.println("First, enter the order's date: (YYYY-MM-DD)");
                        input.nextLine();
                        String dateString = input.nextLine();
                        LocalDate orderDtoDate = LocalDate.parse(dateString); //parse

                        //customer name
                        System.out.println("Enter customer name:");
                        String customerName = input.nextLine();
                        if (customerName.isEmpty()) { //validation
                            System.out.println("Customer name field cannot be empty. Redirecting you to main menu...\n");
                            runMenu();
                        }

                        //TAXES
                        //display choices to the user and prompt to choose one of the options
                        System.out.println("Enter Tax State: Option are limited to : " + keys);
                        String state = input.nextLine();

                        if (state.isEmpty()) { //validation
                            System.out.println("This field cannot be empty. Please try again.\nRedirecting you to main menu...\n");
                            runMenu();
                        }

                        //check if the user input matches a map key
                        for (String k : keys) {
                            if (k.equals(state)) { //if yes, get the key ad value and store them in a TaxDto
                                System.out.println("Ok. Order state has been set to:" + k +
                                        ", with a tax rate value of: " + taxInfoCollection.get(k) + ".\n");
                                taxDetails = new TaxDto(k, taxInfoCollection.get(k));
                            }
                        }
                        //if the input does not match a key, display msg and redirect to main menu
                        if (taxDetails == null) {
                            System.out.println("Sorry, this state is not on the list! Redirecting you back to main menu...");
                            runMenu();
                        }

                        //PRODUCT
                        System.out.println("Next, here is the list of available products and pricing information to choose from.");

                        // print the keys to the screen - to allow user to choose the product
                        for (String k : productKeys) {
                            System.out.println("Option " + k + ": " + productInfoMap.get(k));
                        }
                        System.out.println("Please type in the product name you wish to add to your order (case sensitive):");
                        String productChoice = input.nextLine();

                        //product field cannot be left empty
                        if (productChoice.isEmpty()) { //validation
                            System.out.println("Product choice field cannot be left empty. Redirecting you to main menu...\n");
                            runMenu();
                        }
                        for (String k : productKeys) {
                            if (k.equals(productChoice)) { //if yes, get the key ad value and store them in a TaxDto
                                System.out.println("Ok. Product details have been set to: " + productInfoMap.get(k).getProductType() +
                                        ", " + productInfoMap.get(k).getCostPerSquareFoot() + " cost per square foot and " +
                                        productInfoMap.get(k).getLaborCostPerSquareFoot() + " labor cost per square foot.\n");
                                productDetails = new ProductDto(k, productInfoMap.get(k).getCostPerSquareFoot(), productInfoMap.get(k).getLaborCostPerSquareFoot());
                            }
                        }
                        //if the input does not match a key (meaning that the productDetails object is null)
                        // display msg and redirect to main menu
                        if (productDetails == null) {
                            System.out.println("Sorry, this product is not on the list! Redirecting you back to main menu...");
                            runMenu();
                        }

                        //Area
                        System.out.println("Next, please input the area (minimum 100).");
                        int areaInt = input.nextInt();
                        //check for minimum 100 condition
                        if (areaInt < 100) {
                            System.out.println("Sorry, area requirements have not been met(minimum 100).");
                            System.out.println("Redirecting you back to main menu....");
                            runMenu();
                        }
                        BigDecimal area = BigDecimal.valueOf(areaInt); //convert int to BigDecimal

                        //material cost = area * costPerSquareFoot - BigDecimal does not take "*" as operand; instead multiply();
                        // call getCostPerSquareFoot() of the above created ProductDto
                        BigDecimal materialCost = area.multiply(productDetails.getCostPerSquareFoot());

                        //similarly to laborCost
                        BigDecimal laborCost = area.multiply(productDetails.getLaborCostPerSquareFoot());

                        //tax = (materialCost + laborCost) * (taxRate/100) - will call TaxDto this time
                        // transform int 100 to a BigDecimal in order to perform division
                        BigDecimal tax = (materialCost.add(laborCost)).multiply(taxDetails.getTaxRate().divide(BigDecimal.valueOf(100)));

                        //total
                        BigDecimal total = materialCost.add(laborCost).add(tax);

                        //set OrderNumber initially to a dummy value, however this will be checked upon calling the addOrder(),
                        //if this is the first order, it will assign value 1 , else it will increment the orderNumber comparing the last entered order id
                        int orderNumber = 0;

                        // form the order DTO
                        myOrderDto = new OrderDto(orderNumber, customerName, taxDetails, productDetails, area, materialCost, laborCost, tax, total);

                        //set the order date for the orderDTO
                        myOrderDto.setOrderDate(orderDtoDate);

                        System.out.println("Here are the new order details: ");
                        //Refactored myOrderDto.toString() , for a more friendly user experience
                        System.out.println("Order Date: " + orderDtoDate
                                + "\nCustomer name: " + myOrderDto.getCustomerName()
                                + "\nState: " + myOrderDto.getTaxDetails().getStateAbbreviation()
                                + "\nTax rate: " + myOrderDto.getTaxDetails().getTaxRate()
                                + "\nProduct type: " + myOrderDto.getProductDetails().getProductType() + "\nArea: " + myOrderDto.getArea()
                                + "\nCost per square foot: " + myOrderDto.getProductDetails().getCostPerSquareFoot()
                                + "\nLabor cost per square foot" + myOrderDto.getProductDetails().getLaborCostPerSquareFoot()
                                + "\nMaterial cost: " + myOrderDto.getMaterialCost() + "\nLabor cost: " + myOrderDto.getLaborCost()
                                + "\nTax: " + myOrderDto.getTax() + "\nTotal: " + myOrderDto.getTotal() + "."
                                + "\n--------------------------------------------");

                        System.out.println("Do you wish to add this order to the system? y/n");
                        char choice = input.next().charAt(0);
                        if (choice == 'y') {
                            try { // IOException handling
                                orderService.addNewOrder(myOrderDto);
                                System.out.println("Order added to the system.");
                            } catch (IOException e) {
                                e.printStackTrace();
                                System.out.println("Something went wrong when trying to save the order to file. Please try again.");
                            }
                        } else {
                            System.out.println("Adding new order was cancelled. Redirecting you to the main menu...");
                            runMenu();
                        }
                        //clear the collection before running the menu again
                        List<OrderDto> collectionToClear = orderService.readOrdersFile(orderDtoDate, false);
                        collectionToClear.clear();
                        runMenu();

                    case 3: //first retrieve the order, and then update the order
                        System.out.println("Edit an order:");
                        // first, prompt the user for te order date and order number
                        System.out.println("Please insert the date of the order: (YYYY-MM-DD)");
                        input.nextLine();
                        String date = input.nextLine();
                        LocalDate orderByDate = LocalDate.parse(date); //parse
                        System.out.println("Please insert the order number:");
                        int orderbyNumber = input.nextInt();

                        //call retrieveOrder() and store its return value in order DTO (retrievedOrder)
                        OrderDto retrievedOrder = orderService.retrieveOrder(orderByDate, orderbyNumber);

                        //check if the value is null
                        if (retrievedOrder == null) {
                            System.out.println("Could not locate an order with these details.");
                            break;
                        }
                        //if the order is found display details to user
                        else {
                            System.out.println("Order found!");
                            System.out.println("Customer name: " + retrievedOrder.getCustomerName()
                                    + "\nState: " + retrievedOrder.getTaxDetails().getStateAbbreviation()
                                    + "\nTax rate: " + retrievedOrder.getTaxDetails().getTaxRate()
                                    + "\nProduct type: " + retrievedOrder.getProductDetails().getProductType() + "\nArea: " + retrievedOrder.getArea()
                                    + "\nCost per square foot: " + retrievedOrder.getProductDetails().getCostPerSquareFoot()
                                    + "\nLabor cost per square foot: " + retrievedOrder.getProductDetails().getLaborCostPerSquareFoot()
                                    + "\nMaterial cost: " + retrievedOrder.getMaterialCost() + "\nLabor cost: " + retrievedOrder.getLaborCost()
                                    + "\nTax: " + retrievedOrder.getTax() + "\nTotal: " + retrievedOrder.getTotal() + "."
                                    + "\n--------------------------------------------");

                            //Second step, update the order details
                            System.out.println("Do you wish to update this order? y/n");
                            char updateChoice = input.next().charAt(0);
                            if (updateChoice == 'y') {
                                System.out.println("The customer name for this order is " + retrievedOrder.getCustomerName() + ".");
                                System.out.println("Please update the customer name. Alternatively, press Enter to skip to the next details.");
                                input.nextLine();
                                String updateName = input.nextLine();
                                if (updateName.isEmpty()) {
                                    retrievedOrder.setCustomerName(retrievedOrder.getCustomerName());
                                } else {
                                    retrievedOrder.setCustomerName(updateName);
                                }

                                //UPDATE STATE DTO
                                //display current state
                                System.out.println("The state set for this order is " + retrievedOrder.getTaxDetails().getStateAbbreviation() + ".");
                                //display available choices to the user
                                System.out.println("Please update the state, choosing one of the following options: " + keys + ". Please bear in mind that options are case sensitive. \nAlternatively, press Enter to skip to the next details.");
                                String updatedState = input.nextLine();
                                if (updatedState.isEmpty()) {
                                    retrievedOrder.setTaxDetails(retrievedOrder.getTaxDetails());
                                }
                                //if user chooses a different state , set the state as new value in TaxDTO
                                //and then set new TaxDTO as the new taxDetails object within OrderDto object
                                else {
                                    retrievedOrder.getTaxDetails().setStateAbbreviation(updatedState);
                                    taxDetails = new TaxDto(updatedState, taxInfoCollection.get(updatedState));
                                    retrievedOrder.setTaxDetails(retrievedOrder.getTaxDetails());
                                }

                                //UPDATE PRODUCT DTO
                                //display current product
                                System.out.println("The current product set for this order is " + retrievedOrder.getProductDetails().getProductType() + ".");
                                //display the available choices to the user
                                System.out.println("Please update the product type, choosing one of the following options: " + productKeys + " Please bear in mind that options are case sensitive. \nAlternatively, press Enter to skip to the next details.");
                                String updatedProduct = input.nextLine();
                                //if empty, keep current details
                                if (updatedProduct.isEmpty()) {
                                    retrievedOrder.setProductDetails(retrievedOrder.getProductDetails());
                                }
                                //if user input is a valid choice
                                else {
                                    retrievedOrder.getProductDetails().setProductType(updatedProduct);
                                    //build the new ProductDTO corresponding to the user choice (which is the key tp productDTo collection)
                                    //so retrieve the map values corresponding the key (user input) , and create a new ProductDto
                                    productDetails = new ProductDto(updatedProduct, productInfoMap.get(updatedProduct).getCostPerSquareFoot(), productInfoMap.get(updatedProduct).getLaborCostPerSquareFoot());

                                    //set the new ProductDto as the new productDetails object composing Order Dto
                                    retrievedOrder.setProductDetails(retrievedOrder.getProductDetails());
                                }

                                //AREA
                                System.out.println("The area set for this order is " + retrievedOrder.getArea() + ".");
                                System.out.println("Please update the area (100 minimum). Alternatively, press Enter to skip to the next details.");
                                String updatedAreaString = input.nextLine();
                                if (updatedAreaString.isEmpty()) {
                                    retrievedOrder.setArea(retrievedOrder.getArea());
                                } else {
                                    Integer updatedAreaInt = Integer.parseInt(updatedAreaString);
                                    if (updatedAreaInt < 100) {
                                        System.out.println("Sorry, area requirements have not been met (minimum 100).");
                                        System.out.println("Redirecting you back to main menu....");
                                        runMenu();
                                    }
                                    BigDecimal updatedArea = new BigDecimal(updatedAreaString);
                                    retrievedOrder.setArea(updatedArea);
                                }

                                //Recalculate and set the other values as required, passing the new values of variables and/or objects
                                materialCost = retrievedOrder.getArea().multiply(retrievedOrder.getProductDetails().getCostPerSquareFoot());
                                retrievedOrder.setMaterialCost(materialCost);

                                laborCost = retrievedOrder.getArea().multiply(retrievedOrder.getProductDetails().getLaborCostPerSquareFoot());
                                retrievedOrder.setLaborCost(laborCost);

                                tax = (materialCost.add(laborCost)).multiply(retrievedOrder.getTaxDetails().getTaxRate().divide(BigDecimal.valueOf(100)));
                                retrievedOrder.setTax(tax);

                                total = materialCost.add(laborCost).add(tax);
                                retrievedOrder.setTotal(total);

                                //form the new OrderDto
                                retrievedOrder = new OrderDto(retrievedOrder.getOrderNumber(), retrievedOrder.getCustomerName(), retrievedOrder.getTaxDetails(), retrievedOrder.getProductDetails(), retrievedOrder.getArea(), retrievedOrder.getMaterialCost(), retrievedOrder.getLaborCost(), retrievedOrder.getTax(), retrievedOrder.getTotal());

                                retrievedOrder.setOrderDate(retrievedOrder.getOrderDate());

                                // display the new order to user:
                                System.out.println("Here are the edited order details: ");
                                System.out.println("Customer name: " + retrievedOrder.getCustomerName()
                                        + "\nState: " + retrievedOrder.getTaxDetails().getStateAbbreviation()
                                        + "\nTax rate: " + retrievedOrder.getTaxDetails().getTaxRate()
                                        + "\nProduct type: " + retrievedOrder.getProductDetails().getProductType() + "\nArea: " + retrievedOrder.getArea()
                                        + "\nCost per square foot: " + retrievedOrder.getProductDetails().getCostPerSquareFoot()
                                        + "\nLabor cost per square foot:" + retrievedOrder.getProductDetails().getLaborCostPerSquareFoot()
                                        + "\nMaterial cost: " + retrievedOrder.getMaterialCost() + "\nLabor cost: " + retrievedOrder.getLaborCost()
                                        + "\nTax: " + retrievedOrder.getTax() + "\nTotal: " + retrievedOrder.getTotal()
                                        + "\n--------------------------------------------");

                                // prompt user to confirm the updated Order
                                System.out.println("Do you wish to update the order to contain these details? y/n");
                                char updateOrderChoice = input.next().charAt(0);

                                //if yes, call the updateOrder() , passing the edited retrievedOrder as parameter
                                if (updateOrderChoice == 'y') {
                                    OrderDto updatedORder = orderService.updateOrder(retrievedOrder);
                                    System.out.println("The order has been updated successfully and saved to file.\nRedirecting back to the main menu...");
                                    //clear the collection before returning to main menu
                                    collectionToClear = orderService.readOrdersFile(orderByDate, false);
                                    collectionToClear.clear();
                                    runMenu();
                                } else { //if user does not confirm, display cancellation message and confirm whether they wish to continue or exit the program
                                    System.out.println("Update order operation cancelled. No details have been saved.");
                                    //clear collection
                                    collectionToClear = orderService.readOrdersFile(orderByDate, false);
                                    collectionToClear.clear();
                                    break;
                                }
                            }
                            // if they choose not to update the displayed order, simply clear the collection break;
                            else {
                                collectionToClear = orderService.readOrdersFile(orderByDate, false);
                                collectionToClear.clear();
                                break;
                            }
                        }
                    case 4: //similarly 2 steps: 1. retrieve order; 2. remove order(upon confirmation)
                        System.out.println("Remove an order:");
                        // first, prompt the user for the order date and order number
                        System.out.println("Please insert the date of the order: (YYYY-MM-DD)");
                        input.nextLine();
                        String d = input.nextLine();
                        LocalDate removeOrderByDate = LocalDate.parse(d);
                        System.out.println("Please insert the order number:");
                        int removeOrderbyNumber = input.nextInt();
                        OrderDto removeOrder = orderService.retrieveOrder(removeOrderByDate, removeOrderbyNumber);

                        //first, check if the order exists
                        if (removeOrder == null) {
                            System.out.println("Sorry, there is no order with the provided details");
                            break;
                        }
                        //if order is found, display the details to the user
                        else {
                            System.out.println("Order found. Here are the details associated with this order: " + removeOrder.toString());
                            System.out.println("Confirm deleting this order? y/n");
                            char deleteOption = input.next().charAt(0);
                            if (deleteOption == 'y') {
                                try { // exception handling - if there is only 1 order on file, it cannot writeToFile after this would be removed
                                    orderService.removeOrder(removeOrderByDate, removeOrderbyNumber);
                                    System.out.println("Order deleted.");
                                } catch (Exception e) {
                                    System.out.println("This is the only record on this date and as such it cannot be deleted. (no empty files allowed!)" +
                                            "\nAll files with containing a single order record need to be deleted by an administrator."
                                            + "\n--------------------------------------------");
                                }
                            } else {
                                //if the user does not confirm
                                System.out.println("Order has not been deleted.");
                            }
                            //clear collection (in case te user chooses to continue)
                            collectionToClear = orderService.readOrdersFile(removeOrderByDate, false);
                            collectionToClear.clear();
                            break;
                        }
                    case 5:
                        System.out.println("All data is saved to file. This was done in real-time as you were performing the CRUD operations.\n");
                        break;
                    case 6:
                        System.out.println("Quit");
                        System.out.println("Are you sure you wish to quit? Press y to confirm.");
                        char quitOption = input.next().charAt(0);
                        if (quitOption == 'y') {
                            System.exit(0);
                        } else {
                            System.out.println("Ok. Redirecting you to main menu...");
                            runMenu();
                        }
                    default:
                        System.out.println("Invalid option. Please choose a value between 1 - 6, according to the menu.");
                        runMenu();
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid option. Program needs to be restarted!");
                System.exit(1);
            } catch (IOException e) {
                System.out.println("Sorry, something went wrong when trying to find the order. Please try again.");
            }
            System.out.println("Do you want to continue? (It will redirect you to the main menu) y/n");
            continueOption = input.next().charAt(0);
        }
        while (continueOption == 'y');
    }
}