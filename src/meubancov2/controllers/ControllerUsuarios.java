package meubancov2.controllers;

import java.sql.SQLException;
import java.util.List;
import meubancov2.models.beans.UsuTipo;
import meubancov2.models.beans.Usuarios;
import meubancov2.models.daos.DaoUsuarios;

/**
 *
 * @author scar
 */
public class ControllerUsuarios {
    static DaoUsuarios daoUsu;
    
    public String validar(String login, String senha) throws SQLException, ClassNotFoundException {
        String retorno = "falso";
        daoUsu = new DaoUsuarios(); 
        Usuarios usuSaida = daoUsu.validar(login, senha);
        if (usuSaida != null && usuSaida.getTipo() == UsuTipo.ADMINISTRADOR) retorno = "adm";
        else if(usuSaida != null && usuSaida.getTipo() == UsuTipo.GERENTE) retorno = "geren";
        return retorno;
    }
    
    public Usuarios inserir (Usuarios usu) throws SQLException, ClassNotFoundException {
        daoUsu = new DaoUsuarios(); 
        return daoUsu.inserir(usu);
    }
    
    public Usuarios buscarId(int id) throws SQLException, ClassNotFoundException {
        daoUsu = new DaoUsuarios(); 
        return daoUsu.buscarId(id);
    }
    
    public List<Usuarios> buscarNome(String nome) throws SQLException, ClassNotFoundException {
        daoUsu = new DaoUsuarios(); 
        return daoUsu.buscarNome(nome);
    }
    
    public List<Usuarios> buscarLogin(String login) throws SQLException, ClassNotFoundException {
        daoUsu = new DaoUsuarios(); 
        return daoUsu.buscarLogin(login);
    }
    
    public List<Usuarios> buscarTipo(UsuTipo tipo) throws SQLException, ClassNotFoundException {
        daoUsu = new DaoUsuarios(); 
        return daoUsu.buscarTipo(tipo);
    }
    
    public void alterarTudo (int id, String nome, String login, String senha, UsuTipo tipo) throws SQLException, ClassNotFoundException {
        daoUsu = new DaoUsuarios(); 
        daoUsu.alterarTudo(id, nome, login, senha, tipo);
    }
    
    public void alterarNome (int id, String nome) throws SQLException, ClassNotFoundException {
        daoUsu = new DaoUsuarios(); 
        daoUsu.alterarNome(id, nome);
    }
    
    public void alterarLogin (int id, String login) throws SQLException, ClassNotFoundException {
        daoUsu = new DaoUsuarios(); 
        daoUsu.alterarLogin(id, login);
    }
    
    public void alterarSenha (int id, String senha) throws SQLException, ClassNotFoundException {
        daoUsu = new DaoUsuarios(); 
        daoUsu.alterarSenha(id, senha);
    }
    
    public void alterarTipo (int id, UsuTipo tipo) throws SQLException, ClassNotFoundException {
        daoUsu = new DaoUsuarios(); 
        daoUsu.alterarTipo(id, tipo);
    }
    
    public List<Usuarios> listar () throws SQLException, ClassNotFoundException {
        daoUsu = new DaoUsuarios(); 
        return daoUsu.listar();
    }
    
    public void excluir(int id) throws SQLException, ClassNotFoundException {
        daoUsu = new DaoUsuarios(); 
        daoUsu.excluir(id);
    }
    
}
