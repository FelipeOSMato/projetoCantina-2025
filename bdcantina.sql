-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 02/10/2026 às 14:02
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
-- Banco de dados: `bdcantina`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `tbfuncionario`
--

CREATE TABLE `tbfuncionario` (
  `idFuncionario` int(11) NOT NULL,
  `nomeFuncionario` varchar(200) DEFAULT NULL,
  `dataNascFuncionario` date NOT NULL,
  `cpfFuncionario` char(12) DEFAULT NULL,
  `statusFuncionario` varchar(30) NOT NULL,
  `emailFuncionario` varchar(200) DEFAULT NULL,
  `foneFunc` char(12) NOT NULL,
  `idHierarquia` int(11) DEFAULT NULL,
  `generoFunc` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `tbfuncionario`
--

INSERT INTO `tbfuncionario` (`idFuncionario`, `nomeFuncionario`, `dataNascFuncionario`, `cpfFuncionario`, `statusFuncionario`, `emailFuncionario`, `foneFunc`, `idHierarquia`, `generoFunc`) VALUES
(1, 'Paulo Souza', '1985-09-07', '12345678910', 'Ativo', 'paulosouza123@gmail.com', '1191234-5678', 4, 'Masculino'),
(2, 'Felipe de Oliveira Silva', '2008-12-08', '525798679-09', 'Ativo', 'felipeosilva0812@gmail.com', '1192923-4433', 1, 'Masculino'),
(3, 'Marcelo Forte', '1958-12-21', '434555888-90', 'Ativo', 'Marcelo_Forte121@hotmail.com', '1198828-3222', 3, 'Masculino'),
(4, 'Silvana Souza', '1987-11-07', '234876098-12', 'Inativo', 'SilvanaNacao@gmail.com', '1198765-3432', 5, 'Feminino');

-- --------------------------------------------------------

--
-- Estrutura para tabela `tbhierarquia`
--

CREATE TABLE `tbhierarquia` (
  `idHierarquia` int(11) NOT NULL,
  `tituloHierarquia` varchar(200) NOT NULL,
  `salarioHierarquia` decimal(9,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `tbhierarquia`
--

INSERT INTO `tbhierarquia` (`idHierarquia`, `tituloHierarquia`, `salarioHierarquia`) VALUES
(1, 'Gerente', 4000.00),
(2, 'Estagiário', 800.00),
(3, 'Proprietário', 6000.00),
(4, 'Atendente', 1800.00),
(5, 'Cozinheiro', 2000.00);

-- --------------------------------------------------------

--
-- Estrutura para tabela `tbpagamentoproduto`
--

CREATE TABLE `tbpagamentoproduto` (
  `idPagamentoProduto` int(11) NOT NULL,
  `quantidadeItensVenda` int(11) NOT NULL,
  `idProduto` int(11) DEFAULT NULL,
  `valorPagamento` decimal(9,2) DEFAULT NULL,
  `idTipoPagamento` int(11) DEFAULT NULL,
  `idUsuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `tbpagamentoproduto`
--

INSERT INTO `tbpagamentoproduto` (`idPagamentoProduto`, `quantidadeItensVenda`, `idProduto`, `valorPagamento`, `idTipoPagamento`, `idUsuario`) VALUES
(4, 2, 14, 5.00, 1, 6),
(8, 3, 17, 16.50, 1, 6),
(9, 1, 17, 5.50, 3, 6),
(10, 2, 17, 11.00, 2, 7),
(11, 2, 21, 40.00, 3, 8),
(12, 2, 14, 5.00, 1, 6),
(13, 2, 14, 5.00, 1, 6);

-- --------------------------------------------------------

--
-- Estrutura para tabela `tbproduto`
--

CREATE TABLE `tbproduto` (
  `idProduto` int(11) NOT NULL,
  `nomeProduto` varchar(50) NOT NULL,
  `descProduto` varchar(200) NOT NULL,
  `valorProduto` decimal(9,2) NOT NULL,
  `idTipoProduto` int(11) DEFAULT NULL,
  `quantidadeEstoque` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `tbproduto`
--

INSERT INTO `tbproduto` (`idProduto`, `nomeProduto`, `descProduto`, `valorProduto`, `idTipoProduto`, `quantidadeEstoque`) VALUES
(13, 'Coxinha de Frango', 'Coxinha tradicional com massa leve', 6.50, 3, 40),
(14, 'Brigadeiro', 'Doce de chocolate enrolado na mão', 2.50, 1, 116),
(15, 'Beirute', 'Sanduíche completo com queijo, presunto e salada', 18.00, 2, -1),
(16, 'Pastel de Carne', 'Pastel frito recheado com carne moída temperada', 7.00, 3, 30),
(17, 'Pudim', 'Pudim de leite condensado tradicional', 5.50, 1, 4),
(18, 'Lasanha Bolonhesa', 'Lasanha artesanal ao molho bolonhesa', 22.00, 2, -9),
(19, 'Quibe', 'Quibe frito crocante', 5.00, 3, 25),
(20, 'Cookies de Chocolate', 'Cookies artesanais com gotas de chocolate', 4.00, 1, 60),
(21, 'Strogonoff', 'Strogonoff de frango com arroz e batata palha', 20.00, 2, 10),
(22, 'Enroladinho de Salsicha', 'Salgado assado recheado com salsicha', 4.50, 3, 35),
(23, 'Bolo de Cenoura', 'Bolo de cenoura com cobertura de chocolate', 3.50, 1, 45),
(24, 'Macarrão ao Alho e Óleo', 'Porção individual de macarrão alho e óleo', 14.00, 2, 20);

-- --------------------------------------------------------

--
-- Estrutura para tabela `tbtipopagamento`
--

CREATE TABLE `tbtipopagamento` (
  `idTipoPagamento` int(11) NOT NULL,
  `tipoPagamento` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `tbtipopagamento`
--

INSERT INTO `tbtipopagamento` (`idTipoPagamento`, `tipoPagamento`) VALUES
(1, 'Pix'),
(2, 'Crédito'),
(3, 'Débito'),
(4, 'Dinheiro');

-- --------------------------------------------------------

--
-- Estrutura para tabela `tbtipoproduto`
--

CREATE TABLE `tbtipoproduto` (
  `idTipoProduto` int(11) NOT NULL,
  `descTipoProduto` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `tbtipoproduto`
--

INSERT INTO `tbtipoproduto` (`idTipoProduto`, `descTipoProduto`) VALUES
(1, 'Doces'),
(2, 'Pratos'),
(3, 'Salgados');

-- --------------------------------------------------------

--
-- Estrutura para tabela `tbusuario`
--

CREATE TABLE `tbusuario` (
  `idUsuario` int(11) NOT NULL,
  `nomeUsuario` varchar(200) NOT NULL,
  `senhaUsuario` varchar(200) NOT NULL,
  `emailUsuario` varchar(200) NOT NULL,
  `foneUsuario` char(12) NOT NULL,
  `cepUsuario` char(9) DEFAULT NULL,
  `cpfUsuario` char(12) DEFAULT NULL,
  `statusUsuario` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `tbusuario`
--

INSERT INTO `tbusuario` (`idUsuario`, `nomeUsuario`, `senhaUsuario`, `emailUsuario`, `foneUsuario`, `cepUsuario`, `cpfUsuario`, `statusUsuario`) VALUES
(6, 'José Figueiredo', '12345678', 'jose.fi1232@gmail.com', '1192928-2121', '12334-011', '112233445-01', 'Ativo'),
(7, 'Paulo Aldemar', '11111111', 'aldeminhoB@gmail.com', '1192223-3322', '08877-221', '122233344-00', 'Inativo'),
(8, 'João Rochas', '12345678', 'pedra@gmail.com', '1190808-2345', '09879-121', '111222333-11', 'Inativo');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `tbfuncionario`
--
ALTER TABLE `tbfuncionario`
  ADD PRIMARY KEY (`idFuncionario`),
  ADD KEY `fk_hierarquia` (`idHierarquia`);

--
-- Índices de tabela `tbhierarquia`
--
ALTER TABLE `tbhierarquia`
  ADD PRIMARY KEY (`idHierarquia`);

--
-- Índices de tabela `tbpagamentoproduto`
--
ALTER TABLE `tbpagamentoproduto`
  ADD PRIMARY KEY (`idPagamentoProduto`),
  ADD KEY `idProduto` (`idProduto`),
  ADD KEY `fk_pagamento_tipo` (`idTipoPagamento`),
  ADD KEY `fk_pagamento_usuario` (`idUsuario`);

--
-- Índices de tabela `tbproduto`
--
ALTER TABLE `tbproduto`
  ADD PRIMARY KEY (`idProduto`),
  ADD KEY `fk_produto_tipoproduto` (`idTipoProduto`);

--
-- Índices de tabela `tbtipopagamento`
--
ALTER TABLE `tbtipopagamento`
  ADD PRIMARY KEY (`idTipoPagamento`);

--
-- Índices de tabela `tbtipoproduto`
--
ALTER TABLE `tbtipoproduto`
  ADD PRIMARY KEY (`idTipoProduto`);

--
-- Índices de tabela `tbusuario`
--
ALTER TABLE `tbusuario`
  ADD PRIMARY KEY (`idUsuario`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `tbfuncionario`
--
ALTER TABLE `tbfuncionario`
  MODIFY `idFuncionario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de tabela `tbhierarquia`
--
ALTER TABLE `tbhierarquia`
  MODIFY `idHierarquia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de tabela `tbpagamentoproduto`
--
ALTER TABLE `tbpagamentoproduto`
  MODIFY `idPagamentoProduto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de tabela `tbproduto`
--
ALTER TABLE `tbproduto`
  MODIFY `idProduto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de tabela `tbtipopagamento`
--
ALTER TABLE `tbtipopagamento`
  MODIFY `idTipoPagamento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de tabela `tbtipoproduto`
--
ALTER TABLE `tbtipoproduto`
  MODIFY `idTipoProduto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `tbusuario`
--
ALTER TABLE `tbusuario`
  MODIFY `idUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `tbfuncionario`
--
ALTER TABLE `tbfuncionario`
  ADD CONSTRAINT `fk_hierarquia` FOREIGN KEY (`idHierarquia`) REFERENCES `tbhierarquia` (`idHierarquia`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Restrições para tabelas `tbpagamentoproduto`
--
ALTER TABLE `tbpagamentoproduto`
  ADD CONSTRAINT `fk_pagamento_tipo` FOREIGN KEY (`idTipoPagamento`) REFERENCES `tbtipopagamento` (`idTipoPagamento`),
  ADD CONSTRAINT `fk_pagamento_usuario` FOREIGN KEY (`idUsuario`) REFERENCES `tbusuario` (`idUsuario`),
  ADD CONSTRAINT `tbpagamentoproduto_ibfk_2` FOREIGN KEY (`idProduto`) REFERENCES `tbproduto` (`idProduto`);

--
-- Restrições para tabelas `tbproduto`
--
ALTER TABLE `tbproduto`
  ADD CONSTRAINT `fk_produto_tipoproduto` FOREIGN KEY (`idTipoProduto`) REFERENCES `tbtipoproduto` (`idTipoProduto`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
