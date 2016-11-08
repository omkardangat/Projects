package Misc;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicStrings {

	public static void main(String[]args) {
		
	}
	
	public boolean CheckIsomorphic(String a, String b) {
		if (a == null || b == null || a.length() != b.length()) {
			return false;
		}
	
		Map<Character, Character> mapped = new HashMap<Character, Character>();
		for(int i = 0; i < a.length(); i++) {
			char t1 = a.charAt(i);
			char t2 = b.charAt(i);
			
			if (mapped.containsKey(t1)) {
				if (!mapped.get(t1).equals(t2)) {
					return false;
				}
			} else {
				if (mapped.containsValue(t2)) {
					return false;
				}
				mapped.put(t1,  t2);
			}
		}
		return true;
	}
}
