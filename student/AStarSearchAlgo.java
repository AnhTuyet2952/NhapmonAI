package student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class AStarSearchAlgo implements IInformedSearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparatorByHn());
		List<Node> explored = new ArrayList<Node>();
		frontier.offer(root);
		while (!frontier.isEmpty()) {
			//chon node co tong chi phi thap nhat tu frontier
			Node current = frontier.poll();
			//kt xem node hien tai co phai node goal
			if(current.getLabel().equals(goal)) {
				return current;
			}
			//danh dau node hien tai da di qua
			explored.add(current);
			//lay danh sach cac node hien tai
			List<Edge> children = current.getChildren();
			for (Edge edge : children) {
				Node child = edge.getEnd();
				double newG = current.getG()+edge.getWeight();
				//kiem tra cac node da duoc di qua hoac co trong frontier
				if(!explored.contains(child)&&!frontier.contains(child)) {
					child.setParent(current);
					child.setG(newG);
					//them node con vao frontier 
					frontier.add(child);
				} else if(newG < child.getG()) {
					//ss gia tri g(n) moi voi g(n) cu
					// neu g(n) moi nho hon, cap nhat gia tri g(n) va node cha 
					//dat node con trong frontier
					child.setParent(current);
					child.setG(newG);
					//loai bo nut con khoi frontier va them lai no de cap nhat vi tri
					frontier.remove(child);
					frontier.add(child);
				}
			}
		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		boolean started = false;
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparatorByHn());
		Set<Node> explored = new HashSet<Node>();
		frontier.add(root);
		while(!frontier.isEmpty()) {
			Node current = frontier.poll();
			if(current.getLabel().equals(start)) {
				started = true;
				//xoa toan bo node khoi frontier va explored de bat dau lai tu node start
				frontier.clear();
				explored.clear();
				current.setParent(null);
			}
			if(current.getLabel().equals(goal)&&started==true) {
				return current;
			}
			explored.add(current);//danh dau current da di qua
			//lay danh sach cac con cua nút hien tai
			List<Edge> children = current.getChildren();
			for (Edge edge : children) {
				Node child = edge.getEnd();
				double newG = current.getG()+edge.getWeight();
				if(!explored.contains(child)&&!frontier.contains(child)) {
					child.setParent(current);
					child.setG(newG);
					frontier.add(child);
				} else {
					if(newG<child.getG()) {
						frontier.remove(child);
						child.setParent(current);
						child.setG(newG);
						frontier.add(child);
					}
				}
			}
		}
	
	return null;
	}
//	public boolean isAdmissibleH(Node root, String goal) {
//		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparatorByHn());
//		frontier.add(root);
//		while(!frontier.isEmpty()) {
//			Node current = frontier.poll();
//			if(current.getLabel().equals(goal)) {
//				//lay gia tri thuc su cua h(n) tu muc tieu den nut hien tai
//				double hStar = current.getG();
//				//ss h(n) va h(n)
//				if(current.getH()>hStar) {
//					return false; 
//				}
//			}
//			List<Edge> children = current.getChildren();
//			for (Edge edge : children) {
//				Node child = edge.getEnd();
//				if(!frontier.contains(child)) {
//				child.setParent(current);
//				frontier.add(child);
//				}
//			}
//		}
//		
//		return true;
//	}
	
	
	public boolean isAdmissibleH(Node tree, String goal) {
		Queue<Node> frontier = new LinkedList<Node>();
		Set<Node> explored = new HashSet<Node>();
		boolean result = true;

		frontier.offer(tree);

		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			explored.add(current);
			Node nodeRe = execute(current, goal);
			if (nodeRe == null) {
				return false;
			}

			if (current.getH() > nodeRe.getG())
				return false;

			for (Node child : current.getChildrenNodes())
				if (!explored.contains(child) && !frontier.contains(child)) {
					child.setParent(null);
					child.setG(0);
					result = result && isAdmissibleH(child, goal);
					if (result==false)
						return result;
				}
		}
		return true;
	}
	class NodeComparatorByHn implements Comparator<Node> {
		@Override
		public int compare(Node o1, Node o2) {
			Double h1 = o1.getH() + o1.getG();
			Double h2 = o2.getH() + o2.getG();
			int result = h1.compareTo(h2);
			if (result == 0)
				return o1.getLabel().compareTo(o2.getLabel());
			else
				return result;
		}
	}
}
