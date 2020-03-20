import java.util.Scanner;
import java.util.Stack;

/**
 * 
 */

/**
 * @author Abhishek Patil
 *
 */
public class MazeSolver {

	/**
	 * @param args
	 */
	/*
	static String givenMaze[][] = {{"#",".","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#",".","#","#"},
								   {"#",".","#","#","#","#","#","#","#","#","#","#","#","#","#","#",".","#","#","#"},
								   {"#",".",".",".",".","#","#","#","#","#","#",".",".",".",".",".",".",".","#","#"},
								   {"#","#","#","#",".",".",".",".","#","#","#",".","#","#",".","#","#",".","#","#"},
								   {"#",".",".",".","#","#","#",".",".",".",".",".","#",".",".","#","#",".","#","#"},
								   {"#",".","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#",".","#","#"}
								  };  */
	static String givenMaze[][];
	static String mazeClone[][];
	static int rows;
	static int columns;
	static Stack st = new Stack();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner s = new Scanner(System.in);
		int n = Integer.parseInt(s.next());
		rows = n;
		int x1 = 0 , y1 = 0;
		
		
		s.nextLine();
		String row = null;
		//givenMaze =  new String[n][3];
		for(int k = 0; k < n; k++) {
			row = s.nextLine();
			if(k == 0) {
				givenMaze =  new String[n][row.length()];
				columns = row.length();
			}
			for(int l = 0; l < row.length(); l++) {
				if(row.charAt(l) == 'S') {
					x1 = k;
					y1 = l;
				}
				givenMaze[k][l] = row.charAt(l)+"";	
			}
			row = null;
		}
		mazeClone = givenMaze;
		startAt(x1, y1, x1, y1);
		int i,j;
		for(i=0;i<rows;i++) {
			for(j=0;j<columns;j++)
				System.out.print(mazeClone[i][j]+" ");
			System.out.println();
		}
	}
	
	
	public static void startAt(int i, int j, int x1, int y1) {
		
		while(true) {
			System.out.println(isCommonJoint(i,j));
		while(!isCommonJoint(i,j)) {
			mazeClone[i][j]="~";
			
	/// the following value is hardcoded change it		
				if(isExit(i,j) && i!= x1 && j!= y1)
					break;
				if(isLeft(i,j)) 
					j = j-1;
				else if(isRight(i,j))
					j = j+1;
				else if(isBackward(i,j))
					i = i+1;
				else if(isForward(i,j))
					i = i-1;
				
				
//			}
			
		}
		
		System.out.println(i+"  "+j);
		
		//if(isExit(i,j) && givenMaze[i][j] == "F") {
		if(isExit(i,j) ) {
			for(int l=0;l<rows;l++) {
				for(int k=0;k<columns;k++)
					System.out.print(mazeClone[l][k]+" ");
				System.out.println();
			}
			System.out.println("---------------------------------------------------------");
			
			return;
		}	
		System.out.println(i+"  "+j);
		
		//if(isCommonJoint(i,j) || ( isExit(i,j) && givenMaze[i][j] != "F" )) {
		if(isCommonJoint(i,j) || ( isExit(i,j) && !givenMaze[i][j].equals("F") )) {	
			st.push(i+" "+j);
			mazeClone[i][j] = "~";
			char c = chooseSomeDirection(i,j);
			switch(c) {
				case 'L':
					j = j-1;
					break;
				case 'R':
					j = j+1;
					break;
				case 'F':
					i = i-1;
					break;
				case 'B':	
					i = i+1;
					break;
			}
		}
//		System.out.println(isRight(i,j));
		
		if(isDeadend(i,j)) {
			if(!st.empty()) {
				int index = 0;
				String goBackTo = (String)st.pop();
				String irow = "";
				String jcolumn = "";
				while(goBackTo.charAt(index) != ' ') {
					irow = irow + Character.toString(goBackTo.charAt(index));
					index++;
				}
				while(index < goBackTo.length()) {
					jcolumn = jcolumn + Character.toString(goBackTo.charAt(index));
					index++;
				}
				
				i = Integer.parseInt(irow);
				j = Integer.parseInt(jcolumn);
				
				while(!isCommonJoint(i,j)) {
					
					mazeClone[i][j]="@";
					
					if(isLeft(i,j)) 
						j = j-1;
					else if(isRight(i,j))
						j = j+1;
					else if(isBackward(i,j))
						i = i+1;
					else
						i = i-1;
					
				}
				i = Integer.parseInt(irow);
				j = Integer.parseInt(jcolumn);
			}
			else 
				return;
			
		}
		
		
		for(int l=0;l<rows;l++) {
			for(int k=0;k<columns;k++)
				System.out.print(mazeClone[l][k]+" ");
			System.out.println();
		}
		System.out.println("---------------------------------------------------------");
		}
		
	}
	
	// ------------------------- MOVES -----------------------------------------------------------------------------------------//
	public static boolean isLeft(int i, int j) {
		if(j > 0)
		if(givenMaze[i][j-1].equals("+") || givenMaze[i][j-1].equals("F") ) {
			return true;
		}
		return false;
	}
	
	public static boolean isRight(int i, int j) {
		if(j < (columns-1))
		if(givenMaze[i][j+1].equals("+") || givenMaze[i][j+1].equals("F") ) {
			return true;
		}
		return false;
	}
	
	public static boolean isBackward(int i, int j) {
		if(i < (rows - 1))
		if(givenMaze[i+1][j].equals("+") || givenMaze[i+1][j].equals("F") ) {
			return true;
		}
		return false;
	}

	public static boolean isForward(int i, int j) {
		if(i > 0 ) 
		if(givenMaze[i-1][j] == "+" || givenMaze[i-1][j] == "F" ) {
			return true;
		}
		return false;
		
	}	
	
	
	//------------------------------------------- check common joints ----------------------------------------------------------//

	public static boolean isCommonJoint(int i, int j) {
		int count = 0;
		
		if(isLeft(i,j)) {
			count++;
		}
		if(isRight(i,j)) {
			count++;
		}
		if(isBackward(i,j)) {
			count++;
		}
		if(isForward(i,j)) {
			count++;
		}
		
		if(count > 1)
			return true;
		
		return false;
	}
	
	//---------------------------------checks if its exit-----------------------------------------------------------------------//
	public static boolean isExit(int i, int j) {
		
		if (i == 0 || i == (rows-1) || j == 0 || j == (columns-1) )
			return true;
		return false;
	}
	
	//----------------------------------selects a direction-----------------------------------------------------------------//
	public static char chooseSomeDirection(int i, int j) {
		
		if(isLeft(i,j))
			return 'L';
		else if(isRight(i,j))
			return 'R';
		else if(isForward(i,j))
			return 'F';
		else 
			return 'B';
	}
	
	//----------------------------------is it dead end-----------------------------------------------------------------//
	public static boolean isDeadend(int i, int j) {
		
		//if(j != 0 && j < columns-1)
		if(!isLeft(i,j) && !isRight(i,j) && !isBackward(i,j) && !isForward(i,j))
			return true;
		
		
		return false;
	}
	
}



/*  

3
S@@@
++++
@@@F


*/