package board;

public class Line {
	private boolean[][] reachableLines;
	private boolean[] line;
	
	public Line(boolean[] line) {
		this.line = line;
	}

	public String toString() {
		String repr = "";
		for (boolean elem : line)
			repr += (elem ? 1 : 0) + " ";
		return repr;
	}
}
