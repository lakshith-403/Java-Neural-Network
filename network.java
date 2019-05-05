
public class network {
    layer[] layers;
    int[] l;

    public network(int[] _layers) {
        architecture.set(_layers);
        this.l = architecture.layers;
        layers = new layer[_layers.length + 1];
    }

    public void construct() {
        for (int i = 0; i < layers.length; i++) {
            layers[i] = new layer(i, l[i]);
        }
    }

    public void update(double eta) {
        for (layer _layer : layers) {
            _layer.updateWeights(eta);
        }
    }

    public double[] feedForward(double[] values) {
        layers[0].input(values);
        for (int i = 1; i < layers.length; i++) {
            layers[i].update(layers[i - 1].neurones);
        }
        return layers[layers.length - 2].output();
    }

    private double squaredError(double[] x, double[] y) {
        double error = 0;
        for (int i = 0; i < x.length; i++) {
            error += Math.pow(y[i] - x[i], 2) * (1 / 2);
        }
        return error;
    }

    public void train(int epochs, double eta, double[] x, double[] y) {
        for (int i = 0; i < epochs; i++) {
            feedForward(x);
            backProp(y);
            update(eta);
        }
    }

    private double transferDeriv(double val) {
        return val * (1.0 - val);
    }

    private void backProp(double[] y) {
        for (int j = layers.length - 2; j >= 0; j--) {
            layer _layer = layers[j];
            double[] errors = new double[_layer.neurones.length];
            int count = 0;
            if (j != layers.length - 2) {
                for (int k = 0; k < _layer.neurones.length; k++) {
                    double error = 0.0;
                    for (neurone n : layers[j + 1].neurones) {
                        error += n.weights[k] * n.error;
                    }
                    errors[count] = error;
                    count++;
                }
            } else {
                for (int k = 0; k < _layer.neurones.length; k++) {
                    neurone n = _layer.neurones[k];
                    errors[count] = y[k] - n.value;
                }
            }
            for (int k = 0; k < _layer.neurones.length; k++) {
                neurone n = _layer.neurones[k];
                n.error = errors[k] * transferDeriv(n.value);
            }
        }
    }
}