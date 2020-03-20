import java.util.Scanner;

public class SortCards {

public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		
		int n = s.nextInt();
		int A[] = new int[n];
		
		for(int i = 0; i < n; i++) {
			A[i] = s.nextInt();
		}
		int lenOfLongestIncreSubsq = maxUsingDynamic(A, n);
		int cardsToBeMoved = n - lenOfLongestIncreSubsq;
		System.out.println(cardsToBeMoved);
	}

	/*
	 *	This function provides the the length of increasing subsequence.
	 * 
	 */	
	public static int maxUsingDynamic(int A[], int n) {  // O(n^2)
		int S[] = new int[n];							 // Dynamic array
		
		S[0] = 1;
		for(int i = 1; i < n; i++) {
			int k = i - 1;
			int max = 0;
			while(k >= 0) {
				if(A[k] < A[i] && S[k] > max) {
					max = S[k];
				}
				k--;
			}
			S[i] = max + 1;
		}
		
		int max = S[0];
		for(int i = 0; i < n; i++) {
			if(S[i] > max)
				max = S[i];
		}
		return max;
	}

}
