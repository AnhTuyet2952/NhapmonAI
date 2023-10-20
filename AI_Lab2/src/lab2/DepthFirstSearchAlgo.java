/**
 * 
 */
package lab2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;


/**
 * Thuật toán tìm kiếm đầu tiên theo chiều sau
 */
public class DepthFirstSearchAlgo implements ISearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		boolean check = false;
		Stack<Node> frontier = new Stack<>();
		frontier.add(root);
		List<Node> explored = new ArrayList<>();
		while (!frontier.isEmpty()) {
			Node current = frontier.pop();
			if (current.getLabel().equals(goal))
				return current;
			explored.add(current);
			List<Edge> path = current.getChildren();
			for (Edge edge : path) {
				Node node = edge.getEnd();
				if (check == false) {
					node.setPathCost(edge.getBegin().getPathCost() + edge.getWeight());
					if (node.getLabel().equals(goal)) {
						check = true;
						break;
					}
				}

			}
			List<Node> children = current.getChildrenNodes();
			DESC_Sort(children);
			for (Node child : children) {
				if (!frontier.contains(child) && !explored.contains(child)) {
					frontier.add(child);
					child.setParent(current);
				}
			}
		}

		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		boolean check = false;
		boolean started = false;
		Stack<Node> frontier = new Stack<>();
		frontier.add(root);
		List<Node> explored = new ArrayList<>();
		while (!frontier.isEmpty()) {
			Node current = frontier.pop();
			if (current.getLabel().equals(start)) {
				started = true;
				frontier.clear();
				explored.clear();
				current.setParent(null);
			}
			if (current.getLabel().equals(goal) && started == true)
				return current;
			explored.add(current);
			List<Edge> path = current.getChildren();
			for (Edge edge : path) {
				Node node = edge.getEnd();
				if (check == false && started == true) {
					node.setPathCost(edge.getBegin().getPathCost() + edge.getWeight());
					if (node.getLabel().equals(goal)) {
						check = true;
						break;
					}
				}

			}
			List<Node> children = current.getChildrenNodes();
			DESC_Sort(children);
			for (Node child : children) {
				if (!frontier.contains(child) && !explored.contains(child)) {
					frontier.add(child);
					child.setParent(current);
				}
			}
		}
		return null;
	}

	public void DESC_Sort(List<Node> children) {
		Collections.sort(children, new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				// TODO Auto-generated method stub
				return o2.getLabel().compareTo(o1.getLabel());
			}

		});
	}

	@Override
	public Node executeTreeSearch(Node tree, String goal) {
		boolean check = false;
		Stack<Node> fontier = new Stack<>();
		fontier.add(tree);
		while (!fontier.isEmpty()) {
			Node current = fontier.pop();
			if (current.getLabel().equals(goal))
				return current;
			List<Edge> path = current.getChildren();
			for (Edge e : path) {
				Node endNode = e.getEnd();
				if (check == false) {
					endNode.setPathCost(e.getBegin().getPathCost() + e.getWeight());
					if (endNode.getLabel().equals(goal)) {
						check=true;
						break;
					}
				}
			}
			List<Node> children = current.getChildrenNodes();
	   		DESC_Sort(children);
			for (Node child : children) {
				fontier.add(child);
					if(child.getParent()==null)
					child.setParent(current);
			}
		}
		return null;
	}

	@Override
	public Node executeTreeSearch(Node tree, String start, String goal) {
		boolean check = false;
		boolean Started = false;
		Stack<Node> fontier = new Stack<>();
		fontier.add(tree);
		while (!fontier.isEmpty()) {
			Node current = fontier.pop();
			if (current.getLabel().equals(start)) {
				Started = true;
				fontier.clear();
				current.setParent(null);
			}
			if (current.getLabel().equals(goal) && Started == true)
				return current;
			//
			List<Edge> path = current.getChildren();
			for (Edge e : path) {
				Node endNode = e.getEnd();

				if (check == false && Started == true) {
					endNode.setPathCost(e.getBegin().getPathCost() + e.getWeight());
					if (endNode.getLabel().equals(goal)) {
						check=true;
						break;
					}
				}

			}
			List<Node> children = current.getChildrenNodes();
			DESC_Sort(children);
			for (Node child : children) {
				fontier.add(child);
					if(child.getParent()==null)
					child.setParent(current);
			}

		}

		return null;
	}
	public static void main(String[] args) {
		Node nodeS = new Node("S");
		Node nodeA = new Node("A"); Node nodeB = new Node("B");
		Node nodeC = new Node("C"); Node nodeD = new Node("D");
		Node nodeE = new Node("E"); Node nodeF = new Node("F");
		Node nodeG = new Node("G"); Node nodeH = new Node("H");
		nodeS.addEdge(nodeA, 5); nodeS.addEdge(nodeB, 2);
		nodeS.addEdge(nodeC, 4); nodeA.addEdge(nodeD, 9);
		nodeA.addEdge(nodeE, 4); nodeB.addEdge(nodeG, 6);
		nodeC.addEdge(nodeF, 2); nodeD.addEdge(nodeH, 7);
		nodeE.addEdge(nodeG, 6); nodeF.addEdge(nodeG, 1);
		ISearchAlgo algo1 = new BreadthFirstSearchAlgo();
		Node result = algo1.execute(nodeS, "G");

	}

	@Override
	public Node execute(Node nodeS, String start, int i) {
		// TODO Auto-generated method stub
		return null;
	}

}
