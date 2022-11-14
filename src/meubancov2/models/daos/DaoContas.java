package meubancov2.models.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import meubancov2.models.beans.Contas;
import meubancov2.models.beans.ContaTipo;
import meubancov2.utils.Conexao;

/**
 *
 * @author scar
 */
public class DaoContas {
    private final Connection con;

    public DaoContas() throws SQLException, ClassNotFoundException {
        this.con = new Conexao().getConnection();
    }
    
    public Contas inserir(Contas conta) throws SQLException{
        String sql = "insert into Contas" + " values (default, ?, ?, ?, ?)";
    
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
    
    public void alterarTudo(int id, int idCliente, int idUsuario, Float valor, ContaTipo tipo) throws SQLException{
        String sql = "UPDATE Contas SET idCliente = ?, idUsuario = ?, valor = ?, tipo = ? WHERE id = ?";
        // seta os valores
        try ( // prepared statement para inserção
                PreparedStatement stmt = con.prepareStatement(sql)) {
            // seta os valores
            stmt.setInt(1, idCliente);
            stmt.setInt(2, idUsuario);
            stmt.setFloat(3, valor);
            stmt.setString(4, tipo.toString());
            stmt.setInt(5, id);
            
            // executa
            stmt.execute();
        }
    }
    
    public void alterarIdClien(int id, int idCliente) throws SQLException{
        String sql = "UPDATE Contas SET idCliente = ? WHERE id = ?";
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
    
    public void alterarIdUsu(int id, int idUsuario) throws SQLException{
        String sql = "UPDATE Contas SET idUsuario = ? WHERE id = ?";
        // seta os valores
        try ( // prepared statement para inserção
                PreparedStatement stmt = con.prepareStatement(sql)) {
            // seta os valores
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, id);
            
            // executa
            stmt.execute();
        }
    }
    
    public void alterarValor(int id, Float valor) throws SQLException{
        String sql = "UPDATE Contas SET valor = ? WHERE id = ?";
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
    
    public void alterarTipo(int id, ContaTipo tipo) throws SQLException{
        String sql = "UPDATE Contas SET tipo = ? WHERE id = ?";
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
        String sql = "DELETE FROM Contas WHERE id = ?";
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
        String sql = "DELETE FROM Contas WHERE idCliente = ?";
        // seta os valores
        try ( // prepared statement para inserção
                PreparedStatement stmt = con.prepareStatement(sql)) {
            // seta os valores
            stmt.setInt(1, idCliente);
            // executa
            stmt.execute();
        }
    }
    
    public Contas buscarId(int id) throws SQLException{
        String sql = "SELECT * FROM Contas WHERE id like ?";
        Contas contaSaida;
        // seta os valores
        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            // seta os valores
            stmt.setInt(1, id);
            // executa
            ResultSet rs = stmt.executeQuery();
            contaSaida = null;
            while (rs.next()) {
                // criando o objeto Usuario
                contaSaida = new Contas(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getFloat(4),
                        ContaTipo.valueOf(rs.getString(5)));
                // adiciona o usu à lista de usus
            }
        }
        return contaSaida;
    }
    
    public List<Contas> buscarIdCliente(int idCliente) throws SQLException{
        String sql = "SELECT * FROM Contas WHERE idCliente like ?";
        List<Contas> contas = new ArrayList();
        // seta os valores
        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            // seta os valores
            stmt.setInt(1, idCliente);
            // executa
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // criando o objeto Usuario
                Contas conta = new Contas(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getFloat(4),
                        ContaTipo.valueOf(rs.getString(5)));
                // adiciona o usu à lista de usus
                contas.add(conta);
            }
        }
        return contas;
    }
    
    public List<Contas> buscarIdUsuario(int idUsuario) throws SQLException{
        String sql = "SELECT * FROM Contas WHERE idUsuario like ?";
        List<Contas> contas = new ArrayList();
        // seta os valores
        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            // seta os valores
            stmt.setInt(1, idUsuario);
            // executa
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // criando o objeto Usuario
                Contas conta = new Contas(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getFloat(4),
                        ContaTipo.valueOf(rs.getString(5)));
                // adiciona o usu à lista de usus
                contas.add(conta);
            }
        }
        return contas;
    }
   
    public List<Contas> buscarTipo(ContaTipo tipo) throws SQLException{
        String sql = "SELECT * FROM Contas WHERE tipo = ?";
        List<Contas> contas = new ArrayList();
        // seta os valores
        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            // seta os valores
            stmt.setString(1, tipo.toString());
            // executa
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // criando o objeto Usuario
                Contas conta = new Contas(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getFloat(4),
                        ContaTipo.valueOf(rs.getString(5)));
                // adiciona o usu à lista de usus
                contas.add(conta);
            }
        }
        return contas;
    }

    public List<Contas> listar() throws SQLException{
        // usus: array armazena a lista de registros

        List<Contas> contas = new ArrayList<>();
        
        String sql = "SELECT * FROM Contas";
        try (PreparedStatement stmt = this.con.prepareStatement(sql); 
                ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                // criando o objeto Usuario
                Contas conta = new Contas(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getFloat(4),
                        ContaTipo.valueOf(rs.getString(5))
                );
                // adiciona o usu à lista de usus
                contas.add(conta);
            }
            
        }
        return contas;
   }
   
    public void alterarUsuario(int idNovo, int idAntigo) throws SQLException {
        String sql = "UPDATE Contas SET idUsuario = ? WHERE idUsuario = ?";
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
