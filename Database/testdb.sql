-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Oct 20, 2019 at 10:59 AM
-- Server version: 5.7.21
-- PHP Version: 5.6.35

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
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `Login` (`Login`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`UserID`, `Login`, `Password`, `Nickname`, `IPAddress`) VALUES
(1, 'Admin', 'pass', 'Administrator', '0.0.0.0'),
(2, 'User', 'test', 'Test', '0.0.0.0'),
(3, 'Banned', '123', 'Ban User', '0.0.0.0');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
