-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Nov 03, 2019 at 07:33 AM
-- Server version: 5.7.26
-- PHP Version: 7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `testdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `iteminfolist`
--

DROP TABLE IF EXISTS `iteminfolist`;
CREATE TABLE IF NOT EXISTS `iteminfolist` (
  `Item_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `Item_Type` int(11) NOT NULL,
  `Item_Name` varchar(64) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  PRIMARY KEY (`Item_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

--
-- Dumping data for table `iteminfolist`
--

INSERT INTO `iteminfolist` (`Item_ID`, `Item_Type`, `Item_Name`) VALUES
(1, 0, 'Pandog'),
(2, 1, 'Jowa'),
(3, 2, 'Yellow Pad');

-- --------------------------------------------------------

--
-- Table structure for table `itemownership`
--

DROP TABLE IF EXISTS `itemownership`;
CREATE TABLE IF NOT EXISTS `itemownership` (
  `OwnershipID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ItemID` bigint(20) DEFAULT NULL,
  `UserID` bigint(20) NOT NULL,
  PRIMARY KEY (`OwnershipID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

-- --------------------------------------------------------

--
-- Table structure for table `userauth`
--

DROP TABLE IF EXISTS `userauth`;
CREATE TABLE IF NOT EXISTS `userauth` (
  `UserID` bigint(20) NOT NULL,
  `AuthLevel` int(11) DEFAULT '0',
  UNIQUE KEY `UserID` (`UserID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userauth`
--

INSERT INTO `userauth` (`UserID`, `AuthLevel`) VALUES
(1, 1),
(3, -1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `UserID` bigint(20) NOT NULL AUTO_INCREMENT,
  `Login` varchar(32) NOT NULL,
  `Password` varchar(32) NOT NULL,
  `Nickname` varchar(32) DEFAULT '',
  `IPAddress` varchar(32) DEFAULT '0.0.0.0',
  `GamePoints` bigint(20) NOT NULL DEFAULT '30',
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `Login` (`Login`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`UserID`, `Login`, `Password`, `Nickname`, `IPAddress`, `GamePoints`) VALUES
(1, 'Admin', 'pass', 'Administrator', '0.0.0.0', 30),
(2, 'User', 'test', 'DarkKnight', '0.0.0.0', 30),
(3, 'Banned', '123', 'Ban User', '0.0.0.0', 30);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
