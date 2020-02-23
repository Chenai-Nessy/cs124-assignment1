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

        return Math.sqrt(sum);
    }

    // function to return vertices from the graph
    static double[] getNode(double[][] array, int index, int d) {

        double[] node = new double[d];
        for(int i = 0; i < node.length; i++) {
           node[i] = array[i][index];
        }
        return node;
    }

    public static void main(String[] args){
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
        double[][] points = new double[dimension][numPoints];
        for (int n = 0; n < numPoints; n++) {
            for (int d = 0; d < dimension; d++) {
                points[d][n] = Math.random();
            }
        }

        // printing stuff for debugging
        System.out.print("Point 1: ");
        for (int d = 0; d < dimension; d++) {
            System.out.print(points[d][1] + "\t");
        }
        System.out.println();
        System.out.print("Point 2: ");
        for (int d = 0; d < dimension; d++) {
            System.out.print(points[d][2] + "\t");
        }
        System.out.println();

        // calculate the distance between two points
        distance = dist(getNode(points, 1, dimension), getNode(points, 2, dimension));
        System.out.println("Distance: " + distance);

        // output
        System.out.println(average + " " + numPoints + " " + numTrials + " " + dimension);
    }
}
