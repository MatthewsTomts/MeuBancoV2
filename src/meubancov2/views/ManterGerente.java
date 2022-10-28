package meubancov2.views;

import java.awt.HeadlessException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import javax.swing.JOptionPane;
import meubancov2.controllers.ControllerConta;
import meubancov2.controllers.ControllerGerente;
import meubancov2.models.beans.Gerente;

/**
 *
 * @author scar
 */
public class ManterGerente {
    
    public static void validar() throws SQLException, ClassNotFoundException {
        String nulo = "Por favor, digite o login e senha";
        String login = JOptionPane.showInputDialog("LOGIN");
        if (login == null) {JOptionPane.showMessageDialog(null, nulo); return;}
        String senha = JOptionPane.showInputDialog("SENHA");
         if (senha == null) {JOptionPane.showMessageDialog(null, nulo); return;}

        ControllerGerente contgeren = new ControllerGerente();
        
        if (contgeren.validar(login, senha)) {
            menuGeren();
        } else {
            JOptionPane.showMessageDialog(null,"Usuário Inválido");
        }
    }
    
    public static void menuGeren() throws SQLException, ClassNotFoundException {
        int opc = 4;
        while (opc != 0) {
            String msg = "Opções:\n 1 - Cliente \n 2 - Conta \n 3 - Voltar";
            String result = JOptionPane.showInputDialog(msg);

            try {
                opc = Integer.parseInt(result);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,"Por favor, escolha uma opção");
                continue;
            }

            switch (opc) {
                case 1 -> ManterCliente.menu();
                case 2 -> ManterConta.menu();
                case 3 -> {System.out.println("Voltando..."); opc = 0;}
                default -> {
                    JOptionPane.showMessageDialog(null,"Opção inválido");
                    opc = 1;
                }
            }
        }
    }
    
    public static void menuAdm() throws SQLException, ClassNotFoundException {
        int opc = 0;
        while (opc != 6) {
            String msg = """
                        O que gostaria de fazer em relação aos gerentes:
                            1 - Inserir
                            2 - Alterar
                            3 - Buscar
                            4 - Listar 
                            5 - Excluir
                            6 - Voltar""";
            
            String result = JOptionPane.showInputDialog(msg);

            try {
                opc = Integer.parseInt(result);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,"Por favor, escolha uma opção");
                continue;
            }
        
            switch (opc) {
                case 1 -> inserir();
                case 2 -> alterar();
                case 3 -> buscar();
                case 4 -> listar();
                case 5 -> excluir();
                case 6 -> System.out.println("Voltando...");
                default -> {
                    JOptionPane.showMessageDialog(null,"Opção inválido");
                    opc = 1;
                }
            }
        }
    }
    
    public static void inserir() throws SQLException, ClassNotFoundException {
        String nulo = "Por favor, digite todos os dados";
        String nome = JOptionPane.showInputDialog("NOME");
        if (nome == null) {JOptionPane.showMessageDialog(null, nulo); return;}

        String login = JOptionPane.showInputDialog("LOGIN");
        if (login == null) {JOptionPane.showMessageDialog(null, nulo); return;}

        String senha = JOptionPane.showInputDialog("SENHA");
        if (senha == null) {JOptionPane.showMessageDialog(null, nulo); return;}
        
        Gerente geren = new Gerente(nome, login, senha);
        ControllerGerente contgeren = new ControllerGerente();
        try {
            geren = contgeren.inserir(geren);
        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "Login já utilizado, " +
                    "por favor escolha outro");
            return;
        }
        JOptionPane.showMessageDialog(null,geren.toString());
    }
    
    public static void alterar() throws SQLException, ClassNotFoundException {
        int id;
        String nome;
        String login;
        String senha ;
        String nulo = "Por favor, digite todos os dados";
        ControllerGerente contgeren = new ControllerGerente();
        
        try {
            id = Integer.parseInt(JOptionPane.showInputDialog("ID"));
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Por favor, digite um ID");
            return;
        }
        Gerente gerenId = contgeren.buscarId(id);
        if (gerenId == null) {
            JOptionPane.showMessageDialog(null,"Gerente não encontrado.");
            return;
        } else JOptionPane.showMessageDialog(null,gerenId.toString());

                
        int opc;
        while (true) {
            boolean stop = false;
            String msg = """
                         O que voc\u00ea deseja alterar?:
                          1 - Nome 
                          2 - Login 
                          3 - Senha
                          4 - Tudo
                          5 - Voltar
                         """;
            String result = JOptionPane.showInputDialog(msg);

            try {
                opc = Integer.parseInt(result);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,"Por favor, escolha uma opção");
                continue;
            }

            switch (opc) {
                case 1 -> {
                    nome = JOptionPane.showInputDialog("NOME");
                    if (nome == null) {JOptionPane.showMessageDialog(null, nulo); continue;}
                    
                    contgeren.alterarNome(id, nome);
                }
                case 2 -> {
                    login = JOptionPane.showInputDialog("LOGIN");
                    if (login == null) {JOptionPane.showMessageDialog(null, nulo); continue;}
                    
                    contgeren.alterarLogin(id, login);
                }
                case 3 -> {
                    senha = JOptionPane.showInputDialog("SENHA");
                    if (senha == null) {JOptionPane.showMessageDialog(null, nulo); continue;}
                    
                    contgeren.alterarSenha(id, senha);
                }
                case 4 -> {
                    nome = JOptionPane.showInputDialog("NOME");
                    if (nome == null) {JOptionPane.showMessageDialog(null, nulo); continue;}
                    login = JOptionPane.showInputDialog("LOGIN");
                    if (login == null) {JOptionPane.showMessageDialog(null, nulo); continue;}
                    senha = JOptionPane.showInputDialog("SENHA");
                    if (senha == null) {JOptionPane.showMessageDialog(null, nulo); continue;}
                    
                    contgeren.alterarTudo(id, nome, login, senha);
                }
                case 5 -> stop = true;
            }
            
            if (stop) {
                break;
            }        
            
            gerenId = contgeren.buscarId(id);
            JOptionPane.showMessageDialog(null,gerenId.toString());
        }
    }
    
    public static void buscar() throws SQLException, ClassNotFoundException {
        int opc;
        int id;
        String nome;
        String login;
        ControllerGerente contgeren = new ControllerGerente();
        List<Gerente> geren;
        String lista = "";
        
        while (true) {
            boolean stop = false;
            String msg = """
                         O que voc\u00ea deseja pesquisa?:
                          1 - Id 
                          2 - Nome 
                          3 - Login
                          4 - Voltar
                         """;
            String result = JOptionPane.showInputDialog(msg);

            try {
                opc = Integer.parseInt(result);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,"Por favor, escolha uma opção");
                continue;
            }

            String nulo = "Digite um valor, por favor";
            switch (opc) {
                case 1 -> {
                    try {
                        id = Integer.parseInt(JOptionPane.showInputDialog("ID"));
                    } catch (HeadlessException | NumberFormatException e) {
                        JOptionPane.showMessageDialog(null,"Por favor, digite um ID");
                        return;
                    }
                    Gerente gerenId = contgeren.buscarId(id);
                    if (gerenId == null) {
                        lista = "Gerente não encontrado.";
                    } else {
                        lista = gerenId.toString();
                    }
                }
                case 2 -> {
                    nome = JOptionPane.showInputDialog("NOME");
                    if (nome == null) {JOptionPane.showMessageDialog(null, nulo); continue;}
                    geren = contgeren.buscarNome(nome);
                    if (geren.isEmpty()) {
                        lista = "Gerente não encontrado.";
                    } else {
                        for (Gerente gerenSaida : geren) {
                            lista = lista + "\n" + gerenSaida.toString();
                        }
                    }
                }
                case 3 -> {
                    login = JOptionPane.showInputDialog("LOGIN");
                    if (login == null) {JOptionPane.showMessageDialog(null, nulo); continue;}
                    geren = contgeren.buscarLogin(login);
                    if (geren.isEmpty()) {
                        lista = "Gerente não encontrado.";
                    } else {
                        for (Gerente gerenSaida : geren) {
                            lista = lista + gerenSaida.toString() + '\n';
                        }
                    }
                }
                case 4 -> stop = true;
                default -> {
                    JOptionPane.showMessageDialog(null,"Digite um valor válido"); 
                    continue;}
            }
            
            if (stop) {
                break;
            }     
            
            JOptionPane.showMessageDialog(null,lista);
            lista = "";
        }
    }
    
    public static void listar() throws SQLException, ClassNotFoundException {
        String lista = "";
        ControllerGerente contgeren = new ControllerGerente();
        List<Gerente> listaGeren = contgeren.listar();
        for (Gerente gerenSaida : listaGeren) {
            lista = lista + gerenSaida.toString() + '\n';
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
        
        ControllerGerente contgeren = new ControllerGerente();
        Gerente gerenId = contgeren.buscarId(id);
        if (gerenId == null) {
            JOptionPane.showMessageDialog(null,"Gerente não encontrado.");
            return;
        } else JOptionPane.showMessageDialog(null,gerenId.toString());
        
        Gerente geren = new Gerente(id);
        Boolean result = false;
        try {
            contgeren.excluir(geren);
        } catch (SQLIntegrityConstraintViolationException e) {
            int alt = JOptionPane.showConfirmDialog(null,"Não é possível excluir" +
                    " este gerente, pois este ainda gerencia contas,\n" +
                    " deseja alterar o gerente responsável por estas contas?");
            if (alt == JOptionPane.YES_OPTION) result = AlterarGerente(geren.getId());
            if (result) {
                contgeren.excluir(geren);
                JOptionPane.showMessageDialog(null,geren.toString());
            }
        }
    }
    
    public static Boolean AlterarGerente(int idAntigo) throws SQLException, ClassNotFoundException {
        int idNovo;
        try {
            idNovo = Integer.parseInt(JOptionPane.showInputDialog("Id do novo Gerente"));
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Por favor, digite um ID");
            return false;
        }
        
        ControllerGerente contgeren = new ControllerGerente();
        Gerente gerenId = contgeren.buscarId(idNovo);
        if (gerenId == null) {
            JOptionPane.showMessageDialog(null,"Gerente não encontrado.");
            return false;
        } else JOptionPane.showMessageDialog(null,gerenId.toString());
        
        ControllerConta contconta = new ControllerConta();
        contconta.alterarGerente(idNovo, idAntigo);
        // Corrigir Buscar e adicionar aqui correntamente
        contconta.listar();
        return true;
    }
}
