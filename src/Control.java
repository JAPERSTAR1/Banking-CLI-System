import java.util.ArrayList;
import java.util.Scanner;

public class Control {
    static Customer CurrentCustomer = null;
    static Scanner SC = new Scanner(System.in);

    static Customer _FindCustomerFromArrayList(ArrayList<Customer> Customers, String ID) {
        for (Customer C : Customers) {
            if (C.getId().equalsIgnoreCase(ID)) {
                return C;
            }

        }
        return null;
    }

    static void _UpdateInfo(Customer C) {
        ScreenAndGenricMethod.printSingleRecord(C);
        PrepareCustomerForUpdate(C);

    }

    static void _UpdateInfo() {
        FileOepration.PrintSingleCustomer(Login.filePass, false);
        CurrentCustomer = new Customer(FileOepration.Record[0], FileOepration.Record[1],
                Double.parseDouble(FileOepration.Record[2]), FileOepration.Record[3], FileOepration.Record[4]);
    }

    static void askToContinue() {
        int choice;
        if (ScreenAndGenricMethod.accountType == 'E') {
            do {
                ScreenAndGenricMethod.resetScreen();
                System.out.println("""
                        1. To go Back to the main menu
                        2. To Exit
                        Do you want to perform another operation? (1/2):
                        """);

                choice = SC.nextInt();
                SC.nextLine(); // consume newline
                if (choice != 1 && choice != 2) {

                    ScreenAndGenricMethod.numberOfTries++;

                    if (ScreenAndGenricMethod.numberOfTries == 3) {
                        System.out.println("You have entered an invalid option 3 times. Exiting...");
                        System.exit(0);
                    }
                    System.out.println(
                            "You have entered an invalid option " + ScreenAndGenricMethod.numberOfTries + " times.");
                }
                ScreenAndGenricMethod.numberOfTries++;
            } while (choice != 1 && choice != 2);
            ScreenAndGenricMethod.numberOfTries = 0;
            if (choice == 1) {
                Checker();
            } else {
                System.exit(0);
            }
        } else {
            do {
                ScreenAndGenricMethod.resetScreen();
                System.out.println("""
                        1. To go Back to the main menu
                        2. To Exit
                        Do you want to perform another operation? (1/2):
                        """);

                choice = SC.nextInt();
                SC.nextLine(); // consume newline
                if (choice != 1 && choice != 2) {

                    ScreenAndGenricMethod.numberOfTries++;

                    if (ScreenAndGenricMethod.numberOfTries == 3) {
                        System.out.println("You have entered an invalid option 3 times. Exiting...");
                        System.exit(0);
                    }
                    System.out.println(
                            "You have entered an invalid option " + ScreenAndGenricMethod.numberOfTries + " times.");
                }
                ScreenAndGenricMethod.numberOfTries++;
            } while (choice != 1 && choice != 2);
            ScreenAndGenricMethod.numberOfTries = 0;
            if (choice == 1) {
                Checker();
            } else {
                System.exit(0);
            }
        }
    }

    static void Checker() {

        int option = 0;
        if (ScreenAndGenricMethod.accountType == 'E') {
            do {
                ScreenAndGenricMethod.resetScreen();
                ScreenAndGenricMethod.printMenu();
                option = SC.nextInt();
                SC.nextLine();
                if (option < 1 || option > 8) {

                    ScreenAndGenricMethod.numberOfTries++;

                    if (ScreenAndGenricMethod.numberOfTries == 3) {
                        System.out.println("You have entered an invalid option 3 times. Exiting...");
                        System.exit(0);
                    }
                    System.out.println(
                            "You have entered an invalid option " + ScreenAndGenricMethod.numberOfTries + " times.");
                }
            } while (option < 1 || option > 9);
        } else {
            do {
                ScreenAndGenricMethod.resetScreen();
                ScreenAndGenricMethod.printMenu();
                option = SC.nextInt();
                SC.nextLine();
                if (option < 1 || option > 7) {

                    ScreenAndGenricMethod.numberOfTries++;

                    if (ScreenAndGenricMethod.numberOfTries == 3) {
                        System.out.println("You have entered an invalid option 3 times. Exiting...");
                        System.exit(0);
                    }
                    System.out.println(
                            "You have entered an invalid option " + ScreenAndGenricMethod.numberOfTries + " times.");
                }
            } while (option < 1 || option > 6);
        }
        chooseOption(option);

    }

    static String CheckID() {
        System.out.print("Pleas Enter a vaild ID: ");
        String ID = SC.nextLine().trim();
        while (!FileOepration.DoesCustomerExists(ID)) {
            System.out.println("Invaild Id, please enter again: ");
            ID = SC.nextLine().trim();
        }
        return ID;
    }

    static Bank askForAccess() {
        int choice;
        do {
            ScreenAndGenricMethod.resetScreen();
            System.out.println("""
                    1. Employee
                    2. Customer
                    Please choose an option (1/2):
                    """);
            choice = SC.nextInt();
            SC.nextLine(); // consume newline
            if (choice != 1 && choice != 2) {

                ScreenAndGenricMethod.numberOfTries++;

                if (ScreenAndGenricMethod.numberOfTries == 3) {
                    System.out.println("You have entered an invalid option 3 times. Exiting...");
                    System.exit(0);
                }
                System.out.println(
                        "You have entered an invalid option " + ScreenAndGenricMethod.numberOfTries + " times.");
            }
        } while (choice != 1 && choice != 2);
        if (choice == 1) {
            ScreenAndGenricMethod.accountType = 'E';
            return new Employee();
        } else {
            ScreenAndGenricMethod.accountType = 'C';
            return new Customer();
        }

    }

    static void chooseOption(int option) {
        char a = ScreenAndGenricMethod.accountType;
        switch (option) {
            case 1 -> {
                if (ScreenAndGenricMethod.accountType == 'E') {// Employee Show Customers List
                    ScreenAndGenricMethod.PrintHeadr("\tShow Customers List");
                    ShowCustomers();
                } else {// Customer Show My Details
                    FileOepration.PrintSingleCustomer(Login.filePass, true);
                    CurrentCustomer.showDetails();
                }
            }

            case 2 -> {
                if (ScreenAndGenricMethod.accountType == 'E') {// Employee Add New Customer
                    ScreenAndGenricMethod.PrintHeadr("\tAdd New Customer");
                    AddNewCustomer();
                } else {// Customer Show Balance
                    CurrentCustomer.showBalance();

                }
            }

            case 3 -> {
                if (ScreenAndGenricMethod.accountType == 'E') {// Employee Delete Customer
                    ScreenAndGenricMethod.PrintHeadr("\tDelete Customer");
                    DeleteCustomer();
                } else {// Customer Deposite
                    CurrentCustomer.deposite();

                }
            }

            case 4 -> {
                if (ScreenAndGenricMethod.accountType == 'E') {// Employee Update Customer Info
                    ScreenAndGenricMethod.PrintHeadr("\tUpdate Customer");
                    UpdateCustomer();
                } else {// Customer Withdraw
                    CurrentCustomer.withdraw();

                }
            }

            case 5 -> {
                if (ScreenAndGenricMethod.accountType == 'E') {// Employee Find Customer
                    ScreenAndGenricMethod.PrintHeadr("\tFind Customer By ID");
                    FindSingleCustomer();

                } else {// Customer Transactions
                    CurrentCustomer.showtransactions();
                }
            }

            case 6 -> {
                if (ScreenAndGenricMethod.accountType == 'E') {// Employee Transactions
                    System.out.print("Enter Customer ID: ");
                    String id = SC.nextLine().trim();
                    FileOepration.PrintTransactions(id);
                } else {// Customer Switch to Employee Mode
                    ScreenAndGenricMethod.accountType = 'E';
                }
            }

            case 7 -> {// Employee Manage Users
                if (ScreenAndGenricMethod.accountType == 'E') {
                   
                } else {// Customer Logout
                    System.exit(0);
                }
            }

            case 8 -> {// Employee Switch to Customer Mode
                ScreenAndGenricMethod.accountType = 'C';
            }
            case 9 -> {// Employee Logout
                System.exit(0);
            }
        }
        char b = ScreenAndGenricMethod.accountType;
        if (a == b) {
            ScreenAndGenricMethod.numberOfTries = 0;
            askToContinue();
        } else {
            if (Login.login()) {
                Control.Checker();

            }
        }
    }

    static Customer PrepareNewCustomer() {
        System.out.println("Enter the name please: ");
        String Name = SC.nextLine();

        String ID = CheckID(); // this method designed to return a vaild ID, ID must be unique for each record

        System.out.println("Enter the Balance: ");
        double Balance = Double.parseDouble(SC.nextLine()); // due nextDouble int issues we read as a full line and
                                                            // parse it to double

        System.out.println("choose what type of account? ");
        String accountType = SC.nextLine().trim();

        System.out.println("Enter the job ");
        String jop = SC.nextLine().trim();

        return new Customer(Name, ID, Balance, accountType, jop);
    }

    static void PrepareCustomerForUpdate(Customer C) {
        System.out.println("Enter the new name please: ");
        C.setName(SC.nextLine().trim());

        System.out.println("enter the new type of account? ");
        C.setAccountType(SC.next());

        System.out.println("Enter the new job after update");
        C.setJop(SC.next());
    }

    static boolean ConfirmForDelete(Customer C) {
        ScreenAndGenricMethod.printSingleRecord(C);

        System.out.println("Are You Sure you want Delete this Client? Y/N");
        if (SC.next().equalsIgnoreCase("y")) {
            System.out.println("Deleting is confirmed");
            return true;
        } else {
            System.out.println("Deleting canceld for inserting no or invalid input");
            return false;
        }
    }

    static void DeleteCustomer() {
        String ID = ScreenAndGenricMethod.ReadStringFromUser("Please Enter an Existing ID for deleting");
        ArrayList<Customer> Customers = FileOepration.MarkCustomerForDelete(ID);

        if (Customers == null) {
            System.out.println(
                    "Failed to delete Customers with " + ID + " , please make sure confirming and enter a  vaild ID. ");
            return;
        } else {
            FileOepration.saveCustomersToFile(Customers);
            System.out.println("Successfuly Deleted!");
        }
    }

    static void UpdateCustomer() {
        String ID = ScreenAndGenricMethod.ReadStringFromUser("Please Enter a valid ID to update Info");
        ArrayList<Customer> Customers = FileOepration.UploadCustomersForUpdate(ID);

        if (Customers == null) {
            System.out.println("Invalid ID " + ID + " try agaim");
        } else {
            _UpdateInfo(_FindCustomerFromArrayList(Customers, ID));
            FileOepration.saveCustomersToFile(Customers);
        }
    }

    static void ShowCustomers() {
        FileOepration.PrintAllCustomersFile();
    }

    static void AddNewCustomer() {
        FileOepration.addNewCustomer(PrepareNewCustomer());
    }

    static void FindSingleCustomer() {
        FileOepration.PrintSingleCustomer(CheckID(), true);
    }
}
