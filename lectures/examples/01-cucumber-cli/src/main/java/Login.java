
package main.java;

public class Login {
    private String usuario;
    private String senha;

    public Login(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public boolean autenticar(String usuario, String senha) {
        System.out.println("Autenticando usuário: " + usuario + " | Senha: " + senha);
        return this.usuario.equals(usuario) && this.senha.equals(senha);
    }
}
