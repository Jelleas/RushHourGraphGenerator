package databaseLink;

import java.sql.SQLException;

public class Link {
	protected final SqlLink sqlLink;
	public final ClusterLinker clusterLink;
	
	public Link() throws SQLException {
		this.sqlLink = new SqlLink();
		this.clusterLink = new ClusterLinker(this);
	}
}
