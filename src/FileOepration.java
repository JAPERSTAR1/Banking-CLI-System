import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;

public class FileOepration extends File {
    static String []Record;
        private static String _PrepareLine(Customer C)
        {
            return (C.getName() + separator + C.getId() + separator + C.getBalance() + separator  +C.getAccountType() + separator + C.getJop());
        }
 
        private static Customer _ConvertLineToObject(String[] Record)
        {  
           return new Customer(Record[0], Record[1], Double.parseDouble(Record[2]),  Record[3], Record[4]);
        }
   
        public static void PrintAllCustomersFile()
        {
                
            try (BufferedReader br = new BufferedReader(new FileReader(CustomerFile))) {
            String line;
            int lineNumber = 0;
            
            ScreenAndGenricMethod.PrintCustomerHeader();
            while ((line = br.readLine()) != null) {
                lineNumber++;
                
                ScreenAndGenricMethod.PrintCustomerFile(line.split(separator ));
                
            }
            
            ScreenAndGenricMethod.printTotalRecord(lineNumber);
            
            
             } catch (Exception e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
              }
            
            
            
            
            
            
        }
        
        public static boolean PrintSingleCustomer(String TargetID,Boolean withMessage)
        {
            try (BufferedReader br = new BufferedReader(new FileReader(CustomerFile))) {
            String line;
          
            while ((line = br.readLine()) != null) 
            {
                Record = line.split(separator);
           
                if(TargetID.equalsIgnoreCase(Record[1]))
                {
                    if(withMessage){
                ScreenAndGenricMethod.printSingleRecord(TargetID,Record);
                    }
                return  true;
                }
            }
            
          
        } catch (Exception e) {
         
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
            return false;   
        }
        
        public  static  void addNewCustomer(Customer newCustomer)
        {
            try (FileWriter fw = new FileWriter(CustomerFile, true)) {
           
            
            fw.write(_PrepareLine(newCustomer )+ "\n"); //new line "every record is in new line "
            
            System.out.println("Successfully appended data to " + CustomerFile);
            
        } catch (Exception e)
        {
            System.out.println("An error occurred while adding new data to the file: " + e.getMessage());
        }
        }
       
        
        public static boolean DoesCustomerExists(String TargetID)
        {
            try (BufferedReader br = new BufferedReader(new FileReader(CustomerFile))) {
            String line;
            
            while ((line = br.readLine()) != null) 
            {
               String []Record = line.split(separator);
               String  ID = Record[1];
               
               if(TargetID.equalsIgnoreCase(ID))
               return true;   
            }
                 
        } catch (Exception e) {
         
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
            return false; 
            
            
        }
        
        public static ArrayList<Customer> MarkCustomerForDelete(String TargetID)
        {
            ArrayList<Customer> Customers = new ArrayList<Customer>();
            boolean isFound = false;
            
            try (BufferedReader br = new BufferedReader(new FileReader(CustomerFile))) {
            String line;
            
            while ((line = br.readLine()) != null) 
            {
               Customer CurrentCustomer =_ConvertLineToObject(line.split(separator));
               
               if(TargetID.equalsIgnoreCase(CurrentCustomer.getId()))
               {
                 isFound = true;
                 if(Control.ConfirmForDelete(CurrentCustomer))
                 {
                     continue;
                 }
                 else
                 {
                     return null;
                 }
                 
               }
               else
               {
                   Customers.add(CurrentCustomer);
               }
            }
                 
        } catch (Exception e)
        {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
            return isFound ?  Customers : null; 
            
        }
       
        public static void saveCustomersToFile(ArrayList<Customer> customers)
        {
     
        try (FileWriter fw = new FileWriter(CustomerFile, false)) {

        for (Customer c : customers)
        {
            fw.write(_PrepareLine(c));
            fw.write(System.lineSeparator());
        }

     

       } catch (IOException e)
         {
             System.out.println("An error occurred while adding new data to the file:" + e.getMessage());
        } 
         
        }
        
      public  static ArrayList<Customer> UploadCustomersForUpdate(String TargetID)
      {
          
            ArrayList<Customer> Customers = new ArrayList<Customer>();
            boolean isFound = false;
            
            try (BufferedReader br = new BufferedReader(new FileReader(CustomerFile))) {
            String line;
            
            while ((line = br.readLine()) != null) 
            {
               Customer CurrentCustomer =_ConvertLineToObject(line.split(separator));
               
                Customers.add(CurrentCustomer);
                
               if(TargetID.equalsIgnoreCase(CurrentCustomer.getId()))
                 isFound = true;                
               
              
            }
                 
        } catch (Exception e)
        {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
            return isFound ?  Customers : null; 
          

      }
      public static void withdrawFromCustomerBalance(String TargetID, double amount)
      {
          ArrayList<Customer> Customers = UploadCustomersForUpdate(TargetID);
          
          if(Customers != null)
          {
              for(Customer C : Customers)
              {
                  if(C.getId().equalsIgnoreCase(TargetID))
                  {
                      C.setBalance(C.getBalance() - amount);
                      break;
                  }
              }
              
              saveCustomersToFile(Customers);
              addTransaction(TargetID, "Deposit", amount);
          }
          else
          {
              System.out.println("Customer with ID: " + TargetID + " was not found!");
          }
      }
            public static void depositeFromCustomerBalance(String TargetID, double amount)
      {
          ArrayList<Customer> Customers = UploadCustomersForUpdate(TargetID);
          
          if(Customers != null)
          {
              for(Customer C : Customers)
              {
                  if(C.getId().equalsIgnoreCase(TargetID))
                  {
                      C.setBalance(C.getBalance() + amount);
                      break;
                  }
              }
              
              saveCustomersToFile(Customers);
              addTransaction(TargetID, "Deposit", amount);
          }
          else
          {
              System.out.println("Customer with ID: " + TargetID + " was not found!");
          }
      }

      public static void addTransaction(String TargetID, String type, double amount)
{
    try (FileWriter fw = new FileWriter(TransactionsFile, true)) {

        fw.write(TargetID + separator + type + separator + amount);
        fw.write(System.lineSeparator());

    } catch (Exception e) {
        System.out.println("An error occurred while adding transaction: " + e.getMessage());
    }
}
public static void PrintTransactions(String TargetID)
{
    
    try (BufferedReader br = new BufferedReader(new FileReader(TransactionsFile))) {

        String line;
        boolean found = false;

        System.out.println("--------- Transactions for: " + TargetID + " ---------");

        while ((line = br.readLine()) != null)
        {
            String[] record = line.split(separator);

            if (record.length >= 3 &&
                record[0].trim().equalsIgnoreCase(TargetID.trim()))
            {
                found = true;
                System.out.println(record[1] + " : " + record[2]);
            }
        }

        if (!found) {
            System.out.println("No transactions found for this customer.");
        }

        System.out.println("-------------------------------------");

    } catch (Exception e) {
        System.out.println("Error reading transactions: " + e.getMessage());
    }
}



      
}


    
