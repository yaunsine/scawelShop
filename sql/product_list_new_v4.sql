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

 Date: 13/02/2023 16:05:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for product_list_new_v4
-- ----------------------------
DROP TABLE IF EXISTS `product_list_new_v4`;
CREATE TABLE `product_list_new_v4`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `domain` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `image_src` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `platform` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `country_code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `categories` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `alexa_rank` int NULL DEFAULT NULL,
  `platform_rank` int NULL DEFAULT NULL,
  `currency` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `gmv` bigint NULL DEFAULT NULL,
  `merchant_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `merchant_shortcut` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `p_id` bigint NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `handle` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `p_published_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `p_created_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `p_updated_at` datetime NULL DEFAULT NULL,
  `vendor` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `product_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `tags` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `price` float NULL DEFAULT NULL,
  `created_at` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP,
  `us_price` float NULL DEFAULT NULL,
  `eu_price` float NULL DEFAULT NULL,
  `uk_price` float NULL DEFAULT NULL,
  `au_price` float NULL DEFAULT NULL,
  `cn_price` float NULL DEFAULT NULL,
  `is_deleted` tinyint UNSIGNED NULL DEFAULT 1 COMMENT '判断图片是否有效',
  `is_link_valid` tinyint UNSIGNED NOT NULL DEFAULT 1 COMMENT '判断链接是否有效，链接拼接规则为[domain]/products/[handle]',
  `is_image_src_del` tinyint UNSIGNED NULL DEFAULT NULL COMMENT '判断图片链接是否删除，1表示404',
  `is_product_link_del` tinyint UNSIGNED NULL DEFAULT NULL COMMENT '判断产品链接是否删除，1表示404',
  `sort` int(10) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_domain_pid`(`domain` ASC, `p_id` ASC) USING BTREE,
  INDEX `id`(`id` ASC) USING BTREE,
  INDEX `idx_domain_currency`(`domain` ASC, `currency` ASC) USING BTREE,
  INDEX `p_id`(`p_id` ASC) USING BTREE,
  INDEX `idx_title_pid`(`title` ASC, `p_id` ASC) USING BTREE,
  INDEX `idx_merchantShortcut_categories_pid`(`merchant_shortcut` ASC, `categories` ASC, `p_id` ASC) USING BTREE,
  INDEX `idx_merchantShortcut_domain_categories_pid`(`merchant_shortcut` ASC, `domain` ASC, `categories` ASC, `p_id` ASC) USING BTREE,
  INDEX `idx_img_pid`(`image_src` ASC, `p_id` ASC) USING BTREE,
  INDEX `idx_countrycode_vendor_platform_price_update_pid`(`country_code` ASC, `vendor` ASC, `platform` ASC, `price` ASC, `updated_at` ASC, `is_deleted` ASC, `is_link_valid` ASC, `p_id` ASC) USING BTREE,
  INDEX `idx_vendor_countrycode_platform_price_update_pid`(`vendor` ASC, `country_code` ASC, `platform` ASC, `price` ASC, `updated_at` ASC, `is_deleted` ASC, `is_link_valid` ASC, `p_id` ASC) USING BTREE,
  INDEX `idx_platform_vendor_countrycode_price_update_pid`(`platform` ASC, `vendor` ASC, `country_code` ASC, `price` ASC, `updated_at` ASC, `is_deleted` ASC, `is_link_valid` ASC, `p_id` ASC) USING BTREE,
  INDEX `idx_price_vendor_countrycode_platform_update_pid`(`price` ASC, `vendor` ASC, `country_code` ASC, `platform` ASC, `updated_at` ASC, `is_deleted` ASC, `is_link_valid` ASC, `p_id` ASC) USING BTREE,
  INDEX `idx_sort`(`sort` ASC) USING BTREE,
  FULLTEXT INDEX `ft_in`(`title`)
) ENGINE = InnoDB AUTO_INCREMENT = 323198776 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
