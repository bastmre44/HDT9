import java.io.*;
import java.util.*;

public class Compresor {
    public static void comprimirArchivo(String inputFile, String outputFile) throws IOException {
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
    }
}
