package databaseLink;

import java.sql.SQLException;

public class Link {
	protected final SqlLink sqlLink;
	public final ClusterLinker clusterLink;
	public final LineFillingLinker lineFillingLink;
	public final LineLinker lineLink;
	
	public Link() throws SQLException {
		this.sqlLink = new SqlLink();
		this.clusterLink = new ClusterLinker(this);
		this.lineFillingLink = new LineFillingLinker(this);
		this.lineLink = new LineLinker(this);
	}
}
