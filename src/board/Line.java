package board;

import java.util.ArrayList;

import library.Library;

public class Line {
	private ArrayList<ArrayList<Line>> reachableLines; // all lines reachable from line to the left
	private boolean[] line;
	private boolean[] occupationLine; // all tiles occupied on line
	private int numberOfCars, numberOfTrucks;
	
	public Line(boolean[] line) {
		this.line = line;
	}
	
	public void init() {
		this.numberOfCars = 0;
		this.numberOfTrucks = 0;
		this.occupationLine = new boolean[line.length];
		ArrayList<int[]> carLocLengths = new ArrayList<int[]>();
		
		int count = 0;
		for (int i = 0; i < line.length; i++) {
			if (line[i] || count > 0) {
				count++;
				occupationLine[i] = true;
			}
			
			if (!line[i] || (i == line.length && count > 0)) {
				if (count == 2)
					numberOfCars++;
				else if (count == 3)
					numberOfTrucks++;
				
				if (count > 0)
					carLocLengths.add(new int[] {i - count + 1, count});
				
				count = 0;
			}
		}
		
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
}
