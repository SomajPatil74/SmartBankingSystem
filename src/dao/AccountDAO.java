package dao;

import model.*;
import db.DBConnection;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class AccountDAO {

    // Save account to DB
    public void saveAccount(BankAccount account) {

        String sql = """
            INSERT INTO accounts
            (account_number, holder_name, balance, account_type, overdraft_limit)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, account.getAccountNumber());
            ps.setString(2, account.getHolderName());
            ps.setDouble(3, account.getBalance());

            if (account instanceof SavingsAccount) {
                ps.setString(4, "SAVINGS");
                ps.setNull(5, Types.DOUBLE);
            }
            else if (account instanceof CurrentAccount ca) {
                ps.setString(4, "CURRENT");
                ps.setDouble(5, ca.getOverdraftLimit());
            }

            ps.executeUpdate();
            System.out.println("Account saved to DB.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Load all accounts from DB
    public Map<String, BankAccount> loadAccounts() {

        Map<String, BankAccount> map = new HashMap<>();
        String sql = "SELECT * FROM accounts";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                String accNo = rs.getString("account_number");
                String name = rs.getString("holder_name");
                double bal = rs.getDouble("balance");
                String type = rs.getString("account_type");
                double limit = rs.getDouble("overdraft_limit");

                BankAccount acc;

                if ("SAVINGS".equals(type)) {
                    acc = new SavingsAccount(accNo, name, bal);
                } else {
                    acc = new CurrentAccount(accNo, name, bal, limit);
                }

                map.put(accNo, acc);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return map;
    }

    // Update account balance in DB
    public void updateBalance(String accountNumber, double newBalance) {

        String sql = "UPDATE accounts SET balance = ? WHERE account_number = ?";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, newBalance);
            ps.setString(2, accountNumber);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
