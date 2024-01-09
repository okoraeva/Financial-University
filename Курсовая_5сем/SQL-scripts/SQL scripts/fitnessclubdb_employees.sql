-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: fitnessclubdb
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `employee_id` int NOT NULL AUTO_INCREMENT,
  `employee_name` varchar(100) NOT NULL,
  `employee_position` varchar(50) NOT NULL,
  `employee_email` varchar(255) NOT NULL,
  `employee_phone` varchar(20) NOT NULL,
  `employee_address` varchar(255) NOT NULL,
  `employee_salary` int NOT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1,'Сидоров Владимир','Управляющий','ivanov@example.com','+7(123)456-78-90','г. Москва, ул. Ленина, д. 10',90000),(2,'Шилов Петр','Администратор','petrov@example.com','+7(456)789-01-23','г. Москва, ул. Пушкина, д. 5',70000),(3,'Сидорова Анна','Инструктор по ТБ','sidorova@example.com','+7(789)012-34-56','г. Москва, ул. Гагарина, д. 15',65000),(4,'Козлов Иван','Инструктор','kozlov@example.com','+7(012)345-67-89','г. Москва, ул. Мира, д. 20',60000),(5,'Смирнова Елена','Тренер','smirnova@example.com','+7(234)567-89-01','г. Москва, ул. Лермонтова, д. 25',50000),(6,'Васильева Ольга','Тренер','vasilieva@example.com','+7(567)890-12-34','г. Москва, ул. Толстого, д. 30',50000),(7,'Николаев Андрей','Уборщик','nikolaev@example.com','+7(890)123-45-67','г. Москва, ул. Пушкина, д. 35',35000),(8,'Морозова Софья','Менеджер','morozova@example.com','+7(901)234-56-78','г. Москва, ул. Гагарина, д. 40',55000),(9,'Кузнецов Сергей','Менеджер','kuznetsov@example.com','+7(123)456-78-90','г. Москва, ул. Мира, д. 45',40000),(10,'Андреева Любовь','Тренер','andreeva@example.com','+7(456)789-01-23','г. Москва, ул. Лермонтова, д. 50',40000),(11,'Петров Борис','Тренер','petrov@example.com','+7(923)894-32-16','г. Москва ул. Ленина, д. 42',40000);
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-16 15:17:41
