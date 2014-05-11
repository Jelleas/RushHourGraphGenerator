package board;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import library.Library;

public final class Cluster {
	final class ClusterBoard {
		final Board board;
		final ClusterBoard parentBoard;
		final int distance;
		
		public ClusterBoard(Board board, ClusterBoard parentBoard, int distance) {
			this.board = board;
			this.parentBoard = parentBoard;
			this.distance = distance;
		}
		
		public boolean equals(Object o) {
			if (o instanceof ClusterBoard)
				return board.equals(((ClusterBoard)o).board);
			else if (o instanceof Board)
				return board.equals((Board)o);
			else
				return false;
		}
		
		public int hashCode() {
			return board.hashCode();
		}
	}
	
	String clusterFilling;
	ArrayList<Integer> distances;

	/*
	HashSet<Board> boardSet;
	ArrayList<Board> boards;
	public Cluster(Board board) {
		boardSet = new HashSet<Board>();
		boards = new ArrayList<Board>();
		distances = new ArrayList<Integer>();
		
		add(board, 0);
		
		clusterFilling = board.getBoardFilling();
	}
	
	private boolean add(Board board, int distance) {
		boolean success = boardSet.add(board);
		if (success) {
			boards.add(board);
			distances.add(distance);
		}
		return success;
	}

	public void expand() {
		LinkedList<Board> boardQueue = new LinkedList<Board>(boards);
		LinkedList<Integer> distancesQueue = new LinkedList<Integer>(distances);
		
		while (!boardQueue.isEmpty()) {
			Board board = boardQueue.poll();
			int distance = distancesQueue.poll();
			ArrayList<Board> reachableBoards = board.getReachableBoards();
			
			for (Board reachableBoard : reachableBoards) {
				if (add(reachableBoard, distance + 1)) {
					boardQueue.add(reachableBoard);
					distancesQueue.add(distance);
				}
			}
		}
	}*/
	
	
	HashSet<ClusterBoard> boardSet;
	ArrayList<ClusterBoard> boards;
	public Cluster(Board board) {
		boardSet = new HashSet<ClusterBoard>();
		boards = new ArrayList<ClusterBoard>();
		
		add(new ClusterBoard(board, null, 0));
		
		clusterFilling = board.getBoardFilling();
	}
	
	public Cluster(List<Board> boards) {
		this.boardSet = new HashSet<ClusterBoard>();
		this.boards = new ArrayList<ClusterBoard>();
		
		for (Board board : boards)
			add(new ClusterBoard(board, null, 0));
			
		this.clusterFilling = boards.get(0).getBoardFilling();
	}
	
	private boolean add(ClusterBoard board) {
		boolean success = boardSet.add(board);
		if (success)
			boards.add(board);
		
		return success;
	}
	
	public void expand() {
		LinkedList<ClusterBoard> boardQueue = new LinkedList<ClusterBoard>(boards);
		
		while (!boardQueue.isEmpty()) {
			ClusterBoard board = boardQueue.poll();
			ArrayList<Board> reachableBoards = board.board.getReachableBoards();
			
			for (Board reachableBoard : reachableBoards) {
				ClusterBoard reachableClusterBoard = new ClusterBoard(reachableBoard, board, board.distance + 1);
				if (add(reachableClusterBoard))
					boardQueue.add(reachableClusterBoard);
			}
		}
	}
	
	public void solve() {
		LinkedList<ClusterBoard> boardQueue = new LinkedList<ClusterBoard>(boards);
		
		while (!boardQueue.isEmpty()) {
			ClusterBoard board = boardQueue.poll();
			ArrayList<Board> reachableBoards = board.board.getReachableBoards();
			
			for (Board reachableBoard : reachableBoards) {
				ClusterBoard reachableClusterBoard = new ClusterBoard(reachableBoard, board, board.distance + 1);
				if (add(reachableClusterBoard)) {
					if (reachableClusterBoard.board.isSolution())
						return;
					boardQueue.add(reachableClusterBoard);
				}
			}
		}
	}
	
	public Line[][] getAllRowsSolutionsOnly() {
		Line[][] allRows = new Line[Board.lineSize][];
		Line[] rows = boards.get(0).board.getRows();
		int goalLoc = (Board.lineSize - 1) / 2;
				
		for (int i = 0; i < Board.lineSize; i++) {
			Line[] allRow = Library.lineLibrary.getLines(rows[i].getLineFilling()).toArray(new Line[0]);
			
			if (i == goalLoc) {
				ArrayList<Line> allRowSolutionsOnly = new ArrayList<Line>();
				for (Line row : allRow)
					if (row.line[Board.lineSize - 2])
						allRowSolutionsOnly.add(row);
				allRow = allRowSolutionsOnly.toArray(new Line[allRowSolutionsOnly.size()]);
			}
			
			allRows[i] = allRow;
		}
		
		return allRows;
	}
	
	// TODO test!
	public Line[][] getAllColumns() {
		Line[][] allColumns = new Line[Board.lineSize][];
		Line[] columns = boards.get(0).board.getColumns();
		for (int i = 0; i < Board.lineSize; i++) {
			allColumns[i] = Library.lineLibrary.getLines(columns[i].getLineFilling()).toArray(new Line[0]);
		}
		return allColumns;
	}
	
	public Line[][] getAllRows() {
		Line[][] allRows = new Line[Board.lineSize][];
		Line[] rows = boards.get(0).board.getRows();
		for (int i = 0; i < Board.lineSize; i++) {
			allRows[i] = Library.lineLibrary.getLines(rows[i].getLineFilling()).toArray(new Line[0]);
		}
		return allRows;
	}
	
	public double getAverageBranchFactor() {
		int nBranches = 0;
		for (ClusterBoard board : boards)
			nBranches += board.board.getReachableBoards().size();
		return ((double)nBranches) / boards.size();
	}
	
	public int getMaxDistance() {
		return boards.get(boards.size() - 1).distance;
	}
	
	
	public int getDistanceOf(Board board) {
		for (int i = 0; i < boards.size(); i++)
			if (boards.get(i).equals(board))
				return boards.get(i).distance;
		return -1;
	}
	
	public ArrayList<Board> getSolutions() {
		ArrayList<Board> solutions = new ArrayList<Board>();
		
		for (ClusterBoard board : boards)
			if (board.board.isSolution())
				solutions.add(board.board);
		
		return solutions;
	}
	
	public ArrayList<Board> getPathToSolution(Board board) {
		ArrayList<Board> path = new ArrayList<Board>();
		path.add(board);
		
		ClusterBoard clusterBoard = null;
		for (ClusterBoard otherBoard : boards)
			if (otherBoard.equals(board))
				clusterBoard = otherBoard;
		
		while ((clusterBoard = clusterBoard.parentBoard) != null)
			path.add(clusterBoard.board);
		
		return path;
	}
	
	public ArrayList<Board> getBoards() {
		ArrayList<Board> boards = new ArrayList<Board>(this.boards.size());
		for (ClusterBoard clusterBoard : this.boards)
			boards.add(clusterBoard.board);
		return boards;
	}
	
	public ArrayList<Board> getBoardsAtMaxDistance() {
		return getBoardsAtDistance(getMaxDistance());
	}
	
	public ArrayList<Board> getBoardsAtDistance(int distance) {
		ArrayList<Board> boardsAtDistance = new ArrayList<Board>();
		
		boolean distanceFound = false;
		for (int i = boards.size() - 1; i >= 0; i--) {
			if (boards.get(i).distance == distance) {
				distanceFound = true;
				boardsAtDistance.add(boards.get(i).board);
			}
			else if (distanceFound)
				break;
		}
		
		return boardsAtDistance;
	}
	
	public String getClusterFilling() {
		return clusterFilling;
	}
	
	public int size() {
		return boards.size();
	}
}
