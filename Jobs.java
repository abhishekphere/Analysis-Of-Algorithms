import java.util.*;

public class Jobs {
	
	public static int employer0[][]; 
	public static int employer1[][];
	public static int indexE0 = 0, indexE1 = 0, count = 1;
	
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		
		int n = s.nextInt();
		int jobs[][] = new int[n][3];
		int noOf0s = 0, noOf1s = 0;
		
		for(int i = 0; i < n; i++) {
			jobs[i][0] = s.nextInt();
			jobs[i][1] = s.nextInt();
			jobs[i][2] = s.nextInt();
			if(jobs[i][2] == 0)
				noOf0s++;
			else
				noOf1s++;
		}
		
		sortBothSeperately(jobs, noOf0s, noOf1s);
		
	}
	
	public static void sortBothSeperately(int jobs[][], int noOf0s, int noOf1s) {
		
		employer0 = new int[noOf0s][2];
		employer1 = new int[noOf1s][2];
		int j = 0, k = 0;
		for(int i = 0; i < jobs.length; i++) {
			if(jobs[i][2] == 0) {
				employer0[j][0] = jobs[i][0];
				employer0[j][1] = jobs[i][1];
				j++;
			}
			else if(jobs[i][2] == 1) {
				employer1[k][0] = jobs[i][0];
				employer1[k][1] = jobs[i][1];
				k++;
			}
		}
		
		
		merge(employer0, 0, employer0.length-1, 1);	
		merge(employer1, 0, employer1.length-1, 1);		
		
		countMaxJobs();
		
		
		
	} 
	
	public static void countMaxJobs() {
		
		int Ftime = employer0[0][1];
		selectCompatibleJob1(Ftime);
		int count1 = count;
		
		count = 1;
		indexE0 = 0;
		indexE1 = 0;
		
		Ftime = employer1[0][1];
		selectCompatibleJob0(Ftime);
		int count2 = count;
		
		if(count1 > count2)
			System.out.println(count1);
		else
			System.out.println(count2);
		
	}
	
	public static void selectCompatibleJob0( int time ) {
		while(indexE0 < employer0.length) {
			if(employer0[indexE0][0] >= time) {
				count += 1;
				time = employer0[indexE0][1];
				break;
			}
			indexE0++;
		}
		if(indexE0 >=  employer0.length)
			return;
		else
			selectCompatibleJob1( time );
	}
	
	
	public static void selectCompatibleJob1( int time ) {
		
		
		while(indexE1 < employer1.length) {
			if(employer1[indexE1][0] >= time) {
				count += 1;
				time = employer1[indexE1][1];
				break;
			}
			indexE1++;
		}
		if(indexE1 >=  employer1.length)
			return;
		else
			selectCompatibleJob0( time );
		
	}
	
	//Implementation of Merge sort, which is a comparison based sort
    public static void merge(int arr[][], int start, int end, int column) {
        int middle = 0;
        if(start < end) {
            middle = (start + end) / 2;

            merge(arr, start, middle, column);
            merge(arr, middle+1, end, column);

            mergeSort(arr, start, middle, end, column);
        }
    }
    
    // This function sorts and merges based on column number 
    public static void mergeSort(int arr[][], int start, int middle, int end, int column1) {

        int temp[][] = new int[end - start + 1][2];

        int i = start;
        int j = middle+1;
        int k = 0;
        int column2;
        if(column1 == 0) 
        	column2 = 1;
        else
        	column2 = 0;

        while( i <= middle && j <= end  ) {
            if(arr[i][column1] < arr[j][column1]) {
                temp[k][column1] = arr[i][column1];
                temp[k][column2] = arr[i][column2];
                i++;
            }
            else {
                temp[k][column1] = arr[j][column1];
                temp[k][column2] = arr[j][column2];
                j++;
            }
            k++;
        }
        while( i <= middle ) {
            temp[k][column1] = arr[i][column1];
            temp[k][column2] = arr[i][column2];
            i++;
            k++;
        }
        while( j <= end ) {
            temp[k][column1] = arr[j][column1];
            temp[k][column2] = arr[j][column2];
            j++;
            k++;
        }
        j = 0;
        for( i = start; i <= end; i++ ) {
            arr[i][column1] = temp[j][column1];
            arr[i][column2] = temp[j][column2];
            j++;
        }
    }
	
}

