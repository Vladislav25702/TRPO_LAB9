-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: internshipproject
-- ------------------------------------------------------
-- Server version	8.1.0

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
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `address` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `userid` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userid_idx` (`userid`),
  CONSTRAINT `userid` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (1,'gh','gh','gh@mail.ru',2);
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `education`
--

DROP TABLE IF EXISTS `education`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `education` (
  `id` int NOT NULL AUTO_INCREMENT,
  `location` varchar(45) DEFAULT NULL,
  `speciality` varchar(45) DEFAULT NULL,
  `enddate` varchar(45) DEFAULT NULL,
  `resume` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `resume_idx` (`resume`),
  CONSTRAINT `fk_education_resume` FOREIGN KEY (`resume`) REFERENCES `resume` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `education`
--

LOCK TABLES `education` WRITE;
/*!40000 ALTER TABLE `education` DISABLE KEYS */;
INSERT INTO `education` VALUES (1,'1','1','1',18),(2,'2','2','2',21),(3,'2','2','2',22),(4,'8','8','8',48),(5,'123','123','123',51),(6,'56','56','56',53),(7,'1','1','1',55),(8,'2','','',56),(9,'ty','ty','ty',57),(12,'1','1','1',65),(14,'123','123','123',62),(17,'57','56','56',66),(18,'','','',66),(21,'rt','rt','rt',68),(22,'gh','gh','gh',68);
/*!40000 ALTER TABLE `education` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expirience`
--

DROP TABLE IF EXISTS `expirience`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `expirience` (
  `id` int NOT NULL AUTO_INCREMENT,
  `location` varchar(45) DEFAULT NULL,
  `position` varchar(45) DEFAULT NULL,
  `desctiption` varchar(45) DEFAULT NULL,
  `Startdate` varchar(45) DEFAULT NULL,
  `Enddate` varchar(45) DEFAULT NULL,
  `Resume` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Resume_idx` (`Resume`),
  CONSTRAINT `Resume` FOREIGN KEY (`Resume`) REFERENCES `resume` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expirience`
--

LOCK TABLES `expirience` WRITE;
/*!40000 ALTER TABLE `expirience` DISABLE KEYS */;
INSERT INTO `expirience` VALUES (1,NULL,'1',NULL,NULL,NULL,17),(2,NULL,'1',NULL,NULL,NULL,18),(3,NULL,'152',NULL,NULL,NULL,51),(4,NULL,'56',NULL,NULL,NULL,53),(5,NULL,'1',NULL,NULL,NULL,55),(6,NULL,'ty',NULL,NULL,NULL,57),(7,'1','1',NULL,'1','1',59),(8,'','2',NULL,'2','2',60),(9,'1','1',NULL,'1','1',61),(11,'1','1',NULL,'','',64),(13,'125','123','','123','123',62),(16,'56','56','','56','56',66),(17,'56','56','','56','56',66),(19,'gh','gh','','gh','gh',68);
/*!40000 ALTER TABLE `expirience` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `internship`
--

DROP TABLE IF EXISTS `internship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `internship` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `company` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `Internshipcol` varchar(45) DEFAULT NULL,
  `requirments` varchar(45) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `salary` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `internship`
--

LOCK TABLES `internship` WRITE;
/*!40000 ALTER TABLE `internship` DISABLE KEYS */;
INSERT INTO `internship` VALUES (1,'Программист-стажёр','comany',NULL,NULL,NULL,NULL,NULL),(2,'Стажёр 2','company2',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `internship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `internship_favorite`
--

DROP TABLE IF EXISTS `internship_favorite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `internship_favorite` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Internships` int NOT NULL,
  `Student` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Internships_idx` (`Internships`),
  KEY `Student_idx` (`Student`),
  CONSTRAINT `Internships` FOREIGN KEY (`Internships`) REFERENCES `internship` (`Id`),
  CONSTRAINT `Student` FOREIGN KEY (`Student`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `internship_favorite`
--

LOCK TABLES `internship_favorite` WRITE;
/*!40000 ALTER TABLE `internship_favorite` DISABLE KEYS */;
/*!40000 ALTER TABLE `internship_favorite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personalinformation`
--

DROP TABLE IF EXISTS `personalinformation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personalinformation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) DEFAULT NULL,
  `Lastname` varchar(45) DEFAULT NULL,
  `Ot` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `Resume` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Resume_idx` (`Resume`),
  CONSTRAINT `fk_PersonalInformation_resume` FOREIGN KEY (`Resume`) REFERENCES `resume` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personalinformation`
--

LOCK TABLES `personalinformation` WRITE;
/*!40000 ALTER TABLE `personalinformation` DISABLE KEYS */;
/*!40000 ALTER TABLE `personalinformation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resume`
--

DROP TABLE IF EXISTS `resume`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resume` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Information` varchar(45) DEFAULT NULL,
  `Student` int NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `ot` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `dateofbirth` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Student_idx` (`Student`),
  CONSTRAINT `fk_resume_student` FOREIGN KEY (`Student`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resume`
--

LOCK TABLES `resume` WRITE;
/*!40000 ALTER TABLE `resume` DISABLE KEYS */;
INSERT INTO `resume` VALUES (7,'1',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(9,'1',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(17,'1',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(18,'1',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(19,'2',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(20,'1',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(21,'2',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(22,'2',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(25,'1',1,NULL,'Владислав','Бахилин',NULL,NULL,NULL,'vladislav25702@gmail.com',NULL),(26,'3',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(27,'4',3,NULL,'','','','','','',NULL),(28,'5',3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(29,'7',3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(30,'9',3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(31,'9',3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(32,'6',3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(33,'9',3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(34,'0',3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(35,'1',3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(36,'36',3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(37,'8',3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(38,'9',3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(39,'18',3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(40,'20',3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(41,'20',3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(42,'22',3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(46,'123',3,NULL,'1','124','124','124','124','124@mail.ru',NULL),(47,'1',3,NULL,'1','1','','','','1@mail.ru',NULL),(48,'8',3,NULL,'','','','','','',NULL),(49,'5',7,NULL,'5','','','','','',NULL),(50,'1',7,NULL,'','','','','','',NULL),(51,'87',8,NULL,'123','123','123','123','124','123@gmail.com',NULL),(52,'5',8,NULL,'5','','','','','',NULL),(53,'56',9,NULL,'56','56','56','','56','',NULL),(54,'1',9,NULL,'1','1','1','','','1@mail.ru',NULL),(55,'1',9,NULL,'1','','','','','',NULL),(56,'1',9,NULL,'1','1','','','','',NULL),(57,'ty',10,NULL,'ty','ty','','','','',NULL),(58,'1',10,NULL,'1','1','','','','',NULL),(59,'',9,NULL,'','','','','','',NULL),(60,'',9,NULL,'','','','','','',NULL),(61,'1',9,NULL,'','','','','','',NULL),(62,'126',11,NULL,'','','','','','',NULL),(63,'124',11,NULL,'','','','','','',NULL),(64,'65',11,NULL,'','','','','','',NULL),(65,'124h',11,NULL,'','','','','','',NULL),(66,'57',12,NULL,'','','','','','',NULL),(67,'123',12,NULL,'123','','','','','',NULL),(68,'rt',13,NULL,'rt','rt','rt','rt','rt','rt@mail',NULL);
/*!40000 ALTER TABLE `resume` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skills`
--

DROP TABLE IF EXISTS `skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `skills` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skills`
--

LOCK TABLES `skills` WRITE;
/*!40000 ALTER TABLE `skills` DISABLE KEYS */;
INSERT INTO `skills` VALUES (1,'JAVA'),(2,'PYTHON'),(3,'html'),(4,'1'),(5,'2'),(6,'3'),(7,'5'),(8,'7'),(9,'9'),(10,'0'),(13,'y-t'),(14,'i o'),(15,'8'),(16,'56'),(17,'ty'),(18,'ty2'),(19,'ty u'),(20,'ty 2'),(21,'ty3'),(22,'123'),(23,'yui'),(24,'o'),(25,'gh'),(26,'yu'),(27,'mn');
/*!40000 ALTER TABLE `skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skillsresume`
--

DROP TABLE IF EXISTS `skillsresume`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `skillsresume` (
  `resumeid` int NOT NULL,
  `skillsid` int NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `resumeid_idx` (`resumeid`),
  KEY `skillsid_idx` (`skillsid`),
  CONSTRAINT `resumeid` FOREIGN KEY (`resumeid`) REFERENCES `resume` (`id`),
  CONSTRAINT `skillsid` FOREIGN KEY (`skillsid`) REFERENCES `skills` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skillsresume`
--

LOCK TABLES `skillsresume` WRITE;
/*!40000 ALTER TABLE `skillsresume` DISABLE KEYS */;
INSERT INTO `skillsresume` VALUES (20,1,1),(20,2,2),(21,1,3),(21,2,4),(22,1,5),(22,3,6),(25,4,7),(26,6,8),(28,8,10),(29,8,11),(30,8,12),(31,9,13),(32,8,14),(33,8,15),(34,10,16),(35,10,17),(37,10,19),(38,10,20),(39,10,21),(40,10,22),(41,10,23),(42,10,24),(36,10,26),(27,7,30),(48,15,32),(49,7,33),(50,4,34),(51,13,35),(51,14,36),(52,7,37),(53,16,38),(53,16,39),(54,4,40),(57,17,41),(57,18,42),(57,19,43),(59,4,44),(63,22,49),(62,17,53),(62,20,54),(62,21,55),(67,22,59),(66,16,60),(66,23,61),(66,24,62),(68,25,67),(68,26,68),(68,27,69);
/*!40000 ALTER TABLE `skillsresume` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `userid` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userid_idx` (`userid`),
  CONSTRAINT `useridstudent` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (1,'Владислав','Бахилин','vladislav25702@gmail.com',1),(2,'5','5','5@g',3),(3,'2','2','1@mail.ru',4),(4,'5','5','5@g',5),(5,'6','6','6@g',6),(6,'7','7','7@g',7),(7,'y','y','y@gm',8),(8,'8','8','8@g',9),(9,'10','10','8@g',10),(10,'r','r','r@g',11),(11,'y','y','1@m',12),(12,'h','h','h@g',13),(13,'pj','p','p@g',14);
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'1','vladislav25702','STUDENT'),(2,'2','company1','COMPANY'),(3,'1','1','STUDENT'),(4,'1','2','STUDENT'),(5,'5','5','STUDENT'),(6,'6','6','STUDENT'),(7,'7','7','STUDENT'),(8,'y','y','STUDENT'),(9,'8','8','STUDENT'),(10,'10','10','STUDENT'),(11,'r','r','STUDENT'),(12,'u','u','STUDENT'),(13,'h','h','STUDENT'),(14,'p','p','STUDENT');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-24  6:17:12
