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
	
	public boolean isSolution() {
		Line row = rows[(Board.lineSize - 1) / 2];
		return row.occupationLine[Board.lineSize - 1] && row.occupationLine[Board.lineSize - 2]; 
	}
	
	/**
	 * Prints rows and columns in binary coding.
	 */
	public void print() {
		System.out.println("Row/Columns");
		for (int i = 0; i < Board.lineSize; i++)
			System.out.println(rows[i] + "   " + columns[i]);
		System.out.println();
	}
	
	/**
	 * Prints the board as a matrix where numbers represent vehicles.
	 */
	public void prettyPrint() {
		int[][] field = new int[Board.lineSize][Board.lineSize];
		int nVehicles = 0;
		
		// fill field with vehicles on the rows
		for (int i = 0; i < Board.lineSize; i++) {
			Line row = rows[i];
			int count = 0;
			
			for (int j = 0; j < Board.lineSize; j++)
				if (row.occupationLine[j]) {
					if (count == 0)
						nVehicles++;
					
					field[i][j] = nVehicles;
					count++;
				} else
					count = 0;
		}
		
		// fill temp field with vehicles on the columns, needed as columns
		// need to be mirrored along diagonal.
		int[][] tempField = new int[Board.lineSize][Board.lineSize];
		for (int i = 0; i < Board.lineSize; i++) {
			Line column = columns[i];
			int count = 0;
			
			for (int j = 0; j < Board.lineSize; j++)
				if (column.occupationLine[j]) {
					if (count == 0)
						nVehicles++;
					
					tempField[i][j] = nVehicles;
					count++;
				} else
					count = 0;
		}
		
		// mirror tempfield along diagonal onto field.
		for (int i = 0; i < Board.lineSize; i++)
			for (int j = 0; j < Board.lineSize; j++)
				if (tempField[j][i] != 0)
					field[i][j] = tempField[j][i];
		
		int maxDigits = 1;
		for (int i = nVehicles; i > 9; i /= 10, maxDigits++);
		for (int[] intLine : field) {
			String repr = "";
			for (int elem : intLine) {
				int nDigits = 1;
				for (int i = elem; i > 9; i /= 10, nDigits++);
				repr += elem + " ";
				for (int i = 0; i < maxDigits - nDigits; i++)
					repr += " ";
			}
			System.out.println(repr);
		}
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
