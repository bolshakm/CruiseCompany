-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: сruise_company
-- ------------------------------------------------------
-- Server version	5.5.46-log

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
-- Table structure for table `bonuses`
--

DROP TABLE IF EXISTS `bonuses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bonuses` (
  `id_bonus` int(11) NOT NULL AUTO_INCREMENT,
  `bonus_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id_bonus`),
  UNIQUE KEY `id_bonuses_UNIQUE` (`id_bonus`),
  UNIQUE KEY `bonuse_name_UNIQUE` (`bonus_name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bonuses`
--

LOCK TABLES `bonuses` WRITE;
/*!40000 ALTER TABLE `bonuses` DISABLE KEYS */;
INSERT INTO `bonuses` VALUES (1,'Empty'),(6,'Gym'),(2,'Restaurant'),(5,'SPA'),(3,'VIP Pool');
/*!40000 ALTER TABLE `bonuses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bonuses_has_ships`
--

DROP TABLE IF EXISTS `bonuses_has_ships`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bonuses_has_ships` (
  `ships_id_ship` int(11) NOT NULL,
  `bonuses_id_bonus` int(11) NOT NULL,
  PRIMARY KEY (`ships_id_ship`,`bonuses_id_bonus`),
  KEY `fk_bonuses_has_ships_ships1_idx` (`ships_id_ship`),
  KEY `fk_bonuses_has_ships_bonuses1_idx` (`bonuses_id_bonus`),
  CONSTRAINT `fk_bonuses_has_ships_bonuses1` FOREIGN KEY (`bonuses_id_bonus`) REFERENCES `bonuses` (`id_bonus`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_bonuses_has_ships_ships1` FOREIGN KEY (`ships_id_ship`) REFERENCES `ships` (`id_ship`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bonuses_has_ships`
--

LOCK TABLES `bonuses_has_ships` WRITE;
/*!40000 ALTER TABLE `bonuses_has_ships` DISABLE KEYS */;
INSERT INTO `bonuses_has_ships` VALUES (1,1),(1,2),(1,6),(2,1),(2,2),(2,3),(3,2),(3,3),(20,1),(21,2),(21,3),(21,6),(24,2),(24,3),(24,5);
/*!40000 ALTER TABLE `bonuses_has_ships` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cruise_statuses`
--

DROP TABLE IF EXISTS `cruise_statuses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cruise_statuses` (
  `id_cruise_status` int(11) NOT NULL AUTO_INCREMENT,
  `cruise_status_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id_cruise_status`),
  UNIQUE KEY `id_cruise_statuse_UNIQUE` (`id_cruise_status`),
  UNIQUE KEY `cruise_statuse_name_UNIQUE` (`cruise_status_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cruise_statuses`
--

LOCK TABLES `cruise_statuses` WRITE;
/*!40000 ALTER TABLE `cruise_statuses` DISABLE KEYS */;
INSERT INTO `cruise_statuses` VALUES (3,'Done'),(2,'During'),(1,'Reserved');
/*!40000 ALTER TABLE `cruise_statuses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cruises`
--

DROP TABLE IF EXISTS `cruises`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cruises` (
  `id_cruise` int(11) NOT NULL AUTO_INCREMENT,
  `cruise_name` varchar(45) NOT NULL,
  `cruise_from` date NOT NULL,
  `cruise_to` date NOT NULL,
  `ships_id_ship` int(11) NOT NULL,
  `cruise_statuses_id_cruise_status` int(11) NOT NULL,
  `routes_id_route` int(11) NOT NULL,
  PRIMARY KEY (`id_cruise`,`ships_id_ship`,`cruise_statuses_id_cruise_status`,`routes_id_route`),
  UNIQUE KEY `id_сruise_UNIQUE` (`id_cruise`),
  UNIQUE KEY `сruise_name_UNIQUE` (`cruise_name`),
  KEY `fk_cruises_ships1_idx` (`ships_id_ship`),
  KEY `fk_cruises_cruise_statuses1_idx` (`cruise_statuses_id_cruise_status`),
  KEY `fk_cruises_routes1_idx` (`routes_id_route`),
  CONSTRAINT `fk_cruises_cruise_statuses1` FOREIGN KEY (`cruise_statuses_id_cruise_status`) REFERENCES `cruise_statuses` (`id_cruise_status`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cruises_routes1` FOREIGN KEY (`routes_id_route`) REFERENCES `routes` (`id_route`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cruises_ships1` FOREIGN KEY (`ships_id_ship`) REFERENCES `ships` (`id_ship`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cruises`
--

LOCK TABLES `cruises` WRITE;
/*!40000 ALTER TABLE `cruises` DISABLE KEYS */;
INSERT INTO `cruises` VALUES (1,'Rast ring','2018-03-12','2018-03-20',3,1,1),(2,'Big amerika','2018-03-21','2018-04-01',2,1,15),(3,'Great Europe','2018-03-21','2018-04-03',3,3,2),(8,'New Super Cruise','2018-04-26','2018-04-28',21,1,2),(11,'Moon light','2018-05-05','2018-05-10',24,1,1),(12,'Mirage','2018-05-11','2018-05-12',24,3,2);
/*!40000 ALTER TABLE `cruises` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `excursions`
--

DROP TABLE IF EXISTS `excursions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `excursions` (
  `id_excursion` int(11) NOT NULL AUTO_INCREMENT,
  `excursion_name` varchar(45) NOT NULL,
  `excursion_price` decimal(7,2) NOT NULL,
  `ports_id_port` int(11) NOT NULL,
  PRIMARY KEY (`id_excursion`,`ports_id_port`),
  UNIQUE KEY `id_excursion_UNIQUE` (`id_excursion`),
  UNIQUE KEY `excursion_name_UNIQUE` (`excursion_name`),
  KEY `fk_excursions_ports1_idx` (`ports_id_port`),
  CONSTRAINT `fk_excursions_ports1` FOREIGN KEY (`ports_id_port`) REFERENCES `ports` (`id_port`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `excursions`
--

LOCK TABLES `excursions` WRITE;
/*!40000 ALTER TABLE `excursions` DISABLE KEYS */;
INSERT INTO `excursions` VALUES (1,'Mariupol View',123.00,1),(2,'testExcursion2',100.00,2),(3,'testEx3',200.00,2),(7,'test5',223.00,2);
/*!40000 ALTER TABLE `excursions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ports`
--

DROP TABLE IF EXISTS `ports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ports` (
  `id_port` int(11) NOT NULL AUTO_INCREMENT,
  `port_name` varchar(45) NOT NULL,
  `port_city` varchar(45) NOT NULL,
  `port_country` varchar(45) NOT NULL,
  PRIMARY KEY (`id_port`),
  UNIQUE KEY `id_port_UNIQUE` (`id_port`),
  UNIQUE KEY `port_name_UNIQUE` (`port_name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ports`
--

LOCK TABLES `ports` WRITE;
/*!40000 ALTER TABLE `ports` DISABLE KEYS */;
INSERT INTO `ports` VALUES (1,'Mariupol ','Mariupol','Ukraine'),(2,'Berdiansk','Berdiansk','Ukraine'),(3,'Izmail','Izmail','Ukraine'),(6,'Kerch','Kerch','Ukraine'),(7,'Milan','Milan','Italy');
/*!40000 ALTER TABLE `ports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ports_has_routes`
--

DROP TABLE IF EXISTS `ports_has_routes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ports_has_routes` (
  `ports_id_port` int(11) NOT NULL,
  `routes_id_route` int(11) NOT NULL,
  PRIMARY KEY (`ports_id_port`,`routes_id_route`),
  KEY `fk_ports_has_routes_routes1_idx` (`routes_id_route`),
  KEY `fk_ports_has_routes_ports1_idx` (`ports_id_port`),
  CONSTRAINT `fk_ports_has_routes_ports1` FOREIGN KEY (`ports_id_port`) REFERENCES `ports` (`id_port`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ports_has_routes_routes1` FOREIGN KEY (`routes_id_route`) REFERENCES `routes` (`id_route`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ports_has_routes`
--

LOCK TABLES `ports_has_routes` WRITE;
/*!40000 ALTER TABLE `ports_has_routes` DISABLE KEYS */;
INSERT INTO `ports_has_routes` VALUES (1,1),(3,1),(6,1),(1,2),(2,2),(3,2),(6,2),(1,15),(6,15),(7,15);
/*!40000 ALTER TABLE `ports_has_routes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id_role` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id_role`),
  UNIQUE KEY `id_roles_UNIQUE` (`id_role`),
  UNIQUE KEY `role_name_UNIQUE` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'administrator'),(5,'capitan'),(8,'cook'),(7,'matros'),(3,'ship administrator'),(4,'staff'),(2,'user');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `routes`
--

DROP TABLE IF EXISTS `routes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `routes` (
  `id_route` int(11) NOT NULL AUTO_INCREMENT,
  `route_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id_route`),
  UNIQUE KEY `id_route_UNIQUE` (`id_route`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `routes`
--

LOCK TABLES `routes` WRITE;
/*!40000 ALTER TABLE `routes` DISABLE KEYS */;
INSERT INTO `routes` VALUES (1,'Midnight party'),(2,'Black sea'),(15,'Golden ring');
/*!40000 ALTER TABLE `routes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ship_has_ticket_types_has_bonuses`
--

DROP TABLE IF EXISTS `ship_has_ticket_types_has_bonuses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ship_has_ticket_types_has_bonuses` (
  `ticket_types_id_ticket_type` int(11) NOT NULL,
  `bonuses_id_bonus` int(11) NOT NULL,
  `ships_id_ship` int(11) NOT NULL,
  PRIMARY KEY (`ticket_types_id_ticket_type`,`bonuses_id_bonus`,`ships_id_ship`),
  KEY `fk_ticket_types_has_bonuses_bonuses1_idx` (`bonuses_id_bonus`),
  KEY `fk_ticket_types_has_bonuses_ticket_types1_idx` (`ticket_types_id_ticket_type`),
  KEY `fk_ticket_types_has_bonuses_ships1_idx` (`ships_id_ship`),
  CONSTRAINT `fk_ticket_types_has_bonuses_bonuses1` FOREIGN KEY (`bonuses_id_bonus`) REFERENCES `bonuses` (`id_bonus`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ticket_types_has_bonuses_ships1` FOREIGN KEY (`ships_id_ship`) REFERENCES `ships` (`id_ship`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ticket_types_has_bonuses_ticket_types1` FOREIGN KEY (`ticket_types_id_ticket_type`) REFERENCES `ticket_types` (`id_ticket_type`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ship_has_ticket_types_has_bonuses`
--

LOCK TABLES `ship_has_ticket_types_has_bonuses` WRITE;
/*!40000 ALTER TABLE `ship_has_ticket_types_has_bonuses` DISABLE KEYS */;
INSERT INTO `ship_has_ticket_types_has_bonuses` VALUES (1,1,2),(1,1,3),(1,1,20),(1,1,24),(2,1,21),(2,1,24),(3,1,24),(1,2,21),(1,3,21);
/*!40000 ALTER TABLE `ship_has_ticket_types_has_bonuses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ship_types`
--

DROP TABLE IF EXISTS `ship_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ship_types` (
  `id_ship_type` int(11) NOT NULL AUTO_INCREMENT,
  `ship_type_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id_ship_type`),
  UNIQUE KEY `ship_type_name_UNIQUE` (`ship_type_name`),
  UNIQUE KEY `id_ship_types_UNIQUE` (`id_ship_type`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ship_types`
--

LOCK TABLES `ship_types` WRITE;
/*!40000 ALTER TABLE `ship_types` DISABLE KEYS */;
INSERT INTO `ship_types` VALUES (3,'Business'),(6,'Econom'),(1,'Empty'),(2,'Normal'),(4,'Vip');
/*!40000 ALTER TABLE `ship_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ships`
--

DROP TABLE IF EXISTS `ships`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ships` (
  `id_ship` int(11) NOT NULL AUTO_INCREMENT,
  `ship_name` varchar(45) NOT NULL,
  `ship_number` varchar(45) NOT NULL,
  `number_of_seats` int(11) NOT NULL,
  `price_per_seat` decimal(7,2) NOT NULL DEFAULT '0.00',
  `ship_types_id_ship_type` int(11) NOT NULL,
  PRIMARY KEY (`id_ship`,`ship_types_id_ship_type`),
  UNIQUE KEY `id_ship_UNIQUE` (`id_ship`),
  UNIQUE KEY `ship_number_UNIQUE` (`ship_number`),
  KEY `fk_ships_ship_types1_idx` (`ship_types_id_ship_type`),
  CONSTRAINT `fk_ships_ship_types1` FOREIGN KEY (`ship_types_id_ship_type`) REFERENCES `ship_types` (`id_ship_type`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ships`
--

LOCK TABLES `ships` WRITE;
/*!40000 ALTER TABLE `ships` DISABLE KEYS */;
INSERT INTO `ships` VALUES (1,' ',' ',0,0.00,1),(2,'Titanic','ti1234',800,700.00,3),(3,'Anna','an123',352,500.00,4),(20,'Aurora','2223a',331,125.00,2),(21,'Lastochka ','126qwe',400,200.00,3),(24,'Star','122223',244,122.00,2);
/*!40000 ALTER TABLE `ships` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_types`
--

DROP TABLE IF EXISTS `ticket_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_types` (
  `id_ticket_type` int(11) NOT NULL AUTO_INCREMENT,
  `ticket_type_name` varchar(45) NOT NULL,
  `ticket_type_price` decimal(8,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id_ticket_type`),
  UNIQUE KEY `id_ticket_types_UNIQUE` (`id_ticket_type`),
  UNIQUE KEY `ticket_type_name_UNIQUE` (`ticket_type_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_types`
--

LOCK TABLES `ticket_types` WRITE;
/*!40000 ALTER TABLE `ticket_types` DISABLE KEYS */;
INSERT INTO `ticket_types` VALUES (1,'Standart',100.00),(2,'Standart+',150.00),(3,'vip',300.00),(4,'VIP+',500.00);
/*!40000 ALTER TABLE `ticket_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tickets`
--

DROP TABLE IF EXISTS `tickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tickets` (
  `id_ticket` int(11) NOT NULL AUTO_INCREMENT,
  `users_id_user` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `ticket_types_id_ticket_type` int(11) NOT NULL,
  `cruises_id_cruise` int(11) NOT NULL,
  `price` decimal(8,2) NOT NULL,
  PRIMARY KEY (`id_ticket`,`users_id_user`,`ticket_types_id_ticket_type`,`cruises_id_cruise`),
  UNIQUE KEY `id_ticket_UNIQUE` (`id_ticket`),
  KEY `fk_tickets_users1_idx` (`users_id_user`),
  KEY `fk_tickets_ticket_types1_idx` (`ticket_types_id_ticket_type`),
  KEY `fk_tickets_cruises1_idx` (`cruises_id_cruise`),
  CONSTRAINT `fk_tickets_cruises1` FOREIGN KEY (`cruises_id_cruise`) REFERENCES `cruises` (`id_cruise`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tickets_ticket_types1` FOREIGN KEY (`ticket_types_id_ticket_type`) REFERENCES `ticket_types` (`id_ticket_type`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tickets_users1` FOREIGN KEY (`users_id_user`) REFERENCES `users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tickets`
--

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
INSERT INTO `tickets` VALUES (3,3,'def','defa',1,3,1323.00),(4,2,'def','def',2,1,373.00),(5,2,'def','def',2,3,950.00),(6,3,'def1','def1',2,2,1373.00),(8,3,'name2','lname2',1,2,1323.00),(9,3,'name2','lname2',1,3,1100.00),(10,3,'name2','lname2',1,2,1100.00),(11,3,'name2','lname2',3,3,1300.00),(12,3,'name2','lname2',2,8,600.00),(14,3,'Andrey','Mahno',2,8,673.00),(19,8,'danil','Danko',1,11,547.00),(20,2,'Ivan','Ivanenko',2,12,495.00),(21,8,'danil','Danko',2,12,372.00),(22,8,'Ivan','Danko',2,12,372.00),(23,8,'Marina','Danko',2,12,472.00),(24,2,'Ivan','Ivanenko',3,12,645.00),(25,9,'taras','tarasenko',2,12,495.00);
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tickets_has_bonuses`
--

DROP TABLE IF EXISTS `tickets_has_bonuses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tickets_has_bonuses` (
  `tickets_id_ticket` int(11) NOT NULL,
  `bonuses_id_bonus` int(11) NOT NULL,
  PRIMARY KEY (`tickets_id_ticket`,`bonuses_id_bonus`),
  KEY `fk_tickets_has_bonuses_bonuses1_idx` (`bonuses_id_bonus`),
  KEY `fk_tickets_has_bonuses_tickets1_idx` (`tickets_id_ticket`),
  CONSTRAINT `fk_tickets_has_bonuses_bonuses1` FOREIGN KEY (`bonuses_id_bonus`) REFERENCES `bonuses` (`id_bonus`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tickets_has_bonuses_tickets1` FOREIGN KEY (`tickets_id_ticket`) REFERENCES `tickets` (`id_ticket`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tickets_has_bonuses`
--

LOCK TABLES `tickets_has_bonuses` WRITE;
/*!40000 ALTER TABLE `tickets_has_bonuses` DISABLE KEYS */;
INSERT INTO `tickets_has_bonuses` VALUES (3,1),(20,2),(21,2),(22,2),(23,2),(24,2),(25,2),(3,3),(20,3),(21,3),(22,3),(23,3),(24,3),(25,3),(24,5),(24,6);
/*!40000 ALTER TABLE `tickets_has_bonuses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tickets_has_excursions`
--

DROP TABLE IF EXISTS `tickets_has_excursions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tickets_has_excursions` (
  `excursions_id_excursion` int(11) NOT NULL,
  `tickets_id_ticket` int(11) NOT NULL,
  PRIMARY KEY (`excursions_id_excursion`,`tickets_id_ticket`),
  KEY `fk_tickets_has_excursions_tickets1_idx` (`tickets_id_ticket`),
  CONSTRAINT `fk_tickets_has_excursions_excursions1` FOREIGN KEY (`excursions_id_excursion`) REFERENCES `excursions` (`id_excursion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tickets_has_excursions_tickets1` FOREIGN KEY (`tickets_id_ticket`) REFERENCES `tickets` (`id_ticket`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tickets_has_excursions`
--

LOCK TABLES `tickets_has_excursions` WRITE;
/*!40000 ALTER TABLE `tickets_has_excursions` DISABLE KEYS */;
INSERT INTO `tickets_has_excursions` VALUES (1,3),(2,3),(3,3),(7,3),(1,4),(2,5),(2,6),(3,6),(7,6),(2,8),(3,8),(7,8),(2,9),(3,9),(2,10),(3,10),(2,11),(3,11),(2,12),(3,12),(2,14),(3,14),(7,14),(1,19),(1,20),(2,20),(2,21),(2,22),(3,23),(1,24),(2,24),(1,25),(2,25);
/*!40000 ALTER TABLE `tickets_has_excursions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `user_last_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `money` decimal(9,2) NOT NULL DEFAULT '0.00',
  `roles_id_role` int(11) NOT NULL,
  `ships_id_ship` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_user`,`roles_id_role`,`ships_id_ship`),
  UNIQUE KEY `id_users_UNIQUE` (`id_user`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fk_users_roles_idx` (`roles_id_role`),
  KEY `fk_users_ships1_idx` (`ships_id_ship`),
  CONSTRAINT `fk_users_roles` FOREIGN KEY (`roles_id_role`) REFERENCES `roles` (`id_role`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_ships1` FOREIGN KEY (`ships_id_ship`) REFERENCES `ships` (`id_ship`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','1234','admin','adminenko','admin@mail.com',495390.00,1,1),(2,'user','1234','Ivan','Ivanenko','name1@mail.com',6311.00,2,1),(3,'user2','1234','Anton','Antonenko','anton@mail.com',11000.00,2,1),(4,'stuff','1234','Kolia','Krivoy','kolia@mail.com',1111.00,8,21),(5,'ship','1234','Denis','Denisov','denis@mail.com',2000.00,3,21),(6,'user5','1234','Anatolyy','Tarasov','tarasov@mail.com',100.00,3,24),(8,'danil','1234','danil','Danko','danil@mai.com',6665.00,2,1),(9,'taras','1234','taras','tarasenko','taras@mail.com',4505.00,2,1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-18 19:29:53
