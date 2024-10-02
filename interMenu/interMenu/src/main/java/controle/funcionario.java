package controle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import conexao.conexao;

import javax.swing.table.DefaultTableModel;//para reconhecimento da JTable

import java.sql.*;
public class funcionario  extends JDialog{
    
        conexao con_cineestrela;
    
    JLabel lId_Funcionario,lNome,ltelefone,lID_Cargo,lID_Cinema,lHora_entrada,lHora_saida,pesq;
    JTextField tId_Funcionario,tNome,ttelefone,tID_Cargo,tID_Cinema,tHora_entrada,tHora_saida,pesqu;
    JTable tblFuncionario;//datagrid
    JScrollPane scp_tabela;
    JButton pri,ant,pro,ult,limpar,gravar,alterar,excluir,pesquisar,botaoVoltar;
      public funcionario(Frame owner, String title,boolean modal){

        Container tela = getContentPane();
        setLayout(null);
        setTitle("Conexão java com MySQL");
        setResizable(false);
        
        con_cineestrela = new conexao();// inicialização do objeto
        con_cineestrela.conecta();// chama o método que conecta
          
        //configuração da JTable
        
         tblFuncionario = new javax.swing.JTable();
         scp_tabela = new javax.swing.JScrollPane();
         
        //posição da JTable

         tblFuncionario.setBounds(10,350,700,200);
         scp_tabela.setBounds(10,350,700,200);
         
         tela.add(tblFuncionario);
         tela.add(scp_tabela);
         
         tblFuncionario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0,0,0)));
         tblFuncionario.setFont(new java.awt.Font("Arial",1,12));
         tblFuncionario.setModel(new javax.swing.table.DefaultTableModel(
         new Object [][]{
             {null,null,null,null,null,null,null},
             {null,null,null,null,null,null,null},
             {null,null,null,null,null,null,null},
             {null,null,null,null,null,null,null}
         },
            new String []{"Id_Func","Nome","Telefone","Horario_inicio","Horario_Fim","Id_Cinema","Id_Cargo"})
         {
             boolean[] canEdit = new boolean[]{
            false,false,false,false,false,false,false};
             
             public boolean isCellEditable(int rowIndex, int columnIndex){
                 return canEdit [columnIndex];}
         });
         scp_tabela.setViewportView(tblFuncionario);
              
                 tblFuncionario.setAutoCreateRowSorter(true);//ativa a classificação ordenada da tabela
                 //fim da config d jtable
                 
       //definindo as JLabel          
                 
       lId_Funcionario= new JLabel("Código:");
       lNome = new JLabel("Nome:");
       ltelefone = new JLabel("Telefone:");
       lID_Cargo= new JLabel("Cargo:");
       lID_Cinema= new JLabel("Id do Cinema:");
       lHora_entrada= new JLabel("Hora de entrada:");
       lHora_saida = new JLabel("Hora de saida:");
       pesq= new JLabel("Pesquisar registro pelo nome:");
       
       //definindo os JTextField
       
       pesqu = new JTextField(10); 
       tId_Funcionario  = new JTextField(5);           
       tNome = new JTextField(5);         
       ttelefone = new JTextField(5);
       tID_Cargo = new JTextField(5);
       tID_Cinema = new JTextField(5);
       tHora_entrada = new JTextField(5);
       tHora_saida = new JTextField(5);
       
       //definindo os JButton
       
       pri = new JButton("Primeiro");
       ant = new JButton("Anterior");
       pro = new JButton("Próximo");
       ult = new JButton("Último");    
       limpar = new JButton("Limpar");
       gravar = new JButton("Gravar");
       alterar = new JButton("Alterar");
       excluir = new JButton("Excluir");   
       pesquisar = new JButton("pesquisar"); 
       
       //definindo os setBounds
                       
       lId_Funcionario.setBounds(10, 10, 150, 20);
       lNome .setBounds(10, 40, 150, 20);
       ltelefone.setBounds(10, 70, 150, 20);
       lHora_entrada.setBounds(10, 100, 150, 20);
       lHora_saida.setBounds(10, 130, 150, 20);
       lID_Cinema.setBounds(10, 160, 150, 20);
       lID_Cargo.setBounds(10, 190, 150, 20);
      
     
       tId_Funcionario.setBounds(120, 10, 150, 20);     
       tNome.setBounds(120, 40, 150, 20);
       ttelefone .setBounds(120, 70, 150, 20);
       tHora_entrada .setBounds(120, 100, 150, 20);
       tHora_saida .setBounds(120, 130, 150, 20);
       tID_Cinema .setBounds(120, 160, 150, 20);
       tID_Cargo .setBounds(120, 190, 150, 20);
        //definindo os setBounds dos botões
       
       pri.setBounds(290, 30, 120, 20);      
       ant.setBounds(290, 80, 120, 20);
       pro.setBounds(290, 130, 120, 20);
       ult.setBounds(290, 180, 120, 20);
       limpar.setBounds(10, 240, 120, 20);      
       gravar.setBounds(150, 240, 120, 20);
       alterar.setBounds(290, 240, 120, 20);
       excluir.setBounds(430, 240, 120, 20);
       pesq.setBounds(10, 290, 200, 20);
       pesqu.setBounds(190, 290, 200, 20);
       pesquisar.setBounds(390, 290, 120, 20);
       
        //definindo as funções dos botões

    // botão primeiro
       pri.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               try{
                con_cineestrela.resultset.first();
                   mostrar_dados();
               }catch(SQLException erro){
                   JOptionPane.showMessageDialog(null,"\n Não foi possível acessar o primeiro registro:\n"+erro,"Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);    
               }
           }} );

       // botão anterior

       ant.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               try{
                con_cineestrela.resultset.previous();
                   mostrar_dados();
               }catch(SQLException erro){
                   JOptionPane.showMessageDialog(null,"\n Não foi possível acessar o registro anterior:\n"+erro,"Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);    
               }
           }} );

       // botão proximo

       pro.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               try{
                con_cineestrela.resultset.next();
                   mostrar_dados();
               }catch(SQLException erro){
                   JOptionPane.showMessageDialog(null,"\n Não foi possível acessar o proximo registro:\n"+erro,"Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);    
               }
           }} );

        // botão ultimo
       
       ult.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               try{
                con_cineestrela.resultset.last();
                   mostrar_dados();
               }catch(SQLException erro){
                   JOptionPane.showMessageDialog(null,"\n Não foi possível acessar o Ultimo registro:\n"+erro,"Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);    
               }
           }} );
       
           // botão de limpar
    
        limpar.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){           
                tId_Funcionario .setText("");           
                tNome.setText("");        
                ttelefone.setText(""); 
                tID_Cargo.setText(""); 
                tID_Cinema.setText(""); 
                tHora_entrada.setText(""); 
                tHora_saida.setText(""); 
              
               tId_Funcionario.requestFocus();
           }} );

        // botão de gravar
        
        gravar.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
                  
            String Id_Func = tId_Funcionario.getText();
            String Nome = tNome.getText();
            String Telefone = ttelefone.getText();        
            String Horario_inicio = tHora_entrada.getText();
            String Horario_Fim = tHora_saida.getText();
            String Id_Cinema = tID_Cinema.getText();
            String Id_Cargo = tID_Cargo.getText();
            try{
                String insert_sql="insert into funcionario (Id_Func, Nome, Telefone, Horario_inicio, Horario_Fim, Id_Cinema, Id_Cargo) values ('" +Id_Func+ "','" + Nome + "','" + Telefone + "','" +  Horario_inicio+ "','" + Horario_Fim + "','" + Id_Cinema+ "','"+  Id_Cargo+"',)";
                con_cineestrela.statement.executeUpdate(insert_sql);
                JOptionPane.showMessageDialog(null," Gravação realizada com sucesso!!","Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);  
                con_cineestrela.executaSQL("select * from funcionario order by Id_Func");
                preencherTabela();
            }catch(SQLException errosql){
                JOptionPane.showMessageDialog(null,"\n Erro na gravação:\n"+errosql,"Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);    
            }
           }} );
         
        // botão de alterar

         alterar.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
             
            String Id_Func = tId_Funcionario.getText();
            String Nome = tNome.getText();
            String Telefone = ttelefone.getText();
            String Horario_inicio = tHora_entrada.getText();
            String Horario_Fim = tHora_saida.getText();
            String Id_Cinema = tID_Cinema.getText();
            String Id_Cargo = tID_Cargo.getText();
            String sql;
            String msg="";
            try{
                if(tId_Funcionario.getText().equals("")){
                sql="insert into funcionario (Id_Func,Nome, Telefone,Horario_inicio,Horario_Fim,Id_Cinema,Id_Cargo) values ('" + Id_Func+ "','" + Nome + "','" + Telefone + "','" + Horario_inicio + "','" + Horario_Fim + "','" + Id_Cinema+ "','"+Id_Cargo+"',)";
                msg="gravação de um novo registro";
                }else{
                  sql="update funcionario set Nome='" + Nome+ "','" + Telefone + "','" +Horario_inicio  + "','" + Horario_Fim+ "','" +Id_Cinema + "','"+ Id_Cargo+"' where Id_Func = " + tId_Funcionario.getText();                
                  msg="Alteração de registro";
                }
                con_cineestrela.statement.executeUpdate(sql);
                JOptionPane.showMessageDialog(null," Alteração realizada com sucesso!!","Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);  
                
                con_cineestrela.executaSQL("select * from Funcionario order by Id_Funcionario");
                preencherTabela();
                
            }catch(SQLException errosql){
                JOptionPane.showMessageDialog(null,"\n Erro na alteração:\n"+errosql,"Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);    
            }
           }} );
    
        
         // botão de excluir

         excluir.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
            String sql="";
            try{
                int resposta=JOptionPane.showConfirmDialog(rootPane, "Deseja excluir o registro: "," Confirmar exclusão ",JOptionPane.YES_NO_OPTION,3);
                if(resposta==0){
                    sql="delete from funcionario where Id_Func = "+tId_Funcionario.getText();
                    int excluir= con_cineestrela.statement.executeUpdate(sql);
                  if(excluir==1){
                    JOptionPane.showMessageDialog(null," Exclusão realizada com sucesso!!","Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);  
                    con_cineestrela.executaSQL("select * from funcionario order by Id_Func");
                    con_cineestrela.resultset.first();
                    preencherTabela();
                    posicionarRegistro();
                    }
                  else{
                    JOptionPane.showMessageDialog(null," Operação cancelada pelo usuario!!","Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);  
                }
               }
            }catch(SQLException excecao){
                JOptionPane.showMessageDialog(null," Erro na exclusão:"+excecao,"Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);    
            }
           }} );
         
         // botão  de pesquisar
         
         pesquisar.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
           try{
              String pesquisa="select * from funcionario where Nome like '"+pesqu.getText()+"%'"; 
              con_cineestrela.executaSQL(pesquisa);
             
             if(con_cineestrela.resultset.first()){
                 preencherTabela();
             }
             else{
                 JOptionPane.showMessageDialog(null,"\n Não existe dados com esse paramêtro!!","Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);  
             }
           }catch(SQLException errosql){
               JOptionPane.showMessageDialog(null,"\n Os dados digitados não foram localizados!! : \n"+errosql,"Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);    
           }
           }} );
         

        // definição do botão voltar

         botaoVoltar = new JButton("Voltar");
         botaoVoltar.setBounds(650,10,80,20); 
         botaoVoltar.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
          menu app = new menu();
            app.setVisible(false);
            dispose();
          }}); 
        
         

      //adicionando os itens na tela
         
       tela.add(botaoVoltar);
       tela.add(pesq);
       tela.add(pesqu);
       tela.add(pesquisar);
       tela.add(limpar);
       tela.add(gravar);
       tela.add(alterar);
       tela.add(excluir);
       tela.add(pri);
       tela.add(ant);
       tela.add(pro);
       tela.add(ult);
       tela.add(lId_Funcionario);
       tela.add(lNome);
       tela.add(ltelefone);
       tela.add(lID_Cargo);
       tela.add(lHora_entrada);
       tela.add(lHora_saida);
       tela.add(lID_Cinema);
       tela.add(tId_Funcionario);
       tela.add(tNome);
       tela.add(ttelefone);
       tela.add(tID_Cargo);
       tela.add(tHora_entrada);
       tela.add(tHora_saida);
       tela.add(tID_Cinema);
       
       
          setSize(800,600);
          setVisible(true);
          setLocationRelativeTo(null);
          

        //chamando as funções

        con_cineestrela.executaSQL("select * from funcionario order by Id_Func");
          preencherTabela();
          posicionarRegistro();
   }

    //função preencherTabela

  public void preencherTabela(){
      tblFuncionario.getColumnModel().getColumn(0).setPreferredWidth(5);
      tblFuncionario.getColumnModel().getColumn(1).setPreferredWidth(100);
      tblFuncionario.getColumnModel().getColumn(2).setPreferredWidth(11);
      tblFuncionario.getColumnModel().getColumn(3).setPreferredWidth(14);
      tblFuncionario.getColumnModel().getColumn(4).setPreferredWidth(14);
      tblFuncionario.getColumnModel().getColumn(5).setPreferredWidth(10);
      tblFuncionario.getColumnModel().getColumn(6).setPreferredWidth(10);
      
      DefaultTableModel modelo =(DefaultTableModel)  tblFuncionario.getModel();
      modelo.setNumRows(0);
      
      try{
        con_cineestrela.resultset.beforeFirst();
           while(con_cineestrela.resultset.next()){
               modelo.addRow(new Object[]{
                con_cineestrela.resultset.getString("Id_Func"),con_cineestrela.resultset.getString("Nome"),con_cineestrela.resultset.getString("Telefone"),con_cineestrela.resultset.getString("Horario_inicio"),con_cineestrela.resultset.getString("Horario_Fim"),con_cineestrela.resultset.getString("Id_Cinema"),con_cineestrela.resultset.getString("Id_Cargo")
               });
           }
      }catch(SQLException erro){
          JOptionPane.showMessageDialog(null,"\n Erro ao listar dados da tabela!!:\n"+erro,"Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
      }
  }


//função posicionarRegistro

  public void posicionarRegistro(){
      try{
        con_cineestrela.resultset.first();// posiciona o 1° registro da tabela
          mostrar_dados();
      }catch(SQLException erro){
          JOptionPane.showMessageDialog(null,"Não foi possivel posicionar no primeiro registro:"+erro,"Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
      }
  }

  //função mostrar_dados

      public void  mostrar_dados(){
          try{
          tId_Funcionario.setText(con_cineestrela.resultset.getString("Id_Func"));
          tNome.setText(con_cineestrela.resultset.getString("Nome"));
          ttelefone.setText(con_cineestrela.resultset.getString("Telefone"));    
          tHora_entrada.setText(con_cineestrela.resultset.getString("Horario_inicio"));
          tHora_saida.setText(con_cineestrela.resultset.getString("Horario_Fim"));
          tID_Cinema.setText(con_cineestrela.resultset.getString("Id_Cinema"));
          tID_Cargo.setText(con_cineestrela.resultset.getString("Id_Cargo"));
      }catch(SQLException erro){
         JOptionPane.showMessageDialog(null,"Não localizou dados:"+erro,"Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
      }
   }
     
}