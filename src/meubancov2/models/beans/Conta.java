package meubancov2.models.beans;

/**
 *
 * @author scar
 */
public class Conta {
    private int id;
    private int idCliente;
    private int idGerente;
    private float valor;
    private Tipo tipo;

    public Conta(int id) {
        this.id = id;
    }

    public Conta(int idGerente, int idCliente, float valor, Tipo tipo) {
        this.idGerente = idGerente;
        this.idCliente = idCliente;
        this.valor = valor;
        this.tipo = tipo;
    }

    public Conta( int id, int idCliente, int idGerente, float valor, Tipo tipo) {
        this.idCliente = idCliente;
        this.idGerente = idGerente;
        this.id = id;
        this.valor = valor;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return """
               Conta:
               Id do Conta: """ + id + ", Id do Cliente: " + idCliente +
                ", Id da Gerente: " + idGerente + ", Valor: " + valor + ", Tipo: " + tipo;
    }
}
