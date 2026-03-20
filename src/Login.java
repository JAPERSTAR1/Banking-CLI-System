import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Login {
static String fileUser,filePass;
static String[] parts;
    static boolean login() {

        ScreenAndGenricMethod.PrintHeadr("Group 7 Bank");


        int count = 0;
        boolean log = false;
        if(ScreenAndGenricMethod.accountType == 'E'){
                    System.out.println("""

        -------------------------------------------------------------------------
                                Welcome to the Employee Banking System
                                Please enter your credentials to log in.
        -------------------------------------------------------------------------
                """);
        }
        else{
                    System.out.println("""

        -------------------------------------------------------------------------
                   Welcome to the Customer Banking System
                   Please enter your credentials to log in.
        -------------------------------------------------------------------------
                """);
        }
        while (count != 3 && !log) {

            if (ScreenAndGenricMethod.accountType == 'E') { // Employee login

                if (findUser(ScreenAndGenricMethod.ReadStringFromUser("Enter the username: "),
                        ScreenAndGenricMethod.ReadStringFromUser("Enter the password: "))) {
                    log = true;

                    return log;
                }
            }

            else {// Customer login

                if (findUser(ScreenAndGenricMethod.ReadStringFromUser("Enter the name: "),
                        ScreenAndGenricMethod.ReadStringFromUser("Enter the ID: "))) {
                    log = true;

                    return log;
                }
            }

            count++;
            ScreenAndGenricMethod.resetScreen();
            System.out.println("Username or password is wrong, try again \n");
            
            if (count == 3) {
                System.out.println("You have exceeded the maximum number of login attempts.");
            }

        }

        return log;
    }

    static boolean findUser(String a, String b) {

        if (ScreenAndGenricMethod.accountType == 'E') { // Employee Find user
            try (BufferedReader br = new BufferedReader(new FileReader(File.EmployeeFile))) {

                String line;
                while ((line = br.readLine()) != null) {

                    if (line.trim().isEmpty()) {
                        continue;
                    }
                    // splitting every line by comma to get username and password
                    parts = line.split("##");

                    fileUser = parts[0].trim();
                    filePass = parts[1].trim();

                    // checking if the entered username and password match the ones in the file
                    if (a.equalsIgnoreCase(fileUser) && b.equals(filePass)) {
                        return true;
                    }
                }

            } catch (IOException e) {
                System.out.println("Error reading EmployeeAcc.txt file!");
            }
        } else {// Customer Find user
            try (BufferedReader br = new BufferedReader(new FileReader(File.CustomerFile))) {

                String line;
                while ((line = br.readLine()) != null) {

                    parts = line.split("##");

                    fileUser = parts[0].trim();
                    filePass = parts[1].trim();

                    if (a.equalsIgnoreCase(fileUser) && b.equals(filePass)) {
                        Control._UpdateInfo();
                        return true;
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading Customers.txt file!");
            }
        }

        return false;
    }
}
