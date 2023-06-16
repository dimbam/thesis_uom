
public class DeleteStatement {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		// 1. Register driver.
		Class.forName("com.mysql.jdbc.Driver");

		// 2. Create Connection
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ncitdb", "root", "");

		// 3. Create Statement
		Statement stat = conn.createStatement();

		stat.executeUpdate("DELETE FROM user WHERE username = 'sushma'");

		System.out.println("Query Deleted: ");

		conn.close();

	}
}
