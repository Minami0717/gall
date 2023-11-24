-- MariaDB dump 10.19-11.0.2-MariaDB, for Win64 (AMD64)
--
-- Host: 127.0.0.1    Database: gall
-- ------------------------------------------------------
-- Server version	11.0.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cmt`
--

DROP TABLE IF EXISTS `cmt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cmt` (
  `cmt_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `created_at` datetime(6) NOT NULL DEFAULT current_timestamp(6),
  `pw` varchar(100) NOT NULL,
  `writer` varchar(50) NOT NULL,
  `post_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`cmt_id`),
  KEY `FKow0ki99i2yt5pqijtu6yjq6n9` (`post_id`),
  KEY `FKtixd2bbp5m8umlyl6g0abtjf6` (`user_id`),
  CONSTRAINT `FKow0ki99i2yt5pqijtu6yjq6n9` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`),
  CONSTRAINT `FKtixd2bbp5m8umlyl6g0abtjf6` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cmt`
--

LOCK TABLES `cmt` WRITE;
/*!40000 ALTER TABLE `cmt` DISABLE KEYS */;
/*!40000 ALTER TABLE `cmt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gall`
--

DROP TABLE IF EXISTS `gall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gall` (
  `gall_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL DEFAULT current_timestamp(6),
  `updated_at` datetime(6) NOT NULL DEFAULT current_timestamp(6),
  `img` varchar(100) DEFAULT NULL,
  `intro` varchar(100) DEFAULT NULL,
  `nm` varchar(50) NOT NULL,
  `type` enum('MAIN','MINI','MINOR') NOT NULL,
  `category_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`gall_id`),
  UNIQUE KEY `UK_ik9k39b76q02tg2xtq3neysfl` (`nm`),
  KEY `FK9k10fpq8rxjachhxv1uxjnkat` (`category_id`),
  CONSTRAINT `FK9k10fpq8rxjachhxv1uxjnkat` FOREIGN KEY (`category_id`) REFERENCES `gall_category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gall`
--

LOCK TABLES `gall` WRITE;
/*!40000 ALTER TABLE `gall` DISABLE KEYS */;
INSERT INTO `gall` VALUES
(1,'2023-11-23 00:13:16.480805','2023-11-23 00:13:16.480805','image12.jpg','테스트용 테스트 갤러리입니다. 테스트 테스트','테스트','MINOR',1);
/*!40000 ALTER TABLE `gall` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gall_category`
--

DROP TABLE IF EXISTS `gall_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gall_category` (
  `category_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `nm` varchar(20) NOT NULL,
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `UK_1ess1u69u0bwwg9hgy4v5v17v` (`nm`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gall_category`
--

LOCK TABLES `gall_category` WRITE;
/*!40000 ALTER TABLE `gall_category` DISABLE KEYS */;
INSERT INTO `gall_category` VALUES
(1,'테스트');
/*!40000 ALTER TABLE `gall_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gall_manager`
--

DROP TABLE IF EXISTS `gall_manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gall_manager` (
  `sub_yn` tinyint(4) NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `gall_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`gall_id`,`user_id`),
  KEY `FKedgcuq442phod2t91nquvkl2y` (`user_id`),
  CONSTRAINT `FKedgcuq442phod2t91nquvkl2y` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKt4tt7lr6yupkcwlg3a7mqtgt5` FOREIGN KEY (`gall_id`) REFERENCES `gall` (`gall_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gall_manager`
--

LOCK TABLES `gall_manager` WRITE;
/*!40000 ALTER TABLE `gall_manager` DISABLE KEYS */;
INSERT INTO `gall_manager` VALUES
(0,1,1),
(1,2,1),
(1,3,1);
/*!40000 ALTER TABLE `gall_manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post` (
  `post_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL DEFAULT current_timestamp(6),
  `updated_at` datetime(6) NOT NULL DEFAULT current_timestamp(6),
  `gall_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `content` text NOT NULL,
  `title` varchar(50) NOT NULL,
  `writer` varchar(50) NOT NULL,
  `hits` int(10) unsigned NOT NULL DEFAULT 0,
  `deco_num` int(10) unsigned NOT NULL DEFAULT 0,
  `pw` varchar(100) NOT NULL,
  `reco_num` int(10) unsigned NOT NULL DEFAULT 0,
  `ip` char(7) NOT NULL,
  PRIMARY KEY (`post_id`),
  KEY `FKmcc27kjuu1wwd6vf0xkxlue29` (`gall_id`),
  KEY `FK72mt33dhhs48hf9gcqrq4fxte` (`user_id`),
  CONSTRAINT `FK72mt33dhhs48hf9gcqrq4fxte` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKmcc27kjuu1wwd6vf0xkxlue29` FOREIGN KEY (`gall_id`) REFERENCES `gall` (`gall_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES
(1,'2023-11-24 01:15:24.681808','2023-11-24 01:15:24.681808',1,1,'ㅋㅋ ㅇㅈ?','앙기모리','test1',0,0,'1111',0,'221.164'),
(2,'2023-11-24 02:00:46.391662','2023-11-24 02:00:46.391662',1,2,'zzzzzzz','testes','test2',0,0,'1111',0,'221.164'),
(3,'2023-11-24 02:33:50.314702','2023-11-24 02:33:50.314702',1,NULL,'ㅋㅋㅋㅋㅋㅋㅋ','ㅋㅋ','ㅇㅇ',0,0,'1111',0,'221.164'),
(4,'2023-11-24 02:35:44.925176','2023-11-24 02:35:44.925176',1,NULL,'ㄹㅇㅋㅋ','ㅇㅈㅇㅈ','ㅇㅇ',0,0,'1111',0,'221.164'),
(5,'2023-11-24 02:46:19.516961','2023-11-24 02:46:19.516961',1,NULL,'ㅋㅋㅋㅋㅋ','ㅇㅇㅇㅇㅇㅇ','ㅇㅇ',0,0,'1111',0,'221.164'),
(6,'2023-11-24 02:50:35.312335','2023-11-24 02:50:35.312335',1,NULL,'ㅋㅇㅋㅇ','ㅇㅋㅇ','ㅇㅇ',0,0,'1111',0,'221.164'),
(7,'2023-11-24 03:35:12.892844','2023-11-24 03:35:12.892844',1,NULL,'zz','wfwf','ㅇㅇ',0,0,'1111',0,'221.164'),
(8,'2023-11-24 05:01:24.929821','2023-11-24 05:01:24.929821',1,NULL,'ㅁㄴㅇㄻ','ㅁㄴㅇㄹ','ㅇㅇ',0,0,'1111',0,'221.164'),
(9,'2023-11-24 05:03:39.855124','2023-11-24 05:03:39.855124',1,NULL,'ㅁㄴㅇㄻ','ㅁㄴㅇㄹ','ㅇㅇ',0,0,'1111',0,'221.164'),
(10,'2023-11-24 05:05:55.267161','2023-11-24 05:05:55.267161',1,NULL,'ㅁㄴㅇㄻ','ㅁㄴㅇㄹ','ㅇㅇ',0,0,'1111',0,'221.164'),
(11,'2023-11-24 05:08:44.153238','2023-11-24 05:08:44.153238',1,NULL,'뮺ㅇ','뮤','ㅇㅇ',0,0,'1111',0,'221.164'),
(13,'2023-11-24 05:24:26.586507','2023-11-24 05:24:26.586507',1,NULL,'ㄹㅈㄷㄹㅈ','ㅁㄴㅇㄻ','ㅇㅇ',0,0,'1111',0,'221.164'),
(14,'2023-11-24 05:26:05.919889','2023-11-24 05:26:05.919889',1,NULL,'ㄹㅈㄷㄹㅈ','ㅁㄴㅇㄻ','ㅇㅇ',0,0,'1111',0,'221.164'),
(15,'2023-11-24 05:28:50.349230','2023-11-24 05:28:50.349230',1,NULL,'ㅁㄴㅇㄹ','ㅅㅅㄷㄴㅋ','ㅇㅇ',0,0,'1111',0,'221.164'),
(16,'2023-11-24 05:32:54.653468','2023-11-24 05:32:54.653468',1,NULL,'ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ','ㅋㅋㅋㅋㅋㅋ','ㅇㅇ',0,0,'1111',0,'221.164');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_img`
--

DROP TABLE IF EXISTS `post_img`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post_img` (
  `img` varchar(100) NOT NULL,
  `post_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`img`,`post_id`),
  KEY `FK1xffckk49kb41ulrxum2gx3n1` (`post_id`),
  CONSTRAINT `FK1xffckk49kb41ulrxum2gx3n1` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_img`
--

LOCK TABLES `post_img` WRITE;
/*!40000 ALTER TABLE `post_img` DISABLE KEYS */;
INSERT INTO `post_img` VALUES
('7b0f079f-88ee-4b8f-94ac-43baf137b95d.png',15),
('7a26dd7d-514c-4cb2-973b-d9a741a49008.png',16),
('b6854d08-ea41-46c1-8f3f-3a8eac5b3841.png',16),
('ead48f6e-9c8c-4ea8-8dec-8c4afe4910f2.png',16);
/*!40000 ALTER TABLE `post_img` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL DEFAULT current_timestamp(6),
  `updated_at` datetime(6) NOT NULL DEFAULT current_timestamp(6),
  `del_yn` tinyint(4) NOT NULL DEFAULT 0,
  `email` varchar(100) NOT NULL,
  `fixed_yn` tinyint(4) NOT NULL,
  `nick` varchar(50) NOT NULL,
  `uid` varchar(50) NOT NULL,
  `upw` varchar(100) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_a7hlm8sj8kmijx6ucp7wfyt31` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES
(1,'2023-11-23 18:56:16.334203','2023-11-23 18:56:16.334203',0,'test1@gmail.com',1,'test1','test01','test1234'),
(2,'2023-11-23 18:56:16.334203','2023-11-23 18:56:16.334203',0,'test2@gmail.com',1,'test2','test02','test1234'),
(3,'2023-11-23 18:56:16.334203','2023-11-23 18:56:16.334203',0,'test3@gmail.com',1,'test3','test03','test1234');
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

-- Dump completed on 2023-11-24 16:16:01
