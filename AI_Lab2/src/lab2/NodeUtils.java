package lab2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NodeUtils {
	public static List<String > printPath(Node node){
		List<String> result = new ArrayList<String>();
		result.add(node.getLabel());
		Node tmp;
		while((tmp = node.getParent()) != null){
			result.add(tmp.getLabel());
			node = tmp;
		}
		Collections.reverse(result);
		return result;
	}
}
