<?php
//Começando aqui a class Cinema que vai abordar todas as nossas classes


require_once("./Model/Conexao/conexao.php");//Incluindo a classe conexao

class Cinema{
    private $conn;
    private $cod_cinema;
    private $endereco;
    private $horaAbrir;
    private $horaFechar;
}

?>