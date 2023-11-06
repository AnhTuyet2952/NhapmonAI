package student;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class AStar implements IPuzzleAlgo{

	@Override
	public Node execute(Puzzle model) {
		 // Khởi tạo hàng đợi ưu tiên (PriorityQueue) dựa trên giá trị f(n)
	    PriorityQueue<Node> frontier = new PriorityQueue<>(PuzzleUtils.HeuristicComparatorByF);
	    Set<Node> explored = new HashSet<>();
	    // Khởi tạo trạng thái ban đầu
	    Node initialState = model.getInitialState();
	    initialState.setG(0); // Số bước từ trạng thái ban đầu tới trạng thái hiện tại

	    // Sử dụng hàm heuristic h1 hoặc h2 tùy chọn
	    // Ví dụ sử dụng h1:
//	    initialState.setH(model.computeH1(initialState));
	    // Ví dụ sử dụng h2:
	     initialState.setH(model.computeH2(initialState));	    
	    frontier.add(initialState);

	    while (!frontier.isEmpty()) {
	        Node current = frontier.poll(); // Lấy trạng thái có giá trị f(n) thấp nhất

	        // Nếu trạng thái hiện tại là trạng thái đích, trả về nó
	        if (current.getH() == 0) {
	            return current; // Trạng thái đích được tìm thấy
	        } else {
	            explored.add(current);

	            // Tạo các trạng thái con từ trạng thái hiện tại
	            List<Node> children = model.getSuccessors(current);

	            for (Node child : children) {
	            	   if (!explored.contains(child) && !frontier.contains(child)) {
	                       child.setG(current.getG() + 1); // Số bước từ trạng thái ban đầu tới trạng thái con
	                       // Sử dụng hàm heuristic h1 hoặc h2 cho trạng thái con tùy chọn
	                       // Ví dụ sử dụng h1:
//	                       child.setH(model.computeH1(child));
	                       // Ví dụ sử dụng h2:
	                        child.setH(model.computeH2(child)); 
	                    frontier.add(child);
	                }
	            }
	        }
	    }

		
		return null;
	}



}
