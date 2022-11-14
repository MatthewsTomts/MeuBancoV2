package meubancov2.views;

import java.awt.HeadlessException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import javax.swing.JOptionPane;
import meubancov2.controllers.ControllerContas;
import meubancov2.controllers.ControllerUsuarios;
import meubancov2.models.beans.UsuTipo;
import meubancov2.models.beans.Usuarios;

/**
 *
 * @author scar
 */
public class ManterUsuarios {
    
    public static void validar() throws SQLException, ClassNotFoundException {
        String nulo = "Por favor, digite o login e senha";
        String login = JOptionPane.showInputDialog("LOGIN");
        if (login == null) {JOptionPane.showMessageDialog(null, nulo); return;}
        String senha = JOptionPane.showInputDialog("SENHA");
         if (senha == null) {JOptionPane.showMessageDialog(null, nulo); return;}

        ControllerUsuarios contusu = new ControllerUsuarios();
        
        switch (contusu.validar(login, senha)) {
            case "adm" -> menuAdm();
            case "geren" -> menuUsu();
            default -> JOptionPane.showMessageDialog(null,"Usuário Inválido");
        }
    }
    
    public static void menuUsu() throws SQLException, ClassNotFoundException {
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
                case 1 -> ManterClientes.menu();
                case 2 -> ManterContas.menu();
                case 3 -> {opc = 0;}
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
                        O que gostaria de fazer em relação aos usuarios:
                            1 - Inserir
                            2 - Buscar
                            3 - Alterar
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
                case 2 -> buscar();
                case 3 -> alterar();
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
        ControllerUsuarios contusu = new ControllerUsuarios();
        String[] tipos = {"ADMINISTRADOR", "GERENTE"};
        
        String nome = JOptionPane.showInputDialog("NOME");
        if (nome == null) {JOptionPane.showMessageDialog(null, nulo); return;}

        String login = JOptionPane.showInputDialog("LOGIN");
        if (login == null) {JOptionPane.showMessageDialog(null, nulo); return;}

        String senha = JOptionPane.showInputDialog("SENHA");
        if (senha == null) {JOptionPane.showMessageDialog(null, nulo); return;}
        
        String tipo = (String) JOptionPane.showInputDialog(
                null,
                "Tipo:",
                "Escolha um tipo",
                JOptionPane.QUESTION_MESSAGE,
                null,
                tipos,
                tipos[1]);
        if (tipo == null) {JOptionPane.showMessageDialog(null, nulo); return;}
        
        Usuarios usu = new Usuarios(nome, login, senha, UsuTipo.valueOf(tipo));
        try {
            usu = contusu.inserir(usu);
        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "Login já utilizado, " +
                    "por favor escolha outro");
            return;
        }
        JOptionPane.showMessageDialog(null, usu.toString());
    }
    
    public static void buscar() throws SQLException, ClassNotFoundException {
        int opc;
        int id;
        String nome;
        String login;
        String tipo;
        String[] tipos = {"ADMINISTRADOR", "GERENTE"};
        ControllerUsuarios contusu = new ControllerUsuarios();
        List<Usuarios> usus;
        String lista = "";
        
        while (true) {
            boolean stop = false;
            String msg = """
                         O que você deseja pesquisa?:
                          1 - Id 
                          2 - Nome 
                          3 - Login
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

            String nulo = "Digite um valor, por favor";
            switch (opc) {
                case 1 -> {
                    try {
                        id = Integer.parseInt(JOptionPane.showInputDialog("ID"));
                    } catch (HeadlessException | NumberFormatException e) {
                        JOptionPane.showMessageDialog(null,"Por favor, digite um ID");
                        return;
                    }
                    Usuarios usuId = contusu.buscarId(id);
                    if (usuId == null) {
                        lista = "Usuario não encontrado.";
                    } else {
                        lista = usuId.toString();
                    }
                }
                case 2 -> {
                    nome = JOptionPane.showInputDialog("NOME");
                    if (nome == null) {JOptionPane.showMessageDialog(null, nulo); continue;}
                    usus = contusu.buscarNome(nome);
                    if (usus.isEmpty()) {
                        lista = "Usuario não encontrado.";
                    } else {
                        for (Usuarios usuSaida : usus) {
                            lista = lista + "\n" + usuSaida.toString();
                        }
                    }
                }
                case 3 -> {
                    login = JOptionPane.showInputDialog("LOGIN");
                    if (login == null) {JOptionPane.showMessageDialog(null, nulo); continue;}
                    usus = contusu.buscarLogin(login);
                    if (usus.isEmpty()) {
                        lista = "Usuario não encontrado.";
                    } else {
                        for (Usuarios usuSaida : usus) {
                            lista = lista + usuSaida.toString() + '\n';
                        }
                    }
                }
                case 4 -> {
                    tipo = (String) JOptionPane.showInputDialog(
                        null,
                        "Tipo:",
                        "Escolha um tipo",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        tipos,
                        tipos[1]);
                    if (tipo == null) {JOptionPane.showMessageDialog(null, nulo); continue;}
                    usus = contusu.buscarTipo(UsuTipo.valueOf(tipo));
                    if (usus.isEmpty()) {
                        lista = "Usuario não encontrado.";
                    } else {
                        for (Usuarios usuSaida : usus) {
                            lista = lista + usuSaida.toString() + '\n';
                        }
                    }
                }
                case 5 -> stop = true;
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
    
    public static void alterar() throws SQLException, ClassNotFoundException {
        int id;
        String nome;
        String login;
        String senha ;
        String tipo;
        String[] tipos = {"ADMINISTRADOR", "GERENTE"};
        String nulo = "Por favor, digite todos os dados";
        ControllerUsuarios contusu = new ControllerUsuarios();
        
        try {
            id = Integer.parseInt(JOptionPane.showInputDialog("ID"));
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Por favor, digite um ID");
            return;
        }
        
        Usuarios usuId = contusu.buscarId(id);
        if (usuId == null) {
            JOptionPane.showMessageDialog(null,"Usuario não encontrado.");
            return;
        } else JOptionPane.showMessageDialog(null,usuId.toString());

                
        int opc;
        while (true) {
            boolean stop = false;
            String msg = """
                         O que você deseja alterar?:
                          1 - Nome 
                          2 - Login 
                          3 - Senha
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

            switch (opc) {
                case 1 -> {
                    nome = JOptionPane.showInputDialog("NOME");
                    if (nome == null) {JOptionPane.showMessageDialog(null, nulo); continue;}
                    
                    contusu.alterarNome(id, nome);
                }
                case 2 -> {
                    login = JOptionPane.showInputDialog("LOGIN");
                    if (login == null) {JOptionPane.showMessageDialog(null, nulo); continue;}
                    
                    contusu.alterarLogin(id, login);
                }
                case 3 -> {
                    senha = JOptionPane.showInputDialog("SENHA");
                    if (senha == null) {JOptionPane.showMessageDialog(null, nulo); continue;}
                    
                    contusu.alterarSenha(id, senha);
                }
                case 4 -> {
                    tipo = (String) JOptionPane.showInputDialog(
                        null,
                        "Tipo:",
                        "Escolha um tipo",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        tipos,
                        tipos[1]);
                    if (tipo == null) {JOptionPane.showMessageDialog(null, nulo); continue;}
                    
                    contusu.alterarTipo(id, UsuTipo.valueOf(tipo));
                }
                case 5 -> {
                    nome = JOptionPane.showInputDialog("NOME");
                    if (nome == null) {JOptionPane.showMessageDialog(null, nulo); continue;}
                    login = JOptionPane.showInputDialog("LOGIN");
                    if (login == null) {JOptionPane.showMessageDialog(null, nulo); continue;}
                    senha = JOptionPane.showInputDialog("SENHA");
                    if (senha == null) {JOptionPane.showMessageDialog(null, nulo); continue;}
                    tipo = (String) JOptionPane.showInputDialog(
                        null,
                        "Tipo:",
                        "Escolha um tipo",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        tipos,
                        tipos[1]);                    
                    if (tipo == null) {JOptionPane.showMessageDialog(null, nulo); continue;}
                    
                    contusu.alterarTudo(id, nome, login, senha, UsuTipo.valueOf(tipo));
                }
                case 6 -> stop = true;
            }
            
            if (stop) {
                break;
            }        
            
            usuId = contusu.buscarId(id);
            JOptionPane.showMessageDialog(null,usuId.toString());
        }
    }
    
    public static void listar() throws SQLException, ClassNotFoundException {
        String lista = "";
        ControllerUsuarios contusu = new ControllerUsuarios();
        List<Usuarios> listaUsu = contusu.listar();
        for (Usuarios usuSaida : listaUsu) {
            lista = lista + usuSaida.toString() + '\n';
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
        
        ControllerUsuarios contusu = new ControllerUsuarios();
        Usuarios usuId = contusu.buscarId(id);
        if (usuId == null) {
            JOptionPane.showMessageDialog(null,"Usuario não encontrado.");
            return;
        } else JOptionPane.showMessageDialog(null,usuId.toString());
        
        Boolean result = false;
        try {
            contusu.excluir(id);
        } catch (SQLIntegrityConstraintViolationException e) {
            int alt = JOptionPane.showConfirmDialog(null,"Não é possível excluir" +
                    " este usuario, pois este ainda gerencia contas,\n" +
                    " deseja alterar o gerente responsável por estas contas?");
            if (alt == JOptionPane.YES_OPTION) result = AlterarUsuario(id);
            if (result) {
                contusu.excluir(id);
            } else {
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Usuario Exluído.");
    }
    
    public static Boolean AlterarUsuario(int idAntigo) throws SQLException, ClassNotFoundException {
        int idNovo;
        try {
            idNovo = Integer.parseInt(JOptionPane.showInputDialog("Id do novo Usuario, responsável"));
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Por favor, digite um ID");
            return false;
        }
        
        ControllerUsuarios contgeren = new ControllerUsuarios();
        Usuarios gerenId = contgeren.buscarId(idNovo);
        if (gerenId == null) {
            JOptionPane.showMessageDialog(null,"Usuario não encontrado.");
            return false;
        } else JOptionPane.showMessageDialog(null,gerenId.toString());
        
        ControllerContas contconta = new ControllerContas();
        contconta.alterarUsuario(idNovo, idAntigo);
        // Corrigir Buscar e adicionar aqui correntamente
        contconta.listar();
        return true;
    }
}
