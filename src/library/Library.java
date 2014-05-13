package library;

import databaseLink.Link;

public class Library {
	public static final LineLibrary lineLibrary = new LineLibrary();
	public static final ClusterLibrary clusterLibrary = new ClusterLibrary();
	
	public static void init() {
		lineLibrary.init();
		/*long start = System.currentTimeMillis();
		clusterLibrary.init();
		long end = System.currentTimeMillis();
		System.out.println("Time taken generating start boards: " + (end - start));*/
	}
	
	public static void syncWithDatabase(Link link) {
		lineLibrary.syncWithDatabase(link);
	}
}
