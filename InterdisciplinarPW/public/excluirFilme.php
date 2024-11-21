<?php
include_once($_SERVER['DOCUMENT_ROOT'] . '/InterdisciplinarPW/config/globals.php');
include_once($BASE_PATH . 'src/Controller/filmesDao.php');

$nome = isset($_POST['nome']) ? $_POST['nome'] : '';

if ($nome) {
    // Conexão com o banco
    try {
        $conn = new PDO("mysql:host=localhost;dbname=cineestrela", "root", "");
        $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    } catch (PDOException $e) {
        echo "Erro de conexão: " . $e->getMessage();
        exit;
    }

    $filmesDao = new filmesDao($conn);
    if ($filmesDao->deleteByName($nome)) {
        echo "Filme excluído com sucesso!";
    } else {
        echo "Erro ao excluir filme.";
    }
}
?>
