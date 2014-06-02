package databaseLink;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import library.ClusterLibrary;
import board.Board;
import board.Cluster;

public class SubclusterLinker extends TableLinker {
	public SubclusterLinker(Link link) {
		super("subcluster", link);
	}
	
	private ArrayList<Cluster> get(String getQuery, boolean shouldExpand) {
		ArrayList<Cluster> clusters = new ArrayList<Cluster>();
		
		Statement st = link.sqlLink.getStatement();
		try {
			ResultSet rs = st.executeQuery(getQuery);
			
			while (rs.next()) {
				int id = rs.getInt("id");
				Board board = link.boardLink.getWhere("subcluster = " + id).get(0);
				
				Cluster cluster;
				if (rs.getInt("maxDistance") == Cluster.unsolvableDistance)
					cluster = new Cluster(board);
				else
					cluster = new Cluster(ClusterLibrary.getAllSolutions(board));
				
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
	
	public int add(Cluster subcluster) {	
		int id = getId(subcluster);
		if (id < 0) {
			int clusterId = link.clusterLink.getId(subcluster);
			int maxDistance = subcluster.getMaxDistance();
			int size = subcluster.size();
			int numSolutions = subcluster.getNumSolutions();
			
			String addQuery = "INSERT INTO `" + tableName + "`(`cluster`, `maxDistance`, `size`, `numSolutions`) VALUES (" +
				 clusterId + ", " + maxDistance + ", " + size + ", " + numSolutions + ")";

			return link.sqlLink.insertQuery(addQuery);
		} else
			return id;
	}
	
	public int getId(Cluster subcluster) {
		int clusterId = link.clusterLink.getId(subcluster);
		if (clusterId < 0) // if cluster does not exist, return < 0 number.
			return clusterId;
		
		List<Long> ids = getNumbersWhere("maxDistance = " + subcluster.getMaxDistance() + " AND size = " +
				subcluster.size() + " AND numSolutions = " + subcluster.getNumSolutions(), "id");
		
		if (ids.isEmpty()) // if subcluster does not exist, return < 0 number.
			return -1;
		if (ids.size() == 1) // if only one subcluster found return its id.
			return (int)(long)ids.get(0);
		
		for (long id : ids) { // if multiple found, find the correct one and return its id.
			Board board = link.boardLink.getWhere("subcluster = " + id).get(0);
			if (subcluster.contains(board)) {
				return (int)id;
			}
		}
		
		return -1;
	}
	
	public ArrayList<Cluster> getWhere(String whereClause, boolean shouldExpand) {
		return get("SELECT * FROM `" + tableName + "` WHERE " + whereClause, shouldExpand);
	}
}
