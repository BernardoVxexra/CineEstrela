-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 21/11/2024 às 23:42
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `cineestrela`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `cargo`
--
create database `cineestrela`;
use `cineestrela`;

CREATE TABLE `cargo` (
  `Id_Cargo` int(11) NOT NULL,
  `Des_Cargo` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `cargo`
--

INSERT INTO `cargo` (`Id_Cargo`, `Des_Cargo`) VALUES
(1, 'sadsaddddddddddddddddddd');

-- --------------------------------------------------------

--
-- Estrutura para tabela `cinema`
--

CREATE TABLE `cinema` (
  `Id_Cinema` int(11) NOT NULL,
  `Endereco` varchar(50) NOT NULL,
  `Hora_de_Abrir` time NOT NULL,
  `Hora_de_Fechar` time NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `cinema`
--

INSERT INTO `cinema` (`Id_Cinema`, `Endereco`, `Hora_de_Abrir`, `Hora_de_Fechar`) VALUES
(1, 'sadsad', '00:21:30', '13:00:30');

-- --------------------------------------------------------

--
-- Estrutura para tabela `cliente`
--

CREATE TABLE `cliente` (
  `Id_Cliente` int(11) NOT NULL,
  `Nome` varchar(40) NOT NULL,
  `dt_nasc` date NOT NULL,
  `Email` varchar(40) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `cliente`
--

INSERT INTO `cliente` (`Id_Cliente`, `Nome`, `dt_nasc`, `Email`) VALUES
(1, 'cassio', '2006-02-26', 'cassio@gmail.com'),
(2, 'David', '2007-10-03', 'david@gmail.com'),
(3, 'Gustavo', '2007-10-10', 'gustavo@gmail.com'),
(4, 'Bernado', '2007-06-23', 'bernado@gmail.com'),
(5, 'guilherme', '2007-02-02', 'guilherme@gmail.com'),
(6, 'gabriel', '2006-04-06', 'gabriel@gmail.com'),
(7, 'vinnicius', '2008-09-18', 'vinnicius@gmail.com'),
(8, 'jonatas', '2004-05-11', 'jonatas@gmail.com'),
(9, 'ana', '2001-07-16', 'ana@gmail.com'),
(10, 'vinnicius', '1999-12-31', 'vinnicius2@gmail.com');

-- --------------------------------------------------------

--
-- Estrutura para tabela `filme`
--

CREATE TABLE `filme` (
  `Id_Filme` int(11) NOT NULL,
  `Nome` varchar(300) NOT NULL,
  `Classi_Etaria` varchar(10) NOT NULL,
  `Descricao` varchar(1000) NOT NULL,
  `Genero` varchar(100) NOT NULL,
  `Duracao` time NOT NULL,
  `capa` varchar(150) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `filme`
--

INSERT INTO `filme` (`Id_Filme`, `Nome`, `Classi_Etaria`, `Descricao`, `Genero`, `Duracao`, `capa`) VALUES
(1, 'Robô Selvagem', 'L', 'A épica aventura acompanha a jornada de uma robô – a unidade ROZZUM 7134, “Roz” – que naufraga em uma ilha desabitada e precisa aprender a se adaptar ao ambiente hostil, construindo pouco a pouco relacionamentos com os animais nativos, e até adotando um filhotinho de ganso órfão.', 'Animação', '01:40:00', 'thewildrobot.jpg'),
(2, 'A Forja: O poder da transformação', 'L', 'Um ano depois de terminar o ensino médio e sem planos para o futuro, Isaiah é desafiado por sua mãe solteira e um empresário de sucesso a começar um relacionamento com Deus para traçar um rumo melhor para sua vida.', 'Drama', '02:00:00', 'capa-the-forge.jpg'),
(3, 'Deadpool e Wolverine', '18', 'Wolverine está se recuperando quando cruza seu caminho com Deadpool. Juntos, eles formam uma equipe e enfrentam um inimigo em comum.', 'ação', '02:05:00', 'Deadpool_Wolverine.jpg');

-- --------------------------------------------------------

--
-- Estrutura para tabela `funcionario`
--

CREATE TABLE `funcionario` (
  `Id_Func` int(11) NOT NULL,
  `Nome` varchar(30) NOT NULL,
  `Telefone` varchar(20) NOT NULL,
  `Horario_inicio` time NOT NULL,
  `Horario_Fim` time NOT NULL,
  `Id_Cinema` int(11) NOT NULL,
  `Id_Cargo` int(11) NOT NULL,
  `Usuario` varchar(30) NOT NULL,
  `Senha` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `funcionario`
--

INSERT INTO `funcionario` (`Id_Func`, `Nome`, `Telefone`, `Horario_inicio`, `Horario_Fim`, `Id_Cinema`, `Id_Cargo`, `Usuario`, `Senha`) VALUES
(1, 'david', '1111111111', '00:00:00', '08:00:00', 2, 2, 'a', '321'),
(2, 'Cassio', '(11)9657-8782', '00:00:00', '00:00:00', 1, 1, 'A', '123'),
(3, 'ds', '(11)1112-3232', '00:00:00', '00:00:00', 1, 1, 's', '1'),
(4, 'gustavo', '(21)4343-2233', '08:00:00', '18:00:00', 2, 1, 'guto', 'guto'),
(5, 'Bernardo', '(12)4566-5132', '07:00:00', '17:00:00', 1, 2, 'berna', 'ber'),
(6, 'vinnicius', '(23)1241-4151', '07:00:00', '17:00:00', 2, 2, 'vini', 'vini'),
(7, 'guilherme', '(22)3141-1241', '07:00:00', '17:00:00', 1, 2, 'guLhErme', 'gui'),
(8, 'gabriel', '(21)4141-2412', '06:00:00', '16:00:00', 1, 2, 'gab', 'gab'),
(9, 'gustavo', '(12)3141-2515', '06:00:00', '16:00:00', 2, 1, 'mohamed', '141'),
(10, 'jonatas', '(12)3123-1231', '06:00:00', '16:00:00', 1, 1, 'jhow', '124');

-- --------------------------------------------------------

--
-- Estrutura para tabela `ingresso`
--

CREATE TABLE `ingresso` (
  `Id_Filme` int(11) NOT NULL,
  `Id_Cliente` int(11) NOT NULL,
  `Id_ingresso` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `inter_sa_fil`
--

CREATE TABLE `inter_sa_fil` (
  `Id_Sala` int(11) NOT NULL,
  `Id_FIlme` int(11) NOT NULL,
  `Horario_Inicio` time NOT NULL,
  `Horario_Fim` time NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `sala`
--

CREATE TABLE `sala` (
  `Id_Sala` int(11) NOT NULL,
  `Numero` int(11) NOT NULL,
  `Id_Cinema` int(11) NOT NULL,
  `Id_Tipo_Sala` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `tipo_sala`
--

CREATE TABLE `tipo_sala` (
  `Id_Tipo_Sala` int(11) NOT NULL,
  `Tipo_Som` varchar(30) NOT NULL,
  `Tipo_Poltrona` varchar(30) NOT NULL,
  `Tipo_Tela` varchar(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `cargo`
--
ALTER TABLE `cargo`
  ADD PRIMARY KEY (`Id_Cargo`);

--
-- Índices de tabela `cinema`
--
ALTER TABLE `cinema`
  ADD PRIMARY KEY (`Id_Cinema`);

--
-- Índices de tabela `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`Id_Cliente`);

--
-- Índices de tabela `filme`
--
ALTER TABLE `filme`
  ADD PRIMARY KEY (`Id_Filme`);

--
-- Índices de tabela `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`Id_Func`);

--
-- Índices de tabela `ingresso`
--
ALTER TABLE `ingresso`
  ADD PRIMARY KEY (`Id_ingresso`);

--
-- Índices de tabela `sala`
--
ALTER TABLE `sala`
  ADD PRIMARY KEY (`Id_Sala`);

--
-- Índices de tabela `tipo_sala`
--
ALTER TABLE `tipo_sala`
  ADD PRIMARY KEY (`Id_Tipo_Sala`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `filme`
--
ALTER TABLE `filme`
  MODIFY `Id_Filme` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
