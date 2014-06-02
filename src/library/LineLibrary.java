package library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import databaseLink.Link;

import board.Line;
import board.Board;

public class LineLibrary {
	private ArrayList<Line> lines;
	private HashMap<Integer, Line> idToLine;
	private HashMap<String, Line> linesMap;
	private HashMap<String, List<Line>> lineFillingToLines;
	private HashMap<Integer, List<Line>> lineFillingIdToLines;
	private ArrayList<String> lineFillings;
	
	protected LineLibrary() {
		initLines();
	}
	
	protected void init() {
		for (Line line : lines)
			line.init();
	}
	
	protected void syncWithDatabase(Link link) {
		for (Line line : lines) {
			line.syncWithDatabase(link);
			lineFillingIdToLines.put(line.getFillingId(), lineFillingToLines.get(line.getLineFilling()));
			idToLine.put(line.getId(), line);
		}
	}
	
	public ArrayList<Line> getLines() {
		return lines;
	}
	
	public List<Line> getLines(String lineFilling) {
		return lineFillingToLines.get(lineFilling);
	}
	
	public List<Line> getLinesByLineFillingId(int lineFillingId) {
		return lineFillingIdToLines.get(lineFillingId);
	}
	
	public Line getLineById(int id) {
		return idToLine.get(id);
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
		return lineFillings;
	}
	
	public List<String> getGoalLineFillings() {
		List<String> goalLineFillings = new ArrayList<String>();
		
		for (String lineFilling : getAllLineFillings()) {
			for (Line line : getLines(lineFilling))
				if (line.isGoal()) {
					goalLineFillings.add(lineFilling);
					break;
				}
		}
		
		return goalLineFillings;
	}
	
	private void initLines() {
		lines = new ArrayList<Line>();
		idToLine = new HashMap<Integer, Line>();
		linesMap = new HashMap<String, Line>();
		lineFillingToLines = new HashMap<String, List<Line>>();
		lineFillingIdToLines = new HashMap<Integer, List<Line>>();
		lineFillings = new ArrayList<String>();
		
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

			boolean shouldAdd = true;
			for (String lineFilling : lineFillings)
				if (lineFilling.equals(line.getLineFilling())) {
					shouldAdd = false;
					break;
				}
			if (shouldAdd)
				lineFillings.add(line.getLineFilling());
			
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
