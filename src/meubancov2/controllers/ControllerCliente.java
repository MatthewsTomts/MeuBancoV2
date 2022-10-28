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
    
    public Cliente inserir(Cliente clien) throws SQLException, ClassNotFoundException {
        daoClien = new DaoCliente(); 
        return daoClien.inserir(clien);
    }
    
    public Cliente buscarId(int id) throws SQLException, ClassNotFoundException {
        daoClien = new DaoCliente(); 
        return daoClien.buscarId(id);
    }
    
    public List<Cliente> buscarNome(String nome) throws SQLException, ClassNotFoundException {
        daoClien = new DaoCliente(); 
        return daoClien.buscarNome(nome);
    }
    
    public Cliente buscarRg(String rg) throws SQLException, ClassNotFoundException {
        daoClien = new DaoCliente(); 
        return daoClien.buscarRg(rg);
    }
    
    public Cliente buscarCpf(String cpf) throws SQLException, ClassNotFoundException {
        daoClien = new DaoCliente(); 
        return daoClien.buscarCpf(cpf);
    }
    
    public List<Cliente> buscarEmail(String email) throws SQLException, ClassNotFoundException {
        daoClien = new DaoCliente(); 
        return daoClien.buscarEmail(email);
    }
    
    public Cliente buscarTelefone(String telefone) throws SQLException, ClassNotFoundException {
        daoClien = new DaoCliente(); 
        return daoClien.buscarTelefone(telefone);
    }
    
    public void alterarTudo(int id, String nome, String rg, String cpf, String email, String telefone) throws SQLException, ClassNotFoundException {
        daoClien = new DaoCliente(); 
        daoClien.alterarTudo(id, nome, rg, cpf, email, telefone);
    }
    
    public void alterarNome(int id, String nome) throws SQLException, ClassNotFoundException {
        daoClien = new DaoCliente(); 
        daoClien.alterarNome(id, nome);
    }
    
    public void alterarRg(int id, String rg) throws SQLException, ClassNotFoundException {
        daoClien = new DaoCliente(); 
        daoClien.alterarRg(id, rg);
    }
    
    public void alterarCpf(int id, String cpf) throws SQLException, ClassNotFoundException {
        daoClien = new DaoCliente(); 
        daoClien.alterarCpf(id, cpf);
    }
    
    public void alterarEmail(int id, String email) throws SQLException, ClassNotFoundException {
        daoClien = new DaoCliente(); 
        daoClien.alterarEmail(id, email);
    }
    
    public void alterarTelefone(int id, String telefone) throws SQLException, ClassNotFoundException {
        daoClien = new DaoCliente(); 
        daoClien.alterarTelefone(id, telefone);
    }
    
    public List<Cliente> listar () throws SQLException, ClassNotFoundException {
        daoClien = new DaoCliente(); 
        return daoClien.listar();
    }
    
    public void excluir(int id) throws SQLException, ClassNotFoundException {
        daoClien = new DaoCliente(); 
        daoClien.excluir(id);
    }
    
    
}
