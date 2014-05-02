package board;

import java.util.ArrayList;

public class Board {
	public static final int lineSize = 6;
	public static final int minCarSize = 2;
	public static final int maxCarSize = 3;
	
	private Line[] columns, rows;
	private ArrayList<Board> reachableBoards;
	
	public Board(Line[] columns, Line[] rows) { // TODO finish!
		this.columns = columns;
		this.rows = rows;
		this.reachableBoards = new ArrayList<Board>();
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
