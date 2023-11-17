public class Token {

    final TipoToken tipo;
    final String lexema;

    final int posicion;

    public Token(TipoToken tipo, String lexema, int posicion) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.posicion = posicion;
    }

    public Token(TipoToken tipo, String lexema) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.posicion = 0;
    }

    public int buscarTerminales() {
        if (this.tipo.toString().equals("SELECT"))
            return 0;
        else if (this.tipo.toString().equals("FROM"))
            return 1;
        else if (this.tipo.toString().equals("DISTINCT"))
            return 2;
        else if (this.tipo.toString().equals("ASTERISCO"))
            return 3;
        else if (this.tipo.toString().equals("COMA"))
            return 4;
        else if (this.tipo.toString().equals("IDENTIFICADOR"))
            return 5;
        else if (this.tipo.toString().equals("PUNTO"))
            return 6;
        else if (this.tipo.toString().equals("EOF"))
            return 7;
        return 8;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Token)) {
            return false;
        }

        if (this.tipo == ((Token) o).tipo) {
            return true;
        }

        return false;
    }

    public String toString() {
        return tipo + " " + lexema + " ";
    }
}
