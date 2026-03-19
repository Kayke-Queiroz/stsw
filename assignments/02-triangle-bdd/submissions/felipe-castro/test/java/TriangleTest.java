package app;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TriangleTest {
    private final Triangle triangle = new Triangle();

    private static final int[] BOUNDARY_VALUES = {0, 1, 100, 199, 200, 201};

    @ParameterizedTest(name = "a={0}, b={1}, c={2} => esperado={3}")
    @MethodSource("boundaryCases")
    void shouldClassifyAllBoundaryCases(int a, int b, int c, String expected) {
        assertEquals(expected, triangle.classify(a, b, c));
    }

    private static Stream<org.junit.jupiter.params.provider.Arguments> boundaryCases() {
        List<org.junit.jupiter.params.provider.Arguments> cases = new ArrayList<>();
        for (int a : BOUNDARY_VALUES) {
            for (int b : BOUNDARY_VALUES) {
                for (int c : BOUNDARY_VALUES) {
                    cases.add(org.junit.jupiter.params.provider.Arguments.of(a, b, c, expectedResult(a, b, c)));
                }
            }
        }
        return cases.stream();
    }

    private static String expectedResult(int a, int b, int c) {
        if (!isValidSide(a) || !isValidSide(b) || !isValidSide(c)) {
            return "Lados invalidos";
        }
        if (!isValidTriangle(a, b, c)) {
            return "Nao eh um triangulo";
        }
        if (a == b && b == c) {
            return "Equilatero";
        }
        if (a == b || b == c || a == c) {
            return "Isosceles";
        }
        return "Escaleno";
    }

    private static boolean isValidSide(int side) {
        return side >= 1 && side <= 200;
    }

    private static boolean isValidTriangle(int a, int b, int c) {
        return (a + b > c) && (a + c > b) && (b + c > a);
    }
}