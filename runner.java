public class runner {
    public static void main(String[] args) {
        network dnn = new network(new int[] { 2, 5, 5, 2 });
        dnn.construct();
        double[] in = { 0.4, 0.4 };
        dnn.train(1000000, 0.2, in, new double[] { 0.0 ,0.0 });
        double[] out = dnn.feedForward(in);
        System.out.println("++++++++++++");
        for (double val : out) {
            System.out.println(val);
        }
    }
}