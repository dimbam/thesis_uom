
public class Runner5 {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		System.out.println("Started");
		
		Connection conn = null;
		Properties connectionProps = new Properties();
	    connectionProps.put("user", "dbs");
	    connectionProps.put("password", "dbs");
	    String connectionString = "jdbc:postgresql://localhost:5432/postgres";
	    
	    ConnectionProvider provider = new ConnectionProvider(connectionString, connectionProps);
	    
	    StudentManager sm = new StudentManager(provider);
		
		

		for (Student student : sm.getAllStudents()) {
			System.out.println(student.getName() + ":" + student.getVsp());
		}
		
		Runner2.javaSystem.out.println("#####################################");
	    
	}

}
