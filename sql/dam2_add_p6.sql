-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-02-2021 a las 19:43:40
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dam2_add_p5`
--
CREATE DATABASE IF NOT EXISTS `dam2_add_p5` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `dam2_add_p5`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `preguntas`
--

CREATE TABLE `preguntas` (
  `Pregunta` varchar(500) NOT NULL,
  `RespuestaA` varchar(500) NOT NULL,
  `RespuestaB` varchar(500) NOT NULL,
  `RespuestaC` varchar(500) NOT NULL,
  `Correcta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `preguntas`
--

INSERT INTO `preguntas` (`Pregunta`, `RespuestaA`, `RespuestaB`, `RespuestaC`, `Correcta`) VALUES
('A.m. es una abreviación que se sitúa detrás de una hora. ¿Qué significado tiene?', 'Antes de merendar', 'Antes del meridiano', 'Antes del mediodía', 2),
('El Estrecho de Bering se encuentra entre dos grandes países. ¿Cuáles son?', 'España y Marruecos', 'Francia y Reino Unido', 'Estados Unidos y Rusia', 3),
('¿A qué grupo musical pertenece Master of Puppets?', 'Nirvana', 'ACDC', 'Metallica', 3),
('¿Cómo se llamaba el caballo que pertenecía a Don Quijote de La Mancha?', 'Ferrari', 'Babieca', 'Rocinante', 3),
('¿Cuál era la moneda utilizada en España antes del euro?', 'La Libra', 'La Peseta', 'El Doblón', 2),
('¿Cuál es la capital actual de Marruecos?', 'Rabat', 'Marrakech', 'Casablanca', 1),
('¿Cuál es la planta principal con la que se hace el tequila?', 'El agave', 'Caña de Azucar', 'Trigo', 1),
('¿Cuál es la velocidad de la luz?', '120 km/h', '300.000 km/s', '120 km/s', 2),
('¿En qué año la humanidad pisó la Luna por primera vez a bordo de la nave Apolo 11?', '1979', '1968', '1969', 3),
('¿En qué año se disolvió la Unión Soviética?', '1989', '1991', '1990', 2),
('¿Qué jugador es considerado el mejor de la historia en baloncesto?', 'Michael Jordan', 'Kobe Bryant', 'Larry Bird', 1),
('¿Qué película hizo famoso al director James Cameron?', 'Titanic', 'Avatar', 'Pulp Fiction', 1),
('¿Qué tenista masculino cuenta con más Grand Slams?', 'Rafael Nadal', 'Roger Federer', 'Pete Sampras', 2),
('¿Quién es el autor de la Ilíada y la Odisea?', 'Ulises', 'Homero', 'Hércules', 1),
('¿Quién fue el primer presidente de la democracia española después del franquismo?', 'Francisco Suarez', 'Alfonso Suarez', 'Adolfo Suarez', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `records`
--

CREATE TABLE `records` (
  `nombre` varchar(100) NOT NULL,
  `aciertos` varchar(100) NOT NULL,
  `fallos` varchar(100) NOT NULL,
  `puntuacion` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `records`
--

INSERT INTO `records` (`nombre`, `aciertos`, `fallos`, `puntuacion`) VALUES
('Juan', '8', '7', '36.5'),
('Pedro', '0', '15', '-7.5'),
('Ruben', '15', '0', '75.0');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `preguntas`
--
ALTER TABLE `preguntas`
  ADD PRIMARY KEY (`Pregunta`);

--
-- Indices de la tabla `records`
--
ALTER TABLE `records`
  ADD PRIMARY KEY (`nombre`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
