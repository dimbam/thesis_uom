
public class DBConnection {
    
    private Connection conn;
    private String driver="org.gjt.mm.mysql.Driver";
    private static DBConnection connection=null;
    
    /** Creates a new instance of DBConnection */
    private DBConnection() 
    {
        initConnection();
    }
    public static DBConnection getDBConnection()
    {
        if(connection==null)
            connection=new DBConnection();
        
        return connection;
        
    }
    public void initConnection()
    {
        try {
            try {
                Class.forName(driver);
                conn = DriverManager.getConnection("jdbc:mysql://localhost/Kamosi","root","");
                
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public Connection getConnection()
    {
        return conn;
    }
    
}
