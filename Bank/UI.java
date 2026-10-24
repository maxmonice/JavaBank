package Bank;
import java.util.Scanner;

public class UI {
    private static final Scanner sc = new Scanner(System.in);
    static Database db = new Database();

    public void program() {
        boolean isRunning = true;
        while (isRunning) {
            BankAccount account = login();
            if (account == null) {
                System.out.println("Invalid Account ID or PIN!");

            } else {
                showAccountMenu(account);
            }
        }
    }
    private BankAccount login() {
        System.out.println("===== Welcome To Polarity Bank! =====");

        System.out.print("Please Enter Your Account ID: ");
        String accountId = sc.nextLine();

        System.out.print("Please Enter Your 6-Digit Pin: ");
        int accountPin = sc.nextInt();
        sc.nextLine();

        return db.verifyAccount(accountId, accountPin);
    }

    private void showAccountMenu(BankAccount account) {
        boolean isLoggedIn = true;
        while(isLoggedIn) {
            System.out.println("\n===== Welcome To Polarity Bank! =====");
            System.out.println("1.) Check Account");
            System.out.println("2.) Deposit Money");
            System.out.println("3.) Withdraw Money");
            System.out.println("4.) Logout");
            System.out.print("Please Choose A Process [1-4]: ");

            if(sc.hasNextInt()) {
                int choice = sc.nextInt();
                switch (choice) {

                case 1:
                    account.displayInformation();
                    break;

                case 2:
                    System.out.print("Please Enter An Amount You Would Like To Deposit: ");
                    if(sc.hasNextDouble()) account.deposit(sc.nextDouble());
                    else {
                        System.out.println("Please Enter a Numeric Amount!");
                        sc.nextLine();
                    }
                    break;

                case 3:
                    System.out.print("Please Enter An Amount You Would Like To Withdraw: ");
                    if(sc.hasNextDouble()) account.withdraw(sc.nextDouble());
                    else {
                        System.out.println("Please Enter a Numeric Amount!");
                        sc.nextLine();
                    }
                    break;

                case 4:
                    System.out.println("===== Thank You For Doing Business With Polarity Bank! =====");
                    isLoggedIn = false;
                    break;

                default:
                    System.out.println("Please Enter A Process From [1-4]!");
                    break;        
                }  
            } 
            else {
                System.out.println("Invalid Input, Please Enter A Number That is A Process [1-4]!");
                sc.nextLine();
            }
        }
    }
}
