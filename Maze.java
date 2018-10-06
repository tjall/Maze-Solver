
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Maze extends JFrame {

	public Maze() {
		
		this.add(new MazeGridPanel(50,50));//setting how many rows and columns i want for the maze
		this.setSize(800, 800);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
			new Maze();
	}
}
