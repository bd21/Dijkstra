import java.util.*;

/**
 * A representation of a graph.
 * Assumes that we do not have negative cost edges in the graph.
 */
public class MyGraph implements Graph {
    // you will need some private fields to represent the graph
    // you are also likely to want some private helper methods
	private Map <Vertex, ArrayList<Edge>> myMap;

    /**
     * Creates a MyGraph object with the given collection of vertices
     * and the given collection of edges.
     * @param v a collection of the vertices in this graph
     * @param e a collection of the edges in this graph
     */
    public MyGraph(Collection<Vertex> v, Collection<Edge> e) {
    	myMap = new HashMap<Vertex, ArrayList<Edge>>(); 
    	for(Vertex city : v) {
    		myMap.put(city, new ArrayList<Edge>());
    	}
		for(Edge trip: e){
			if(!myMap.containsKey(trip.getSource()) || !myMap.containsKey(trip.getDestination())) {
				throw new IllegalArgumentException("Trip info not added because one or both cities is not listed in the "
						+ "system :" + trip.toString());
			}
			ArrayList<Edge> temp = myMap.get(trip.getSource());
			for(Edge existingTrip : temp) {
				if(existingTrip.getSource().equals(trip.getSource()) && 
						   existingTrip.getDestination().equals(trip.getDestination()) && 
						   existingTrip.getWeight() != trip.getWeight()) {
					throw new IllegalArgumentException("The trip " + trip.toString() + " already exists with a different cost");
				}
			}
			myMap.get(trip.getSource()).add(trip);
		}			
    }

    /** 
     * Return the collection of vertices of this graph
     * @return the vertices as a collection (which is anything iterable)
     */
    public Collection<Vertex> vertices() {
    	return myMap.keySet();	
	// YOUR CODE HERE
    }

    /** 
     * Return the collection of edges of this graph
     * @return the edges as a collection (which is anything iterable)
     */
    public Collection<Edge> edges() {
    	return (Collection) myMap.values();	
	// YOUR CODE HERE

    }

    /**
     * Return a collection of vertices adjacent to a given vertex v.
     *   i.e., the set of all vertices w where edges v -> w exist in the graph.
     * Return an empty collection if there are no adjacent vertices.
     * @param v one of the vertices in the graph
     * @return an iterable collection of vertices adjacent to v in the graph
     * @throws IllegalArgumentException if v does not exist.
     */
    public Collection<Vertex> adjacentVertices(Vertex v) {
    	if(!myMap.containsKey(v)) {
    		throw new IllegalArgumentException("This city does not exist in the system");
    	}
    	Collection<Vertex> adj = new HashSet<Vertex>();
    	for(Edge trip : myMap.get(v)) {
    		adj.add(trip.getDestination());
    	}
    	return adj;
	// YOUR CODE HERE
    }

    /**
     * Test whether vertex b is adjacent to vertex a (i.e. a -> b) in a directed graph.
     * Assumes that we do not have negative cost edges in the graph.
     * @param a one vertex
     * @param b another vertex
     * @return cost of edge if there is a directed edge from a to b in the graph, 
     * return -1 otherwise.
     * @throws IllegalArgumentException if a or b do not exist.
     */
    public int edgeCost(Vertex a, Vertex b) {
    	if(!myMap.containsKey(a) || !myMap.containsKey(b)) {
    		throw new IllegalArgumentException("Either one or both of the cities given are not in the system");
    	}
    	for(Edge trip : myMap.get(a)) {
    		if(trip.getDestination().equals(b)) {
    			return trip.getWeight();
    		}
    	}
    	return -1;
	// YOUR CODE HERE

    }
    
    /*TODO make a helper for checking if city exists and throwing exception*/
    private void checkCityExist(Vertex a) {
    	
    }
    
    /**
     * Returns the shortest path from a to b in the graph, or null if there is
     * no such path.  Assumes all edge weights are nonnegative.
     * Uses Dijkstra's algorithm.
     * @param a the starting vertex
     * @param b the destination vertex
     * @return a Path where the vertices indicate the path from a to b in order
     *   and contains a (first) and b (last) and the cost is the cost of 
     *   the path. Returns null if b is not reachable from a.
     * @throws IllegalArgumentException if a or b does not exist.
     */
    
    /*
    public Path shortestPath(Vertex a, Vertex b) {
    	Set<Vertex> visited = new HashSet<Vertex>(); 
    	PriorityQueue<Vertex> PQ = new PriorityQueue<Vertex>();	// to store the vertex
    	
    	for(Vertex v : myMap.keySet()) {
    		if(v.equals(a)) {
    			v.setCost(0);
    			a = v;
    		}
    		if(v.equals(b)) {
    			b = v;
    		}
    		PQ.add(v);
    	}
    	
    	if(!myMap.containsKey(a) || !myMap.containsKey(b)) {
    		return null;
    	}
    	
    	while(!PQ.isEmpty()) {	// stop when priority queue is empty
    		Vertex current = PQ.poll();
    		visited.add(current);	// add the current city to the cloud of known
    		System.out.print("(" + current.getCost() + ")");
    		System.out.print(current.toString());
    		for(Edge trip : myMap.get(current)) {	// get every trip from current city
    			Vertex u = trip.getDestination();		// get the next possible city
    			if(!visited.contains(u)) {		// if the next possible city has not been visited
	    			int c1 = current.getCost() + trip.getWeight();
	    			int c2 = u.getCost();
	    			
	    			if(c1 < c2) {		// if the path through current is better
	    				PQ.remove(u);	// remove and update the cost of u
	    				ArrayList<Edge> myEdge = myMap.get(u);	// save the trip info from our database
	    				myMap.remove(u);	// remove the city and its trip from our database
	    				u.setCost(c1);		// update u
	    				u.setPrev(current);		// for computing the actual shortest path
	    				myMap.put(u, myEdge);	// add the updated information back to our database
	    				PQ.add(u);		// add your possible shortest path to be process
	    			}
    			}
    			System.out.println("+" + u.getCost() + "+");
    			if(current.equals(b)) {
        			//b.setCost(current.getCost());
        			break;
        		}
    		}
    	}
 
    	List<Vertex> result = new ArrayList<Vertex>();

    	while(b.getPrev() != null) {
    		result.add(b);
    		b = b.getPrev();
    	}
    	Collections.reverse(result);
    	return new Path(result, b.getCost());
    }
    */
    public Path shortestPath(Vertex a, Vertex b) {
    	PriorityQueue<VertexInfo> PQ = new PriorityQueue<VertexInfo>();	// to store the vertex
    	Map <String, VertexInfo> vertexMap = new HashMap<String, VertexInfo>();
    	/*
    	for(Vertex v : myMap.keySet()) {
    		vertexMap.put(v.getLabel(), new VertexInfo());
    		if(v.equals(a)) {
    			vertexMap.get(v.getLabel()).setCost(0);
    		}
    		PQ.add(vertexMap.get(v.getLabel()));
    	}
    	
    	while(PQ.isEmpty()) {
    		VertexInfo current = PQ.poll();
    		for(Edge trip : myMap.get(current)) {
    			String next = trip.getDestination().getLabel();
    			 
    		}
    	}
    	*/
    	for(Vertex v : myMap.keySet()) {
    		vertexMap.put(v.getLabel(), new VertexInfo(v.getLabel(), Integer.MAX_VALUE, null, false));
    		if(v.equals(a)) {
    			vertexMap.get(v.getLabel()).setCost(0);
    		}
    		//PQ.add(vertexMap.get(v.getLabel()));
    	}
    	
    	PQ.add(new VertexInfo(a.getLabel(), 0, null, false));
    	
    	while(!PQ.isEmpty()) {	
    		fVertexInfo current = PQ.poll();		// remove the lowest cost
    		if(!vertexMap.get(current.getLabel()).getKnown()) {	// if current is not known
    			vertexMap.get(current.getLabel()).setKnown(true);
	    		List<Vertex> adjList = new ArrayList<Vertex>();		
	    		for(Edge e : myMap.get(new Vertex(current.getLabel()))) {
	    			adjList.add(e.getDestination());
	    		}
	    		for (Vertex adj : adjList) {
	    			if(!vertexMap.get(adj.getLabel()).getKnown()) {	// if the cost of the potential destination is not known
	    				vertexMap.get(adj.getLabel()).setCost(current.getCost()
	    								+ edgeCost(new Vertex(current.getLabel()), adj));
	    				PQ.add(new VertexInfo(adj.getLabel(), vertexMap.get(adj.getLabel()).getCost(), current, false));
	    			}
	    		}
	    		//vertexMap.put(current.getLabel(), current);
    		}
    	}
    	
    	List<Vertex> result = new ArrayList<Vertex>();
    	int totalCost = vertexMap.get(b.getLabel()).getCost();
    	while(vertexMap.get(b.getLabel()).getPrev() != null) {
    		totalCost = vertexMap.get(b.getLabel()).getCost();
    		result.add(new Vertex(b.getLabel()));
    		b = new Vertex(vertexMap.get(b.getLabel()).getPrev().getLabel());
    	}
    	Collections.reverse(result);
    	return new Path(result, totalCost);
/*
    	construct path
    	
    	vertexMap.containsKey(v.getLabel());	// check if known
    	
    	return null;
    	*/
    }
}
