# Bank Management System

A console-based banking application built in Java. This project supports dual user modes (Employee and Customer) with flat-file persistence (`txt`) for managing accounts, balances, and transactions.

## Features

- **Employee Mode:**
  - View, add, update, and delete customer records.
  - Search for specific customers by ID.
  - View individual customer transaction logs.
- **Customer Mode:**
  - View account details and check current balances.
  - Perform deposit and withdrawal operations.
  - View personal transaction history.
- **Data Persistence:** Uses text files inside the `data` folder (`Customers.txt`, `EmployeeAcc.txt`, `Transactions.txt`) to save and retrieve system data.

## Project Structure

- `App.java`: Main entry point of the application.
- `Bank.java`: Interface defining core banking behaviors.
- `Control.java`: Manages application control flow, menus, and user actions.
- `Customer.java`: Implements customer-specific banking actions and attributes.
- `Employee.java`: Implements employee attributes and access management.
- `File.java`: Contains file path constants and field separators.
- `FileOepration.java`: Handles all file reading, writing, and data updating operations.
- `Login.java`: Handles authentication for both employees and customers.
- `ScreenAndGenricMethod.java`: Manages console screen resets, menus, and input formatting.

## How to Run

1. Ensure you have the Java Development Kit (JDK) installed on your system.
2. Clone this repository or download the source code.
3. Open a terminal or command prompt inside the project directory.
4. Compile all Java files:
   ```bash
   javac src/*.java
