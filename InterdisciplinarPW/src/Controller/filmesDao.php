<?php

require_once($_SERVER['DOCUMENT_ROOT'] . '/InterdisciplinarPW/config/globals.php');

require_once($BASE_PATH . 'src/Classes/Filme.php');

class filmesDao implements FilmeDAOInterface {
    private $conn;

    // Construtor - Recebe a conexão com o banco de dados
    public function __construct($conn) {

        if ($conn instanceof PDO) {
            $this->conn = $conn;
        } else {
            throw new Exception("Conexão com o banco de dados não é uma instância de PDO.");
        }
    }
    

    // Método para construir um objeto Filme
    public function buildFilme($data) {
        $filme = new Filme();
    
        $filme->setId_Filme($data["Id_Filme"]);
        $filme->setNome($data["Nome"]);
        $filme->setClassi_Etaria($data["Classi_Etaria"]);
        $filme->setDescricao($data["Descricao"]);
        $filme->setGenero($data["Genero"]);
        $filme->setDuracao($data["Duracao"]);
        $filme->setCapa($data["capa"]);
        
        return $filme;
    }
    

    // Método para inserir um filme no banco
    public function create(filme $filme) {
        $sql = "INSERT INTO filme (Nome, Classi_Etaria, Descricao, Genero, Duracao, capa) 
                VALUES (:Nome, :Classi_Etaria, :Descricao, :Genero, :Duracao, :capa)";
        $stmt = $this->conn->prepare($sql);

        $stmt->bindParam(':Nome', $filme->getNome());
        $stmt->bindParam(':Classi_Etaria', $filme->getClassi_Etaria());
        $stmt->bindParam(':Descricao', $filme->getDescricao());
        $stmt->bindParam(':Genero', $filme->getGenero());
        $stmt->bindParam(':Duracao', $filme->getDuracao());
        $stmt->bindParam(':capa', $filme->getCapa());

        return $stmt->execute();
    }

    // Método para buscar um filme por ID
    public function findByName($name) {
        $sql = "SELECT * FROM filme WHERE Nome LIKE :Nome";
        $stmt = $this->conn->prepare($sql);
        $searchTerm = "%" . $name . "%"; // Adiciona os curingas para busca parcial
        $stmt->bindParam(':Nome', $searchTerm);
        $stmt->execute();
    
        $rows = $stmt->fetchAll(PDO::FETCH_ASSOC);
        $filmes = [];
    
        foreach ($rows as $row) {
            $filmes[] = $this->buildFilme($row); // Usa o método buildFilme para criar os objetos
        }
    
        return $filmes;
    }
    

  // Método para listar todos os filmes
  public function findAll() {
    $filmes = [];
    $stmt = $this->conn->prepare("SELECT * FROM filme");
    $stmt->execute();

    if ($stmt->rowCount() > 0) {
        $filmesArray = $stmt->fetchAll(PDO::FETCH_ASSOC);
        
        // Agrupar filmes e suas capas
        $filmesMap = [];
        
        foreach ($filmesArray as $filmeData) {
            $filmeId = $filmeData['Id_Filme'];
        
            if (!isset($filmesMap[$filmeId])) {
                // Inicializa o array de capas, incluindo o nome da capa se existir
                $filmeData['capas'] = [];
        
                // Se a capa existe e não é um array, transforme em um array
                if ($filmeData['capa']) {
                    // Verifica se a capa é uma string e a transforma em array
                    if (!is_array($filmeData['capa'])) {
                        $filmeData['capas'][] = $filmeData['capa'];
                    } else {
                        $filmeData['capas'] = $filmeData['capa'];
                    }
                }
        
                // Cria o filme e armazena no map
                $filmesMap[$filmeId] = $this->buildFilme($filmeData);
            } else {
                // Se o filme já existir, apenas adiciona a nova capa
                if ($filmeData['capa']) {
                    // Verifica se a capa é uma string e a transforma em array
                    if (!is_array($filmeData['capa'])) {
                        $filmesMap[$filmeId]->capas[] = $filmeData['capa'];
                    } else {
                        $filmesMap[$filmeId]->capas = $filmeData['capa'];
                    }
                }
            }
        }

        $filmes = array_values($filmesMap); // Converte de volta para um array numérico
    }

    return $filmes;
}



    // Método para atualizar um filme
    public function update(Filme $filme) {
        $sql = "UPDATE filme SET 
                Nome = :Nome,
                Classi_Etaria = :Classi_Etaria,
                Descricao = :Descricao,
                Genero = :Genero,
                Duracao = :Duracao,
                capa = :capa
                WHERE Id_Filme = :Id_Filme";
        $stmt = $this->conn->prepare($sql);

        $stmt->bindParam(':Id_Filme', $filme->getId_Filme());
        $stmt->bindParam(':Nome', $filme->getNome());
        $stmt->bindParam(':Classi_Etaria', $filme->getClassi_Etaria());
        $stmt->bindParam(':Descricao', $filme->getDescricao());
        $stmt->bindParam(':Genero', $filme->getGenero());
        $stmt->bindParam(':Duracao', $filme->getDuracao());
        $stmt->bindParam(':capa', $filme->getCapa());

        return $stmt->execute();
    }

    // Método para excluir um filme
    public function deleteByName($nome) {
        $stmt = $this->conn->prepare("DELETE FROM filme WHERE nome = :nome");
        $stmt->bindParam(':nome', $nome, PDO::PARAM_STR);
        return $stmt->execute();
    }
    
    

    // Método para buscar filmes aleatórios
    public function getFilmesAleatorios($limite = 10) {
        $filmes = [];
        $stmt = $this->conn->prepare("SELECT * FROM filme ORDER BY RAND() LIMIT :limite");
        
        // Vinculando o parâmetro :limite com o valor real
        $stmt->bindValue(':limite', $limite, PDO::PARAM_INT);
        
        // Executando a consulta
        $stmt->execute();
        
        if ($stmt->rowCount() > 0) {
            $filmesArray = $stmt->fetchAll(PDO::FETCH_ASSOC);
            
            // Agrupar filmes e suas capas
            $filmesMap = [];
            
            foreach ($filmesArray as $filmeData) {
                $filmeId = $filmeData['Id_Filme'];
            
                if (!isset($filmesMap[$filmeId])) {
                    // Inicializa o array de capas, incluindo o nome da capa se existir
                    $filmeData['capas'] = [];
            
                    // Se a capa existe e não é um array, transforme em um array
                    if ($filmeData['capa']) {
                        // Verifica se a capa é uma string e a transforma em array
                        if (!is_array($filmeData['capa'])) {
                            $filmeData['capas'][] = $filmeData['capa'];
                        } else {
                            $filmeData['capas'] = $filmeData['capa'];
                        }
                    }
            
                    // Cria o filme e armazena no map
                    $filmesMap[$filmeId] = $this->buildFilme($filmeData);
                } else {
                    // Se o filme já existir, apenas adiciona a nova capa
                    if ($filmeData['capa']) {
                        // Verifica se a capa é uma string e a transforma em array
                        if (!is_array($filmeData['capa'])) {
                            $filmesMap[$filmeId]->capas[] = $filmeData['capa'];
                        } else {
                            $filmesMap[$filmeId]->capas = $filmeData['capa'];
                        }
                    }
                }
            }
        
            $filmes = array_values($filmesMap); // Converte de volta para um array numérico
        }
        
        return $filmes;
    }
    
}
