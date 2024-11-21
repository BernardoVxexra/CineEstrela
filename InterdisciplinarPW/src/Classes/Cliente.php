<?php
require_once("./Model/Conexao/conexao.php");

//Criando classe Cliente
class cliente{

    private $conn;
    private $Id_Cliente;
    private $Nome;
    private $dt_nasc;
    private $Email;

    public function getId_CLIENTE(){
        return $this->Id_Cliente;
     }
     public function setId_CLIENTE($IId_Cliente){
       $this->Id_Cliente = $IId_Cliente;
     }
     
     public function getNOME(){
       return $this->Nome;
     }
     public function setNOME($NNome){
        $this->Nome= $NNome;
     }

     public function getDT_nasc(){
        return $this->dt_nasc;
     }
     public function setDT_nasc($ddt_nasc){
       $this->dt_nasc = $ddt_nasc;
     }
     
     public function getEMAIL(){
       return $this->Email;
     }
     public function setEMAIL($EEmail){
        $this->Email= $EEmail;
     }



function salvar()
{
    try{
    $this-> conn = new Conectar();
    $sql = $this->  conn->prepare("insert into cliente values (?,?,?,?)");
    @$sql->bindParam(1,$this->getId_CLIENTE(), PDO::PARAM_STR);
    @$sql->bindParam(2,$this->getNOME(), PDO::PARAM_STR);
    @$sql->bindParam(3,$this->getDT_nasc(), PDO::PARAM_STR);
    @$sql->bindParam(4,$this->getEMAIL(), PDO::PARAM_STR);


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
    $sql = $this-> conn->prepare("select * from cliente where Id_Cliente=?");// informei o ? (parametro)
    @$sql->bindParam(1,$this->getId_CLIENTE(), PDO::PARAM_STR); // inclui esta linha para definir o parametro
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
    $sql = $this-> conn->prepare("update cliente set Nome=?, dt_nasc=?, Email=?, where Id_Cliente=?");
    @$sql->bindParam(1,$this->getNOME(), PDO::PARAM_STR);
    @$sql->bindParam(2,$this->getDT_nasc(), PDO::PARAM_STR);
    @$sql->bindParam(3,$this->getEMAIL(), PDO::PARAM_STR);
    @$sql->bindParam(4,$this->getId_CLIENTE(), PDO::PARAM_STR);
    
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
    $sql = $this-> conn->prepare("select * from cliente where Id_Cliente = ?");// informei o ?
    @$sql->bindParam(1,$this->getNOME(), PDO::PARAM_STR);
    @$sql->bindParam(2,$this->getDT_nasc(), PDO::PARAM_STR);
    @$sql->bindParam(3,$this->getEMAIL(), PDO::PARAM_STR); // inclui esta linha para definir o parametro
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
        $sql = $this-> conn->prepare("delete from cliente where Id_Cliente=?");// informei o ? (parametro)
        @$sql->bindParam(1,$this->getId_CLIENTE(), PDO::PARAM_STR); // inclui esta linha para definir o parametro
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
        $sql = $this-> conn->query("select * from cliente order by Id_Cliente");
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