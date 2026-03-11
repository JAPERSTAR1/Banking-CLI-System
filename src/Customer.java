public class Customer implements Bank {
    private String name;
    private String id;
    private double balance;
    private String accountType;
    private String jop;

    public Customer() {
    }

    public Customer(String name, String id, double balance, String accountType, String jop) {
        this.name = name;
        this.id = id;
        this.balance = balance;
        this.accountType = accountType;
        this.jop = jop;

    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getJop() {
        return jop;
    }

    public void setJop(String jop) {
        this.jop = jop;
    }

    @Override
    public void showDetails() {
        String a = "";
        ScreenAndGenricMethod.PrintCustomerHeader();
        for (String s : FileOepration.Record) {
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
