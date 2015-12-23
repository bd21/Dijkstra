
public class VertexInfo implements Comparable<VertexInfo> {
	private String label;
	private int cost;
	private VertexInfo previous;
	private boolean known;
	
	/**
	 * Construct a new vertex
	 * @param label the label attached to this vertex
	 */
	public VertexInfo(String label, int cost, VertexInfo previous, boolean known) {
		this.label = label;
		this.cost = cost;
		this.previous = previous;
		this.known = known;
	}
	
	public void setKnown(boolean input) {
		known = input;
	}
	public boolean getKnown() {
		return known;
	}
	public void setPrev(VertexInfo x) {
		previous = x;
	}
	
	public VertexInfo getPrev() {
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
	
	public int compareTo(VertexInfo other) {
		return this.cost - other.cost;
	}
}
