package br.com.triangle;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TriangleClassifierTest {

    // 1) Fronteira do domínio: não-positivo vs positivo
    @Test
    void bva_naoTriangulo_zero() {
        assertEquals("Não é um triângulo", TriangleClassifier.classify(0, 1, 1));
    }

    @Test
    void bva_naoTriangulo_negativo() {
        assertEquals("Não é um triângulo", TriangleClassifier.classify(-1, 2, 3));
    }

    // 2) Fronteira da desigualdade: a+b = c (inválido) e a+b = c+1 (mínimo válido)
    @Test
    void bva_desigualdade_igualdade_invalido() {
        // a + b = c  -> inválido na borda
        assertEquals("Não é um triângulo", TriangleClassifier.classify(1, 2, 3));
    }

    @Test
    void bva_desigualdade_minimo_valido_escaleno() {
        // a + b = c + 1 -> mínimo válido
        assertEquals("Escaleno", TriangleClassifier.classify(2, 3, 4));
    }

    // 3) Classes na borda mínima
    @Test
    void bva_equilatero_minimo() {
        assertEquals("Equilátero", TriangleClassifier.classify(1, 1, 1));
    }

    @Test
    void bva_isosceles_minimo_valido_proximo_da_borda() {
        // 2 + 2 > 3 (por 1) => válido na borda
        assertEquals("Isósceles", TriangleClassifier.classify(2, 2, 3));
    }

    // 4) (Opcional) Comutatividade na borda (permutação de 1,2,3 permanece inválido)
    @Test
    void bva_desigualdade_permutacoes_igualdade_invalido() {
        assertEquals("Não é um triângulo", TriangleClassifier.classify(1, 2, 3));
        assertEquals("Não é um triângulo", TriangleClassifier.classify(1, 3, 2));
        assertEquals("Não é um triângulo", TriangleClassifier.classify(2, 1, 3));
    }
}
