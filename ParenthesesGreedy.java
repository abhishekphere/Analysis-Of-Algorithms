
// Add a stack class Also!!

import java.util.Scanner;

public class ParenthesesGreedy {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		
		Stack st = new Stack();
		
		String expression = s.nextLine();
		
		String array[] = expression.trim().split(" ");
		
		for(int i = 0; i < array.length; i++) {
			if(array[i].charAt(0) == '+') {
				
				int a = Integer.parseInt(array[i+1]);
				int b = st.peek();
				st.pop();
				st.push(a+b);
				
				i++;
			}
			else if(array[i].charAt(0) == '*') {
				continue;
			}
			else {
				st.push(Integer.parseInt(array[i]));
			}
			
		}
		int maxValue = 1, top;
		int size = st.size();
		for(int i = 0; i < size; i++) {
			top = st.peek();
			st.pop();
			maxValue = maxValue * top;  
		}
		System.out.println(maxValue);
	}

}

