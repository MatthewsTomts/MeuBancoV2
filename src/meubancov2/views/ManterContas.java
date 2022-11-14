package meubancov2.views;

import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import meubancov2.controllers.ControllerContas;
import meubancov2.controllers.ControllerClientes;
import meubancov2.controllers.ControllerUsuarios;
import meubancov2.models.beans.Contas;
import meubancov2.models.beans.Clientes;
import meubancov2.models.beans.Usuarios;
import meubancov2.models.beans.ContaTipo;
/**
 *
 * @author scar
 */
public class ManterContas {

    public static void menu() throws SQLException, ClassNotFoundException {
        int opc = 7;
        while (opc != 0) {
            String msg = """
                         Contas:
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
        int idUsuario;
        int idCliente;
        float valor;
        String tipoContas;
        String[] tipos = {"POUPANÇA", "CORRENTE"};
        ControllerContas contconta = new ControllerContas();
        ControllerClientes contclien = new ControllerClientes();
        ControllerUsuarios contgeren = new ControllerUsuarios();
        
        try {
            idCliente = Integer.parseInt(JOptionPane.showInputDialog("ID DO CLIENTE"));
            Clientes clienId = contclien.buscarId(idCliente);
            if (clienId == null) {
                JOptionPane.showMessageDialog(null,"Cliente não encontrado.");
                return;
            } else JOptionPane.showMessageDialog(null,clienId.toString());
            
            idUsuario = Integer.parseInt(JOptionPane.showInputDialog("ID DO USUARIO"));
            Usuarios gerenId = contgeren.buscarId(idUsuario);
            if (gerenId == null) {
                JOptionPane.showMessageDialog(null,"Usuario não encontrado.");
                return;
            } else JOptionPane.showMessageDialog(null,gerenId.toString());

            valor = Float.parseFloat(JOptionPane.showInputDialog("VALOR"));

            tipoContas = (String) JOptionPane.showInputDialog(
                            null,
                            "Tipo:",
                            "Escolha um tipo",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            tipos,
                            tipos[1]);
            
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, digite os valores corretos!");
            return;
        }
        
        Contas conta = new Contas(idUsuario, idCliente, valor, ContaTipo.valueOf(tipoContas));
        conta = contconta.inserir(conta);
        JOptionPane.showMessageDialog(null, conta.toString());
    }
    
    public static void buscar() throws SQLException, ClassNotFoundException {
        while (true) {
            int opc;
            String msg = """
                         O que você deseja pesquisa?:
                          1 - Id do Cliente
                          2 - Id do Usuario 
                          3 - Id da Conta
                          4 - Tipo
                          5 - Voltar
                         """;
            String result = JOptionPane.showInputDialog(msg);

            try {
                opc = Integer.parseInt(result);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,"Por favor, escolha uma opção");
                continue;
            }

            String lista = BuscarOpc(opc);
            
            if (lista.equals("")) {
                break;
            }     
            
            JOptionPane.showMessageDialog(null,lista);
        }
    }
    
    public static void alterar() throws SQLException, ClassNotFoundException {
        int id;
        ControllerContas contconta = new ControllerContas();
        
        try {
            id = Integer.parseInt(JOptionPane.showInputDialog("ID"));
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Por favor, digite um ID");
            return;
        }
        
        Contas contaId = contconta.buscarId(id);
        if (contaId == null) {
            JOptionPane.showMessageDialog(null,"Conta não encontrado.");
            return;
        } else JOptionPane.showMessageDialog(null,contaId.toString());
        
        while (true) {
            int opc;
            String msg = """
                         O que você deseja alterar?:
                          1 - Id do Cliente 
                          2 - Id do Usuario
                          3 - Valor
                          4 - Tipo
                          5 - Tudo
                          6 - Voltar
                         """;
            String result = JOptionPane.showInputDialog(msg);

            try {
                opc = Integer.parseInt(result);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,"Por favor, escolha uma opção");
                continue;
            }

            if (AlterarOpc(opc, id)) {
                break;
            }
            
            contaId = contconta.buscarId(id);
            JOptionPane.showMessageDialog(null, contaId);
        }
        
    }
    
    public static void listar() throws SQLException, ClassNotFoundException {
        String lista = "";
        ControllerContas contconta = new ControllerContas();
        List<Contas> listaConta = contconta.listar();
        for (Contas contaSaida : listaConta) {
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
        
        ControllerContas contconta = new ControllerContas();
        Contas contaId = contconta.buscarId(id);
        if (contaId == null) {
            JOptionPane.showMessageDialog(null,"Conta não encontrado.");
            return;
        } else JOptionPane.showMessageDialog(null, contaId.toString());
        
        contconta.excluir(id);
    }
    
    public static String BuscarOpc(int opc) throws SQLException, ClassNotFoundException {
        int idCliente;
        int idUsuario;
        int id;
        String tipoContas;
        String[] tipos = {"POUPANÇA", "CORRENTE"};
        
        String lista = "";
        List<Contas> conta;
        ControllerContas contconta = new ControllerContas();
        
        switch (opc) {
            case 1 -> {
                try {
                    idCliente = Integer.parseInt(JOptionPane.showInputDialog("Id do Cliente"));
                } catch (HeadlessException | NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Digite um Id válido");
                    return lista;
                }

                conta = contconta.buscarIdCliente(idCliente);
                if (conta.isEmpty()) {
                    lista = "Conta não encontrado.";
                } else {
                    for (Contas contaSaida : conta) {
                        lista = lista + "\n" + contaSaida.toString();
                    }
                }
                break;
            }
            case 2 -> {
                try {
                    idUsuario = Integer.parseInt(JOptionPane.showInputDialog("Id do Usuario"));
                } catch (HeadlessException | NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Digite um Id válido");
                    return lista;
                }

                conta = contconta.buscarIdUsuario(idUsuario);
                if (conta.isEmpty()) {
                    lista = "Conta não encontrado.";
                } else {
                    for (Contas contaSaida : conta) {
                        lista = lista + "\n" + contaSaida.toString();
                    }
                }
                break;
            }
            case 3 -> {
                try {
                    id = Integer.parseInt(JOptionPane.showInputDialog("Id da Conta"));
                } catch (HeadlessException | NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Digite um ID válido");
                    return lista;
                }

                Contas contaId = contconta.buscarId(id);
                if (contaId == null) {
                    lista = "Conta não encontrado.";
                } else {
                    lista = contaId.toString();
                }
            }
            case 4 -> {
                try {
                    tipoContas = (String) JOptionPane.showInputDialog(
                            null,
                            "Tipo:",
                            "Escolha um tipo",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            tipos,
                            tipos[1]);
                } catch (HeadlessException | NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Digite um tipo válido");
                    return lista;
                }

                conta = contconta.buscarTipo(ContaTipo.valueOf(tipoContas));

                if (conta.isEmpty()) {
                    lista = "Conta não encontrado.";
                } else {
                    for (Contas contaSaida : conta) {
                        lista = lista + "\n" + contaSaida.toString();
                    }
                }
                break;
            }
            case 5 -> {return lista;}
            default -> {
                JOptionPane.showMessageDialog(null,"Digite um valor válido"); 

            }
        }
        return lista;
    }
    
    public static Boolean AlterarOpc(int opc, int id) throws SQLException, ClassNotFoundException {
        int idCliente;
        int idUsuario;
        Float valor;
        String tipoContas;
        String[] tipos = {"POUPANÇA", "CORRENTE"};
        ControllerContas contconta = new ControllerContas();
        ControllerUsuarios contusu = new ControllerUsuarios();
        ControllerClientes contclien = new ControllerClientes();
        
        switch (opc) {
            case 1 -> {
                try {
                    idCliente = Integer.parseInt(JOptionPane.showInputDialog("Id do Cliente"));

                    Clientes clienId = contclien.buscarId(id);
                    if (clienId == null) {
                        JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
                        break;
                    } else JOptionPane.showMessageDialog(null, clienId.toString());
                } catch (HeadlessException | NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Digite um Id válido");
                    break;
                }
                
                contconta.alterarIdClien(id, idCliente);
            }
            case 2 -> {
                try {
                    idUsuario = Integer.parseInt(JOptionPane.showInputDialog("Id do Usuario"));
                    
                    Usuarios usuId = contusu.buscarId(id);
                    if (usuId == null) {
                        JOptionPane.showMessageDialog(null,"Usuario não encontrado.");
                        break;
                    } else JOptionPane.showMessageDialog(null,usuId.toString());
                } catch (HeadlessException | NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Digite um Id válido");
                    break;
                }
                
                contconta.alterarIdUsu(id, idUsuario);
            }
            case 3 -> {
                try {
                    valor = Float.parseFloat(JOptionPane.showInputDialog("Valor"));
                } catch (HeadlessException | NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Digite um valor válido");
                    break;
                }
                
                contconta.alterarValor(id, valor);
            }
            case 4 -> {
                try {
                    tipoContas = (String) JOptionPane.showInputDialog(
                            null,
                            "Tipo:",
                            "Escolha um tipo",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            tipos,
                            tipos[1]);
                } catch (HeadlessException | NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Digite um tipo válido");
                    break;
                }
                
                contconta.alterarTipo(id, ContaTipo.valueOf(tipoContas));
            }
            case 5 -> {
                try {
                    idCliente = Integer.parseInt(JOptionPane.showInputDialog("Id do Cliente"));
                    idUsuario = Integer.parseInt(JOptionPane.showInputDialog("Id do Usuario"));
                    valor = Float.parseFloat(JOptionPane.showInputDialog("Valor"));
                    tipoContas = (String) JOptionPane.showInputDialog(
                            null,
                            "Tipo:",
                            "Escolha um tipo",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            tipos,
                            tipos[1]);
                } catch (HeadlessException | NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Digite um Id válido");
                    break;
                }
                
                contconta.alterarTudo(id, idCliente, idUsuario,
                        valor, ContaTipo.valueOf(tipoContas));
            }
            case 6 -> {return true;}
            default -> {
                JOptionPane.showMessageDialog(null,"Digite um valor válido"); 

            }
        }
        return false;
    }

}
