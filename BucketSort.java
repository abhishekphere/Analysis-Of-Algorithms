import java.util.LinkedList;
import java.util.Random;

public class BucketSort {

	public static void main(String[] args) {
		
		double arr[] = new double[1000];			
		int i;
		Random random = new Random();
		for( i = 0; i < 1000; i++ ) {
			arr[i] = (random.nextGaussian())*(0.0001) + 0.5 ;			
		}
		System.out.println();
		long t1 = System.currentTimeMillis();
		bucketSort(arr, 1000);
		long t2 = System.currentTimeMillis();
		
		System.out.println((t2 - t1));
		
	}
	
	public static void bucketSort(double arr[], int n) {
		
		MyLinkedList1 bucket[] = new MyLinkedList1[n];
		
		for(int i = 0; i < n; i++) {
			bucket[i] = new MyLinkedList1();
		}
		for(int i = 0; i < n; i++) {
			bucket[(int)(Math.floor(arr[i]*n))].add(arr[i]);
		}
		
		//prints the sorted list
		/*
		for(int i = 0; i < n; i++) {	
			bucket[i].print();
		}*/
	}
	
}

class MyLinkedList1{
	
	Node1 front;
	int data;
	MyLinkedList1(){
		this.front = null;
	}
	
	public void add(double data) {	
		
		if( front == null ) {
			front = new Node1(data);
		}
		else {
			Node1 newNode = new Node1(data);
			Node1 current = front;
			Node1 tracker = current;
			
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
	
	public void print() {
		Node1 current = front;
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

class Node1{
	Node1 next;
	double data;
	
	Node1(double data){
		next = null;
		this.data = data;
	}
}

