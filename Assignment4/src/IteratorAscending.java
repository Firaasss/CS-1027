import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorAscending<T> implements Iterator<IteratorAscending> {
	
	//Attributes
	private int count; //number of elements in ordered list
	private int current; //current position in the iteration
	private T[] items; //items in the collection
	
	public IteratorAscending (T[] collection, int size) {
		items = collection;
		count = size;
		current = 0;
	}
	
	public boolean hasNext() {
		return (current < count);
	}
	
	public T next() {
		if (!hasNext())
			throw new NoSuchElementException();
		current++;
		return items[current-1];
	}
}
