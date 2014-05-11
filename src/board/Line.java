package board;

import java.util.ArrayList;

import library.Library;

public final class Line {
	private final ArrayList<ArrayList<Line>> reachableLines; // all lines reachable from line to the left
	private final ArrayList<ArrayList<Integer>> reachableLinesIndices;
	
	public final boolean[] line;
	public final boolean[] occupationLine; // all tiles occupied on line
	
	private final int numberOfCars, numberOfTrucks;
	private final String filling;
	private final ArrayList<int[]> carLocLengths; // per car its location and length.
	private boolean isGoal, order;
	
	public Line(boolean[] line) {
		this.line = line;
		this.reachableLines = new ArrayList<ArrayList<Line>>();
		this.reachableLinesIndices = new ArrayList<ArrayList<Integer>>();
		this.occupationLine = new boolean[line.length];
		this.carLocLengths = new ArrayList<int[]>();
		this.order = false;
		
		int numberOfCars = 0;
		int numberOfTrucks = 0;
		String filling = "";
		int count = 0;
		for (int i = 0; i < line.length; i++) {
			if (line[i] || count > 0) {
				count++;
				occupationLine[i] = true;
			}
			
			if (!line[i] || (i == line.length && count > 0)) {
				if (count == 2) {
					numberOfCars++;
					if (filling.equals(""))
						order = true;
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
		
		this.numberOfCars = numberOfCars;
		this.numberOfTrucks = numberOfTrucks;
		
		filling += numberOfCars;
		filling += numberOfTrucks;
		this.filling = filling;
		this.isGoal = this.line[Board.lineSize - 2] && !this.line[Board.lineSize - 3];
	}
	
	public ArrayList<ArrayList<Line>> getReachableLines() {
		return reachableLines;
	}
	
	public ArrayList<ArrayList<Integer>> getReachableLinesIndices() {
		return reachableLinesIndices;
	}
	
	public void init() { // init reachableLines
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
	
	public boolean getOrder() {
		return order;
	}
	
	public boolean isGoal() {
		return isGoal;
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