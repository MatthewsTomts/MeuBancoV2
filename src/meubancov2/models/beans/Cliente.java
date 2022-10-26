package meubancov2.models.beans;

/**
 *
 * @author scar
 */
public class Cliente {
    private int id;
    private String nome;
    private String rg;
    private String cpf;
    private String email;
    private String telefone;

    public Cliente(int id) {
        this.id = id;
    }

    public Cliente(String nome, String rg, String cpf, String email,
            String telefone) {
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    public Cliente(int id, String nome, String rg, String cpf, String email,
            String telefone) {
        this.id = id;
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return """
               Cliente:
               Id: """ + id + ", Nome: " + nome + ", RG: " + rg +
                ", CPF: " + cpf + ", Email: " + email + ", Telefone: " + telefone;
    }
}
