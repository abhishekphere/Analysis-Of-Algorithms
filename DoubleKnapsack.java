/**
 * Donut.java
 *
 * Version:
 * 		 v1.2, 2/27/2018, 16:11:09
 *
 *		Initial revision
 */
/**
 * This program determines the maximum cost possible when splitting the n items in two different bags of weights W1 and W2 respectively.
 *
 * @author Patil, Abhishek Sanjay
 *
 */
//importing the module
import java.util.Scanner;
public class DoubleKnapsack {

    //function to return the maximum of the two values
    public static int maximum(int i, int j)
    {
        int result = Math.max(i, j);
        return result;
    }

    //function to initialize the 3-dimensional array and return the same.
    public static int[][][] init(int items[][], int W1, int W2)
    {
        int n = items.length;
        int S[][][] = new int[n + 1][W1 + 1][W2 + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= W1; j++) {
                for (int k = 0; k <= W2; k++) {
                    S[i][0][0] = 0;
                    S[i][0][k] = 0;
                    S[i][j][0] = 0;
                }
            }
        }
        return S;
    }

    //the main function to calculate the maximum cost
    public static int knapsack(int S[][][], int items[][], int W1, int W2)
    {
        int n = items.length;
        for (int i = 1; i <n; i++) {
            for (int j = 0; j <= W1; j++) {
                for (int k = 0; k <= W2; k++) {
                    S[i][j][k] = S[i - 1][j][k];

                    if (items[i][0] <= j && items[i][0] <= k){
                        S[i][j][k] = maximum((maximum(((S[i - 1][j - items[i][0]][k]) + items[i][1]), ((S[i - 1][j][k - items[i][0]]) + items[i][1]))), S[i - 1][j][k]);
                    }
                    else if (items[i][0] <= j &&items[i][0]> k ){
                        S[i][j][k] = maximum((((S[i - 1][j - items[i][0]][k]) + items[i][1])), S[i - 1][j][k]);
                    }
                    else if(items[i][0] <= k &&items[i][0] > j  ){
                        S[i][j][k] = maximum((S[i - 1][j][k - items[i][0]] + items[i][1]), S[i - 1][j][k]);
                    }
                }
            }
        }
        return S[n-1][W1][W2];
    }

    public static void main(String args[])
    {
        //taking the items, W1 and W2 as input from the user
        int n, W1, W2;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int[][] items = new int[n+1][2];
        W1 = sc.nextInt();
        W2 = sc.nextInt();
        items[0][0]=0;
        items[0][1]=0;
        for (int x = 1; x <= n; x++) {
            for (int y = 0; y < 2; y++) {
                items[x][y] = sc.nextInt();
            }
        }
        //calling the different functions
        int S[][][]= init(items, W1, W2);
        int result = knapsack(S, items, W1, W2);

        //Printing the required
        System.out.println(result);
    }
}
