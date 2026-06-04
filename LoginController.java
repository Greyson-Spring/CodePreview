import java.sql.Connection;
import java.sql.Statement;

public class LoginController {
    
    // 有问题的代码（SQL注入风险）
    public boolean login(Connection conn, String username, String password) {
        // 危险：字符串拼接 SQL
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
    
    // 有问题的代码（性能问题）
    public String formatUsers(String[] users) {
        String result = "";
        for (int i = 0; i < users.length; i++) {
            result = result + users[i] + ",";  // 问题：循环内字符串拼接
        }
        return result;
    }
}