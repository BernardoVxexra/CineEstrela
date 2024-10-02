
package controle;

/*Importando*/
import conexao.conexao;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.sql.*;

/*
Depois importar a classe conexao
*/
public class TelaLogin extends JFrame{
    
conexao con_cineestrela;
    
    JPasswordField senha;
    JLabel rotusuario,rotsenha,rottitulo,rotCadastro;
    JTextField usuario;
    JButton logar, sair,Cadastro;

    
    public TelaLogin() {
        con_cineestrela = new conexao();// inicialização do objeto
        con_cineestrela.conecta();// chama o método que conecta
        
        setTitle("Acesso de Login ao CineEstrela");
        Container tela = getContentPane();
        tela.setBackground(new Color(231,231,232));
        setLayout(null);
      
        //Adicionando um painel esquerdo para a logo do cinema
        JPanel painelEsquerdo = new JPanel();
        painelEsquerdo.setBounds(0, 0, 400, 500);
        painelEsquerdo.setBackground(new Color(8,2,39));
        tela.add(painelEsquerdo);   
        
        /*Icones CineEstrela */
       ImageIcon icone = new ImageIcon(getClass().getResource("/images/cinema.gif"));
       setIconImage(icone.getImage());
       
       /*ImageIcon EstrelaIcon = new ImageIcon (getClass().getResource("/images/CineEstrela2.png"));
       JLabel EstrelaLabel = new JLabel(EstrelaIcon);
       EstrelaLabel.setBounds(5, 5, 5, 5);
       painelEsquerdo.add(EstrelaLabel);*/
     
        
        /*JLabels */
        
        rottitulo = new JLabel("Bem vindo ao CineEstrela");
        rottitulo.setForeground(new Color(8,2,39));
        rottitulo.setFont(new Font("Arial", Font.BOLD, 24));
        
        rotusuario = new JLabel("Usuário: ");
        rotusuario.setForeground(new Color(8,2,39));
        rotsenha = new JLabel("Senha: ");
        rotsenha.setForeground(new Color(8,2,39));
        rotCadastro = new JLabel("Ainda não tem uma conta?");
        rotCadastro.setForeground(new Color(8,2,39));
        
        /*Set Bounds da JLabel*/
        rottitulo.setBounds(445, 20, 300, 100);
        rotusuario.setBounds(550, 130, 100, 20);
        rotsenha.setBounds(550, 190, 100, 20);
        rotCadastro.setBounds(430, 355,170,20);
        
        /*TextField e setBounds*/
        JTextField usuario = new JTextField();
        usuario.setBounds(470, 160, 200, 20);
        
        /*PasswordField e setBounds*/
        JPasswordField senha = new JPasswordField();
        senha.setBounds(470, 220, 200, 20);
        
        /*JButton e setBounds*/
        JButton logar = new JButton("Login");
        JButton cadastrar = new JButton("Cadastrar-se");
        cadastrar.setBounds(600, 355, 110, 20);
        cadastrar.setToolTipText("Clique aqui para realizar seu cadastro");
        cadastrar.setBackground(new Color(8,2,39));
        cadastrar.setForeground(Color.WHITE);
        
        logar.setBounds(495, 280, 150, 30);
        logar.setToolTipText("Clique aqui para fazer Login");
        logar.setBackground(new Color(8,2,39));
        logar.setForeground(Color.WHITE);
        
        /*JButton e setBounds*/
        JButton sair = new JButton("Sair");
        sair.setBounds(725, 420, 62, 40);
        sair.setToolTipText("Finalizar e sair");
        sair.setBackground(new Color(8,2,39));
        sair.setForeground(Color.WHITE);

       
        
         /*Adicionando funcionalidades ActionListener*/
        logar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {              
                    String pesquisa = "select * from funcionario where Usuario like '" + usuario.getText() + "' && Senha = " + senha.getText() + "";
                    con_cineestrela.executaSQL(pesquisa);

                    if (con_cineestrela.resultset.first())
                     {
                       menu tm = new menu();
                       tm.setVisible(true);
                       tm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                       dispose();
                    } 
                    else 
                    {
                        JOptionPane.showMessageDialog(null, "\n Usuário não cadastro!!! ", "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                        con_cineestrela.desconecta();
                        System.exit(0);
                    }
                
                 
                } catch (SQLException errosql) {
                    JOptionPane.showMessageDialog(null, "\n Os dados Digitados não foram localizados!! :\n" + errosql, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });
        
       
        
        sair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int opcao;
                opcao = JOptionPane.showConfirmDialog(null,
                        "Deseja fechar a janela?",
                        "Fechar", JOptionPane.YES_NO_OPTION);
                if (opcao == JOptionPane.YES_NO_OPTION) {
                    System.exit(0);
                }
            }
        });
             /*Futura função Cadastrar*/
   
        
        
         /*Atalhos*/
        logar.setMnemonic(KeyEvent.VK_L);
        sair.setMnemonic(KeyEvent.VK_S);
        cadastrar.setMnemonic(KeyEvent.VK_C);
        
        setSize(800, 500);
        setVisible(true);
        setLocationRelativeTo(null);
        
        /*Telas Add´s*/
        tela.add(senha);
        tela.add(rotusuario);
        tela.add(rotsenha);
        tela.add(rottitulo);
        tela.add(rotCadastro);
        tela.add(usuario);
        tela.add(logar);
        tela.add(cadastrar);
        tela.add(sair);
        
    }
 public static void main(String[] args) {
        TelaLogin app = new TelaLogin();
        app.setVisible(true);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}



    
     
 
  
