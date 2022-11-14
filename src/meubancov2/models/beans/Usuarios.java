package meubancov2.models.beans;

/**
 *
 * @author scar
 */
public class Usuarios {
    private int id;
    private String nome;
    private String login;
    private String senha;
    private UsuTipo tipo;

    public Usuarios() {
    }

    public Usuarios(String login) {
        this.login = login;
    }

    public Usuarios(int id) {
        this.id = id;
    }

    public Usuarios(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }
    
    public Usuarios(String nome, String login, String senha, UsuTipo tipo) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
    }

    public Usuarios(int id, String nome, String login, String senha, UsuTipo tipo) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
    }
    
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UsuTipo getTipo() {
        return tipo;
    }

    public void setTipo(UsuTipo tipo) {
        this.tipo = tipo;
    }
    

    @Override
    public String toString() {
        return "Gerente:\n" +
               "id: " + id + ", nome: " + nome + ", login: " + login + ", senha: " + senha +
               ", tipo: " + tipo;
    }
    
}
