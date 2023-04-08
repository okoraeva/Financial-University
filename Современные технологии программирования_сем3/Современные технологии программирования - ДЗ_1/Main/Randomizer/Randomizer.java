package Randomizer;

import java.util.Random;

public class Randomizer {
    double[] values;
    double[] weights;

    public Randomizer(double[] values, int[] weights) {
        this.values = values;

        double[] newWeights = new double[weights.length];
        double sumator = 0;
        for(int i = 0; i < weights.length; i++){
            sumator += weights[i];
        }
        double weight = 1.0/sumator;
        sumator = 0;
        for(int i = 0; i < newWeights.length; i++) {
            sumator += weight * weights[i];
            newWeights[i] = sumator;
            if (i + 1 == newWeights.length) {
                newWeights[i] = 1.0;
            }
        }
        this.weights = newWeights;
    }

    public double random(){
        Random random = new Random();
        double value = random.nextDouble();
        for(int i = 0; i < weights.length; i++){
            if(value <= weights[i]){
                return values[i];
            }
        }
        return 0;
    }
}
