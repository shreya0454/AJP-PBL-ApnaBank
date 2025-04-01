package bank.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.mindrot.jbcrypt.BCrypt;

public class UserDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/ApnaBank_DB";
    private static final String USER = "root";
    private static final String PASSWORD = "Yug!namdar04";

    public static boolean registerUser(String username, String password) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Accounts (user_id, password) VALUES (?, ?);");
            PreparedStatement balancestmt = conn.prepareStatement("INSERT INTO User_Balance (balance, user_id) VALUES (?, ?);");
            balancestmt.setInt(1, 0);
            balancestmt.setString(2, username);
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String loginUser(String username, String password) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement stmt = conn.prepareStatement("SELECT password FROM Accounts WHERE user_id = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("password");
                if (BCrypt.checkpw(password, hashedPassword)) {
                    return JwtUtil.generateToken(username);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean verifyToken(String token) {
        try {
            String username = JwtUtil.extractUsername(token);
            return username != null;
        } catch (Exception e) {
            return false;
        }
    }
}
