-- MySQL dump 10.13  Distrib 8.0.36, for macos14 (x86_64)
--
-- Host: localhost    Database: library_manager
-- ------------------------------------------------------
-- Server version	8.0.37

CREATE DATABASE quanlymem;
USE quanlymem;

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
-- Table structure for table `Authors`
--

DROP TABLE IF EXISTS `Authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Authors` (
  `author_id` int NOT NULL,
  `author_name` varchar(255) DEFAULT NULL,
  `author_biography` text,
  PRIMARY KEY (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Authors`
--




LOCK TABLES `Authors` WRITE;
/*!40000 ALTER TABLE `Authors` DISABLE KEYS */;
INSERT INTO `Authors` VALUES (1,'Nguyễn Nhật Ánh','Tác giả nổi tiếng với những tác phẩm văn học dành cho tuổi trẻ.'),(2,'Nguyễn Du','Tác giả của \"Truyện Kiều\" - một trong những kiệt tác văn học của Việt Nam.'),(3,'Tô Hoài','Nhà văn nổi tiếng Việt Nam, tác giả của \"Lão Hạc\".'),(4,'George Orwell','Tác giả của \"1984\" và \"Trại súc vật\".'),(5,'Jane Austen','Tác giả của \"Tự do\" và \"Đại học Emma\".'),(6,'Harper Lee','Tác giả của \"Giết con chim nhại\".'),(7,'J.K. Rowling','Nữ nhà văn nổi tiếng với loạt sách \"Harry Potter\".'),(8,'Victor Hugo','Tác giả của \"Les Misérables\" và \"The Hunchback of Notre-Dame\".'),(9,'Leo Tolstoy','Tác giả của \"Chiến tranh và hòa bình\" và \"Anna Karenina\".'),(10,'Gabriel García Márquez','Tác giả của \"Trăm năm cô đơn\" và \"Tình yêu trong thời cholera\".'),(11,'Hermann Hesse','Tác giả của \"Siddhartha\" và \"Demian\".'),(12,'Agatha Christie','Nữ tác giả tài năng với thể loại trinh thám.'),(13,'Emily Brontë','Tác giả của \"Nhà thờ đứng trên đồng cỏ\"'),(14,'Fyodor Dostoevsky','Tác giả của \"Tội ác và hình phạt\" và \"Anh em Karamazov\".'),(15,'Mark Twain','Tác giả của \"Cuộc phiêu lưu của Tom Sawyer\" và \"Huckleberry Finn\".'),(16,'H.G. Wells','Tác giả của \"Chiến tranh của thế giới\" và \"Người ngoài hành tinh đến từ Mars\".'),(17,'Arthur Conan Doyle','Tác giả của loạt truyện \"Sherlock Holmes\".'),(18,'J.R.R. Tolkien','Tác giả của \"The Lord of the Rings\" và \"The Hobbit\".'),(19,'Dan Brown','Tác giả của \"Mật mã Da Vinci\" và \"Thế giới mất tích\".'),(20,'Paulo Coelho','Tác giả nổi tiếng thế giới với những tác phẩm tâm linh và triết học.');
/*!40000 ALTER TABLE `Authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Books`
--

DROP TABLE IF EXISTS `Books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Books` (
  `book_id` int NOT NULL,
  `book_title` varchar(255) DEFAULT NULL,
  `book_isbn` varchar(20) DEFAULT NULL,
  `author_id` int DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `book_published_year` int DEFAULT NULL,
  `book_number_of_copies` int DEFAULT NULL,
  PRIMARY KEY (`book_id`),
  KEY `author_id` (`author_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `books_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `Authors` (`author_id`),
  CONSTRAINT `books_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `Categories` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Books`
--

LOCK TABLES `Books` WRITE;
/*!40000 ALTER TABLE `Books` DISABLE KEYS */;
INSERT INTO `Books` VALUES (1,'Tôi thấy hoa vàng trên cỏ xanh','9786048559206',1,1,2005,5),(2,'Truyện Kiều','9786048011970',2,1,1803,3),(3,'Lão Hạc','9786048020583',3,1,1961,4),(4,'1984','9780141036144',4,1,1949,2),(5,'Tự do','9780141439587',5,1,1815,3),(6,'Giết con chim nhại','9780061120084',6,1,1960,4),(7,'Harry Potter và Hòn Đá Phù Thủy','9781408855652',7,1,1997,5),(8,'Les Misérables','9780451419439',8,1,1862,3),(9,'Chiến tranh và hòa bình','9780394603478',9,1,1869,4),(10,'Trăm năm cô đơn','9780307474728',10,1,1967,2),(11,'Siddhartha','9780553208849',11,1,1922,3),(12,'Cái chết đến thăm Ông Bảy','9780062071080',12,1,1939,4),(13,'Nhà thờ đứng trên đồng cỏ','9780141439556',13,1,1847,3),(14,'Tội ác và hình phạt','9780451528616',14,1,1866,2),(15,'Cuộc phiêu lưu của Tom Sawyer','9780486400778',15,1,1876,3),(16,'Chiến tranh của thế giới','9780141441764',16,1,1898,4),(17,'Sherlock Holmes: Số Nhà 221B, Baker Street','9780143107132',17,1,1892,2),(18,'The Lord of the Rings: The Fellowship of the Ring','9780618645619',18,1,1954,3),(19,'Mật mã Da Vinci','9780307277671',19,1,2003,4),(20,'Brida','9780061578953',20,1,1990,5);
/*!40000 ALTER TABLE `Books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Categories`
--

DROP TABLE IF EXISTS `Categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Categories` (
  `category_id` int NOT NULL,
  `category_name` varchar(255) DEFAULT NULL,
  `category_description` text,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Categories`
--

LOCK TABLES `Categories` WRITE;
/*!40000 ALTER TABLE `Categories` DISABLE KEYS */;
INSERT INTO `Categories` VALUES (1,'Văn học','Các tác phẩm văn học, tiểu thuyết, truyện ngắn.'),(2,'Khoa học','Các sách liên quan đến khoa học tự nhiên và xã hội.'),(3,'Kinh tế','Sách về kinh tế, quản lý kinh doanh, và tài chính.'),(4,'Tâm lý học','Các sách nói về tâm lý học và phát triển cá nhân.'),(5,'Lịch sử','Sách về lịch sử thế giới và lịch sử Việt Nam.');
/*!40000 ALTER TABLE `Categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Loans`
--

DROP TABLE IF EXISTS `Loans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Loans` (
  `loan_id` int NOT NULL,
  `book_id` int DEFAULT NULL,
  `member_id` int DEFAULT NULL,
  `loan_date` date DEFAULT NULL,
  `loan_due_date` date DEFAULT NULL,
  `loan_return_date` date DEFAULT NULL,
  PRIMARY KEY (`loan_id`),
  KEY `book_id` (`book_id`),
  KEY `member_id` (`member_id`),
  CONSTRAINT `loans_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `Books` (`book_id`),
  CONSTRAINT `loans_ibfk_2` FOREIGN KEY (`member_id`) REFERENCES `Members` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Loans`
--

LOCK TABLES `Loans` WRITE;
/*!40000 ALTER TABLE `Loans` DISABLE KEYS */;
INSERT INTO `Loans` VALUES (1,1,1,'2024-06-01','2024-06-15',NULL),(2,2,2,'2024-06-02','2024-06-16',NULL),(3,3,3,'2024-06-03','2024-06-17',NULL),(4,4,4,'2024-06-04','2024-06-18',NULL),(5,5,5,'2024-06-05','2024-06-19',NULL),(6,6,6,'2024-06-06','2024-06-20',NULL),(7,7,7,'2024-06-07','2024-06-21',NULL),(8,8,8,'2024-06-08','2024-06-22',NULL),(9,9,9,'2024-06-09','2024-06-23',NULL),(10,10,10,'2024-06-10','2024-06-24',NULL),(11,11,11,'2024-06-11','2024-06-25',NULL),(12,12,12,'2024-06-12','2024-06-26',NULL),(13,13,13,'2024-06-13','2024-06-27',NULL),(14,14,14,'2024-06-14','2024-06-28',NULL),(15,15,15,'2024-06-15','2024-06-29',NULL),(16,16,16,'2024-06-16','2024-06-30',NULL),(17,17,17,'2024-06-17','2024-07-01',NULL),(18,18,18,'2024-06-18','2024-07-02',NULL),(19,19,19,'2024-06-19','2024-07-03',NULL),(20,20,20,'2024-06-20','2024-07-04',NULL);
/*!40000 ALTER TABLE `Loans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Members`
--

DROP TABLE IF EXISTS `Members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Members` (
  `member_id` int NOT NULL auto_increment,
  `member_name` varchar(255) DEFAULT NULL,
  `member_address` varchar(255) DEFAULT NULL,
  `member_phone` varchar(20) DEFAULT NULL,
  `member_email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Members`
--

LOCK TABLES `Members` WRITE;
/*!40000 ALTER TABLE `Members` DISABLE KEYS */;
INSERT INTO `Members` VALUES (1,'Nguyễn Văn A','123 Đường ABC, Quận 1, TP.HCM','0901234567','nguyenvana@example.com'),(2,'Trần Thị B','456 Đường XYZ, Quận 2, TP.HCM','0909876543','tranthib@example.com'),(3,'Lê Văn C','789 Đường DEF, Quận 3, TP.HCM','0903333333','levanc@example.com'),(4,'Phạm Thị D','012 Đường GHI, Quận 4, TP.HCM','0904444444','phamthid@example.com'),(5,'Hoàng Văn E','567 Đường KLM, Quận 5, TP.HCM','0905555555','hoangvane@example.com'),(6,'Mai Thị F','678 Đường NOP, Quận 6, TP.HCM','0906666666','maithif@example.com'),(7,'Đinh Văn G','890 Đường QRS, Quận 7, TP.HCM','0907777777','dinhvang@example.com'),(8,'Nguyễn Thị H','234 Đường TUV, Quận 8, TP.HCM','0908888888','nguyenthih@example.com'),(9,'Trần Văn I','567 Đường WXY, Quận 9, TP.HCM','0909999999','tranvani@example.com'),(10,'Phan Thị K','678 Đường ZAB, Quận 10, TP.HCM','0901010101','phanthik@example.com'),(11,'Lê Thị L','789 Đường CDE, Quận 11, TP.HCM','0901212121','lethil@example.com'),(12,'Trần Văn M','890 Đường FGH, Quận 12, TP.HCM','0901313131','tranvanm@example.com'),(13,'Nguyễn Thị N','123 Đường IJK, Quận Bình Thạnh, TP.HCM','0901414141','nguyenthin@example.com'),(14,'Hoàng Văn O','234 Đường LMN, Quận Gò Vấp, TP.HCM','0901515151','hoangvano@example.com'),(15,'Lê Thị P','345 Đường OPQ, Quận Phú Nhuận, TP.HCM','0901616161','lethip@example.com'),(16,'Nguyễn Văn Q','456 Đường RST, Quận Tân Bình, TP.HCM','0901717171','nguyenvanq@example.com'),(17,'Trần Thị R','567 Đường UVW, Quận Tân Phú, TP.HCM','0901818181','tranthir@example.com'),(18,'Hoàng Văn S','678 Đường XYZ, Quận Bình Tân, TP.HCM','0901919191','hoangvans@example.com'),(19,'Mai Thị T','789 Đường ABC, Quận Thủ Đức, TP.HCM','0902020202','maithit@example.com'),(20,'Nguyễn Văn U','890 Đường DEF, Quận Bình Chánh, TP.HCM','0902121212','nguyenvanu@example.com');
/*!40000 ALTER TABLE `Members` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-04 22:32:47

SET PASSWORD FOR root@localhost='root';
