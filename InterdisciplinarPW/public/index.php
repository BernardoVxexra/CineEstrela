
<?php
$pageCSS = ["Menu5.css"];
$pageJS = ["header.js"];
$pageJS = ["index.js"];
require_once($_SERVER['DOCUMENT_ROOT'] . '/InterdisciplinarPW/config/globals.php');
include $BASE_PATH . 'src/View/header.php';

include_once($BASE_PATH . 'src/Classes/Filme.php');
include_once($BASE_PATH . 'src/Controller/filmesDao.php');


// Criando a conexão com o banco
try {
    $conn = new PDO("mysql:host=localhost;dbname=cineestrela", "root", "");
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
    echo "Erro de conexão: " . $e->getMessage();
    exit;
}

// Criando a instância de filmesDao
$filmesDao = new filmesDao($conn);

// Usando o método findAll para obter todos os filmes
$filmes = $filmesDao->getFilmesAleatorios();

?>
<main>
  <div class="tudo">
    <div class="titu">
      <h1>Em Cartaz</h1>
    </div>

    <div class="Catalogo">
    <?php if(count($filmes) != 0): ?>
        <div class="sliderFilmes" id="slider1">
            <div class="slideFilmes">
                <div class="cards" id="cards">
                    <?php 
                    // Divida os filmes em dois grupos de 5 filmes
                    $filmesParte1 = array_slice($filmes, 0, 5);
                    $filmesParte2 = array_slice($filmes, 5, 5);
                    ?>
                    
                    <!-- Primeira linha de 5 filmes -->
                    <div class="row">
                        <?php foreach($filmesParte1 as $filme): ?>
                            <div class="card">
                            <div class="fundo">
                                <img src="<?= $BASE_URL ?>imagens/<?= $filme->getCapa() ?>" alt="Imagem do filme" class="img-filme">
                                <div class="titu2"><?= $filme->getNome() ?></div>
                                <div class="informacoes-filme">
                                <div class="classificacao"><?= $filme->getClassi_Etaria() ?></div>
                                <div class="genero"><?= $filme->getGenero() ?></div>
                                </div>
                                <div class="duracao"> <?= $filme->getDuracao() ?> m</div>
                            </div>

                            <!-- Botões -->
                            <div class="acoes">
                                <button>Ver Detalhes</button>
                                <button>Alterar</button>
                                <button>Excluir</button>
                            </div>
                            </div>
                        <?php endforeach; ?>
                    </div>

                    <!-- Segunda linha de 5 filmes -->
                    <div class="row">
                        <?php foreach($filmesParte2 as $filme): ?>
                            <div class="card">
                            <div class="fundo">
                                <img src="<?= $BASE_URL ?>imagens/<?= $filme->getCapa() ?>" alt="Imagem do filme" class="img-filme">
                                <div class="titu2"><?= $filme->getNome() ?></div>
                                <div class="informacoes-filme">
                                <div class="classificacao"><?= $filme->getClassi_Etaria() ?></div>
                                <div class="genero"><?= $filme->getGenero() ?></div>
                                </div>
                                <div class="duracao"> <?= $filme->getDuracao() ?> m</div>
                            </div>

                            <!-- Botões -->
                            <div class="acoes">
                                <button>Ver Detalhes</button>
                                <button>Alterar</button>
                                <button>Excluir</button>
                            </div>
                            </div>
                        <?php endforeach; ?>
                    </div>

                </div>
            </div>
        </div>
    <?php else: ?>
        <p>Não há filmes disponíveis no momento.</p>
    <?php endif; ?>
</div>

  </div>
</main>


</body>
</html>
