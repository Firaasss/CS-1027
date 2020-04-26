import java.io.IOException;

public class MazeSolver {
	public static void main(final String[] args) throws Exception{
		String maze1 = args[0];
		try {
			//if no maze is provided to open file
			if(args.length < 1 ) {
					throw new IllegalArgumentException("You did not enter a maze file.");
			}
			Maze maze = new Maze(maze1);  //new maze object, open maze file
			Hexagon startTile = maze.getStart();  //start maze at beginning
			ArrayStack<Hexagon> hexagonStack = new ArrayStack<Hexagon>();  //creating hexagon stack
			hexagonStack.push(startTile);  //insert starting element in stack
			int stepCounter = 0;  //steps to reach the end
			int hexagonOnStack = 1;  //number of hexagons in stack
			maze.setTimeDelay(250); //show steps in the maze preview
			boolean complete = false;
			
			
			while(!hexagonStack.isEmpty() && complete == false) {
				Hexagon currHexagon = hexagonStack.peek(); //setting currHexagon as top element in stack with peek()
				
				if (currHexagon.isEnd()) {  //if tile is at the end
					currHexagon.setProcessed();  //set visited and update color
					maze.repaint();  //update maze
					complete = true;  //break out of loop
					}
				else { 
					for(int i = 0; i < 6; i++) {  //for all of hexagon neighbours
						Hexagon neighbour = currHexagon.getNeighbour(i);
						if (neighbour != null && neighbour.isUnvisited() && !neighbour.isWall()) {
							hexagonStack.push(neighbour);  //add to stack
							neighbour.setPushed();  //setPush method to neighbour tile
							hexagonOnStack++;  //increase counter for hexagon
							stepCounter++;  //add step
							break;  
						}
						else {
							if(i == 5) {  //if the last side of the hexagon is reached
								currHexagon.setProcessed();  //set the current hexagon as processed
								hexagonStack.pop();  //remove top element
								stepCounter++; 
							}
						}
					}
				}
				maze.repaint();
				
			} //while brace
			
			if(complete == true) {
			System.out.println("Completed! It took " + stepCounter + " moves and " + hexagonOnStack + " in the stack!");
			}
			
			else {
			System.out.println("Not finished. It took " + stepCounter + " moves and " + hexagonOnStack + " in the stack!");
			}
			
		} //try	
		
		//exceptions
		catch (IOException e){  //file not found
			System.err.println("IO Error");
		} 
		catch (EmptyCollectionException e){  
			System.err.println("Please provide a maze");
		} 
		catch (IllegalArgumentException e){  
			System.err.println("Illegal arguments were supplied");
		} 
		catch (UnknownMazeCharacterException e){  //no maze created
			System.err.println("Load failed");
		} 
		catch (InvalidNeighbourIndexException e){ 
			System.err.println("Invalid Neighbour");
		}
		
	}
}