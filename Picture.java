/**
 * Picture.java
 *
 * Version:
 * 		 v1.2, 2/11/2018, 9:11:09
 *
 *		Initial revision
 */

/**
 * This program determines the number of swaps required to sort the people in desired manner.
 *
 * @author Patil, Abhishek Sanjay
 *
 */


import java.util.*;

class Picture
{
   
    public static void main(String[] args) 
    {        
        Scanner s = new Scanner(System.in);
        
        int n = s.nextInt();								// total no of people
        int age, teacherHeightIndex = 0;				
        float height;
        float heightOfSeven[][] = new float[(n-1)/2][2]; 	// Stores heights and indexes of people with age seven
        float heightOfEight[][] = new float[(n-1)/2][2]; 	// Stores heights and indexes of people with age eight
        int j = 0, k = 0;
        
        // Takes input and stores in heightOfSeven or heightOfEight array accordingly
        for(int i = 0; i < n; i++) {
        	
        	age = s.nextInt();
        	height = s.nextFloat();
        	
        	if(age == 7) {
        		heightOfSeven[j][0] = height;
        		heightOfSeven[j][1] = i;
        		j++;
        	}
        	else if(age == 8) {
        		heightOfEight[k][0] = height;
        		heightOfEight[k][1] = i;
        		k++;
        	}
        	else {
        		teacherHeightIndex = i;
        	}
        }
        
        computeSwaps(heightOfSeven, heightOfEight, teacherHeightIndex, n);
       
    }
    
    /*
     * This function computes the number of swaps required to set the elements in desired manner
     * 
     */ 
    public static void computeSwaps(float heightOfSeven[][], float heightOfEight[][], int teacherHeightIndex, int n) {
    	 
    	double indexesForCountingSwaps[] = new double[n];     // Stores the indexes in the manner they must appear according to question 
        int i = 0;
    	
        
        
        // Sorts array(age 7 people) in increasing order of height		  O(nlogn)
        merge(heightOfSeven, 0, heightOfSeven.length-1);
        
        // Sorts array(age 8 people) in increasing order of height		  O(nlogn)
        merge(heightOfEight, 0, heightOfEight.length-1);
        
        // Stores the indexes in the manner they must appear according to question 
        for(i = 0; i < heightOfSeven.length; i++) 
        	indexesForCountingSwaps[i] = (int)heightOfSeven[i][1];
    
        indexesForCountingSwaps[i] = teacherHeightIndex;
        i++;
        
        for(int a = heightOfEight.length - 1; a >= 0; a--) {
        	indexesForCountingSwaps[i] = (int)heightOfEight[a][1];
        	i++;
        }
        
        System.out.println(sortAndCount(indexesForCountingSwaps, 0, indexesForCountingSwaps.length-1));
      
    }
    
    /*
     * This function divides and recursively sorts the array by computing the number of swaps. 
     * 
     */
    public static int sortAndCount(double arr[], int start, int end) {
        int middle = 0;
        int count = 0;
        if(start < end) {
            middle = (start + end) / 2;

            count += sortAndCount(arr, start, middle);
            count += sortAndCount(arr, middle+1, end);

            count += mergeAndCount(arr, start, middle, end);
        }
        return count;
    }

    // This function merges two arrays in sorting manner and computes swaps required for them
    public static int mergeAndCount(double arr[], int start, int middle, int end) {

        double temp[] = new double[end - start + 1];

        int i = start;
        int j = middle+1;
        int k = 0;
        int count = 0;

        while( i <= middle && j <= end  ) {
            if(arr[i] <= arr[j]) {
                temp[k] = arr[i];
                i++;
            }
            else {
                temp[k] = arr[j];
                j++;
                count = count + (middle+1 - i);
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
        return count;
    }
    
    
    //Implementation of Merge sort for sorting 2D array.
    public static void merge(float arr[][], int start, int end) {
        int middle = 0;
        if(start < end) {
            middle = (start + end) / 2;

            merge(arr, start, middle);
            merge(arr, middle+1, end);

            mergeSort(arr, start, middle, end);
        }
    }

    public static void mergeSort(float arr[][], int start, int middle, int end) {

        float temp[][] = new float[end - start + 1][2];

        int i = start;
        int j = middle+1;
        int k = 0;

        while( i <= middle && j <= end  ) {
            if(arr[i][0] < arr[j][0]) {
                temp[k][0] = arr[i][0];
                temp[k][1] = arr[i][1];
                i++;
            }
            else {
                temp[k][0] = arr[j][0];
                temp[k][1] = arr[j][1];
                j++;
            }
            k++;
        }
        while( i <= middle ) {
            temp[k][0] = arr[i][0];
            temp[k][1] = arr[i][1]; 
            i++;
            k++;
        }
        while( j <= end ) {
            temp[k][0] = arr[j][0];
            temp[k][1] = arr[j][1];
            j++;
            k++;
        }
        j = 0;
        for( i = start; i <= end; i++ ) {
            arr[i][0] = temp[j][0];
            arr[i][1] = temp[j][1];
            j++;
        }
    }
    
}