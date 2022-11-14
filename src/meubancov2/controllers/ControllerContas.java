package meubancov2.controllers;

import java.sql.SQLException;
import java.util.List;
import meubancov2.models.beans.Contas;
import meubancov2.models.beans.ContaTipo;
import meubancov2.models.daos.DaoContas;

/**
 *
 * @author scar
 */
public class ControllerContas {
    static DaoContas daoConta;
    
    public Contas inserir (Contas conta) throws SQLException, ClassNotFoundException {
        daoConta = new DaoContas(); 
        return daoConta.inserir(conta);
    }
    
    public void alterarTudo(int id, int idCliente, int idGerente, Float valor, ContaTipo tipo) throws SQLException, ClassNotFoundException {
        daoConta = new DaoContas(); 
        daoConta.alterarTudo(id, idCliente, idGerente, valor, tipo);
    }
    
    public void alterarIdClien(int id, int idCliente) throws SQLException, ClassNotFoundException {
        daoConta = new DaoContas(); 
        daoConta.alterarIdClien(id, idCliente);
    }
    
    public void alterarIdUsu(int id, int idUsuario) throws SQLException, ClassNotFoundException {
        daoConta = new DaoContas(); 
        daoConta.alterarIdUsu(id, idUsuario);
    }
    
    public void alterarValor(int id, Float valor) throws SQLException, ClassNotFoundException {
        daoConta = new DaoContas(); 
        daoConta.alterarValor(id, valor);
    }
    
    public void alterarTipo(int id, ContaTipo tipo) throws SQLException, ClassNotFoundException {
        daoConta = new DaoContas(); 
        daoConta.alterarTipo(id, tipo);
    }
    
    public List<Contas> listar () throws SQLException, ClassNotFoundException {
        daoConta = new DaoContas(); 
        return daoConta.listar();
    }
    
    public void excluir(int id) throws SQLException, ClassNotFoundException {
        daoConta = new DaoContas(); 
        daoConta.excluir(id);
    }
    
    public void excluirContas(int idCliente) throws SQLException, ClassNotFoundException {
        daoConta = new DaoContas(); 
        daoConta.excluirContas(idCliente);
    }
    
    public Contas buscarId(int id) throws SQLException, ClassNotFoundException {
        daoConta = new DaoContas(); 
        return daoConta.buscarId(id);
    }
    
    public List<Contas> buscarIdCliente(int idCliente) throws SQLException, ClassNotFoundException {
        daoConta = new DaoContas(); 
        return daoConta.buscarIdCliente(idCliente);
    }
    
    public List<Contas> buscarIdUsuario(int idUsuario) throws SQLException, ClassNotFoundException {
        daoConta = new DaoContas(); 
        return daoConta.buscarIdUsuario(idUsuario);
    }
    
    public List<Contas> buscarTipo(ContaTipo tipo) throws SQLException, ClassNotFoundException {
        daoConta = new DaoContas(); 
        return daoConta.buscarTipo(tipo);
    }
    
    public void alterarUsuario (int idNovo, int idAntigo) throws SQLException, ClassNotFoundException {
        daoConta = new DaoContas(); 
        daoConta.alterarUsuario(idNovo, idAntigo);
    }
}
