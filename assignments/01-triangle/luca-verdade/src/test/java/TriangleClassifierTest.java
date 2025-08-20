import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TriangleClassifierTest {

    @Test
    void testEquilatero() {
        assertEquals("Equilátero", TriangleClassifier.classificarTriangulo(5, 5, 5));
    }

    @Test
    void testIsosceles() {
        assertEquals("Isósceles", TriangleClassifier.classificarTriangulo(5, 5, 3));
    }

    @Test
    void testEscaleno() {
        assertEquals("Escaleno", TriangleClassifier.classificarTriangulo(5, 4, 3));
    }

    @Test
    void testNaoTriangulo() {
        assertEquals("Não é um triângulo", TriangleClassifier.classificarTriangulo(1, 2, 3));
    }

    @Test
    void testLadosInvalidos() {
        assertEquals("Lados inválidos", TriangleClassifier.classificarTriangulo(-5, 0, 5));
    }
}
