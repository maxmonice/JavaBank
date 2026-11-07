package JavaBank;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import JavaBank.Exceptions.*;
public class Database {

    private String url = "jdbc:mysql://localhost:3306/JavaBank";
    private String username = "root";
    private String password = "";

    public Connection openConnection() throws SQLException{

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            return con;

        } catch (SQLException e) {
            System.out.println("Database Connection Failed!");
            e.printStackTrace();
            return null;
        }
    }

    public BankAccount authenticateAccount(String accountId, String accountPin) throws SQLException, AccountNotFoundException, InvalidPinException {

        String sql = "SELECT account_name, balance, account_id, pin_hash FROM accounts WHERE account_id = ?";

        try (Connection con = openConnection()) {

            if (con == null) {
                return null;
            }

            try (PreparedStatement stmt = con.prepareStatement(sql)) {

                stmt.setString(1, accountId);

                try (ResultSet result = stmt.executeQuery()) {

                    if (result.next()) {
                        String storedPinHash = result.getString("pin_hash");
                        if(PasswordHasher.verifyPassword(accountPin, storedPinHash)) { 

                            String accountName = result.getString("account_name");
                            BigDecimal accountBalance = result.getBigDecimal("balance");
                            String id = result.getString("account_id");

                            return new BankAccount(accountName, accountBalance, id);
                        }
                        else{
                            throw new InvalidPinException("Invalid Pin, Please Try Again!");
                        }
                    }
                    else {
                        throw new AccountNotFoundException("No Account Found, Please Try Again!");
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateAccountBalance(String accountId, BigDecimal newBalance) {
        String sql = "UPDATE accounts SET balance = ? WHERE account_id = ?";

        try (Connection con = openConnection()) {
            if (con == null) {
                System.out.println("Database Connection Failed!");
                return false;
            }

            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setBigDecimal(1, newBalance);
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