package dao;

import db.DBConnection;
import model.Transaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionDAO {
    
    public void saveTransaction(Transaction tx) {
        String sql = """
            INSERT INTO transactions (account_number, type, amount)
            VALUES (?, ?, ?)
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, tx.getAccountNumber());
                ps.setString(2, tx.getType());
                ps.setDouble(3, tx.getAmount());
                ps.executeUpdate();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
