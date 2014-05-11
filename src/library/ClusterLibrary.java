package library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import board.*;

public final class ClusterLibrary {
	private final HashMap<String, Cluster> clusterMap;
	private final LinkedList<Cluster> clusterQueue;
	private static long numBoards = 0; // TODO remove
	
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
	
	public ArrayList<Board> getBoards(Cluster cluster) {
		return getBoards(cluster.getAllRows(), cluster.getAllColumns());
	}
	
	private ArrayList<Board> getBoards(Line[][] allRows, Line[][] allColumns) {
		ArrayList<Board> boards = new ArrayList<Board>();
		generateBoards(boards, allRows, allColumns, new Line[Board.lineSize], 0);
		return boards;
	}
	
	private static void generateBoards(ArrayList<Board> boards, Line[][] allRows, Line[][] allColumns,
			Line[] rowSelection, int depth) {
		if (depth == Board.lineSize)
			generateBoards(boards, allColumns, rowSelection);
		else
			for (Line row : allRows[depth]) {
				rowSelection[depth] = row;
				generateBoards(boards, allRows, allColumns, rowSelection, depth + 1);
			}
	}
	
	private static void generateBoards(ArrayList<Board> boards, Line[][] allColumns, Line[] rowSelection) {
		ArrayList<ArrayList<Line>> allColumnsThatFit = new ArrayList<ArrayList<Line>>(Board.lineSize);
		for (int i = 0; i < Board.lineSize; i++) {
			ArrayList<Line> columnsThatFit = new ArrayList<Line>();
			
			for (Line column : allColumns[i]) {
				boolean doesFit = true;
				
				for (int j = 0; j < Board.lineSize; j++)
					if (rowSelection[j].occupationLine[i] && column.occupationLine[j]) {
						doesFit = false;
						break;
					}
				
				if (doesFit)
					columnsThatFit.add(column);
			}
			
			if (!columnsThatFit.isEmpty())
				allColumnsThatFit.add(columnsThatFit);
			else
				return;
		}
		
		generateBoards(boards, allColumnsThatFit, rowSelection, new Line[Board.lineSize], 0);
	}
	
	private static void generateBoards(ArrayList<Board> boards,
			ArrayList<ArrayList<Line>> allColumnsThatFit,
			Line[] rowSelection, Line[] columnSelection, int depth) {
		if (depth == Board.lineSize) {
			Line[] rows = new Line[Board.lineSize];
			System.arraycopy(rowSelection, 0, rows, 0, Board.lineSize);
			Line[] columns = new Line[Board.lineSize];
			System.arraycopy(columnSelection, 0, columns, 0, Board.lineSize);
			boards.add(new Board(rows, columns));
		} else {
			for (Line column : allColumnsThatFit.get(depth)) {
				columnSelection[depth] = column;
				generateBoards(allColumnsThatFit, rowSelection, columnSelection, depth + 1);
			}
		}
	}
	
	/* TODO remove?
	private void add(Board board) {
		Cluster cluster = clusterMap.get(board.getBoardFilling());
		if (cluster == null) {
			cluster = new Cluster(board);
			add(cluster);
		}
		cluster.add(board);
	}*/
	
	public synchronized Cluster getOne() {
		Cluster cluster = clusterQueue.pollFirst();
		if (cluster != null)
			clusterMap.remove(cluster.getClusterFilling());
		return cluster;
	}
	
	protected void init() {
		//TempBoard tempBoard = new TempBoard();
		//tempBoard.add(0, 4, 2, 2, false);
		//ArrayList<Board> boards = new ArrayList<Board>();
		//generateBoards(boards, tempBoard, 1);
		
		//for (Board board : boards)
		//	add(board);
		
		generateBoards();
	}
	
	private static void generateBoards() {
		Line[] allLines = Library.lineLibrary.getLines().toArray(new Line[Library.lineLibrary.size()]);
		ArrayList<Line> goalLinesList = Library.lineLibrary.getGoalLines();
		Line[] goalLines = goalLinesList.toArray(new Line[goalLinesList.size()]);
		
		Line[][] rows = new Line[Board.lineSize][];
		Line[][] columns = new Line[Board.lineSize][];
		
		for (int i = 0; i < Board.lineSize; i++) {
			rows[i] = allLines;
			columns[i] = allLines;
		}
		
		rows[(Board.lineSize - 1) / 2] = goalLines;
		
		generateBoards(rows, columns, new Line[Board.lineSize], 0);
		System.out.println(ClusterLibrary.numBoards);
	}
	
	private static void generateBoards(Line[][] allRows, Line[][] allColumns, Line[] rowSelection, int depth) {
		if (depth == Board.lineSize)
			generateBoards(allColumns, rowSelection);
		else
			for (Line row : allRows[depth]) {
				rowSelection[depth] = row;
				generateBoards(allRows, allColumns, rowSelection, depth + 1);
			}
	}
	
	private static void generateBoards(Line[][] allColumns, Line[] rowSelection) {
		ArrayList<ArrayList<Line>> allColumnsThatFit = new ArrayList<ArrayList<Line>>(Board.lineSize);
		for (int i = 0; i < Board.lineSize; i++) {
			ArrayList<Line> columnsThatFit = new ArrayList<Line>();
			
			for (Line column : allColumns[i]) {
				boolean doesFit = true;
				
				for (int j = 0; j < Board.lineSize; j++)
					if (rowSelection[j].occupationLine[i] && column.occupationLine[j]) {
						doesFit = false;
						break;
					}
				
				if (doesFit)
					columnsThatFit.add(column);
			}
			
			if (!columnsThatFit.isEmpty())
				allColumnsThatFit.add(columnsThatFit);
			else
				return;
		}
		
		generateBoards(allColumnsThatFit, rowSelection, new Line[Board.lineSize], 0);
	}
	
	private static void generateBoards(ArrayList<ArrayList<Line>> allColumnsThatFit,
			Line[] rowSelection, Line[] columnSelection, int depth) {
		if (depth == Board.lineSize) {
			// TODO write to db
			numBoards++;
			if (numBoards % 100000000 == 0)
				System.out.println(numBoards);
		} else {
			for (Line column : allColumnsThatFit.get(depth)) {
				columnSelection[depth] = column;
				generateBoards(allColumnsThatFit, rowSelection, columnSelection, depth + 1);
			}
		}
	}
	
	/*
	public void generateBoards2() {
		Line[] allLines = new Line[Library.lineLibrary.size()];
		allLines = Library.lineLibrary.getLines().toArray(allLines);
		
		ArrayList<Line> goalLinesList = Library.lineLibrary.getGoalLines();
		Line[] goalLines = new Line[goalLinesList.size()];
		goalLines = goalLinesList.toArray(goalLines);
		
		//goalLines = new Line[] {Library.lineLibrary.getLine(new boolean[] {false, false, false, false, true, false})};
		
		generateBoards(new Line[Board.lineSize], new Line[Board.lineSize], allLines, goalLines, 0);
		System.out.println(numBoards);
	}
		
	private void generateBoards(Line[] rows, Line[] columns, Line[] allLines, Line[] goalLines, int depth) {
		if (depth == Board.lineSize) { // basecase
			numBoards++; // TODO remove
			if (numBoards % 100000000 == 0)
				System.out.println(numBoards);
			System.out.println(numBoards);
			for (Line row : rows) {
				for (boolean elem : row.occupationLine)
					System.out.print((elem ? 1 : 0) + " ");
				System.out.println();
			}
			
			System.out.println();
			
			for (int i = 0; i < Board.lineSize; i++) {
				for (Line column : columns) {	
					System.out.print((column.occupationLine[i] ? 1 : 0) + " ");
				}
				System.out.println();
			}
			
			System.out.println("\n");
			
			for (int i = 0; i < Board.lineSize; i++) {
				Line row = rows[i];
				Line column = columns[i];
				
				for (int j = 0; j < Board.lineSize; j++) {
					if ((row.occupationLine[j] && columns[j].occupationLine[i]) || (column.occupationLine[j] && rows[j].occupationLine[i])) {
						System.out.println(i + " " + j);
					}
				}
			}
			return; // TODO write to db!
		}
		
		Line[] rowLines = depth == (Board.lineSize - 1) / 2 ? goalLines : allLines;
		
		boolean doesFit;
		for (Line rowLine : rowLines) {
			rows[depth] = rowLine;
			
			doesFit = true;
			for (int i = 0; i < depth; i++) // check if row fits
				if (rowLine.occupationLine[i] && columns[i].occupationLine[depth]) {
					doesFit = false;
					break;
				}
			
			if (doesFit) // if row does fit, try all columns
				for (Line columnLine : allLines) {
					columns[depth] = columnLine;
					
					doesFit = true;
					for (int i = 0; i <= depth; i++) // check if column fits
						if (columnLine.occupationLine[i] && rows[i].occupationLine[depth]) {
							doesFit = false;
							break;
						}
					if (doesFit) // if column does fit, go to next depth
						generateBoards(rows, columns, allLines, goalLines, depth + 1);
				}
		}
	}
	
	
	public void generateBoards3(ArrayList<Board> boards, TempBoard board, int depth) {
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
	} */
}
