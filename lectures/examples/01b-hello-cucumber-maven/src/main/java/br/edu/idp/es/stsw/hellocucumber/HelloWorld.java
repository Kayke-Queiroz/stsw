package br.edu.idp.es.stsw.hellocucumber;

public class HelloWorld {
    public static void main(String[] args) {
        HelloWorld helloWorld = new HelloWorld();
        System.out.println(helloWorld.getHelloWorld());
    }

    public String getHelloWorld() {
        return "Hello, World!";
    }

    public String getHelloWorld(String nome) {
        return "Hello, " + nome + "!";
    }
}
