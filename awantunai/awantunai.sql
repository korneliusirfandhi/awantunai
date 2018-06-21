-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 21, 2018 at 12:59 PM
-- Server version: 10.1.25-MariaDB
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `awantunai`
--

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE `member` (
  `ID` bigint(20) NOT NULL,
  `AccountNo` varchar(255) DEFAULT NULL,
  `Balance` bigint(20) DEFAULT NULL,
  `JoinDate` datetime DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `NIK` varchar(255) DEFAULT NULL,
  `NPWP` varchar(255) DEFAULT NULL,
  `PIN` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`ID`, `AccountNo`, `Balance`, `JoinDate`, `Name`, `NIK`, `NPWP`, `PIN`) VALUES
(1, '82075319', 250000, '2018-06-21 15:53:47', 'Kornelius Irfandhi', '1234567890123456', '12.345.678.9-012.000', 'dd6e5e5918e94d997c686fcebc56922f'),
(2, '95929005', 2800000, '2018-06-21 16:18:36', 'Kornelius Irfandhi', '1234567890123456', '12.345.678.9-012.000', 'dd6e5e5918e94d997c686fcebc56922f'),
(3, '89454478', 0, '2018-06-21 17:20:35', 'Kornelius Irfandhi', '1234567890123456', '12.345.678.9-012.000', 'dd6e5e5918e94d997c686fcebc56922f');

-- --------------------------------------------------------

--
-- Table structure for table `transactionhistory`
--

CREATE TABLE `transactionhistory` (
  `ID` bigint(20) NOT NULL,
  `AccountNo1` varchar(255) DEFAULT NULL,
  `AccountNo2` varchar(255) DEFAULT NULL,
  `Credit` bigint(20) DEFAULT NULL,
  `Debet` bigint(20) DEFAULT NULL,
  `LastBalance` bigint(20) DEFAULT NULL,
  `TrxDate` datetime DEFAULT NULL,
  `Type` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transactionhistory`
--

INSERT INTO `transactionhistory` (`ID`, `AccountNo1`, `AccountNo2`, `Credit`, `Debet`, `LastBalance`, `TrxDate`, `Type`) VALUES
(1, '95929005', NULL, 100000, NULL, 100000, '2018-06-21 17:25:10', 'DEPOSIT'),
(2, '95929005', NULL, 100000, NULL, 100000, '2018-06-21 17:25:26', 'DEPOSIT'),
(3, '95929005', NULL, 100000, NULL, 100000, '2018-06-21 17:29:45', 'DEPOSIT'),
(4, '95929005', NULL, 100000, NULL, 200000, '2018-06-21 17:29:59', 'DEPOSIT'),
(5, '95929005', NULL, NULL, 100000, 100000, '2018-06-21 17:31:33', 'WITHDRAWAL'),
(6, '95929005', NULL, NULL, 20000, 80000, '2018-06-21 17:31:59', 'WITHDRAWAL'),
(7, '95929005', NULL, NULL, 30000, 50000, '2018-06-21 17:40:59', 'WITHDRAWAL'),
(8, '95929005', NULL, 3000000, NULL, 3050000, '2018-06-21 17:41:26', 'DEPOSIT'),
(9, '95929005', '82075319', NULL, 250000, 2800000, '2018-06-21 17:44:12', 'TRANSFER_SEND'),
(10, '82075319', '95929005', 250000, NULL, 250000, '2018-06-21 17:44:12', 'TRANSFER_RECEIVE');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `transactionhistory`
--
ALTER TABLE `transactionhistory`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `transactionhistory`
--
ALTER TABLE `transactionhistory`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
