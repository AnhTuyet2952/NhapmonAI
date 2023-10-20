package lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Node implements Comparable<Node>{
	private String label; //
	private Node parent;  //for printing the path from the start node to the goal node
	private double pathCost; // chi phí
	private int depth; // độ sâu của nút trong tree
	private List<Edge> Children = new ArrayList<Edge>();
	public Node(String label) {
		this.label= label;
	}
	public Node(String label, int depth) {
		this.label = label;
		this.depth = depth;
	}
	public List<Node> getChildrenNodes() {
		List<Node> result = new ArrayList<Node>();
		for (Edge edge : this.Children) {
			result.add(edge.getEnd());
		}
		return result;
	}
	// an edge is generated using this node and "that" with the given cost
	public void addEdge(Node that, double cost) {
		Edge edge = new Edge(this, that, cost);
		this.Children.add(edge);
	}

	// an edge is generated using this node and "that" with the given cost
	public void addEdge(Node that) {
		Edge edge = new Edge(this, that);
		this.Children.add(edge);
	}
	
	
	
	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return this.label.compareTo(o.label);
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public double getPathCost() {
		return pathCost;
	}
	public void setPathCost(double pathCost) {
		this.pathCost = pathCost;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public List<Edge> getChildren() {
		return Children;
	}
	public void setChildren(List<Edge> children) {
		Children = children;
	}
	@Override
	public String toString() {
		return this.label + "_" + this.parent.getLabel() + " " + this.pathCost;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		return Objects.equals(Children, other.Children) && depth == other.depth && Objects.equals(label, other.label)
				&& Objects.equals(parent, other.parent)
				&& Double.doubleToLongBits(pathCost) == Double.doubleToLongBits(other.pathCost);
	}
	
	
}
