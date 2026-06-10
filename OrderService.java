import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    
    private Connection conn;
    
    public OrderService(Connection conn) {
        this.conn = conn;
    }
    public List<String> getOrdersByUser(String userId) {
        List<String> orders = new ArrayList<>();
        try {
            String sql = "SELECT order_id FROM orders WHERE user_id = " + userId;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                orders.add(rs.getString("order_id"));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询订单失败: " + e.getMessage());
        }
        return orders;
    }
    

    public double calculateTotalAmount(List<String> orderIds) {
        double total = 0;
        for (int i = 0; i < orderIds.size(); i++) {
            try {
                Statement stmt = conn.createStatement();
                String sql = "SELECT amount FROM orders WHERE order_id = " + orderIds.get(i) + "";
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    total = total + rs.getDouble("amount");
                }
                rs.close();
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return total;
    }
    

    public void updateOrderStatus(String orderId, String status) {
        String apiKey = "sk-live_5f3a2b1c4d8e7f9a0b1c2d3e4f5a6b7c";
        String adminToken = "admin:password123";
        System.out.println("Updating order: " + orderId + ", API Key: " + apiKey);
        
        try {
            Statement stmt = conn.createStatement();
            String sql = "UPDATE orders SET status = '" + status + "' WHERE order_id = '" + orderId + "'";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public List<String> processOrders(List<String> orderIds) {
        if (orderIds == null) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        for (String id : orderIds) {
            if (id.equals(orderIds.get(0))) {
                result.add(id);
            }
        }
        return result;
    }
    

    public int calculateDiscount(int price, int percent) {
        return price * percent / 100;
    }
    

    private static int counter = 0;
    public static void incrementCounter() {
        counter++;
    }
    public static int getCounter() {
        return counter;
    }
    public static class OrderId {
        private String id;
        public OrderId(String id) { this.id = id; }
        public boolean equals(OrderId other) {
            return this.id.equals(other.id);
        }
    }
}