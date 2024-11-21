<?php
require_once("./Model/Conexao/conexao.php");

class cargo {
   private $conn;
   private $Id_Cargo;
   private $Des_Cargo;

public function getId_CARGO(){
   return $this->Id_Cargo;
}
public function setId_CARGO($IId_Cargo){
  $this->Id_Cargo = $IId_Cargo;
}

public function getDes_CARGO(){
  return $this->Des_Cargo;
}
public function setDes_CARGO($DDes_Cargo){
   $this->Des_Cargo= $DDes_Cargo;
}





function salvar()
{
    try{
    $this-> conn = new Conectar();
    $sql = $this->  conn->prepare("insert into cargo values (?,?)");
    @$sql->bindParam(1,$this->getId_CARGO(), PDO::PARAM_STR);
    @$sql->bindParam(2,$this->getDes_CARGO(), PDO::PARAM_STR);

    if($sql->execute() == 1)
    {
       return "Registro salvo com sucesso!";
    }
    $this->conn=null;
    }
    catch(PDOException $exc)
    {
     echo"erro ao salvar o registro." . $exc->getMessage();    
    }
}

function alterar()
{
   try
   {
    $this-> conn = new Conectar();
    $sql = $this-> conn->prepare("select * from cargo where Id_Cargo=?");// informei o ? (parametro)
    @$sql->bindParam(1,$this->getId_CARGO(), PDO::PARAM_STR); // inclui esta linha para definir o parametro
    $sql->execute();
    return $sql->fetchAll();
    $this->conn=null;
   }
   catch(PDOException $exc)
    {
     echo"erro ao alterar   ." . $exc->getMessage();    
    }
}

function alterar2()
{
    try
   {
    $this-> conn = new Conectar(); 
    $sql = $this-> conn->prepare("update cargo set Des_Cargo=? where Id_Cargo=?");
    @$sql->bindParam(1,$this->getDes_CARGO(), PDO::PARAM_STR);
    @$sql->bindParam(2,$this->getId_CARGO(), PDO::PARAM_STR);
    
    if($sql->execute() == 1){
        return "Registro alterado com sucesso!";
    }
    $this->conn=null;
   }
   catch(PDOException $exc)
   {
    echo"erro ao salvar o registro." . $exc->getMessage();    
   }
}

function consultar()
{
    try
   {
    $this-> conn = new Conectar();
    $sql = $this-> conn->prepare("select * from cargo where Id_Cargo = ?");// informei o ?
    @$sql->bindParam(1,$this->getDes_CARGO(), PDO::PARAM_STR); // inclui esta linha para definir o parametro
    //@$sql->bindParam(1,$this->getNome(),"%", PDO::PARAM_STR);
    $sql->execute();
    return $sql->fetchAll();
    $this->conn=null;
   }
   catch(PDOException $exc)
    {
     echo"erro ao executar a consulta  ." . $exc->getMessage();    
    }
}

function exclusao()
{
    
    try{
        $this-> conn = new Conectar();
        $sql = $this-> conn->prepare("delete from cargo where Id_Cargo=?");// informei o ? (parametro)
        @$sql->bindParam(1,$this->getId_CARGO(), PDO::PARAM_STR); // inclui esta linha para definir o parametro
        if($sql->execute() == 1)
        {
           return "Excluido com sucesso!";
        }
        else{
            return "Erro na exclusão!";
        }
        $this->conn=null;
        }
        catch(PDOException $exc)
        {
         echo"erro ao excluir." . $exc->getMessage();    
        }   
}

function listar()
{
    try{
        $this-> conn = new Conectar();
        $sql = $this-> conn->query("select * from cargo order by Id_Cargo");
        $sql->execute();
        return $sql->fetchAll();    
        $this->conn=null;   
       }
       catch(PDOException $exc)
    {
     echo"erro ao executar a consulta  ." . $exc->getMessage();    
    }
}

}

?>