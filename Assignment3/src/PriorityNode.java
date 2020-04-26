public class PriorityNode<E>{
    private PriorityNode<E> next;
    private E element;
    private double priority; 
	public PriorityNode(){
		next = null;
		element = null;
		priority = Double.MAX_VALUE;
	}
	public PriorityNode(E elem, double my_priority){
		next = null;
		element = elem;
		priority = my_priority;
	}
	
	
	public E getElement() {
		return element;
		
	}
	
	public PriorityNode<E> getNext() {
		return next;
		
	}
	
	
	public double getPriority() {
		return priority;
		
	}
	public void setNext(PriorityNode<E> pNode ) {
		this.next=pNode;
		
		
	}
	public String toString(){
		   String result = "";
		   PriorityNode<E> current = next;
		   while(current != null){
			   result = result + String.valueOf(current.getElement()) + "\n";
			   current = current.getNext();
		   }
		   return result;
	   }
}
