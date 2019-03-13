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

 Date: 13/03/2019 10:08:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_product
-- ----------------------------
DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE `tb_product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '标题',
  `introduct` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '简介',
  `type` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '料码内容描述',
  `img_arr` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '图片名数组',
  `price` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '料码价格',
  `expritation_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '过期时间',
  `is_refund` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否退款(1:有退款 0：没有退款)',
  `qr_img_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '二维码图片名',
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_trade
-- ----------------------------
DROP TABLE IF EXISTS `tb_trade`;
CREATE TABLE `tb_trade`  (
  `id` int(13) NOT NULL AUTO_INCREMENT,
  `product_id` int(13) NOT NULL COMMENT '产品id',
  `buyer_open_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '买家openId',
  `seller_open_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '卖家openId',
  `money` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易金额',
  `pay_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付类型(余额支付和支付宝支付，待定)',
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open_id` bigint(20) NULL DEFAULT NULL,
  `profit` decimal(10, 0) NULL DEFAULT NULL COMMENT '收益总额',
  `balance` decimal(10, 0) NULL DEFAULT NULL COMMENT '余额',
  `rate` float NULL DEFAULT NULL COMMENT '费率',
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
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在城市',
  `country` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在国家',
  `province` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份',
  `language` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户的语言，简体中文为zh_CN',
  `headImgUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像)',
  `subscribeTime` date NULL DEFAULT NULL COMMENT '用户关注时间',
  `cancelSubscribeTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户取消关注时间',
  `bindTime` date NULL DEFAULT NULL COMMENT '绑定时间',
  `unionId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `privilege` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_withdrawal
-- ----------------------------
DROP TABLE IF EXISTS `tb_withdrawal`;
CREATE TABLE `tb_withdrawal`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `money` decimal(10, 0) NOT NULL COMMENT '收入金额',
  `profit` decimal(10, 0) NULL DEFAULT 0 COMMENT '提现金额',
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
