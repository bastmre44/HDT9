class Nodo implements Comparable<Nodo> {
    char valor;
    int frecuencia;
    Nodo izquierda, derecha;

    public Nodo(char valor, int frecuencia) {
        this.valor = valor;
        this.frecuencia = frecuencia;
    }

    @Override
    public int compareTo(Nodo otro) {
        return this.frecuencia - otro.frecuencia;
    }
}
