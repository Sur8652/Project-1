import java.util.Scanner;

public class Test {

    static void menu() {
        System.out.println("1. Add Account");
        System.out.println("2. Show Account");
        System.out.println("3. Show All Account's");
        System.out.println("4. Diposit Money");
        System.out.println("5. Withdraw Money");
        System.out.println("6. Delete Account");
        System.out.println("7. Exit");
        System.out.println("Enter Your Choice: ");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BankFunction call = new BankFunction();

        while (true) {
            menu();
            int input = sc.nextInt();

            switch (input) {
                case 1:
                    call.Addaccount();
                    break;

                case 2:
                    call.showaccount();
                    break;

                case 3:
                    call.showallaccounts();
                    break;

                case 4:
                    call.diposit();
                    break;

                case 5:
                    System.out.println("Enter Account Number: ");
                    long temp_Acc_no = sc.nextLong();
                    call.withdraw(temp_Acc_no);
                    break;

                case 6:
                    call.delete();
                    break;

                case 7:
                    System.out.println("Thank You! ");
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}
