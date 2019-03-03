CREATE DATABASE  IF NOT EXISTS `beathovendb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `beathovendb`;

-- MySQL dump 10.13  Distrib 8.0.15, for macos10.14 (x86_64)
--
-- Host: localhost    Database: 
-- ------------------------------------------------------
-- Server version	8.0.13


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `album`
--

DROP TABLE IF EXISTS `album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `album` (
  `album_id` int(11) NOT NULL AUTO_INCREMENT,
  `album_name` varchar(45) NOT NULL,
  `user_id` int(11) NOT NULL,
  `cover_url` blob,
  `artist_id` varchar(45) DEFAULT NULL,
  `artist_name` varchar(45) NOT NULL,
  PRIMARY KEY (`album_id`),
  UNIQUE KEY `album_id_UNIQUE` (`album_id`),
  KEY `artistID-album_idx` (`user_id`),
  CONSTRAINT `userID-album` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `album`
--

LOCK TABLES `album` WRITE;
/*!40000 ALTER TABLE `album` DISABLE KEYS */;
/*!40000 ALTER TABLE `album` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist`
--

DROP TABLE IF EXISTS `playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `playlist` (
  `playlist_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `playlist_name` varchar(45) NOT NULL,
  `is_favorite` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`playlist_id`),
  UNIQUE KEY `playlist_id_UNIQUE` (`playlist_id`),
  KEY `userID-playlist_idx` (`user_id`),
  CONSTRAINT `userID-playlist` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist`
--

LOCK TABLES `playlist` WRITE;
/*!40000 ALTER TABLE `playlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `playlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist_contents`
--

DROP TABLE IF EXISTS `playlist_contents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `playlist_contents` (
  `playlist_id` int(11) NOT NULL,
  `song_id` int(11) NOT NULL,
  PRIMARY KEY (`playlist_id`,`song_id`),
  KEY `song_id-content_idx` (`song_id`),
  CONSTRAINT `playlist_id-content` FOREIGN KEY (`playlist_id`) REFERENCES `playlist` (`playlist_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `song_id-content` FOREIGN KEY (`song_id`) REFERENCES `song` (`song_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist_contents`
--

LOCK TABLES `playlist_contents` WRITE;
/*!40000 ALTER TABLE `playlist_contents` DISABLE KEYS */;
/*!40000 ALTER TABLE `playlist_contents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `song`
--

DROP TABLE IF EXISTS `song`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `song` (
  `song_id` int(11) NOT NULL AUTO_INCREMENT,
  `song_name` varchar(45) NOT NULL,
  `artist_name` varchar(45) NOT NULL,
  `genre` varchar(45) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `is_favorite` int(11) NOT NULL DEFAULT '0',
  `song_url` blob NOT NULL,
  `user_id` int(11) NOT NULL,
  `album_id` int(11) DEFAULT NULL,
  `times_played` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`song_id`),
  UNIQUE KEY `idsong_UNIQUE` (`song_id`),
  KEY `uploader_id_idx` (`user_id`),
  KEY `albumID-song_idx` (`album_id`),
  CONSTRAINT `albumID-song` FOREIGN KEY (`album_id`) REFERENCES `album` (`album_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userID-song` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `song`
--

LOCK TABLES `song` WRITE;
/*!40000 ALTER TABLE `song` DISABLE KEYS */;
/*!40000 ALTER TABLE `song` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `avatar_url` blob,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `registered_userscol_UNIQUE` (`user_id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
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

-- Dump completed on 2019-03-03 15:12:52

ALTER TABLE`beathovendb`.`song` 
CHANGE COLUMN `song_url` `song_url` BLOB NULL ;

INSERT INTO`beathovendb`.`user` (`user_id`, `username`, `password`, `last_name`, `first_name`) VALUES ('1', 'stevejebs', 'apple', 'Jebs', 'Steve');
INSERT INTO`beathovendb`.`user` (`user_id`, `username`, `password`, `last_name`, `first_name`) VALUES ('2', 'billgatas', 'microsoft', 'Gatas', 'Bill');
INSERT INTO`beathovendb`.`user` (`user_id`, `username`, `password`, `last_name`, `first_name`) VALUES ('3', 'jackmac', 'alibaba', 'Mac', 'Jack');

INSERT INTO`beathovendb`.`album` (`album_id`, `album_name`, `user_id`, `artist_id`, `artist_name`) VALUES ('1', 'Square', '1', '1', 'Ed Sheeran');
INSERT INTO`beathovendb`.`album` (`album_id`, `album_name`, `user_id`, `artist_id`, `artist_name`) VALUES ('2', 'Circle', '2', '2', 'Janren Quiachon');


INSERT INTO`beathovendb`.`song` (`song_id`, `song_name`, `artist_name`, `genre`, `year`, `is_favorite`, `user_id`, `album_id`) VALUES ('1', 'Supermarket Showers', 'Ed Sheeran', 'Pop', '1999', '0', '1', '1');
INSERT INTO`beathovendb`.`song` (`song_id`, `song_name`, `artist_name`, `genre`, `year`, `is_favorite`, `user_id`, `album_id`) VALUES ('2', 'Let it go', 'Janren Quiachon', 'Rock', '2018', '1', '2', '2');

INSERT INTO`beathovendb`.`playlist` (`playlist_id`, `user_id`, `playlist_name`, `is_favorite`) VALUES ('1', '1', 'Disney', '0');
INSERT INTO`beathovendb`.`playlist` (`playlist_id`, `user_id`, `playlist_name`, `is_favorite`) VALUES ('2', '2', 'Samsung', '1');

INSERT INTO`beathovendb`.`playlist_contents` (`playlist_id`, `song_id`) VALUES ('1', '1');
INSERT INTO`beathovendb`.`playlist_contents` (`playlist_id`, `song_id`) VALUES ('2', '2');
