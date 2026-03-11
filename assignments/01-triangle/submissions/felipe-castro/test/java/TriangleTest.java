package app;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TriangleTest {
    private final Triangle triangle = new Triangle();

    @Test
    void shouldClassifyEquilateralTriangle() {
        assertEquals("Equilatero", triangle.classify(5, 5, 5));
    }

    @Test
    void shouldClassifyIsoscelesTriangle() {
        assertEquals("Isosceles", triangle.classify(5, 5, 3));
    }

    @Test
    void shouldClassifyScaleneTriangle() {
        assertEquals("Escaleno", triangle.classify(5, 4, 3));
    }

    @Test
    void shouldReturnNotATriangle() {
        assertEquals("Nao eh um triangulo", triangle.classify(1, 2, 3));
    }

    @Test
    void shouldReturnInvalidSidesForNegativeOrZero() {
        assertEquals("Lados invalidos", triangle.classify(-5, 0, 5));
    }

    @Test
    void shouldReturnInvalidSidesForValuesGreaterThan200() {
        assertEquals("Lados invalidos", triangle.classify(201, 10, 10));
    }
}