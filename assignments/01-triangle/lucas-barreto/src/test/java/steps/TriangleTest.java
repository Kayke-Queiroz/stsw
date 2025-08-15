package app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Triangle;



public class TriangleTest {

    Triangle triangle = new Triangle();
    @BeforeEach 
    void setUp() {
      z
    }

    @Test 
    void teste1() {
        String resultado = triangle.teste(5,5, 5);
        assertEquals("Equilátero", resultado);
    }

     @Test 
    void teste2() {
        String resultado = triangle.teste(5,5, 3);
        assertEquals("Isósceles", resultado);
    }
     @Test 
    void teste3() {
        String resultado = triangle.teste(3,4, 5);
        assertEquals("Escaleno", resultado);
    }
    
    void teste4() {
        String resultado = triangle.teste(1,2, 3);
        assertEquals("Não é um triângulo", resultado);
    } 
    void teste5() {
        String resultado = triangle.teste(-5,0, 5);
        assertEquals("Lados inválidos", resultado);
    } 


}
