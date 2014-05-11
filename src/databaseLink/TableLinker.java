package databaseLink;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class TableLinker {
	String tableName;
	Link link;
	
	public TableLinker(String tableName, Link link) {
		this.tableName = tableName;
		this.link = link;
	}
	
	/**
	 * Check if id exists in table in database.
	 * @param id of entry to check for
	 * @param table table to check in
	 * @return
	 */
	public boolean check(int id) {
		String query = "SELECT id FROM " + tableName + " WHERE id = " + id + ";";
		ResultSet questionQueryResult = link.sqlLink.extractQuery(query);
		
		try {
			questionQueryResult.next();
			if (questionQueryResult.getInt("id") == id)
				return true;
		} catch (SQLException e) {}
		
		return false;
	}
}
