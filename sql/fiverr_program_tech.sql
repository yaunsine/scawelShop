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

 Date: 13/02/2023 16:04:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for fiverr_program_tech
-- ----------------------------
DROP TABLE IF EXISTS `fiverr_program_tech`;
CREATE TABLE `fiverr_program_tech`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `platform_tool` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
