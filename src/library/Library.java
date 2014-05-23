package library;

import java.sql.SQLException;

import board.Line;
import databaseLink.Link;

public class Library {
	public static final LineLibrary lineLibrary = new LineLibrary();
	public static final ClusterLibrary clusterLibrary = new ClusterLibrary();
	public static Link link;
	
	public static void init() {
		lineLibrary.init();
		clusterLibrary.init();
		try {
			link = new Link();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/*long start = System.currentTimeMillis();
		clusterLibrary.init();
		long end = System.currentTimeMillis();
		System.out.println("Time taken generating start boards: " + (end - start));*/
	}
	
	public static void syncWithDatabase() {
		lineLibrary.syncWithDatabase(link);
	}
	
	public static void buildDatabase() {
		for (Line line : lineLibrary.getLines())
			line.setId(link.lineFillingLink.add(line));
		
		clusterLibrary.fillDatabase(link);
	}
}
