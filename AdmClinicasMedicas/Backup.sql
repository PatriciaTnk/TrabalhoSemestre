CREATE DATABASE  IF NOT EXISTS `clinicasmedica` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `clinicasmedica`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: clinicasmedica
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `consultas`
--

DROP TABLE IF EXISTS `consultas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consultas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `diaEhorario` datetime NOT NULL,
  `estado` varchar(45) NOT NULL,
  `medico` int NOT NULL,
  `paciente` int NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `unidade` int NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime NOT NULL,
  `visible` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consultas`
--

LOCK TABLES `consultas` WRITE;
/*!40000 ALTER TABLE `consultas` DISABLE KEYS */;
INSERT INTO `consultas` VALUES (1,'2023-05-15 00:00:00','Agendado',1,14,70.50,1,'2023-03-10 00:00:00','2023-03-10 00:00:00',1),(2,'2023-05-16 00:00:00','Agendado',1,15,70.50,1,'2023-03-10 00:00:00','2023-03-10 00:00:00',1),(3,'2023-05-15 00:00:00','Agendado',1,16,70.60,1,'2023-03-10 00:00:00','2023-03-10 00:00:00',1),(4,'2023-05-16 00:00:00','Agendado',1,17,70.60,1,'2023-03-10 00:00:00','2023-03-10 00:00:00',1),(5,'2023-05-15 00:00:00','Agendado',2,18,85.90,2,'2023-03-10 00:00:00','2023-03-10 00:00:00',1),(6,'2023-05-16 00:00:00','Agendado',2,19,85.90,2,'2023-03-10 00:00:00','2023-03-10 00:00:00',1),(7,'2023-05-15 00:00:00','Agendado',3,20,150.00,3,'2023-03-10 00:00:00','2023-03-10 00:00:00',1),(8,'2023-05-16 00:00:00','Agendado',4,21,94.30,4,'2023-03-10 00:00:00','2023-03-10 00:00:00',1),(9,'2023-05-15 00:00:00','Agendado',4,14,94.30,4,'2023-03-10 00:00:00','2023-03-10 00:00:00',1),(10,'2023-05-16 00:00:00','Agendado',4,15,94.30,4,'2023-03-10 00:00:00','2023-03-10 00:00:00',1),(11,'2023-05-16 00:00:00','Agendado',5,16,120.70,3,'2023-03-10 00:00:00','2023-03-10 00:00:00',1),(12,'2023-05-15 00:00:00','Agendado',5,17,120.70,3,'2023-03-10 00:00:00','2023-03-10 00:00:00',1),(13,'2023-05-16 00:00:00','Agendado',5,18,120.70,3,'2023-03-10 00:00:00','2023-03-10 00:00:00',1),(14,'2023-05-15 00:00:00','Agendado',5,19,120.70,3,'2023-03-10 00:00:00','2023-03-10 00:00:00',1),(15,'2023-05-18 00:00:00','Agendada',2,1,145.20,1,'2023-06-29 00:00:00','2023-06-29 00:00:00',1);
/*!40000 ALTER TABLE `consultas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `financeiro_administrativo`
--

DROP TABLE IF EXISTS `financeiro_administrativo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `financeiro_administrativo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tipoDeMovimentacao` varchar(45) NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `unidade` int NOT NULL,
  `Descritivo` varchar(100) NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime NOT NULL,
  `visible` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `financeiro_administrativo`
--

LOCK TABLES `financeiro_administrativo` WRITE;
/*!40000 ALTER TABLE `financeiro_administrativo` DISABLE KEYS */;
/*!40000 ALTER TABLE `financeiro_administrativo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `financeiro_medicos`
--

DROP TABLE IF EXISTS `financeiro_medicos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `financeiro_medicos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `valor` decimal(10,2) NOT NULL,
  `medico` int NOT NULL,
  `estado` varchar(45) NOT NULL,
  `franquia` int NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime NOT NULL,
  `visible` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `financeiro_medicos`
--

LOCK TABLES `financeiro_medicos` WRITE;
/*!40000 ALTER TABLE `financeiro_medicos` DISABLE KEYS */;
/*!40000 ALTER TABLE `financeiro_medicos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `franquias`
--

DROP TABLE IF EXISTS `franquias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `franquias` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `cnpj` varchar(45) NOT NULL,
  `cidade` varchar(45) NOT NULL,
  `endereco` varchar(45) NOT NULL,
  `responsavel` int NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime NOT NULL,
  `visible` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `franquias`
--

LOCK TABLES `franquias` WRITE;
/*!40000 ALTER TABLE `franquias` DISABLE KEYS */;
INSERT INTO `franquias` VALUES (1,'Nexus Corporation','12.345.678/0001-23','Macapá','Rua Tiradentes, 123',8,'2023-03-10 00:00:00','2023-03-10 00:00:00',1),(2,'Stellar Enterprises','98.765.432/0001-10','Santana','Avenida Amazonas, 456',9,'2023-03-10 00:00:00','2023-03-10 00:00:00',1);
/*!40000 ALTER TABLE `franquias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionarios_adiministrativo`
--

DROP TABLE IF EXISTS `funcionarios_adiministrativo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funcionarios_adiministrativo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_franquia` int NOT NULL,
  `id_funcionario` int NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime NOT NULL,
  `visible` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionarios_adiministrativo`
--

LOCK TABLES `funcionarios_adiministrativo` WRITE;
/*!40000 ALTER TABLE `funcionarios_adiministrativo` DISABLE KEYS */;
INSERT INTO `funcionarios_adiministrativo` VALUES (1,1,14,'2023-03-10 00:00:00','2023-03-10 00:00:00',1),(2,2,15,'2023-03-10 00:00:00','2023-03-10 00:00:00',1),(3,3,16,'2023-03-10 00:00:00','2023-03-10 00:00:00',1),(4,4,17,'2023-03-10 00:00:00','2023-03-10 00:00:00',1),(5,2,18,'2023-03-10 00:00:00','2023-03-10 00:00:00',1),(6,3,19,'2023-03-10 00:00:00','2023-03-10 00:00:00',1);
/*!40000 ALTER TABLE `funcionarios_adiministrativo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `info_consultas`
--

DROP TABLE IF EXISTS `info_consultas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `info_consultas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `consulta` int NOT NULL,
  `descricao` varchar(200) NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime NOT NULL,
  `visible` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `info_consultas`
--

LOCK TABLES `info_consultas` WRITE;
/*!40000 ALTER TABLE `info_consultas` DISABLE KEYS */;
INSERT INTO `info_consultas` VALUES (1,1,'Teste deu certo','2023-05-14 00:00:00','2023-05-14 00:00:00',1);
/*!40000 ALTER TABLE `info_consultas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicos`
--

DROP TABLE IF EXISTS `medicos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medicos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `crm` varchar(45) NOT NULL,
  `pessoa` int NOT NULL,
  `especialidade` varchar(45) NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime NOT NULL,
  `visible` tinyint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Pessoa_idx` (`pessoa`),
  CONSTRAINT `Medico` FOREIGN KEY (`pessoa`) REFERENCES `pessoas` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicos`
--

LOCK TABLES `medicos` WRITE;
/*!40000 ALTER TABLE `medicos` DISABLE KEYS */;
INSERT INTO `medicos` VALUES (1,'CRM 123456',3,'Cardiologia','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(2,'CRM 654321',4,'Dermatologia','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(3,'CRM 987654',5,'Ortopedia','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(4,'CRM 246813',6,'Pediatria','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(5,'CRM 531642',7,'Ginecologia','2023-03-10 00:00:00','2023-03-10 00:00:00',1);
/*!40000 ALTER TABLE `medicos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoas`
--

DROP TABLE IF EXISTS `pessoas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pessoas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `endereco` varchar(45) NOT NULL,
  `cpf` varchar(45) NOT NULL,
  `telefone` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `tipoDeUsuario` varchar(45) NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime NOT NULL,
  `visible` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoas`
--

LOCK TABLES `pessoas` WRITE;
/*!40000 ALTER TABLE `pessoas` DISABLE KEYS */;
INSERT INTO `pessoas` VALUES (1,'joana','rua Claretin Jorges, 987','145698723','36982547','clara','clara','Regente','2023-03-10 00:00:00','2023-05-03 00:00:00',1),(2,'Marlene','rua Marinho Junior','123456789','14258763','mari','mari','Dono da Matriz','2023-03-10 00:00:00','2023-05-03 00:00:00',1),(3,'Helena','rua Antonio de Souza Filho, 87','123456789','12345678','hele','hele','medico','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(4,'Beatriz','Rua Durval de Soura, 1586','987654321','87654321','beat','beat','medico','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(5,'Bernardo','Rua Ediene Douat, 345','246813579','24681357','bern','bern','medico','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(6,'Matheus','Rua Arthur Rodrigues, 6784','135792468','13579246','math','math','medico','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(7,'Gael','Rua Araci de Almeida, 234','864209753','86420975','gael','gael','medico','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(8,'Valentina','Av. Hamburgo,1987','573801649','57380164','valen','valen','Dono de Franquia','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(9,'Pietro','Av.Santa Francisca, 2343','318927654','31892765','piet','piet','Dono de Franquia','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(10,'Esther','Av. Santa Luzia, 567','765492813','76549281','esther','esther','Responsavel pela Franquia','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(11,'Sara','Av. Sidnei Costa dos Santos, 88','428137596','42813759','sara','sara','Responsavel pela Franquia','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(12,'Isabelly','Av. Brigadeiro Eduardo Gomes, 876','679823145','67982314','isab','isab','Responsavel pela Franquia','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(13,'Olivia','Av. Estrada Sul, 453','251496837','25149683','oliv','oliv','Responsavel pela Franquia','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(14,'Erick','Rua Blumenau, 332','394572816','39457281','erick','erick','FuncAdministrativo','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(15,'Lino','Av. Antenor Canuto Vieira, 458','628735914','62873591','lino','lino','FuncAdministrativo','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(16,'Inacio','Av. Pedro Bueno, 987','547619283','54761928','inac','inac','FuncAdministrativo','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(17,'Hermano','Av. Mateo Bei, 222','876345921','87634592','herman','herman','FuncAdministrativo','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(18,'Frederica','Rua Carijos, 354','965248137','96524813','frederica','frederica','FuncAdministrativo','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(19,'Micaela','Rua Tiradentes, 2235','712459386','71245938','mica','mica','FuncAdministrativo','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(20,'Teodora','Rua Nossa Sra. das Merces, 741','437861592','43786159','teod','teod','Paciente','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(21,'Ramona','Rua Maranhão, 3215','183926475','18392647','ram','ram','Paciente','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(22,'Maria Silva','Rua das Flores, 123','123.456.789-01',' (99) 98765-4321','mar','mar','Paciente','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(23,'João Santos','Avenida Principal, 456','987.654.321-09','(88) 87654-3210','joao','joao','Paciente','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(24,'Ana Souza','Travessa dos Sonhos, 789','456.123.789-45','(77) 76543-2109','anas','anas','Paciente','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(25,'Pedro Almeida','Praça Central, 321','789.321.456-98','(66) 65432-1098','pedro','pedro','Paciente','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(26,'Carla Pereira','Rua da Esperança, 654','567.890.123-56','(55) 54321-0987','carla','carla','Paciente','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(27,'Lucas Oliveira','Avenida dos Sonhos, 987','234.567.890-12','(44) 43210-9876','lucao','lucao','Paciente','2023-03-10 00:00:00','2023-03-10 00:00:00',1);
/*!40000 ALTER TABLE `pessoas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `procedimentos`
--

DROP TABLE IF EXISTS `procedimentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `procedimentos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `consulta` int NOT NULL,
  `diaEhorario` datetime NOT NULL,
  `estado` varchar(45) NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `laudo` varchar(200) NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime NOT NULL,
  `visible` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `procedimentos`
--

LOCK TABLES `procedimentos` WRITE;
/*!40000 ALTER TABLE `procedimentos` DISABLE KEYS */;
INSERT INTO `procedimentos` VALUES (1,'Exame de sangue',1,'2023-05-15 00:00:00','Agendado',100.00,'Sera preenchido no dia','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(2,'Radiografia',2,'2023-05-16 00:00:00','Agendado',100.40,'Sera preenchido no dia','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(3,'Tomografia computadorizada',3,'2023-05-15 00:00:00','Agendado',101.00,'Sera preenchido no dia','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(4,'Ressonância magnética',4,'2023-05-16 00:00:00','Agendado',101.50,'Sera preenchido no dia','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(5,'Cirurgia de Endoscopia',5,'2023-05-15 00:00:00','Agendado',102.00,'Sera preenchido no dia','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(6,'Colonoscopia',6,'2023-05-16 00:00:00','Agendado',102.80,'Sera preenchido no dia','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(7,'Ultrassonografia',7,'2023-05-15 00:00:00','Agendado',103.00,'Sera preenchido no dia','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(8,'Ecocardiograma',8,'2023-05-16 00:00:00','Agendado',103.15,'Sera preenchido no dia','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(9,'Mamografia',9,'2023-05-15 00:00:00','Agendado',104.00,'Sera preenchido no dia','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(10,'Biópsia de pele',10,'2023-05-16 00:00:00','Agendado',104.65,'Sera preenchido no dia','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(11,'Endoscopia digestiva',11,'2023-05-15 00:00:00','Agendado',105.00,'Sera preenchido no dia','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(12,'Cirurgia de apendicite',12,'2023-05-16 00:00:00','Agendado',105.45,'Sera preenchido no dia','2023-03-10 00:00:00','2023-03-10 00:00:00',1),(13,'Teste de esforço (ergometria)',13,'2023-05-15 00:00:00','Agendado',106.00,'Sera preenchido no dia','2023-03-10 00:00:00','2023-03-10 00:00:00',1);
/*!40000 ALTER TABLE `procedimentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unidades_franquias`
--

DROP TABLE IF EXISTS `unidades_franquias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unidades_franquias` (
  `id` int NOT NULL AUTO_INCREMENT,
  `franquia` int NOT NULL,
  `cidade` varchar(45) NOT NULL,
  `endereco` varchar(45) NOT NULL,
  `responsavel` int NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime NOT NULL,
  `visible` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unidades_franquias`
--

LOCK TABLES `unidades_franquias` WRITE;
/*!40000 ALTER TABLE `unidades_franquias` DISABLE KEYS */;
INSERT INTO `unidades_franquias` VALUES (1,1,'Porto Grande','Rua Principal, 789',10,'2023-03-10 00:00:00','2023-03-10 00:00:00',1),(2,2,'Mazagão','Travessa dos Coqueiros, 321',11,'2023-03-10 00:00:00','2023-03-10 00:00:00',1),(3,2,'Oiapoque','Avenida Beira Rio, 456',12,'2023-03-10 00:00:00','2023-03-10 00:00:00',1),(4,2,'Laranjal do Jari','Rua das Flores, 123',13,'2023-03-10 00:00:00','2023-03-10 00:00:00',1);
/*!40000 ALTER TABLE `unidades_franquias` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-29 17:53:18
