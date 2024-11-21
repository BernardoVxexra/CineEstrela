<?php
require_once($_SERVER['DOCUMENT_ROOT'] . '/InterdisciplinarPW/config/globals.php');
include_once($BASE_PATH . 'src/Controller/filmesDao.php');

try {
    $conn = new PDO("mysql:host=localhost;dbname=cineestrela", "root", "");
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
    echo "Erro de conexão: " . $e->getMessage();
    exit;
}

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $nome = isset($_POST['nome']) ? trim($_POST['nome']) : '';

    if (!empty($nome)) {
        $filmesDao = new filmesDao($conn);
        if ($filmesDao->deleteByName($nome)) {
            header("Location: catalogo.php?mensagem=Filme excluído com sucesso");
            exit;
        } else {
            echo "Erro ao excluir o filme.";
        }
    } else {
        echo "Nome inválido!";
    }
}

?>
