-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: oocinema
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `film`
--

DROP TABLE IF EXISTS `film`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `film` (
  `ID_film` varchar(10) NOT NULL,
  `Titolo` text,
  `Descrizione` text,
  `Genere` text,
  `Durata` int DEFAULT NULL,
  `Regista` text,
  `Cast` text,
  `Costo` int DEFAULT NULL,
  `CoverPath` varchar(400) DEFAULT NULL,
  `TrailerPath` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`ID_film`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `film`
--

LOCK TABLES `film` WRITE;
/*!40000 ALTER TABLE `film` DISABLE KEYS */;
INSERT INTO `film` VALUES ('F479623','THE BATMAN','Dal celebre fumetto, le avventurose vicende di Batman, il supereroe che combatte il crimine indossando un costume da pipistrello, mentre è alla costante ricerca dei segreti del suo passato.','Fantasy, Noir, Azione',175,'Matt Reeves','Robert Pattinson, Zoe Kravitz, Jeffry Wright',5,NULL,NULL),('F479624','SPIDER-MAN-NO WAY HOME','L\'identità dell\'Uomo Ragno viene rivelata a tutti, e non riesce più a separare la sua vita normale dalla vita da supereroe, e quando chiede aiuto al Dottor Strange, lo costringe a scoprire cosa significa veramente essere l\'Uomo Ragno.','Azione, Avventura, Fantasy',148,'Jon Watts','Tom Holland, Zendaya, Benedict Cumberbatch',5,NULL,NULL),('F479626','ASSASSINO SUL NILO','Mentre è in vacanza sul Nilo, il geniale detective di fama mondiale Hercule Poirot si ritrova a dover indagare sull\'omicidio di una giovane ereditiera.','Giallo, Drammatico',134,'Kenneth Branagh','Jodie Comer, Gal Gadot, Armie Hammer',5,NULL,NULL),('F479627','HOUSE OF GUCCI','Il difficile matrimonio e il tormentato divorzio di Patrizia e Maurizio Gucci, capo della casa di moda Gucci, porta ad un efferato omicidio','Drammaico, Biografico',157,'Ridley Scott','Lady Gaga, Jared Leto, Al Pacino',5,NULL,NULL),('F479628','SING 2','Buster Moon e il suo gruppo di animali artisti si preparano a lanciare un abbagliante spettacolo teatrale nella capitale mondiale del divertimento. L\'unico problema è che deve convincere la solitaria stella del rock a unirsi a loro','Animazione, Commedia',110,'Garth Jennings','Taron Egerton, Tori Kelly, Garth Jennings',5,NULL,NULL),('F479629','DIABOLIK','Dal famoso fumetto di Angela Giussani, le avventurose vicende di Diabolik, un ladro spietato che si scaglia contro banche, famiglie benestanti e persone che si sono arricchite in modo illecito, derubandole senza pietà.','Commedia, Giallo',133,'Marco Manetti, Antonio Manetti','Luca Marinelli, Miriam Leone, Claudia Gerini',5,NULL,NULL);
/*!40000 ALTER TABLE `film` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-25 22:35:48
