public class randmst {
    public static void main(String[] args){
        if (args.length < 4) {
            System.out.println("Usage: java randmst.java 0 numpoints numtrials dimension");
            return;
        }
        int d = Integer.parseInt(args[1]);
        int numTrials = Integer.parseInt(args[2]);
        int n = Integer.parseInt(args[3]);
        // figure out how to generate random graphs
            // figure out how to create tuples
            // figure out how to make a makefile

        // print random graphs
        System.out.println("Number of dimensions: " + d);
        System.out.println("Number of samples: " + n);
    }
}
