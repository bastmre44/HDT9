import java.util.*;

public class HuffmanTree {
    Nodo raiz;

    public void construirArbol(Map<Character, Integer> frecuencias) {
        PriorityQueue<Nodo> colaPrioridad = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : frecuencias.entrySet()) {
            colaPrioridad.offer(new Nodo(entry.getKey(), entry.getValue()));
        }

        while (colaPrioridad.size() > 1) {
            Nodo izquierda = colaPrioridad.poll();
            Nodo derecha = colaPrioridad.poll();
            Nodo nuevoNodo = new Nodo('\0', izquierda.frecuencia + derecha.frecuencia);
            nuevoNodo.izquierda = izquierda;
            nuevoNodo.derecha = derecha;
            colaPrioridad.offer(nuevoNodo);
        }
        raiz = colaPrioridad.poll();
    }

    private void generarCodigosRecursivo(Nodo nodo, String codigoActual, Map<Character, String> codigos) {
        if (nodo != null) {
            if (nodo.valor != '\0') {
                codigos.put(nodo.valor, codigoActual);
            }
            generarCodigosRecursivo(nodo.izquierda, codigoActual + "0", codigos);
            generarCodigosRecursivo(nodo.derecha, codigoActual + "1", codigos);
        }
    }

    public Map<Character, String> generarCodigos() {
        Map<Character, String> codigos = new HashMap<>();
        generarCodigosRecursivo(raiz, "", codigos);
        return codigos;
    }
}
