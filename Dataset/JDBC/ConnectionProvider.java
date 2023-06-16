

public class ConnectionProvider {
	private String connectionString;
	private Properties connectionProps;
	
	public ConnectionProvider(String connectionString, Properties connectionProps){
		this.connectionString = connectionString;
		this.connectionProps = connectionProps;
	}
	
	public Connection getConnection() {
		try {
			return(DriverManager.getConnection(connectionString, connectionProps));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
