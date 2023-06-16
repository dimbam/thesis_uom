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
            
            // Create a prepared statement object
            String sql = "INSERT INTO mytable (name, age) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            // Set the parameter values for the prepared statement
            pstmt.setString(1, "John Smith");
            pstmt.setInt(2, 30);
            
            // Execute the prepared statement
            int rowsInserted = pstmt.executeUpdate();
            
            // Print out the number of rows inserted
            System.out.println(rowsInserted + " rows inserted.");
            
            // Clean up
            pstmt.close();
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
