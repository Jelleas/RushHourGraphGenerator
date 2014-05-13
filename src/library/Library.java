package library;

import board.Line;
import databaseLink.Link;

public class Library {
	public static final LineLibrary lineLibrary = new LineLibrary();
	public static final ClusterLibrary clusterLibrary = new ClusterLibrary();
	
	public static void init() {
		lineLibrary.init();
		clusterLibrary.init();
		/*long start = System.currentTimeMillis();
		clusterLibrary.init();
		long end = System.currentTimeMillis();
		System.out.println("Time taken generating start boards: " + (end - start));*/
	}
	
	public static void syncWithDatabase(Link link) {
		lineLibrary.syncWithDatabase(link);
	}
	
	public static void buildDatabase(Link link) {
		for (Line line : lineLibrary.getLines())
			line.setId(link.lineFillingLink.add(line));
		
		clusterLibrary.fillDatabase(link);
	}
}
