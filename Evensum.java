/*
 * Evensum.java
 *
 * Version: Evensum.java, v 1.8 01/16/2018 10:20:12
 *
 * Revisions: Initial Revision
 *
 */

/**
 * This program reads a series of non-negative integers and prints out the sum of all inputs that are even.
 *
 * @author Abhishek Patil 
 *
 */
//importing the necessary java libraries
import java.util.Scanner;

public class Evensum {

    public static void main(String args[])
    {
        //creating the scanner class object
        Scanner scanner = new Scanner(System.in);
        //taking the number of non-negative integers as an input
        int numberOfElements = scanner.nextInt();
        //declaring the output variable
        int num, result = 0;
        //logic for calculating the sum of even numbers
        for( int i = 0; i < numberOfElements; i++ ) {
        	num = scanner.nextInt();
        	//if the element is divided by 2, add it to result
        	if( num % 2 == 0 ) {
        		result += num;
        	}
        }
        //print the final result
        System.out.println(result);
    }
}
