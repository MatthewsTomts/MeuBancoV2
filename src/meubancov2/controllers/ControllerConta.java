package meubancov2.controllers;

import java.sql.SQLException;
import java.util.List;
import meubancov2.models.beans.Conta;
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
    
    public Conta alterar (Conta conta) throws SQLException, ClassNotFoundException {
        daoConta = new DaoConta(); 
        return daoConta.alterar(conta);
    }
    
    public List<Conta> listar () throws SQLException, ClassNotFoundException {
        daoConta = new DaoConta(); 
        return daoConta.listar();
    }
    
    public Conta excluir(Conta conta) throws SQLException, ClassNotFoundException {
        daoConta = new DaoConta(); 
        return daoConta.excluir(conta);
    }
    
    public void excluirContas(int idCliente) throws SQLException, ClassNotFoundException {
        daoConta = new DaoConta(); 
        daoConta.excluirContas(idCliente);
    }
    
    public Conta buscarId(int id) throws SQLException, ClassNotFoundException {
        daoConta = new DaoConta(); 
        return daoConta.buscarId(id);
    }
    
    public Conta buscarNome(String nome) throws SQLException, ClassNotFoundException {
        daoConta = new DaoConta(); 
        return daoConta.buscarNome(nome);
    }
    
    public Conta buscarLogin(String login) throws SQLException, ClassNotFoundException {
        daoConta = new DaoConta(); 
        return daoConta.buscarLogin(login);
    }
    
    public void alterarGerente (int idNovo, int idAntigo) throws SQLException, ClassNotFoundException {
        daoConta = new DaoConta(); 
        daoConta.alterarGerente(idNovo, idAntigo);
    }
}
