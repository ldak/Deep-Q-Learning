package neural_network;

import java.io.Serializable;

public class Layer implements Serializable {

    private Matrix values;
    private Matrix weights;
    private Matrix biases;
    private float learningRate=0.1f;

    public Layer(int prevLayerSize,int thisLayerSize){
        values =new Matrix(thisLayerSize,1);
        weights=new Matrix(thisLayerSize,prevLayerSize);
        biases=new Matrix(thisLayerSize,1);
        System.out.println(this);
    }

    public Layer(Layer layer){
        this.values=new Matrix(layer.values);
        this.weights=new Matrix(layer.weights);
        this.biases=new Matrix(layer.biases);
        this.learningRate=layer.learningRate;
    }

    public void forwardProp(Matrix prevLayerValues) throws Exception {
       // System.out.println("weights rows: "+weights.rows+" cols: "+weights.cols);
        //System.out.println("prevLayerValues rows: "+prevLayerValues.rows+" cols: "+prevLayerValues.cols);

        Matrix a=Matrix.multiply(weights,prevLayerValues);
        //System.out.println("rows: "+a.rows+" cols: "+a.cols);

        a.add(biases);
        a.sigmoid();
        values=a;
    }

    public Matrix backProp(Matrix error,Matrix prevLayer) throws Exception {
        Matrix gradient=values.dsigmoid();
        gradient.multiply(error);
        gradient.multiply(learningRate);
        Matrix a=Matrix.transpose(prevLayer);
        a=Matrix.multiply(gradient,a);
        weights.subtract(a);
        biases.subtract(gradient);
        Matrix transposedWeights=Matrix.transpose(weights);
        return Matrix.multiply(transposedWeights,gradient);
    }

    public Matrix getValues() {
        return values;
    }

    public void setLearningRate(float learningRate) {
        this.learningRate = learningRate;
    }

    @Override
    public String toString() {
        return "Layer{" +
                "values=" + values +
                ", weights=" + weights +
                ", biases=" + biases +
                ", learningRate=" + learningRate +
                '}';
    }
}
