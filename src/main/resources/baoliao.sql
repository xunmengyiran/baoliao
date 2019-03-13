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

 Date: 13/03/2019 22:03:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_focus_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_focus_info`;
CREATE TABLE `tb_focus_info`  (
  `id` int(11) NOT NULL,
  `self_open_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '自身openId',
  `other_open_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '被关注着openId',
  `create_time` datetime NULL DEFAULT NULL COMMENT '关注时间',
  `is_cancel` int(1) NULL DEFAULT 0 COMMENT '是否取消关注，默认未取消',
  `cancel_time` datetime NULL DEFAULT NULL COMMENT '取消关注时间',
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

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
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `is_delete` int(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_product
-- ----------------------------
INSERT INTO `tb_product` VALUES (27, 'ohDAp1PJ7rxxLGZIoKbN1T2UllIo', '1', '2', NULL, '444', '', '88', '2019-03-12 10:23', '0', '32', NULL, NULL);
INSERT INTO `tb_product` VALUES (28, 'ohDAp1PJ7rxxLGZIoKbN1T2UllIo', '2', '3', NULL, '44', '20190312105000015,20190312105000015,20190312105000015,20190312105000015,20190312105000015,20190312105000015', '68', '2019-03-12 10:47', '0', '32', NULL, NULL);
INSERT INTO `tb_product` VALUES (29, 'ohDAp1PJ7rxxLGZIoKbN1T2UllIo', '1', '2', NULL, '你在', '', '88', '2019-03-12 11:05', '0', '32', NULL, NULL);
INSERT INTO `tb_product` VALUES (30, 'ohDAp1PJ7rxxLGZIoKbN1T2UllIo', '1', '2', NULL, '不好喝', '', '88', '2019-03-12 17:40', '0', '32', NULL, NULL);
INSERT INTO `tb_product` VALUES (31, 'ohDAp1MbpmyfnexLVONp2xCCTt-Q', '1', '2', NULL, '4', '', '5', '2019-03-12 22:19', '0', '32', NULL, NULL);
INSERT INTO `tb_product` VALUES (32, 'ohDAp1PALQ8jZ7pVn2d8Wjz8Bv_4', 'rrrf f f', 'c&nbsp;c&nbsp;c', NULL, '', '20190313103500016605', '188', '', '0', '32', NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_trade
-- ----------------------------
INSERT INTO `tb_trade` VALUES (1, 31, 'ohDAp1PJ7rxxLGZIoKbN1T2UllIo', 'ohDAp1MbpmyfnexLVONp2xCCTt-Q', '5', '1', '2019-03-13 10:09:20');
INSERT INTO `tb_trade` VALUES (2, 32, 'ohDAp1PJ7rxxLGZIoKbN1T2UllIo', 'ohDAp1PALQ8jZ7pVn2d8Wjz8Bv_4', '188', '1', '2019-03-13 10:35:36');

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
) ENGINE = InnoDB AUTO_INCREMENT = 68 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_wechat_user_info
-- ----------------------------
INSERT INTO `tb_wechat_user_info` VALUES (56, NULL, 'ohDAp1PJ7rxxLGZIoKbN1T2UllIo', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-01', '2019-03-05 13:02:55', NULL, NULL, NULL, NULL);
INSERT INTO `tb_wechat_user_info` VALUES (57, NULL, 'ohDAp1PykHDgkhyTXqEAEWQRo0bk', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_wechat_user_info` VALUES (58, NULL, 'ohDAp1PJ7rxxLGZIoKbN1T2UllIo', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-05', '2019-03-05 13:02:55', NULL, NULL, NULL, NULL);
INSERT INTO `tb_wechat_user_info` VALUES (59, NULL, 'ohDAp1PJ7rxxLGZIoKbN1T2UllIo', 0, '寻梦依然', 1, '合肥', '中国', '安徽', 'zh_CN', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqfAA1AJAgRCFthEdAvqzMSut19A09ibzBVv5lkjdia643BGmXrLKeZZJ5sXptUyjrHyILcJHcax58A/132', '2019-03-05', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_wechat_user_info` VALUES (60, NULL, 'ohDAp1MbpmyfnexLVONp2xCCTt-Q', 0, '七里香。', 2, '普陀', '中国', '上海', 'zh_CN', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83epibvrsnOqiauhz4xA5jb6l2MUdEPtjNriaq9G3CYeyEDQ6BNCnXXjJYAiaiahsBbDjg4v3T4W0PLLjic1w/132', '2019-03-09', '2019-03-12 22:04:41', NULL, NULL, NULL, NULL);
INSERT INTO `tb_wechat_user_info` VALUES (61, NULL, 'ohDAp1N1zrrbwZIwpMtjatFjDfac', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-10', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_wechat_user_info` VALUES (62, NULL, 'ohDAp1PJ7rxxLGZIoKbN1T2UllIo', 1, '寻梦依然', 1, '合肥', '中国', '安徽', 'zh_CN', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqfAA1AJAgRCFthEdAvqzMSut19A09ibzBVv5lkjdia643BGmXrLKeZZJ5sXptUyjrHyILcJHcax58A/132', '2019-03-12', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_wechat_user_info` VALUES (63, NULL, 'ohDAp1I6x_EcrGc67wwrGdiFHAJ8', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-12', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_wechat_user_info` VALUES (64, NULL, 'ohDAp1MbpmyfnexLVONp2xCCTt-Q', 1, '七里香。', 2, '普陀', '中国', '上海', 'zh_CN', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83epibvrsnOqiauhz4xA5jb6l2MUdEPtjNriaq9G3CYeyEDQ6BNCnXXjJYAiaiahsBbDjg4v3T4W0PLLjic1w/132', '2019-03-12', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_wechat_user_info` VALUES (65, NULL, 'ohDAp1F_iIP1BzLu3275_ofoNhrk', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-13', '2019-03-13 00:50:46', NULL, NULL, NULL, NULL);
INSERT INTO `tb_wechat_user_info` VALUES (66, NULL, 'ohDAp1HGsXCWudGJYGigkV6Wd_cU', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-13', '2019-03-13 03:38:06', NULL, NULL, NULL, NULL);
INSERT INTO `tb_wechat_user_info` VALUES (67, NULL, 'ohDAp1PALQ8jZ7pVn2d8Wjz8Bv_4', 1, 'chop', 1, '南京', '中国', '江苏', 'zh_CN', 'http://thirdwx.qlogo.cn/mmopen/vi_32/aoDuLiaKNpKDUccxmEDmPojQYEZWLH4BGiayCPiaDXphOqM5fTibiaUVlKYtCB1eSYbicDYD1SPRG7YPic0BItAic8e7Gw/132', '2019-03-13', NULL, NULL, NULL, NULL, NULL);

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
