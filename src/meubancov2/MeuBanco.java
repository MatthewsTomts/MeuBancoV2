package meubancov2;

import java.io.IOException;
import meubancov2.views.ManterUsuarios;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author scar
 */
public class MeuBanco {   
    
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        /*Connection teste = new Conexao().getConnection();
        try {
            PreparedStatement stmt = teste.prepareStatement("use MeuBanco");
            int rs = stmt.executeUpdate();
        
        } catch (SQLSyntaxErrorException e) {
            String cmd = "mysql MeuBanco < ~/Documents/Bit/MeuBanco/meuBanco.sql";
            Process teste1 =  Runtime.getRuntime().exec(cmd);
            JOptionPane.showMessageDialog(null,  teste1);
            PreparedStatement stmt = teste.prepareStatement("use MeuBanco");
            int rs = stmt.executeUpdate();
        }*/
        
        int opc = 0;
        while (opc != 4) {
            String msg = "Usuário:\n 1 - Entrar\n 2 - Sair";
            String result = JOptionPane.showInputDialog(msg);

            try {
                opc = Integer.parseInt(result);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,"Por favor, escolha uma opção");
                continue;
            }

            switch (opc) {
                case 1 -> ManterUsuarios.validar();
                case 2 -> {
                    int sair = JOptionPane.showConfirmDialog(null,"Deseja Sair?");
                    if (sair == JOptionPane.YES_OPTION) opc = 4;
                }
                default -> {
                    JOptionPane.showMessageDialog(null,"Opção inválido");
                    opc = 1;
                }
            }
        }
    }
    
    public static void loginAdm() throws SQLException, ClassNotFoundException {
        String nulo = "Por favor, digite o login e senha";
        String login = JOptionPane.showInputDialog("LOGIN");
        if (login == null) {JOptionPane.showMessageDialog(null, nulo); return;}
        String senha = JOptionPane.showInputDialog("SENHA");
        if (senha == null) {JOptionPane.showMessageDialog(null, nulo); return;}

        if (login.equals("t") && senha.equals("t")) {
            ManterUsuarios.menuAdm();
        } else {
            JOptionPane.showMessageDialog(null,"Usuario Inválido");            
        }
    }
}
