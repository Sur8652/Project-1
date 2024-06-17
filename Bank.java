public class Bank {
   
    private String name, email, accounttype;
    private long accountnumber, mobilenumber;
    private float balance;
    private int otp;

    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }
    public void setAccountnumber(long accountnumber) {
        this.accountnumber = accountnumber;
    }
    public void setMobilenumber(long mobilenumber) {
        this.mobilenumber = mobilenumber;
    }
    public long getAccountnumber() {
        return accountnumber;
    }
    
    public float getBalance() {
        return balance;
    }
    public void setBalance(float balance) {
        this.balance = balance;
    }

    

    public int getOtp() {
        return otp;
    }
    public void setOtp(int otp) {
        this.otp = otp;
    }

    

    public String getAccounttype() {
        return accounttype;
    }
    public Bank(String name, String email, String accounttype, long accountnumber, long mobilenumber, float balance, int otp) {
        this.name = name;
        this.email = email;
        this.accounttype = accounttype;
        this.accountnumber = accountnumber;
        this.mobilenumber = mobilenumber;
        this.balance = balance;
        this.otp = otp;
    }
    
    public void display(){
        System.out.println("Account Number: " + accountnumber);
        System.out.println("Account Type: " + accounttype);
        System.out.println("Your Balance: " + balance);
        System.out.println("Account Holder's Name: " + name);
        System.out.println("Mobile Number: "+ mobilenumber);
        System.out.println("Email Id: " + email);

    }

    
}
