# Java Essentials: Flooring Mastery

The goal of this project is to create an application that can read and write flooring orders for Wiley Edge Corp:

**Tech Specs:**

- The program was planned using flowcharts to describe the individual steps the program must complete, including any loops and conditional outputs.
- Incorporates solid OOP principles and design: code is organised into well-named classes and logically distributed into the appropriate packages. It incorporates interfaces along with their implementation. DTO/POJO objects are used, as well as skilful Object Composition.
- Layered architecture, ensuring the separation of concerns. Comprised of model, DAO, view, and service packages.
- Spring Framework, effectively using Spring Dependency Injection.
- Uses Spring JDBC to manage data stored in files.#
- File I/O Operations: The application is able to perform I/O operations to a file to store and retrieve item information.
- Ensures real-time updates to data on file within the application runtime.
- Unit testing is performed at both the DAO and Service layers to validate the functionality and reliability of the code.
- User input validation and exception handling have been implemented thoroughly.




**Scenario and Requirements:**

Orders Files & Format

A new order file is created for each sales day. The file name is guaranteed to be unique for that day because the date is part of the file name. With this format, the order file for August 21st, 2025 would be named Orders_08212025.txt.

Data rows will contain the following fields and should be in this order:
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
Here is a sample line from an orders file:
1,Ada Lovelace,CA,25.00,Tile,249.00,3.50,4.15,871.50,1033.35,476.21,2381.06

Some of the order fields are calculations:
MaterialCost = (Area * CostPerSquareFoot)
LaborCost = (Area * LaborCostPerSquareFoot)
Tax = (MaterialCost + LaborCost) * (TaxRate/100)
Tax rates are stored as whole numbers
Total = (MaterialCost + LaborCost + Tax)


Tax File & Format

The tax information can be found in Data/Taxes.txt. This file will contain the following fields:
StateAbbreviation – String
StateName – String
TaxRate – BigDecimal
Here is a sample line from the taxes file:
TX,Texas,4.45

  
Products File & Format

The current product information can be found in Data/Products.txt. It contains the following fields:
ProductType – String
CostPerSquareFoot – BigDecimal
LaborCostPerSquareFoot – BigDecimal
Here is a sample line from the products file:
Tile,3.50,4.15

  
User Stories

The UI should start with a menu to prompt the user for what they would like to do:
  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
  * <<Flooring Program>>
  * 1. Display Orders
  * 2. Add an Order
  * 3. Edit an Order
  * 4. Remove an Order
  * 5. Export All Data
  * 6. Quit
  *
  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
  
Display Orders

Display orders will ask the user for a date and then display the orders for that date. If no orders exist for that date, it will display an error message and return the user to the main menu.

Add an Order
To add an order will query the user for each piece of order data necessary:
Order Date – Must be in the future
Customer Name – May not be blank and is limited to characters [a-z][0-9] as well as periods and comma characters. "Acme, Inc." is a valid name.
State – Entered states must be checked against the tax file. If the state does not exist in the tax file, we cannot sell there. If the tax file is modified to include the state, it should be allowed without changing the application code.
Product Type – Show a list of available products and pricing information to choose from. Again, if a product is added to the file it should show up in the application without a code change.
Area – The area must be a positive decimal. Minimum order size is 100 sq ft.
The remaining fields are calculated from the user entry and the tax/product information in the files. Show a summary of the order once the calculations are completed and prompt the user as to whether they want to place the order (Y/N). If yes, the data will be added to in-memory storage. If no, simply return to the main menu.
The system should generate an order number for the user based on the next available order # (so if there are two orders and the max order number is 4, the next order number should be 5).

Edit an Order
Edit will ask the user for a date and order number. If the order exists for that date, it will ask the user for each piece of order data but display the existing data. If the user enters something new, it will replace that data; if the user hits Enter without entering data, it will leave the existing data in place. For example:
Enter customer name (Ada Lovelace):
  
If the user enters a new name, the name will replace Ada Lovelace. If the user hits enter without entering any data, it will leave the data as-is.
Only certain data is allowed to be changed:
CustomerName
State
ProductType
Area
If the state, product type, or area are changed, the order will need to be recalculated. Order date may not be changed!
After querying for each editable field display a summary of the new order information and prompt for whether the edit should be saved. If yes, replace the data in the file then return to the main menu. If no, do not save and return to the main menu.

Remove an Order
For removing an order, the system should ask for the date and order number. If it exists, the system should display the order information and prompt the user if they are sure. If yes, it should be removed from the list.

Quit
On quit, exit the application.
