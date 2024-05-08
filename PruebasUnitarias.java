import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.io.IOException;
import java.util.Map;

public class PruebasUnitarias {

    @Test
    public void pruebaGenerarCodigos() {
        HuffmanTree arbol = new HuffmanTree();
        // Construir un árbol de ejemplo
        Map<Character, Integer> frecuencias = Map.of('a', 5, 'b', 9, 'c', 12, 'd', 13, 'e', 16, 'f', 45);
        arbol.construirArbol(frecuencias);

        // Generar códigos esperados para las letras
        Map<Character, String> codigosEsperados = Map.of(
            'a', "1100",
            'b', "1101",
            'c', "100",
            'd', "101",
            'e', "111",
            'f', "0"
        );

        // Obtener códigos generados por el método
        Map<Character, String> codigosGenerados = arbol.generarCodigos();

        // Verificar si los códigos generados coinciden con los esperados
        assertEquals(codigosEsperados.size(), codigosGenerados.size());
        assertTrue(codigosGenerados.entrySet().containsAll(codigosEsperados.entrySet()));
    }

    @Test
    public void pruebaDescomprimirArchivo() {
        String archivoComprimido = "archivo_comprimido.huff";
        String archivoArbol = "arbol.tree";
        String archivoDescomprimido = "archivo_descomprimido.txt";

        try {
            // Realizar la descompresión del archivo
            Descompresor.descomprimirArchivo(archivoComprimido, archivoArbol, archivoDescomprimido);

            // Verificar si el archivo descomprimido no está vacío
            File file = new File(archivoDescomprimido);
            assertTrue(file.exists());
            assertTrue(file.length() > 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
