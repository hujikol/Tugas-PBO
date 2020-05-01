-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 01 Bulan Mei 2020 pada 09.15
-- Versi server: 10.4.6-MariaDB
-- Versi PHP: 7.3.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `praktikum`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `fakultas`
--

CREATE TABLE `fakultas` (
  `ID_Fakultas` int(11) NOT NULL,
  `Nama` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `fakultas`
--

INSERT INTO `fakultas` (`ID_Fakultas`, `Nama`) VALUES
(110, 'Teknologi Mineral'),
(120, 'Teknik Industri'),
(130, 'Ekonomi & Bisnis'),
(140, 'Ilmu Sosial & Ilmu Politik'),
(150, 'Pertanian');

-- --------------------------------------------------------

--
-- Struktur dari tabel `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `NIM` int(9) NOT NULL,
  `Nama` varchar(255) NOT NULL,
  `Alamat` varchar(255) NOT NULL,
  `ID_Fakultas` int(11) NOT NULL,
  `ID_Prodi` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `mahasiswa`
--

INSERT INTO `mahasiswa` (`NIM`, `Nama`, `Alamat`, `ID_Fakultas`, `ID_Prodi`) VALUES
(123180049, 'sulaksana', 'pandowoharjo sleman', 120, 123),
(132180049, 'nanda', 'sleman', 130, 132),
(142180125, 'kartoyono', 'belok kanan', 140, 142);

-- --------------------------------------------------------

--
-- Struktur dari tabel `prodi`
--

CREATE TABLE `prodi` (
  `ID_Prodi` int(11) NOT NULL,
  `ID_Fakultas` int(11) NOT NULL,
  `Nama` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `prodi`
--

INSERT INTO `prodi` (`ID_Prodi`, `ID_Fakultas`, `Nama`) VALUES
(111, 110, 'Teknik Geologi'),
(112, 110, 'Teknik Pertambangan'),
(113, 110, 'Teknik Perminyakan'),
(114, 110, 'Teknik Geofisika'),
(115, 110, 'Teknik Lingkungan'),
(116, 110, 'Teknik Metalurgi'),
(117, 110, 'Teknik Geomatika'),
(121, 120, 'Teknik Kimia'),
(122, 120, 'Teknik Industri'),
(123, 120, 'Informatika'),
(124, 120, 'Sistem Informasi'),
(131, 130, 'Manajemen'),
(132, 130, 'Akuntansi'),
(133, 130, 'Ilmu Ekonomi Pembangunan'),
(141, 140, 'Ilmu Hubungan Internasional'),
(142, 140, 'Ilmu Administrasi Bisnis'),
(143, 140, 'Ilmu Komunikasi'),
(144, 140, 'Hubungan Masyarakat'),
(151, 150, 'Agribisnis'),
(152, 150, 'Agroteknologi'),
(153, 150, 'Ilmu Tanah');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `fakultas`
--
ALTER TABLE `fakultas`
  ADD PRIMARY KEY (`ID_Fakultas`);

--
-- Indeks untuk tabel `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`NIM`),
  ADD KEY `ID_Prodi` (`ID_Prodi`),
  ADD KEY `ID_Fakultas` (`ID_Fakultas`);

--
-- Indeks untuk tabel `prodi`
--
ALTER TABLE `prodi`
  ADD PRIMARY KEY (`ID_Prodi`),
  ADD KEY `ID_Fakultas` (`ID_Fakultas`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD CONSTRAINT `mahasiswa_ibfk_2` FOREIGN KEY (`ID_Prodi`) REFERENCES `prodi` (`ID_Prodi`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `mahasiswa_ibfk_3` FOREIGN KEY (`ID_Fakultas`) REFERENCES `fakultas` (`ID_Fakultas`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `prodi`
--
ALTER TABLE `prodi`
  ADD CONSTRAINT `prodi_ibfk_1` FOREIGN KEY (`ID_Fakultas`) REFERENCES `fakultas` (`ID_Fakultas`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
