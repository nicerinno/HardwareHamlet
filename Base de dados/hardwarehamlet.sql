-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 09-Nov-2018 às 11:13
-- Versão do servidor: 10.1.36-MariaDB
-- versão do PHP: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hardwarehamlet`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `builds`
--

CREATE TABLE `builds` (
  `build_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `buildtype_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(500) NOT NULL,
  `cpu_description` varchar(500) NOT NULL,
  `gpu_description` varchar(500) NOT NULL,
  `ram_description` varchar(500) NOT NULL,
  `likes` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `build_components`
--

CREATE TABLE `build_components` (
  `build_id` int(11) NOT NULL,
  `component_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `build_type`
--

CREATE TABLE `build_type` (
  `buildtype_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `comments`
--

CREATE TABLE `comments` (
  `comment_id` int(11) NOT NULL,
  `build_id` int(11) NOT NULL,
  `content` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `components`
--

CREATE TABLE `components` (
  `component_id` int(11) NOT NULL,
  `component_type_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `brand` varchar(100) NOT NULL,
  `name` varchar(150) NOT NULL,
  `description` varchar(500) NOT NULL,
  `price` varchar(50) NOT NULL,
  `flg_available` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `component_type`
--

CREATE TABLE `component_type` (
  `component_type_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `medals`
--

CREATE TABLE `medals` (
  `medal_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `icon` varchar(100) NOT NULL,
  `amount_likes` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `titles`
--

CREATE TABLE `titles` (
  `title_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `rep_amount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `title_id` int(11) NOT NULL,
  `usertype_id` int(11) NOT NULL,
  `medal_id` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `description` varchar(500) NOT NULL,
  `reputation` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `user_type`
--

CREATE TABLE `user_type` (
  `usertype_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `builds`
--
ALTER TABLE `builds`
  ADD PRIMARY KEY (`build_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `buildtype_id` (`buildtype_id`);

--
-- Indexes for table `build_components`
--
ALTER TABLE `build_components`
  ADD PRIMARY KEY (`build_id`,`component_id`),
  ADD KEY `component_id` (`component_id`);

--
-- Indexes for table `build_type`
--
ALTER TABLE `build_type`
  ADD PRIMARY KEY (`buildtype_id`);

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`comment_id`),
  ADD KEY `build_id` (`build_id`);

--
-- Indexes for table `components`
--
ALTER TABLE `components`
  ADD PRIMARY KEY (`component_id`),
  ADD KEY `component_type_id` (`component_type_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `component_type`
--
ALTER TABLE `component_type`
  ADD PRIMARY KEY (`component_type_id`);

--
-- Indexes for table `medals`
--
ALTER TABLE `medals`
  ADD PRIMARY KEY (`medal_id`);

--
-- Indexes for table `titles`
--
ALTER TABLE `titles`
  ADD PRIMARY KEY (`title_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD KEY `title_id` (`title_id`),
  ADD KEY `usertype_id` (`usertype_id`),
  ADD KEY `medal_id` (`medal_id`);

--
-- Indexes for table `user_type`
--
ALTER TABLE `user_type`
  ADD PRIMARY KEY (`usertype_id`);

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `builds`
--
ALTER TABLE `builds`
  ADD CONSTRAINT `builds_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `builds_ibfk_2` FOREIGN KEY (`buildtype_id`) REFERENCES `build_type` (`buildtype_id`);

--
-- Limitadores para a tabela `build_components`
--
ALTER TABLE `build_components`
  ADD CONSTRAINT `build_components_ibfk_1` FOREIGN KEY (`build_id`) REFERENCES `builds` (`build_id`),
  ADD CONSTRAINT `build_components_ibfk_2` FOREIGN KEY (`component_id`) REFERENCES `components` (`component_id`);

--
-- Limitadores para a tabela `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`build_id`) REFERENCES `builds` (`build_id`);

--
-- Limitadores para a tabela `components`
--
ALTER TABLE `components`
  ADD CONSTRAINT `components_ibfk_1` FOREIGN KEY (`component_type_id`) REFERENCES `component_type` (`component_type_id`),
  ADD CONSTRAINT `components_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Limitadores para a tabela `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`title_id`) REFERENCES `titles` (`title_id`),
  ADD CONSTRAINT `users_ibfk_2` FOREIGN KEY (`usertype_id`) REFERENCES `user_type` (`usertype_id`),
  ADD CONSTRAINT `users_ibfk_3` FOREIGN KEY (`medal_id`) REFERENCES `medals` (`medal_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
