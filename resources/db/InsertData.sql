-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: restoran
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Dumping data for table `artikal`
--

LOCK TABLES `artikal` WRITE;
/*!40000 ALTER TABLE `artikal` DISABLE KEYS */;
INSERT INTO `artikal` VALUES (1,'Calzone',8.00,''),(2,'Capriciosa',12.00,''),(3,'Kafa',1.50,''),(4,'Cappucino',1.80,''),(5,'Tiramisu',2.50,''),(6,'Limunada',2.00,''),(7,'Cezar salata',10.00,''),(8,'Mesna plata',25.00,''),(9,'Šopska salata',3.00,''),(10,'Pastrmka na žaru',15.00,'');
/*!40000 ALTER TABLE `artikal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `artikal_ima_meni`
--

LOCK TABLES `artikal_ima_meni` WRITE;
/*!40000 ALTER TABLE `artikal_ima_meni` DISABLE KEYS */;
INSERT INTO `artikal_ima_meni` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1);
/*!40000 ALTER TABLE `artikal_ima_meni` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `jelo`
--

LOCK TABLES `jelo` WRITE;
/*!40000 ALTER TABLE `jelo` DISABLE KEYS */;
INSERT INTO `jelo` VALUES ('','Standardna',1,1),('','Standardna',1,2),('','Standardna',7,5),('','Standardna',4,7),('','Standardna',2,8),('','Standardna',4,9),('','Standardna',3,10);
/*!40000 ALTER TABLE `jelo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `jelo_ima_sastojak`
--

LOCK TABLES `jelo_ima_sastojak` WRITE;
/*!40000 ALTER TABLE `jelo_ima_sastojak` DISABLE KEYS */;
/*!40000 ALTER TABLE `jelo_ima_sastojak` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `meni`
--

LOCK TABLES `meni` WRITE;
/*!40000 ALTER TABLE `meni` DISABLE KEYS */;
INSERT INTO `meni` VALUES (1,'SummerMenu','2022-03-21','2022-09-01'),(2,'WinterMenu','2022-09-02','2022-12-31');
/*!40000 ALTER TABLE `meni` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `mjesto_stanovanja`
--

LOCK TABLES `mjesto_stanovanja` WRITE;
/*!40000 ALTER TABLE `mjesto_stanovanja` DISABLE KEYS */;
INSERT INTO `mjesto_stanovanja` VALUES (78000,'Banja Luka');
/*!40000 ALTER TABLE `mjesto_stanovanja` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `narudžba`
--

LOCK TABLES `narudžba` WRITE;
/*!40000 ALTER TABLE `narudžba` DISABLE KEYS */;
INSERT INTO `narudžba` VALUES (1,'2022-05-20 00:00:00',1,3,1,1,1);
/*!40000 ALTER TABLE `narudžba` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `narudžba_ima_artikal`
--

LOCK TABLES `narudžba_ima_artikal` WRITE;
/*!40000 ALTER TABLE `narudžba_ima_artikal` DISABLE KEYS */;
INSERT INTO `narudžba_ima_artikal` VALUES (1,1,1),(1,2,1);
/*!40000 ALTER TABLE `narudžba_ima_artikal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `osoba`
--

LOCK TABLES `osoba` WRITE;
/*!40000 ALTER TABLE `osoba` DISABLE KEYS */;
INSERT INTO `osoba` VALUES (1,'Mini','Maus','minimaus','minimaus','065-123-123',78000);
/*!40000 ALTER TABLE `osoba` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `piće`
--

LOCK TABLES `piće` WRITE;
/*!40000 ALTER TABLE `piće` DISABLE KEYS */;
INSERT INTO `piće` VALUES (3,100,NULL,1),(4,100,NULL,1),(6,100,NULL,2);
/*!40000 ALTER TABLE `piće` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `proizvođač`
--

LOCK TABLES `proizvođač` WRITE;
/*!40000 ALTER TABLE `proizvođač` DISABLE KEYS */;
INSERT INTO `proizvođač` VALUES (1,'Vinarija Jungić'),(2,'Banjalučka pivara'),(3,'Nestea'),(4,'Pepsico'),(5,'Coca Cola Group');
/*!40000 ALTER TABLE `proizvođač` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `račun`
--

LOCK TABLES `račun` WRITE;
/*!40000 ALTER TABLE `račun` DISABLE KEYS */;
INSERT INTO `račun` VALUES (1,20.00,'2022-05-20',1,1);
/*!40000 ALTER TABLE `račun` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `restoran`
--

LOCK TABLES `restoran` WRITE;
/*!40000 ALTER TABLE `restoran` DISABLE KEYS */;
INSERT INTO `restoran` VALUES (1,'Sketch','9 Conduit St, London W1S 2XG, United Kingdom','+44 20 7659 4500','sketch@email.com','Friday	9AM–2AM\nSaturday	9AM–2AM\nSunday	9PM–12AM\nMonday	9AM–12AM\nTuesday	9AM–12AM\nWednesday	9AM–12AM\nThursday	9AM–2AM\n');
/*!40000 ALTER TABLE `restoran` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `rezervacija`
--

LOCK TABLES `rezervacija` WRITE;
/*!40000 ALTER TABLE `rezervacija` DISABLE KEYS */;
/*!40000 ALTER TABLE `rezervacija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sastojak`
--

LOCK TABLES `sastojak` WRITE;
/*!40000 ALTER TABLE `sastojak` DISABLE KEYS */;
/*!40000 ALTER TABLE `sastojak` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'Kreirana'),(2,'Isporučena'),(3,'Završena');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `stavka`
--

LOCK TABLES `stavka` WRITE;
/*!40000 ALTER TABLE `stavka` DISABLE KEYS */;
INSERT INTO `stavka` VALUES (1,1,8.00,1),(1,2,12.00,1);
/*!40000 ALTER TABLE `stavka` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sto`
--

LOCK TABLES `sto` WRITE;
/*!40000 ALTER TABLE `sto` DISABLE KEYS */;
INSERT INTO `sto` VALUES (1,2,0),(2,4,0),(3,4,0);
/*!40000 ALTER TABLE `sto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sto_ima_rezervaciju`
--

LOCK TABLES `sto_ima_rezervaciju` WRITE;
/*!40000 ALTER TABLE `sto_ima_rezervaciju` DISABLE KEYS */;
/*!40000 ALTER TABLE `sto_ima_rezervaciju` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `vrsta_jela`
--

LOCK TABLES `vrsta_jela` WRITE;
/*!40000 ALTER TABLE `vrsta_jela` DISABLE KEYS */;
INSERT INTO `vrsta_jela` VALUES (1,'Pizza'),(2,'Mesni specijaliteti'),(3,'Riblji specijaliteti'),(4,'Salate'),(5,'Paste'),(6,'Lazanje'),(7,'Dezert');
/*!40000 ALTER TABLE `vrsta_jela` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `vrsta_pića`
--

LOCK TABLES `vrsta_pića` WRITE;
/*!40000 ALTER TABLE `vrsta_pića` DISABLE KEYS */;
INSERT INTO `vrsta_pića` VALUES (1,'Topli napici'),(2,'Sokovi'),(3,'Likeri'),(4,'Kokteli'),(5,'Vino');
/*!40000 ALTER TABLE `vrsta_pića` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `zaposleni`
--

LOCK TABLES `zaposleni` WRITE;
/*!40000 ALTER TABLE `zaposleni` DISABLE KEYS */;
INSERT INTO `zaposleni` VALUES (1,1000,1,'Konobar');
/*!40000 ALTER TABLE `zaposleni` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-20 21:41:04
