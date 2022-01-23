-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 23 Jan 2022 pada 04.25
-- Versi server: 10.4.21-MariaDB
-- Versi PHP: 7.4.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `payroll`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_gaji_karyawan`
--

CREATE TABLE `data_gaji_karyawan` (
  `id_gaji` int(5) NOT NULL,
  `id_karyawan` int(5) NOT NULL,
  `id_jabatan` int(5) NOT NULL,
  `gaji_pokok` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `data_gaji_karyawan`
--

INSERT INTO `data_gaji_karyawan` (`id_gaji`, `id_karyawan`, `id_jabatan`, `gaji_pokok`) VALUES
(2, 1, 1121, 10000000),
(3, 2, 1122, 2500000),
(4, 3, 1123, 4500000),
(5, 4, 1124, 2000000),
(6, 5, 1125, 2500000),
(7, 6, 1126, 2000000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_karyawan`
--

CREATE TABLE `data_karyawan` (
  `id_karyawan` int(5) NOT NULL,
  `nik` int(5) NOT NULL,
  `nama_karyawan` varchar(255) NOT NULL,
  `id_jabatan` int(5) NOT NULL,
  `no_hp` int(25) NOT NULL,
  `alamat` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `data_karyawan`
--

INSERT INTO `data_karyawan` (`id_karyawan`, `nik`, `nama_karyawan`, `id_jabatan`, `no_hp`, `alamat`) VALUES
(1, 12345, 'Ujang Surajang', 1121, 882317098, 'Kp.Surakan'),
(2, 12346, 'Dasep Ahmad', 1122, 851535111, 'Kp.Anjuang'),
(3, 12347, 'Geugeut Ansyah', 1123, 882317080, 'Kp.Ciucai'),
(4, 12348, 'Yogut Agut', 1124, 851535122, 'Kp.Sarimun'),
(5, 12349, 'Junto Anto', 1125, 882317077, 'Kp.Ciacau'),
(6, 12350, 'Edward akward', 1126, 851535333, 'Kp.Indahasri');

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_rekap_absensi`
--

CREATE TABLE `data_rekap_absensi` (
  `id_rekap_absensi` int(5) NOT NULL,
  `kode_absensi` int(5) NOT NULL,
  `id_karyawan` int(5) NOT NULL,
  `id_jabatan` int(5) NOT NULL,
  `bolos` int(2) NOT NULL,
  `sakit` int(2) NOT NULL,
  `izin` int(2) NOT NULL,
  `bulan` enum('Januari','Februari','Maret','April','Mei','Juni','Juli','Agustus','Oktober','September','November','Desember') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `data_rekap_absensi`
--

INSERT INTO `data_rekap_absensi` (`id_rekap_absensi`, `kode_absensi`, `id_karyawan`, `id_jabatan`, `bolos`, `sakit`, `izin`, `bulan`) VALUES
(1, 11001, 1, 1121, 1, 2, 3, 'Desember'),
(2, 11002, 2, 1122, 0, 1, 9, 'Desember'),
(3, 11003, 3, 1123, 0, 0, 0, 'Desember'),
(4, 11004, 4, 1124, 0, 0, 0, 'Desember'),
(7, 11005, 5, 1125, 0, 0, 0, 'Desember'),
(8, 11006, 6, 1126, 0, 0, 0, 'Desember');

-- --------------------------------------------------------

--
-- Struktur dari tabel `jabatan`
--

CREATE TABLE `jabatan` (
  `id_jabatan` int(5) NOT NULL,
  `nama_jabatan` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `jabatan`
--

INSERT INTO `jabatan` (`id_jabatan`, `nama_jabatan`) VALUES
(1121, 'Supervisor'),
(1122, 'Kasir'),
(1123, 'Sales'),
(1124, 'Office Boy'),
(1125, 'Satpam'),
(1126, 'Supir');

-- --------------------------------------------------------

--
-- Struktur dari tabel `laporan`
--

CREATE TABLE `laporan` (
  `id_laporan` int(5) NOT NULL,
  `kode_laporan` int(5) NOT NULL,
  `id_karyawan` int(5) NOT NULL,
  `id_jabatan` int(5) NOT NULL,
  `id_gaji` int(5) NOT NULL,
  `id_rekap_absensi` int(5) NOT NULL,
  `total_gaji` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `laporan`
--

INSERT INTO `laporan` (`id_laporan`, `kode_laporan`, `id_karyawan`, `id_jabatan`, `id_gaji`, `id_rekap_absensi`, `total_gaji`) VALUES
(10111, 10111, 1, 1121, 2, 1, 9810000),
(10112, 10112, 2, 1122, 3, 2, 0),
(10113, 10113, 3, 1123, 4, 3, 0),
(21104, 21104, 4, 1124, 5, 4, 0),
(21105, 21105, 5, 1125, 6, 7, 0);

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `id_user` int(5) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `level` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`id_user`, `username`, `password`, `level`) VALUES
(1, 'admin', 'admin', 0),
(2, 'owner', 'owner', 1);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `data_gaji_karyawan`
--
ALTER TABLE `data_gaji_karyawan`
  ADD PRIMARY KEY (`id_gaji`),
  ADD KEY `id_karyawan` (`id_karyawan`,`id_jabatan`),
  ADD KEY `id_jabatan` (`id_jabatan`);

--
-- Indeks untuk tabel `data_karyawan`
--
ALTER TABLE `data_karyawan`
  ADD PRIMARY KEY (`id_karyawan`),
  ADD KEY `id_jabatan` (`id_jabatan`);

--
-- Indeks untuk tabel `data_rekap_absensi`
--
ALTER TABLE `data_rekap_absensi`
  ADD PRIMARY KEY (`id_rekap_absensi`),
  ADD KEY `id_jabatan` (`id_jabatan`),
  ADD KEY `id_karyawan` (`id_karyawan`);

--
-- Indeks untuk tabel `jabatan`
--
ALTER TABLE `jabatan`
  ADD PRIMARY KEY (`id_jabatan`);

--
-- Indeks untuk tabel `laporan`
--
ALTER TABLE `laporan`
  ADD PRIMARY KEY (`id_laporan`),
  ADD KEY `id_karyawan` (`id_karyawan`),
  ADD KEY `id_jabatan` (`id_jabatan`),
  ADD KEY `id_gaji` (`id_gaji`),
  ADD KEY `id_rekap_absensi` (`id_rekap_absensi`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `data_gaji_karyawan`
--
ALTER TABLE `data_gaji_karyawan`
  MODIFY `id_gaji` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT untuk tabel `data_karyawan`
--
ALTER TABLE `data_karyawan`
  MODIFY `id_karyawan` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT untuk tabel `data_rekap_absensi`
--
ALTER TABLE `data_rekap_absensi`
  MODIFY `id_rekap_absensi` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT untuk tabel `laporan`
--
ALTER TABLE `laporan`
  MODIFY `id_laporan` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21106;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `data_gaji_karyawan`
--
ALTER TABLE `data_gaji_karyawan`
  ADD CONSTRAINT `data_gaji_karyawan_ibfk_1` FOREIGN KEY (`id_karyawan`) REFERENCES `data_karyawan` (`id_karyawan`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `data_gaji_karyawan_ibfk_2` FOREIGN KEY (`id_jabatan`) REFERENCES `jabatan` (`id_jabatan`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `data_karyawan`
--
ALTER TABLE `data_karyawan`
  ADD CONSTRAINT `data_karyawan_ibfk_1` FOREIGN KEY (`id_jabatan`) REFERENCES `jabatan` (`id_jabatan`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `data_rekap_absensi`
--
ALTER TABLE `data_rekap_absensi`
  ADD CONSTRAINT `data_rekap_absensi_ibfk_1` FOREIGN KEY (`id_jabatan`) REFERENCES `jabatan` (`id_jabatan`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `data_rekap_absensi_ibfk_2` FOREIGN KEY (`id_karyawan`) REFERENCES `data_karyawan` (`id_karyawan`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `laporan`
--
ALTER TABLE `laporan`
  ADD CONSTRAINT `laporan` FOREIGN KEY (`id_gaji`) REFERENCES `data_gaji_karyawan` (`id_gaji`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `laporan_ibfk_1` FOREIGN KEY (`id_jabatan`) REFERENCES `jabatan` (`id_jabatan`),
  ADD CONSTRAINT `laporan_ibfk_2` FOREIGN KEY (`id_karyawan`) REFERENCES `data_karyawan` (`id_karyawan`),
  ADD CONSTRAINT `laporan_ibfk_3` FOREIGN KEY (`id_rekap_absensi`) REFERENCES `data_rekap_absensi` (`id_rekap_absensi`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
