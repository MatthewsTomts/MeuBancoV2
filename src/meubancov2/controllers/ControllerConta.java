package meubancov2.controllers;

import java.sql.SQLException;
import java.util.List;
import meubancov2.models.beans.Conta;
import meubancov2.models.beans.Tipo;
import meubancov2.models.daos.DaoConta;

/**
 *
 * @author scar
 */
public class ControllerConta {
    static DaoConta daoConta;
    
    public Conta inserir (Conta conta) throws SQLException, ClassNotFoundException {
        daoConta = new DaoConta(); 
        return daoConta.inserir(conta);
    }
    
    public void alterarTudo(int id, int idCliente, int idGerente, Float valor, Tipo tipo) throws SQLException, ClassNotFoundException {
        daoConta = new DaoConta(); 
        daoConta.alterarTudo(id, idCliente, idGerente, valor, tipo);
    }
    
    public void alterarIdClien(int id, int idCliente) throws SQLException, ClassNotFoundException {
        daoConta = new DaoConta(); 
        daoConta.alterarIdClien(id, idCliente);
    }
    
    public void alterarIdGeren(int id, int idGerente) throws SQLException, ClassNotFoundException {
        daoConta = new DaoConta(); 
        daoConta.alterarIdGeren(id, idGerente);
    }
    
    public void alterarValor(int id, Float valor) throws SQLException, ClassNotFoundException {
        daoConta = new DaoConta(); 
        daoConta.alterarValor(id, valor);
    }
    
    public void alterarTipo(int id, Tipo tipo) throws SQLException, ClassNotFoundException {
        daoConta = new DaoConta(); 
        daoConta.alterarTipo(id, tipo);
    }
    
    public List<Conta> listar () throws SQLException, ClassNotFoundException {
        daoConta = new DaoConta(); 
        return daoConta.listar();
    }
    
    public void excluir(int id) throws SQLException, ClassNotFoundException {
        daoConta = new DaoConta(); 
        daoConta.excluir(id);
    }
    
    public void excluirContas(int idCliente) throws SQLException, ClassNotFoundException {
        daoConta = new DaoConta(); 
        daoConta.excluirContas(idCliente);
    }
    
    public Conta buscarId(int id) throws SQLException, ClassNotFoundException {
        daoConta = new DaoConta(); 
        return daoConta.buscarId(id);
    }
    
    public Conta buscarIdCliente(int idCliente) throws SQLException, ClassNotFoundException {
        daoConta = new DaoConta(); 
        return daoConta.buscarIdCliente(idCliente);
    }
    
    public Conta buscarIdGerente(int idGerente) throws SQLException, ClassNotFoundException {
        daoConta = new DaoConta(); 
        return daoConta.buscarIdGerente(idGerente);
    }
    
    public List<Conta> buscarTipo(Tipo tipo) throws SQLException, ClassNotFoundException {
        daoConta = new DaoConta(); 
        return daoConta.buscarTipo(tipo);
    }
    
    public void alterarGerente (int idNovo, int idAntigo) throws SQLException, ClassNotFoundException {
        daoConta = new DaoConta(); 
        daoConta.alterarGerente(idNovo, idAntigo);
    }
}
