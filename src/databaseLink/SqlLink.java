package databaseLink;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Is used to send SQL queries to the database and
 * receive data if the query is an extractquery.
 */
public class SqlLink {
	public static final String defaultUrl = "jdbc:mysql://localhost:3306/rushhour";
    public static final String defaultUser = "root";
    public static final String defaultPassword = "";

    private Connection con;
    
    /**
     * Constructor for SQLLink which sets url, user and password to default values.
     * @throws SQLException 
     */
	protected SqlLink() throws SQLException {
		this(defaultUrl, defaultUser, defaultPassword);
	}

	/**
	 * Constructor for SQLLink which sets url, user and password to specified url, user, password
	 * @param url
	 * @param user
	 * @param password
	 * @throws SQLException 
	 */
	protected SqlLink(String url, String user, String password) throws SQLException {
		try {
			Class.forName ("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
		this.con = DriverManager.getConnection(url, user, password);
	}

	/**
	 * Query the database, returns the result of the query.
	 * @param q The query.
	 * @return result of query.
	 */
	protected ResultSet extractQuery(String q) {
		ResultSet rs = null;
		try{
			Statement st = con.createStatement();
            rs = st.executeQuery(q);
		} catch (SQLException ex) {
			System.out.println(ex);
		} 
		return rs;
	}
	
	/**
	 * InsertQuery returns the id of a added element (if an element is added)
	 * (does not support multiple inserts in a single query (impossible?) but can be edited to do so)
	 * @param q
	 * @return the new id of an added element, -1 if there is no such id
	 */
	protected int insertQuery(String q) {
		try { 
			Statement st = con.createStatement();
            st.executeUpdate(q, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next())
                return rs.getInt(1);
            return -1;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return -2;
	}

}