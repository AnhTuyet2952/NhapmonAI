package student;

public class HillClimbingSearchQueen {
	int stepClimbed = 0; 
	int stepClimbedAfterRandomRestart = 0; 
	int randomRestarts = 0; 

	public Node execute(Node initialState) {
		Node current = initialState;
		Node neighbor = null;
		while(true) {
			neighbor = current.getBestCandidate();
			if(neighbor.getH()<current.getH()) {
				current=neighbor;
				stepClimbed++;
			}else {
				return current;
			}
		}
		} 
	public Node executeWithRandomRestart(Node initialState) {
		Node state = execute(initialState);
		while(state.getH() != 0) {
			randomRestarts++;
			stepClimbedAfterRandomRestart++;
			stepClimbed++;
			state.generateBoard();
			state = new Node(state);
		}
		System.out.println("randomRestarts: " + randomRestarts);
		System.out.println("stepClimbedAfterRandomRestart: " + stepClimbedAfterRandomRestart);
		System.out.println("stepClimbed: " + stepClimbed);
		return state;
	}
}
