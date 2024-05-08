import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
// Hoja de trabajo 9, algoritmos y estructuras de datos
//Nina Nájera 231088
//Mishell Ciprian 231169

public class Main {
    public static void main(String[] args) {
        // Comprimir un archivo
        String inputFile = "texto_prueba.txt";
        String compressedFile = "texto.huff";
        String treeFile = "tree.tree";
        try {
            Compresor.comprimirArchivo(inputFile, compressedFile, treeFile);
            System.out.println("Archivo comprimido correctamente.");

            // Imprimir el árbol de Huffman (solo para propósitos de depuración)
            HuffmanTree arbol = new HuffmanTree();
            FileInputStream treeInput = new FileInputStream(treeFile);
            ObjectInputStream objectInput = new ObjectInputStream(treeInput);
            arbol = (HuffmanTree) objectInput.readObject();
            objectInput.close();
            treeInput.close();
            Compresor.imprimirArbol(arbol);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Descomprimir un archivo
        String decompressedFile = "texto_descomprimido.txt";
        try {
            Descompresor.descomprimirArchivo(compressedFile, treeFile, decompressedFile);
            System.out.println("Archivo descomprimido correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
