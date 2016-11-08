package Serialize;

import java.util.StringTokenizer;

public class BTreeSerialize {
	public class Node {
		Node Right;
		Node Left;
		int value;
		public Node(int value) {
			this.value = value;
		}
	}
	
	public String Serialize (Node root, StringBuilder builder) {
		if (root == null) {
			return "#";
		}
		
		builder.append(root.value + ",");
		Serialize(root.Left, builder);
		Serialize(root.Right, builder);
		return builder.toString();
	}
	
	public Node Deserialize(String searialized) {
		if (searialized == null || searialized.isEmpty()) {
			// need return value
			return null;
		}
		
		StringTokenizer tokens = new StringTokenizer(searialized, ",");
		return Deserialize(tokens);
	}
	
	private Node Deserialize(StringTokenizer tokens) {
		if (!tokens.hasMoreTokens()) {
			return null;
		}
		
		String data = tokens.nextToken();
		if (data == "#") {
				return null;
		}
		Node node = new Node(Integer.parseInt(data));
		node.Left = Deserialize(tokens);
		node.Right = Deserialize(tokens);
		return node;
	}
}
