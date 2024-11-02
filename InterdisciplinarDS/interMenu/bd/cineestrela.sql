-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 19/09/2024 às 05:34
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

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
CREATE DATABASE cineestrela;

USE cineestrela;

CREATE TABLE `cargo` (
  `Id_Cargo` int(11) NOT NULL,
  `Des_Cargo` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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

-- --------------------------------------------------------

--
-- Estrutura para tabela `filme`
--

CREATE TABLE `filme` (
  `Id_Filme` int(11) NOT NULL,
  `Nome` varchar(50) NOT NULL,
  `Classi_Etaria` varchar(10) NOT NULL,
  `Descricao` varchar(200) NOT NULL,
  `Genero` varchar(20) NOT NULL,
  `Duracao` time NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `funcionario`
--

CREATE TABLE `funcionario` (
  `Id_Func` int(11) NOT NULL,
  `Nome` varchar(30) NOT NULL,
  `Telefone` varchar(11) NOT NULL,
  `Horario_inicio` time NOT NULL,
  `Horario_Fim` time NOT NULL,
  `Id_Cinema` int(11) NOT NULL,
  `Id_Cargo` int(11) NOT NULL,
  `Usuario` varchar(30) NOT NULL,
  `Senha` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
