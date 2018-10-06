import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.JPanel;

public class MazeGridPanel extends JPanel{
	private int rows;
	private int cols;
	private Cell[][] maze;



	// extra credit
	public void genDFSMaze() {
		boolean[][] visited;
		Stack<Cell> stack  = new Stack<Cell>();
		Cell start = maze[0][0];
		stack.push(start);
	}

	//This method figures out the correct path to take to solve the maze
	public void solveMaze() {
		Stack<Cell> stack  = new Stack<Cell>();
		Cell start = maze[0][0];
		start.setBackground(Color.GREEN);
		Cell finish = maze[rows-1][cols-1];
		finish.setBackground(Color.RED);
		stack.push(start);
		Cell current = stack.peek();
		boolean done = false;
		
		/* So what is happening for the code below is that when a new cell(or block on the grid) 
		 * is referenced by current it is checked to see if it has been visited already and if a wall exists on either its north, west, east , or south side.
		 * the first side found not to have a wall gets pushed onto the stack and colored green on the grid.
		 * when the solver gets to a location where it can't move forward it begins to backtrack and look for an new path to go on
		 * from a already visited block. It does this by popping the top of the stack. If the block has been visited but not apart of the correct path it gets colored grey
		 *  The boolean variable done is used to break out of the for loop once the current block being view is the cell finish
		 */
		while(done == false) {
			if(current.eastWall == false&&!visited(current.row, current.col+1)) {
				current.setBackground(Color.green);
				stack.push(current);
				current = maze[current.row][current.col+1];
				if(current.equals(finish)) {
					finish.setBackground(Color.green);
					done = true;
				}
				else if(current.southWall == true &&current.eastWall == true&& current.northWall == true) {
					current.setBackground(Color.GRAY);
					current = stack.pop();
				}
				
				
			}
			
			else if(current.southWall == false&&!visited(current.row+1, current.col)) {
				current.setBackground(Color.green);
				stack.push(current);
				current = maze[current.row+1][current.col];
				if(current.equals(finish)) {
					finish.setBackground(Color.green);
					done = true;
				}
				else if(current.southWall == true &&current.eastWall == true&& current.westWall == true) {
					current.setBackground(Color.GRAY);
					current = stack.pop();
				}
				
			}
			
			else if(current.westWall == false&&!visited(current.row, current.col-1)){
				current.setBackground(Color.green);
				stack.push(current);
				current = maze[current.row][current.col-1];
				if(current.equals(finish)) {
					finish.setBackground(Color.green);
					done = true;
				}
				else if(current.westWall == true &&current.northWall == true&& current.southWall == true) {
					current.setBackground(Color.GRAY);
					current = stack.pop();
				}
				
			}
			else if(current.northWall == false&&!visited(current.row-1, current.col)){
				current.setBackground(Color.green);
				stack.push(current);
				current = maze[current.row-1][current.col];
				if(current.equals(finish)) {
					finish.setBackground(Color.green);
					done = true;
				}
				else if(current.eastWall == true &&current.westWall == true&& current.northWall == true) {
					current.setBackground(Color.GRAY);
					current = stack.pop();
				}
				
			}
			
			else{
				current.setBackground(Color.gray);
				current = stack.pop();
			}
			
		}

	}


	

	

	/*
	 * this method checks to see if a certain position on the map was visited already
	 * returns true if it has been visited and false otherwise
	 */
	public boolean visited(int row, int col) {
		Cell c = maze[row][col];
		Color status = c.getBackground();
		if(status.equals(Color.WHITE)  || status.equals(Color.RED)  ) {
			return false;
		}


		return true;


	}

	// this method generates the maze by removing walls
	// 
	public void genNWMaze() {
		
		for(int row = 0; row  < rows; row++) {
			for(int col = 0; col < cols; col++) {

				if(row == 0 && col ==0) {
					continue;
				}

				else if(row ==0) {
					maze[row][col].westWall = false;
					maze[row][col-1].eastWall = false;
				} else if(col == 0) {
					maze[row][col].northWall = false;
					maze[row-1][col].southWall = false;
				}else {
					boolean north = Math.random()  < 0.5;
					if(north ) {
						maze[row][col].northWall = false;
						maze[row-1][col].southWall = false;
					} else {  // remove west
						maze[row][col].westWall = false;
						maze[row][col-1].eastWall = false;
					}
					maze[row][col].repaint();
				}
			}
		}
		this.repaint();
		
	}
	
	
	public MazeGridPanel(int rows, int cols) {
		this.setPreferredSize(new Dimension(800,800));
		this.rows = rows;
		this.cols = cols;
		this.setLayout(new GridLayout(rows,cols));
		this.maze =  new Cell[rows][cols];
		for(int row = 0 ; row  < rows ; row++) {
			for(int col = 0; col < cols; col++) {

				maze[row][col] = new Cell(row,col);

				this.add(maze[row][col]);
			}

		}


		this.genNWMaze();
		this.solveMaze();
		
	}




}