package br.com.vollmed.vollmed.domain.usuario;

//USANDO O RECORD O JAVA JÁ VAI CRIAR UMA CLASSE COM OS ATRIBUTOS ENTRE PARENTESES E CRIAR OS GETTERS E SETTERS POR BAIXO DOS PANOS.
public record DadosCadastroUsuario(String login, String senha) {

    public DadosCadastroUsuario(Usuario usuario) {
        this(usuario.getLogin(), usuario.getSenha());
    }
}
