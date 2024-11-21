<?php

class Filme {
    private $Id_Filme;
    private $Nome;
    private $Classi_Etaria;
    private $Descricao;
    private $Genero;
    private $Duracao;
    private $Capa;

  
    public function getId_Filme() {
        return $this->Id_Filme;
    }

    public function setId_Filme($id) {
        $this->Id_Filme = $id;
    }

    public function getNome() {
        return $this->Nome;
    }

    public function setNome($nome) {
        $this->Nome = $nome;
    }

    public function getClassi_Etaria() {
        return $this->Classi_Etaria;
    }

    public function setClassi_Etaria($classificacao) {
        $this->Classi_Etaria = $classificacao;
    }

    public function getDescricao() {
        return $this->Descricao;
    }

    public function setDescricao($descricao) {
        $this->Descricao = $descricao;
    }

    public function getGenero() {
        return $this->Genero;
    }

    public function setGenero($genero) {
        $this->Genero = $genero;
    }

    public function getDuracao() {
        return $this->Duracao;
    }

    public function setDuracao($duracao) {
        $this->Duracao = $duracao;
    }

    public function getCapa() {
        return $this->Capa;
    }

    public function setCapa($capa) {
        $this->Capa = $capa;
    }
}


interface FilmeDAOInterface {
    public function buildFilme($data); // Criar filme
    public function create(Filme $filme); // Inserir filme
    public function update(Filme $filme); // Atualizar filme
    public function findAll(); // Listar todos os filmes
    public function findByName($nome); // Buscar filme por ID
    public function deleteByName($nome); // Excluir filme
    public function getFilmesAleatorios($limite); // Buscar filmes aleatórios
}

