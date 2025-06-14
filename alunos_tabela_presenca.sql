-- MySQL dump 10.13  Distrib 5.6.23, for Win32 (x86)
--
-- Host: localhost    Database: alunos
-- ------------------------------------------------------
-- Server version	5.5.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tabela_presenca`
--

DROP TABLE IF EXISTS `tabela_presenca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tabela_presenca` (
  `data` date NOT NULL,
  `hora_entrada` time NOT NULL,
  `hora_saida` datetime DEFAULT NULL,
  `RA` int(11) NOT NULL,
  `Localizacao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`RA`,`data`),
  CONSTRAINT `tabela_presenca_ibfk_1` FOREIGN KEY (`RA`) REFERENCES `tabela_alunos` (`RA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tabela_presenca`
--

LOCK TABLES `tabela_presenca` WRITE;
/*!40000 ALTER TABLE `tabela_presenca` DISABLE KEYS */;
INSERT INTO `tabela_presenca` VALUES ('2025-06-09','20:44:45','2025-06-09 20:53:05',1,NULL),('2025-06-10','20:30:26',NULL,1,'São Paulo, Brazil (Lat: -23.6301, Lon: -46.6378)'),('2025-06-11','20:11:09','2025-06-11 20:36:59',1,'São Paulo, Brazil (Lat: -23.6301, Lon: -46.6378)'),('2025-06-09','21:45:36','2025-06-09 21:48:09',7070,'São Paulo, Brazil (Lat: -23.6301, Lon: -46.6378)'),('2025-06-11','20:36:07','2025-06-11 21:35:33',7070,'São Paulo, Brazil (Lat: -23.6301, Lon: -46.6378)'),('2025-06-12','15:38:32','2025-06-12 15:51:42',7070,'São Paulo, Brazil (Lat: -23.6301, Lon: -46.6378)');
/*!40000 ALTER TABLE `tabela_presenca` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-13 21:43:35
