
import java.util.ArrayList;
// Se crea cada uno de los objetos de tipo casilla y se introducen al arraylist
public class casilla {
    private ArrayList<String> casilla=new ArrayList<String>();
    casilla(ArrayList<String> auxi){
        casilla.addAll(auxi);
    }
    public ArrayList<String> getcasilla(){
        return casilla;
    }
    
}
