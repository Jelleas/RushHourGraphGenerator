package library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import board.*;

public class ClusterLibrary {
	private HashMap<String, Cluster> clusterMap;
	private LinkedList<Cluster> clusterQueue;
	
	protected ClusterLibrary() {
		clusterMap = new HashMap<String, Cluster>();
		clusterQueue = new LinkedList<Cluster>();
	}
	
	private boolean add(Cluster cluster) {
		if (clusterMap.containsKey(cluster.getClusterFilling()))
			return false;
		
		clusterMap.put(cluster.getClusterFilling(), cluster);
		clusterQueue.add(cluster);
		return true;
	}
	
	private void add(Board board) {
		Cluster cluster = clusterMap.get(board.getBoardFilling());
		if (cluster == null) {
			cluster = new Cluster(board);
			add(cluster);
		}
		cluster.add(board);
	}
	
	public synchronized Cluster getOne() {
		Cluster cluster = clusterQueue.getFirst();
		clusterMap.remove(cluster.getClusterFilling());
		return cluster;
	}
	
	protected void init() {
		TempBoard tempBoard = new TempBoard();
		tempBoard.add(0, 4, 2, 2, false);
		ArrayList<Board> boards = new ArrayList<Board>();
		generateBoards(boards, tempBoard, 1);
		
		for (Board board : boards)
			add(board);
	}
	
	public void generateBoards(ArrayList<Board> boards, TempBoard board, int depth) {
		//Line[][] lines = board.getLines();
		//boards.add(new Board(lines[0], lines[1])); // TODO reintroduce.
		
		for (int i = 0; i < Board.lineSize; i++) // over x
			for (int j = 0; j < Board.lineSize; j++) // over y
				for (int k = Board.minCarSize; k < Board.maxCarSize; k++) // over cars
					for (int l = 0; l < 2; l++) { // over orientation
						TempBoard tempBoard = board.add(depth, i, j, k, l == 0);
						if (tempBoard != null)
							generateBoards(boards, tempBoard, depth + 1);
					}
	}

	static int numBoards = 0; // TODO remove
	class TempBoard {
		int[][] board;
		
		public TempBoard() {
			this.board = new int[Board.lineSize][Board.lineSize];
			numBoards++;
		}
		
		public TempBoard(int[][] board) {
			this.board = board;
		}
		
		public TempBoard add(int num, int x, int y, int length, boolean isVertical) {
			int[][] boardCopy;
			
			if (isVertical) {
				for (int i = y; i < y + length; i++)
					if (i >= board.length || board[x][i] != 0)
						return null;
				
				boardCopy = new int[Board.lineSize][];
				for(int i = 0; i < Board.lineSize; i++)
				    boardCopy[i] = board[i].clone();
				
				for (int i = y; i < y + length; i++)
					boardCopy[x][i] = num;
			} else {
				for (int i = x; i < x + length; i++)
					if (i >= board.length || board[i][y] != 0)
						return null;
				
				boardCopy = new int[Board.lineSize][];
				for(int i = 0; i < Board.lineSize; i++)
				    boardCopy[i] = board[i].clone();
				
				for (int i = x; i < x + length; i++)
					boardCopy[i][y] = num;
			}
			
			numBoards++; // TODO remove
			if (numBoards % 1000000 == 0)
				System.out.println(numBoards);

			return new TempBoard(boardCopy);
		}
		
		public Line[][] getLines() {
			boolean[] horizontalLine = new boolean[Board.lineSize];
			boolean[] verticalLine = new boolean[Board.lineSize];
			
			Line[] horizontalLines = new Line[Board.lineSize];
			Line[] verticalLines = new Line[Board.lineSize];
			
			for (int i = 0; i < Board.lineSize; i++) {
				for (int j = 0; j < Board.lineSize; j++) {
					if (board[i][j] != 0)
						verticalLine[j] = true;
					else if (j > 0)
						verticalLine[j - 1] = false;
					
					if (board[j][i] != 0)
						horizontalLine[j] = true;
					else if (j > 0)
						horizontalLine[j - 1] = false;
				}
				
				horizontalLines[i] = Library.lineLibrary.getLine(horizontalLine);
				verticalLines[i] = Library.lineLibrary.getLine(verticalLine);
			}
			
			return new Line[][] {horizontalLines, verticalLines};
		}
	}
}
