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

 Date: 13/02/2023 16:04:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for affiliate_cj_advertiser
-- ----------------------------
DROP TABLE IF EXISTS `affiliate_cj_advertiser`;
CREATE TABLE `affiliate_cj_advertiser`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `advertiser_id` int UNSIGNED NULL DEFAULT NULL COMMENT '广告商id',
  `account_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账户状态',
  `seven_day_epc` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `three_month_epc` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `language` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `advertiser_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `program_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `relationship_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `mobile_tracking_certified` tinyint NULL DEFAULT NULL,
  `cookieless_tracking_certified` tinyint NULL DEFAULT NULL,
  `network_rank` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `primary_category_parent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `primary_category_child` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `performance_incentives` tinyint NULL DEFAULT NULL,
  `actions` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `link_types` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7145 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'cj广告商表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
