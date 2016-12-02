# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.10)
# Database: BookBank
# Generation Time: 2016-12-02 15:08:26 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table Bill
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Bill`;

CREATE TABLE `Bill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dateOfBilling` varchar(50) DEFAULT NULL,
  `member_id` int(11) NOT NULL,
  `transaction_id` int(11) NOT NULL,
  `amount` int(11) DEFAULT NULL,
  `paid` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `member_id` (`member_id`),
  KEY `transaction_id` (`transaction_id`),
  CONSTRAINT `bill_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `Member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bill_ibfk_2` FOREIGN KEY (`transaction_id`) REFERENCES `Transaction` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

LOCK TABLES `Bill` WRITE;
/*!40000 ALTER TABLE `Bill` DISABLE KEYS */;

INSERT INTO `Bill` (`id`, `dateOfBilling`, `member_id`, `transaction_id`, `amount`, `paid`)
VALUES
	(4,'Wed Nov 30 18:14:20 EST 2016',8,11,10,0);

/*!40000 ALTER TABLE `Bill` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Book
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Book`;

CREATE TABLE `Book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL,
  `status` enum('available','unavailable') DEFAULT NULL,
  `genre` enum('science fiction','satire','drama','action and adventure','romance','mystery','horror','travel','kids','history','math','comic','cookbook','biography','autobiography','health','religion and spiritual','encyclopedia','dictionary') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

LOCK TABLES `Book` WRITE;
/*!40000 ALTER TABLE `Book` DISABLE KEYS */;

INSERT INTO `Book` (`id`, `title`, `author`, `status`, `genre`)
VALUES
	(1,'The Alchemist','Paulo Coelho','available','drama'),
	(2,'The Girl on the train','Paula Hawkins','available','romance'),
	(3,'In a Dark, Dark Wood','Ruth Ware','available','horror'),
	(4,'Eat Happy','Anna Vocino','available','cookbook'),
	(5,'The Perfume Collector','Kathleen Tessaro','available','romance'),
	(6,'Dune','Frank Herbert','available','science fiction'),
	(7,'Animal Farm','George Orwell','available','satire'),
	(8,'The call of the Wild','Jack London','available','action and adventure'),
	(9,'Gone Girl','Gillian Flynn','available','mystery'),
	(10,'The Great Railway Bazaar','Paul Theroux','available','travel'),
	(11,'Goodnight Moon','Margaret Wise','available','kids'),
	(12,'The guns of August','Barabara Tuchman','available','history'),
	(13,'Journey thriugh Genius','William Dunham','available','math'),
	(14,'BAtman: The killing joke','Alan Moore','available','comic');

/*!40000 ALTER TABLE `Book` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Member
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Member`;

CREATE TABLE `Member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

LOCK TABLES `Member` WRITE;
/*!40000 ALTER TABLE `Member` DISABLE KEYS */;

INSERT INTO `Member` (`id`, `firstname`, `lastname`, `address`, `email`, `username`, `password`)
VALUES
	(1,'Alice','Wonderland','USA','alice@wonderland.com','alice','alice'),
	(3,'Dhruv','Upadhyay','NH','dhruv@upadhyay.com','dhruv','dhruv'),
	(4,'Bob','Waltz','DC','bob@waltz.com','bob','bob'),
	(7,'Charlie','Brown','SFO','charlie@brown.com','charlie','charlie'),
	(8,'Kreena','Mehta','Boston','kreena@mehta.com','kreena','kreena');

/*!40000 ALTER TABLE `Member` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Transaction
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Transaction`;

CREATE TABLE `Transaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `dateOfIssue` varchar(50) DEFAULT NULL,
  `dateOfReturn` varchar(50) DEFAULT NULL,
  `bookReturned` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `member_id` (`member_id`),
  KEY `book_id` (`book_id`),
  CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `Member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `transaction_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `Book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

LOCK TABLES `Transaction` WRITE;
/*!40000 ALTER TABLE `Transaction` DISABLE KEYS */;

INSERT INTO `Transaction` (`id`, `member_id`, `book_id`, `dateOfIssue`, `dateOfReturn`, `bookReturned`)
VALUES
	(10,8,2,'Wed Nov 30 18:11:50 EST 2016','Wed Nov 30 18:12:50 EST 2016',1),
	(11,8,4,'Wed Nov 30 18:13:07 EST 2016','Wed Nov 30 18:14:07 EST 2016',1),
	(12,8,4,'Fri Dec 02 09:22:37 EST 2016','Fri Dec 02 09:23:37 EST 2016',1);

/*!40000 ALTER TABLE `Transaction` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
