package meubancov2.controllers;

import java.sql.SQLException;
import java.util.List;
import meubancov2.models.beans.Clientes;
import meubancov2.models.daos.DaoClientes;

/**
 *
 * @author scar
 */
public class ControllerClientes {
    static DaoClientes daoClien;
    
    public Clientes inserir(Clientes clien) throws SQLException, ClassNotFoundException {
        daoClien = new DaoClientes(); 
        return daoClien.inserir(clien);
    }
    
    public Clientes buscarId(int id) throws SQLException, ClassNotFoundException {
        daoClien = new DaoClientes(); 
        return daoClien.buscarId(id);
    }
    
    public List<Clientes> buscarNome(String nome) throws SQLException, ClassNotFoundException {
        daoClien = new DaoClientes(); 
        return daoClien.buscarNome(nome);
    }
    
    public Clientes buscarRg(String rg) throws SQLException, ClassNotFoundException {
        daoClien = new DaoClientes(); 
        return daoClien.buscarRg(rg);
    }
    
    public Clientes buscarCpf(String cpf) throws SQLException, ClassNotFoundException {
        daoClien = new DaoClientes(); 
        return daoClien.buscarCpf(cpf);
    }
    
    public List<Clientes> buscarEmail(String email) throws SQLException, ClassNotFoundException {
        daoClien = new DaoClientes(); 
        return daoClien.buscarEmail(email);
    }
    
    public Clientes buscarTelefone(String telefone) throws SQLException, ClassNotFoundException {
        daoClien = new DaoClientes(); 
        return daoClien.buscarTelefone(telefone);
    }
    
    public void alterarTudo(int id, String nome, String rg, String cpf, String email, String telefone) throws SQLException, ClassNotFoundException {
        daoClien = new DaoClientes(); 
        daoClien.alterarTudo(id, nome, rg, cpf, email, telefone);
    }
    
    public void alterarNome(int id, String nome) throws SQLException, ClassNotFoundException {
        daoClien = new DaoClientes(); 
        daoClien.alterarNome(id, nome);
    }
    
    public void alterarRg(int id, String rg) throws SQLException, ClassNotFoundException {
        daoClien = new DaoClientes(); 
        daoClien.alterarRg(id, rg);
    }
    
    public void alterarCpf(int id, String cpf) throws SQLException, ClassNotFoundException {
        daoClien = new DaoClientes(); 
        daoClien.alterarCpf(id, cpf);
    }
    
    public void alterarEmail(int id, String email) throws SQLException, ClassNotFoundException {
        daoClien = new DaoClientes(); 
        daoClien.alterarEmail(id, email);
    }
    
    public void alterarTelefone(int id, String telefone) throws SQLException, ClassNotFoundException {
        daoClien = new DaoClientes(); 
        daoClien.alterarTelefone(id, telefone);
    }
    
    public List<Clientes> listar () throws SQLException, ClassNotFoundException {
        daoClien = new DaoClientes(); 
        return daoClien.listar();
    }
    
    public void excluir(int id) throws SQLException, ClassNotFoundException {
        daoClien = new DaoClientes(); 
        daoClien.excluir(id);
    }
    
    
}
