package meubancov2.views;

import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import meubancov2.controllers.ControllerConta;
import meubancov2.models.beans.Conta;

/**
 *
 * @author scar
 */
public class ManterConta {

    public static void menu() throws SQLException, ClassNotFoundException {
        int opc = 7;
        while (opc != 0) {
            String msg = """
                         Conta:
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
        int idGerente;
        int idCliente;
        float valor;
        String tipo;
        
        try {
            String resultGeren = JOptionPane.showInputDialog("ID DO GERENTE");
            idGerente = Integer.parseInt(resultGeren);

            String resultClien = JOptionPane.showInputDialog("ID DO CLIENTE");
            idCliente = Integer.parseInt(resultClien);

            String resultVal = JOptionPane.showInputDialog("VALOR");
            valor = Float.parseFloat(resultVal);

            tipo = JOptionPane.showInputDialog("TIPO");
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, digite os valores corretos!");
            return;
        }
        
        Conta conta = new Conta(idGerente, idCliente, valor, tipo);
        ControllerConta contclien = new ControllerConta();
        conta = contclien.inserir(conta);
        JOptionPane.showMessageDialog(null, conta.toString());
    }
    
    public static void alterar() throws SQLException, ClassNotFoundException {
        int id;
        int idGerente;
        int idCliente;
        float valor;
        String tipo;
        
        try {
            String resultId = JOptionPane.showInputDialog("ID");
            id = Integer.parseInt(resultId);

            String resultGeren = JOptionPane.showInputDialog("ID DO GERENTE");
            idGerente = Integer.parseInt(resultGeren);

            String resultClien = JOptionPane.showInputDialog("ID DO CLIENTE");
            idCliente = Integer.parseInt(resultClien);

            String resultVal = JOptionPane.showInputDialog("VALOR");
            valor = Float.parseFloat(resultVal);

            tipo = JOptionPane.showInputDialog("TIPO");
        } catch (HeadlessException | NumberFormatException e) {
            return;
        }
        
        Conta conta = new Conta(id, idGerente, idCliente, valor, tipo);
        ControllerConta contclien = new ControllerConta();
        conta = contclien.alterar(conta);
        JOptionPane.showMessageDialog(null,conta.toString());
    }
    
    public static void buscar() throws SQLException, ClassNotFoundException {
        int id;
        try {
            id = Integer.parseInt(JOptionPane.showInputDialog("ID"));
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Por favor, digite um ID");
            return;
        }
        
        Conta conta = new Conta(id);
        ControllerConta contclien = new ControllerConta();
        conta = contclien.buscar(conta);
        JOptionPane.showMessageDialog(null, conta.toString());
    }
    
    public static void listar() throws SQLException, ClassNotFoundException {
        String lista = "";
        ControllerConta contconta = new ControllerConta();
        List<Conta> listaConta = contconta.listar();
        for (Conta contaSaida : listaConta) {
            lista = lista + contaSaida.toString() + '\n';
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
        
        Conta conta = new Conta(id);
        ControllerConta contclien = new ControllerConta();
        conta = contclien.excluir(conta);
        JOptionPane.showMessageDialog(null, conta.toString());
    }
}
