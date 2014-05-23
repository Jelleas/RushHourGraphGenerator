package databaseLink;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import library.ClusterLibrary;
import library.Library;

import board.Board;
import board.Cluster;
import board.Line;

public class ClusterLinker extends TableLinker {

	public ClusterLinker(Link link) {
		super("cluster", link);
	}
	
	public int add(Cluster cluster) {
		int[] rows = cluster.getRowFillingIds();
		int[] columns = cluster.getColumnFillingsIds();
		int maxDistance = cluster.getMaxDistance();
		int size = cluster.size();
		int numSolutions = cluster.getNumSolutions();
		
		String rowsString = "";
		String columnsString = "";
		for (int i = 0; i < Board.lineSize; i++) {
			rowsString += rows[i] + ", ";
			columnsString += columns[i] + ", ";
		}
		
		String values = rowsString + columnsString + maxDistance + ", " + size + ", " + numSolutions;
		
		String addQuery = "INSERT INTO `" + tableName + "`(`rowFilling0`, `rowFilling1`, `rowFilling2`, `rowFilling3`, `rowFilling4`, `rowFilling5`," +
				" `columnFilling0`, `columnFilling1`, `columnFilling2`, `columnFilling3`, `columnFilling4`, `columnFilling5`, `maxDistance`, `size`, `numSolutions`)" +
				" VALUES(" + values + ")";
		
		return link.sqlLink.insertQuery(addQuery);
	}
	
	private ArrayList<Cluster> get(String getQuery) {
		Statement st = link.sqlLink.getStatement();
		ArrayList<Cluster> clusters = new ArrayList<Cluster>();
		
		try {
			ResultSet rs = st.executeQuery(getQuery);
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
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return clusters;
	}
	
	public ArrayList<Cluster> getRandom(int limit) {
		ArrayList<Cluster> clusters = new ArrayList<Cluster>();
		
		int min = getMin("id");
		int max = getMax("id");
		Random rand = new Random();
		
		for (int i = 0; i < limit; i++) {
			int id = rand.nextInt(max - min + 1) + min;
			clusters.add(getWhere("id = " + id).get(0));
		}
		
		return clusters;
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
	
	/*public long getSum(String columnName) {
		return 
	}*/
	
	public double getAverage(String columnName) {
		return getDouble("SELECT AVG(`" + columnName + "`) AS " + columnName + " FROM `" + tableName + "`", columnName);
	}
	
	public boolean contains(Cluster cluster) {
		int[] rowIds = cluster.getRowFillingIds();
		int[] columnIds = cluster.getColumnFillingsIds();
		String getQuery = "SELECT id FROM " + tableName + " WHERE ";
		
		for (int i = 0; i < Board.lineSize; i++)
			getQuery += "`rowFilling" + i + "` = " + rowIds[i] + " AND ";
		
		for (int i = 0; i < Board.lineSize - 1; i++)
			getQuery += "`columnFilling" + i + "` = " + columnIds[i] + " AND ";
		
		getQuery += "`columnFilling" + (Board.lineSize - 1) + "` = " + columnIds[Board.lineSize - 1];
		return getInt(getQuery, "id") > 0;
	}
} 
