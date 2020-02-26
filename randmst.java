import java.lang.Math;

public class randmst {
    // function to compute Euclidean distance between two nodes
    static double dist(double[] a1, double[] a2) {
        int len = a1.length;
        double sum = 0;

        if (len != a2.length) {
            return 0;
        }

        for (int i = 0; i < len; i++) {
            sum += (a1[i] - a2[i]) * (a1[i] - a2[i]);
        }

        return (Math.sqrt(sum));
    }

    // function to return vertices from the graph
    static double[] getNode(double[][] array, int index) {
        return array[index];
    }

    public static void main(String[] args) {
        // getting values
        if (args.length < 4) {
            System.out.println("Usage: java randmst.java 0 numpoints numtrials dimension");
            return;
        }

        int numPoints = Integer.parseInt(args[1]);
        int numTrials = Integer.parseInt(args[2]);
        int dimension = Integer.parseInt(args[3]);

        double average = 0, distance = 0;

        // generate random graphs
        double[][] points = new double[numPoints][dimension];
        for (int n = 0; n < numPoints; n++) {
            for (int d = 0; d < dimension; d++) {
                points[n][d] = Math.random();
            }
        }

        // print to see if all points have been created correctly
        for (int i = 0; i < numPoints; i++) {
            for (int j = 0; j < dimension; j++) {
                System.out.println("points: " + points[i][j]);
            }
            System.out.println("");
          }

        // variable that keeps track of distance as we check each edge
        double smallestEdge = Double.POSITIVE_INFINITY;

        // checking size of points array
        System.out.println("Number of points in array: " + points.length);

        // calculate distance between the first point and all the other points.
        for (int i = 0; i < numPoints-1; i++) {
            // calculate the distance between two points
            distance = dist(getNode(points, 0), getNode(points, i+1 ));
            System.out.println("Distance Init: " + distance);
            // add distance
            if (distance < smallestEdge) {
                // update smallest edge to reflect smallest distance
                smallestEdge = distance;
            }
        }

        // print out the smallest edge from the first point to all the other points
        System.out.println("Final smallest edge: " + smallestEdge);

        //                                                                                          Algorithm  beginning
        long startTime = System.nanoTime();

        // have an array of distances; initialize them to infinity
        double[] minWeights = new double[numPoints - 1];
        for (int i = 0; i < numPoints - 1; i++) {
            minWeights[i] = 0;
        }

        // total weight of MST
        double totalWeight = 0;

        // have an array of visited nodes; initialize everything to 1 to indicate not visited
        int[] visitedNodes = new int[numPoints];
        for (int i = 0; i < numPoints; i++) {
            visitedNodes[i] = 1;
        }

        // mark first node as visited
        visitedNodes[0] = 0;

        // marker that tells us when we want to stop iterating through our MST algorithm
        int counter = 0;

        // MST algorithm
        while( counter < numPoints - 1 ) {
            // initialize index tracker. We will update the index every time we visit a node
            int index = 0;

            // have an array of distances; initialize them to infinity
            double[] edgeWeights = new double[numPoints];
            for (int i = 0; i < numPoints; i++) {
                edgeWeights[i] = Double.POSITIVE_INFINITY;
            }

            // calculate edges of all nodes visited combined and find the smallest that connects to nodes not visited
            for (int i = 0; i < numPoints; i++) {

                // go to a visited node
                if (visitedNodes[i] == 0) {
                    // calculate its distances from all other unvisited edges
                    for (int j = 0; j < numPoints; j++) {
                        if (visitedNodes[j] == 1) {
                            // calculate the distance between vertex j and vertex i for all unvisited j
                            distance = dist(getNode(points, i), getNode(points, j));
                            if (distance < edgeWeights[i] && (distance != 0) ) {
                                edgeWeights[i] = distance;
                                index = j;
                            }
                        }
                        // save the smallest distance into distance array
                        //dist[i] = smallestEdge2;
                    }
                }
            }
            // find the minimum distance; this correponds to the next smallest edge weight connected to our current
            // minimum spanning forest -- aka the edge that we have to add
            double minEdge = Double.POSITIVE_INFINITY;
            for (int i = 0; i < numPoints; i++) {
                if (edgeWeights[i] < minEdge) {
                    minEdge = edgeWeights[i];
                }
            }
            // add edge to our MST edges
            minWeights[counter] = minEdge;
            totalWeight += minEdge;

            // mark the node that gave the smallest distance as visited, and update our marker
            visitedNodes[index] = 0;
            counter ++;
        }
        long endTime = System.nanoTime();

        for (int i = 0; i < minWeights.length; i++) {
            System.out.println("Distance" + i + " = " + minWeights[i]);
        }
        System.out.println("Took " + (double) (endTime - startTime) / 1000000000 + " s");

        //output
        System.out.println(average + " " + numPoints + " " + numTrials + " " + dimension);
    }
}