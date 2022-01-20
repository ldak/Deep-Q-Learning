package utils.logger;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Logger {

    public final List<IntegerField> fields=new ArrayList<>();

    public Logger(String [] fields){

        for (String field: fields) {
            this.fields.add(new IntegerField(field));
        }

    }

    public void increaseField(String field,int a){
        for (IntegerField intField:fields) {
            if (intField.getName().equals(field))
                intField.increase(a);
        }
    }

    public void increaseField(String field){
        increaseField(field,1);
    }

    public int getFieldValue(String field){
        for (IntegerField intField: fields) {
            if (intField.getName().equals(field))
                return intField.getValue();
        }
        return Integer.MIN_VALUE;
    }

    public void log(){
        log(System.out);
    }

    public void log(PrintStream out){
        out.println("-----------------");
        for (IntegerField intField:fields) {
            out.println(intField);
        }
    }

    public void reset() {
        for (IntegerField field:fields) {
            field.setValue(0);
        }
    }
}
