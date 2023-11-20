package lab_7;

public class Test {
	public static void main(String[] args) {

		
		GA_NQueenAlgo a = new GA_NQueenAlgo();
		a.initPopulation();
		Node x = a.getParentByRandomSelection();
		Node y = a.getParentByTournamentSelection();
		Node child = a.reproduce(x, y);
		System.out.println("cha");
		x.displayBoard();
		System.out.println("me");
		y.displayBoard();
		System.out.println("con");
		child.displayBoard();
	}
}
