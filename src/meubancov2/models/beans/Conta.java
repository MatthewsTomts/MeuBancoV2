package meubancov2.models.beans;

/**
 *
 * @author scar
 */
public class Conta {
    private int idCliente;
    private int idGerente;
    private int id;
    private float valor;
    private String tipo;

    public Conta(int id) {
        this.id = id;
    }

    public Conta(int idGerente, int idCliente, float valor, String tipo) {
        this.idGerente = idGerente;
        this.idCliente = idCliente;
        this.valor = valor;
        this.tipo = tipo;
    }

    public Conta(int idCliente, int idGerente, int id, float valor, String tipo) {
        this.idCliente = idCliente;
        this.idGerente = idGerente;
        this.id = id;
        this.valor = valor;
        this.tipo = tipo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdGerente() {
        return idGerente;
    }

    public void setIdGerente(int idGerente) {
        this.idGerente = idGerente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return """
               Conta:
               Id do Cliente: """ + idCliente + ", Id do Gerente: " + idGerente +
                ", Id da Conta: " + id + ", Valor: " + valor + ", Tipo: " + tipo;
    }
}
