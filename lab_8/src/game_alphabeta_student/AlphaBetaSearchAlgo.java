package game_alphabeta_student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlphaBetaSearchAlgo implements ISearchAlgo {

	// function ALPHA-BETA-SEARCH(state) returns an action
	// inputs: state, current state in game
	// v <- MAX-VALUE(state, Integer.MIN_VALUE , Integer.MAX_VALUE)
	// return the action in SUCCESSORS(state) with value v
	@Override
	public void execute(Node node) {
		int v = maxValue(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
		System.out.println("values:" + v);
	}

	// function MAX-VALUE(state, alpha, beta) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- MIN_VALUE;
	// for each s in SUCCESSORS(state) do
	// v <- MAX(v, MIN-VALUE(s, alpha, beta))
	// if v >= beta then return v
	// alpha <- MAX(alpha, v)
	// return v

	public int maxValue(Node node, int alpha, int beta) {
		if (node.isTerminal())
			return node.getValue();
		else {
			int v = Integer.MIN_VALUE;
			Node bestMove = null;

			List<Node> successors = node.getChildren();
			List<Node> explored = new ArrayList<>();
			explored.addAll(successors);

			for (Node s : successors) {
				explored.remove(s);
				int minValueResult = minValue(s, alpha, beta);

				if (minValueResult > v) {
					node.setBestNextMove(s);
					v = minValueResult;
					bestMove = s;
				}

				if (v >= beta) {
					for (Node p : explored) {
						print(p);
					}
					return v;
				}
				alpha = Math.max(alpha, v);
			}
			return v;
		}
	}
	// function MIN-VALUE(state, alpha , beta) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- Integer.MAX_VALUE
	// for each s in SUCCESSORS(state) do
	// v <- MIN(v, MAX-VALUE(s, alpha , beta))
	// if v <= alpha then return v
	// beta <- MIN(beta ,v)
	// return v

	public int minValue(Node node, int alpha, int beta) {
		if (node.isTerminal())
			return node.getValue();
		else {
			int v = Integer.MAX_VALUE;
			Node bestMove = null;

			List<Node> successors = node.getChildren();
			List<Node> explored = new ArrayList<>(successors);

			for (Node s : successors) {
				explored.remove(s);
				int maxValueResult = maxValue(s, alpha, beta);

				if (maxValueResult < v) {
					v = maxValueResult;
					bestMove = s;
				}

				if (v <= alpha) {
					// Pruning
					for (Node e : explored) {
						print(e);
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
