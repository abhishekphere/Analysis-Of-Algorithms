/**
 * SortingTest.java
 *
 * Version:
 * 		 v1.2, 1/27/2018, 16:11:09
 *
 *		Initial revision
 */

/**
 * This program determines the execution time taken by the three different types of sorts
 *
 * @author Patil, Abhishek Sanjay
 *
 */


import java.util.Random; 
import java.util.Scanner;

public class SortingTest {
    public static void main(String[] args) 
    {
        //caling the two functions
        testUsingUniformData();
        testUsingGaussianData();
    }

    public static void testUsingUniformData()
    {
        //Printing out the required results by calling various functions
        long t1, t2;
        System.out.println("RESULTS FOR UNIFORMLY DISTRIBUTED DATA");
        System.out.println("----------------------------------------");
        System.out.println("-------------------------------------------------------------------------------------------------------------");
        for(int n = 100; n < 1000001; n = n* 10) {
            double arr1[] = new double[n];
            double arr2[] = new double[n];
            double arr3[] = new double[n];
            int i;
            //generating random numbers in [0,1)
            for( i = 0; i < n; i++ ) {
                arr1[i] = (double)Math.random();
                arr2[i] = arr1[i];
                arr3[i] = arr1[i];
            }
            //Insertion sort for uniform distribution
            System.out.println("Input Value(N): " + n);
            System.out.println("-------------------------------------------------------------------------------------------------------------");
            
            t1 = System.nanoTime();
            insertionSort(arr1);
            t2 = System.nanoTime();
            System.out.println("Insertion Sort : " + (t2 - t1) + " ns");
            
            //Merge sort for uniform distribution
            t1 = System.nanoTime();
            merge(arr2, 0, arr2.length - 1);
            t2 = System.nanoTime();
            System.out.println("Merge Sort : " + (t2 - t1) + " ns");

            //Bucket sort for uniform distribution
            t1 = System.nanoTime();
            bucketSort(arr3, n);
            t2 = System.nanoTime();
            System.out.println("Bucket Sort : " + (t2 - t1) + " ns");
            System.out.println();
            System.out.println();

            System.out.println("-------------------------------------------------------------------------------------------------------------");
        }
    }

    public static void testUsingGaussianData()
    {
        //Printing out the required results
        long t1, t2;
        Random random = new Random();
        System.out.println("RESULTS FOR GAUSSIAN DISTRIBUTED DATA");
        System.out.println("----------------------------------------");
        System.out.println("-------------------------------------------------------------------------------------------------------------");        for(int n = 100; n < 1000001; n = n* 10) {
        	double arr1[] = new double[n];
            double arr2[] = new double[n];
            double arr3[] = new double[n];
            int i;
            for( i = 0; i < n; i++ ) {
                //generating randoms numbers with mean 0.5 and variance 0.0001
                arr1[i] = (0.0001)*(random.nextGaussian()) + 0.5;
                arr2[i] = arr1[i];
                arr3[i] = arr1[i];
            }

            //Insertion sort for Gaussian distribution
            System.out.println("Input Value(N): " + n);
            System.out.println("-------------------------------------------------------------------------------------------------------------");
            t1 = System.nanoTime();
            insertionSort(arr1);
            t2 = System.nanoTime();
            System.out.println("Insertion Sort : " + (t2 - t1) + " ns");

            //Merge sort for uniform distribution
            t1 = System.nanoTime();
            merge(arr2, 0, arr2.length - 1);
            t2 = System.nanoTime();
            System.out.println("Merge Sort : " + (t2 - t1) + " ns"); 

            //Bucket sort for uniform distribution
            t1 = System.nanoTime();
            bucketSort(arr3, n);
            t2 = System.nanoTime();
            System.out.println("Bucket Sort : " + (t2 - t1) + " ns");
            System.out.println();
            System.out.println();
            System.out.println("-----------------------------------------------------------------------------");
        }
    }

    //Implementation of Insertion sort, which is a comparison based sort
    public static void insertionSort(double arr[]) {

        int flag = 0, index = 0;
        double num, t1, t2;
        for(int i = 0; i < arr.length ; i++) {
            num = arr[i];
            for(int j = 0; j <= i ; j++)
                if(num < arr[j]) {
                    index = j;
                    flag = 1;
                    break;
                }

            if(flag == 1) {
                for(int j = i; j > index; j--)
                    arr[j] = arr[j - 1];
                arr[index] = num;
            }
            flag = 0;
        }
    }

    //Implementation of Merge sort, which is a comparison based sort
    public static void merge(double arr[], int start, int end) {
        int middle = 0;
        if(start < end) {
            middle = (start + end) / 2;

            merge(arr, start, middle);
            merge(arr, middle+1, end);

            mergeSort(arr, start, middle, end);
        }
    }

    public static void mergeSort(double arr[], int start, int middle, int end) {

        double temp[] = new double[end - start + 1];

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

    //Implementation of Bucket sort
    public static void bucketSort(double arr[], int n) {

        MyLinkedList bucket[] = new MyLinkedList[n];

        for(int i = 0; i < n; i++) {
            bucket[i] = new MyLinkedList();
        }
        for(int i = 0; i < n; i++) {
            bucket[(int)(Math.floor(arr[i]*n))].add(arr[i]);
        }

    }
}

//class node for implementing bucket sort through linked list
class MyLinkedList{

    Node front;
    int data;
    MyLinkedList(){
        this.front = null;
    }

    public void add(double data) {
        if( front == null ) {
            front = new Node(data);
        }
        else {
            Node newNode = new Node(data);
            Node current = front;
            Node tracker = current;

            // First node of list is greater than upcoming
            if(current.data > data) {
                newNode.next = front;
                front = newNode;
            }
            // if element is inserted in center or end of list
            else {
                while( current != null) {
                    tracker = current;
                    current = current.next;

                    // if you reach at the end
                    if(current == null) {
                        tracker.next = newNode;
                        //current = newNode;
                        break;
                    }
                    // if the element to be added found in middle
                    else if(current.data > data){
                        tracker.next = newNode;
                        newNode.next = current;
                        break;
                    }
                }
            }
        }
    }
//print method
    public void print() {
        Node current = front;
        if(current == null)
            System.out.print("");
        else {
            while(current != null) {
                System.out.print(current.data +"  ");
                current = current.next;
            }
        }
    }

}

//Node class for linked list
class Node{
    Node next;
    double data;

    Node(double data){
        next = null;
        this.data = data;
    }
}

