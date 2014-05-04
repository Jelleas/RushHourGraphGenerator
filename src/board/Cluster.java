package board;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

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
	
	public boolean add(Board board) {
		boolean success = boardSet.add(board);
		if (success)
			boards.add(board);
		return success;
	}
	
	public void expand() {
		LinkedList<Board> boardQueue = new LinkedList<Board>(boards);
		
		while (!boardQueue.isEmpty()) {
			Board board = boardQueue.poll();
			ArrayList<Board> reachableBoards = board.getReachableBoards();
			for (Board reachableBoard : reachableBoards)
				if (add(reachableBoard))
					boardQueue.add(reachableBoard);
		}
	}
	
	public ArrayList<Board> getBoards() {
		return boards;
	}
	
	public String getClusterFilling() {
		return clusterFilling;
	}
	
	public int size() {
		return boards.size();
	}
}
