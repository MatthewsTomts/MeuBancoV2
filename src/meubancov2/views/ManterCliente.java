package meubancov2.views;

import java.awt.HeadlessException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import javax.swing.JOptionPane;
import meubancov2.controllers.ControllerCliente;
import meubancov2.controllers.ControllerConta;
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
        ControllerCliente contclien = new ControllerCliente();
        
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
        clien = contclien.inserir(clien);
        JOptionPane.showMessageDialog(null, clien.toString());
    }
    
    public static void buscar() throws SQLException, ClassNotFoundException {
        while (true) {
            int opc;
            String msg = """
                         O que você deseja pesquisa?:
                          1 - Id 
                          2 - Nome 
                          3 - RG
                          4 - CPF
                          5 - Email
                          6 - Telefone
                          7 - Voltar
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
        ControllerCliente contclien = new ControllerCliente();
        
        try {
            id = Integer.parseInt(JOptionPane.showInputDialog("ID"));
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Por favor, digite um ID");
            return;
        }
        
        Cliente clienId = contclien.buscarId(id);
        if (clienId == null) {
            JOptionPane.showMessageDialog(null,"Cliente não encontrado.");
            return;
        } else JOptionPane.showMessageDialog(null,clienId.toString());
        
        while (true) {
            int opc;
            String msg = """
                         O que você deseja alterar?:
                          1 - Nome 
                          2 - RG
                          3 - CPF
                          4 - Email
                          5 - Telefone
                          6 - Tudo
                          7 - Voltar
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
        
        clienId = contclien.buscarId(id);
        JOptionPane.showMessageDialog(null,clienId);
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
        
        ControllerCliente contclien = new ControllerCliente();
        Cliente clienId = contclien.buscarId(id);
        if (clienId == null) {
            JOptionPane.showMessageDialog(null,"Cliente não encontrado.");
            return;
        } else JOptionPane.showMessageDialog(null, clienId.toString());
        
        try {
            contclien.excluir(id);
        } catch (SQLIntegrityConstraintViolationException e) {
            int alt = JOptionPane.showConfirmDialog(null,"Não é possível excluir" +
                    " este Cliente, pois este ainda tem contas ativas,\n" +
                    " deseja excluir estas contas?");
            ControllerConta contconta = new ControllerConta();
            if (alt == JOptionPane.YES_OPTION) {
                contconta.excluirContas(id);
                contclien.excluir(id);
            } else {
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Cliente Exluído.");
    }
    
    public static String BuscarOpc(int opc) throws SQLException, ClassNotFoundException {
        int id;
        String nome;
        String rg;
        String cpf;
        String email;
        String telefone;
        
        String lista = "";
        List<Cliente> clien;
        String nulo = "Digite um valor, por favor";
        ControllerCliente contclien = new ControllerCliente();
        
        switch (opc) {
            case 1 -> {
                try {
                    id = Integer.parseInt(JOptionPane.showInputDialog("ID"));
                } catch (HeadlessException | NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,"Por favor, digite um ID");
                    return lista;
                }

                Cliente clienId = contclien.buscarId(id);
                if (clienId == null) {
                    lista = "Cliente não encontrado.";
                } else {
                    lista = clienId.toString();
                }
            }
            case 2 -> {
                nome = JOptionPane.showInputDialog("NOME");
                if (nome == null) {JOptionPane.showMessageDialog(null, nulo); return lista;}

                clien = contclien.buscarNome(nome);

                if (clien.isEmpty()) {
                    lista = "Cliente não encontrado.";
                } else {
                    for (Cliente clienSaida : clien) {
                        lista = lista + "\n" + clienSaida.toString();
                    }
                }
                break;
            }
            case 3 -> {
                rg = JOptionPane.showInputDialog("RG");
                if (rg == null) {JOptionPane.showMessageDialog(null, nulo); return lista;}

                Cliente clienRg = contclien.buscarRg(rg);
                if (clienRg == null) {
                    lista = "Cliente não encontrado.";
                } else {
                    lista = clienRg.toString();
                }
                break;
            }
            case 4 -> {
                cpf = JOptionPane.showInputDialog("CPF");
                if (cpf == null) {JOptionPane.showMessageDialog(null, nulo); return lista;}

                Cliente clienCpf = contclien.buscarCpf(cpf);
                if (clienCpf == null) {
                    lista = "Cliente não encontrado.";
                } else {
                    lista = clienCpf.toString();
                }
                break;
            }
            case 5 -> {
                email = JOptionPane.showInputDialog("EMAIL");
                if (email == null) {JOptionPane.showMessageDialog(null, nulo); return lista;}

                clien = contclien.buscarEmail(email);

                if (clien.isEmpty()) {
                    lista = "Cliente não encontrado.";
                } else {
                    for (Cliente clienSaida : clien) {
                        lista = lista + clienSaida.toString() + '\n';
                    }
                }
                break;
            }
            case 6 -> {
                telefone = JOptionPane.showInputDialog("TELEFONE");
                if (telefone == null) {JOptionPane.showMessageDialog(null, nulo); return lista;}

                Cliente clienTelefone = contclien.buscarTelefone(telefone);
                if (clienTelefone == null) {
                    lista = "Cliente não encontrado.";
                } else {
                    lista = clienTelefone.toString();
                }
                break;
            }
            case 7 -> {return lista;}
            default -> {
                JOptionPane.showMessageDialog(null,"Digite um valor válido"); 

            }
        }
        return lista;
    }
    
    public static String AlterarOpc(int opc, int id) throws SQLException, ClassNotFoundException {
        String nome;
        String rg;
        String cpf;
        String email;
        String telefone;
        
        String lista = "";
        String nulo = "Digite um valor, por favor";
        ControllerCliente contclien = new ControllerCliente();
        
        switch (opc) {
            case 1 -> {
                nome = JOptionPane.showInputDialog("NOME");
                if (nome == null) {JOptionPane.showMessageDialog(null, nulo); return lista;}

                contclien.alterarNome(id, nome);
                break;
            }
            case 2 -> {
                rg = JOptionPane.showInputDialog("RG");
                if (rg == null) {JOptionPane.showMessageDialog(null, nulo); return lista;}

                contclien.alterarRg(id, rg);
                break;
            }
            case 3 -> {
                cpf = JOptionPane.showInputDialog("CPF");
                if (cpf == null) {JOptionPane.showMessageDialog(null, nulo); return lista;}

                contclien.alterarCpf(id, cpf);
                break;
            }
            case 4 -> {
                email = JOptionPane.showInputDialog("EMAIL");
                if (email == null) {JOptionPane.showMessageDialog(null, nulo); return lista;}

                contclien.alterarEmail(id, email);
                break;
            }
            case 5 -> {
                telefone = JOptionPane.showInputDialog("TELEFONE");
                if (telefone == null) {JOptionPane.showMessageDialog(null, nulo); return lista;}

                contclien.alterarTelefone(id, telefone);
                break;
            }
            case 6 -> {
                nome = JOptionPane.showInputDialog("NOME");
                if (nome == null) {JOptionPane.showMessageDialog(null, nulo); return lista;}

                rg = JOptionPane.showInputDialog("RG");
                if (rg == null) {JOptionPane.showMessageDialog(null, nulo); return lista;}

                cpf = JOptionPane.showInputDialog("CPF");
                if (cpf == null) {JOptionPane.showMessageDialog(null, nulo); return lista;}

                email = JOptionPane.showInputDialog("EMAIL");
                if (email == null) {JOptionPane.showMessageDialog(null, nulo); return lista;}

                telefone = JOptionPane.showInputDialog("TELEFONE");
                if (telefone == null) {JOptionPane.showMessageDialog(null, nulo); return lista;}

                contclien.alterarTudo(id, nome, rg, cpf, email, telefone);
                break;
            }
            case 7 -> {return lista;}
            default -> {
                JOptionPane.showMessageDialog(null,"Digite um valor válido"); 

            }
        }
        return lista;
    }
}
