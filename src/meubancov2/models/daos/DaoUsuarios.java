package meubancov2.models.daos;

import meubancov2.models.beans.Usuarios;
import meubancov2.utils.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import meubancov2.models.beans.UsuTipo;

/**
 *
 * @author scar
 */
public class DaoUsuarios {
    private final Connection con;

    public DaoUsuarios() throws SQLException, ClassNotFoundException {
        this.con = new Conexao().getConnection();
    }
    
    public Usuarios validar(String login, String senha) throws SQLException {
        String sql = "select * from Usuarios WHERE login = ? AND senha = ?";
        Usuarios usuSaida;
        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            usuSaida = null;
            while (rs.next()) {
                usuSaida = new Usuarios(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        UsuTipo.valueOf(rs.getString(5)));
            }
        }
        return usuSaida; 
    }
    
    public Usuarios inserir(Usuarios usu) throws SQLException{
        String sql = "insert into Usuarios values (default, ?, ?, ?, ?)";
    
        // seta os valores
        try ( // prepared statement para inserção
                PreparedStatement stmt =
                        con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            // seta os valores
            stmt.setString(1, usu.getNome());
            stmt.setString(2, usu.getLogin());
            stmt.setString(3, usu.getSenha());
            stmt.setString(4, usu.getTipo().toString());
            
            // executa
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                usu.setId(rs.getInt(1));
            }
        }
        return usu;
    }
    
    public Usuarios buscarId(int id) throws SQLException{
        String sql = "SELECT * FROM Usuarios WHERE id = ?";
        Usuarios usu = null;
        // seta os valores
        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            // seta os valores
            stmt.setInt(1, id);
            // executa
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // criando o objeto Usuarios
                usu = new Usuarios(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        UsuTipo.valueOf(rs.getString(5)));
                // adiciona o usu à lista de usus
            }
        }
        return usu;
    }
    
    public List<Usuarios> buscarNome(String nome) throws SQLException{
        String sql = "SELECT * FROM Usuarios WHERE nome like ?";
        List<Usuarios> usus = new ArrayList();
        // seta os valores
        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            // seta os valores
            stmt.setString(1, nome + "%");
            // executa
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // criando o objeto Usuarios
                Usuarios usu = new Usuarios(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        UsuTipo.valueOf(rs.getString(5)));
                // adiciona o usu à lista de usus
                usus.add(usu);
            }
        }
        return usus;
    }
    
    public List<Usuarios> buscarLogin(String login) throws SQLException{
        String sql = "SELECT * FROM Usuarios WHERE login like ?";
        List<Usuarios> usus = new ArrayList();
        // seta os valores
        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            // seta os valores
            stmt.setString(1, login + "%");
            // executa
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // criando o objeto Usuarios
                Usuarios usu = new Usuarios(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        UsuTipo.valueOf(rs.getString(5)));
                // adiciona o usu à lista de usus
                usus.add(usu);
            }
        }
        return usus;
    }
    
    public List<Usuarios> buscarTipo(UsuTipo tipo) throws SQLException{
        String sql = "SELECT * FROM Usuarios WHERE tipo like ?";
        List<Usuarios> usus = new ArrayList();
        // seta os valores
        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            // seta os valores
            stmt.setString(1, tipo + "%");
            // executa
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // criando o objeto Usuarios
                Usuarios usu = new Usuarios(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        UsuTipo.valueOf(rs.getString(5)));
                // adiciona o usu à lista de usus
                usus.add(usu);
            }
        }
        return usus;
    }
    
    public void alterarTudo(int id, String nome, String login, String senha, UsuTipo tipo) throws SQLException{
        String sql;
        sql = "UPDATE Usuarios SET nome = ?, login = ?, senha = ?, tipo = ? WHERE id = ?";
        try ( // prepared statement para inserção
            PreparedStatement stmt = con.prepareStatement(sql)) {
            // seta os valores
            stmt.setString(1, nome);
            stmt.setString(2, login);
            stmt.setString(3, senha);
            stmt.setString(4, tipo.toString());
            stmt.setInt(5, id);

            // executa
            stmt.execute();
        }
    }
    
    public void alterarNome(int id, String nome) throws SQLException{
        String sql;
        sql = "UPDATE Usuarios SET nome = ? WHERE id = ?";
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
        sql = "UPDATE Usuarios SET login = ? WHERE id = ?";
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
        sql = "UPDATE Usuarios SET senha = ? WHERE id = ?";
        try ( // prepared statement para inserção
            PreparedStatement stmt = con.prepareStatement(sql)) {
            // seta os valores
            stmt.setString(1, senha);
            stmt.setInt(2, id);

            // executa
            stmt.execute();
        }
    }
    
    public void alterarTipo(int id, UsuTipo tipo) throws SQLException{
        String sql;
        sql = "UPDATE Usuarios SET tipo = ? WHERE id = ?";
        try ( // prepared statement para inserção
            PreparedStatement stmt = con.prepareStatement(sql)) {
            // seta os valores
            stmt.setString(1, tipo.toString());
            stmt.setInt(2, id);

            // executa
            stmt.execute();
        }
    }
    
    public void excluir(int id) throws SQLException{
        String sql = "DELETE FROM Usuarios WHERE id = ?";
        // seta os valores
        try ( // prepared statement para inserção
                PreparedStatement stmt = con.prepareStatement(sql)) {
            // seta os valores
            stmt.setInt(1, id);
            // executa
            stmt.execute();
        }
    }
    
    public List<Usuarios> listar() throws SQLException{
        // usus: array armazena a lista de registros

        List<Usuarios> usus = new ArrayList<>();
        
        String sql = "SELECT * FROM Usuarios";
        try (PreparedStatement stmt = this.con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                // criando o objeto Usuarios
                Usuarios usu = new Usuarios(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        UsuTipo.valueOf(rs.getString(5))
                );
                // adiciona o usu à lista de usus
                usus.add(usu);
            }
            
        }
        return usus;
   }
}
