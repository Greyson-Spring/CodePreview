import java.sql.Connection;
import java.sql.Statement;
// test push webhook - 测试代码审查
// 修改了push脚本代码，测试push webhook功能
// 测试代码审查功能，修改了LoginController类，增加了一个formatUsers方法，并在login方法中添加了一个SQL注入漏洞。
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