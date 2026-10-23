package Bank;
import java.util.Scanner;

public class UI2 {
    private static final Scanner sc = new Scanner(System.in);
    static Database db = new Database();
    static boolean isRunning = true;

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
