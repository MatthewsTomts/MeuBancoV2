package meubancov2.models.daos;

import meubancov2.models.beans.Gerente;
import meubancov2.utils.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author scar
 */
public class DaoGerente {
    private final Connection con;

    public DaoGerente() throws SQLException, ClassNotFoundException {
        this.con = new Conexao().getConnection();
    }
    
    public Gerente validar(String login, String senha) throws SQLException {
        String sql = "select * from Gerente WHERE login = ? AND senha = ?";
        Gerente gerenSaida;
        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            gerenSaida = null;
            while (rs.next()) {
                gerenSaida = new Gerente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
            }
        }
        return gerenSaida; 
    }
    
    public Gerente inserir(Gerente geren) throws SQLException{
        String sql = "insert into Gerente" + " values (default,?,?, ?)";
    
        // seta os valores
        try ( // prepared statement para inserção
                PreparedStatement stmt =
                        con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            // seta os valores
            stmt.setString(1, geren.getNome());
            stmt.setString(2, geren.getLogin());
            stmt.setString(3, geren.getSenha());
            
            // executa
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                geren.setId(id);
            }
        }
        return geren;
    }
    
    public void alterarTudo(int id, String nome, String login, String senha) throws SQLException{
        String sql;
        sql = "UPDATE Gerente SET nome = ?, login = ?, senha = ? WHERE id = ?";
        try ( // prepared statement para inserção
            PreparedStatement stmt = con.prepareStatement(sql)) {
            // seta os valores
            stmt.setString(1, nome);
            stmt.setString(2, login);
            stmt.setString(3, senha);
            stmt.setInt(4, id);

            // executa
            stmt.execute();
        }
    }
    
    public void alterarNome(int id, String nome) throws SQLException{
        String sql;
        sql = "UPDATE Gerente SET nome = ? WHERE id = ?";
        try ( // prepared statement para inserção
            PreparedStatement stmt = con.prepareStatement(sql)) {
            // seta os valores
            stmt.setString(1, nome);
            stmt.setInt(2, id);

            // executa
            stmt.execute();
        }
    }
    
    public void alterarLogin(int id, String login) throws SQLException{
        String sql;
        sql = "UPDATE Gerente SET login = ? WHERE id = ?";
        try ( // prepared statement para inserção
            PreparedStatement stmt = con.prepareStatement(sql)) {
            // seta os valores
            stmt.setString(1, login);
            stmt.setInt(2, id);

            // executa
            stmt.execute();
        }
    }
    
    public void alterarSenha(int id, String senha) throws SQLException{
        String sql;
        sql = "UPDATE Gerente SET senha = ? WHERE id = ?";
        try ( // prepared statement para inserção
            PreparedStatement stmt = con.prepareStatement(sql)) {
            // seta os valores
            stmt.setString(1, senha);
            stmt.setInt(2, id);

            // executa
            stmt.execute();
        }
    }
    
    public void excluir(Gerente geren) throws SQLException{
        String sql = "DELETE FROM Gerente WHERE id = ?";
        // seta os valores
        try ( // prepared statement para inserção
                PreparedStatement stmt = con.prepareStatement(sql)) {
            // seta os valores
            stmt.setInt(1,geren.getId());
            // executa
            stmt.execute();
        }
    }
    
    public Gerente buscarId(int id) throws SQLException{
        String sql = "SELECT * FROM Gerente WHERE id = ?";
        Gerente geren = null;
        // seta os valores
        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            // seta os valores
            stmt.setInt(1, id);
            // executa
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // criando o objeto Usuario
                geren = new Gerente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
                // adiciona o usu à lista de usus
            }
        }
        return geren;
    }
    
    public List<Gerente> buscarNome(String nome) throws SQLException{
        String sql = "SELECT * FROM Gerente WHERE nome like ?";
        List<Gerente> gerens = new ArrayList();
        // seta os valores
        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            // seta os valores
            stmt.setString(1, nome + "%");
            // executa
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // criando o objeto Usuario
                Gerente geren = new Gerente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
                // adiciona o usu à lista de usus
                gerens.add(geren);
            }
        }
        return gerens;
    }
    
    public List<Gerente> buscarLogin(String login) throws SQLException{
        String sql = "SELECT * FROM Gerente WHERE login like ?";
        List<Gerente> gerens = new ArrayList();
        // seta os valores
        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            // seta os valores
            stmt.setString(1, login + "%");
            // executa
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // criando o objeto Usuario
                Gerente geren = new Gerente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
                // adiciona o usu à lista de usus
                gerens.add(geren);
            }
        }
        return gerens;
    }
    
   public List<Gerente> listar() throws SQLException{
        // usus: array armazena a lista de registros

        List<Gerente> gerens = new ArrayList<>();
        
        String sql = "SELECT * FROM Gerente";
        try (PreparedStatement stmt = this.con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                // criando o objeto Usuario
                Gerente geren = new Gerente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
                // adiciona o usu à lista de usus
                gerens.add(geren);
            }
            
        }
        return gerens;
   }
}
