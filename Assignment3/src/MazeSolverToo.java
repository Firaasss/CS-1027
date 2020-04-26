import java.io.IOException;

public class MazeSolverToo {  //pub main
	public static void main (String[] args) throws Exception{  //main method with args param
		String mazeFile = args[0];  //taking the args param from sys configuration
		LinkedPriorityQueue<Hexagon> queue = new LinkedPriorityQueue<Hexagon>();  //creating the priority queue
		
		try {  //try to do the following
			if(args.length<1){  //if there is no input for mazefile then throw error
				 throw new IllegalArgumentException("No Maze Provided");
			}
			Maze maze = new Maze(mazeFile);  //creating new maze object
			Hexagon startTile = maze.getStart();  //initializing the start tile
			queue.enqueue(startTile);  //adding it to the queue
			startTile.setSteps(0);  //start the maze at 0
			int count = 0;  //initialize the counter	
			int hexCounter = 0;  //initialize hex counter
			boolean complete = false;  //to break the while loop
			startTile.setStarted();  //initialize the start tile to begin maze
			
			
			while(!queue.isEmpty() && complete == false) {  //while queue is empty and incomplete;
				startTile = queue.dequeue();  //dequeue the tile from priority queue
				hexCounter--;  //remove 1 from hexCounter
				startTile.setDequeued();  //set the tile as dequeued
				if(startTile.isEnd()) {  //if at the end
					maze.repaint();  //finished; repaint maze
					count++;  //increase counter
					startTile.setFinished();  //set the tile as complete
					complete = true;	 //break from loop
				} 
				else {  //if queue is not empty 
					for(int i = 0; i < 6; i++) {  //for all of hexagon neighbours
						Hexagon neighbour = startTile.getNeighbour(i);  //get the different sides of hex
						if (neighbour != null && !neighbour.isWall() && !neighbour.isDequeued()) {  //if not wall, null and is in the stack (not dequeued)
							queue.enqueue(neighbour, 2);  //add the neighbour to the queue
							startTile.setFinished();  //finished from the startTile set finished
							hexCounter++;  //increase the hexCounter
							count++;  //increase the counter
							break;  //break from loop
						}
					}
			
				} //else
			}//while
			
		if(complete == true) {  //if the maze is complete; print statement
			System.out.println("Completed! It took " + count + " moves and " + hexCounter + " in the queue!");
		}
				
		else {  //if the maze is incomplete; print statement
			System.out.println("Not finished. It took " + count + " moves and " + hexCounter + " in the queue!");
		}
			
		}  //catches
		catch (EmptyCollectionException e){  
			System.err.println("Please provide a maze");
		}
		catch (IOException e){  //file not found
			System.err.println("IO Error");
		} 
		
	}//main
}//top
