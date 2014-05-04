package library;

public class Library {
	public static final LineLibrary lineLibrary = new LineLibrary();
	public static final ClusterLibrary clusterLibrary = new ClusterLibrary();
	
	public static void init() {
		lineLibrary.init();
		//clusterLibrary.init();
	}
}
