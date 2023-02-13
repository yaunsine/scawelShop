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

 Date: 13/02/2023 16:03:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for fiverr_gig_reviews_old
-- ----------------------------
DROP TABLE IF EXISTS `fiverr_gig_reviews_old`;
CREATE TABLE `fiverr_gig_reviews_old`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `img_src` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `country_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `rating_score` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `score` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `platform_tool` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `website_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `freelance_id` int NULL DEFAULT NULL,
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `website_feature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 542 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'Fiverr网站Gig表，废弃' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
