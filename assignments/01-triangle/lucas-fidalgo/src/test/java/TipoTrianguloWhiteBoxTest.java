import org.junit.Test;
import static org.junit.Assert.*;

public class TipoTrianguloWhiteBoxTest {

    // ---- Validação: lados não positivos ----
    @Test
    public void ladosInvalidos_zeroOuNegativo() {
        assertEquals("Lados inválidos", TipoTriangulo.determinarTipo(0, 5, 5));
        assertEquals("Lados inválidos", TipoTriangulo.determinarTipo(-1, 5, 5));
        assertEquals("Lados inválidos", TipoTriangulo.determinarTipo(5, 0, 5));
        assertEquals("Lados inválidos", TipoTriangulo.determinarTipo(5, 5, -1));
    }

    // ---- Desigualdade triangular (não forma triângulo) ----
    @Test
    public void naoFormaTriangulo_porSomaMenorOuIgual() {
        assertEquals("Não é um triângulo", TipoTriangulo.determinarTipo(1, 2, 3)); // soma == maior
        assertEquals("Não é um triângulo", TipoTriangulo.determinarTipo(1, 2, 4)); // soma < maior
        assertEquals("Não é um triângulo", TipoTriangulo.determinarTipo(10, 1, 1)); // ordem diferente
    }

    // ---- Equilátero ----
    @Test
    public void equilatero_casoBasico() {
        assertEquals("Equilátero", TipoTriangulo.determinarTipo(5, 5, 5));
    }

    // ---- Isósceles ----
    @Test
    public void isosceles_permutacoes() {
        assertEquals("Isósceles", TipoTriangulo.determinarTipo(5, 5, 3));
        assertEquals("Isósceles", TipoTriangulo.determinarTipo(5, 3, 5));
        assertEquals("Isósceles", TipoTriangulo.determinarTipo(3, 5, 5));
    }

    @Test
    public void isosceles_limiteDaDesigualdade() {
        assertEquals("Não é um triângulo", TipoTriangulo.determinarTipo(5, 5, 10)); // boundary soma == maior
        assertEquals("Isósceles", TipoTriangulo.determinarTipo(5, 5, 9)); // boundary válido soma > maior
    }

    // ---- Escaleno ----
    @Test
    public void escaleno_basicoEPermutacoes() {
        assertEquals("Escaleno", TipoTriangulo.determinarTipo(3, 4, 5));
        assertEquals("Escaleno", TipoTriangulo.determinarTipo(4, 5, 3));
        assertEquals("Escaleno", TipoTriangulo.determinarTipo(5, 3, 4));
    }

    // ---- Overflow guard (soma em long) ----
    @Test
    public void numerosGrandes_semOverflow() {
        assertEquals("Escaleno", TipoTriangulo.determinarTipo(2_000_000_000, 1_500_000_000, 1_000_000_000));
        assertEquals("Não é um triângulo", TipoTriangulo.determinarTipo(2_100_000_000, 100_000_000, 50_000_000));
    }
}
