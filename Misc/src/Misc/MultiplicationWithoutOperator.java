package Misc;

public class MultiplicationWithoutOperator {

	public static void main(String[] args) {
		int total = 0;
		int i = 3;
		int j = 7;
		while (i > 0) {
			if ((i&1)>0) {
				total += j;
			}
			i>>=1;
			j<<=1;
		}
		System.out.println(total);
	}
}
