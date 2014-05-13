package databaseLink;

import board.Board;
import board.Cluster;

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
} 
