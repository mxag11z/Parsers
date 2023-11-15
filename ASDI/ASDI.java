
import java.util.List;
import java.util.Stack;

public class ASDI implements Parser {

    private int currentToken = 0;
    private boolean hayErrores = false;
    private Token preanalisis;
    private final List<Token> tokens;

    /*Definimos tabla de ASDI como una tabla de pilas */

  private Stack<String>[][] parsingTable = new Stack[11][8];
  private String[] terminales = {"SELECT", "FROM", "DISTINCT", "ASTERISCO","COMA","IDENTIFICADOR","PUNTO","EOF"};

    
    /*Aqui inicialimos la parsing table */
  ASDI(List<Token> tokens){
    this.tokens = tokens;
    preanalisis = tokens.get(currentToken);
    
    /*Q -> select D from T */
    parsingTable[0][0] = new Stack<>();
    parsingTable[0][0].push("Q");
    parsingTable[0][0].push("SELECT");
    parsingTable[0][0].push("D");
    parsingTable[0][0].push("FROM");
    parsingTable[0][0].push("T");

    /*D -> distinct P */
    parsingTable[1][2] = new Stack<>();
    parsingTable[1][2].push("D");
    parsingTable[1][2].push("DISTINCT");
    parsingTable[1][2].push("P");

    /* D -> P en */
    
    parsingTable[1][3] = new Stack<>();
    parsingTable[1][3].push("D");
    parsingTable[1][3].push("P");
    /*D -> P en id */
      
    parsingTable[1][5] = new Stack<>();
    parsingTable[1][5].push("D");
    parsingTable[1][5].push("P");

    /*p -> * EN  ASTERISCO */
    
    parsingTable[2][3] = new Stack<>();
    parsingTable[2][3].push("P");
    parsingTable[2][3].push("ASTERISCO");

    /*P -> A en IDENTIFICADOR */

    parsingTable[2][5] = new Stack<>();
    parsingTable[2][5].push("P");
    parsingTable[2][5].push("A");
    
    /*A->A1A2 en IDENTIDICADOR */
    
    parsingTable[3][5] = new Stack<>();
    parsingTable[3][5].push("A");
    parsingTable[3][5].push("A2");
    parsingTable[3][5].push("A1");

    /*A1->epsilon en from */
    parsingTable[4][1] = new Stack<>();
    parsingTable[4][1].push("A1");
    parsingTable[4][1].push("epsilon");

    /*A1->,A en , */

    parsingTable[4][4] = new Stack<>();
    parsingTable[4][4].push("A1");
    parsingTable[4][4].push("COMA");
    parsingTable[4][4].push("A");

    /*A2 -> idA3 */
    
    parsingTable[5][5] = new Stack<>();
    parsingTable[5][4].push("A2");
    parsingTable[5][4].push("IDENTIFICADOR");
    parsingTable[5][4].push("A3");

    /*A3 ->EPSILON en from */
    
    parsingTable[6][1] = new Stack<>();
    parsingTable[6][1].push("A3");
    parsingTable[6][1].push("epsilon");

    /*A3->EPSILON EN , */
    parsingTable[6][4] = new Stack<>();
    parsingTable[6][4].push("A3");
    parsingTable[6][4].push("epsilon");

    /*A3 -> .id en punto */
    parsingTable[6][6] = new Stack<>();
    parsingTable[6][6].push("A3");
    parsingTable[6][6].push("PUNTO");
    parsingTable[6][6].push("IDENTIFICADOR");
    /*T ->T2T1 */
    parsingTable[7][5] = new Stack<>();
    parsingTable[7][5].push("T");
    parsingTable[7][5].push("T2");
    parsingTable[7][5].push("T1");
    /*T1 -> COMA T */
    parsingTable[8][4] = new Stack<>();
    parsingTable[8][4].push("T1");
    parsingTable[8][4].push("COMA");
    parsingTable[8][4].push("T");
    /*T1 -> EPSILON */
    parsingTable[8][7] = new Stack<>();
    parsingTable[8][7].push("T1");
    parsingTable[8][7].push("epsilon");
    /*T2 ->idT3 */
      parsingTable[9][5] = new Stack<>();
    parsingTable[9][5].push("T2");
    parsingTable[9][5].push("IDENTIFICADOR");
    parsingTable[9][5].push("T3");
    /*T3 -> epsilon en coma */
    parsingTable[10][4] = new Stack<>();
    parsingTable[10][4].push("T3");
    parsingTable[10][4].push("epsilon");
    /*T3 -> identificador */
    parsingTable[10][5] = new Stack<>();
    parsingTable[10][5].push("T3");
    parsingTable[10][5].push("IDENTIFICADOR")
    /*T3 -> epsilon en eof */
    parsingTable[10][7] = new Stack<>();
    parsingTable[10][7].push("T3");
    parsingTable[10][7].push("epsilon");
  }

  @Override
  public boolean parse(){

    Stack<String> stackPrincipal = new Stack<>();
    stackPrincipal.push("$");
    stackPrincipal.push("Q");
    String temp;
    Stack<String> pilaTemporal
    while(!stackPrincipal.empty()){
      temp = stackPrincipal.pop();

      if(terminalOno(temp)){

          while(parsingTable[buscarNoTerminales(temp)][buscarTerminales(preanalisis)])
      }

    }

    
    
  }
  private boolean terminalOno(String compare){
    int i;
    for(i=0;i<terminales.length;i++){
      if(compare == terminales[i])
        return true;
    }
    return false;
  }

  private int buscarNoTerminales(String terminal){
  if(terminal.equals("Q"))
    return 0;
  else if(terminal.equals("D"))
    return 1;
  else if(terminal.equals("p"))
    return 2;
  else if(terminal.equals("A"))
    return 3;
    else if(terminal.equals("A1"))
    return 4;
    else if(terminal.equals("A2"))
    return 5;
    else if(terminal.equals("A3"))
    return 6;
    else if(terminal.equals("T"))
    return 7;
    else if(terminal.equals("T1"))
    return 8;
    else if(terminal.equals("T2"))
    return 9;
    else if(terminal.equals("T3"))
    return 10;
    else
    return -1;

}
}





/*while(!pilaPrincipal.empty()){
temp = pilaPrincipal.pop();
currentToken;

  if(terminalOno(temp)){
    clonar la pila;
          while(!table(temp,currentToken)){
              pilaPrincipal = pilaColanada.pop();
          }
  else if(temp == currentToken){
          pilaPrincipal.pop();
          currentTokem++;
  }
}*/

