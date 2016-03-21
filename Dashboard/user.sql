/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.20 : Database - tms
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`tms` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `honeycomb`;

/*Table structure for table `honeycomb` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
`id` varchar(255) NOT NULL,
`email` varchar(255) DEFAULT NULL,
`password` varchar(255) DEFAULT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`email`,`password`) values ('1', 'honeycomb@gmail.com', '123456')
