import java.io.IOException;

// Hoja de trabajo 9, algoritmos y estructuras de datos
//Nina Nájera 231088
//Mishell Ciprian 231169

public class Main {
    public static void main(String[] args) {
        String inputFile = "texto_prueba.txt";
        String outputFile = "texto.huff";
        try {
            Compresor.comprimirArchivo(inputFile, outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
