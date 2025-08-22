package src;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.beans.Transient;

public class TrinaguloTest {
    @Test
    public void testEquilatero(){
        assertEquals("Equilátero", Triangulo.classificarTriangulo(5, 5, 5));
    }
    @Test
    public void testIsosceles(){
        assertEquals("Isósceles", Triangulo.classificarTriangulo(5, 5, 3));
    }
    @Test
    public void testEscaleno(){
        assertEquals("Escaleno", Triangulo.classificarTriangulo(3, 5, 4));
    }

     @Test
    public void testNaoTriangulo() {
        assertEquals("Não é um triângulo", Triangulo.classificarTriangulo(1, 2, 3));
    }

    
    @Test
    public void testLadosInvalidosMaiorQue200() {
        assertEquals("Lados invalidos", Triangulo.classificarTriangulo(201, 5, 5));
    }

}
