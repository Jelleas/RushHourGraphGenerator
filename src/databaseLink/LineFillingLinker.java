package databaseLink;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import board.Line;

public class LineFillingLinker extends TableLinker {
	
	public LineFillingLinker(Link link) {
		super("linefilling", link);
	}
	
	public int add(Line line) {	
		int id = getId(line);
		if (id < 0) {
			int numCars = line.getNumberOfCars();
			int numTrucks = line.getNumberOfTrucks();
			String carFirst = line.isCarFirst() ? "TRUE" : "FALSE";
			
			String addQuery = "INSERT INTO `" + tableName + "`(`numCars`, `numTrucks`, `carFirst`) VALUES (" +
				 numCars + ", " + numTrucks + ", " + carFirst + ")";

			return link.sqlLink.insertQuery(addQuery);
		} else
			return id;
	}
	
	public int getId(Line line) {
		int numCars = line.getNumberOfCars();
		int numTrucks = line.getNumberOfTrucks();
		String carFirst = line.isCarFirst() ? "TRUE" : "FALSE";
		
		String getQuery = "SELECT id FROM " + tableName + " WHERE" +
				" `numCars` = " + numCars +
				" AND `numTrucks` = " + numTrucks +
				" AND `carFirst` = " + carFirst;
		Statement st = link.sqlLink.getStatement();
		
		try {
			ResultSet rs = st.executeQuery(getQuery);
			if (rs.next()) {
				int id = rs.getInt("id");
				rs.close();
				return id;
			}
			rs.close();
			st.close();
		} catch (SQLException e) {}
		
		return -1;
	}
}
