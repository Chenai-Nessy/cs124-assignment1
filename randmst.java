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

        double average = 0;

        for (int trial = 0; trial < numTrials; trial++) {
            double distance = 0;

            // generate random graphs
            double[][] points = new double[numPoints][dimension];
            for (int n = 0; n < numPoints; n++) {
                for (int d = 0; d < dimension; d++) {
                    points[n][d] = Math.random();
                }
            }

            // _________________________________________________________________________________________Algorithm  beginning
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
            while (counter < numPoints - 1) {
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
                                if (distance < edgeWeights[i] && (distance != 0)) {
                                    edgeWeights[i] = distance;
                                    index = j;
                                }
                            }
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
                counter++;
            }
            long endTime = System.nanoTime();

            // how long did it take
            // System.out.println("Took " + (double) (endTime - startTime) / 1000000000 + " s");

            // update the average
            average += totalWeight;

        }

        // compute the average
        average /= numTrials;

        //output
        System.out.println(average + " " + numPoints + " " + numTrials + " " + dimension);
    }
}