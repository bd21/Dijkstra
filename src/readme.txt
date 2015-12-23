1. adjacentVertices has an O(E) runtime, because one vertex could potentially have all the edges connected to it, so the method would have to loop through every edge.
   edgeCost is also O(E) for the same reasons listed above.  It could have to loop through all of edges.
   shortestPath is O(Vlog(V) + Elog(V)), which variable outgrows the other depends on the density or sparsity of the graph.  A sparse graph would depend more on the vertices and a dense one more on the number of edges.

2. We ensured abstraction was protected by making another class to store important data of the Vertex objects.  If we had put these fields inside of our vertex class, we would have had to put getters and setters inside our Vertex class, which would have made it vulnerable for clients to be malicious.

3. We tested our code by using a test file to test all the edge cases (exceptions thrown, illegal inputs, and impossible starting/ending locations).  We also made graphs of odd cases, such as where there was no path, where there were multiple even paths, etc.

4. We worked together by setting out 6 hour chunks of time and talking and writing the ideas up on a whiteboard then implementing them on the computer.  The project took us 15 hours.
	One bad thing was scheduling conflicts - finding large amounts of time to spend working on this project was a hassle.
	One good thing was that we were able to combine ideas - Thang was very picky and knowledgable about the abstract ideas and I was better with knowing the API and implementing ideas.