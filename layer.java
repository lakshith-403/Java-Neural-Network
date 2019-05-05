public class layer {
    public neurone[] neurones;
    public int position;

    public layer(int pos, int length) {
        this.neurones = new neurone[length];

        for (int i = 0; i < length; i++) {
            neurones[i] = new neurone(i, pos);
        }

        this.position = pos;
    }

    public void update(neurone[] nlayer) {
        for (neurone n : neurones) {
            n.update(nlayer);
        }
    }

    public void updateWeights(double eta){
        for (neurone n : this.neurones) {
            n.weightUpdate(eta);
        }
    }

    public void input(double[] values) {
        int i = 0;
        for (neurone n : neurones) {
            n.value = values[i];
            i++;
        }
    }

    public double[] output() {
        double[] returnVal = new double[neurones.length];
        for (int i = 0; i < neurones.length; i++) {
            returnVal[i] = neurones[i].value;
        }
        return returnVal;
    }
}