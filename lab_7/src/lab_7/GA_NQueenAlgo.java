package lab_7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GA_NQueenAlgo {
	public static final int POP_SIZE = 100;
	public static final double MUTATION_RATE = 0.03;
	public static final int MAX_ITERATIONS = 1000;
	List<Node> population = new ArrayList<Node>();
	Random rd = new Random();

	public void initPopulation() {
		for (int i = 0; i < POP_SIZE; i++) {
			Node ni = new Node();
			ni.generateBoard();
			population.add(ni);
		}
	}

	public Node execute() {
		int iteration = 0;
		Node child = new Node();
		while (iteration++ < MAX_ITERATIONS) {
			List<Node> newPopulation = new ArrayList<Node>();
			for (int i = 0; i < population.size(); i++) {
				Node x = getParentByRandomSelection();
				Node y = getParentByRandomSelection();
				child = reproduce(x, y);

				if (Math.random() * 1 < MUTATION_RATE) {
					child = mutate(child);
				}
				if (child.getH() == 0) {
					return child;
				}
				newPopulation.add(child);
			}
			population = newPopulation;
		}
		Collections.sort(population);
		return population.get(0);
	}

	// Mutate an individual by selecting a random Queen and
		// move it to a random row.
		public Node mutate(Node node) {
			Random rand = new Random();
			int index = rand.nextInt(Node.N);
			node.getState()[index].setRow(rand.nextInt(Node.N));
			return node;
		}
		
	public Node reproduce(Node x, Node y) { // Enter your code here
		Node child = new Node();
		child.generateBoard();
		int c = rd.nextInt(Node.N);
		for (int i = 0; i < c; i++) {
			child.setRow(i, x.getRow(i));
		}
		for (int i = c+1; i < Node.N; i++) {
			child.setRow(i, y.getRow(i));
		}
		
		return child;
	}

	// Select K individuals from the population at random and //select the best out
	// of these to become a parent.
	public Node getParentByTournamentSelection() {
		// Enter your code here
		int K = rd.nextInt(5);
		List<Node> list = new ArrayList<Node>();

		for (int i = 0; i < K; i++) {
			list.add(population.get(i));
		}
		Collections.sort(list);
		return list.get(0);
	}
	

	// Select a random parent from the population
	public Node getParentByRandomSelection() {
		// Enter your code here
		Random random = new Random();
		Node parent = population.get(random.nextInt(population.size()));
		return parent;
	}
}
