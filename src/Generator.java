import java.util.ArrayList;

import library.Library;
import board.*;

public class Generator {
	public static void main(String[] args) {
		Library.init();
		
		//for (Line line : Library.lineLibrary.getLines())
		//	line.print();
		
		Line[] rows = new Line[Board.lineSize];
		Line[] columns = new Line[Board.lineSize];
		
		for (int i = 0; i < Board.lineSize; i++) {
			rows[i] = Library.lineLibrary.getLine(new boolean[] {true, true, false, true, false, false});
			columns[i] = Library.lineLibrary.getLine(new boolean[] {false, true, true, false, true, false});
		}
		
		rows[0] 	= Library.lineLibrary.getLine(new boolean[] {true, true, false, false, false, false});
		columns[5] 	= Library.lineLibrary.getLine(new boolean[] {true, true, false, false, false, false});
		
		Board board = new Board(rows, columns);
		
		ArrayList<Board> reachableBoards = null;
		
		long start = System.currentTimeMillis();
		//for (int i = 0; i < 10000000; i++) 
			reachableBoards = board.getReachableBoards();
		long end = System.currentTimeMillis();
		System.out.println("Time taken in millisec: " + (end - start));
		
		board.print();
		for (Board reachableBoard : reachableBoards)
			reachableBoard.print();
	}
}