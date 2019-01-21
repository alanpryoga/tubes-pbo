/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 100137
 Source Host           : localhost:3306
 Source Schema         : db_penjualan_caffe

 Target Server Type    : MySQL
 Target Server Version : 100137
 File Encoding         : 65001

 Date: 17/01/2019 05:31:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for detail_penjualan
-- ----------------------------
DROP TABLE IF EXISTS `detail_penjualan`;
CREATE TABLE `detail_penjualan`  (
  `id_detail_penjualan` int(11) NOT NULL AUTO_INCREMENT,
  `no_penjualan` varchar(12) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `kode_produk` varchar(12) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `jumlah` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id_detail_penjualan`) USING BTREE,
  INDEX `FK_detail_penjualan_penjualan`(`no_penjualan`) USING BTREE,
  INDEX `FK_detail_penjualan_produk`(`kode_produk`) USING BTREE,
  CONSTRAINT `FK_detail_penjualan_penjualan` FOREIGN KEY (`no_penjualan`) REFERENCES `penjualan` (`no_penjualan`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `FK_detail_penjualan_produk` FOREIGN KEY (`kode_produk`) REFERENCES `produk` (`kode_produk`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of detail_penjualan
-- ----------------------------
INSERT INTO `detail_penjualan` VALUES (1, 'INV001', 'PRD001', 2);
INSERT INTO `detail_penjualan` VALUES (2, 'INV002', 'PRD005', 2);
INSERT INTO `detail_penjualan` VALUES (3, 'PJL0001', 'PRD001', 1);
INSERT INTO `detail_penjualan` VALUES (4, 'PJL0002', 'PRD004', 5);
INSERT INTO `detail_penjualan` VALUES (5, 'PJL0002', 'PRD006', 1);
INSERT INTO `detail_penjualan` VALUES (6, 'PJL0003', 'PRD005', 1);

-- ----------------------------
-- Table structure for pelanggan
-- ----------------------------
DROP TABLE IF EXISTS `pelanggan`;
CREATE TABLE `pelanggan`  (
  `id_pelanggan` int(11) NOT NULL AUTO_INCREMENT,
  `nama_pelanggan` varchar(60) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `alamat` text CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `no_telp` varchar(14) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`id_pelanggan`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of pelanggan
-- ----------------------------
INSERT INTO `pelanggan` VALUES (1, 'Ahmad', 'Cihampelas', '089619111010');
INSERT INTO `pelanggan` VALUES (2, 'Budi', 'Dago', '081380971678');
INSERT INTO `pelanggan` VALUES (3, 'Cici', 'Sekeloa', '083120902890');
INSERT INTO `pelanggan` VALUES (4, 'Dadang', 'Cieumbeulit', '089610226078');
INSERT INTO `pelanggan` VALUES (5, 'Eri', 'Cikutra', '0813888101202');

-- ----------------------------
-- Table structure for penjualan
-- ----------------------------
DROP TABLE IF EXISTS `penjualan`;
CREATE TABLE `penjualan`  (
  `no_penjualan` varchar(12) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_pelanggan` int(11) NOT NULL,
  `tanggal` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `subtotal` int(11) NOT NULL,
  `diskon` int(11) NOT NULL,
  `pajak` int(11) NOT NULL,
  `total_pembayaran` int(11) NOT NULL,
  PRIMARY KEY (`no_penjualan`) USING BTREE,
  INDEX `FK_penjualan_user`(`id_user`) USING BTREE,
  INDEX `FK_penjualan_pelanggan`(`id_pelanggan`) USING BTREE,
  CONSTRAINT `FK_penjualan_pelanggan` FOREIGN KEY (`id_pelanggan`) REFERENCES `pelanggan` (`id_pelanggan`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `FK_penjualan_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of penjualan
-- ----------------------------
INSERT INTO `penjualan` VALUES ('INV001', 1, 1, '2019-01-13 21:02:12', 20000, 0, 0, 20000);
INSERT INTO `penjualan` VALUES ('INV002', 1, 2, '2019-01-13 21:03:05', 4000, 0, 0, 4000);
INSERT INTO `penjualan` VALUES ('PJL0001', 1, 4, '2019-01-15 21:19:19', 10000, 0, 0, 10000);
INSERT INTO `penjualan` VALUES ('PJL0002', 1, 5, '2019-01-15 23:19:15', 8000, 0, 0, 8000);
INSERT INTO `penjualan` VALUES ('PJL0003', 1, 3, '2019-01-15 23:37:36', 2000, 0, 0, 2000);

-- ----------------------------
-- Table structure for produk
-- ----------------------------
DROP TABLE IF EXISTS `produk`;
CREATE TABLE `produk`  (
  `kode_produk` varchar(12) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `nama_produk` varchar(60) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `harga` int(11) NOT NULL,
  `stok` int(11) NOT NULL,
  `aktif` tinyint(1) NOT NULL,
  PRIMARY KEY (`kode_produk`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of produk
-- ----------------------------
INSERT INTO `produk` VALUES ('PRD001', 'Es Kepal Milo', 10000, 8, 1);
INSERT INTO `produk` VALUES ('PRD002', 'Thai Tea', 8000, 5, 1);
INSERT INTO `produk` VALUES ('PRD003', 'Cappucino Cincau', 5000, 15, 1);
INSERT INTO `produk` VALUES ('PRD004', 'Teh Gelas', 1000, 45, 1);
INSERT INTO `produk` VALUES ('PRD005', 'Freshtea Gelas', 2000, 7, 1);
INSERT INTO `produk` VALUES ('PRD006', 'Ades Air Mineral', 3000, 49, 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `nama_user` varchar(60) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `username` varchar(60) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `password` varchar(60) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `jabatan` varchar(14) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`id_user`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'Ade Syahlan Prayoga', 'alanprayoga', 'e10adc3949ba59abbe56e057f20f883e', 'Administrator');

-- ----------------------------
-- Triggers structure for table detail_penjualan
-- ----------------------------
DROP TRIGGER IF EXISTS `TG_UPDATE_STOK_BARANG`;
delimiter ;;
CREATE TRIGGER `TG_UPDATE_STOK_BARANG` AFTER INSERT ON `detail_penjualan` FOR EACH ROW BEGIN
	UPDATE produk
    SET stok = stok - NEW.jumlah
    WHERE kode_produk = NEW.kode_produk;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
