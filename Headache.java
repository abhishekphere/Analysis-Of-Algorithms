import java.util.*;


public class Headache {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		
		int m = s.nextInt();
		int n = s.nextInt();
		s.nextLine();
		String firstLine[] = (" " + s.nextLine().trim()).split(" ");
		String secondLine[] = (" "+s.nextLine().trim()).split(" ");
		
		int alignment[][] = new int[m+1][n+1];
		
		// If one or 2 elements are selected from a single line
		for(int i = 2; i < m+1; i++) {
			if(isCompatible(firstLine[i], firstLine[i-1])) {
				int min = 99999999;
				if (i-2 >= 0)
					min = alignment[i-2][0];
				if(4 + alignment[i-1][0] < min)
					min = 4 + alignment[i-1][0];
				alignment[i][0] = min;
			}
			else {
					int min = 99999999;
					if (i-2 >= 0)
						min = 5 + alignment[i-2][0];
					if(4 + alignment[i-1][0] < min)
						min = 4 + alignment[i-1][0];
					alignment[i][0] = min;
				}	
		}
		
		// If one or 2 elements are selected from a single line
		for(int i = 2; i < n+1; i++) {
			if(isCompatible(secondLine[i], secondLine[i-1])) {
				int min = 99999999;
				if (i-2 >= 0)
					min = alignment[0][i-2];
				if(4 + alignment[0][i-1] < min)
					min = 4 + alignment[0][i-1];
				alignment[0][i] = min;
			}
			else {
					int min = 99999999;
					if (i-2 >= 0)
						min = 5 + alignment[0][i-2];
					if(4 + alignment[0][i-1] < min)
						min = 4 + alignment[0][i-1];
					alignment[0][i] = min;
				}	
		}		
 
		int mismatchCost, gap1Cost1, gap1Cost2, gap2Cost1 = 999999999, gap2Cost2 = 99999999, min;
		for(int i = 1; i <= m ; i++) {
			for(int j = 1; j <= n; j++) {
				
				// mismatches
				if(!isCompatible(firstLine[i], secondLine[j])) {
					mismatchCost = 5 + alignment[i-1][j-1]; 
				}
				else {
					mismatchCost = alignment[i-1][j-1]; 
				}
				
				//gap if only one element selected at time
				gap1Cost1 = 4 + alignment[i-1][j];
				gap1Cost2 = 4 + alignment[i][j-1];
				
				//gap if 2 elements selected at a time
				if( i-2 >= 0 ) {
					if(isCompatible(firstLine[i-1],firstLine[i]))
						gap2Cost1 = 3 + alignment[i-2][j];
					else {
						gap2Cost1 = 8 + alignment[i-2][j];
					}
				}
				if( j-2 >= 0 ) {
					if(isCompatible(secondLine[j],secondLine[j-1]))
						gap2Cost2 = 3 + alignment[i][j-2];
					else {
						gap2Cost2 = 8 + alignment[i][j-2]; 
					}
				}
				alignment[i][j] = min(mismatchCost, gap1Cost1, gap1Cost2, gap2Cost1, gap2Cost2);
			}
		}
		 
		System.out.println(alignment[m][n]);	
	}
	
	
	public static boolean isCompatible(String a, String b) {
		
		if((a.equals("E") & b.equals("N")) || (a.equals("N") & b.equals("E")) )
			return false;
		return true;
		
	}
	
	public static int min(int ...a) {
		int min = a[0];
		for(int i: a)
			if(i < min)
				min = i;

		return min;
	}
}
