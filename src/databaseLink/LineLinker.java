package databaseLink;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import library.Library;

import board.Board;
import board.Line;

public class LineLinker extends TableLinker {
	LineLinker(Link link) {
		super("line", link);
	}
	
	public int add(Line line) {	
		int id = getId(line);
		if (id < 0) {
			int lineFillingId = Library.link.lineFillingLink.getId(line);
			int structure = 0;
			for (int i = 0; i < Board.lineSize; i++) {
				structure += line.line[i] ? 1 : 0;
				structure *= 10;
			}
			structure /= 10;
			
			String addQuery = "INSERT INTO `" + tableName + "`(`lineFilling`, `structure`) VALUES (" +
				 lineFillingId + ", " + structure + ")";

			System.out.println(addQuery);
			return link.sqlLink.insertQuery(addQuery);
		} else
			return id;
	}
	
	public int getId(Line line) {
		int structure = 0;
		for (int i = 0; i < Board.lineSize; i++) {
			structure += line.line[i] ? 1 : 0;
			structure *= 10;
		}
		structure /= 10;
		
		String getQuery = "SELECT id FROM " + tableName + " WHERE" +
				" `structure` = " + structure;
		
		Statement st = link.sqlLink.getStatement();
		try {
			ResultSet rs = st.executeQuery(getQuery);
			if (rs.next()) {
				int id = rs.getInt("id");
				rs.close();
				st.close();
				return id;
			}
			rs.close();
			st.close();
		} catch (SQLException e) {}
		
		return -1;
	}
}
