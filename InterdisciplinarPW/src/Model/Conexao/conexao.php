<?php
//Começando a classe de conexão do cineestrela
 class Conectar extends PDO
 {
    //Criando variáveis
    private static $instancia;
    private $query;
    private $host = "127.0.0.1";
    private $usuario = "root";
    private $senha = "";
    private $db = "cineestrela";
    
    //Função mágica construct
    public function __construct()
    {
        parent::__construct("mysql:host=$this->host;dbname=$this->db","$this->usuario","$this->senha");

    }

    //Função getInstance
    public static function getInstance()
    {
        if (!isset(self::$instancia))
        {
            try
            {
                self::$instancia = new Conectar;
                echo 'Conectado com sucesso!!!';
            }
            catch(Exception $e)
            {
                echo 'Erro ao conectar';
                exit();
            }
        }
        return self::$instancia;

    }
    
    //Função SQL
    public function sql($query)
    {
        $this->getInstance();
        $this->query = $query;
        //$stmt = $pdo->prepare($this->query);
       // $stmt->execute();
        $pdo = null;
    }
 }
?>