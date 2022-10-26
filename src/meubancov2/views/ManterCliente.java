package meubancov2.views;

import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import meubancov2.controllers.ControllerCliente;
import meubancov2.models.beans.Cliente;

/**
 *
 * @author scar
 */
public class ManterCliente {
    
    public static void menu() throws SQLException, ClassNotFoundException {
        int opc = 7;
        while (opc != 0) {
            String msg = """
                         Cliente:
                          1 - Inserir 
                          2 - Alterar 
                          3 - Buscar 
                          4 - Listar 
                          5 - Excluir 
                          6 - Voltar""" ;


            String result = JOptionPane.showInputDialog(msg);
            try {
                 opc = Integer.parseInt(result);
            }catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,"Por favor, escolha uma opção");
                continue;
            }      
            switch (opc) {
                case 1 -> inserir();
                case 2 -> alterar();
                case 3 -> buscar();
                case 4 -> listar();
                case 5 -> excluir();
                case 6 -> opc = 0;
                default -> {
                        JOptionPane.showMessageDialog(null,"Opção inválido");
                        opc = 1;
                    }
            }
        }
    }
    
    public static void inserir() throws SQLException, ClassNotFoundException {
        String nome = JOptionPane.showInputDialog("NOME");
        if (nome == null) return;
        String rg = JOptionPane.showInputDialog("RG");
        if (rg == null) return;
        String cpf = JOptionPane.showInputDialog("CPF");
        if (cpf == null) return;
        String email = JOptionPane.showInputDialog("EMAIL");
        if (email == null) return;
        String telefone = JOptionPane.showInputDialog("TELEFONE");
        if (telefone == null) return;
        
        Cliente clien = new Cliente(nome, rg, cpf, email, telefone);
        ControllerCliente contclien = new ControllerCliente();
        clien = contclien.inserir(clien);
        JOptionPane.showMessageDialog(null, clien.toString());
    }
    
    public static void alterar() throws SQLException, ClassNotFoundException {
        int id;
        try {
            id = Integer.parseInt(JOptionPane.showInputDialog("ID"));
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Por favor, digite um ID");
            return;
        }
        
        String nome = JOptionPane.showInputDialog("NOME");
        if (nome == null) return;
        String rg = JOptionPane.showInputDialog("RG");
        if (rg == null) return;
        String cpf = JOptionPane.showInputDialog("CPF");
        if (cpf == null) return;
        String email = JOptionPane.showInputDialog("EMAIL");
        if (email == null) return;
        String telefone = JOptionPane.showInputDialog("TELEFONE");
        if (telefone == null) return;
        
        Cliente clien = new Cliente(id, nome, rg, cpf, email, telefone);
        ControllerCliente contclien = new ControllerCliente();
        clien = contclien.alterar(clien);
        JOptionPane.showMessageDialog(null,clien.toString());
    }
    
    public static void buscar() throws SQLException, ClassNotFoundException {
        int id;
        try {
            id = Integer.parseInt(JOptionPane.showInputDialog("ID"));
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Por favor, digite um ID");
            return;
        }
        
        Cliente clien = new Cliente(id);
        ControllerCliente contclien = new ControllerCliente();
        clien = contclien.buscar(clien);
        JOptionPane.showMessageDialog(null, clien.toString());
    }
    
    public static void listar() throws SQLException, ClassNotFoundException {
        String lista = "";
        ControllerCliente contclien = new ControllerCliente();
        List<Cliente> listaClien = contclien.listar();
        for (Cliente clienSaida : listaClien) {
            lista = lista + clienSaida.toString() + '\n';
        }
        JOptionPane.showMessageDialog(null,lista);
    }
    
    public static void excluir() throws SQLException, ClassNotFoundException {
        int id;
        try {
            id = Integer.parseInt(JOptionPane.showInputDialog("ID"));
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Por favor, digite um ID");
            return;
        }
        
        Cliente clien = new Cliente(id);
        ControllerCliente contclien = new ControllerCliente();
        clien = contclien.excluir(clien);
        JOptionPane.showMessageDialog(null, clien.toString());
    }
}
