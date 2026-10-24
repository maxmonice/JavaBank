package Bank;
import java.util.Scanner;

public class UI2 {
    private static final Scanner sc = new Scanner(System.in);
    static Database db = new Database();


    private Account login() {

    }

    private void showAccountMenu() {
        System.out.println("\n===== Welcome To Polarity Bank! =====");
                    System.out.println("1.) Check Account");
                    System.out.println("2.) Deposit Money");
                    System.out.println("3.) Withdraw Money");
                    System.out.println("4.) Logout");
                    System.out.print("Please Choose A Process [1-4]: ");

                    int choice = sc.nextInt();
                    switch (choice) {

                        case 1:
                            account.displayInformation();
                            break;

                        case 2:
                            System.out.print("Please Enter An Amount You Would Like To Deposit: ");
                            account.deposit(sc.nextDouble());
                            break;

                        case 3:
                            System.out.print("Please Enter An Amount You Would Like To Withdraw: ");
                            account.withdraw(sc.nextDouble());
                            break;

                        case 4:
                            System.out.println("===== Thank You For Doing Business With Polarity Bank! =====");
                            isLoggedIn = false;
                            break;

                        default:
                            System.out.println("Please Enter A Process From [1-4]!");
                    }
    }

    public void program() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("===== Welcome To Polarity Bank! =====");

            System.out.print("Please Enter Your Account ID: ");
            String accountId = sc.nextLine();

            System.out.print("Please Enter Your 6-Digit Pin: ");
            int accountPin = sc.nextInt();
            sc.nextLine();

            BankAccount account = db.verifyAccount(accountId, accountPin);
            if (account == null) {
                System.out.println("Invalid Account ID or PIN!");

            } else {
                boolean isLoggedIn = true;
                while (isLoggedIn) {
                    
                }
            }
        }
    }
}
