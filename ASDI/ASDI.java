
import java.util.List;
import java.util.Stack;

public class ASDI implements Parser {

  private int currentToken = 0;
  private boolean hayErrores = false;
  public Token preanalisis;
  private final List<Token> tokens;

  /* Definimos tabla de ASDI como una tabla de pilas */

  private Stack<String>[][] parsingTable = new Stack[12][9];

  private String[] terminales = { "SELECT", "FROM", "DISTINCT", "ASTERISCO", "COMA", "IDENTIFICADOR", "PUNTO", "EOF" };

  /* Aqui inicialimos la parsing table */
  ASDI(List<Token> tokens) {
    this.tokens = tokens;
    preanalisis = tokens.get(currentToken);

    /* Q -> select D from T */
    parsingTable[0][0] = new Stack<String>();
    parsingTable[0][0].push("SELECT");
    parsingTable[0][0].push("D");
    parsingTable[0][0].push("FROM");
    parsingTable[0][0].push("T");

    /* D -> distinct P */
    parsingTable[1][2] = new Stack<String>();
    parsingTable[1][2].push("DISTINCT");
    parsingTable[1][2].push("P");

    /* D -> P en */

    parsingTable[1][3] = new Stack<String>();
    parsingTable[1][3].push("P");
    /* D -> P en id */

    parsingTable[1][5] = new Stack<String>();
    parsingTable[1][5].push("P");

    /* p -> * EN ASTERISCO */

    parsingTable[2][3] = new Stack<String>();
    parsingTable[2][3].push("ASTERISCO");

    /* P -> A en IDENTIFICADOR */

    parsingTable[2][5] = new Stack<String>();
    parsingTable[2][5].push("A");

    /* A->A1A2 en IDENTIDICADOR */

    parsingTable[3][5] = new Stack<String>();
    parsingTable[3][5].push("A2");
    parsingTable[3][5].push("A1");

    /* A1->epsilon en from */
    parsingTable[4][1] = new Stack<String>();
    parsingTable[4][1].push("epsilon");

    /* A1->,A en , */

    parsingTable[4][4] = new Stack<String>();
    parsingTable[4][4].push("COMA");
    parsingTable[4][4].push("A");

    /* A2 -> idA3 */

    parsingTable[5][5] = new Stack<String>();
    parsingTable[5][5].push("IDENTIFICADOR");
    parsingTable[5][5].push("A3");

    /* A3 ->EPSILON en from */

    parsingTable[6][1] = new Stack<String>();
    parsingTable[6][1].push("epsilon");

    /* A3->EPSILON EN , */
    parsingTable[6][4] = new Stack<String>();
    parsingTable[6][4].push("epsilon");

    /* A3 -> .id en punto */
    parsingTable[6][6] = new Stack<String>();
    parsingTable[6][6].push("PUNTO");
    parsingTable[6][6].push("IDENTIFICADOR");
    /* T ->T2T1 */
    parsingTable[7][5] = new Stack<String>();
    parsingTable[7][5].push("T2");
    parsingTable[7][5].push("T1");
    /* T1 -> COMA T */
    parsingTable[8][4] = new Stack<String>();
    parsingTable[8][4].push("COMA");
    parsingTable[8][4].push("T");
    /* T1 -> EPSILON */
    parsingTable[8][7] = new Stack<String>();
    parsingTable[8][7].push("epsilon");
    /* T2 ->idT3 */
    parsingTable[9][5] = new Stack<String>();
    parsingTable[9][5].push("IDENTIFICADOR");
    parsingTable[9][5].push("T3");
    /* T3 -> epsilon en coma */
    parsingTable[10][4] = new Stack<String>();
    parsingTable[10][4].push("epsilon");
    /* T3 -> identificador */
    parsingTable[10][5] = new Stack<String>();
    parsingTable[10][5].push("IDENTIFICADOR");
    /* T3 -> epsilon en eof */
    parsingTable[10][7] = new Stack<String>();
    parsingTable[10][7].push("epsilon");
  }

  @Override
  public boolean parse() {

    Stack<String> stackPrincipal = new Stack<>();

    stackPrincipal.push("EOF");
    stackPrincipal.push("Q");

    String temp;
    // Stack<String> pilaTemporal;
    while (!stackPrincipal.isEmpty()) {

      temp = stackPrincipal.peek();
      // System.out.println(temp);
      /* Si es no terminal entra */
      if (!terminalOno(temp)) {
        System.out.println("No terminal en stack principal:" + stackPrincipal.pop());
        /* Cuando no hay produccion en dichas coordenadas de la tabla hay error */
        if (parsingTable[buscarNoTerminales(temp)][preanalisis.buscarTerminales()] == null) {
          hayErrores = true;
          System.out.println("No se encontro produccion para: {" + temp + "," + preanalisis.tipo.toString() + "}");
          break;
        }
        /*
         * Si el elemento en la tabla es diferente de epsilon, entonces si vacia la pila
         * en la pila principal, de otro
         * modo no hacer nada.
         */
        else if (parsingTable[buscarNoTerminales(temp)][preanalisis.buscarTerminales()].peek() != "epsilon") {
          Stack<String> pilaTemporal = clonarPila(
              parsingTable[buscarNoTerminales(temp)][preanalisis.buscarTerminales()]);
          System.out.println("{" + temp + "," + preanalisis.tipo.toString() + "}");
          while (!pilaTemporal.isEmpty()) {
            /* Vacia los elementos en la pila principal en reversa */
            System.out.println("No Terminal:" + pilaTemporal.peek());
            stackPrincipal.push(pilaTemporal.pop());
          }
        }
      } else {
        /*
         * En caso de que sea un terminal, entonces compara que exista un matching entre
         * el token actual
         * y el tope de la pila (temp), si es asi avanzar recorriendo el token. Cuando
         * se encuentra que el current token
         * es EOF, entonces ya no avanza recorriendo los tokens pues ya no existen,
         * simplemente se espera
         * a que se vacie la pila.
         */
        System.out.println("Token actual: " + preanalisis.tipo.toString());
        System.out.println("Terminal: " + stackPrincipal.pop());
        if (temp == preanalisis.tipo.toString() && temp != "EOF") {
          System.out.println("Matching: (" + temp + "," + preanalisis.tipo.toString() + ")");
          currentToken++;
          preanalisis = tokens.get(currentToken);
        }
      }
    }
    /* Si se encontraron errores */
    if (hayErrores) {
      System.out.println("Sentencia Incorrecta");
      return false;
    }
    /* No hubo errores */
    System.out.println("Sentencia Correcta");
    return true;

  }

  /*
   * Funcion que nos dice si un string pertenece al conjunto de los terminales de
   * la gramatica
   */
  private boolean terminalOno(String compare) {
    int i;
    for (i = 0; i < terminales.length; i++) {
      if (compare == terminales[i])
        return true;
    }
    return false;
  }

  /* Clona una Pila */
  public Stack<String> clonarPila(Stack<String> original) {
    // Paso 1: Crear una pila temporal
    Stack<String> tempStack = new Stack<>();
    // Paso 2: Mover todos los valores de la pila original a la pila temporal
    while (!original.isEmpty()) {
      tempStack.push(original.pop());
    }

    // Paso 3: Crear una pila de copia
    Stack<String> copyStack = new Stack<>();
    // Paso 4: Mover valores de la pila temporal a ambas pilas (original y copia)
    while (!tempStack.isEmpty()) {
      String element = tempStack.peek();
      original.push(element); // Devolver elementos a la pila original
      copyStack.push(element); // Llenar la pila de copia
      tempStack.pop(); // Eliminar elementos de la pila temporal
    }

    return copyStack; // Devolver la pila clonada
  }

  /* Asigna un numero a cada no terminal, para poder rastrear en tabla */
  private int buscarNoTerminales(String terminal) {
    if (terminal.equals("Q"))
      return 0;
    else if (terminal.equals("D"))
      return 1;
    else if (terminal.equals("P"))
      return 2;
    else if (terminal.equals("A"))
      return 3;
    else if (terminal.equals("A1"))
      return 4;
    else if (terminal.equals("A2"))
      return 5;
    else if (terminal.equals("A3"))
      return 6;
    else if (terminal.equals("T"))
      return 7;
    else if (terminal.equals("T1"))
      return 8;
    else if (terminal.equals("T2"))
      return 9;
    else if (terminal.equals("T3"))
      return 10;
    else
      return 11;

  }
}
