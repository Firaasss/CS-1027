
public class LinkedPriorityQueue<T> implements PriorityQueueADT<T>
{
	private int count;
	private PriorityNode<T> front, rear , temp , prev;

   /**
    * Creates an empty queue.
    */
	public LinkedPriorityQueue() {
		count = 0;
		front = rear = null;
	}

	@Override
	public void enqueue(T element) {
	}
	
	public void enqueue(T element, double priority){
		PriorityNode<T> node = new PriorityNode<T>(element,priority);
		if(front==null){
			front=node;
		}
		else{
			if(priority<front.getPriority()){
				node.setNext(front);
				front=node;
			}
			else{
				PriorityNode<T> current = front;
				PriorityNode<T> previous = new PriorityNode<T>();
				previous.setNext(front);
				while(current!=null && priority>=current.getPriority()){
					current=current.getNext();
					previous=previous.getNext();
				}
				previous.setNext(node);
				node.setNext(current);
			}
		}
	}

   public T dequeue() throws EmptyCollectionException{
	   if (isEmpty())
		   throw new EmptyCollectionException ("queue");

	   T result = front.getElement();
	   front = front.getNext();
	   count--;

	   if (isEmpty())
		   rear = null;

	   return result;
   }
   
   /**
    * Returns a reference to the element at the front of this queue.
    * The element is not removed from the queue.  Throws an
    * EmptyCollectionException if the queue is empty.  
    *
    * @return                            a reference to the first element in
    *                                    this queue
    * @throws EmptyCollectionsException  if an empty collection exception occurs
    */
   public T first() throws EmptyCollectionException{
 
	   if (!isEmpty())
		   return front.getElement();
	   else
		   return null;
   }


   public boolean isEmpty(){
      
	   return front==null;
   }
 

   public int size(){
	   return count;
       
   }


   public String toString(){
	   String result = "";
	   PriorityNode<T> current = front;
	   while(current != null){
		   result = result + (current.getElement()).toString() + "\n";
		   current = current.getNext();
	   }
	   return result;
   }

}