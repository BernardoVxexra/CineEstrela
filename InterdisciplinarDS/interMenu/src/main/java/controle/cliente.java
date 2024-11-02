package controle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.*;
import javax.swing.text.MaskFormatter;

import conexao.conexao;

import javax.swing.table.DefaultTableModel;//para reconhecimento da JTable

import java.sql.*;
        
public class cliente extends JDialog{
    conexao con_cineestrela;
    
    JLabel lId_cliente,lNome,lEmail,lData,pesq;
    JTextField tId_cliente,tNome,tEmail,pesqu;
    JFormattedTextField data;
    MaskFormatter mData;

    
    JTable tblClientes;//datagrid
    JScrollPane scp_tabela;
    JButton pri,ant,pro,ult,limpar,gravar,alterar,pesquisar,botaoVoltar,excluir;
    public cliente(Frame owner, String title,boolean modal){
        
        Container tela = getContentPane();
        setLayout(null);
        setTitle("Clientes");
        setResizable(false);
        
       con_cineestrela = new conexao();// inicialização do objeto
       con_cineestrela.conecta();// chama o método que conecta
          
        //configuração da JTable
        
         tblClientes = new javax.swing.JTable();
         scp_tabela = new javax.swing.JScrollPane();
         
        //posição da JTable 

         tblClientes.setBounds(10,350,700,200);
         scp_tabela.setBounds(10,350,700,200);
         
         tela.add(tblClientes);
         tela.add(scp_tabela);
         
         tblClientes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0,0,0)));
         tblClientes.setFont(new java.awt.Font("Arial",1,12));
         tblClientes.setModel(new javax.swing.table.DefaultTableModel(
         new Object [][]{
             {null,null,null,null},
             {null,null,null,null},
             {null,null,null,null},
             {null,null,null,null}
         },
            new String []{"Id_cliente","nome","Data de nascimento","Email"})
         {
             boolean[] canEdit = new boolean[]{
            false,false,false,false};
             
             public boolean isCellEditable(int rowIndex, int columnIndex){
                 return canEdit [columnIndex];}
         });
         scp_tabela.setViewportView(tblClientes);
              
                 tblClientes.setAutoCreateRowSorter(true);//ativa a classificação ordenada da tabela
                 //fim da config d jtable
                 
       //definindo as JLabel       

       lId_cliente= new JLabel("Código");
       lNome= new JLabel("Nome:");
       lEmail = new JLabel("Email:");
       lData= new JLabel("Data:");
       pesq= new JLabel("Pesquisar registro pelo nome:");

       //definindo os JTextField

       tId_cliente = new JTextField(5); 
       pesqu = new JTextField(10); 
       tNome = new JTextField(5);         
       tEmail = new JTextField(5);

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

       //definindo o MaskFormatter

       try{
           mData = new MaskFormatter("####/##/##");
           mData.setPlaceholderCharacter('_');
       }
       catch(ParseException excp)
       {
    }
       data = new JFormattedTextField (mData);
        //definindo os setBounds
                  
       lId_cliente.setBounds(10, 10, 70, 50);
       lNome.setBounds(10, 40, 70, 50);
       lEmail.setBounds(10, 70, 70, 50);
       lData.setBounds(10, 100, 70, 50);
       
       tId_cliente.setBounds(80, 25, 150, 20);  
       tNome.setBounds(80, 55, 150, 20);      
       tEmail.setBounds(80, 85, 150, 20);
       data.setBounds(80, 115, 150, 20);

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
               tId_cliente.setText("");
               tNome.setText("");
               data.setText("");
               tEmail.setText("");
               tId_cliente.requestFocus();
           }} );



        
        
        gravar.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
            String Id_Cliente = tId_cliente.getText();
            String Nome = tNome.getText();          
            String data_nasc = data.getText();
            String Email = tEmail.getText();
            try{
                String insert_sql="insert into cliente (Id_Cliente,Nome,dt_nasc,Email) values ('" + Id_Cliente + "','" + Nome + "','" + data_nasc + "','" +  Email+ "')";
                con_cineestrela.statement.executeUpdate(insert_sql);
                JOptionPane.showMessageDialog(null," Gravação realizada com sucesso!!","Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);  
                con_cineestrela.executaSQL("select * from cliente order by Id_Cliente");
                preencherTabela();
            }catch(SQLException errosql){
                JOptionPane.showMessageDialog(null,"\n Erro na gravação:\n"+errosql,"Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);    
            }
           }} );
         
        




         alterar.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
             
            String Nome = tNome.getText();
            String data_nasc = data.getText();
            String Email = tEmail.getText();
            String sql;
            String msg="";
            try{
                if(tId_cliente.getText().equals("")){
                sql="insert into cliente (Nome, data_nasc,Email) values ('" + Nome + "','" +data_nasc + "','" +Email+ "')";
                msg="gravação de um novo registro";
                }else{
                  sql="update cliente set nome='" + Nome + "', email='" + data_nasc + "',dt_nasc='" +  Email+ "' where Id_Cliente = " + tId_cliente.getText();                
                  msg="Alteração de registro";
                }
                con_cineestrela.statement.executeUpdate(sql);
                JOptionPane.showMessageDialog(null," Alteração realizada com sucesso!!","Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);  
                
                con_cineestrela.executaSQL("select * from cliente order by Id_Cliente");
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
                    sql="delete from cliente where Id_Cliente = "+tId_cliente.getText();
                    int excluir= con_cineestrela.statement.executeUpdate(sql);
                  if(excluir==1){
                    JOptionPane.showMessageDialog(null," Exclusão realizada com sucesso!!","Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);  
                    con_cineestrela.executaSQL("select * from cliente order by Id_Cliente");
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
              String pesquisa="select * from cliente where Nome like '"+pesqu.getText()+"%'"; 
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
         botaoVoltar.addActionListener((ActionEvent e) ->{
            menu app = new menu();
            app.setVisible(false);
            dispose();
         });



        //adicionando os itens na tela
         
       tela.add(botaoVoltar);
       tela.add(pesq);
       tela.add(pesqu);
       tela.add(pesquisar);
       tela.add(limpar);
       tela.add(gravar);
       tela.add(alterar);
       tela.add(pri);
       tela.add(ant);
       tela.add(pro);
       tela.add(ult);
       tela.add(lNome);
       tela.add(lId_cliente);
       tela.add(lData);
       tela.add(lNome);
       tela.add(lEmail);
       tela.add(tNome);
       tela.add(tId_cliente);
       tela.add(data);
       tela.add(tEmail);
       tela.add(excluir);
       
          setSize(800,600);
          setVisible(true);
          setLocationRelativeTo(null);
          
        // chamando as funções

        con_cineestrela.executaSQL("select * from cliente order by Id_Cliente");
          preencherTabela();
          posicionarRegistro();
   }


//função preencherTabela
   
  public void preencherTabela(){
      tblClientes.getColumnModel().getColumn(0).setPreferredWidth(4);
      tblClientes.getColumnModel().getColumn(1).setPreferredWidth(150);
      tblClientes.getColumnModel().getColumn(3).setPreferredWidth(14);
      tblClientes.getColumnModel().getColumn(4).setPreferredWidth(100);
      
      DefaultTableModel modelo =(DefaultTableModel)  tblClientes.getModel();
      modelo.setNumRows(0);
      
      try{
        con_cineestrela.resultset.beforeFirst();
           while(con_cineestrela.resultset.next()){
               modelo.addRow(new Object[]{
                con_cineestrela.resultset.getString("Id_Cliente"),con_cineestrela.resultset.getString("Nome"),con_cineestrela.resultset.getString("dt_nasc"),con_cineestrela.resultset.getString("Email")
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
          tId_cliente.setText(con_cineestrela.resultset.getString("Id_Cliente"));
          tNome.setText(con_cineestrela.resultset.getString("Nome"));
          data.setText(con_cineestrela.resultset.getString("dt_nasc"));
          tEmail.setText(con_cineestrela.resultset.getString("Email"));
      }catch(SQLException erro){
         JOptionPane.showMessageDialog(null,"Não localizou dados:"+erro,"Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
      }
   }
  }


