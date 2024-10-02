
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class conexao {
    
    final private String driver="com.mysql.cj.jdbc.Driver";// definição do driver MySql para o acesso de dados
    final private String url="jdbc:mysql://localhost/cineestrela";
    final private String usuario="root";/*usuario do mysql*/
    final private String senha="";/*senha do mysql*/
    private Connection conexao;//variavel que amanezara a conexão aberta
    public Statement statement;// variavel para a execução dos comandos sql dentro do ambiente java
    public ResultSet resultset;// variavel que amanezara o resultado da execução de um comando sql
    
    public boolean conecta (){
        boolean result = true;
        try{
         Class.forName(driver);
         conexao = DriverManager.getConnection(url,usuario,senha);
         
        }catch(ClassNotFoundException Driver){
         JOptionPane.showMessageDialog(null,"Driver não localizado "+Driver,"Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        result= false;    
        }catch(SQLException Fonte){
         JOptionPane.showMessageDialog(null,"Fonte de dados não localizada "+Fonte,"Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        result= false; 
        }
        return result; 
    }

    public void desconecta(){
        try{
            conexao.close();
              JOptionPane.showMessageDialog(null,"Conexão com o banco fechada","Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException Fecha){
         JOptionPane.showMessageDialog(null,"Erro ao fechar o banco ","Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    
    public void executaSQL(String sql){
        try{
            statement= conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultset = statement.executeQuery(sql);
        }catch(SQLException excecao){
         JOptionPane.showMessageDialog(null,"Erro no comando SQL! \n Erro: "+excecao,"Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
