package library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import board.Line;
import board.Board;

public class LineLibrary {
	private ArrayList<Line> lines;
	private HashMap<String, Line> linesMap;
	
	protected LineLibrary() {
		initLines();
	}
	
	protected void init() {
		for (Line line : lines)
			line.init();
	}
	
	public ArrayList<Line> getLines() {
		return lines;
	}
	
	public Line getLine(boolean[] boolLine) {
		String repr = "";
		for (boolean elem : boolLine)
			repr += elem;
		
		return linesMap.get(repr);
	}
	
	private void initLines() {
		lines = new ArrayList<Line>();
		linesMap = new HashMap<String, Line>();
		
		for (boolean[] boolLine : generateAllBoolLines()) {
			Line line = new Line(boolLine);
			
			String repr = "";
			for (boolean elem : boolLine)
				repr += elem;
			
			linesMap.put(repr, line);
			lines.add(line);
		}
	}
	
	private ArrayList<boolean[]> generateAllBoolLines() {
		return generateAllBoolLinesRec(new ArrayList<boolean[]>(), new boolean[Board.lineSize], 0);
	}
	
	private ArrayList<boolean[]> generateAllBoolLinesRec(ArrayList<boolean[]> intLines, boolean[] line, int depth) {
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
		generateAllBoolLinesRec(intLines, line.clone(), depth + 1);
		
		line[depth] = false;
		generateAllBoolLinesRec(intLines, line.clone(), depth + 1);
		
		return intLines;
	}
}
