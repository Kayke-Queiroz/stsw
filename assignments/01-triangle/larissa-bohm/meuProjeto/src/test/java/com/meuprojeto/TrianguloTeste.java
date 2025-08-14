package com.meuprojeto;

import org.junit.Test;
import static org.junit.Assert.*;

public class TrianguloTeste {

    @Test
    public void testeEquilatero() {
        Triangulo t = new Triangulo(5, 5, 5);
        assertEquals("Equilatero", t.tipo());
    }

    @Test
    public void testeIsoceles() {
        Triangulo t = new Triangulo(5, 5, 3);
        assertEquals("Isoceles", t.tipo());
    }

    @Test
    public void testeEscaleno() {
        Triangulo t = new Triangulo(5, 4, 3);
        assertEquals("Escaleno", t.tipo());
    }

    @Test
    public void testeNaoTriangulo() {
        Triangulo t = new Triangulo(1, 2, 3);
        assertEquals("Nao e triangulo", t.tipo());
    }
}
