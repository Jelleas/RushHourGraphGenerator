import java.util.ArrayList;

import library.Library;
import board.*;

public class Generator {
	
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
	
	public static Board getExampleBoard1() {
		Line[] rows = new Line[Board.lineSize];
		Line[] columns = new Line[Board.lineSize];
		
		for (int i = 0; i < Board.lineSize; i++) {
			rows[i] = Library.lineLibrary.getLine(new boolean[] {true, true, false, true, false, false});
			columns[i] = Library.lineLibrary.getLine(new boolean[] {false, true, true, false, true, false});
		}
		
		rows[0] 	= Library.lineLibrary.getLine(new boolean[] {true, true, false, false, false, false});
		columns[5] 	= Library.lineLibrary.getLine(new boolean[] {true, true, false, false, false, false});
		
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
		System.out.println("Time taken in millisec: " + (end - start));
	}
	
	public static void main(String[] args) {
		Library.init();
		
		//for (Line line : Library.lineLibrary.getLines())
		//	line.print();
		
		Board board = Generator.getHardestBoard();
		Cluster cluster = new Cluster(board);
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			cluster = new Cluster(board);
			cluster.expand();
		}
		long end = System.currentTimeMillis();
		System.out.println("Time taken in millisec: " + (end - start));
		
		System.out.println("Size of cluster: " + cluster.size());
	}
}