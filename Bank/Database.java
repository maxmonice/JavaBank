package Bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {

    private String url = "jdbc:mysql://localhost:3306/Bank";
    private String username = "root";
    private String password = "";

    public Connection connect() {

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            return con;

        } catch (SQLException e) {
            System.out.println("Database Connection Failed!");
            e.printStackTrace();
            return null;
        }
    }

    public BankAccount verifyAccount(String accountId, int accountPin) {

        String sql = "SELECT account_name, balance, account_id, pin " + "FROM accounts WHERE account_id = ?";

        try (Connection con = connect()) {

            if (con == null) {
                return null;
            }

            try (PreparedStatement stmt = con.prepareStatement(sql)) {

                stmt.setString(1, accountId);

                try (ResultSet result = stmt.executeQuery()) {

                    if (result.next()) {
                        int storedPin = result.getInt("pin");
                        if(accountPin == storedPin) {

                            String accountName = result.getString("account_name");
                            double accountBalance = result.getDouble("balance");
                            String id = result.getString("account_id");

                            return new BankAccount(accountName, accountBalance, id);
                        }
                        else{
                            System.out.println("Wrong Pin Entered, Please Try Again");
                            return null;
                        }
                    }
                    else {
                        System.out.println("No Account Found, Please Try Again!");
                        return null;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateBalance(String accountId, double newBalance) {
        String sql = "UPDATE accounts SET balance = ? WHERE account_id = ?";

        try (Connection con = connect()) {
            if (con == null) {
                System.out.println("Database Connection Failed!");
                return false;
            }

            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setDouble(1, newBalance);
                stmt.setString(2, accountId);
                int rowsUpdated = stmt.executeUpdate();

                if (rowsUpdated == 0) {
                    System.out.println("Balance Update Failed: No Rows Changed!");
                    return false;
                } else if (rowsUpdated == 1) {
                    return true;
                } else {
                    System.out.println("CRITICAL: Multiple Accounts Changed! " + rowsUpdated + " rows were affected. Please investigate!");
                    return false;
                }
            }

        } catch (SQLException e) {
            System.out.println("Failed To Update Balance!");
            e.printStackTrace();
            return false;
        }
    }
}