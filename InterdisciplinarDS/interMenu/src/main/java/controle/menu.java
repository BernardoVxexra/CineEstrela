
package controle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class menu extends JFrame{

    // j intens

JMenuBar barra;
JMenu opcoes,sobre,sair;
JMenuItem Mcliente,Mfilme,Mfuncionario, osair, popsair,osobre,popsobre;
Sobre jasobre;
cliente clientejanela;
filme Filmejanela;
funcionario Funcionáriojanela;

JPopupMenu pop;

public menu(){

setTitle("CineEstrela");
Container tela = getContentPane(); 
setLayout(null); 

//definido o menu

barra = new JMenuBar();
setJMenuBar(barra); 
opcoes = new JMenu("Tabelas");
sobre= new JMenu("Sobre");
sair = new JMenu("Sair");

//adcionando ao menu

barra.add(opcoes); 
barra.add(sobre); 
barra.add(sair); 

//definido as opções

Mcliente = new JMenuItem("Tabela Cliente");
Mfilme = new JMenuItem("Tabela Filme");
Mfuncionario = new JMenuItem("Tabela Funcionário");
osair = new JMenuItem("Sair");
osobre = new JMenuItem("Sobre");

//adcionando as opções no menu

opcoes.add(Mcliente);
opcoes.add(Mfilme);
opcoes.add(Mfuncionario);
sobre.add(osobre);
sair.add(osair);



 //fazendo o menu funcionar
        
        Tratador tratmenu = new Tratador();
        
        pop = new JPopupMenu();
        popsair = new JMenuItem("Sair");
        popsobre = new JMenuItem("Sobre");
        popsair.addActionListener(tratmenu);
        popsobre.addActionListener(tratmenu);
        
        pop.add(popsair);
        pop.add(popsobre);

        tela.addMouseListener(
            new MouseAdapter(){
                public void mouseReleased(MouseEvent clicar){
                    if(clicar.isPopupTrigger())
                        pop.show(clicar.getComponent(),clicar.getX(),clicar.getY()); 
                }
            }
        );

         //opção sobre

        popsobre.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent popsobre) {
                    jasobre = new Sobre(null, "Sobre", true);
                    jasobre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }
            }
        );

//opção Mcliente

Mcliente.addActionListener((ActionEvent e) -> {
    clientejanela = new cliente(null,"cliente",true);
    clientejanela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    clientejanela.setVisible(true);
  });

//opção Mfilme
  
Mfilme.addActionListener((ActionEvent e) -> {
    Filmejanela = new filme(null,"Filme",true);
    Filmejanela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    Filmejanela.setVisible(true);
  });

  //opção Mfuncionario

Mfuncionario.addActionListener((ActionEvent e) -> {
    Funcionáriojanela = new funcionario(null,"Funcionário",true);
    Funcionáriojanela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    Funcionáriojanela.setVisible(true);
  });

  //opção osair

        osair.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent sair) {
                    int status = JOptionPane.showConfirmDialog(null, "Deseja fechar o programa?","Sair",JOptionPane.YES_NO_OPTION);
                    if(status == JOptionPane.YES_NO_OPTION) {
                        System.exit(0);
                    }
                }
            }
        );
        
//opção osobre

       osobre.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent sobre) {
                   jasobre = new Sobre(null, "Sobre", true);
                   jasobre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                   jasobre.setVisible(true);
                }
            }
        );




setSize(300, 350);
setVisible(true);
setLocationRelativeTo(null);
}

// função sobre nós

 public class Sobre extends JDialog {
    JButton voltar;
    JLabel  Nome1, Sala,Nome2,Nome3;
 
   private Sobre(Frame owner, String title,boolean modal) {
        super(owner,title,modal);
        Container tela1 = getContentPane();
        tela1.setLayout(null);
        
        Nome1 = new JLabel("Nome: Bernardo Vieira Costa");
        Nome2 = new JLabel("Nome: David Romero Garcia"); 
        Nome3 = new JLabel("Nome: Gustavo e Souza Morais"); 
        Sala = new JLabel("2° Ds/Ams");
        voltar = new JButton("Voltar");
        
        voltar.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent voltar){
                    dispose();
                }
            }
        );
        
        Nome1.setBounds(50,20,250,20);
        Nome2.setBounds(50,60,200,20);
        Nome3.setBounds(50,100,200,20);
        Sala.setBounds(50,140,200,20);
        voltar.setBounds(50,200,100,20);
        
        tela1.add(Nome1);
        tela1.add(Nome2);
        tela1.add(Nome3);
        tela1.add(Sala);
        tela1.add(voltar);
        
        setSize(600,600);
        setLocationRelativeTo(null);
        }
    }
    
// função Tratador

 private class Tratador implements ActionListener{
        public void actionPerformed(ActionEvent tratar) {
            if(tratar.getSource()==popsair)
                System.exit(0);
            if(tratar.getSource()==popsobre)
               jasobre.setVisible(true);
        }
    }
}
