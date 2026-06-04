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
            e.printStackTrace();  // 问题：不应该用 printStackTrace
            return false;
        }
    }
    public String formatUsers(String[] users) {
        String result = "";
        for (int i = 0; i < users.length; i++) {
            result = result + users[i] + ",";  // 问题：循环内字符串拼接
        }
        return result;
    }
}