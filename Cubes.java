/*
 * Cubes.java
 *
 * Version: Cubes.java, v 1.8 01/16/2018 09:20:12
 *
 * Revisions: Initial Revision
 *
 */

/**
 * This program prints out all the perfect cubes within a given range
 *
 * @author Abhishek Patil 
 *
 */
//importing the necessary java libraries
import java.util.*;
public class Cubes
{
    public static void main(String args[])
    {
        //creating scanner class object
        Scanner scanner = new Scanner(System.in);
        //storing the input in number
        int number = scanner.nextInt();
        //declaring the loop variable
        int i;
        //declaring the cube variable used for storing the cube of a number
        int cube;

            //logic
            for (i = 0; i <= number; i++) {
                //computing the cube
                cube = i * i * i;
                //if cube is less than the number, print the cube
                if ((cube) <= number) {
                    System.out.println(cube);
                }
				else
					break;
            }
    }
}