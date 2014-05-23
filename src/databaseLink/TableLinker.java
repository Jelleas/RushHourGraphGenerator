package databaseLink;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class TableLinker {
	String tableName;
	Link link;
	
	public TableLinker(String tableName, Link link) {
		this.tableName = tableName;
		this.link = link;
	}
	
	protected int getInt(String getQuery, String columnName) {
		Statement st = link.sqlLink.getStatement();
		int result = -1;
		
		try {
			ResultSet rs = st.executeQuery(getQuery);
			if (rs.next())
				result = rs.getInt(columnName);
			rs.close();
			st.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return result;
	}
	
	protected double getDouble(String getQuery, String columnName) {
		Statement st = link.sqlLink.getStatement();
		double result = -1;
		
		try {
			ResultSet rs = st.executeQuery(getQuery);
			if (rs.next())
				result = rs.getDouble(columnName);
			rs.close();
			st.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return result;
	}
	
	
	/**
	 * Check if id exists in table in database.
	 * @param id of entry to check for
	 * @param table table to check in
	 * @return
	 */
	public boolean check(int id) {
		String query = "SELECT id FROM " + tableName + " WHERE id = " + id + ";";
		Statement st = link.sqlLink.getStatement();
		
		try {
			ResultSet questionQueryResult = st.executeQuery(query);
			if (questionQueryResult.next())
				if (questionQueryResult.getInt("id") == id) {
					questionQueryResult.close();
					st.close();
					return true;
				}
			questionQueryResult.close();
			st.close();
		} catch (SQLException e) {}
		
		return false;
	}
}
