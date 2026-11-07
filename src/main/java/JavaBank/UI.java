package JavaBank;
import java.sql.SQLException;
import java.util.Scanner;

import JavaBank.Exceptions.*;

public class UI {
    private static final Scanner sc = new Scanner(System.in);
    static Database db = new Database();

    public void startBankingInterface() {
        boolean isRunning = true;
        while (isRunning) {
            try {
                BankAccount account = login();
                showAccountMenu(account);
            } 
            catch(AuthenticationException e){
                System.out.println(e.getMessage());
            } 
            catch(SQLException e) {
                System.out.println("A Database Error Has Occured! " + e.getMessage());
            }
    }
}

    private BankAccount login() throws SQLException, AuthenticationException {
        System.out.println("===== Welcome To JavaBank! =====");

        System.out.print("Please Enter Your Account ID: ");
        String accountId = sc.nextLine();

        System.out.print("Please Enter Your 6-Digit Pin: ");
        String accountPin = sc.nextLine();

        if (!accountPin.matches("\\d{6}")) throw new InvalidPinFormatException("Invalid Pin Format! Must be 6 Digits Long");
        return db.authenticateAccount(accountId, accountPin);
    }

    private void showAccountMenu(BankAccount account) {
        boolean isLoggedIn = true;
        while(isLoggedIn) {
            System.out.println("\n===== Welcome To JavaBank! =====");
            System.out.println("1.) Check Account");
            System.out.println("2.) Deposit Money");
            System.out.println("3.) Withdraw Money");
            System.out.println("4.) Logout");
            System.out.print("Please Choose A Process [1-4]: ");

            if(sc.hasNextInt()) {
                int choice = sc.nextInt();
                switch (choice) {

                case 1:
                    account.displayAccountDetails();
                    break;

                case 2:
                    System.out.print("Please Enter An Amount You Would Like To Deposit: ");
                    if(sc.hasNextBigDecimal()) account.deposit(sc.nextBigDecimal());
                    else {
                        System.out.println("Please Enter a Numeric Amount!");
                        sc.nextLine();
                    }
                    break;

                case 3:
                    System.out.print("Please Enter An Amount You Would Like To Withdraw: ");
                    if(sc.hasNextBigDecimal()) account.withdraw(sc.nextBigDecimal());
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
