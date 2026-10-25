package JavaBank;
import java.math.BigDecimal;

public class BankAccount {
    static Database db = new Database();

    private String accountName;
    private BigDecimal accountBalance;
    private String accountId;
    private static final BigDecimal maintainingBalance = new BigDecimal("3000");

    // Parameterized Constructor
    public BankAccount(String accountName, BigDecimal accountBalance, String accountId) {
        this.accountName = accountName;
        this.accountBalance = accountBalance;
        this.accountId = accountId;

    }

    // Empty Constructor For Child Class
    public BankAccount() {}

    // Setters
    private void setAccountName(String accountName) {
        this.accountName = accountName;
        return;

    }

    private void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
        return;
    }

    private void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    // Getters
    public String getAccountName() {
        return accountName;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public String getAccountId() {
        return accountId;
    }

    // Methods

    public void changeAccountName(String accountName) {
        setAccountName(accountName);
    }

    public void changeAccountId(String accountId) {
        setAccountId(accountId);
    }

    public void deposit(BigDecimal deposit) {
        BigDecimal total = getAccountBalance().add(deposit);
        if (deposit.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Please Enter An Amount Greater Than 0!");
            return;
        }

        if (total.compareTo(maintainingBalance) < 0) {
            System.out.println("Your Current Balance is " + getAccountBalance());
            System.out.println("Your Balance Must Be At Least 3000!");
            return;
        }
        if (db.updateBalance(accountId, total)) {
            setAccountBalance(total);
            System.out.println("Your Balance Is Now " + total + "!");
        } else {
            System.out.println("Deposit Failed! Your Balance Remains Unchanged.");
        }
    }

    public void withdraw(BigDecimal withdrawal) {
        BigDecimal total = getAccountBalance().subtract(withdrawal);

        if (withdrawal.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Please Enter An Amount Greater Than 0!");
            return;
        }

        if (total.compareTo(maintainingBalance) < 0) {
            System.out.println("Your Current Balance is " + getAccountBalance());
            System.out.println("Your Withdrawal Would Violate The Maintaining Balance Of " + maintainingBalance + "!");
            return;

        }
        if (db.updateBalance(accountId, total)) {
            setAccountBalance(total);
            System.out.println("Your Balance Is Now " + total + "!");
        } else {
            System.out.println("Withdrawal Failed! Your Balance Remains Unchanged.");
        }

    }

    public void displayInformation() {
        System.out.println(getAccountName());
        System.out.println(getAccountBalance());
        System.out.println(getAccountId());

    }

}

