
public class MysqlConnection {

	public static Connection getConnection( ) throws ClassNotFoundException, SQLException {
		Class.forName( "com.mysql.jdbc.Driver" );
		Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/testdb", "root", "" );
		return con;
	}

}
