import java.util.ArrayList;


public class Employee implements Bank {
    
static ArrayList<Employee> Employees = new ArrayList<>();

String name, id, userName, password;
double balance = 0.0; 
Employee(){

}    
Employee(String name, String id, String userName, String password){
    this.name = name;
    this.id = id;
    this.userName = userName;
    this.password = password;
}

    @Override
    public void showDetails() {
        String a = ""    ;
    ScreenAndGenricMethod.PrintCustomerHeader();
    for(String s: FileOepration.Record){
        a += s + "            ";
    }
    System.out.println(a);
    }

    @Override
    public void showBalance() {
        ScreenAndGenricMethod.resetScreen();
        System.out.println("Your current balance is: " + this.balance + " SAR");
    }

    @Override
    public void deposite() {
        ScreenAndGenricMethod.resetScreen();
        FileOepration.depositeFromCustomerBalance(this.id,
                Double.parseDouble(ScreenAndGenricMethod.ReadStringFromUser("Enter the amount to deposite: ")));
        Control._UpdateInfo();
        System.out.println(
                "deposite successful. Your new balance is: " + Control.CurrentCustomer.getBalance() + " SAR");
    }

    @Override
    public void withdraw() {
        ScreenAndGenricMethod.resetScreen();
        FileOepration.withdrawFromCustomerBalance(this.id,
                Double.parseDouble(ScreenAndGenricMethod.ReadStringFromUser("Enter the amount to withdraw: ")));
        Control._UpdateInfo();
        System.out.println(
                "Withdrawal successful. Your new balance is: " + Control.CurrentCustomer.getBalance() + " SAR");
    }
    @Override
    public void showtransactions() {
        ScreenAndGenricMethod.resetScreen();
        FileOepration.PrintTransactions(this.id);
    }
    
}
