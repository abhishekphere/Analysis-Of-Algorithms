import java.util.*;

public class Planters {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		
		int n = s.nextInt();
		int m = s.nextInt();
		int availablePlanters[] = new int[n];
		int extraPlanters[] = new int[m];
		
		int i;
		for( i = 0; i < n; i++ ) {
			availablePlanters[i] = s.nextInt();
		}
		for( i = 0; i < m; i++ ) {
			extraPlanters[i] = s.nextInt();
		}
		MergeSort(availablePlanters, 0, n-1);
		MergeSort(extraPlanters, 0, m-1);
		
		int j = 0;
		for(i = 0 ; i < n; i++) {
			
			if(i != n-1 && availablePlanters[i] != availablePlanters[i+1]) {
				availablePlanters[i] = -1;
			}
			else {
				while(j < m && extraPlanters[j] <= availablePlanters[i])
					j++;
				if(j != m) {
					availablePlanters[i] = -1;
					j++;
				}	
				else 
					break;
			}
		}
		boolean flag = true;
		for(i = 0 ; i < n; i++) {
			if( availablePlanters[i] != -1 ) {
				System.out.println("NO");
				flag = false;
				break;
			}
		}
		if( flag ) {
			System.out.println("YES");
		}
	}
	
	public static void MergeSort(int arr[], int start, int end) {
		int middle = 0;
		if(start < end) {
			middle = (start + end) / 2;
			
			MergeSort(arr, start, middle);
			MergeSort(arr, middle+1, end);
			
			sorting(arr, start, middle, end);
		}	
	}
	
	public static void sorting(int arr[], int start, int middle, int end) {

		int temp[] = new int[end - start + 1];
		
		int i = start;
		int j = middle+1;
		int k = 0;
					
		while( i <= middle && j <= end  ) {
			if(arr[i] < arr[j]) {
				temp[k] = arr[i];
				i++;
			}
			else {
				temp[k] = arr[j];
				j++;
			}
			k++;
		}
		while( i <= middle ) {
			temp[k] = arr[i];
			i++;
			k++;
		}
		while( j <= end ) {
			temp[k] = arr[j];
			j++;
			k++;
		}
		j = 0;
		for( i = start; i <= end; i++ ) {
			arr[i] = temp[j];
			j++;
		}
	}

}
