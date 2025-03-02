package br.edu.idp.es.stsw;

public class Login {
    private String usuario;
    private String senha;

    public Login(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public String autenticar(String usuario, String senha) {

        System.out.println("Autenticando usuário: " + usuario + " | Senha: " + senha);

        if (this.usuario.equals("admin") && this.senha.equals("1234"))
            return "Usuário autenticado com sucesso!";
        else
            return "Usuário ou senha incoretos! Tente novamente.";
    }
}
