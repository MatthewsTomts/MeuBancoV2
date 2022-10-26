package meubancov2.models.beans;

/**
 *
 * @author scar
 */
public class Gerente {
    private int id;
    private String nome;
    private String login;
    private String senha;

    public Gerente() {
    }

    public Gerente(String login) {
        this.login = login;
    }

    public Gerente(int id) {
        this.id = id;
    }

    public Gerente(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }
    
    public Gerente(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public Gerente(int id, String nome, String login, String senha) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
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
    

    @Override
    public String toString() {
        return """
               
               Gerente:
               id: """ + id + ", nome: " + nome + ", login: " + login + ", senha: " + senha;
    }
    
}
