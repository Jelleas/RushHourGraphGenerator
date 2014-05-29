package databaseLink;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class TableLinker {
	String tableName;
	Link link;
	
	public TableLinker(String tableName, Link link) {
		this.tableName = tableName;
		this.link = link;
	}
	
	protected long getLong(String getQuery, String columnName) {
		Statement st = link.sqlLink.getStatement();
		long result = -1;
		
		try {
			ResultSet rs = st.executeQuery(getQuery);
			if (rs.next())
				result = rs.getLong(columnName);
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
	
	public ArrayList<Long> getNumbersWhere(String whereClause, String columnName) {
		String getQuery = "SELECT " + columnName + " FROM `" + tableName + "` WHERE " + whereClause;
		ArrayList<Long> results = new ArrayList<Long>();
		
		try {
			Statement st = link.sqlLink.getStatement();
			ResultSet rs = st.executeQuery(getQuery);
			
			while (rs.next())
				results.add(rs.getLong(columnName));
			
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return results;
	}
	
	public long getMax(String columnName) {
		return getLong("SELECT MAX(`" + columnName + "`) AS " + columnName + " FROM `" + tableName + "`", columnName);
	}
	
	public long getMin(String columnName) {
		return getLong("SELECT MIN(`" + columnName + "`) AS " + columnName + " FROM `" + tableName + "`", columnName);
	}
	
	public long getCountWhere(String whereClause) {
		return getLong("SELECT COUNT(*) AS countResult FROM `" + tableName + "` WHERE " + whereClause, "countResult");
	}
	
	public double getAverage(String columnName) {
		return getDouble("SELECT AVG(`" + columnName + "`) AS " + columnName + " FROM `" + tableName + "`", columnName);
	}
	
	public long getSum(String columnName) {
		return getSumWhere("", columnName);
	}
	
	public long getSumWhere(String whereClause, String columnName) {
		if (!whereClause.isEmpty())
			whereClause = " WHERE " + whereClause;
		return getLong("SELECT SUM(`" + columnName + "`) AS " + columnName + " FROM " + tableName + whereClause, columnName);
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
