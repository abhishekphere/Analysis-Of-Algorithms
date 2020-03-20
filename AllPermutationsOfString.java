
import java.util.*;

public class AllPermutationsOfString {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
			
		String str = s.nextLine();
		
//		permutations(str, 0, str.length());
		permute(str, 0, str.length());
	}
	
	
	public static void permutations(String str, int l, int r) {
		if(l >= r) {
			System.out.println(str);
			return;
		}
		for(int i = l; i < r; i++) {
			str = swap(str, l, i);
			permutations(str, l+1, r);
		}
	}

	public static String swap(String str, int i, int j) {
		char carray[] = str.toCharArray();
		
		char temp = carray[j];
		carray[j] = carray[i];
		carray[i] = temp;
		
		return String.valueOf(carray);
	}
	
	public static void permute(String s, int start, int end) {	
		if(start >= end)
			System.out.println(s);
		else {
			int  j = start;
			for(int i = start; i < end; i++) {
				permute(swap(s, j, i), start+1, end);
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
