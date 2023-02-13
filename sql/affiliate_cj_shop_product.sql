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

 Date: 13/02/2023 16:04:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for affiliate_cj_shop_product
-- ----------------------------
DROP TABLE IF EXISTS `affiliate_cj_shop_product`;
CREATE TABLE `affiliate_cj_shop_product`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '产品唯一标识',
  `pid` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'cj产品库中的id',
  `ad_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '广告商关联id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品名称',
  `adult` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '指示产品是否包含性暗示内容',
  `advertiser_country` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '广告商所属国家',
  `advertiser_id` int NULL DEFAULT NULL COMMENT '广告商账户id',
  `advertiser_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '广告商名称',
  `age_group` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品的目标人群（仅限服装产品）值：新生儿、婴儿、学步儿童、儿童、成人',
  `availability` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品可用性值：IN_STOCK、OUT_OF_STOCK、PREORDER',
  `size` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品尺寸，只针对服装',
  `availability_date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '预订商品可用的日期',
  `brand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品品牌',
  `catalog_id` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品分类id',
  `color` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品颜色',
  `additional_image_link` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '多个附件图片，以逗号分隔',
  `condition` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品状况值：全新、翻新、二手',
  `image_link` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '主图链接',
  `custom_label0` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '广告商自定义产品 Feed 字段',
  `custom_label1` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `custom_label2` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `custom_label3` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `custom_label4` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '产品详情描述',
  `energy_efficiency_class` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '您的产品能源标签（仅限欧盟和瑞士）值：G、F、E、D、C、B、A、A+、A++、A+++',
  `energy_efficiency_class_max` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `energy_efficiency_class_min` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `expiration_date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '您的产品停止供应的日期',
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品的目标性别（仅限服装产品）值：男性、女性、男女皆宜',
  `google_product_category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'google定义的产品类别',
  `gtin` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品全球贸易项目编号',
  `identifier_exists` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '如果新产品没有：GTIN 和品牌或 MPN 和品牌，将显示值：是、否、空白（默认为是）',
  `installment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分期付款计划的详细信息',
  `is_bundle` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表示产品是包含一个主要产品的不同产品的自定义组（例如，与相关配件捆绑销售的产品）',
  `is_deleted` tinyint NULL DEFAULT NULL COMMENT '产品是否被广告商删除',
  `item_group_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `item_list_id` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `item_list_name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `last_updated` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '最后更新产品的日期',
  `link` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT 'Landing页链接',
  `link_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `loyalty_points` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品的面料或材料',
  `material` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `maximum_handling_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `minimum_handling_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `mobile_link` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `mpn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品的制造商部件号',
  `multipack` int NULL DEFAULT NULL COMMENT '作为多件组合装一起销售的相同产品的数量',
  `pattern` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `price` varbinary(255) NULL DEFAULT NULL,
  `product_type` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `sale_price` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sale_price_effective_date_end` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sale_price_effective_date_start` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `serviceable_areas` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `shipping` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '运输费用',
  `size_system` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品尺码系统所在国家/地区的尺码值：US, UK, EU, DE, FR, JP, CN, IT, BR, MEX, AU',
  `size_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '服装产品剪裁值：常规、娇小、加大、高大、孕妇装',
  `source_feed_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品所属的 Feed 类型（即购物、旅游）',
  `target_country` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '您的产品可供销售或运往的主要国家/地区',
  `tax` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品销售税明细',
  `unit_pricing_base_measure` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品的定价基准（如100ml表示以100ml为单位计算价格）。提交unit_pricing_measure 时可选。整数+单位',
  `unit_pricing_measure` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9182707 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'cj产品表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
