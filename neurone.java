public class neurone {
    public double value;
    public double bias;
    public double[] weights;
    public double error;

    public int position;
    public int layer;

    public neurone(int _position, int _layer) {
        this.value = 0.0;
        this.bias = 0.0;
        this.error = 0.0;
        this.weights = new double[architecture.layers[layer + 1]];

        for (int i = 0; i < weights.length; i++) {
            weights[i] = NP.uniform();
        }

        this.position = _position;
        this.layer = _layer;
    }

    public double getWeight(neurone N) {
        int pos = N.position;
        return weights[pos];
    }

    public void update(neurone[] nlayer) {
        double sum = 0.0;
        for (neurone N : nlayer) {
            sum += (N.value * N.getWeight(this));
        }
        sum += this.bias;
        sum = 1 / (1 + Math.exp(-sum));
        this.value = sum;
    }

    public void weightUpdate(double eta){
     for(int i=0;i<this.weights.length;i++){
         this.weights[i] += eta * error *this.value;
     }   
    }
}