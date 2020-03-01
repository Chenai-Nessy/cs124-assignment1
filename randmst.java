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

        long startTime = System.nanoTime();

        for (int trial = 0; trial < numTrials; trial++) {
            double distance = 0; // temp distance variable
            double totalWeight = 0; // total weight of MST

            // generate random graphs
            double[][] points = new double[numPoints][dimension];
            if (dimension > 0) {
                for (int n = 0; n < numPoints; n++) {
                    for (int d = 0; d < dimension; d++) {
                        points[n][d] = Math.random();
                    }
                }
            }

            // _____________________________________________________________________________________Algorithm  beginning
            // have an array of distances; initialize them to infinity
            double[] minWeights = new double[numPoints - 1];
            for (int i = 0; i < numPoints - 1; i++) {
                    minWeights[i] = Double.POSITIVE_INFINITY;
                }

            // have an array of visited nodes; initialize everything to 1 to indicate not visited
            int[] visitedNodes = new int[numPoints];
            for (int i = 0; i < numPoints; i++) {
                    visitedNodes[i] = 1;
                }

            // mark first node as visited
            visitedNodes[0] = 0;

            // marker that tells us when we want to stop iterating through our MST algorithm
            // index variable tells us what node to look at during the next iteration
            int counter = 0;
            int index = 0;

            // MST algorithm
            while (counter < numPoints - 1) {
                // array that stores the distances to the edges not in our current MST; we'll use this to find the
                // shortest edge to the rest of the available forest nodes
                // O(n^2)
                double[] edgeWeights = new double[numPoints - 1];
                for (int i = 0; i < numPoints - 1; i++) {
                        edgeWeights[i] = Double.POSITIVE_INFINITY;
                    }

                // we're gonna store the minimum edge weight to every node from our current MST to the rest of the
                // available forest
                // O(n^2)
                for (int i = 0; i < numPoints - 1; i++) {
                    if (visitedNodes[i + 1] == 1) {
                        // calculate the distance between vertex i and the current vertex that we are considering
                        // (which is the vertex at index)
                        if (dimension > 0) {
                            distance = dist(points[i + 1], points[index]);
                        } else {
                            // this case covers the case where the dimension is 0
                            distance = Math.random();
                        }
                        edgeWeights[i] = distance;
                        // add edge to our MST edges if it's less than the one already in there
                        if (edgeWeights[i] < minWeights[i]) {
                            minWeights[i] = edgeWeights[i];
                        }
                        if (edgeWeights[i] > minWeights[i]) {
                            edgeWeights[i] = minWeights[i];
                        }
                    }
                }
                // find the minimum distance; this correponds to the next smallest edge weight connected to our current
                // minimum spanning forest -- aka the edge that we have to add
                // O(n^2)
                double minEdge = Double.POSITIVE_INFINITY;
                for (int i = 0; i < numPoints - 1; i++) {
                    if (edgeWeights[i] < minEdge) {
                        minEdge = edgeWeights[i];
                        index = i + 1;
                    }
                }

                // mark the node that gave the smallest distance as visited, and update our counter
                visitedNodes[index] = 0;
                counter++;
            }
            // add up all of the edges in our minWeights array
            for (int i = 0; i < numPoints - 1; i++) {
                average += minWeights[i];
            }
        }
        long endTime = System.nanoTime();

        // time elapsed
        System.out.println("Took " + ((float)(endTime - startTime) / 1000000000) + " s");

        // compute the average
        average /= numTrials;

        //output
        System.out.println(average + " " + numPoints + " " + numTrials + " " + dimension);
    }
}