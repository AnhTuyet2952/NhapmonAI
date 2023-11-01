/**
 * 
 */
package student;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 
 */
public class GreedyBestFirstSearchAlgo implements IInformedSearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparatorByHn());
		Set<Node> explored = new HashSet<Node>();
		frontier.add(root);
		while(!frontier.isEmpty()) {
			Node current = frontier.poll();
			if(current.getLabel().equals(goal)) {
				return current;
			}
			explored.add(current);
			List<Edge> children = current.getChildren();
			for (Edge edge : children) {
				Node child = edge.getEnd();
				if(!explored.contains(child)&&!frontier.contains(child)) {
					child.setParent(current);
					frontier.add(child);
				}
			}
		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		boolean started = false;
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparatorByHn());
		Set<Node> explored = new HashSet<Node>();
		frontier.add(root);
		while(!frontier.isEmpty()) {
			Node current = frontier.poll();
			if(current.getLabel().equals(start)) {
				//dat lai cac gia tri ve ban dau
				started = true;
				frontier.clear();
				explored.clear();
				current.setParent(null);
			}
			if(current.getLabel().equals(goal)) {
				return current;
			}
			explored.add(current);
			List<Edge> children = current.getChildren();
			for (Edge edge : children) {
				Node child = edge.getEnd();
				if(!explored.contains(child)&&!frontier.contains(child)) {
					//gan node hien tai lam node cha cua node con
					child.setParent(current);
					//them node con vao frontier de xem xet o cac vong lap sau
					frontier.add(child);
				}
			}
		}
		
		
		
		return null;
	}

	class NodeComparatorByHn implements Comparator<Node> {
		@Override
		public int compare(Node o1, Node o2) {
			Double h1 = o1.getH();
			Double h2 = o2.getH();
			int result = h1.compareTo(h2);
			if (result == 0)
				return o1.getLabel().compareTo(o2.getLabel());
			else
				return result;
		}
	}
}
