-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Mar 06 Juin 2017 à 01:05
-- Version du serveur :  10.1.21-MariaDB
-- Version de PHP :  5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `gestionetudiant`
--

-- --------------------------------------------------------

--
-- Structure de la table `affecter`
--

CREATE TABLE `affecter` (
  `id_classe` int(11) NOT NULL,
  `Code_Prof` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `affecter`
--

INSERT INTO `affecter` (`id_classe`, `Code_Prof`) VALUES
(1, 2245),
(4, 1252),
(4, 5222);

-- --------------------------------------------------------

--
-- Structure de la table `classes`
--

CREATE TABLE `classes` (
  `id_classe` int(4) NOT NULL,
  `libelle_classe` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `classes`
--

INSERT INTO `classes` (`id_classe`, `libelle_classe`) VALUES
(1, 'BTS SIO 1'),
(4, 'BTS SIO 2'),
(5, 'TERMINAL STMG');

-- --------------------------------------------------------

--
-- Structure de la table `contient`
--

CREATE TABLE `contient` (
  `id_classe` int(11) NOT NULL,
  `id_eleve` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `contient`
--

INSERT INTO `contient` (`id_classe`, `id_eleve`) VALUES
(1, 1245789),
(1, 1234567891),
(4, 741852963);

-- --------------------------------------------------------

--
-- Structure de la table `eleves`
--

CREATE TABLE `eleves` (
  `Code_INE` int(10) NOT NULL,
  `nom_eleve` varchar(25) NOT NULL,
  `prenom_eleve` varchar(25) NOT NULL,
  `date_naiss` date NOT NULL,
  `adresse_eleve` varchar(255) NOT NULL,
  `tel_eleve` varchar(10) NOT NULL,
  `id_classe_eleve` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `eleves`
--

INSERT INTO `eleves` (`Code_INE`, `nom_eleve`, `prenom_eleve`, `date_naiss`, `adresse_eleve`, `tel_eleve`, `id_classe_eleve`) VALUES
(12485, 'Dalton', 'Avrelle', '1997-09-12', '17 rue famine', '0692365478', 4),
(1245789, 'Dalton', 'William', '1998-05-01', '12 rue grenade', '0692547896', 4),
(4587962, 'tata', 'tata', '1997-11-12', '8 rue chose', '0692759682', 1),
(7854269, 'tutu', 'tutu', '1997-02-11', '42 rue machin', '0692469718', 4),
(12485458, 'titi', 'titi', '1997-03-04', '4 rue Général de Gaulle', '0692451278', 4),
(124854584, 'toto', 'toto', '1997-02-10', '42 rue truc', '0692751486', 1),
(741852963, 'Dalton', 'Jack', '1997-04-06', '1 rue bam', '0692145698', 1),
(1234567891, 'Dalton', 'Joe', '1997-10-05', '1 rue bombe', '0692123456', 1);

-- --------------------------------------------------------

--
-- Structure de la table `enseigner`
--

CREATE TABLE `enseigner` (
  `id_prof` int(11) NOT NULL,
  `id_matiere` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `enseigner`
--

INSERT INTO `enseigner` (`id_prof`, `id_matiere`) VALUES
(2245, 4),
(5222, 6),
(5222, 8);

-- --------------------------------------------------------

--
-- Structure de la table `matieres`
--

CREATE TABLE `matieres` (
  `id_matiere` int(11) NOT NULL,
  `libelle_matiere` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `matieres`
--

INSERT INTO `matieres` (`id_matiere`, `libelle_matiere`) VALUES
(2, 'Math'),
(3, 'SI 1'),
(4, 'SI 2'),
(5, 'SI 3'),
(6, 'SI 4'),
(7, 'SI 5'),
(8, 'SI 6'),
(9, 'SLAM 1'),
(10, 'SLAM 2'),
(11, 'SISR 1'),
(12, 'SISR 2'),
(13, 'Français'),
(14, 'Anglais'),
(15, 'SLAM 3'),
(16, 'SLAM 4'),
(17, 'SLAM 5'),
(18, 'SISR 3'),
(19, 'SISR 4'),
(20, 'SISR 5');

-- --------------------------------------------------------

--
-- Structure de la table `professeurs`
--

CREATE TABLE `professeurs` (
  `Code_Prof` int(10) NOT NULL,
  `nom_prof` varchar(25) NOT NULL,
  `prenom_prof` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `professeurs`
--

INSERT INTO `professeurs` (`Code_Prof`, `nom_prof`, `prenom_prof`) VALUES
(1252, 'Math', 'Gep'),
(2245, 'Lucky', 'Luke'),
(5222, 'Truc', 'affaire');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

CREATE TABLE `utilisateurs` (
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`username`, `password`, `id_user`) VALUES
('User01', '123', 1),
('User02', '234', 2),
('Lucas', 'aaaa', 3),
('User03', '14', 4),
('user04', '12a', 6),
('Utilisateur1', '12', 8);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `affecter`
--
ALTER TABLE `affecter`
  ADD PRIMARY KEY (`id_classe`,`Code_Prof`);

--
-- Index pour la table `classes`
--
ALTER TABLE `classes`
  ADD PRIMARY KEY (`id_classe`);

--
-- Index pour la table `contient`
--
ALTER TABLE `contient`
  ADD PRIMARY KEY (`id_classe`,`id_eleve`),
  ADD KEY `id_eleve` (`id_eleve`);

--
-- Index pour la table `eleves`
--
ALTER TABLE `eleves`
  ADD PRIMARY KEY (`Code_INE`),
  ADD KEY `id_classe_eleve` (`id_classe_eleve`);

--
-- Index pour la table `enseigner`
--
ALTER TABLE `enseigner`
  ADD PRIMARY KEY (`id_prof`,`id_matiere`),
  ADD KEY `id_matiere` (`id_matiere`);

--
-- Index pour la table `matieres`
--
ALTER TABLE `matieres`
  ADD PRIMARY KEY (`id_matiere`);

--
-- Index pour la table `professeurs`
--
ALTER TABLE `professeurs`
  ADD PRIMARY KEY (`Code_Prof`);

--
-- Index pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `classes`
--
ALTER TABLE `classes`
  MODIFY `id_classe` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT pour la table `matieres`
--
ALTER TABLE `matieres`
  MODIFY `id_matiere` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `contient`
--
ALTER TABLE `contient`
  ADD CONSTRAINT `contient_ibfk_1` FOREIGN KEY (`id_classe`) REFERENCES `classes` (`id_classe`),
  ADD CONSTRAINT `contient_ibfk_2` FOREIGN KEY (`id_eleve`) REFERENCES `eleves` (`Code_INE`);

--
-- Contraintes pour la table `eleves`
--
ALTER TABLE `eleves`
  ADD CONSTRAINT `eleves_ibfk_1` FOREIGN KEY (`id_classe_eleve`) REFERENCES `classes` (`id_classe`);

--
-- Contraintes pour la table `enseigner`
--
ALTER TABLE `enseigner`
  ADD CONSTRAINT `enseigner_ibfk_1` FOREIGN KEY (`id_prof`) REFERENCES `professeurs` (`Code_Prof`),
  ADD CONSTRAINT `enseigner_ibfk_2` FOREIGN KEY (`id_matiere`) REFERENCES `matieres` (`id_matiere`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
