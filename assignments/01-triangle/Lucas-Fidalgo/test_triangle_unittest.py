#!/usr/bin/env python3
import unittest
import subprocess
import os

class TriangleClassifierTest(unittest.TestCase):
    """Testes unitários usando unittest para o Triangle Classifier Java"""
    
    def run_triangle_classifier(self, a, b, c):
        """Executa o classificador Java e retorna o resultado"""
        try:
            # Garantir que está compilado
            os.makedirs("build/classes", exist_ok=True)
            
            # Compilar se necessário
            subprocess.run([
                "javac", "-d", "build/classes", 
                "src/main/java/com/example/triangle/*.java"
            ], capture_output=True, text=True, check=True)
            
            # Criar wrapper temporário
            wrapper = f"""
import com.example.triangle.TriangleClassifier;
public class TestWrapper {{
    public static void main(String[] args) {{
        TriangleClassifier c = new TriangleClassifier();
        System.out.print(c.classify({a}, {b}, {c}));
    }}
}}"""
            
            with open("TestWrapper.java", "w") as f:
                f.write(wrapper)
            
            # Compilar e executar
            subprocess.run(["javac", "-cp", "build/classes", "TestWrapper.java"], check=True)
            result = subprocess.run(["java", "-cp", "build/classes;.", "TestWrapper"], 
                                  capture_output=True, text=True, timeout=5)
            
            # Limpar
            for file in ["TestWrapper.java", "TestWrapper.class"]:
                if os.path.exists(file):
                    os.remove(file)
            
            return result.stdout.strip()
            
        except Exception as e:
            return f"ERRO: {e}"

    # CASOS DE USO DA ESPECIFICAÇÃO
    
    def test_caso_uso_1_equilatero(self):
        """Caso 1: Triângulo Equilátero (5,5,5) deve retornar 'Equilátero'"""
        resultado = self.run_triangle_classifier(5, 5, 5)
        self.assertEqual(resultado, "Equilátero")

    def test_caso_uso_2_isosceles(self):
        """Caso 2: Triângulo Isósceles (5,5,3) deve retornar 'Isósceles'"""
        resultado = self.run_triangle_classifier(5, 5, 3)
        self.assertEqual(resultado, "Isósceles")

    def test_caso_uso_3_escaleno(self):
        """Caso 3: Triângulo Escaleno (5,4,3) deve retornar 'Escaleno'"""
        resultado = self.run_triangle_classifier(5, 4, 3)
        self.assertEqual(resultado, "Escaleno")

    def test_caso_uso_4_nao_triangulo(self):
        """Caso 4: Não forma triângulo (1,2,3) deve retornar 'Não é um triângulo'"""
        resultado = self.run_triangle_classifier(1, 2, 3)
        self.assertEqual(resultado, "Não é um triângulo")

    def test_caso_uso_5_invalidos(self):
        """Caso 5: Lados inválidos (-5,0,5) deve retornar 'Lados inválidos'"""
        resultado = self.run_triangle_classifier(-5, 0, 5)
        self.assertEqual(resultado, "Lados inválidos")


if __name__ == "__main__":
    print("🧪 UNITTEST - Testando Triangle Classifier Java")
    print("=" * 50)
    unittest.main(verbosity=2)
