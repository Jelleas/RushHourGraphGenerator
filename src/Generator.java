import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import databaseLink.SqlLink;

import library.ClusterLibrary;
import library.Library;
import board.*;

public final class Generator {
	public static Board getHardestBoard() {
		Line[] rows = new Line[Board.lineSize];
		Line[] columns = new Line[Board.lineSize];
		
		rows[0] 	= Library.lineLibrary.getLine(new boolean[] {true, true, false, false, false, false});
		rows[1] 	= Library.lineLibrary.getLine(new boolean[] {false, true, false, false, false, false});
		rows[2] 	= Library.lineLibrary.getLine(new boolean[] {false, false, true, false, false, false});
		rows[3] 	= Library.lineLibrary.getLine(new boolean[] {true, false, false, false, false, false});
		rows[4] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, true, false});
		rows[5] 	= Library.lineLibrary.getLine(new boolean[] {false, false, true, false, true, false});
		
		columns[0] 	= Library.lineLibrary.getLine(new boolean[] {false, true, false, false, false, false});
		columns[1] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, true, false});
		columns[2] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, true, false, false});
		columns[3] 	= Library.lineLibrary.getLine(new boolean[] {true, false, false, false, false, false});
		columns[4] 	= Library.lineLibrary.getLine(new boolean[] {true, true, false, false, false, false});
		columns[5] 	= Library.lineLibrary.getLine(new boolean[] {true, true, false, false, false, false});
		
		return new Board(rows, columns);
	}
	
	public static Board getBoard1() {
		Line[] rows = new Line[Board.lineSize];
		Line[] columns = new Line[Board.lineSize];
		
		rows[0] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, true, false, false});
		rows[1] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, false, false});
		rows[2] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, true, false, false});
		rows[3] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, true, false});
		rows[4] 	= Library.lineLibrary.getLine(new boolean[] {false, true, false, false, false, false});
		rows[5] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, true, false});
		
		columns[0] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, true, false});
		columns[1] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, false, false});
		columns[2] 	= Library.lineLibrary.getLine(new boolean[] {true, true, false, false, false, false});
		columns[3] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, true, true, false});
		columns[4] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, false, false});
		columns[5] 	= Library.lineLibrary.getLine(new boolean[] {true, true, false, false, false, false});
		
		return new Board(rows, columns);
	}
	
	public static Board getBoard2() {
		Line[] rows = new Line[Board.lineSize];
		Line[] columns = new Line[Board.lineSize];
		
		rows[0] 	= Library.lineLibrary.getLine(new boolean[] {false, false, true, false, true, false});
		rows[1] 	= Library.lineLibrary.getLine(new boolean[] {false, true, false, true, false, false});
		rows[2] 	= Library.lineLibrary.getLine(new boolean[] {false, false, true, false, false, false});
		rows[3] 	= Library.lineLibrary.getLine(new boolean[] {true, false, true, false, false, false});
		rows[4] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, true, false});
		rows[5] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, true, false});
		
		columns[0] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, true, false});
		columns[1] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, false, false});
		columns[2] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, false, false});
		columns[3] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, true, false});
		columns[4] 	= Library.lineLibrary.getLine(new boolean[] {false, false, true, false, false, false});
		columns[5] 	= Library.lineLibrary.getLine(new boolean[] {false, true, true, false, false, false});
		
		return new Board(rows, columns);
	}
	
	public static Board getBoard3() {
		Line[] rows = new Line[Board.lineSize];
		Line[] columns = new Line[Board.lineSize];
		
		rows[0] 	= Library.lineLibrary.getLine(new boolean[] {false, true, false, true, true, false});
		rows[1] 	= Library.lineLibrary.getLine(new boolean[] {false, true, false, false, true, false});
		rows[2] 	= Library.lineLibrary.getLine(new boolean[] {true, false, false, false, false, false});
		rows[3] 	= Library.lineLibrary.getLine(new boolean[] {true, false, false, true, false, false});
		rows[4] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, true, false});
		rows[5] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, false, false});
		
		columns[0] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, true, false});
		columns[1] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, false, false});
		columns[2] 	= Library.lineLibrary.getLine(new boolean[] {false, false, true, false, true, false});
		columns[3] 	= Library.lineLibrary.getLine(new boolean[] {false, true, false, false, false, false});
		columns[4] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, false, false});
		columns[5] 	= Library.lineLibrary.getLine(new boolean[] {false, false, true, false, false, false});
		
		return new Board(rows, columns);
	}
	
	public static Board getExampleBoard1() {
		Line[] rows = new Line[Board.lineSize];
		Line[] columns = new Line[Board.lineSize];
		
		for (int i = 0; i < Board.lineSize; i++) {
			rows[i] = Library.lineLibrary.getLine(new boolean[] {false, false, false, false, false, false});
			columns[i] = Library.lineLibrary.getLine(new boolean[] {false, false, false, false, false, false});
		}
		
		rows[2] = Library.lineLibrary.getLine(new boolean[] {false, true, false, false, false, false});
		
		return new Board(rows, columns);
	}
	
	public static void speedTestReachableBoards(Board board, long iterations) {
		ArrayList<Board> reachableBoards = null;
		
		long start = System.currentTimeMillis();
		for (long i = 0; i < iterations; i++) 
			reachableBoards = board.getReachableBoards();
		long end = System.currentTimeMillis();
		
		board.print();
		for (Board reachableBoard : reachableBoards)
			reachableBoard.print();
		
		System.out.println("Number of iterations              " + iterations);
		System.out.println("Time taken in millisec: " + (end - start));
	}
	
	public static void speedTestClusterExpand(Board board, long iterations) {
		Cluster cluster = new Cluster(board);
		//cluster.expand();
		//ArrayList<Board> solutions = cluster.getSolutions();
		ArrayList<Board> solutions = ClusterLibrary.getAllSolutions(new Cluster(board));
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < iterations; i++) {
			cluster = new Cluster(solutions);
			cluster.expand();
		}
		long end = System.currentTimeMillis();

		System.out.println("Number of iterations              " + iterations);
		System.out.println("Time taken in millisec:           " + (end - start));
		System.out.println("Size of cluster:                  " + cluster.size());
		System.out.println("Max distance in cluster:          " + cluster.getMaxDistance());
		System.out.println("Number of solutions in cluster:   " + cluster.getSolutions().size());
		System.out.println("Distance to original board:       " + cluster.getDistanceOf(board));
		System.out.println("Average branch factor of cluster: " + cluster.getAverageBranchFactor());
		
		/*System.out.println();
		System.out.println("\n============= ORIGINAL BOARD =============\n");
		board.prettyPrint();
		System.out.println();
		System.out.println("==========================================");
		
		System.out.println();
		System.out.println("============ PATH TO SOLUTION ============");
		System.out.println();
		for (Board boardOnPath : cluster.getPathToSolution(board)) {
			boardOnPath.prettyPrint();
			System.out.println();
		}
		System.out.println("==========================================");
		
		System.out.println();
		System.out.println("== BOARDS AT MAX DISTANCE FROM SOLUTION ==");
		System.out.println();
		for (Board boardAtMaxDistance : cluster.getBoardsAtMaxDistance()) {
			boardAtMaxDistance.prettyPrint();
			System.out.println();
		}
		System.out.println("=========================================="); */
	}
	
	public static void speedTestSolve(Board board, int iterations) {
		Cluster cluster = null;
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < iterations; i++) {
			cluster = new Cluster(board);
			cluster.solve();
		}
		long end = System.currentTimeMillis();

		System.out.println("Number of iterations              " + iterations);
		System.out.println("Time taken to solve in millisec:  " + (end - start));
		System.out.println("Found solution at depth:          " + cluster.getMaxDistance());
		System.out.println("Number of nodes searched:         " + cluster.size());
	}
	
	public static void speedTestFindSolutions(Board board, int iterations) {
		ArrayList<Board> solutions = null;
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < iterations; i++)
			solutions = ClusterLibrary.getAllSolutions(new Cluster(board));
		long end = System.currentTimeMillis();
		
		System.out.println("Number of iterations              " + iterations);
		System.out.println("Time taken in millisec:           " + (end - start));
		System.out.println("Number of solutions found:        " + solutions.size());
	}
	
	public static void main(String[] args) {
		Library.init();
		
		try {
			SqlLink link = new SqlLink();
			link.insertQuery("INSERT INTO test VALUES(2)");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Board board = Generator.getHardestBoard();
		//Board board = Generator.getBoard1();
		//Board board = Generator.getBoard2();
		//Board board = Generator.getBoard3();
		
		//Generator.speedTestClusterExpand(board, 1);
		//Generator.speedTestSolve(board, 1);
		Generator.speedTestFindSolutions(board, 1000);
	}
}