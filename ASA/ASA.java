import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ASA {

            /*Tabla de parsing asa*/ 
            private casilla[][] parsingTable=new casilla[28][20];
            
            private int i = 0;
            private boolean hayErrores = false;
            private Token currentToken;
            private final List<Token> tokens;
                private ArrayList<String>rule0=new ArrayList<String>();
                
       /*Reglas de produccion */         
    private ArrayList<String>rule1=new ArrayList<String>();
    private ArrayList<String>rule2=new ArrayList<String>();
    private ArrayList<String>rule3=new ArrayList<String>();
    private ArrayList<String>rule4=new ArrayList<String>();
    private ArrayList<String>rule5=new ArrayList<String>();
    private ArrayList<String>rule6=new ArrayList<String>();
    private ArrayList<String>rule7=new ArrayList<String>();
    private ArrayList<String>rule8=new ArrayList<String>();
    private ArrayList<String>rule9=new ArrayList<String>();
    private ArrayList<String>rule10=new ArrayList<String>();
    private ArrayList<String>rule11=new ArrayList<String>();
    private ArrayList<String>rule12=new ArrayList<String>();
    private ArrayList<String>rule13=new ArrayList<String>();
    private ArrayList<String>rule14=new ArrayList<String>();
    private ArrayList<String>rule15=new ArrayList<String>();
    private ArrayList<String>rule16=new ArrayList<String>();

    ASA(List<Token> tokens){

        this.tokens=tokens;
        
        currentToken = this.tokens.get(i);



        // Reglas de la grámatica definidas previamente 
        rule0.add("Q");rule0.add("select");rule0.add("D");rule0.add("from");rule0.add("T"); //0. Q → select D from T
        rule1.add("D");rule1.add("distinct");rule1.add("P");//1. D → distinct P 
        rule2.add("D");rule2.add("P");//2. D → P
        rule3.add("P");rule3.add("*");//3. P → * 
        rule4.add("P");rule4.add("A");//4. P →  A
        rule5.add("A");rule5.add("A");rule5.add(",");rule5.add("A1");//5. A → A , A1 
        rule6.add("A");rule6.add("A1");//6. A → A1
        rule7.add("A1");rule7.add("id");rule7.add("A2");//7. A1 → id A2
        rule8.add("A2");rule8.add(".");rule8.add("id");//8. A2 → . id 
        rule10.add("A1");rule10.add("id");//9. A2 → Ɛ
        rule11.add("T");rule11.add("T");rule11.add(",");rule11.add("T1");//10. T → T , T1 
        rule12.add("T");rule12.add("T1");//11. T → T1
        rule13.add("T1");rule13.add("id");rule13.add("T2");//12. T1 → id T2
        rule14.add("T2");rule14.add("id");//13. T2 → id 
        rule15.add("T1");rule15.add("id");//14. T2 → Ɛ

        /*Variable auxiliar para ingresarlos a la parsingTable*/
        ArrayList<String> auxi=new ArrayList<String>();  
        
        //Casos de la parsingTable, casilla es un arraylist
        //Se definen los shifts y reducciones y producciones

        auxi.clear();
        auxi.add("acc");
        parsingTable[1][7]=new casilla(auxi);
        
        auxi.clear();
        auxi.add("s");
        auxi.add("12");
        parsingTable[2][2]=new casilla(auxi);

        auxi.clear();
        auxi.add("s");
        auxi.add("14");
        parsingTable[2][3]=new casilla(auxi);
        
        auxi.clear();
        auxi.add("s");
        auxi.add("18");
        parsingTable[2][5]=new casilla(auxi);

        auxi.clear();
        auxi.add("3");
        parsingTable[2][9]=new casilla(auxi);

        auxi.clear();
        auxi.add("23");
        parsingTable[2][10]=new casilla(auxi);

        auxi.clear();
        auxi.add("15");
        parsingTable[2][11]=new casilla(auxi);

        auxi.clear();
        auxi.add("22");
        parsingTable[2][12]=new casilla(auxi);

        auxi.clear();
        auxi.add("s");
        auxi.add("4");
        parsingTable[3][1]=new casilla(auxi);

        auxi.clear();
        auxi.add("s");
        auxi.add("6");
        parsingTable[4][4]=new casilla(auxi);

        auxi.clear();
        auxi.add("s");
        auxi.add("7");
        parsingTable[4][5]=new casilla(auxi);

        auxi.clear();
        auxi.add("5");
        parsingTable[4][15]=new casilla(auxi);

        auxi.clear();
        auxi.add("s");
        auxi.add("10");
        parsingTable[5][4]=new casilla(auxi);

        auxi.clear();
        auxi.add("r");
        auxi.add("0");
        parsingTable[5][7]=new casilla(auxi);

        auxi.clear();
        auxi.add("r");
        auxi.add("12");
        parsingTable[6][4]=new casilla(auxi);

        auxi.clear();
        auxi.add("r");
        auxi.add("12");
        parsingTable[6][7]=new casilla(auxi);

        auxi.clear();
        auxi.add("s");
        auxi.add("9");
        parsingTable[7][5]=new casilla(auxi);

        auxi.clear();
        auxi.add("8");
        parsingTable[7][17]=new casilla(auxi);

        auxi.clear();
        auxi.add("r");
        auxi.add("13");
        parsingTable[8][4]=new casilla(auxi);

        auxi.clear();
        auxi.add("r");
        auxi.add("13");
        parsingTable[8][7]=new casilla(auxi);

        auxi.clear();
        auxi.add("r");
        auxi.add("14");
        parsingTable[9][4]=new casilla(auxi);

        auxi.clear();
        auxi.add("r");
        auxi.add("14");
        parsingTable[9][7]=new casilla(auxi);

        auxi.clear();
        auxi.add("s");
        auxi.add("7");
        parsingTable[10][5]=new casilla(auxi);

        auxi.clear();
        auxi.add("11");
        parsingTable[10][16]=new casilla(auxi);

        auxi.clear();
        auxi.add("r");
        auxi.add("11");
        parsingTable[11][4]=new casilla(auxi);

        auxi.clear();
        auxi.add("r");
        auxi.add("11");
        parsingTable[11][7]=new casilla(auxi);

        auxi.clear();
        auxi.add("s");
        auxi.add("14");
        parsingTable[12][3]=new casilla(auxi);

        auxi.clear();
        auxi.add("s");
        auxi.add("18");
        parsingTable[12][5]=new casilla(auxi);

        auxi.clear();
        auxi.add("13");
        parsingTable[12][10]=new casilla(auxi);

        auxi.clear();
        auxi.add("15");
        parsingTable[12][11]=new casilla(auxi);

        auxi.clear();
        auxi.add("22");
        parsingTable[12][12]=new casilla(auxi);

        auxi.clear();
        auxi.add("r");
        auxi.add("1");
        parsingTable[13][1]=new casilla(auxi);

        auxi.clear();
        auxi.add("r");
        auxi.add("3");
        parsingTable[14][1]=new casilla(auxi);

        auxi.clear();
        auxi.add("r");
        auxi.add("4");
        parsingTable[15][1]=new casilla(auxi);

        auxi.clear();
        auxi.add("s");
        auxi.add("16");
        parsingTable[15][4]=new casilla(auxi);

        auxi.clear();
        auxi.add("s");
        auxi.add("18");
        parsingTable[16][5]=new casilla(auxi);

        auxi.clear();
        auxi.add("17");
        parsingTable[16][12]=new casilla(auxi);

        auxi.clear();
        auxi.add("r");
        auxi.add("5");
        parsingTable[17][1]=new casilla(auxi);

        auxi.clear();
        auxi.add("r");
        auxi.add("5");
        parsingTable[17][4]=new casilla(auxi);

        auxi.clear();
        auxi.add("s");
        auxi.add("20");
        parsingTable[18][6]=new casilla(auxi);

        auxi.clear();
        auxi.add("19");
        parsingTable[18][13]=new casilla(auxi);

        auxi.clear();
        auxi.add("r");
        auxi.add("7");
        parsingTable[19][1]=new casilla(auxi);

        auxi.clear();
        auxi.add("r");
        auxi.add("7");
        parsingTable[19][4]=new casilla(auxi);

        auxi.clear();
        auxi.add("s");
        auxi.add("21");
        parsingTable[20][5]=new casilla(auxi);

        auxi.clear();
        auxi.add("r");
        auxi.add("8");
        parsingTable[21][1]=new casilla(auxi);

        auxi.clear();
        auxi.add("r");
        auxi.add("8");
        parsingTable[21][4]=new casilla(auxi);
        
        auxi.clear();
        auxi.add("r");
        auxi.add("6");
        parsingTable[22][1]=new casilla(auxi);

        auxi.clear();
        auxi.add("r");
        auxi.add("6");
        parsingTable[22][4]=new casilla(auxi);

        auxi.clear();
        auxi.add("r");
        auxi.add("2");
        parsingTable[23][1]=new casilla(auxi);
    
        auxi.clear();
        auxi.add("s");
        auxi.add("2");
        parsingTable[0][0]=new casilla(auxi);

        auxi.clear();
        auxi.add("1");
        parsingTable[0][8]=new casilla(auxi);

        auxi.clear();
        auxi.add("r");
        auxi.add("10");
        parsingTable[18][1]=new casilla(auxi);

        auxi.clear();
        auxi.add("r");
        auxi.add("10");
        parsingTable[18][4]=new casilla(auxi);

        auxi.clear();
        auxi.add("r");
        auxi.add("15");
        parsingTable[7][4]=new casilla(auxi);

        auxi.clear();
        auxi.add("r");
        auxi.add("15");
        parsingTable[7][7]=new casilla(auxi);

        auxi.clear();
        auxi.add("24");
        parsingTable[4][16]=new casilla(auxi);

        auxi.clear();
        auxi.add("r");
        auxi.add("12");
        parsingTable[24][4]=new casilla(auxi);

        auxi.clear();
        auxi.add("r");
        auxi.add("12");
        parsingTable[24][7]=new casilla(auxi);

    }

    /*Algoritmo de parsing ascendente*/ 
    
    public boolean parse() {
        /*Inicializando la pila principal*/

        Stack<Integer> stackPrincipal = new Stack<Integer>();

        stackPrincipal.clear();

        int a=currentToken.buscarNoTerminales();

        int state=0;

        while(true){
            // Obtener el estado actual (s) de la pila, si está vacía, se asigna cero
            if(!stackPrincipal.empty()) state=(int)stackPrincipal.peek();
            else stackPrincipal.push(state);

            if(parsingTable[state][a]!=null&&parsingTable[state][a].getcasilla().get(0)=="s"){ //Manejo de Desplazamientos (Shifts)
                // Agregar el nuevo estado a la pila
                stackPrincipal.push(Integer.valueOf(parsingTable[state][a].getcasilla().get(1)));
                i++;// Avanzar al siguiente token
                currentToken = tokens.get(i);
                // Actualiza el número asociado al no terminal del nuevo token
                a=currentToken.buscarNoTerminales();
            }
            else if(parsingTable[state][a]!=null&&parsingTable[state][a].getcasilla().get(0)=="r"){  //MANEJO DE REDUCCIONES

                ArrayList<String>produccion=reducciones(parsingTable[state][a].getcasilla().get(1));// Obtener la producción para la reducción
                //Tamano de la produccion
                int tamProduccion=produccion.size()-1;

                String simboloInicial=produccion.get(0);

                for(int j=0;j<tamProduccion;j++){
                    stackPrincipal.pop();
                }
              
                stackPrincipal.push(Integer.valueOf(parsingTable[(int)stackPrincipal.peek()][buscarNoTerminales(simboloInicial)].getcasilla().get(0)));
        
            }
            /*Estado de aceptacion*/
            else if (parsingTable[state][a] != null && parsingTable[state][a].getcasilla().get(0) == "acc"){
                break;
            }
            else{
                hayErrores=true;
                break;
            }
     
        }
        if(currentToken.tipo == TipoToken.EOF && !hayErrores){
            System.out.println("Consulta realizada con exito");
            return  true;
        }else {
            System.out.println("Se encontraron errores");
        }
        return false;
    }
    public ArrayList<String> reducciones(String auxi){
        if(auxi.equals("0")){
            return rule0;
        }
        else if(auxi.equals("1")){
            return rule1;
        }
        else if(auxi.equals("2")){
            return rule2;
        }
        else if(auxi.equals("3")){
            return rule3;
        }
        else if(auxi.equals("4")){
            return rule4;
        }
        else if(auxi.equals("5")){
            return rule5;
        }
        else if(auxi.equals("6")){
            return rule6;
        }
        else if(auxi.equals("7")){
            return rule7;
        }
        else if(auxi.equals("8")){
            return rule8;
        }
        else if(auxi.equals("9")){
            return rule9;
        }
        else if(auxi.equals("10")){
            return rule10;
        }
        else if(auxi.equals("11")){
            return rule11;
        }
        else if(auxi.equals("12")){
            return rule12;
        }
        else if(auxi.equals("13")){
            return rule13;
        }
        else if(auxi.equals("14")){
            return rule14;
        }
        else if(auxi.equals("15")){
            return rule15;
        }
        return rule16;
        
    }
    public int buscarNoTerminales(String A){
        if(A.equals("Q")){
            return 8;
        }
        else if(A.equals("D")){
            return 9;
        }
        else if(A.equals("P")){
            return 10;
        }
        else if(A.equals("A")){
            return 11;
        }
        else if(A.equals("A1")){
            return 12;
        }
        else if(A.equals("A2")){
            return 13;
        }
        else if(A.equals("A3")){
            return 14;
        }
        else if(A.equals("T")){
            return 15;
        }
        else if(A.equals("T1")){
            return 16;
        }
        else if(A.equals("T2")){
            return 17;
        }
        else if(A.equals("T3")){
            return 18;
        }
        return -1;
    }


}


