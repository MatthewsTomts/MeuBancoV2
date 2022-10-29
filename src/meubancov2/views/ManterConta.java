package meubancov2.views;

import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import meubancov2.controllers.ControllerConta;
import meubancov2.controllers.ControllerCliente;
import meubancov2.controllers.ControllerGerente;
import meubancov2.models.beans.Conta;
import meubancov2.models.beans.Cliente;
import meubancov2.models.beans.Gerente;
import meubancov2.models.beans.Tipo;

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
        int tipo;
        Tipo tipoConta;
        ControllerConta contconta = new ControllerConta();
        ControllerCliente contclien = new ControllerCliente();
        ControllerGerente contgeren = new ControllerGerente();
        
        try {
            idCliente = Integer.parseInt(JOptionPane.showInputDialog("ID DO CLIENTE"));
            Cliente clienId = contclien.buscarId(idCliente);
            if (clienId == null) {
                JOptionPane.showMessageDialog(null,"Cliente não encontrado.");
                return;
            } else JOptionPane.showMessageDialog(null,clienId.toString());
            
            idGerente = Integer.parseInt(JOptionPane.showInputDialog("ID DO GERENTE"));
            Gerente gerenId = contgeren.buscarId(idGerente);
            if (gerenId == null) {
                JOptionPane.showMessageDialog(null,"Gerente não encontrado.");
                return;
            } else JOptionPane.showMessageDialog(null,gerenId.toString());

            valor = Float.parseFloat(JOptionPane.showInputDialog("VALOR"));

            tipo = Integer.parseInt(JOptionPane.showInputDialog("""
                TIPO:
                 1- Corrente
                 2- Poupança"""));
            
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, digite os valores corretos!");
            return;
        }
        
        switch (tipo) {
            case 1 -> {
                tipoConta = Tipo.CORRENTE;
            }
            
            case 2 -> {
                tipoConta = Tipo.POUPANÇA;
            }
            
            default -> {
                JOptionPane.showMessageDialog(null, "Digite uma opção válida");
                return;
            }
        }
        
        Conta conta = new Conta(idGerente, idCliente, valor, tipoConta);
        conta = contconta.inserir(conta);
        JOptionPane.showMessageDialog(null, conta.toString());
    }
    
    public static void buscar() throws SQLException, ClassNotFoundException {
        while (true) {
            int opc;
            String msg = """
                         O que você deseja pesquisa?:
                          1 - Id do Cliente
                          2 - Id do Gerente 
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
        ControllerConta contconta = new ControllerConta();
        
        try {
            id = Integer.parseInt(JOptionPane.showInputDialog("ID"));
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Por favor, digite um ID");
            return;
        }
        
        Conta contaId = contconta.buscarId(id);
        if (contaId == null) {
            JOptionPane.showMessageDialog(null,"Conta não encontrado.");
            return;
        } else JOptionPane.showMessageDialog(null,contaId.toString());
        
        while (true) {
            int opc;
            String msg = """
                         O que você deseja alterar?:
                          1 - Id do Gerente 
                          2 - Id do Cliente
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

            String lista = AlterarOpc(opc, id);
            
            if (lista.equals("")) {
                break;
            }     
            
            JOptionPane.showMessageDialog(null, lista);
        }
        
        contaId = contconta.buscarId(id);
        JOptionPane.showMessageDialog(null, contaId);
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
        
        ControllerConta contconta = new ControllerConta();
        Conta contaId = contconta.buscarId(id);
        if (contaId == null) {
            JOptionPane.showMessageDialog(null,"Conta não encontrado.");
            return;
        } else JOptionPane.showMessageDialog(null, contaId.toString());
        
        contconta.excluir(id);
    }
    
    public static String BuscarOpc(int opc) throws SQLException, ClassNotFoundException {
        int idCliente;
        int idGerente;
        int id;
        Tipo tipoConta;
        
        String lista = "";
        List<Conta> conta;
        ControllerConta contconta = new ControllerConta();
        
        switch (opc) {
            case 1 -> {
                try {
                    idCliente = Integer.parseInt(JOptionPane.showInputDialog("Id do Cliente"));
                } catch (HeadlessException | NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Digite um Id válido");
                    return lista;
                }

                Conta contaId = contconta.buscarIdCliente(idCliente);
                if (contaId == null) {
                    lista = "Conta não encontrado.";
                } else {
                    lista = contaId.toString();
                }
                break;
            }
            case 2 -> {
                try {
                    idGerente = Integer.parseInt(JOptionPane.showInputDialog("Id do Gerente"));
                } catch (HeadlessException | NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Digite um Id válido");
                    return lista;
                }

                Conta contaId = contconta.buscarIdCliente(idGerente);
                if (contaId == null) {
                    lista = "Conta não encontrado.";
                } else {
                    lista = contaId.toString();
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

                Conta contaId = contconta.buscarId(id);
                if (contaId == null) {
                    lista = "Conta não encontrado.";
                } else {
                    lista = contaId.toString();
                }
            }
            case 4 -> {
                try {
                    int tipo = Integer.parseInt(JOptionPane.showInputDialog("TIPO:" +
                        "1- Corrente" +
                        "2- Poupança"));
                    switch (tipo) {
                        case 1 -> {
                            tipoConta = Tipo.CORRENTE;
                        }
                        case 2 -> {
                            tipoConta = Tipo.POUPANÇA;
                        }
                        default -> {
                            JOptionPane.showMessageDialog(null, "Digite um tipo válido");
                            return lista;
                        }
                    }
                } catch (HeadlessException | NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Digite um tipo válido");
                    return lista;
                }

                conta = contconta.buscarTipo(tipoConta);

                if (conta.isEmpty()) {
                    lista = "Conta não encontrado.";
                } else {
                    for (Conta contaSaida : conta) {
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
    
    public static String AlterarOpc(int opc, int id) throws SQLException, ClassNotFoundException {
        int idCliente;
        int idGerente;
        Float valor;
        Tipo tipoConta;
        
        String lista = "";
        ControllerConta contconta = new ControllerConta();
        
        switch (opc) {
            case 1 -> {
                try {
                    idCliente = Integer.parseInt(JOptionPane.showInputDialog("Id do Cliente"));
                } catch (HeadlessException | NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Digite um Id válido");
                    return lista;
                }
                
                contconta.alterarIdClien(id, idCliente);
                break;
            }
            case 2 -> {
                try {
                    idGerente = Integer.parseInt(JOptionPane.showInputDialog("Id do Cliente"));
                } catch (HeadlessException | NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Digite um Id válido");
                    return lista;
                }
                
                contconta.alterarIdGeren(id, idGerente);
                break;
            }
            case 3 -> {
                try {
                    int tipo = Integer.parseInt(JOptionPane.showInputDialog("TIPO:" +
                        "1- Corrente" +
                        "2- Poupança"));
                    switch (tipo) {
                        case 1 -> {
                            tipoConta = Tipo.CORRENTE;
                        }
                        case 2 -> {
                            tipoConta = Tipo.POUPANÇA;
                        }
                        default -> {
                            JOptionPane.showMessageDialog(null, "Digite um tipo válido");
                            return lista;
                        }
                    }
                } catch (HeadlessException | NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Digite um tipo válido");
                    return lista;
                }
                
                contconta.alterarTipo(id, tipoConta);
                break;
            }
            case 4 -> {
                try {
                    valor = Float.parseFloat(JOptionPane.showInputDialog("Valor"));
                } catch (HeadlessException | NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Digite um valor válido");
                    return lista;
                }
                
                contconta.alterarValor(id, valor);
                break;
            }
            case 5 -> {
                try {
                    idCliente = Integer.parseInt(JOptionPane.showInputDialog("Id do Cliente"));
                    idGerente = Integer.parseInt(JOptionPane.showInputDialog("Id do Cliente"));
                    valor = Float.parseFloat(JOptionPane.showInputDialog("Valor"));
                    int tipo = Integer.parseInt(JOptionPane.showInputDialog("TIPO:" +
                        "1- Corrente" +
                        "2- Poupança"));
                    switch (tipo) {
                        case 1 -> {
                            tipoConta = Tipo.CORRENTE;
                        }
                        case 2 -> {
                            tipoConta = Tipo.POUPANÇA;
                        }
                        default -> {
                            JOptionPane.showMessageDialog(null, "Digite um tipo válido");
                            return lista;
                        }
                    }
                } catch (HeadlessException | NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Digite um Id válido");
                    return lista;
                }
                
                contconta.alterarTudo(id, idCliente, idGerente, valor, tipoConta);
                break;
            }
            case 6 -> {return lista;}
            default -> {
                JOptionPane.showMessageDialog(null,"Digite um valor válido"); 

            }
        }
        return lista;
    }

}
