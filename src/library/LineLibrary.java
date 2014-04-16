package library;

import java.util.ArrayList;
import board.Line;
import board.Board;

public class LineLibrary {
	private ArrayList<Line> lines;
	
	protected LineLibrary() {
		lines = generateAllLines();
	}
	
	public ArrayList<Line> getLines() {
		return lines;
	}
	
	private ArrayList<Line> generateAllLines() {
		ArrayList<Line> allLines = new ArrayList<Line>();
		
		for (boolean[] intLine : generateAllIntLines())
			allLines.add(new Line(intLine));
		
		return allLines;
	}
	
	private ArrayList<boolean[]> generateAllIntLines() {
		return generateAllIntLinesRec(new ArrayList<boolean[]>(), new boolean[Board.lineSize], 0);
	}
	
	private ArrayList<boolean[]> generateAllIntLinesRec(ArrayList<boolean[]> intLines, boolean[] line, int depth) {
		if (depth == line.length) { // basecase		
			if (line[line.length - 1]) // last elem must be false
				return intLines;
			
			int carSize = 0;
			for (boolean elem : line) {
				if (elem || carSize > 0) {
					carSize++;
					if (carSize > Board.maxCarSize) // no car bigger than maxCarSize
						return intLines;
				}
				
				if (!elem) {
					if (carSize > 0 && carSize < Board.minCarSize) // no car smaller than minCarSize
						return intLines;
					carSize = 0;
				}
			}
			intLines.add(line);
			return intLines;
		}
		
		line[depth] = true;
		generateAllIntLinesRec(intLines, line.clone(), depth + 1);
		
		line[depth] = false;
		generateAllIntLinesRec(intLines, line.clone(), depth + 1);
		
		return intLines;
	}
}
