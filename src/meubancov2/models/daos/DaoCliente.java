package meubancov2.models.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import meubancov2.models.beans.Cliente;
import meubancov2.utils.Conexao;

/**
 *
 * @author scar
 */
public class DaoCliente {
    private final Connection con;

    public DaoCliente() throws SQLException, ClassNotFoundException {
        this.con = new Conexao().getConnection();
    }
    
    public Cliente inserir(Cliente clien) throws SQLException{
        String sql = "insert into Cliente" + " values (default, ?, ?, ?, ?, ?)";
    
        // prepared statement para inserção
        PreparedStatement stmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

        // seta os valores
        stmt.setString(1,clien.getNome());
        stmt.setString(2,clien.getRg());
        stmt.setString(3,clien.getCpf());
        stmt.setString(4,clien.getEmail());
        stmt.setString(5,clien.getTelefone());

        // executa
        stmt.executeUpdate();
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            int id = rs.getInt(1);
            clien.setId(id);
        }
        stmt.close();
        return clien;
    }
    
    public Cliente buscarId(int id) throws SQLException{
        String sql = "SELECT * FROM Cliente WHERE id = ?";
        Cliente clienSaida;
        // seta os valores
        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            // seta os valores
            stmt.setInt(1, id);
            // executa
            ResultSet rs = stmt.executeQuery();
            clienSaida = null;
            while (rs.next()) {
                // criando o objeto Usuario
                clienSaida = new Cliente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));
                // adiciona o usu à lista de usus
            }
        }
        return clienSaida;
    }
    
    public List<Cliente> buscarNome(String nome) throws SQLException{
        String sql = "SELECT * FROM Cliente WHERE nome like ?";
        List<Cliente> cliens = new ArrayList();
        // seta os valores
        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            // seta os valores
            stmt.setString(1, nome + "%");
            // executa
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // criando o objeto Usuario
                Cliente clien = new Cliente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));
                // adiciona o usu à lista de usus
                cliens.add(clien);
            }
        }
        return cliens;
    }
    
    public Cliente buscarRg(String rg) throws SQLException{
        String sql = "SELECT * FROM Cliente WHERE rg = ?";
        Cliente clienSaida;
        // seta os valores
        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            // seta os valores
            stmt.setString(1, rg);
            // executa
            ResultSet rs = stmt.executeQuery();
            clienSaida = null;
            while (rs.next()) {
                // criando o objeto Usuario
                clienSaida = new Cliente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));
                // adiciona o usu à lista de usus
            }
        }
        return clienSaida;
    }
    
    public Cliente buscarCpf(String cpf) throws SQLException{
        String sql = "SELECT * FROM Cliente WHERE cpf = ?";
        Cliente clienSaida;
        // seta os valores
        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            // seta os valores
            stmt.setString(1, cpf);
            // executa
            ResultSet rs = stmt.executeQuery();
            clienSaida = null;
            while (rs.next()) {
                // criando o objeto Usuario
                clienSaida = new Cliente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));
                // adiciona o usu à lista de usus
            }
        }
        return clienSaida;
    }
    
    public List<Cliente> buscarEmail(String email) throws SQLException{
        String sql = "SELECT * FROM Cliente WHERE email like ?";
        List<Cliente> cliens = new ArrayList();
        // seta os valores
        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            // seta os valores
            stmt.setString(1, email + "%");
            // executa
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // criando o objeto Usuario
                Cliente clien = new Cliente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));
                // adiciona o usu à lista de usus
                cliens.add(clien);
            }
        }
        return cliens;
    }
    
    public Cliente buscarTelefone(String telefone) throws SQLException{
        String sql = "SELECT * FROM Cliente WHERE telefone = ?";
        Cliente clienSaida;
        // seta os valores
        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            // seta os valores
            stmt.setString(1, telefone);
            // executa
            ResultSet rs = stmt.executeQuery();
            clienSaida = null;
            while (rs.next()) {
                // criando o objeto Usuario
                clienSaida = new Cliente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));
                // adiciona o usu à lista de usus
            }
        }
        return clienSaida;
    }
    
    public void alterarTudo(int id, String nome, String rg, String cpf, String email, String telefone) throws SQLException{
        String sql = "UPDATE Cliente SET nome = ?, rg = ?, cpf = ?, email = ?, telefone = ? WHERE id = ?";
        // seta os valores
        try ( // prepared statement para inserção
                PreparedStatement stmt = con.prepareStatement(sql)) {
            // seta os valores
            stmt.setString(1, nome);
            stmt.setString(2, rg);
            stmt.setString(3, cpf);
            stmt.setString(4, email);
            stmt.setString(5, telefone);
            stmt.setInt(6, id);
            
            // executa
            stmt.execute();
        }
    }
    
    public void alterarNome(int id, String nome) throws SQLException{
        String sql = "UPDATE Cliente SET nome = ? WHERE id = ?";
        // seta os valores
        try ( // prepared statement para inserção
                PreparedStatement stmt = con.prepareStatement(sql)) {
            // seta os valores
            stmt.setString(1, nome);
            stmt.setInt(2, id);
            
            // executa
            stmt.execute();
        }
    }
    
    public void alterarRg(int id, String rg) throws SQLException{
        String sql = "UPDATE Cliente SET rg = ? WHERE id = ?";
        // seta os valores
        try ( // prepared statement para inserção
                PreparedStatement stmt = con.prepareStatement(sql)) {
            // seta os valores
            stmt.setString(1, rg);
            stmt.setInt(2, id);
            
            // executa
            stmt.execute();
        }
    }
    
    public void alterarCpf(int id, String cpf) throws SQLException{
        String sql = "UPDATE Cliente SET cpf = ? WHERE id = ?";
        // seta os valores
        try ( // prepared statement para inserção
                PreparedStatement stmt = con.prepareStatement(sql)) {
            // seta os valores
            stmt.setString(1, cpf);
            stmt.setInt(2, id);
            
            // executa
            stmt.execute();
        }
    }
    
    public void alterarEmail(int id, String email) throws SQLException{
        String sql = "UPDATE Cliente SET email = ? WHERE id = ?";
        // seta os valores
        try ( // prepared statement para inserção
                PreparedStatement stmt = con.prepareStatement(sql)) {
            // seta os valores
            stmt.setString(1, email);
            stmt.setInt(2, id);
            
            // executa
            stmt.execute();
        }
    }
    
    public void alterarTelefone(int id, String telefone) throws SQLException{
        String sql = "UPDATE Cliente SET telefone = ? WHERE id = ?";
        // seta os valores
        try ( // prepared statement para inserção
                PreparedStatement stmt = con.prepareStatement(sql)) {
            // seta os valores
            stmt.setString(1, telefone);
            stmt.setInt(2, id);
            
            // executa
            stmt.execute();
        }
    }
    
    public void excluir(int id) throws SQLException{
        String sql = "DELETE FROM Cliente WHERE id = ?";
        // seta os valores
        try ( // prepared statement para inserção
                PreparedStatement stmt = con.prepareStatement(sql)) {
            // seta os valores
            stmt.setInt(1, id);
            // executa
            stmt.execute();
        }
    }
    
    public List<Cliente> listar() throws SQLException{
        // usus: array armazena a lista de registros

        List<Cliente> cliens = new ArrayList<>();
        
        String sql = "SELECT * FROM Cliente";
        try (PreparedStatement stmt = this.con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                // criando o objeto Usuario
                Cliente clien = new Cliente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                );
                // adiciona o usu à lista de usus
                cliens.add(clien);
            }
            
        }
        return cliens;
   }
}
