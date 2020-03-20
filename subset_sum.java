import java.util.*;

public class subset_sum
{
	public static void packetsUsed(int packets[], int backTracker[], int n, int amountLeft) {
		int count = 1;
		  for (int i = n; i >= 1; i--) {
			  int num = packets[backTracker[i]];
			  while(i > 1 && num == packets[backTracker[i-1]]) {
				  count++;
				  i--;
			  }
			  System.out.print(count + ":" + packets[backTracker[i]] + " ");
			  count = 1;
		  }
		  System.out.println(amountLeft);
		  
		}

		public static boolean comboPossible(int target, int sum, int packets[], int noOfPackets, int backTracker[], int n, int amountLeft) {
		  if (sum > target) {
		    return false;
		  }
		  if (sum == target) {
			  packetsUsed(packets, backTracker, n, amountLeft);
		    return true;
		  }  

		  for (int i = backTracker[n]; i < noOfPackets; i++) {
			  backTracker[n+1] = i;
		    if(comboPossible(target, sum + packets[i], packets, noOfPackets, backTracker, n+1, amountLeft))
		    	return true;
		  }
		return false;
		}

		public static boolean solver(int target, int packets[], int noOfPackets, int amountLeft) {
		  int backTracker[] = new int[100];
		  ArrayList al = new ArrayList();
		  backTracker[0] = 0;
		  return comboPossible(target, 0, packets, noOfPackets, backTracker, 0, amountLeft);
		}
    
    public static void main (String args[])
    {	
    	  Scanner s = new Scanner(System.in);
    	  ArrayList<Double> al = new ArrayList<Double>();
    	  al.add(3.2);
    	  al.add(5.4);
    	  al.add(6.8);
    	  double d[] = new double[al.size()];
    	  for(int i =0; i< al.size(); i++) {
    		  d[i] = al.get(i);
    	  }
    	  for(int i =0; i< al.size(); i++) {
    		 System.out.println(d[i]);
    	  }
          int B = s.nextInt();
          int W = s.nextInt();
          int N = s.nextInt();
          
          int amountLeft = B - W;
          
          if(amountLeft < 0) {
        	  System.out.println("Not enough balance");
          }
          else {
        	  int packets[] = new int[N];
              for(int i = 0; i < N; i++) {
            	  packets[i] = s.nextInt(); 
              }
        
            //  int finalSum = 567;
              int n = packets.length;
              Arrays.sort(packets);
              int j = n-1;
              for(int i = 0 ; i < n/2 ; i++) {
            	  int temp = packets[i];
            	  packets[i] = packets[j];
            	  packets[j] = temp;
            	  j--;
              }

              if(!solver(W, packets, n, amountLeft)) {
            	  System.out.println("Cannot put into packets");
              }
              
          }
    }
}


/*
 * 
 1000
567
5
2
5
10
50
100
 * 
 */


