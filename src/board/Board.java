package board;

import java.util.ArrayList;

public class Board {
	public static final int lineSize = 6;
	public static final int minCarSize = 2;
	public static final int maxCarSize = 3;
	
	private Line[] columns, rows;
	
	public Board(Line[] rows, Line[] columns) { // TODO finish!
		this.columns = columns;
		this.rows = rows;
	}
	
	public ArrayList<Board> getReachableBoards() {
		ArrayList<Board> reachableBoards = new ArrayList<Board>();
		
		for (int h = 0; h < 2; h++) {
			Line[] lines, otherLines;
			if (h == 0) {
				lines = rows;
				otherLines = columns;
			} else {
				lines = columns;
				otherLines = rows;
			}
		
			for (int i = 0; i < lines.length; i++) {
				ArrayList<ArrayList<Line>> reachableLinesSet = lines[i].getReachableLines(); // TODO
				ArrayList<ArrayList<Integer>> reachableLinesIndicesSet = lines[i].getReachableLinesIndices();
				
				for (int j = 0; j < reachableLinesSet.size(); j++) {
					ArrayList<Line> reachableLines = reachableLinesSet.get(j);
					ArrayList<Integer> reachableLinesIndices = reachableLinesIndicesSet.get(j);
					
					for (int k = 0, length = reachableLines.size(); k < length; k++) {
						if (otherLines[reachableLinesIndices.get(k)].occupationLine[i])
							break;
						else {
							Line[] tempColumns;
							Line[] tempRows;
							
							if (h == 0) {
								tempRows = rows.clone();
								tempRows[i] = reachableLines.get(k);
								tempColumns = columns;
							} else {
								tempColumns = columns.clone();
								tempColumns[i] = reachableLines.get(k);
								tempRows = rows;
							}
							
							reachableBoards.add(new Board(tempRows, tempColumns));
						}
					}
				}
			}
		}
		
		return reachableBoards;
	}
	
	public void print() {
		System.out.println("Row/Columns");
		for (int i = 0; i < Board.lineSize; i++)
			System.out.println(rows[i] + "   " + columns[i]);
		System.out.println();
	}
	
	/**
	 * HashCode based on contents of board.
	 */
	public int hashCode() { 
		int hashCode = 1;
		for (int i = 0; i < lineSize; i++)
			hashCode *= columns[i].hashCode() * rows[i].hashCode();
		return hashCode;
	}
	
	/**
	 * Equals based on the content of the board
	 */
	public boolean equals(Object o) {
		if (o == null || !(o instanceof Board))
			return false;
		
		Board other = (Board)o;
		
		for (int i = 0; i < lineSize; i++)
			if (other.columns[i] != this.columns[i] || other.rows[i] != this.rows[i])
				return false;
		return true;
	}
	
	public String getBoardFilling() {
		String filling = "";
		
		for (Line line : this.columns)
			filling += line.getLineFilling();
		
		for (Line line : this.rows)
			filling += line.getLineFilling();
		
		return filling;
	}
}
