-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : Dim 15 jan. 2023 à 19:10
-- Version du serveur :  5.7.31
-- Version de PHP : 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `airport_controller`
--

-- --------------------------------------------------------

--
-- Structure de la table `avion`
--

DROP TABLE IF EXISTS `avion`;
CREATE TABLE IF NOT EXISTS `avion` (
  `identifiant` char(20) NOT NULL,
  `etat` char(20) NOT NULL,
  `reservoir` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `avion`
--

INSERT INTO `avion` (`identifiant`, `etat`, `reservoir`) VALUES
('EMB-145XR', 'New', 100),
('A320-214', 'New', 100),
('737-824', 'New', 100),
('EMB-145LR', 'New', 100),
('A321-211', 'New', 100),
('767-332', 'New', 100),
('021222888', 'New', 100);

-- --------------------------------------------------------

--
-- Structure de la table `station`
--

DROP TABLE IF EXISTS `station`;
CREATE TABLE IF NOT EXISTS `station` (
  `Name` char(20) NOT NULL,
  `positionX` int(11) NOT NULL,
  `positionY` int(11) NOT NULL,
  `capaciteAvions` int(11) NOT NULL,
  `CapaciteCarb` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `station`
--

INSERT INTO `station` (`Name`, `positionX`, `positionY`, `capaciteAvions`, `CapaciteCarb`) VALUES
('ALG', 185, 220, 3, 2000),
('ORY', 192, 180, 4, 4000),
('LAX', 0, 300, 6, 2000),
('DME', 120, 0, 6, 2000),
('BCN', 120, 50, 3, 2000),
('HND', 0, 0, 5, 4000),
('SYD', 300, 300, 6, 1000);

-- --------------------------------------------------------

--
-- Structure de la table `vol`
--

DROP TABLE IF EXISTS `vol`;
CREATE TABLE IF NOT EXISTS `vol` (
  `Aeroport de depart` char(20) NOT NULL,
  `Aeroport d'arrivée` char(20) NOT NULL,
  `Numero vol` char(20) NOT NULL,
  `Vol_type` int(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `vol`
--

INSERT INTO `vol` (`Aeroport de depart`, `Aeroport d'arrivée`, `Numero vol`, `Vol_type`) VALUES
('ALG', 'LAX', '1', 1),
('SYD', 'HND', '2', 1),
('ORY', 'BCN', '3', 0);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
