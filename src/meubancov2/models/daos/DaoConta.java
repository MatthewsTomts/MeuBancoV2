package meubancov2.models.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import meubancov2.models.beans.Conta;
import meubancov2.models.beans.Tipo;
import meubancov2.utils.Conexao;

/**
 *
 * @author scar
 */
public class DaoConta {
    private final Connection con;

    public DaoConta() throws SQLException, ClassNotFoundException {
        this.con = new Conexao().getConnection();
    }
    
    public Conta inserir(Conta conta) throws SQLException{
        String sql = "insert into Conta" + " values (default, ?, ?, ?, ?)";
    
        try (PreparedStatement stmt =
                        con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, conta.getIdCliente());
            stmt.setInt(2, conta.getIdGerente());
            stmt.setFloat(3, conta.getValor());
            stmt.setString(4, conta.getTipo().toString());
            
            // executa
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                conta.setId(id);
            }
        }
        return conta;
    }
    
    public void alterarTudo(int id, int idCliente, int idGerente, Float valor, Tipo tipo) throws SQLException{
        String sql = "UPDATE Conta SET idCliente = ?, idGerente = ?, valor = ?, tipo = ? WHERE id = ?";
        // seta os valores
        try ( // prepared statement para inserção
                PreparedStatement stmt = con.prepareStatement(sql)) {
            // seta os valores
            stmt.setInt(1, idCliente);
            stmt.setInt(2, idGerente);
            stmt.setFloat(3, valor);
            stmt.setString(4, tipo.toString());
            stmt.setInt(5, id);
            
            // executa
            stmt.execute();
        }
    }
    
    public void alterarIdClien(int id, int idCliente) throws SQLException{
        String sql = "UPDATE Conta SET idCliente = ? WHERE id = ?";
        // seta os valores
        try ( // prepared statement para inserção
                PreparedStatement stmt = con.prepareStatement(sql)) {
            // seta os valores
            stmt.setInt(1, idCliente);
            stmt.setInt(2, id);
            
            // executa
            stmt.execute();
        }
    }
    
    public void alterarIdGeren(int id, int idGerente) throws SQLException{
        String sql = "UPDATE Conta SET idGerente = ? WHERE id = ?";
        // seta os valores
        try ( // prepared statement para inserção
                PreparedStatement stmt = con.prepareStatement(sql)) {
            // seta os valores
            stmt.setInt(1, idGerente);
            stmt.setInt(2, id);
            
            // executa
            stmt.execute();
        }
    }
    
    public void alterarValor(int id, Float valor) throws SQLException{
        String sql = "UPDATE Conta SET valor = ? WHERE id = ?";
        // seta os valores
        try ( // prepared statement para inserção
                PreparedStatement stmt = con.prepareStatement(sql)) {
            // seta os valores
            stmt.setFloat(1, valor);
            stmt.setInt(2, id);
            
            // executa
            stmt.execute();
        }
    }
    
    public void alterarTipo(int id, Tipo tipo) throws SQLException{
        String sql = "UPDATE Conta SET tipo = ? WHERE id = ?";
        // seta os valores
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
        String sql = "DELETE FROM Conta WHERE id = ?";
        // seta os valores
        try ( // prepared statement para inserção
                PreparedStatement stmt = con.prepareStatement(sql)) {
            // seta os valores
            stmt.setInt(1, id);
            // executa
            stmt.execute();
        }
    }
    
    public void excluirContas(int idCliente) throws SQLException {
        String sql = "DELETE FROM Conta WHERE idCliente = ?";
        // seta os valores
        try ( // prepared statement para inserção
                PreparedStatement stmt = con.prepareStatement(sql)) {
            // seta os valores
            stmt.setInt(1, idCliente);
            // executa
            stmt.execute();
        }
    }
    
    public Conta buscarId(int id) throws SQLException{
        String sql = "SELECT * FROM Conta WHERE id like ?";
        Conta contaSaida;
        // seta os valores
        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            // seta os valores
            stmt.setInt(1, id);
            // executa
            ResultSet rs = stmt.executeQuery();
            contaSaida = null;
            while (rs.next()) {
                // criando o objeto Usuario
                contaSaida = new Conta(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getFloat(4),
                        Tipo.valueOf(rs.getString(5)));
                // adiciona o usu à lista de usus
            }
        }
        return contaSaida;
    }
    
    public Conta buscarIdCliente(int idCliente) throws SQLException{
        String sql = "SELECT * FROM Conta WHERE idCliente like ?";
        Conta contaSaida;
        // seta os valores
        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            // seta os valores
            stmt.setInt(1, idCliente);
            // executa
            ResultSet rs = stmt.executeQuery();
            contaSaida = null;
            while (rs.next()) {
                // criando o objeto Usuario
                contaSaida = new Conta(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getFloat(4),
                        Tipo.valueOf(rs.getString(5)));
                // adiciona o usu à lista de usus
            }
        }
        return contaSaida;
    }
    
    public Conta buscarIdGerente(int idGerente) throws SQLException{
        String sql = "SELECT * FROM Conta WHERE idGerente like ?";
        Conta contaSaida;
        // seta os valores
        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            // seta os valores
            stmt.setInt(1, idGerente);
            // executa
            ResultSet rs = stmt.executeQuery();
            contaSaida = null;
            while (rs.next()) {
                // criando o objeto Usuario
                contaSaida = new Conta(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getFloat(4),
                        Tipo.valueOf(rs.getString(5)));
                // adiciona o usu à lista de usus
            }
        }
        return contaSaida;
    }
   
    public List<Conta> buscarTipo(Tipo tipo) throws SQLException{
        String sql = "SELECT * FROM Conta WHERE tipo = ?";
        List<Conta> contas = new ArrayList();
        // seta os valores
        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            // seta os valores
            stmt.setString(1, tipo.toString());
            // executa
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // criando o objeto Usuario
                Conta conta = new Conta(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getFloat(4),
                        Tipo.valueOf(rs.getString(5)));
                // adiciona o usu à lista de usus
                contas.add(conta);
            }
        }
        return contas;
    }

    public List<Conta> listar() throws SQLException{
        // usus: array armazena a lista de registros

        List<Conta> contas = new ArrayList<>();
        
        String sql = "SELECT * FROM Conta";
        try (PreparedStatement stmt = this.con.prepareStatement(sql); 
                ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                // criando o objeto Usuario
                Conta conta = new Conta(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getFloat(4),
                        Tipo.valueOf(rs.getString(5))
                );
                // adiciona o usu à lista de usus
                contas.add(conta);
            }
            
        }
        return contas;
   }
   
    public void alterarGerente(int idNovo, int idAntigo) throws SQLException {
        String sql = "UPDATE Conta SET idGerente = ? WHERE idGerente = ?";
        // seta os valores
        try ( // prepared statement para inserção
                PreparedStatement stmt = con.prepareStatement(sql)) {
            // seta os valores
            stmt.setInt(1, idNovo);
            stmt.setInt(2, idAntigo);
            
            // executa
            stmt.execute();
        }
    }
}
