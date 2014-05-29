package library;

import java.util.ArrayList;
import java.util.List;

import board.Board;
import board.Cluster;
import board.Line;

public class Node {
	public static long numClusters = 0;
	public static long numNewClusters = 0;
	
	public final String lineFilling;
	private List<String> lineFillings;
	private boolean hasSpawned;
	private List<Node> children;
	
	public Node(List<String> lineFillings) {
		this(lineFillings, null);
	}
	
	private Node(List<String> lineFillings, String lineFilling) {
		this.lineFilling = lineFilling;
		this.lineFillings = lineFillings;
		this.hasSpawned = false;
		this.children = new ArrayList<Node>();
	}
	
	public String getLineFilling() {
		return this.lineFilling;
	}
	
	public boolean kill(List<List<Line>> allRows, List<Line> path) {
		if (allRows == null) // Basecase1: if no rows, survive.
			return false;
		
		int depth = path.size();
		
		if (depth == Board.lineSize) { // Basecase2: if leaf, write and die.
			Line[] rows = new Line[Board.lineSize];
			for (int i = 0; i < Board.lineSize; i++)
				rows[i] = allRows.get(i).get(0);
			
			Line[] columns = new Line[Board.lineSize];
			path.toArray(columns);
			
			writeClusterToDatabase(rows, columns);
			
			return true;
		}
		
		if (!hasSpawned) { // If node has not spawned children yet, spawn children.
			for (String lineFilling : lineFillings)
				children.add(new Node(lineFillings, lineFilling));
			
			hasSpawned = true;
		}
		
		List<Node> childrenTemp = new ArrayList<Node>(children.size());
		
		for (Node child : children) {
			boolean childSurvives = true;
			for (Line column : Library.lineLibrary.getLines(child.getLineFilling())) {
				path.add(column);
				if (child.kill(getLinesThatFit(allRows, column, depth), path)) { // if child has died, break.
					childSurvives = false;
					path.remove(depth);
					break;
				}
				path.remove(depth);
			}
			if (childSurvives)
				childrenTemp.add(child);
		}
		
		children = childrenTemp;
		
		if (children.size() > 0)
			return false;
		else
			return true;
	}
	
	private static void writeClusterToDatabase(Line[] rows, Line[] columns) {
		Node.numClusters++;
		if (Node.numClusters % 250000 == 0) {
			System.out.println("NumClusters: " + Node.numClusters + " NumNewClusters: " + numNewClusters + " Time Taken: " + (System.currentTimeMillis() - ClusterLibrary.start));
		}
		
		Board board = new Board(rows, columns);
		if (!Library.link.clusterLink.contains(new Cluster(board))) {
			numNewClusters++;
			Cluster cluster = new Cluster(ClusterLibrary.getAllSolutions(board));
			cluster.expand();
			Library.link.clusterLink.add(cluster);
		}
	}
	
	private static List<List<Line>> getLinesThatFit(List<List<Line>> allLines, Line otherLine, int depth) {
		List<List<Line>> allLinesThatFit = new ArrayList<List<Line>>();
		
		for (int i = 0; i < Board.lineSize; i++) {
			List<Line> linesThatFit;
			
			if (otherLine.occupationLine[i]) {
				linesThatFit = new ArrayList<Line>();
				for (Line line : allLines.get(i))
					if (!line.occupationLine[depth])
						linesThatFit.add(line);
			} else
				linesThatFit = allLines.get(i);
			
			if (linesThatFit.isEmpty())
				return null;
			else
				allLinesThatFit.add(linesThatFit);
		}
		
		return allLinesThatFit;
	}
}
