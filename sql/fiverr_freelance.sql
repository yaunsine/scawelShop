/*
 Navicat Premium Data Transfer

 Source Server         : 123.57.101.3
 Source Server Type    : MySQL
 Source Server Version : 101001 (10.10.1-MariaDB-log)
 Source Host           : 123.57.101.3:3306
 Source Schema         : mongoshop

 Target Server Type    : MySQL
 Target Server Version : 101001 (10.10.1-MariaDB-log)
 File Encoding         : 65001

 Date: 13/02/2023 16:04:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for fiverr_freelance
-- ----------------------------
DROP TABLE IF EXISTS `fiverr_freelance`;
CREATE TABLE `fiverr_freelance`  (
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `link` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `img_src` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `score` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `price` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `program_tech_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1249 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '自由职业者表Fiverr.com网站' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
