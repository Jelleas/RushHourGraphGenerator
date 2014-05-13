package databaseLink;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import library.ClusterLibrary;
import library.Library;

import board.Board;
import board.Cluster;
import board.Line;

public class ClusterLinker extends TableLinker{

	public ClusterLinker(Link link) {
		super("cluster", link);
	}
	
	public int add(Cluster cluster) {
		int[] rows = cluster.getRowFillingIds();
		int[] columns = cluster.getColumnFillingsIds();
		int maxDistance = cluster.getMaxDistance();
		int size = cluster.size();
		
		String rowsString = "";
		String columnsString = "";
		for (int i = 0; i < Board.lineSize; i++) {
			rowsString += rows[i] + ", ";
			columnsString += columns[i] + ", ";
		}
		
		String values = rowsString + columnsString + maxDistance + ", " + size;
		
		String addQuery = "INSERT INTO `" + tableName + "`(`rowFilling0`, `rowFilling1`, `rowFilling2`, `rowFilling3`, `rowFilling4`, `rowFilling5`," +
				" `columnFilling0`, `columnFilling1`, `columnFilling2`, `columnFilling3`, `columnFilling4`, `columnFilling5`, `maxDistance`, `size`)" +
				" VALUES(" + values + ")";
		
		return link.sqlLink.insertQuery(addQuery);
	}
	
	private ArrayList<Cluster> get(String getQuery) {
		ResultSet rs = link.sqlLink.extractQuery(getQuery);
		ArrayList<Cluster> clusters = new ArrayList<Cluster>();
		
		try {
			while (rs.next()) {
				int[] rowFillings = new int[] {rs.getInt("rowFilling0"), rs.getInt("rowFilling1"),
						rs.getInt("rowFilling2"), rs.getInt("rowFilling3"), rs.getInt("rowFilling4"), rs.getInt("rowFilling5")};
				int[] columnFillings = new int[] {rs.getInt("columnFilling0"), rs.getInt("columnFilling1"),
						rs.getInt("columnFilling2"), rs.getInt("columnFilling3"), rs.getInt("columnFilling4"), rs.getInt("columnFilling5")};
				
				Line[] rows = new Line[rowFillings.length];
				Line[] columns = new Line[rowFillings.length];
				
				for (int i = 0; i < rowFillings.length; i++) {
					rows[i] = Library.lineLibrary.getLines(rowFillings[i]).get(0);
					columns[i] = Library.lineLibrary.getLines(columnFillings[i]).get(0);
				}
				
				Cluster cluster = new Cluster(ClusterLibrary.getAllSolutions(new Board(rows, columns)));
				cluster.expand();
				clusters.add(cluster);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return clusters;
	}
	
	private int getInt(String getQuery, String columnName) {
		ResultSet rs = link.sqlLink.extractQuery(getQuery);
		int result = -1;
		
		try {
			if (rs.next())
				result = rs.getInt(columnName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	private double getDouble(String getQuery, String columnName) {
		ResultSet rs = link.sqlLink.extractQuery(getQuery);
		double result = -1;
		
		try {
			if (rs.next())
				result = rs.getDouble(columnName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<Cluster> getWhere(String whereClause) {
		return get("SELECT * FROM `" + tableName + "` WHERE " + whereClause);
	}
	
	public int getMax(String columnName) {
		return getInt("SELECT MAX(`" + columnName + "`) AS " + columnName + " FROM `" + tableName + "`", columnName);
	}
	
	public int getMin(String columnName) {
		return getInt("SELECT MIN(`" + columnName + "`) AS " + columnName + " FROM `" + tableName + "`", columnName);
	}
	
	public double getAverage(String columnName) {
		return getDouble("SELECT AVG(`" + columnName + "`) AS " + columnName + " FROM `" + tableName + "`", columnName);
	}
} 
