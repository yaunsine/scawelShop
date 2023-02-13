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

 Date: 13/02/2023 16:03:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for fiverr_gig_reviews
-- ----------------------------
DROP TABLE IF EXISTS `fiverr_gig_reviews`;
CREATE TABLE `fiverr_gig_reviews`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '标识',
  `seller_from` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所在国家',
  `seller_rating_count` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '完成项目数',
  `reviews_count` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论数',
  `reviews_country_name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '评论国家',
  `gig_description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT 'gig描述',
  `url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT 'gig链接',
  `freelance_id` int NULL DEFAULT NULL COMMENT '自由职业者id',
  `building_tool` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支持建站工具',
  `seller_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '服务商名称',
  `seller_score` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评分',
  `is_deleted` tinyint NULL DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 554 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'Fiverr网站Gig表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
