import java.io.*;

public class Descompresor {
    public static void descomprimirArchivo(String compressedFile, String treeFile, String outputFile) throws IOException {
        // Leer el árbol de Huffman desde el archivo
        HuffmanTree arbol = null;
        FileInputStream treeInput = new FileInputStream(treeFile);
        ObjectInputStream objectInput = new ObjectInputStream(treeInput);
        try {
            arbol = (HuffmanTree) objectInput.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        objectInput.close();
        treeInput.close();

        // Leer el archivo comprimido y descomprimirlo
        FileInputStream inputStream = new FileInputStream(compressedFile);
        FileOutputStream outputStream = new FileOutputStream(outputFile);

        Nodo nodoActual = arbol.raiz;
        int bit;
        while ((bit = inputStream.read()) != -1) {
            for (int i = 7; i >= 0; i--) {
                if (nodoActual.izquierda == null && nodoActual.derecha == null) {
                    outputStream.write(nodoActual.valor);
                    nodoActual = arbol.raiz;
                }
                if (((bit >> i) & 1) == 0) {
                    nodoActual = nodoActual.izquierda;
                } else {
                    nodoActual = nodoActual.derecha;
                }
            }
        }

        // Cerrar streams
        inputStream.close();
        outputStream.close();

        System.out.println("Archivo descomprimido correctamente.");
    }
}
