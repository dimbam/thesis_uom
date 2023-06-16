import java.sql.*;

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
            
            // Create a new record
            String insertSql = "INSERT INTO mytable (name, age) VALUES ('John', 25)";
            int numRowsInserted = stmt.executeUpdate(insertSql);
            System.out.println(numRowsInserted + " row(s) inserted.");
            
            // Read the records
            String selectSql = "SELECT * FROM mytable";
            ResultSet rs = stmt.executeQuery(selectSql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                
                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
            }
            
            // Update a record
            String updateSql = "UPDATE mytable SET age = 30 WHERE name = 'John'";
            int numRowsUpdated = stmt.executeUpdate(updateSql);
            System.out.println(numRowsUpdated + " row(s) updated.");
            
            // Delete a record
            String deleteSql = "DELETE FROM mytable WHERE name = 'John'";
            int numRowsDeleted = stmt.executeUpdate(deleteSql);
            System.out.println(numRowsDeleted + " row(s) deleted.");
            
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
