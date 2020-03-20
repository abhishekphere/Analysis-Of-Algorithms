
public class ReverseAlist {

	public static void main(String args[]) {
		
		String i = "abhi";
		String a = "abhi";
		String b = a;
		String h = "abhi";
		
		System.out.println(a);
		b = "shek";
		System.out.println(b);
		
		LinkedListss l = new LinkedListss();
		l.add(1);
		l.add(2);
		l.add(3);
		l.add(4);
		l.add(5);
		
		l.print(l.front);

		Nodess newHead1 = l.reverseREC(l.front, null);
		l.print(newHead1);
	}

	
}

class LinkedListss{
	
	Nodess front, back;
	
	LinkedListss(){
		this.front = null;
		this.back = null;
	}
	
	public boolean add(int data) {
		Nodess newNode = new Nodess(data); 
		
		if (front == null) {
			front = newNode;
			back = front;
		}
		else {
			back.next = newNode;
			back = newNode;
		}
		
		return true;
	}
	
	public void print(Nodess head) {
		Nodess current = head;
		
		while(current != null) {
			System.out.print(current.data+"  ");
			current = current.next;
		}
		System.out.println();
		
	}
	
	public Nodess reverseREC(Nodess current, Nodess prev) {
		
		if(current.next == null) {
			//nl.add(current.data);
			front = current;
			
			current.next = prev;
			return null;
		}
		
			Nodess next = current.next;
			current.next = prev;
			prev = current;
			
			reverseREC( next ,prev);
		
		
		return front;
	}
	public Nodess reverse(Nodess head) {
		
		Nodess current = head;
		Nodess prev = null;
		Nodess next = null;
		
		//Nodess newNode = null;
		while(current!=null) {
			
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
			
		}
		
		return prev;
		
	}
}

class Nodess{
	int data;
	Nodess next;
	
	public Nodess(int data){
		this.data = data;
		this.next = null;
	}
}