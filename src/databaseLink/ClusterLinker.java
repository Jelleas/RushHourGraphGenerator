package databaseLink;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
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
		int[] columns = cluster.getColumnFillingIds();
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
	
	private ArrayList<Cluster> get(String getQuery, boolean shouldExpand, boolean justOneBoard) {
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
					rows[i] = Library.lineLibrary.getLinesByLineFillingId(rowFillings[i]).get(0);
					columns[i] = Library.lineLibrary.getLinesByLineFillingId(columnFillings[i]).get(0);
				}
				
				Cluster cluster;
				if (justOneBoard || rs.getInt("maxDistance") == Cluster.unsolvableDistance)
					cluster = new Cluster(new Board(rows, columns), rs.getInt("id"));
				else
					cluster = new Cluster(ClusterLibrary.getAllSolutions(new Board(rows, columns)), rs.getInt("id"));
				
				if (shouldExpand)
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
		return getRandom(limit, true);
	}
	
	public ArrayList<Cluster> getRandom(int limit, boolean shouldExpand) {
		return getRandomWhere("", limit, shouldExpand);
	}
	
	public ArrayList<Cluster> getRandomWhere(String whereClause, int limit, boolean shouldExpand) {
		ArrayList<Cluster> clusters = new ArrayList<Cluster>();
		Random rand = new Random();
		
		if (whereClause.isEmpty() || getCountWhere(whereClause) > 100000) {
			if (whereClause.isEmpty())
				whereClause = "";
			else
				whereClause = " AND " + whereClause;
			
			int min = (int)getMin("id");
			int max = (int)getMax("id");
			
			while (clusters.size() < limit) {
				int id = rand.nextInt(max - min + 1) + min;
				List<Cluster> clustersTemp = getWhere("id = " + id + whereClause, shouldExpand);
				if (!clustersTemp.isEmpty())
					clusters.add(clustersTemp.get(0));
			}
		} else {
			List<Long> ids = getNumbersWhere(whereClause, "id");
			Collections.shuffle(ids);
			
			while (!ids.isEmpty() && clusters.size() < limit) {
				long id = ids.get(ids.size() - 1);
				ids.remove(ids.size() - 1);
				clusters.add(getWhere("id = " + id, shouldExpand).get(0));
			}
		}
		
		return clusters;
	}
	
	public ArrayList<Long> getRandomSizesWhere(String whereClause, int limit) {
		ArrayList<Long> numbers = new ArrayList<Long>();
		
		if (!whereClause.isEmpty())
			whereClause = " AND " + whereClause;
		
		int min = (int)getMin("id");
		int max = (int)getMax("id");
		Random rand = new Random();
		
		while (numbers.size() < limit) {
			int id = rand.nextInt(max - min + 1) + min;
			List<Long> numbersTemp = getNumbersWhere("id = " + id +  whereClause, "size");
			if (!numbersTemp.isEmpty())
				numbers.add(numbersTemp.get(0));
		}
		
		return numbers;
	}
	
	public ArrayList<Cluster> getWhere(String whereClause) {
		return getWhere(whereClause, true);
	}
	
	public ArrayList<Cluster> getWhere(String whereClause, boolean shouldExpand) {
		return getWhere(whereClause, shouldExpand, false);
	}
	
	public ArrayList<Cluster> getWhere(String whereClause, boolean shouldExpand, boolean justOneBoard) {
		return get("SELECT * FROM `" + tableName + "` WHERE " + whereClause, shouldExpand, justOneBoard);
	}
	
	public int getId(Cluster cluster) {
		int[] rowIds = cluster.getRowFillingIds();
		int[] columnIds = cluster.getColumnFillingIds();
		String getQuery = "SELECT id FROM " + tableName + " WHERE ";
		
		for (int i = 0; i < Board.lineSize; i++)
			getQuery += "`rowFilling" + i + "` = " + rowIds[i] + " AND ";
		
		for (int i = 0; i < Board.lineSize - 1; i++)
			getQuery += "`columnFilling" + i + "` = " + columnIds[i] + " AND ";
		
		getQuery += "`columnFilling" + (Board.lineSize - 1) + "` = " + columnIds[Board.lineSize - 1];
		
		return (int)getLong(getQuery, "id");
	}
	
	public boolean contains(Cluster cluster) {
		return getId(cluster) > 0;
	}
} 
