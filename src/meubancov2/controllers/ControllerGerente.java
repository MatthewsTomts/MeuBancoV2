package meubancov2.controllers;

import java.sql.SQLException;
import java.util.List;
import meubancov2.models.beans.Gerente;
import meubancov2.models.daos.DaoGerente;

/**
 *
 * @author scar
 */
public class ControllerGerente {
    static DaoGerente daoGeren;
    
    public Boolean validar(String login, String senha) throws SQLException, ClassNotFoundException {
        boolean retorno = false;
        daoGeren = new DaoGerente(); 
        Gerente gerenSaida = daoGeren.validar(login, senha);
        if(gerenSaida != null) retorno = true;
        return retorno;
    }
    
    public Gerente inserir (Gerente geren) throws SQLException, ClassNotFoundException {
        daoGeren = new DaoGerente(); 
        return daoGeren.inserir(geren);
    }
    
    public Gerente buscarId(int id) throws SQLException, ClassNotFoundException {
        daoGeren = new DaoGerente(); 
        return daoGeren.buscarId(id);
    }
    
    public List<Gerente> buscarNome(String nome) throws SQLException, ClassNotFoundException {
        daoGeren = new DaoGerente(); 
        return daoGeren.buscarNome(nome);
    }
    
    public List<Gerente> buscarLogin(String login) throws SQLException, ClassNotFoundException {
        daoGeren = new DaoGerente(); 
        return daoGeren.buscarLogin(login);
    }
    
    public void alterarTudo (int id, String nome, String login, String senha) throws SQLException, ClassNotFoundException {
        daoGeren = new DaoGerente(); 
        daoGeren.alterarTudo(id, nome, login, senha);
    }
    
    public void alterarNome (int id, String nome) throws SQLException, ClassNotFoundException {
        daoGeren = new DaoGerente(); 
        daoGeren.alterarNome(id, nome);
    }
    
    public void alterarLogin (int id, String login) throws SQLException, ClassNotFoundException {
        daoGeren = new DaoGerente(); 
        daoGeren.alterarLogin(id, login);
    }
    
    public void alterarSenha (int id, String senha) throws SQLException, ClassNotFoundException {
        daoGeren = new DaoGerente(); 
        daoGeren.alterarSenha(id, senha);
    }
    
    public List<Gerente> listar () throws SQLException, ClassNotFoundException {
        daoGeren = new DaoGerente(); 
        return daoGeren.listar();
    }
    
    public void excluir(int id) throws SQLException, ClassNotFoundException {
        daoGeren = new DaoGerente(); 
        daoGeren.excluir(id);
    }
    
}
