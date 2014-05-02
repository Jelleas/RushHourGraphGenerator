package board;

import java.util.ArrayList;
import java.util.HashSet;

public class Cluster {
	String clusterFilling;
	HashSet<Board> boardSet;
	ArrayList<Board> boards;
	
	public Cluster(Board board) {
		boardSet = new HashSet<Board>();
		boards = new ArrayList<Board>();
		
		boardSet.add(board);
		boards.add(board);
		
		clusterFilling = board.getBoardFilling();
	}
	
	/**
	 * Unsafe! Does not check if cluster contains board!
	 * @param board
	 */
	public void add(Board board) {
		boardSet.add(board);
		boards.add(board);
	}
	
	public ArrayList<Board> getBoards() {
		return boards;
	}
	
	public String getClusterFilling() {
		return clusterFilling;
	}
}
