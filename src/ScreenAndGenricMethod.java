
import java.util.Scanner;

public class ScreenAndGenricMethod 
{
    static char accountType = ' ';
    static Scanner SC = new Scanner(System.in);
    
    
    public static String ReadStringFromUser(String Message)
    {
        System.out.print(Message + " ");
        return SC.next();
    }
    
    static int numberOfTries = 0;

   
   
    static void printMenu() {
        if(accountType == 'C')
        {
                    System.out.println("""
                ------------------------------------------------------------------------
                ------------------------------------------------------------------------
                """);
        System.out.println("[1] Show Data");
        System.out.println("[2] Show Balance");
        System.out.println("[3] Deposite");
        System.out.println("[4] Withdraw");
        System.out.println("[5] Transactions");
        System.out.println("[6] Switch to Employee Mode");
        System.out.println("[7] Logout");
        System.out.println("""
                ------------------------------------------------------------------------
                ------------------------------------------------------------------------

                """);
        System.out.print("Choose what do you want to do? [1 to 6]? ");
        }
        else
        {
        
        System.out.println("""
                ------------------------------------------------------------------------
                ------------------------------------------------------------------------
                """);
        System.out.println("[1] Show Client List");
        System.out.println("[2] Add New Client");
        System.out.println("[3] Delete Client");
        System.out.println("[4] Update Client Information");
        System.out.println("[5] Find Client");
        System.out.println("[6] Transactions");
        System.out.println("[7] Manage Users");
        System.out.println("[8] Switch to Customer Mode");
        System.out.println("[9] Logout");
        System.out.println("""
                ------------------------------------------------------------------------
                ------------------------------------------------------------------------

                """);
        
        System.out.print("Choose what do you want to do? [1 to 9]? ");
    }}
    
     static void resetScreen() 
     {
        for(int i = 0; i < 3; i ++)
            System.out.println("\n");
       
    }

    
      static void PrintCustomerHeader()
    {
        System.out.println("-----------------------------------------------------------");
        System.out.println("Name        ||   ID      ||     balance       ||     account type ||   jop   ");
    }
      
      static void PrintCustomerFile(String []values)
    {
            for (String value : values) 
            {
              System.out.print("[" + value.trim() + "]          "); 
            }
                System.out.println();
    }
          static void printSingleRecord(String ID ,String []Value)
    {
        System.out.println("Customer with ID: " +  ID    + " was found! ");
        System.out.println("Name: " + Value[0]  +"\nID: " +  Value[1]  +"\nbalance: " + Value[2] + "\naccount type: " +  Value[3] + "\njop: " +Value[4]);
    }
    
    static void printSingleRecord(Customer C)
    {
      System.out.print("--------------------------------------------");
      System.out.println("\nName: " + C.getName()  +"\nID: " +  C.getId()  +"\nbalance: " +
      C.getBalance() + "\naccount type: " +  C.getAccountType() + "\njop: " + C.getJop());
    }
    
     static void printTotalRecord(int total)
    {
        if(total > 0)
        {
            System.out.println("-----------------------------------------");
            System.out.println("\t\tTotal of Record (" + total +")" );
        }
        else
        {
            System.out.println("\tNo Record was found!!");
        }
        
    }
    
    static void PrintHeadr(String Message)
    {
        System.out.println("------------------------------------------------------------");
        System.out.println("\t\t" +Message);
        System.out.println("------------------------------------------------------------");
    }
}
