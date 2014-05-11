package databaseLink;

import java.sql.ResultSet;
import java.sql.SQLException;

import board.Line;

public class LineFillingLinker extends TableLinker {
	
	public LineFillingLinker(Link link) {
		super("linefilling", link);
	}
	
	public int add(Line line) {
		int numCars = line.getNumberOfCars();
		int numTrucks = line.getNumberOfTrucks();
		String order = line.getOrder() ? "TRUE" : "FALSE";
		
		String getQuery = "SELECT id FROM " + tableName + " WHERE" +
				" `numCars` = " + numCars +
				" AND `numTrucks` = " + numTrucks +
				" AND `carFirst` = " + order;
		ResultSet rs = link.sqlLink.extractQuery(getQuery);
		
		try {
			if (!rs.next()) {
				String addQuery = "INSERT INTO `" + tableName + "`(`numCars`, `numTrucks`, `carFirst`) VALUES (" +
					 numCars + ", " + numTrucks + ", " + order + ")";

				return link.sqlLink.insertQuery(addQuery);
			} else
				return rs.getInt("id");
		} catch (SQLException e) {}
		
		return -1;
	}
}
