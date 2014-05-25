package library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import databaseLink.Link;

import board.*;

public final class ClusterLibrary {
	private final HashMap<String, Cluster> clusterMap;
	private final LinkedList<Cluster> clusterQueue;
	private static long numBoards = 0; // TODO remove
	
	protected ClusterLibrary() {
		clusterMap = new HashMap<String, Cluster>();
		clusterQueue = new LinkedList<Cluster>();
	}
	
	private boolean add(Board board) {
		return add(new Cluster(board));
	}
	
	private boolean add(Cluster cluster) {
		if (clusterMap.containsKey(cluster.getClusterFilling()))
			return false;
		
		clusterMap.put(cluster.getClusterFilling(), cluster);
		clusterQueue.add(cluster);
		return true;
	}
	
	public static ArrayList<Board> getAllBoards(Board board) {
		return getBoards(board.getAllRows(), board.getAllColumns());
	}
	
	public static ArrayList<Board> getAllSolutions(Board board) {
		return getBoards(board.getAllRowsSolutionsOnly(), board.getAllColumns());
	}
	
	private static ArrayList<Board> getBoards(Line[][] allRows, Line[][] allColumns) {
		ArrayList<Board> boards = new ArrayList<Board>();
		getBoards(boards, allRows, allColumns, new Line[Board.lineSize], 0);
		return boards;
	}
	
	private static void getBoards(ArrayList<Board> boards, Line[][] allRows, Line[][] allColumns,
			Line[] rowSelection, int depth) {
		if (depth == Board.lineSize)
			getBoards(boards, allColumns, rowSelection);
		else
			for (Line row : allRows[depth]) {
				rowSelection[depth] = row;
				getBoards(boards, allRows, allColumns, rowSelection, depth + 1);
			}
	}
	
	private static void getBoards(ArrayList<Board> boards, Line[][] allColumns, Line[] rowSelection) {
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
		
		getBoards(boards, allColumnsThatFit, rowSelection, new Line[Board.lineSize], 0);
	}
	
	private static void getBoards(ArrayList<Board> boards,
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
				getBoards(boards, allColumnsThatFit, rowSelection, columnSelection, depth + 1);
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
	}
	
	public void fillDatabase(Link link) {
		start = System.currentTimeMillis();
		startFromId = link.clusterLink.getMax("id");
		generateClusters(link);
		System.out.println(Node.numClusters + " " + Node.numNewClusters);
	}
	
	static long numClusters = 0; // TODO remove
	static long start;
	long startFromId;
	
	
	private void generateClusters(Link link) {
		List<List<Line>> goalLines = Library.lineLibrary.getGoalLinesByLineFilling();
		
		List<List<Line>> rows = new ArrayList<List<Line>>(Board.lineSize);
		for (int i = 0; i < Board.lineSize; i++)
			rows.add(null);
		
		Line[] columns = new Line[Board.lineSize];
		
		generateClusters(link, Library.lineLibrary.getAllLineFillings(), rows, goalLines, columns, 0);
	}

	private void generateClusters(Link link, List<String> lineFillings, List<List<Line>> allRows,
			List<List<Line>> goalLinesPerFilling, Line[] columns, int depth) {
		if (depth == (Board.lineSize - 1) / 2) {
			for (List<Line> goalLines : goalLinesPerFilling) {
				allRows.set(depth, goalLines);
				generateClusters(link, lineFillings, allRows, goalLinesPerFilling, columns, depth + 1);
			}
		}
		else if (depth < Board.lineSize) {
			for (String lineFillingRow : lineFillings) {
				allRows.set(depth, Library.lineLibrary.getLines(lineFillingRow));
				generateClusters(link, lineFillings, allRows, goalLinesPerFilling, columns, depth + 1);
			}
		} 
		else if (depth == Board.lineSize) {
			//generateClustersColumns(link, lineFillings, lineFillings, allRows, columns, 0);
			Node node = new Node(lineFillings);
			node.kill(allRows, new ArrayList<Line>(Board.lineSize));
		} 	
	}
	static long numSolutions = 0;
	/*
	public static List<String> generateClustersColumns(Link link, List<String> allColumnFillings, List<String> columnFillings,
			List<List<Line>> allRows, Line[] columns, int depth) {
		if (depth == Board.lineSize) {
			Line[] rows = new Line[Board.lineSize];
			for (int i = 0; i < Board.lineSize; i++)
				rows[i] = allRows.get(i).get(0);
			
			Board board = new Board(rows, columns);
			//if (!link.clusterLink.contains(new Cluster(board))) {
				//Cluster cluster = new Cluster(getAllSolutions(board));
				//cluster.expand();
				//link.clusterLink.add(cluster);
			//}
			numSolutions += getAllSolutions(board).size();
			numClusters++;
			if (numClusters % 10000 == 0) {
				System.out.println("numClusters: " + numClusters + " numSolutions: " + numSolutions + " Time taken: " + (System.currentTimeMillis() - start));

				//System.out.println(allColumnFillings.size());
			}
			return null;
		}
		
		List<String> columnFillingsThatDoNotFit = new ArrayList<String>();

		for (String lineFillingColumn : columnFillings) {
			List<String> tempColumnFillings = allColumnFillings;
			boolean noneFit = true;
			
			for (Line column : Library.lineLibrary.getLines(lineFillingColumn)) {
				List<List<Line>> allRowsTemp = getLinesThatFit(allRows, column, depth);
				
				if (allRowsTemp != null) {
					noneFit = false;
					columns[depth] = column;
					tempColumnFillings = generateClustersColumns(link, allColumnFillings, tempColumnFillings, allRowsTemp, columns, depth + 1);
					
					if (depth + 1 == Board.lineSize)
						break;
				}
			}
			
			if (noneFit) 
				columnFillingsThatDoNotFit.add(lineFillingColumn);
		}
		
		return columnFillingsThatDoNotFit;
	}
	*/

	/*
	public static void generateClusters(Link link) {
		generateClusters(link, Library.lineLibrary.getGoalLineFillings(), new String[Board.lineSize], 0);
	}
	
	private static void generateClusters(Link link, List<String> goalLineFillings, String[] rowLineFillings, int depth) {
		if (depth == Board.lineSize) {
			HashMap<String, Cluster> clusterMap = generateClusters(rowLineFillings);
			
			for (String key : clusterMap.keySet()) {
				Cluster cluster = clusterMap.get(key);
				if (!link.clusterLink.contains(cluster)) {
					cluster.expand();
					link.clusterLink.add(cluster);
				}
			}
			return;
		}
		
		List<String> lineFillings = depth == (Board.lineSize - 1) / 2 ? goalLineFillings : Library.lineLibrary.getAllLineFillings();
	
		for (String lineFilling : lineFillings) {
			rowLineFillings[depth] = lineFilling;
			generateClusters(link, goalLineFillings, rowLineFillings, depth + 1);
		}
	
	}
	
	private static HashMap<String, Cluster> generateClusters(String[] rowLineFillings) {
		HashMap<String, Cluster> clusterMap = new HashMap<String, Cluster>();
		
		List<List<Line>> allRows = new ArrayList<List<Line>>(Board.lineSize);
		List<List<Line>> allColumns = new ArrayList<List<Line>>(Board.lineSize);
		
		for (int i = 0; i < Board.lineSize; i++) {
			allRows.add(Library.lineLibrary.getLines(rowLineFillings[i]));
			allColumns.add(Library.lineLibrary.getLines());
		}
		
		generateClusters(clusterMap, allRows, allColumns, new Line[Board.lineSize], 0);
		
		return clusterMap;
	}
	
	private static void generateClusters(HashMap<String, Cluster> clusterMap, List<List<Line>> allRows, List<List<Line>> allColumns, Line[] rowSelection, int depth) {
		if (depth == Board.lineSize)
			generateClusters(clusterMap, allColumns, rowSelection);
		else
			for (Line row : allRows.get(depth)) {
				rowSelection[depth] = row;
				generateClusters(clusterMap, allRows, allColumns, rowSelection, depth + 1);
			}
	}
	
	private static void generateClusters(HashMap<String, Cluster> clusterMap, List<List<Line>> allColumns, Line[] rowSelection) {
		List<List<Line>> allColumnsThatFit = new ArrayList<List<Line>>(Board.lineSize);
		for (int i = 0; i < Board.lineSize; i++) {
			ArrayList<Line> columnsThatFit = new ArrayList<Line>();

			for (Line column : allColumns.get(i)) {
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

		generateClusters(clusterMap, allColumnsThatFit, rowSelection, new Line[Board.lineSize], 0);
	}
	
	private static void generateClusters(HashMap<String, Cluster> clusterMap, List<List<Line>> allColumnsThatFit,
			Line[] rowSelection, Line[] columnSelection, int depth) {
		if (depth == Board.lineSize) {
			// TODO write to db
			Board board = new Board(rowSelection, columnSelection);
			Cluster cluster = clusterMap.get(board.getBoardFilling());
			if (cluster == null) {
				cluster = new Cluster(board);
				clusterMap.put(board.getBoardFilling(), cluster);
				numClusters++;
				
				if (numClusters % 25000 == 0)
					System.out.println("numClusters: " + numClusters + " Time taken: " + (System.currentTimeMillis() - start));
			} else {
				cluster.addNewSolution(board);
			}
			
			numBoards++;
			if (numBoards % 100000000 == 0)
				System.out.println(numBoards);
		} else {
			for (Line column : allColumnsThatFit.get(depth)) {
				columnSelection[depth] = column;
				generateClusters(clusterMap, allColumnsThatFit, rowSelection, columnSelection, depth + 1);
			}
		}
	}*/
	
	public static void generateBoards() {
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
