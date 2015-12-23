import.java.util.PriorityQueue;
public class myPQ<E> extends PriorityQueue<E> {
	public boolean removeThis(Object o) {
		  int i = indexOf(o);
		  if (i == -1) {
			  return false;
		  } else {
			  removeAt(i);
			  return true;
		  }
	}
}
