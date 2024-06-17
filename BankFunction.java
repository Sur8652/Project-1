import java.util.Random;
import java.util.Scanner;

public class BankFunction {
    String name, email, accounttype;
    long accountnumber, mobilenumber, access = 4133219;
    float balance;
    boolean otpchecker = false;
    int counter = 0, otp;

    Bank bank[] = new Bank[20];

    Scanner sc = new Scanner(System.in);

    public String accounttype() {
        System.out.println("1. Saving");
        System.out.println("2. Current");
        System.out.println("Choose Your Account Type: ");
        int temp = sc.nextInt();


        if (temp == 1) {
            accounttype = "Saving";
        } else if (temp == 2) {
            accounttype = "Current";
        } else {
            System.out.println("Choose Correct Type: ");
            accounttype();
        }
        return accounttype;

    }

    public long checkmobilenumber(long mobile) {
        int count = 0;
        long tempmobile = mobile;
        while (mobile > 0) {
            mobile = mobile / 10;
            count++;
        }

        if (count == 10) {
            mobilenumber = tempmobile;
        } else {
            System.out.println("Enter Correct Mobile Number: ");
            checkmobilenumber(sc.nextLong());
        }

        return mobilenumber;
    }

    public long generateaccountnumber(long mobilenumber) {

        long temp = mobilenumber % 10000;
        accountnumber = access * 10000 + temp;
        access++;
        return accountnumber;
    }

    public float genetatebalance(String demo) {
        if (demo.equals("Saving")) {
            System.out.println("Enter Initial Balance: ");
            balance = sc.nextFloat();
        } else {
            System.out.println("Minimum Balance Should Be 10000");
            float tempbalance = sc.nextFloat();
            if (tempbalance >= 10000) {
                balance = tempbalance;
            } else {
                genetatebalance(demo);
            }
        }
        return balance;
    }

    public int otpgenerator() {
        Random otp = new Random();
        int temp = otp.nextInt(10000);
        return temp;
    }

    public void Addaccount() {
        System.out.println("Enter Your Name: ");
        name = sc.next();
        System.out.println("Enter Mobile Number: ");
        mobilenumber = checkmobilenumber(sc.nextLong());
        System.out.println("Enter Email Id: ");
        email = sc.next();
        accounttype = accounttype();
        accountnumber = generateaccountnumber(mobilenumber);
        balance = genetatebalance(accounttype);
        System.out.println("Account Created Successfully.");
        otp = otpgenerator();
        bank[counter] = new Bank(name, email, accounttype, accountnumber, mobilenumber, balance, otp);
        counter++;
        System.out.println("Your Otp For Validation: ");
        System.out.println(otp);
        otpchecker = true;

    }

    public void showaccount() {
        if (counter > 0 && otpchecker) {
            System.out.println("Enter OTP: ");
            int tempaccount = sc.nextInt();
            boolean found = false;
            for (int i = 0; i < counter; i++) {
                if (bank[i] != null && bank[i].getOtp() == tempaccount) {
                    bank[i].display();
                    found = true;
                }
            }

            if (!found) {
                System.err.println("Account Not Found !!!!!");
                System.out.println("Enter Correct OTP: ");
                showaccount();
            }
            otpchecker = false;

        } else if (counter > 0 && !otpchecker) {
            System.out.println("Enter Account Number: ");
            long tempaccount = sc.nextLong();
            boolean found = false;
            for (int i = 0; i < counter; i++) {
                if (bank[i] != null && bank[i].getAccountnumber() == tempaccount) {
                    bank[i].display();
                    found = true;
                }
            }

            if (!found) {
                System.err.println("Account Not Found !!!!!");
                System.out.println("Enter Account Number: ");
                showaccount();
            }
        } else {
            System.err.println("Generate Account First: ");
            Addaccount();
        }
    }

    public void showallaccounts() {
        boolean found = false;
        if (counter > 0) {
            for (int i = 0; i < counter; i++) {
                if (bank[i] != null) {
                    bank[i].display();
                    System.out.println("****************");
                    found = true;
                }
            }
        } else {
                found = true;
                System.err.println("There is No Account To Show");
        }

        if(!found){
            System.out.println("There is No Account's To Display.");
        }


    }

    public void diposit() {
        System.out.println("Enter Account Number: ");
        long temp_Acc_no = sc.nextLong();
        boolean TransactionCompleted = false;
        for (int i = 0; i < counter; i++) {
            if (bank[i] != null && bank[i].getAccountnumber() == temp_Acc_no) {
                System.out.println("How Much Money You Want To Add: ");
                float tempbalance = sc.nextFloat();
                balance = tempbalance + bank[i].getBalance();
                bank[i].setBalance(balance);
                System.out.println("Transaction Completed");
                TransactionCompleted = true;
            }
        }

        if (!TransactionCompleted) {// if(TransactionCompleted == false)
            System.out.println("Enter Correct Account Number: ");
            diposit();
        }
    }

    public void withdraw(long temp_Acc_no) {
        boolean TransactionCompleted = false;

        for (int i = 0; i < counter; i++) {
            if (bank[i] != null && bank[i].getAccountnumber() == temp_Acc_no) {
                System.out.println("Enter Amout: ");
                float tempbalance = sc.nextFloat();
                // For Saving Account
                if (bank[i].getBalance() > tempbalance && bank[i].getAccounttype().equals("Current")) {
                    tempbalance = bank[i].getBalance() - tempbalance;
                    if (tempbalance >= 10000) {
                        bank[i].setBalance(tempbalance);
                        TransactionCompleted = true;
                    } else {
                        System.out.println("Invalid Amout.");
                        TransactionCompleted = false;
                        withdraw(temp_Acc_no);
                    }

                } else {
                    tempbalance = bank[i].getBalance() - tempbalance;
                    if (tempbalance > 0) {
                        bank[i].setBalance(tempbalance);
                        TransactionCompleted = true;
                    } else {
                        System.out.println("Invalid Amout.");
                        TransactionCompleted = false;
                        withdraw(temp_Acc_no);
                    }
                }
            }

        }


        if (TransactionCompleted) {
            System.out.println("Transaction Done");
        } else {
            System.out.println("Enter Correct Account Number: ");
            temp_Acc_no = sc.nextLong();
            withdraw(temp_Acc_no);
        }
    }

    public void delete() {
        System.out.println("Enter Account Number: ");
        long temp_Acc_no = sc.nextLong();
        boolean delete = false;

        for (int i = 0; i < counter; i++) {
            if (bank[i] != null && bank[i].getAccountnumber() == temp_Acc_no) {
                bank[i] = null;
                delete = true;
            }
        }

        if (delete) {
            System.out.println("Account Deleted Successfully ! ");
        } else {
            System.out.println("Error !!!!");
            delete();
        }
    }

}
