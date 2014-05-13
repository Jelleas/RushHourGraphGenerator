package library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import databaseLink.Link;

import board.Line;
import board.Board;

public class LineLibrary {
	private ArrayList<Line> lines;
	private HashMap<String, Line> linesMap;
	private HashMap<String, List<Line>> lineFillingToLines;
	
	protected LineLibrary() {
		initLines();
	}
	
	protected void init() {
		for (Line line : lines)
			line.init();
	}
	
	protected void syncWithDatabase(Link link) {
		for (Line line : lines)
			line.syncWithDatabase(link);
	}
	
	public ArrayList<Line> getLines() {
		return lines;
	}
	
	public List<Line> getLines(String lineFilling) {
		return lineFillingToLines.get(lineFilling);
	}
	
	/*
	public ArrayList<Line> getRowLines() {
		ArrayList<Line> lines = new ArrayList<Line>();
		
		for (Line line : this.lines) {
			for (int i = 4; i > 0; i--) {
				if (line.line[i]) {
					if (!line.line[i - 1]) {
						lines.add(line);
						for (boolean elem: line.line) {
							System.out.print((elem ? 1 : 0) + " ");
						} System.out.println();
					}
					break;
				}
				
				if (i == 1 && line.occupationLine[i]) {
					lines.add(line);
					for (boolean elem: line.line) {
						System.out.print((elem ? 1 : 0) + " ");
					} System.out.println();
				}
			}
		}
		System.out.println(lines.size());
		return lines;
	}
	
	public ArrayList<Line> getColumnLines() {
		ArrayList<Line> lines = new ArrayList<Line>();
		
		for (Line line : this.lines) {
			if (!line.occupationLine[2]) {
				lines.add(line);
				for (boolean elem: line.occupationLine) {
					System.out.print((elem ? 1 : 0) + " ");
				} System.out.println();
			}
		}
		
		return lines;
	}*/
	
	public ArrayList<Line> getGoalLines() {
		ArrayList<Line> goalLines = new ArrayList<Line>();
		for (Line line : lines)
			if (line.line[Board.lineSize - 2] && !line.line[Board.lineSize - 3])
				goalLines.add(line);
		return goalLines;
	}
	
	public Line getLine(boolean[] boolLine) {
		String repr = "";
		for (boolean elem : boolLine)
			repr += elem;
		
		return linesMap.get(repr);
	}
	
	public List<List<Line>> getGoalLinesByLineFilling() {
		List<List<Line>> goalLinesByLineFilling = new ArrayList<List<Line>>();
		
		for (String lineFilling : getAllLineFillings()) {
			List<Line> goalLines = new ArrayList<Line>();
			
			for (Line line : getLines(lineFilling))
				if (line.isGoal())
					goalLines.add(line);
			
			if (!goalLines.isEmpty())
				goalLinesByLineFilling.add(goalLines);
		}
		
		return goalLinesByLineFilling;
	}
	
	public int size() {
		return lines.size();
	}
	
	public int getNumClusterFillings() {
		return getAllLineFillings().size();
	}
	
	public List<String> getAllLineFillings() {
		return new ArrayList<String>(lineFillingToLines.keySet());
	}
	
	private void initLines() {
		lines = new ArrayList<Line>();
		linesMap = new HashMap<String, Line>();
		lineFillingToLines = new HashMap<String, List<Line>>();
		
		for (boolean[] boolLine : generateAllBoolLines()) {
			Line line = new Line(boolLine);
			
			String repr = "";
			for (boolean elem : boolLine)
				repr += elem;
			
			linesMap.put(repr, line);
			lines.add(line);
			List<Line> tempLines = lineFillingToLines.get(line.getLineFilling());
			if (tempLines == null)
				tempLines = new ArrayList<Line>();
			tempLines.add(line);
			lineFillingToLines.put(line.getLineFilling(), tempLines);
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
