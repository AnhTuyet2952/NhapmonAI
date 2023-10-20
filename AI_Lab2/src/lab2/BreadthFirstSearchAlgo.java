/**
 * 
 */
package lab2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Thuật toán tìm kiếm đầu tiên theo chiều rộng
 */
public class BreadthFirstSearchAlgo implements ISearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		Queue<Node> frontier = new LinkedList<Node>();
		frontier.add(root);
		List<Node> explored = new ArrayList<Node>();// luu cac nut da kiem tra
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();// gán nút đầu tiên cho current
			if (current.getLabel().equals(goal)) {
				return current;
			}
			explored.add(current);
			List<Node> children = current.getChildrenNodes();
			for (Node node : children) {
				if (!frontier.contains(node) && !explored.contains(node)) {
					frontier.add(node);
					node.setParent(current);
				}
			}

		}

		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		boolean check = false;
		Queue<Node> frontier = new LinkedList<Node>();
		frontier.add(root);
		List<Node> explored = new ArrayList<Node>();
		boolean started = false;
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if (current.getLabel().equals(start)) {
				started = true;
				explored.clear();
				current.setParent(null);
			}
			if (current.getLabel().equals(goal) && started == true) {
				return current;
			}
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
			List<Node> chidren = current.getChildrenNodes();
			for (Node node : chidren) {
				if (!frontier.contains(node) && !explored.contains(node)) {
					frontier.add(node);
					node.setParent(current);
				}
			}
		}
		return null;
	}

	public void ACS_Sort(List<Node> children) {
		Collections.sort(children, new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				return o1.getLabel().compareTo(o2.getLabel());
			}
		});
	}

	@Override
	public Node executeTreeSearch(Node tree, String goal) {
		boolean check = false;
		Queue<Node> fontier = new LinkedList<>();
		fontier.add(tree);
		while (!fontier.isEmpty()) {
			Node current = fontier.poll();
			if (current.getLabel().equals(goal))
				return current;
			List<Node> children = current.getChildrenNodes();
			ACS_Sort(children);
			for (Node child : children) {
				fontier.add(child);
				if (child.getParent() == null)
					child.setParent(current);
			}
		}
		return null;
	}

	@Override
	public Node executeTreeSearch(Node tree, String start, String goal) {
		boolean check = false;
		boolean Started = false;
		Queue<Node> fontier = new LinkedList<>();
		fontier.add(tree);
		while (!fontier.isEmpty()) {
			Node current = fontier.poll();
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
						check = true;
						break;
					}
				}

			}
			List<Node> children = current.getChildrenNodes();
			ACS_Sort(children);
			for (Node child : children) {
				fontier.add(child);
				if (child.getParent() == null)
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
