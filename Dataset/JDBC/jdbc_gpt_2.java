public class JDBCExample {
    public static void main(String[] args) {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Set up the connection to the database
            String url = "jdbc:mysql://localhost:3306/mydatabase";
            String username = "myusername";
            String password = "mypassword";
            Connection conn = DriverManager.getConnection(url, username, password);
            
            // Create a statement object
            Statement stmt = conn.createStatement();
            
            // Execute a SQL query
            String sql = "SELECT * FROM mytable";
            ResultSet rs = stmt.executeQuery(sql);
            
            // Process the results
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                
                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
            }
            
            // Clean up
            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to load JDBC driver.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Failed to connect to database.");
            e.printStackTrace();
        }
    }
}
