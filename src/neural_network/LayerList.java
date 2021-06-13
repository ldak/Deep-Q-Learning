package neural_network;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LayerList  implements Serializable {

    private List<Layer> data;

    public LayerList(){
        data=new ArrayList<>();
    }

    public void add(Layer t){
        data.add(t);
    }

    public int size(){
        return data.size();
    }

    public Layer get(int a){
        return data.get(a);
    }

    public Layer first(){
        return data.get(0);
    }

    public Layer last(){
        return data.get(data.size()-1);
    }

    public Matrix getValueOf(int a){
        return data.get(a).getValues();
    }

    public Matrix getValueOfLast(){
        return data.get(data.size()-1).getValues();
    }

    public List<Layer> getData() {
        return data;
    }
}
