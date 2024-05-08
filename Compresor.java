import java.io.*;
import java.util.*;

public class Compresor {
    public static void comprimirArchivo(String inputFile, String outputFile, String treeFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        StringBuilder texto = new StringBuilder();
        String linea;
        while ((linea = reader.readLine()) != null) {
            texto.append(linea);
        }
        reader.close();

        Map<Character, Integer> frecuencias = new HashMap<>();
        for (char c : texto.toString().toCharArray()) {
            frecuencias.put(c, frecuencias.getOrDefault(c, 0) + 1);
        }

        HuffmanTree arbol = new HuffmanTree();
        arbol.construirArbol(frecuencias);
        Map<Character, String> codigos = arbol.generarCodigos();

        StringBuilder textoCodificado = new StringBuilder();
        for (char c : texto.toString().toCharArray()) {
            textoCodificado.append(codigos.get(c));
        }

        int padding = 8 - textoCodificado.length() % 8;
        for (int i = 0; i < padding; i++) {
            textoCodificado.append('0');
        }

        String paddingInfo = String.format("%8s", Integer.toBinaryString(padding)).replace(' ', '0');

        FileOutputStream outputStream = new FileOutputStream(outputFile);
        outputStream.write((byte) Integer.parseInt(paddingInfo, 2));

        for (int i = 0; i < textoCodificado.length(); i += 8) {
            String byteString = textoCodificado.substring(i, Math.min(i + 8, textoCodificado.length()));
            outputStream.write((byte) Integer.parseInt(byteString, 2));
        }

        outputStream.close();
        System.out.println("Archivo comprimido correctamente.");

        // Guardar el árbol de Huffman en un archivo
        FileOutputStream treeOutputStream = new FileOutputStream(treeFile);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(treeOutputStream);
        objectOutputStream.writeObject(arbol);
        objectOutputStream.close();
        treeOutputStream.close();
    }

    // Método para imprimir el árbol de Huffman (solo para propósitos de depuración)
    public static void imprimirArbol(HuffmanTree arbol) {
        imprimirArbolRecursivo(arbol.raiz, "");
    }

    private static void imprimirArbolRecursivo(Nodo nodo, String prefijo) {
        if (nodo != null) {
            if (nodo.valor != '\0') {
                System.out.println(prefijo + nodo.valor);
            } else {
                imprimirArbolRecursivo(nodo.izquierda, prefijo + "0");
                imprimirArbolRecursivo(nodo.derecha, prefijo + "1");
            }
        }
    }
}
