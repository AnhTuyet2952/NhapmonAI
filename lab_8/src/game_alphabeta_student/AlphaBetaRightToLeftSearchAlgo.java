package game_alphabeta_student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlphaBetaRightToLeftSearchAlgo implements ISearchAlgo {

	@Override
	public void execute(Node node) {
		 int v = maxValueRightToLeft(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
		    System.out.println("Values: " + v);
	}
	public int maxValueRightToLeft(Node node, int alpha, int beta) {
	    if (node.isTerminal())
	        return node.getValue();
	    else {
	        int v = Integer.MIN_VALUE;
	        List<Node> successors = node.getChildren();
	        List<Node> pruned = new ArrayList<>();
	        pruned.addAll(successors);
	        Collections.reverse(successors); 
	        for (Node s : successors) {
	            pruned.remove(s);
	            v = Math.max(v, minValueRightToLeft(s, alpha, beta));
	            node.setBestNextMove(s);
	            if (v >= beta) {
	                for (Node p : pruned) {
                    print(p);
	                }
	                return v;
	            }
	            alpha = Math.max(alpha, v);
	        }
	        return v;
	    }
	}

	public int minValueRightToLeft(Node node, int alpha, int beta) {
	    if (node.isTerminal())
	        return node.getValue();
	    else {
	        int v = Integer.MAX_VALUE;
	        List<Node> successors = node.getChildren();
	        List<Node> pruned = new ArrayList<>();
	        pruned.addAll(successors);
	        Collections.reverse(successors); 
	        for (Node s : successors) {
	            pruned.remove(s);
	            v = Math.min(v, maxValueRightToLeft(s, alpha, beta));
	            if (v <= alpha) {
	                // Pruning
	                for (Node p : pruned) {
	                    print(p);
	                }
	                return v;
	            }
	            beta = Math.min(beta, v);
	        }
	        return v;
	    }
	}

	public void print(Node node) {
		if (node.isTerminal()) {
			System.out.println(node.getLabel() + ":" + node.getValue());
		} else {
			System.out.println(node.getLabel());
			for (Node child : node.getChildren()) {
				if (child.isTerminal()) {
					System.out.println(child.getLabel() + ":" + child.getValue());
					continue;
				}
				print(child);
			}
		}
	}
}
