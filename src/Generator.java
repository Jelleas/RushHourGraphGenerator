import library.Library;
import board.Line;

public class Generator {
	public static void main(String[] args) {
		for (Line line : Library.lineLibrary.getLines())
			System.out.println(line);
	}
}
