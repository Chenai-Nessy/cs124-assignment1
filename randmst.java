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
        /*double[] node = new double[d];
        for(int i = 0; i < node.length; i++) {
           node[i] = array[i][index];
        }
        return node;*/
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

        //  Algorithm  beginning

        // have an array of distances
        double[] dist = new double[numPoints];

        // have an array of visited nodes
        int[] visitedNodes = new int[numPoints];

        // set everything to 1 to indicate not visited
        for (int i = 0; i < numPoints; i++) {
            visitedNodes[i] = 1;
        }


        // mark first node as visited
        visitedNodes[0] = 0;

        // marker that tells us when we want to stop iterating through our MST algorithm
        int sum = numPoints - 1;

        // MST algorithm
        while( sum > 0 ) {
            // initialize index tracker
            int index = 0;
            // calculate edges of all nodes visited combined and find the smallest that connects to nodes not visited
            for (int i = 0; i < numPoints; i++) {
                // initialize distance variable
                double smallestEdge2 = Double.POSITIVE_INFINITY;

                // go to a visited node
                if (visitedNodes[i] == 0) {
                    // calculate its distances from all other unvisited edges
                    for (int j = 0; j < numPoints; j++) {
                        if (visitedNodes[j] == 1) {
                            // calculate the distance between two points
                            distance = dist(getNode(points, i), getNode(points, j));
                            System.out.println("Distance" + j + ": " + distance);
                            if (distance < smallestEdge2 && (distance != 0) ) {
                                smallestEdge2 = distance;
                                index = j;
                                System.out.println("IndexJ = " + index);

                            }
                        }
                    }
                    // save the smallest distance into distance array
                    dist[i] = smallestEdge2;
                }
            }
            // mark the node that gave the smallest distance as visited, and update our marker
            visitedNodes[index] = 0;
            sum -= 1;
        }

        for (int i = 0; i < dist.length; i++) {
            System.out.println("Distance" + i + " = " + dist[i]);
        }
        //
        System.out.println(average + " " + numPoints + " " + numTrials + " " + dimension);
    }
}
