<?php
$pageCSS = ["confirmar.css"];
// Confirmar exclusão do filme
require_once($_SERVER['DOCUMENT_ROOT'] . '/InterdisciplinarPW/config/globals.php');
include_once($BASE_PATH . 'src/Controller/filmesDao.php');
include $BASE_PATH . 'src/View/header.php';

try {
    $conn = new PDO("mysql:host=localhost;dbname=cineestrela", "root", "");
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
    echo "Erro de conexão: " . $e->getMessage();
    exit;
}
$filmesDao = new filmesDao($conn);
$nome = isset($_GET['Nome']) ? trim($_GET['Nome']) : '';


$filme = $filmesDao->findByName($nome);

?>

<main>
<div class="Catalogo">
    <div class="sliderFilmes" id="slider1">
        <div class="slideFilmes">
            <div class="cards" id="cards">   
            <?php 
                    $filmeItem = array($nome);
                  ?>  
                <div class="row">
                    <!-- Iterando sobre cada filme dentro da linha -->
                    <?php foreach ($filmeItem as $filme): ?>
                        <div class="card">
                            <div class="fundo">
                                <img src="<?= $BASE_URL ?>imagens/<?= $filmeItem->getCapa() ?>" alt="Imagem do filme" class="img-filme">
                                <div class="titu2"><?= htmlspecialchars($filmeItem->getNome()) ?></div>
                                <div class="informacoes-filme">
                                    <div class="classificacao"><?= htmlspecialchars($filmeItem->getClassi_Etaria()) ?></div>
                                    <div class="genero"><?= htmlspecialchars($filmeItem->getGenero()) ?></div>
                                </div>
                                <div class="duracao"><?= htmlspecialchars($filmeItem->getDuracao()) ?> m</div>
                            </div>
                        </div>
                    <?php endforeach; ?>
                </div>
            </div>
        </div>
    </div>
    <p>Nenhum filme encontrado com o termo "<?= htmlspecialchars($nome) ?>".</p>
</div>
</div>
<div class="confirmacao">
    <p>Tem certeza de que deseja excluir este filme?</p>
    <form method="POST" action="excluirFilme.php">
        <input type="hidden" name="nome" value="<?= htmlspecialchars($nome) ?>">
        <button type="submit" class="confirm-button">Sim</button>
        <a href="catalogo.php" class="confirm-button">Não</a>
    </form>
</div>
</main>
