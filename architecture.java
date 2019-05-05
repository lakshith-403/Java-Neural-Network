public class architecture {
    public static int[] layers;

    public static void set(int[] arr) {
        layers = new int[arr.length + 1];
        int pos = 0;
        for (int var : arr) {
            layers[pos] = var;
            pos++;
        }
    }
}