package board;

import java.util.ArrayList;
import java.util.Arrays;
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
	
	public final static int unsolvableDistance = 127;
	
	private int id; 
	private String clusterFilling;
	private HashSet<ClusterBoard> boardSet;
	private ArrayList<ClusterBoard> boards;
	private ArrayList<Board> solutions;
	
	public Cluster(Board board) {
		this(board, -1);
	}
	
	public Cluster(Board board, int id) {
		this(Arrays.asList(new Board[] {board}), id);
	}
	
	public Cluster(List<Board> solutionBoards) {
		this(solutionBoards, -1);
	}
	
	public Cluster(List<Board> solutionBoards, int id) {
		this.boardSet = new HashSet<ClusterBoard>();
		this.boards = new ArrayList<ClusterBoard>();
		this.solutions = new ArrayList<Board>();
		
		for (Board board : solutionBoards)
			add(new ClusterBoard(board, null, 0));
		
		this.clusterFilling = solutionBoards.get(0).getBoardFilling();
		
		this.id = id;
	}
	
	private boolean add(ClusterBoard board) {
		boolean success = boardSet.add(board);
		if (success) {
			boards.add(board);
			if (board.board.isSolution())
				solutions.add(board.board);
		}
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
	
	public double getAverageBranchFactor() {
		int nBranches = 0;
		for (ClusterBoard board : boards)
			nBranches += board.board.getReachableBoards().size();
		return ((double)nBranches) / boards.size();
	}
	
	public int getMaxDistance() {
		if (getNumSolutions() == 0)
			return Cluster.unsolvableDistance;
		
		return boards.get(boards.size() - 1).distance;
	}
	
	public int getDistanceOf(Board board) {
		for (int i = 0; i < boards.size(); i++)
			if (boards.get(i).equals(board))
				return boards.get(i).distance;
		return -1;
	}
	
	public ArrayList<Board> getSolutions() {
		return solutions;
	}
	
	public int getNumSolutions() {
		return solutions.size();
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
	
	public ArrayList<Board> getPath(Board board) {
		ArrayList<Board> path = new ArrayList<Board>();
		
		for (ClusterBoard clusterBoard : boards) {
			if (clusterBoard.equals(board)) {
				
				while (clusterBoard.parentBoard != null) {
					path.add(clusterBoard.board);
					clusterBoard = clusterBoard.parentBoard;
				}
				path.add(clusterBoard.board);
				
				return path;
			}
		}
		
		return path;
	}
	
	public int[] getRowFillingIds() {
		int[] rowFillingIds = new int[Board.lineSize];
		Line[] rows = boards.get(0).board.getRows();
		
		for (int i = 0; i < Board.lineSize; i++)
			rowFillingIds[i] = rows[i].getFillingId();
		
		return rowFillingIds;
	}
	
	public int[] getColumnFillingIds() {
		int[] columnFillingIds = new int[Board.lineSize];
		Line[] columns = boards.get(0).board.getColumns();
		
		for (int i = 0; i < Board.lineSize; i++)
			columnFillingIds[i] = columns[i].getFillingId();
		
		return columnFillingIds;
	}
	
	public int getId() {
		if (id < 0)
			id = Library.link.clusterLink.getId(this);
		return id;
	}
	
	public int getNumberOfVehiclesOnColumns() {
		return boards.get(0).board.getNumberOfVehiclesOnColumns();
	}
	
	public int getNumberOfVehiclesOnRows() {
		return boards.get(0).board.getNumberOfVehiclesOnRows();
	}
	
	public int getNumberOfVehicles() {
		return boards.get(0).board.getNumberOfVehicles();
	}
	
	public int getNumberOfCars() {
		return boards.get(0).board.getNumberOfCars();
	}
	
	public int getNumberOfTrucks() {
		return boards.get(0).board.getNumberOfTrucks();
	}
	
	public double calcHardnessRatio(int hardnessBorder) {
		double numberBoardsBelowBorder = 0;
		for (int distance = 0; distance <= hardnessBorder; distance++)
			numberBoardsBelowBorder += getBoardsAtDistance(distance).size();
		
		double numberBoardsAboveOrOnBorder = 0;
		for (int distance = hardnessBorder, maxDistance = getMaxDistance(); distance <= maxDistance; distance++)
			numberBoardsAboveOrOnBorder += getBoardsAtDistance(distance).size();
		
		return numberBoardsAboveOrOnBorder / numberBoardsBelowBorder;
	}
	
	public double calcSquaredErrorForSizePerDistanceFromAverage() {
		int maxDistance = getMaxDistance();
		
		double average = 0;
		for (int distance = 0; distance <= maxDistance; distance++)
			average += getBoardsAtDistance(distance).size();
		average /= maxDistance + 1;
		
		double squaredError = 0;
		for (int distance = 0; distance <= maxDistance; distance++)
			//if (average - getBoardsAtDistance(distance).size() > 0) // TODO remove hack
			squaredError += Math.pow(average - getBoardsAtDistance(distance).size(), 2);
		
		return squaredError;
	}
	
	public static Board getSolution(Board board) {
		Cluster cluster = new Cluster(board);
		cluster.solve();
		return cluster.getSolutions().get(0);
	}
	
	public boolean contains(Board board) {
		return boardSet.contains(new ClusterBoard(board, null, 0));
	}
	
	public boolean equals(Object o) {
		if (o instanceof Cluster)
			return this.getClusterFilling().equals(((Cluster)o).getClusterFilling());
		return false;
	}
	
	public int hashCode() {
		return this.getClusterFilling().hashCode();
	}
}
