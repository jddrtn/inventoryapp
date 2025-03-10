### **Overview**
The Inventory Management System is a Java-based application that utilizes Swing for the GUI and an SQL database for storing inventory data. The system is designed to manage products in an inventory, allowing users to perform various actions such as adding, updating, deleting, and searching for items. This project was developed as part of a university assignment to demonstrate skills in Java programming.

### **Features**
**Login:** Enter username and password to access the system.
**Add Inventory Item:** Allows the user to add a new item to the inventory.
**Update Inventory Item:** Enables users to modify details of an existing inventory item.
**Delete Inventory Item:** Allows the removal of items from the inventory.
**Search Inventory:** Users can search for inventory items by name or ID.
**Display Inventory:** Shows a list of all inventory items currently stored in the database.
**SQL Integration:** All inventory data is stored and managed in a SQL database for persistent storage.
**User-friendly GUI:** Simple and intuitive interface built using Java Swing.

### **Technologies Used**
**Java:** The main programming language for developing the application.
**Swing:** Used for designing the graphical user interface (GUI).
**SQL Database:** Stores inventory data, such as product names, IDs, quantities, and prices.
**JDBC (Java Database Connectivity):** Used to connect and interact with the SQL database.

### **How to Install**
1. First, open the **Inventory Management System** folder in IntelliJ IDE to access the source code.
   
2. In IntelliJ, go to File > Project Structure. In the Libraries tab under Project Settings, click the + button to add a new Java Library. Add the sql connector JAR file. Press Apply and then OK.
   
3. Open **XAMPP Control Panel** (or any program on your system to run SQL).
   
4. Start the Apache and MySQL servers. Click Admin next to MySQL to open the database in browser.

---- TBC ----

### **How to Use**
After launching the program, you will be asked to enter a username and password. Enter **admin** in the username box and **password** in the password box (all lowercase).
Once the application is running:

**Add Inventory Item:**
Fill in the item details (name, category, quantity, price) and click "Add Item."
**Edit Inventory Item:**
Select an existing item from the inventory, edit the details, and click "Update Item."
**Delete Inventory Item:**
Select an item from the list and click "Delete Item."
**Search Inventory:**
Enter an item name or ID in the search bar and click "Search."
**Display Inventory:**
Display all the inventory records from the database.
