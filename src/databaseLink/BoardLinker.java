package databaseLink;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import library.Library;

import board.Board;
import board.Cluster;
import board.Line;

public class BoardLinker extends TableLinker {
	public BoardLinker(Link link) {
		super("board", link);
	}
	
	private ArrayList<Board> get(String getQuery) {
		ArrayList<Board> boards = new ArrayList<Board>();
		
		Statement st = link.sqlLink.getStatement();
		try {
			ResultSet rs = st.executeQuery(getQuery);
			
			while (rs.next()) {
				int[] rowIds = new int[] {rs.getInt("row0"), rs.getInt("row1"),
						rs.getInt("row2"), rs.getInt("row3"), rs.getInt("row4"), rs.getInt("row5")};
				int[] columnIds = new int[] {rs.getInt("column0"), rs.getInt("column1"),
						rs.getInt("column2"), rs.getInt("column3"), rs.getInt("column4"), rs.getInt("column5")};
				
				Line[] rows = new Line[rowIds.length];
				Line[] columns = new Line[rowIds.length];
				
				for (int i = 0; i < rowIds.length; i++) {
					rows[i] = Library.lineLibrary.getLineById(rowIds[i]);
					columns[i] = Library.lineLibrary.getLineById(columnIds[i]);
				}
				
				boards.add(new Board(rows, columns));
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return boards;
	}
	
	public int add(Board board, int subclusterId, int distance) {
		int id = getId(board);
		if (id >= 0)
			return id;
		
		return uncheckedAdd(board, subclusterId, distance);
	}
	
	public int uncheckedAdd(Board board, int subclusterId, int distance) {
		String addQuery = "INSERT INTO `" + tableName + "`(`subcluster`, `row0`, `row1`, `row2`, `row3`, `row4`, `row5`," +
				" `column0`,  `column1`, `column2`, `column3`, `column4`, `column5`, `distance`) VALUES(" + subclusterId + ", "; 
		
		Line[] rows = board.getRows();
		Line[] columns = board.getColumns();
		String rowsString = "";
		String columnsString = "";
		
		for (int i = 0; i < Board.lineSize; i++) {
			rowsString += rows[i].getId() + ", ";
			columnsString += columns[i].getId() + ", ";
		}
		
		addQuery += rowsString + columnsString + distance + ")";
		
		return link.sqlLink.insertQuery(addQuery);
	}
	
	public int getId(Board board) {
		String whereClauseRows = "";
		String whereClauseColumns = "";
		Line[] rows = board.getRows();
		Line[] columns = board.getColumns();
		
		for (int i = 0; i < Board.lineSize; i++) {
			whereClauseRows += "`row" + i + "` = " + rows[i].getId() + " AND ";
			whereClauseColumns += "`column" + i + "` = " + columns[i].getId();
			if (i != Board.lineSize - 1)
				whereClauseColumns += " AND ";
		}
		
		String whereClause = whereClauseRows + whereClauseColumns;
		List<Long> ids = getNumbersWhere(whereClause, "id");
		
		if (ids.isEmpty())
			return -1;
		else
			return (int)(long)ids.get(0);
	}
	
	public ArrayList<Board> getWhere(String whereClause) {
		return get("SELECT * FROM `" + tableName + "` WHERE " + whereClause);
	}
}
