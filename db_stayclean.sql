-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 20, 2023 at 12:41 PM
-- Server version: 5.7.33
-- PHP Version: 7.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_stayclean`
--

DELIMITER $$
--
-- Functions
--
CREATE DEFINER=`root`@`localhost` FUNCTION `tambah_id_akun` (`nomor` INT) RETURNS CHAR(7) CHARSET latin1 BEGIN
DECLARE kode char(7);
DECLARE urut int;

set urut = IF(nomor is null, 1, nomor + 1);
set kode = CONCAT("AKUN",LPAD(urut, 3, 0));

RETURN kode;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `tambah_id_detailPaket` (`nomor` INT) RETURNS CHAR(7) CHARSET latin1 BEGIN
DECLARE kode char(7);
DECLARE urut int;

set urut = IF(nomor is null, 1, nomor + 1);
set kode = CONCAT("DPKT",LPAD(urut, 3, 0));

RETURN kode;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `tambah_id_paket` (`nomor` INT) RETURNS CHAR(7) CHARSET latin1 BEGIN
DECLARE kode char(7);
DECLARE urut int;

set urut = IF(nomor is null, 1, nomor + 1);
set kode = CONCAT("PAKET",LPAD(urut, 2, 0));

RETURN kode;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `tambah_id_pelanggan` (`nomor` INT) RETURNS CHAR(7) CHARSET latin1 BEGIN
DECLARE kode char(7);
DECLARE urut int;

set urut = IF(nomor is null, 1, nomor + 1);
set kode = CONCAT("CUST",LPAD(urut, 3, 0));

RETURN kode;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `tambah_id_rak` (`nomor` INT) RETURNS CHAR(5) CHARSET latin1 BEGIN
DECLARE kode char(5);
DECLARE urut int;

set urut = IF(nomor is null, 1, nomor + 1);
set kode = CONCAT("RAK",LPAD(urut, 2, 0));

RETURN kode;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `tambah_id_transaksi` (`nomor` INT) RETURNS CHAR(7) CHARSET latin1 BEGIN
DECLARE kode char(7);
DECLARE urut int;

set urut = IF(nomor is null, 1, nomor + 1);
set kode = CONCAT("SC",LPAD(urut, 5, 0));

RETURN kode;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `akun`
--

CREATE TABLE `akun` (
  `id_akun` char(7) NOT NULL,
  `scan_ktp` char(10) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `hak_akses` enum('Admin','Karyawan') NOT NULL,
  `email` varchar(150) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `akun`
--

INSERT INTO `akun` (`id_akun`, `scan_ktp`, `nama`, `hak_akses`, `email`, `username`, `password`) VALUES
('AKUN001', '1339198213', 'admin', 'Admin', 'rayasya.dziqi@gmail.com', 'admin', 'admin'),
('AKUN002', '1283123123', 'asdnjkasjdnk', 'Admin', 'jnkjanskdjn@gmail.com', 'jahsdka', 'kjnaksjdn');

--
-- Triggers `akun`
--
DELIMITER $$
CREATE TRIGGER `trigger_idAkun` BEFORE INSERT ON `akun` FOR EACH ROW BEGIN
DECLARE i char(7);
DECLARE j integer;

set j = (SELECT substring(id_akun, 5, 6) as nomor
         from akun order by nomor desc limit 1);
set i = (SELECT tambah_id_akun(j));

IF(new.id_akun is null or new.id_akun = '')
then set new.id_akun = i;
end IF;
end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `detail_paket`
--

CREATE TABLE `detail_paket` (
  `id_detailPaket` char(7) NOT NULL,
  `id_paket` char(7) NOT NULL,
  `nama_paket` varchar(50) NOT NULL,
  `harga_paket` int(11) NOT NULL,
  `durasi_paket (hari)` int(11) NOT NULL,
  `durasi_paket (jam)` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detail_paket`
--

INSERT INTO `detail_paket` (`id_detailPaket`, `id_paket`, `nama_paket`, `harga_paket`, `durasi_paket (hari)`, `durasi_paket (jam)`) VALUES
('DPKT001', 'PAKET01', 'Cuci Setrika >5 Kg', 6000, 2, 0),
('DPKT002', 'PAKET01', 'Cuci Setrika <5 Kg', 7000, 2, 0),
('DPKT003', 'PAKET01', 'Setrika', 5000, 2, 0),
('DPKT004', 'PAKET01', 'Cuci Lipat', 5000, 2, 0),
('DPKT005', 'PAKET02', 'Cuci Lipat', 7000, 1, 0),
('DPKT006', 'PAKET02', 'Cuci Setrika (1 hari)', 9000, 1, 0),
('DPKT007', 'PAKET02', 'Cuci Setrika (6 jam)', 11000, 0, 6),
('DPKT008', 'PAKET02', 'Cuci Setrika (4 jam)', 14000, 0, 4),
('DPKT009', 'PAKET03', 'Selimut', 16000, 1, 0),
('DPKT010', 'PAKET03', 'Bed Cover', 20000, 1, 0),
('DPKT011', 'PAKET03', 'Jaket', 13000, 1, 0);

--
-- Triggers `detail_paket`
--
DELIMITER $$
CREATE TRIGGER `trigger_idDetailPaket` BEFORE INSERT ON `detail_paket` FOR EACH ROW BEGIN
DECLARE i char(7);
DECLARE j integer;

set j = (SELECT substring(id_detailPaket, 5, 6) as nomor
         from detail_paket order by nomor desc limit 1);
set i = (SELECT tambah_id_detailPaket(j));

IF(new.id_detailPaket is null or new.id_detailPaket = '')
then set new.id_detailPaket = i;
end IF;
end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `detail_transaksi`
--

CREATE TABLE `detail_transaksi` (
  `id_transaksi` char(7) NOT NULL,
  `id_detailPaket` char(7) NOT NULL,
  `id_rak` char(5) NOT NULL,
  `total_qty` int(11) NOT NULL,
  `jumlah_satuan` int(11) NOT NULL,
  `sub_total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detail_transaksi`
--

INSERT INTO `detail_transaksi` (`id_transaksi`, `id_detailPaket`, `id_rak`, `total_qty`, `jumlah_satuan`, `sub_total`) VALUES
('SC00001', 'DPKT001', 'RAK01', 12, 6, 36000),
('SC00001', 'DPKT004', 'RAK02', 3, 2, 14000),
('SC00001', 'DPKT010', 'RAK04', 1, 1, 20000),
('SC00002', 'DPKT009', 'RAK01', 1, 1, 16000),
('SC00002', 'DPKT004', 'RAK03', 5, 12, 84000),
('SC00002', 'DPKT002', 'RAK03', 12, 2, 14000),
('SC00002', 'DPKT009', 'RAK04', 1, 1, 16000),
('SC00002', 'DPKT010', 'RAK04', 1, 1, 20000),
('SC00003', 'DPKT011', 'RAK07', 1, 1, 13000),
('SC00003', 'DPKT007', 'RAK03', 2, 3, 33000),
('SC00004', 'DPKT007', 'RAK05', 5, 5, 55000),
('SC00004', 'DPKT004', 'RAK03', 2, 3, 21000);

-- --------------------------------------------------------

--
-- Table structure for table `paket`
--

CREATE TABLE `paket` (
  `id_paket` char(7) NOT NULL,
  `tipe_paket` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `paket`
--

INSERT INTO `paket` (`id_paket`, `tipe_paket`) VALUES
('PAKET01', 'Regular'),
('PAKET02', 'Express'),
('PAKET03', 'Satuan');

--
-- Triggers `paket`
--
DELIMITER $$
CREATE TRIGGER `trigger_idPaket` BEFORE INSERT ON `paket` FOR EACH ROW BEGIN
DECLARE i char(7);
DECLARE j integer;

set j = (SELECT substring(id_paket, 6, 6) as nomor
         from paket order by nomor desc limit 1);
set i = (SELECT tambah_id_paket(j));

IF(new.id_paket is null or new.id_paket = '')
then set new.id_paket = i;
end IF;
end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `id_pelanggan` char(7) NOT NULL,
  `nama_pelanggan` varchar(50) NOT NULL,
  `nope_pelanggan` varchar(15) NOT NULL,
  `alamat_pelanggan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`id_pelanggan`, `nama_pelanggan`, `nope_pelanggan`, `alamat_pelanggan`) VALUES
('CUST001', 'Muhammad Rayasya Dziqi Cahyana', '083115761027', 'Jalan jalan'),
('CUST002', 'Dila', '081234567890', 'Jalan kota'),
('CUST003', 'Budi Setiawan', '083115761027', 'jalan jalan'),
('CUST004', 'Dila Adelia Juliarti', '083115761027', 'jalan bunga mawar');

--
-- Triggers `pelanggan`
--
DELIMITER $$
CREATE TRIGGER `trigger_idPelanggan` BEFORE INSERT ON `pelanggan` FOR EACH ROW BEGIN
DECLARE i char(7);
DECLARE j integer;

set j = (SELECT substring(id_pelanggan, 5, 6) as nomor
         from pelanggan order by nomor desc limit 1);
set i = (SELECT tambah_id_pelanggan(j));

IF(new.id_pelanggan is null or new.id_pelanggan = '')
then set new.id_pelanggan = i;
end IF;
end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `pengeluaran`
--

CREATE TABLE `pengeluaran` (
  `id_pengeluaran` char(7) NOT NULL,
  `nama_pengeluaran` varchar(50) NOT NULL,
  `kategori` varchar(50) NOT NULL,
  `tagihan` int(11) NOT NULL,
  `tanggal_pengeluaran` date NOT NULL,
  `deskripsi` text NOT NULL,
  `tanggal_input` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pengeluaran`
--

INSERT INTO `pengeluaran` (`id_pengeluaran`, `nama_pengeluaran`, `kategori`, `tagihan`, `tanggal_pengeluaran`, `deskripsi`, `tanggal_input`) VALUES
('', 'contoh', 'Bulanan', 23423, '2023-06-02', 'aASDASDASDasdasd', '2023-06-19 01:01:09');

-- --------------------------------------------------------

--
-- Table structure for table `rak`
--

CREATE TABLE `rak` (
  `id_rak` char(5) NOT NULL,
  `nama_rak` varchar(10) NOT NULL,
  `status` enum('Penuh','Belum Penuh','Kosong') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rak`
--

INSERT INTO `rak` (`id_rak`, `nama_rak`, `status`) VALUES
('RAK01', 'RAK 1', 'Penuh'),
('RAK02', 'RAK 2', 'Kosong'),
('RAK03', 'RAK 3', 'Belum Penuh'),
('RAK04', 'RAK 4', 'Kosong'),
('RAK05', 'RAK 5', 'Kosong'),
('RAK06', 'RAK 6', 'Kosong'),
('RAK07', 'RAK 7', 'Kosong'),
('RAK08', 'RAK 8', 'Kosong'),
('RAK09', 'RAK 9', 'Kosong');

--
-- Triggers `rak`
--
DELIMITER $$
CREATE TRIGGER `trigger_idRak` BEFORE INSERT ON `rak` FOR EACH ROW BEGIN
DECLARE i char(5);
DECLARE j integer;

set j = (SELECT substring(id_rak, 4, 4) as nomor
         from rak order by nomor desc limit 1);
set i = (SELECT tambah_id_rak(j));

IF(new.id_rak is null or new.id_rak = '')
then set new.id_rak = i;
end IF;
end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` char(7) NOT NULL,
  `id_pelanggan` char(7) NOT NULL,
  `id_akun` char(7) NOT NULL,
  `total` int(11) NOT NULL,
  `tunai` int(11) NOT NULL,
  `kembalian` int(11) NOT NULL,
  `status_pembayaran` enum('Lunas','Belum Lunas') NOT NULL,
  `status_pesanan` enum('Belum Diambil','Sudah Diambil') NOT NULL,
  `estimasi_pengambilan` datetime NOT NULL,
  `tgl_transaksi` datetime NOT NULL,
  `tgl_pengambilan` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `id_pelanggan`, `id_akun`, `total`, `tunai`, `kembalian`, `status_pembayaran`, `status_pesanan`, `estimasi_pengambilan`, `tgl_transaksi`, `tgl_pengambilan`) VALUES
('SC00001', 'CUST001', 'AKUN001', 70000, 70000, 0, 'Lunas', 'Sudah Diambil', '2023-06-22 09:40:49', '2023-06-18 21:40:50', '2023-06-18 22:12:25'),
('SC00002', 'CUST002', 'AKUN001', 100000, 100000, 0, 'Lunas', 'Sudah Diambil', '2023-06-20 09:42:04', '2023-06-18 21:42:04', '2023-06-18 22:13:18'),
('SC00003', 'CUST003', 'AKUN001', 13000, 13000, 0, 'Lunas', 'Sudah Diambil', '2023-06-20 10:07:04', '2023-06-18 23:07:04', '2023-06-18 23:37:27'),
('SC00004', 'CUST004', 'AKUN001', 55000, 60000, 5000, 'Belum Lunas', 'Belum Diambil', '2023-06-19 05:51:00', '2023-06-18 23:51:00', NULL);

--
-- Triggers `transaksi`
--
DELIMITER $$
CREATE TRIGGER `trigger_idTransaksi` BEFORE INSERT ON `transaksi` FOR EACH ROW BEGIN
DECLARE i char(7);
DECLARE j integer;

set j = (SELECT substring(id_transaksi, 3, 6) as nomor
         from transaksi order by nomor desc limit 1);
set i = (SELECT tambah_id_transaksi(j));

IF(new.id_transaksi is null or new.id_transaksi = '')
then set new.id_transaksi = i;
end IF;
end
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `akun`
--
ALTER TABLE `akun`
  ADD PRIMARY KEY (`id_akun`);

--
-- Indexes for table `detail_paket`
--
ALTER TABLE `detail_paket`
  ADD PRIMARY KEY (`id_detailPaket`),
  ADD KEY `id_package` (`id_paket`);

--
-- Indexes for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD KEY `id_transaction` (`id_transaksi`),
  ADD KEY `id_package` (`id_detailPaket`),
  ADD KEY `id_rak` (`id_rak`);

--
-- Indexes for table `paket`
--
ALTER TABLE `paket`
  ADD PRIMARY KEY (`id_paket`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`id_pelanggan`);

--
-- Indexes for table `pengeluaran`
--
ALTER TABLE `pengeluaran`
  ADD PRIMARY KEY (`id_pengeluaran`);

--
-- Indexes for table `rak`
--
ALTER TABLE `rak`
  ADD PRIMARY KEY (`id_rak`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `id_customer` (`id_pelanggan`),
  ADD KEY `id_akun` (`id_akun`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `detail_paket`
--
ALTER TABLE `detail_paket`
  ADD CONSTRAINT `detail_paket_ibfk_1` FOREIGN KEY (`id_paket`) REFERENCES `paket` (`id_paket`);

--
-- Constraints for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD CONSTRAINT `detail_transaksi_ibfk_2` FOREIGN KEY (`id_detailPaket`) REFERENCES `detail_paket` (`id_detailPaket`),
  ADD CONSTRAINT `detail_transaksi_ibfk_3` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detail_transaksi_ibfk_4` FOREIGN KEY (`id_rak`) REFERENCES `rak` (`id_rak`);

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_3` FOREIGN KEY (`id_akun`) REFERENCES `akun` (`id_akun`),
  ADD CONSTRAINT `transaksi_ibfk_4` FOREIGN KEY (`id_pelanggan`) REFERENCES `pelanggan` (`id_pelanggan`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
