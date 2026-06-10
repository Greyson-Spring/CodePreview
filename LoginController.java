import java.sql.Connection;
import java.sql.Statement;

public class LoginController {
    
    public boolean login(Connection conn, String username, String password) {
        String sql = "SELECT * FROM users WHERE name='" + username + "' AND pwd='" + password + "'";
        try {
            Statement stmt = conn.createStatement();
            stmt.executeQuery(sql);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
    
    public String formatUsers(String[] users) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < users.length; i++) {
            result.append(users[i]).append(",");
        }
        return result.toString();
    }
}