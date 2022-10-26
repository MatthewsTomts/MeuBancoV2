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
    
    public Boolean validar(Gerente gerenEnt) throws SQLException, ClassNotFoundException {
        boolean retorno = false;
        daoGeren = new DaoGerente(); 
        Gerente gerenSaida = daoGeren.validar(gerenEnt);
        if(gerenSaida != null) retorno = true;
        return retorno;
    }
    
    public Gerente inserir (Gerente geren) throws SQLException, ClassNotFoundException {
        daoGeren = new DaoGerente(); 
        return daoGeren.inserir(geren);
    }
    
    public void alterar (Gerente geren) throws SQLException, ClassNotFoundException {
        daoGeren = new DaoGerente(); 
        daoGeren.alterar(geren);
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
    
    public List<Gerente> listar () throws SQLException, ClassNotFoundException {
        daoGeren = new DaoGerente(); 
        return daoGeren.listar();
    }
    
    public void excluir(Gerente geren) throws SQLException, ClassNotFoundException {
        daoGeren = new DaoGerente(); 
        daoGeren.excluir(geren);
    }
    
}
