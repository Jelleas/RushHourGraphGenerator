import library.Library;
import board.Line;

public class Generator {
	public static void main(String[] args) {
		Library.init();
		
		for (Line line : Library.lineLibrary.getLines())
			line.print();
	}
}
