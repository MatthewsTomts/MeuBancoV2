package meubancov2.controllers;

import java.sql.SQLException;
import java.util.List;
import meubancov2.models.beans.Cliente;
import meubancov2.models.daos.DaoCliente;

/**
 *
 * @author scar
 */
public class ControllerCliente {
    static DaoCliente daoClien;
    
    public Cliente inserir (Cliente clien) throws SQLException, ClassNotFoundException {
        daoClien = new DaoCliente(); 
        return daoClien.inserir(clien);
    }
    
    public Cliente alterar (Cliente clien) throws SQLException, ClassNotFoundException {
        daoClien = new DaoCliente(); 
        return daoClien.alterar(clien);
    }
    
    public List<Cliente> listar () throws SQLException, ClassNotFoundException {
        daoClien = new DaoCliente(); 
        return daoClien.listar();
    }
    
    public Cliente excluir(Cliente clien) throws SQLException, ClassNotFoundException {
        daoClien = new DaoCliente(); 
        return daoClien.excluir(clien);
    }
    
    public Cliente buscar(Cliente clien) throws SQLException, ClassNotFoundException {
        daoClien = new DaoCliente(); 
        return daoClien.buscar(clien);
    }
    
}
