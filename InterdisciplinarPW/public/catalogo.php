<?php
$pageCSS = ["Catalogo3.css"];
$pageJS = ["catalogo1.js"];

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
$filmesDao = new filmesDao($conn);
// Verifica se há um termo de busca
$searchQuery = isset($_GET['query']) ? $_GET['query'] : null;

// Busca os filmes com base no termo ou exibe todos
if ($searchQuery) {
    $filmes = $filmesDao->findByName($searchQuery);
} else {
    $filmes = $filmesDao->findAll();
}

$filmesDivididos = array_chunk($filmes, 5); // Verifica se há resultados
if (empty($filmesDivididos)) {
    echo '<p>Nenhum filme disponível no momento.</p>';
}


?>
<main>
  <div class="tudo">
  <div class="titu">
  <h1>Filmes</h1>
  <div class="search-bar">
    <form method="GET" action="">
        <input type="text" name="query" placeholder="Pesquisar filmes..." class="search-input" value="<?= htmlspecialchars($searchQuery) ?>">  
   
    <button type="submit" class="search-button">🔍</button>
</form>
</div>

</div>


    <div class="Catalogo">
      <?php if (count($filmes) != 0): ?>
        <div class="sliderFilmes" id="slider1">
          <div class="slideFilmes">
            <div class="cards" id="cards">
              <?php
              // Dividindo os filmes em linhas de 5 registros cada
              $filmesDivididos = array_chunk($filmes, 5);
              ?>
              
              <!-- Iterando sobre cada linha -->
              <?php foreach ($filmesDivididos as $linhaFilmes): ?>
                
                <div class="row">
                  <!-- Iterando sobre cada filme dentro da linha -->
                  <?php foreach ($linhaFilmes as $filme): ?>
                    
                    <div class="card">
                      <div class="fundo">
                        <img src="<?= $BASE_URL ?>imagens/<?= $filme->getCapa() ?>" alt="Imagem do filme" class="img-filme">
                        <div class="titu2"><?= $filme->getNome() ?></div>
                        <div class="informacoes-filme">
                          <div class="classificacao"><?= $filme->getClassi_Etaria() ?></div>
                          <div class="genero"><?= $filme->getGenero() ?></div>
                        </div>
                        <div class="duracao"><?= $filme->getDuracao() ?> m</div>
                      </div>

                      <!-- Botões -->
                      <div class="acoes">
                        <button>Ver Detalhes</button>
                        <button>Alterar</button>
                        <button onclick="openModal('<?= $filme->getNome() ?>')">Excluir</button>
                            <!-- Modal de Confirmação -->
                    
                    </div>
                  </div>
                  <div id="modalConfirmar" class="modal">
                           <div class="modal-content">
                            <p>Tem certeza que deseja excluir este filme?</p>
                            <div class="modal-actions">
                            <button id="btnConfirmar" class="confirm-button">Sim</button>
                            <button id="btnCancelar" class="confirm-button">Cancelar</button>
                      </div>
                        </div>
                  </div>
                  <?php endforeach; ?>
                  
                </div>
              <?php endforeach; ?>
            </div>
          </div>
        </div>
        <?php else: ?>
            <p>Nenhum filme encontrado com o termo "<?= htmlspecialchars($searchQuery) ?>".</p>
<?php endif; ?>

    </div>


  </div>

</main>



</body>
</html>