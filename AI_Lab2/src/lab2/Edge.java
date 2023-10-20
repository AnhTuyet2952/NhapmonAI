/**
 * 
 */
package lab2;

/**
 * canh
 */
public class Edge implements Comparable<Edge>{
	private Node begin;
	private Node end;
	private double weight; //trong so
	public Edge(Node begin, Node end, double weight) {
		super();
		this.begin = begin;
		this.end = end;
		this.weight = weight;
	}
	
	

	public Edge(Node begin, Node end) {
		super();
		this.begin = begin;
		this.end = end;
		this.weight = 1;
	}
	


	public Node getBegin() {
		return begin;
	}



	public void setBegin(Node begin) {
		this.begin = begin;
	}



	public Node getEnd() {
		return end;
	}



	public void setEnd(Node end) {
		this.end = end;
	}



	public double getWeight() {
		return weight;
	}



	public void setWeight(double weight) {
		this.weight = weight;
	}



	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
