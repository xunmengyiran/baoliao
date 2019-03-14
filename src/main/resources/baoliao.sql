-- MySQL dump 10.13  Distrib 5.5.59, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: baoliao
-- ------------------------------------------------------
-- Server version	5.5.59-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_focus_info`
--

DROP TABLE IF EXISTS `tb_focus_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_focus_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `self_open_id` varchar(255) DEFAULT NULL COMMENT '自身openId',
  `other_open_id` varchar(255) DEFAULT NULL COMMENT '被关注着openId',
  `create_time` datetime DEFAULT NULL COMMENT '关注时间',
  `is_cancel` int(1) DEFAULT '0' COMMENT '是否取消关注，默认未取消',
  `cancel_time` datetime DEFAULT NULL COMMENT '取消关注时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_focus_info`
--

LOCK TABLES `tb_focus_info` WRITE;
/*!40000 ALTER TABLE `tb_focus_info` DISABLE KEYS */;
INSERT INTO `tb_focus_info` VALUES (4,'ohDAp1PJ7rxxLGZIoKbN1T2UllIo','ohDAp1MbpmyfnexLVONp2xCCTt-Q','2019-03-14 16:28:59',0,NULL);
/*!40000 ALTER TABLE `tb_focus_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_product`
--

DROP TABLE IF EXISTS `tb_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(255) NOT NULL COMMENT '用户id',
  `title` varchar(255) DEFAULT '' COMMENT '标题',
  `introduct` varchar(255) DEFAULT '' COMMENT '简介',
  `type` varchar(4) DEFAULT NULL,
  `content` varchar(255) DEFAULT '' COMMENT '料码内容描述',
  `img_arr` varchar(4000) DEFAULT '' COMMENT '图片名数组',
  `price` varchar(10) DEFAULT NULL COMMENT '料码价格',
  `expritation_date` varchar(255) DEFAULT NULL COMMENT '过期时间',
  `is_refund` varchar(255) DEFAULT NULL COMMENT '是否退款(1:有退款 0：没有退款)',
  `qr_img_name` varchar(255) DEFAULT NULL COMMENT '二维码图片名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_delete` int(1) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_product`
--

LOCK TABLES `tb_product` WRITE;
/*!40000 ALTER TABLE `tb_product` DISABLE KEYS */;
INSERT INTO `tb_product` VALUES (27,'ohDAp1PJ7rxxLGZIoKbN1T2UllIo','1','2',NULL,'444','','88','2019-03-12 10:23','0','32',NULL,NULL),(28,'ohDAp1PJ7rxxLGZIoKbN1T2UllIo','2','3',NULL,'44','20190312105000015,20190312105000015,20190312105000015,20190312105000015,20190312105000015,20190312105000015','68','2019-03-12 10:47','0','32',NULL,NULL),(29,'ohDAp1PJ7rxxLGZIoKbN1T2UllIo','1','2',NULL,'你在','','88','2019-03-12 11:05','0','32',NULL,NULL),(30,'ohDAp1PJ7rxxLGZIoKbN1T2UllIo','1','2',NULL,'不好喝','','88','2019-03-12 17:40','0','32',NULL,NULL),(31,'ohDAp1MbpmyfnexLVONp2xCCTt-Q','1','2',NULL,'4','','5','2019-03-12 22:19','0','32',NULL,NULL),(32,'ohDAp1PALQ8jZ7pVn2d8Wjz8Bv_4','rrrf f f','c&nbsp;c&nbsp;c',NULL,'','20190313103500016605','188','','0','32',NULL,NULL),(33,'ohDAp1PJ7rxxLGZIoKbN1T2UllIo','测试标题','测试简介',NULL,'222','','68','2019-03-13 20:55','0','36',NULL,0),(34,'ohDAp1PJ7rxxLGZIoKbN1T2UllIo','测试','1',NULL,'3','','68','2019-03-13 21:10','0','36',NULL,0),(35,'ohDAp1PJ7rxxLGZIoKbN1T2UllIo','1','2',NULL,'我们','','68','2019-03-13 21:19','0','36',NULL,0),(36,'ohDAp1MbpmyfnexLVONp2xCCTt-Q','啊啊啊啊啊啊啊','哈哈哈哈哈哈哈哈',NULL,'','20190313212000058930.png,20190313212000058618.png','5','','0','36',NULL,0);
/*!40000 ALTER TABLE `tb_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_trade`
--

DROP TABLE IF EXISTS `tb_trade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_trade` (
  `id` int(13) NOT NULL AUTO_INCREMENT,
  `product_id` int(13) NOT NULL COMMENT '产品id',
  `buyer_open_id` varchar(255) DEFAULT NULL COMMENT '买家openId',
  `seller_open_id` varchar(255) DEFAULT NULL COMMENT '卖家openId',
  `money` varchar(255) DEFAULT NULL COMMENT '交易金额',
  `pay_type` varchar(255) DEFAULT NULL COMMENT '支付类型(余额支付和支付宝支付，待定)',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_trade`
--

LOCK TABLES `tb_trade` WRITE;
/*!40000 ALTER TABLE `tb_trade` DISABLE KEYS */;
INSERT INTO `tb_trade` VALUES (1,31,'ohDAp1PJ7rxxLGZIoKbN1T2UllIo','ohDAp1MbpmyfnexLVONp2xCCTt-Q','5','1','2019-03-13 10:09:20'),(2,32,'ohDAp1PJ7rxxLGZIoKbN1T2UllIo','ohDAp1PALQ8jZ7pVn2d8Wjz8Bv_4','188','1','2019-03-13 10:35:36'),(3,31,'ohDAp1PJ7rxxLGZIoKbN1T2UllIo','ohDAp1MbpmyfnexLVONp2xCCTt-Q','5','1','2019-03-14 15:55:57');
/*!40000 ALTER TABLE `tb_trade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open_id` bigint(20) DEFAULT NULL,
  `profit` decimal(10,0) DEFAULT NULL COMMENT '收益总额',
  `balance` decimal(10,0) DEFAULT NULL COMMENT '余额',
  `rate` float DEFAULT NULL COMMENT '费率',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_vermicelli`
--

DROP TABLE IF EXISTS `tb_vermicelli`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_vermicelli` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buyer_id` bigint(20) NOT NULL COMMENT '买家用户id',
  `selller_id` bigint(20) NOT NULL COMMENT '卖家用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_vermicelli`
--

LOCK TABLES `tb_vermicelli` WRITE;
/*!40000 ALTER TABLE `tb_vermicelli` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_vermicelli` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_wechat_user_info`
--

DROP TABLE IF EXISTS `tb_wechat_user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_wechat_user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phoneNumber` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `openId` varchar(255) DEFAULT NULL COMMENT '微信用户唯一标识',
  `subscribe` int(13) DEFAULT NULL COMMENT '是否关注（0否1是）',
  `nickName` varchar(255) DEFAULT NULL COMMENT '昵称',
  `sex` int(255) DEFAULT NULL COMMENT '性别（1男2女0未知)',
  `city` varchar(255) DEFAULT NULL COMMENT '所在城市',
  `country` varchar(255) DEFAULT NULL COMMENT '所在国家',
  `province` varchar(255) DEFAULT NULL COMMENT '省份',
  `language` varchar(255) DEFAULT NULL COMMENT '用户的语言，简体中文为zh_CN',
  `headImgUrl` varchar(255) DEFAULT NULL COMMENT '用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像)',
  `subscribeTime` date DEFAULT NULL COMMENT '用户关注时间',
  `cancelSubscribeTime` varchar(255) DEFAULT NULL COMMENT '用户取消关注时间',
  `bindTime` date DEFAULT NULL COMMENT '绑定时间',
  `unionId` varchar(255) DEFAULT NULL COMMENT '只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）',
  `remark` varchar(255) DEFAULT NULL,
  `privilege` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_wechat_user_info`
--

LOCK TABLES `tb_wechat_user_info` WRITE;
/*!40000 ALTER TABLE `tb_wechat_user_info` DISABLE KEYS */;
INSERT INTO `tb_wechat_user_info` VALUES (56,NULL,'ohDAp1PJ7rxxLGZIoKbN1T2UllIo',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2019-03-01','2019-03-05 13:02:55',NULL,NULL,NULL,NULL),(57,NULL,'ohDAp1PykHDgkhyTXqEAEWQRo0bk',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2019-03-02',NULL,NULL,NULL,NULL,NULL),(58,NULL,'ohDAp1PJ7rxxLGZIoKbN1T2UllIo',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2019-03-05','2019-03-05 13:02:55',NULL,NULL,NULL,NULL),(59,NULL,'ohDAp1PJ7rxxLGZIoKbN1T2UllIo',0,'寻梦依然',1,'合肥','中国','安徽','zh_CN','http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqfAA1AJAgRCFthEdAvqzMSut19A09ibzBVv5lkjdia643BGmXrLKeZZJ5sXptUyjrHyILcJHcax58A/132','2019-03-05',NULL,NULL,NULL,NULL,NULL),(60,NULL,'ohDAp1MbpmyfnexLVONp2xCCTt-Q',0,'七里香。',2,'普陀','中国','上海','zh_CN','http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83epibvrsnOqiauhz4xA5jb6l2MUdEPtjNriaq9G3CYeyEDQ6BNCnXXjJYAiaiahsBbDjg4v3T4W0PLLjic1w/132','2019-03-09','2019-03-12 22:04:41',NULL,NULL,NULL,NULL),(61,NULL,'ohDAp1N1zrrbwZIwpMtjatFjDfac',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2019-03-10',NULL,NULL,NULL,NULL,NULL),(62,NULL,'ohDAp1PJ7rxxLGZIoKbN1T2UllIo',1,'寻梦依然',1,'合肥','中国','安徽','zh_CN','http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqfAA1AJAgRCFthEdAvqzMSut19A09ibzBVv5lkjdia643BGmXrLKeZZJ5sXptUyjrHyILcJHcax58A/132','2019-03-12',NULL,NULL,NULL,NULL,NULL),(63,NULL,'ohDAp1I6x_EcrGc67wwrGdiFHAJ8',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2019-03-12',NULL,NULL,NULL,NULL,NULL),(64,NULL,'ohDAp1MbpmyfnexLVONp2xCCTt-Q',1,'七里香。',2,'普陀','中国','上海','zh_CN','http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83epibvrsnOqiauhz4xA5jb6l2MUdEPtjNriaq9G3CYeyEDQ6BNCnXXjJYAiaiahsBbDjg4v3T4W0PLLjic1w/132','2019-03-12',NULL,NULL,NULL,NULL,NULL),(65,NULL,'ohDAp1F_iIP1BzLu3275_ofoNhrk',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2019-03-13','2019-03-13 00:50:46',NULL,NULL,NULL,NULL),(66,NULL,'ohDAp1HGsXCWudGJYGigkV6Wd_cU',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2019-03-13','2019-03-13 03:38:06',NULL,NULL,NULL,NULL),(67,NULL,'ohDAp1PALQ8jZ7pVn2d8Wjz8Bv_4',1,'chop',1,'南京','中国','江苏','zh_CN','http://thirdwx.qlogo.cn/mmopen/vi_32/aoDuLiaKNpKDUccxmEDmPojQYEZWLH4BGiayCPiaDXphOqM5fTibiaUVlKYtCB1eSYbicDYD1SPRG7YPic0BItAic8e7Gw/132','2019-03-13',NULL,NULL,NULL,NULL,NULL),(68,NULL,'ohDAp1AbtQPCOCdYmC9YJMPfZW3I',0,'陈铎',1,'深圳','中国','广东','zh_CN','http://thirdwx.qlogo.cn/mmopen/vi_32/l98JXX5gEZoJYd6xuNBFsibIk8mBR9UZC6A4EwiaJicUIGSYL6OyYVDElvNy2wOGDFo0iamh9CKmfkZR69XliaJxzBA/132','2019-03-13','2019-03-13 23:21:23',NULL,NULL,NULL,NULL),(69,NULL,'ohDAp1CBVPEC312VcVQ2whlMHp2c',0,'不合格',1,'','中国','','zh_CN','http://thirdwx.qlogo.cn/mmopen/vi_32/DxA89AgRfqibxlG0jvNzbq0Cz2OTibsnI2qJ5jIlsedpTkXhjl18eyJRJ9poYicPnkic3nBHDXMibdoAMxIlhzUWz3w/132','2019-03-14','2019-03-14 05:37:07',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `tb_wechat_user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_withdrawal`
--

DROP TABLE IF EXISTS `tb_withdrawal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_withdrawal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(255) NOT NULL,
  `money` decimal(10,0) NOT NULL COMMENT '收入金额',
  `profit` decimal(10,0) DEFAULT '0' COMMENT '提现金额',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_withdrawal`
--

LOCK TABLES `tb_withdrawal` WRITE;
/*!40000 ALTER TABLE `tb_withdrawal` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_withdrawal` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-14 16:37:36
