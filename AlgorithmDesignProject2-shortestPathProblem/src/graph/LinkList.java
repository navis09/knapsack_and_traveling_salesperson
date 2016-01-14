package graph;

public class LinkList<T> { 
	
	private Node<T> head; //Head node
	private Node<T> tail; //Tail Node
	
	/**
	 * Make empty linklist
	 */
	public LinkList() { 
		head = tail = null;    
	}  
	
	/**
	 * Node in linklist
	 */
	public static class Node<T> {  
		private T data;//Data of the node
		private Node<T> next;//Pointer to next node
		
		Node(T data) { 
		    this.setData(data);  
		    this.setNext(null);   
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public Node<T> getNext() {
			return next;
		}

		public void setNext(Node<T> next) {
			this.next = next;
		}  
  
	}  
	
	public void addHead(T point) {   
		this.head = new Node<T>(point);  
		if(tail == null) {  
		    tail = head;  
		}  
	}  
	
	public void addTail(T point){
	    tail = new Node<T>(point);  
	    head.setNext(tail);  
	}  
	
	public void insert(T point) {
		if (this.head == null) {
			addHead(point);
			
		} else if (this.tail == this.head) {
			addTail(point);
			
		} else {
	    	Node<T> preNext = head.getNext();  
			@SuppressWarnings({ "unchecked", "rawtypes" })
			Node<T> newNode = new Node(point);  
	        preNext = head.getNext();  
	        this.head.setNext(newNode);  
	        newNode.setNext(preNext);  
		}
	     
	}  
	
	public void printLinkList() {  
		Node<T> curr = this.head;  
		if (isEmpty()) {  
			try {
				throw new Exception("linklist is empty");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		while(curr != null){  
			System.out.print(curr.getData()+" ");      
			curr = curr.getNext();  
		}
		System.out.println();
	}  
	
	public void delete(T data){
		Node<T> curr = head, prev = null;
		boolean suc = false;
		while (curr != null) {
			if (curr.getData().equals(data)) {
				if (curr == head) {  
					head = curr.getNext();
					suc = true;
					return;
				}
				if (curr == tail) {
					tail = prev;
					prev.setNext(null);
					suc = true;
					return;
				} else {
					prev.setNext(curr.getNext());
					suc = true;
					return;
				}
			}

			prev = curr;
			curr = curr.getNext();	
		}
		
		if(suc == false) {
			System.out.println('\n'+"Do not exist!");
		}	
	
	}
	
	public boolean isEmpty(){
		return this.head == null || this.tail == null;
    } 

    public Node<T> getHead() {
		return head;
	}

	public void setHead(Node<T> head) {
		this.head = head;
	}

	public Node<T> getTail() {
		return tail;
	}

	public void setTail(Node<T> tail) {
		this.tail = tail;
	}
  
} 