
/*Clase para cada recuadro de la parsing table*/

public class recuadro {
	
    private ArrayList<String> recuadro=new ArrayList<String>();
	/*recuadro*/
    recuadro(ArrayList<String> init){
        recuadro.addAll(init);
    }
    public ArrayList<String> getRecuadro(){
        return recuadro;
    }
    
}