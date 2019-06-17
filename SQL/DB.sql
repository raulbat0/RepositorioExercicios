-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           5.7.23-log - MySQL Community Server (GPL)
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Copiando estrutura do banco de dados para mydb
CREATE DATABASE IF NOT EXISTS `mydb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mydb`;

-- Copiando estrutura para tabela mydb.empresa
CREATE TABLE IF NOT EXISTS `empresa` (
  `id_empresa` smallint(7) NOT NULL,
  `nome_empresa` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_empresa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela mydb.empresa: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
INSERT INTO `empresa` (`id_empresa`, `nome_empresa`) VALUES
	(1, 'Empresa de Testes 1'),
	(2, 'Empresa de Testes 2'),
	(3, 'Empresa de Testes 3'),
	(4, 'Empresa de Testes 4'),
	(5, 'Empresa de Testes 5');
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;

-- Copiando estrutura para tabela mydb.projeto
CREATE TABLE IF NOT EXISTS `projeto` (
  `id_projeto` smallint(7) NOT NULL AUTO_INCREMENT,
  `nome_projeto` varchar(255) NOT NULL,
  `id_empresa` smallint(7) DEFAULT NULL,
  `id_status` smallint(7) DEFAULT NULL,
  `data_ativacao` date NOT NULL,
  `data_desativacao` date NOT NULL,
  PRIMARY KEY (`id_projeto`),
  KEY `statusProjeto_idx` (`id_status`),
  KEY `empresaProjeto_idx` (`id_empresa`),
  CONSTRAINT `empresaProjeto` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id_empresa`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `statusProjeto` FOREIGN KEY (`id_status`) REFERENCES `status` (`id_status`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela mydb.projeto: ~8 rows (aproximadamente)
/*!40000 ALTER TABLE `projeto` DISABLE KEYS */;
INSERT INTO `projeto` (`id_projeto`, `nome_projeto`, `id_empresa`, `id_status`, `data_ativacao`, `data_desativacao`) VALUES
	(1, 'Projeto Teste 1', 1, 1, '2019-06-02', '2019-06-29'),
	(2, 'Projeto Teste 2', 1, 1, '2019-06-04', '2019-06-29'),
	(3, 'Projeto Teste 3', 1, 1, '2019-06-03', '2019-06-19'),
	(4, 'Projeto Teste 4', 1, 1, '2019-06-10', '2019-06-20'),
	(5, 'Projeto Teste 5', 1, 1, '2019-06-03', '2019-06-28'),
	(6, 'Projeto Teste 6', 1, 1, '2019-06-03', '2019-06-21'),
	(7, 'Projeto Teste 7', 1, 1, '2019-06-29', '2019-07-24'),
	(8, 'Projeto Teste 8', 2, 1, '2019-06-03', '2019-06-29'),
	(9, 'Projeto Teste 9', 1, 1, '2019-08-05', '2019-08-30'),
	(10, 'Projeto Teste 10', 1, 1, '2019-08-30', '2019-10-27');
/*!40000 ALTER TABLE `projeto` ENABLE KEYS */;

-- Copiando estrutura para tabela mydb.status
CREATE TABLE IF NOT EXISTS `status` (
  `id_status` smallint(7) NOT NULL,
  `nome_status` varchar(32) NOT NULL,
  PRIMARY KEY (`id_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela mydb.status: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` (`id_status`, `nome_status`) VALUES
	(1, 'Ativo'),
	(2, 'Concluído'),
	(3, 'Cancelado'),
	(4, 'Suspenso');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;

-- Copiando estrutura para tabela mydb.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` smallint(7) NOT NULL AUTO_INCREMENT,
  `nome_usuario` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `apelido` varchar(32) NOT NULL,
  `nascimento` date NOT NULL,
  `status` tinyint(1) DEFAULT '0',
  `id_empresa` smallint(7) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `empresaUsuario_idx` (`id_empresa`),
  CONSTRAINT `empresaUsuario` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id_empresa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela mydb.usuario: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id_usuario`, `nome_usuario`, `email`, `apelido`, `nascimento`, `status`, `id_empresa`) VALUES
	(1, 'Usuário de Testes 1', 'usuario@teste.com', 'usuario1', '1990-07-07', 1, 1),
	(2, 'Usuario Testes 2', 'usuario@teste2.com', 'usuario2', '1992-07-06', 1, 1),
	(3, 'Usuário de Testes 3', 'usuario@teste3.com', 'usuario3', '1952-03-02', 1, 3),
	(4, 'Usuario de Testes 4', 'teste@teste', 'Usuario4', '2010-03-04', 1, 1),
	(5, 'Usuario de Teste 5', 'teste@teste.com', 'usuario5', '1990-07-07', 1, 5);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

-- Copiando estrutura para tabela mydb.usuarioprojeto
CREATE TABLE IF NOT EXISTS `usuarioprojeto` (
  `idusuarioProjeto` smallint(7) NOT NULL AUTO_INCREMENT,
  `id_projeto` smallint(7) DEFAULT NULL,
  `id_usuario` smallint(7) DEFAULT NULL,
  PRIMARY KEY (`idusuarioProjeto`),
  KEY `projetoUsuario_idx` (`id_projeto`),
  KEY `usuarioProjeto_idx` (`id_usuario`),
  CONSTRAINT `projetoUsuario` FOREIGN KEY (`id_projeto`) REFERENCES `projeto` (`id_projeto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `usuarioProjeto` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela mydb.usuarioprojeto: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `usuarioprojeto` DISABLE KEYS */;
INSERT INTO `usuarioprojeto` (`idusuarioProjeto`, `id_projeto`, `id_usuario`) VALUES
	(1, 8, 1),
	(2, 1, 3),
	(3, 1, 4),
	(4, 5, 1);
/*!40000 ALTER TABLE `usuarioprojeto` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
