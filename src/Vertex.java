/**
 * Representation of a graph vertex
 */
public class Vertex implements Comparable<Vertex> {
	private final String label;   // label attached to this vertex
	private int cost;
	private Vertex previous;
	
	/**
	 * Construct a new vertex
	 * @param label the label attached to this vertex
	 */
	public Vertex(String label) {
		if(label == null)
			throw new IllegalArgumentException("null");
		this.label = label;
		this.cost = Integer.MAX_VALUE;
		previous = null;
	}
	
	public void setPrev(Vertex x) {
		previous = x;
	}
	
	public Vertex getPrev() {
		return previous;
	}
	
	/**
	 * Get a vertex label
	 * @return the label attached to this vertex
	 */
	public String getLabel() {
		return label;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void setCost(int x) {
		this.cost = x;
	}
	/**
	 * A string representation of this object
	 * @return the label attached to this vertex
	 */
	public String toString() {
		return label;
	}

	//auto-generated: hashes on label
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		return result;
	}

	//auto-generated: compares labels
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Vertex other = (Vertex) obj;
		if (label == null) {
                    return other.label == null;
		} else {
		    return label.equals(other.label);
		}
	}
	
	public int compareTo(Vertex other) {
		return this.cost - other.cost;
	}
}
