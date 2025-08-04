package app;

public class Login {
    private String usuario;
    private String senha;
    public int cdig;

    public Login(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public boolean autenticar(String usuario, String senha) {
        System.out.println("Autenticando usuário: " + usuario + " | Senha: " + senha);
        if (cdig == 1) {
            return this.usuario.equals(usuario) && this.senha.equals(senha);
        } else {
            return false;
        }
    }   
}   