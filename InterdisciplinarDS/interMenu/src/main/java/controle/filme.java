package controle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import conexao.conexao;

import javax.swing.table.DefaultTableModel;//para reconhecimento da JTable

import java.sql.*;

public class filme extends JDialog{
        conexao con_cineestrela;
    
    JLabel lId_filme,lNome,lClass_etaria,lDescri,lGenero,lDuracao,pesq;
    JTextField tId_filme,tNome,tClass_etaria,tDescri,tGenero,tDuracao,pesqu;
    JTable tblFilme;//datagrid
    JScrollPane scp_tabela;
    JButton pri,ant,pro,ult,limpar,gravar,alterar,excluir,pesquisar,botaoVoltar;

      public filme(Frame owner, String title,boolean modal){
        Container tela = getContentPane();
        setLayout(null);
        setTitle("Conexão java com MySQL");
        setResizable(false);
        
        con_cineestrela = new conexao();// inicialização do objeto
        con_cineestrela.conecta();// chama o método que conecta
          
        //configuração da JTable
        
         tblFilme = new javax.swing.JTable();
         scp_tabela = new javax.swing.JScrollPane();
         
        //posição da JTable

         tblFilme.setBounds(10,350,700,200);
         scp_tabela.setBounds(10,350,700,200);
         
         tela.add(tblFilme);
         tela.add(scp_tabela);
         
         tblFilme.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0,0,0)));
         tblFilme.setFont(new java.awt.Font("Arial",1,12));
         tblFilme.setModel(new javax.swing.table.DefaultTableModel(
         new Object [][]{
             {null,null,null,null,null,null},
             {null,null,null,null,null,null},
             {null,null,null,null,null,null},
             {null,null,null,null,null,null}
         },
            new String []{"Id Filme","Nome","Classificação etária", "Descrição","Genero","Duração"})
         {
             boolean[] canEdit = new boolean[]{
            false,false,false,false,false};
             
             public boolean isCellEditable(int rowIndex, int columnIndex){
                 return canEdit [columnIndex];}
         });
         scp_tabela.setViewportView(tblFilme);
              
                 tblFilme.setAutoCreateRowSorter(true);//ativa a classificação ordenada da tabela
                 //fim da config d jtable                
                 
       //definindo as JLabel       

       lId_filme= new JLabel("Código");
       lNome = new JLabel("Nome:");
       lClass_etaria = new JLabel("Classificação etária:");
       lDescri = new JLabel("Descrição:");
       lGenero = new JLabel("Genêro:");
       lDuracao = new JLabel("Duração:");
       pesq= new JLabel("Pesquisar registro pelo nome:");

       //definindo os JTextField

       tId_filme = new JTextField(5);     
       pesqu = new JTextField(10); 
       tNome = new JTextField(5);         
       tClass_etaria = new JTextField(5);
       tDescri = new JTextField(5);
       tGenero = new JTextField(5);
       tDuracao = new JTextField(5);

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
                       
       lId_filme.setBounds(10, 10, 70, 50);
       lNome.setBounds(10, 40, 70, 50);
       lClass_etaria.setBounds(10, 70, 120, 50);
       lDescri.setBounds(10, 100, 70, 50);
       lGenero.setBounds(10, 135, 70, 50);
       lDuracao.setBounds(10, 185, 150, 20);
       
       
       tId_filme.setBounds(140, 25, 150, 20);      
       tNome.setBounds(140, 55, 150, 20);
       tClass_etaria.setBounds(140, 85, 150, 20);
       tDescri.setBounds(140, 120, 150, 20);      
       tGenero.setBounds(140, 155, 150, 20);
       tDuracao.setBounds(140, 190, 150, 20);

        //definindo os setBounds dos botões

       pri.setBounds(300, 30, 120, 20);      
       ant.setBounds(300, 80, 120, 20);
       pro.setBounds(300, 130, 120, 20);
       ult.setBounds(300, 180, 120, 20);
       
       
       limpar.setBounds(10, 240, 120, 20);      
       gravar.setBounds(150, 240, 120, 20);
       alterar.setBounds(290, 240, 120, 20);
       excluir.setBounds(430, 240, 120, 20);
       pesq.setBounds(10, 290, 200, 20);
       pesqu.setBounds(190, 290, 200, 20);
       pesquisar.setBounds(390, 290, 120, 20);
       

        //definindo as funções dos botões


       pri.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               try{
                con_cineestrela.resultset.first();
                   mostrar_dados();
               }catch(SQLException erro){
                   JOptionPane.showMessageDialog(null,"\n Não foi possível acessar o primeiro registro:\n"+erro,"Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);    
               }
           }} );
       



       ant.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               try{
                con_cineestrela.resultset.previous();
                   mostrar_dados();
               }catch(SQLException erro){
                   JOptionPane.showMessageDialog(null,"\n Não foi possível acessar o registro anterior:\n"+erro,"Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);    
               }
           }} );
       



       pro.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               try{
                con_cineestrela.resultset.next();
                   mostrar_dados();
               }catch(SQLException erro){
                   JOptionPane.showMessageDialog(null,"\n Não foi possível acessar o proximo registro:\n"+erro,"Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);    
               }
           }} );
    
           



       ult.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               try{
                con_cineestrela.resultset.last();
                   mostrar_dados();
               }catch(SQLException erro){
                   JOptionPane.showMessageDialog(null,"\n Não foi possível acessar o Ultimo registro:\n"+erro,"Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);    
               }
           }} );
       
    



        limpar.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){           
               tId_filme.setText("");
               tNome.setText("");
               tClass_etaria.setText(""); 
               tDescri.setText("");
               tGenero.setText("");
               tDuracao.setText(""); 
               tId_filme.requestFocus();
           }} );
        
        



        gravar.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               
            String Id_Filme = tId_filme.getText();
            String Nome = tNome.getText();
            String Classi_Etaria = tClass_etaria.getText();
            String Descricao = tDescri.getText();
            String Genero = tGenero.getText();
            String Duracao = tDuracao.getText();
            
            try{
                String insert_sql="insert into filme (Id_Filme,Nome, Classi_Etaria, Descricao,Genero,Duracao) values ('" + Id_Filme + "','" + Nome+ "','" + Classi_Etaria + "','" + Descricao + "','" + Genero + "','" + Duracao + "',)";
                con_cineestrela.statement.executeUpdate(insert_sql);
                JOptionPane.showMessageDialog(null," Gravação realizada com sucesso!!","Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);  
                con_cineestrela.executaSQL("select * from filme order by Id_Filme");
                preencherTabela();
            }catch(SQLException errosql){
                JOptionPane.showMessageDialog(null,"\n Erro na gravação:\n"+errosql,"Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);    
            }
           }} );
         



        
         alterar.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
             
            String Id_Filme = tId_filme.getText();
            String Nome = tNome.getText();
            String Classi_Etaria = tClass_etaria.getText();
            String Descricao = tDescri.getText();
            String Genero = tGenero.getText();
            String Duracao = tDuracao.getText();
            String sql;
            String msg="";
            try{
                if(tId_filme.getText().equals("")){
                sql="insert into filme (Id_Filme,Nome, Classi_Etaria, Descricao,Genero,Duracao) values ('" + Id_Filme + "','" + Nome+ "','" + Classi_Etaria + "','" + Descricao + "','" + Genero + "',,'" + Duracao + "',)";
                msg="gravação de um novo registro";
                }else{
                  sql="update filme set Nome='" + Nome+ "','" + Classi_Etaria + "','" + Descricao + "','" + Genero + "',,'" + Duracao + "' where Id_Filme = " + tId_filme.getText();                
                  msg="Alteração de registro";
                }
                con_cineestrela.statement.executeUpdate(sql);
                JOptionPane.showMessageDialog(null," Alteração realizada com sucesso!!","Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);  
                
                con_cineestrela.executaSQL("select * from filme order by Id_Filme");
                preencherTabela();
                
            }catch(SQLException errosql){
                JOptionPane.showMessageDialog(null,"\n Erro na alteração:\n"+errosql,"Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);    
            }
           }} );
        
        


         
         excluir.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
            String sql="";
            try{
                int resposta=JOptionPane.showConfirmDialog(rootPane, "Deseja excluir o registro: "," Confirmar exclusão ",JOptionPane.YES_NO_OPTION,3);
                if(resposta==0){
                    sql="delete from filme where Id_Filme = "+tId_filme.getText();
                    int excluir= con_cineestrela.statement.executeUpdate(sql);
                  if(excluir==1){
                    JOptionPane.showMessageDialog(null," Exclusão realizada com sucesso!!","Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);  
                    con_cineestrela.executaSQL("select * from filme order by Id_Filme");
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
         
         
         
         pesquisar.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
           try{
              String pesquisa="select * from filme where Nome like '"+pesqu.getText()+"%'"; 
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
       tela.add(lId_filme);
       tela.add(lNome);
       tela.add(lClass_etaria);
       tela.add(lDescri);
       tela.add(lGenero);
       tela.add(lDuracao);
       tela.add(tId_filme);
       tela.add(tNome);
       tela.add(tClass_etaria);
       tela.add(tDescri);
       tela.add(tGenero);
       tela.add(tDuracao);
       
          setSize(800,600);
          setVisible(true);
          setLocationRelativeTo(null);
          
         // chamando as funções

         con_cineestrela.executaSQL("select * from filme order by Id_Filme");
          preencherTabela();
          posicionarRegistro();
   }

    //função preencherTabela


  public void preencherTabela(){
      tblFilme.getColumnModel().getColumn(0).setPreferredWidth(4);
      tblFilme.getColumnModel().getColumn(1).setPreferredWidth(100);
      tblFilme.getColumnModel().getColumn(2).setPreferredWidth(14);
      tblFilme.getColumnModel().getColumn(3).setPreferredWidth(150);
      tblFilme.getColumnModel().getColumn(4).setPreferredWidth(30);
      tblFilme.getColumnModel().getColumn(5).setPreferredWidth(20);

      DefaultTableModel modelo =(DefaultTableModel)  tblFilme.getModel();
      modelo.setNumRows(0);
      
      try{
        con_cineestrela.resultset.beforeFirst();
           while(con_cineestrela.resultset.next()){
               modelo.addRow(new Object[]{
                con_cineestrela.resultset.getString("Id_Filme"),con_cineestrela.resultset.getString("Nome"),con_cineestrela.resultset.getString("Classi_Etaria"),con_cineestrela.resultset.getString("Descricao"),con_cineestrela.resultset.getString("Genero"),con_cineestrela.resultset.getString("Duracao")
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
          tId_filme.setText(con_cineestrela.resultset.getString("Id_Filme"));
          tNome.setText(con_cineestrela.resultset.getString("Nome"));
          tClass_etaria.setText(con_cineestrela.resultset.getString("Classi_Etaria"));
          tDescri.setText(con_cineestrela.resultset.getString("Descricao"));
          tGenero.setText(con_cineestrela.resultset.getString("Genero"));
          tDuracao.setText(con_cineestrela.resultset.getString("Duracao"));
      }catch(SQLException erro){
         JOptionPane.showMessageDialog(null,"Não localizou dados:"+erro,"Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
      }
   }
     
}
        





  
  

