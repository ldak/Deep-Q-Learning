package neural_network;

import java.io.Serializable;
import java.util.List;


public class NeuralNetwork implements Serializable {

    private final LayerList layerList=new LayerList();
    private Matrix lastInputData;

    public NeuralNetwork(int inputLayerSize,int hiddenLayersSize,int outputLayerSize,int numberHidden){
        layerList.add(new Layer(inputLayerSize,hiddenLayersSize));
        for (int i = 0; i <numberHidden-2 ; i++) {
            layerList.add(new Layer(hiddenLayersSize,hiddenLayersSize));
        }
        layerList.add(new Layer(hiddenLayersSize,outputLayerSize));
    }

    public NeuralNetwork(NeuralNetwork neuralNetwork){
        for (Layer layer :
                neuralNetwork.layerList.getData()) {
            layerList.add(new Layer(layer));
        }
        this.lastInputData=neuralNetwork.lastInputData;
    }

    public List<Double> forwardProp(double[] data) throws Exception {
        Matrix input= Matrix.fromArray(data);
        lastInputData=input;
        // System.out.println("layer: "+1);
        layerList.first().forwardProp(input);
        for (int i = 1; i <layerList.size() ; i++) {
           // System.out.println("layer: "+(i+1));
            layerList.get(i).forwardProp(layerList.get(i-1).getValues());
        }
        return layerList.getValueOfLast().toArray();
    }

    public void backProp(double[] expected) throws Exception {
        Matrix error= Matrix.fromArray(expected);
        error = Matrix.subtract(layerList.get(layerList.size()-1).getValues(),error);
        for (int i = layerList.size()-1; i >0; i--) {
            error=layerList.get(i).backProp(error,layerList.get(i-1).getValues());
        }
        layerList.first().backProp(error,lastInputData);
    }

    public void backProp(int index,double value) throws Exception {
        if (lastInputData==null) {
            return;
        }
        List<Double> error=layerList.getValueOfLast().toArray();
        error.set(index,value);
        double[] expectedArray = new double[error.size()];
        for (int i = 0; i < expectedArray.length; i++) {
            expectedArray[i]=error.get(i);
        }
        backProp(expectedArray);
    }

    public void setLearningRate(float learningRate){
        for (Layer l :
                layerList.getData()) {
            l.setLearningRate(learningRate);
        }
    }


}
