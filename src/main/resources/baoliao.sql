/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50087
 Source Host           : localhost:3306
 Source Schema         : baoliao

 Target Server Type    : MySQL
 Target Server Version : 50087
 File Encoding         : 65001

 Date: 06/03/2019 11:21:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_product
-- ----------------------------
DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE `tb_product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `introduct` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `type` tinyint(4) NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `picture_url` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `price` decimal(10, 0) NULL DEFAULT NULL,
  `expritation_date` datetime NULL DEFAULT NULL,
  `is_refund` tinyint(4) NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_product
-- ----------------------------
INSERT INTO `tb_product` VALUES (1, 'ohDAp1PJ7rxxLGZIoKbN1T2UllIo', '我', '你', NULL, '你', '', 5, '2019-03-05 12:59:00', 0);
INSERT INTO `tb_product` VALUES (2, 'ohDAp1PJ7rxxLGZIoKbN1T2UllIo', '我', '你', NULL, '在', '', 5, '2019-03-05 13:06:00', 0);
INSERT INTO `tb_product` VALUES (3, 'ohDAp1PJ7rxxLGZIoKbN1T2UllIo', '1', '2', NULL, '3', '', 88, '2019-03-06 11:03:00', 0);

-- ----------------------------
-- Table structure for tb_trade
-- ----------------------------
DROP TABLE IF EXISTS `tb_trade`;
CREATE TABLE `tb_trade`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open_id` bigint(20) NOT NULL,
  `productb_id` int(11) NOT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `money` decimal(10, 0) NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open_id` bigint(20) NULL DEFAULT NULL,
  `profit` decimal(10, 0) NULL DEFAULT NULL,
  `balance` decimal(10, 0) NULL DEFAULT NULL,
  `rate` float NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_vermicelli
-- ----------------------------
DROP TABLE IF EXISTS `tb_vermicelli`;
CREATE TABLE `tb_vermicelli`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buyer_id` bigint(20) NOT NULL,
  `selller_id` bigint(20) NOT NULL,
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_wechat_user_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_wechat_user_info`;
CREATE TABLE `tb_wechat_user_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phoneNumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `openId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信用户唯一标识',
  `subscribe` int(13) NULL DEFAULT NULL COMMENT '是否关注（0否1是）',
  `nickName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `sex` int(255) NULL DEFAULT NULL COMMENT '性别（1男2女0未知)',
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `country` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `province` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份',
  `language` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户的语言，简体中文为zh_CN',
  `headimgUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像)',
  `subscribeTime` date NULL DEFAULT NULL COMMENT '用户关注时间',
  `cancelSubscribeTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户取消关注时间',
  `bindTime` date NULL DEFAULT NULL,
  `unionId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `privilege` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 60 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_wechat_user_info
-- ----------------------------
INSERT INTO `tb_wechat_user_info` VALUES (56, NULL, 'ohDAp1PJ7rxxLGZIoKbN1T2UllIo', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-01', '2019-03-05 13:02:55', NULL, NULL, NULL, NULL);
INSERT INTO `tb_wechat_user_info` VALUES (57, NULL, 'ohDAp1PykHDgkhyTXqEAEWQRo0bk', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_wechat_user_info` VALUES (58, NULL, 'ohDAp1PJ7rxxLGZIoKbN1T2UllIo', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-05', '2019-03-05 13:02:55', NULL, NULL, NULL, NULL);
INSERT INTO `tb_wechat_user_info` VALUES (59, NULL, 'ohDAp1PJ7rxxLGZIoKbN1T2UllIo', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-05', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for tb_withdrawal
-- ----------------------------
DROP TABLE IF EXISTS `tb_withdrawal`;
CREATE TABLE `tb_withdrawal`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open_id` bigint(20) NOT NULL,
  `money` decimal(10, 0) NOT NULL,
  `profit` decimal(10, 0) NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
