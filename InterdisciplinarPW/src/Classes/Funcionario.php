<?php
require_once("./Model/Conexao/conexao.php");

class funcionario {
   private $conn;
   private $Id_Func;
   private $Nome;
   private $Telefone;
   private $Horario_inicio;
   private $Horario_Fim;
   private $Id_Cinema;
   private $Id_Cargo;
   private $Usuario;
   private $Senha;


   public function getId_FUNC(){
      return $this->Id_Func;
   }
   public function setId_FUNC($IId_Func){
     $this->Id_Func = $IId_Func;
   }
   
   public function getNOME(){
     return $this->Nome;
   }
   public function setNOME($NNome){
      $this->Nome= $NNome;
   }

   public function getTELEFONE(){
      return $this->Telefone;
   }
   public function setTELEFONE($TTelefone){
     $this->Telefone = $TTelefone;
   }
   
   public function getHorario_INICIO(){
     return $this->Email;
   }
   public function setHorario_INICIO($HHorario_inicio){
      $this->Horario_inicio= $HHorario_inicio;
   }

   public function getHorario_FIM(){
      return $this->Horario_Fim;
   }
   public function setHorario_FIM($HHorario_Fim){
     $this->Horario_Fim = $HHorario_Fim;
   }
   
   public function getID_Cinema(){
     return $this->Id_Cinema;
   }
   public function setID_Cinema($IId_Cinema){
      $this->Id_Cinema= $IId_Cinema;
   }

   public function getID_Cargo(){
      return $this->Id_Cargo;
   }
   public function setID_Cargo($IId_Cargo){
     $this->Id_Cargo = $IId_Cargo;
   }
   
   public function getUSUARIO(){
     return $this->Usuario;
   }
   public function setUSUARIO($UUsuario){
      $this->Usuario= $UUsuario;
   }

   public function getSENHA(){
      return $this->Senha;
    }
    public function setSENHA($SSenha){
       $this->Senha= $SSenha;
    }



function salvar()
{
    try{
    $this-> conn = new Conectar();
    $sql = $this->  conn->prepare("insert into funcionario values (?,?,?,?,?,?,?,?,?)");
    @$sql->bindParam(1,$this->getId_FUNC(), PDO::PARAM_STR);
    @$sql->bindParam(2,$this->getNOME(), PDO::PARAM_STR);
    @$sql->bindParam(3,$this->getTELEFONE(), PDO::PARAM_STR);
    @$sql->bindParam(4,$this->getHorario_INICIO(), PDO::PARAM_STR);
    @$sql->bindParam(5,$this->getHorario_FIM(), PDO::PARAM_STR);
    @$sql->bindParam(6,$this->getID_Cinema(), PDO::PARAM_STR);
    @$sql->bindParam(7,$this->getID_Cargo(), PDO::PARAM_STR);
    @$sql->bindParam(8,$this->getUSUARIO(), PDO::PARAM_STR);
    @$sql->bindParam(9,$this->getSENHA(), PDO::PARAM_STR);


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
    $sql = $this-> conn->prepare("select * from funcionario where Id_Func=?");// informei o ? (parametro)
    @$sql->bindParam(1,$this->getId_FUNC(), PDO::PARAM_STR); // inclui esta linha para definir o parametro
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
    $sql = $this-> conn->prepare("update funcionario set Nome=?, Telefone=?, Horario_inicio=?, Horario_Fim=?, Id_Cinema=?, Id_Cargo=?, Usuario=?, Senha=?,  where Id_Func=?");  
    @$sql->bindParam(1,$this->getNOME(), PDO::PARAM_STR);
    @$sql->bindParam(2,$this->getTELEFONE(), PDO::PARAM_STR);
    @$sql->bindParam(3,$this->getHorario_INICIO(), PDO::PARAM_STR);
    @$sql->bindParam(4,$this->getHorario_FIM(), PDO::PARAM_STR);
    @$sql->bindParam(5,$this->getID_Cinema(), PDO::PARAM_STR);
    @$sql->bindParam(6,$this->getID_Cargo(), PDO::PARAM_STR);
    @$sql->bindParam(7,$this->getUSUARIO(), PDO::PARAM_STR);
    @$sql->bindParam(8,$this->getSENHA(), PDO::PARAM_STR);
    @$sql->bindParam(9,$this->getId_FUNC(), PDO::PARAM_STR);
    
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
    $sql = $this-> conn->prepare("select * from funcionario where Id_Func = ?");// informei o ?
    @$sql->bindParam(1,$this->getNOME(), PDO::PARAM_STR);
    @$sql->bindParam(2,$this->getTELEFONE(), PDO::PARAM_STR);
    @$sql->bindParam(3,$this->getHorario_INICIO(), PDO::PARAM_STR);
    @$sql->bindParam(4,$this->getHorario_FIM(), PDO::PARAM_STR);
    @$sql->bindParam(5,$this->getID_Cinema(), PDO::PARAM_STR);
    @$sql->bindParam(6,$this->getID_Cargo(), PDO::PARAM_STR);
    @$sql->bindParam(7,$this->getUSUARIO(), PDO::PARAM_STR);
    @$sql->bindParam(8,$this->getSENHA(), PDO::PARAM_STR);// inclui esta linha para definir o parametro
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
        $sql = $this-> conn->prepare("delete from funcionario where Id_Func=?");// informei o ? (parametro)
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
        $sql = $this-> conn->query("select * from funcionario order by Id_Func");
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