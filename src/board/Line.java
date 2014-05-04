package board;

import java.util.ArrayList;

import library.Library;

public class Line {
	private ArrayList<ArrayList<Line>> reachableLines; // all lines reachable from line to the left
	private ArrayList<ArrayList<Integer>> reachableLinesIndices;
	
	public boolean[] line;
	public boolean[] occupationLine; // all tiles occupied on line
	private int numberOfCars, numberOfTrucks;
	private String filling;
	
	ArrayList<int[]> carLocLengths; // per car its location and length.
	
	public Line(boolean[] line) {
		this.line = line;
		
		this.numberOfCars = 0;
		this.numberOfTrucks = 0;
		this.filling = "";
		this.occupationLine = new boolean[line.length];
		this.carLocLengths = new ArrayList<int[]>();
		
		int count = 0;
		for (int i = 0; i < line.length; i++) {
			if (line[i] || count > 0) {
				count++;
				occupationLine[i] = true;
			}
			
			if (!line[i] || (i == line.length && count > 0)) {
				if (count == 2) {
					numberOfCars++;
					filling += "2";
				} else if (count == 3) {
					numberOfTrucks++;
					filling += "3";
				}
				
				if (count > 0)
					carLocLengths.add(new int[] {i - count + 1, count});
				
				count = 0;
			}
		}
		
		filling += numberOfCars;
		filling += numberOfTrucks;
	}
	
	public ArrayList<ArrayList<Line>> getReachableLines() {
		return reachableLines;
	}
	
	public ArrayList<ArrayList<Integer>> getReachableLinesIndices() {
		return reachableLinesIndices;
	}
	
	public void init() {
		reachableLines = new ArrayList<ArrayList<Line>>(); // init reachableLines
		
		for (int[] carLocLength : carLocLengths) {
			ArrayList<Line> reachables = new ArrayList<Line>();
			int carEndPos = carLocLength[0] + carLocLength[1];
			
			for (int i = carLocLength[0] - 1; i >= 0; i--) {
				if (!occupationLine[i]) {
					boolean[] tempLine = line.clone();
					for (int j = carLocLength[0]; j < carEndPos; j++)
						tempLine[j] = false;
					
					for (int j = i; j < i + carLocLength[1] - 1; j++)
						tempLine[j] = true;
					
					//System.out.println(this + " " + Library.lineLibrary.getLine(tempLine) + " LEFT " +
					//		carLocLength[0] + "," + carLocLength[1] + " " + i + "," + carLocLength[1]);
					reachables.add(Library.lineLibrary.getLine(tempLine));	
				} else // if car is blocked, stop moving in this direction
					break; 
			}
			
			if (!reachables.isEmpty())
				reachableLines.add(reachables);
			reachables = new ArrayList<Line>();
			
			for (int i = carEndPos; i < line.length; i++) {
				if (!occupationLine[i]) {
					boolean[] tempLine = line.clone();
					for (int j = carLocLength[0]; j < carEndPos; j++)
						tempLine[j] = false;
					
					for (int j = i - 1; j > i - carLocLength[1]; j--)
						tempLine[j] = true;
					
					//System.out.println(this + " " + Library.lineLibrary.getLine(tempLine) + " RIGHT " +
					//carLocLength[0] + "," + carLocLength[1] + " " + i + "," + carLocLength[1]);
					reachables.add(Library.lineLibrary.getLine(tempLine));	
				} else // if car is blocked, stop moving in this direction
					break;
			}
			
			if (!reachables.isEmpty())
				reachableLines.add(reachables);
		}
		
		// compute indices for checking if move is valid.
		reachableLinesIndices = new ArrayList<ArrayList<Integer>>();
		for (ArrayList<Line> lines : reachableLines) {
			Line line = this; // moves are done incrementally, so keep track of last increment.
			ArrayList<Integer> indices = new ArrayList<Integer>();
			
			for (Line reachableLine : lines)
				for (int i = 0; i < Board.lineSize; i++)
					if (reachableLine.occupationLine[i] && !line.occupationLine[i]) {
						indices.add(i);
						line = reachableLine;
						break;
					}
			reachableLinesIndices.add(indices);
		}
	}

	public int getNumberOfCars() {
		return this.numberOfCars;
	}
	
	public int getNumberOfTrucks() {
		return this.numberOfTrucks;
	}
	
	public void print() {
		System.out.println(this);
		System.out.println("Reachables:");
		for (ArrayList<Line> reachables : this.reachableLines)
			for (Line line : reachables)
				System.out.println(line);
		
		System.out.println();
	}
	
	public String toString() {
		String repr = "";
		for (boolean elem : line)
			repr += (elem ? 1 : 0) + " ";
		return repr;
	}
	
	public String getLineFilling() {
		return filling;
	}
}