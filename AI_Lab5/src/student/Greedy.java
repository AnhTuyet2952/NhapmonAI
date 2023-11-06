package student;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Greedy implements IPuzzleAlgo{

	@Override
	public Node execute(Puzzle model) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(PuzzleUtils.HeuristicComparatorByH);
		Set<Node> explored = new HashSet<Node>();
		frontier.add(model.getInitialState());
		while(!frontier.isEmpty()) {
			Node current = frontier.poll();
			if(current.getH()==0) return current;
			else {
				explored.add(current);
				List<Node> children = model.getSuccessors(current);
				for (Node child : children) {
					if(!explored.contains(child)&&!frontier.contains(child)) {
						child.setG(current.getG()+1);
						frontier.add(child);
					}
				}
			}
		}
		
		
		
		// TODO Auto-generated method stub
		return null;
	}
	
}
