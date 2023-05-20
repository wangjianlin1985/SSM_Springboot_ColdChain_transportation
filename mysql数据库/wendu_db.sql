/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : wendu_db

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2019-06-14 12:22:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `username` varchar(20) NOT NULL default '',
  `password` varchar(32) default NULL,
  PRIMARY KEY  (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('a', 'a');

-- ----------------------------
-- Table structure for `t_carproduct`
-- ----------------------------
DROP TABLE IF EXISTS `t_carproduct`;
CREATE TABLE `t_carproduct` (
  `id` int(11) NOT NULL auto_increment COMMENT '记录id',
  `productObj` int(11) NOT NULL COMMENT '产品名称',
  `carObj` varchar(20) NOT NULL COMMENT '所在冷藏车',
  `productNum` int(11) NOT NULL COMMENT '产品数量',
  `productMemo` varchar(500) default NULL COMMENT '备注信息',
  PRIMARY KEY  (`id`),
  KEY `productObj` (`productObj`),
  KEY `carObj` (`carObj`),
  CONSTRAINT `t_carproduct_ibfk_2` FOREIGN KEY (`carObj`) REFERENCES `t_chillcar` (`carNo`),
  CONSTRAINT `t_carproduct_ibfk_1` FOREIGN KEY (`productObj`) REFERENCES `t_product` (`productId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_carproduct
-- ----------------------------
INSERT INTO `t_carproduct` VALUES ('1', '1', '001', '200', 'test');
INSERT INTO `t_carproduct` VALUES ('2', '1', '002', '200', '测试');
INSERT INTO `t_carproduct` VALUES ('3', '2', '002', '199', '测试');
INSERT INTO `t_carproduct` VALUES ('4', '3', '002', '150', '测试');

-- ----------------------------
-- Table structure for `t_chillcar`
-- ----------------------------
DROP TABLE IF EXISTS `t_chillcar`;
CREATE TABLE `t_chillcar` (
  `carNo` varchar(20) NOT NULL COMMENT 'carNo',
  `carType` varchar(20) NOT NULL COMMENT '车辆种类',
  `pinpai` varchar(20) NOT NULL COMMENT '品牌',
  `pl` varchar(20) NOT NULL COMMENT '排量',
  `carPhoto` varchar(60) NOT NULL COMMENT '车辆照片',
  `carDesc` varchar(8000) NOT NULL COMMENT '冷藏车介绍',
  `dqwd` float NOT NULL COMMENT '当前温度',
  `carMemo` varchar(500) default NULL COMMENT '备注信息',
  PRIMARY KEY  (`carNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_chillcar
-- ----------------------------
INSERT INTO `t_chillcar` VALUES ('001', '货车', '程力威', '1.4L', 'upload/89127dd0-5e75-439d-8d71-6bceb48f4a8d.jpg', '<ul class=\"attributes-list list-paddingleft-2\" style=\"list-style-type: none;\"><li><p>变速器描述:&nbsp;手动变速箱</p></li><li><p>品牌:&nbsp;程力威</p></li><li><p>车系:&nbsp;冷藏车</p></li><li><p>国产合资进口:&nbsp;国产</p></li><li><p>国别:&nbsp;中国</p></li><li><p>座位数:&nbsp;2 5 3</p></li><li><p>汽车进气类型:&nbsp;涡轮增压</p></li><li><p>车辆级别:&nbsp;中大型车</p></li><li><p>汽车级别:&nbsp;货车</p></li><li><p>排量:&nbsp;1.2L 1.3L 1.5L</p></li><li><p>新车价格区间:&nbsp;12-18万</p></li><li><p>汽车动力类型:&nbsp;柴油类型</p></li><li><p>销售区域:&nbsp;河北省 河南省 陕西省 山西省 山东省 甘肃省 辽宁省 吉林省 黑龙江省 云南省 贵州省 福建省 广东省 海南省 四川省 湖北省 湖南省 江西省 安徽省 江苏省 浙江省 青海省 新疆 内蒙古 宁夏 西藏 北京 天津 重庆 上海 澳门特别行政区 香港特别行政区</p></li><li><p>提车时间:&nbsp;15天提车</p></li><li><p>整车质保:&nbsp;三年不限公里</p></li><li><p>指导价格:&nbsp;10-20万元</p></li><li><p>颜色分类:&nbsp;新车定金：购车电话152</p></li></ul><p><br/></p>', '-2.5', '测试车');
INSERT INTO `t_chillcar` VALUES ('002', '轿车', '福田', '1.5L', 'upload/9947bf22-6b9f-4d95-b1c4-bd11ce44a559.jpg', '<ul class=\"attributes-list list-paddingleft-2\" style=\"list-style-type: none;\"><li><p>产品名称:&nbsp;FOTON/福田 北京伽途iX5</p></li><li><p>品牌:&nbsp;FOTON/福田</p></li><li><p>车系:&nbsp;北京伽途ix5</p></li><li><p>销售区域:&nbsp;河北省 河南省 陕西省 山西省 山东省 甘肃省 辽宁省 吉林省 黑龙江省 云南省 贵州省 福建省 广东省 海南省 四川省 湖北省 湖南省 江西省 安徽省 江苏省 浙江省 青海省 新疆 内蒙古 宁夏 西藏 北京 天津 重庆 上海 广西壮族自治区</p></li><li><p>车辆版本:&nbsp;咨询热线18607225222</p></li><li><p>颜色分类:&nbsp;整车定金 配置可选 厂家直销 三包服务</p></li><li><p>汽车级别:&nbsp;MPV</p></li><li><p>排量:&nbsp;1.5L</p></li><li><p>整车质保:&nbsp;十年或20万公里</p></li><li><p>变速器描述:&nbsp;手动变速箱</p></li><li><p>国别:&nbsp;中国</p></li><li><p>座位数:&nbsp;5 7 8</p></li><li><p>国产合资进口:&nbsp;国产</p></li><li><p>汽车动力类型:&nbsp;汽油类型</p></li><li><p>汽车进气类型:&nbsp;自然吸气</p></li><li><p>指导价格:&nbsp;4.89-5.89万元</p></li><li><p>车辆级别:&nbsp;微面</p></li></ul><p><br/></p>', '20', '测试运输车');

-- ----------------------------
-- Table structure for `t_notice`
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice` (
  `noticeId` int(11) NOT NULL auto_increment COMMENT '公告id',
  `title` varchar(80) NOT NULL COMMENT '标题',
  `content` varchar(5000) NOT NULL COMMENT '公告内容',
  `publishDate` varchar(20) default NULL COMMENT '发布时间',
  PRIMARY KEY  (`noticeId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_notice
-- ----------------------------
INSERT INTO `t_notice` VALUES ('1', '冷藏车温度监控系统上线', '<p>从此温度监控实时采集上传到服务器，如果温度超过了商品的保鲜温度范围就报警，方便管理了！</p>', '2019-06-12 16:55:29');

-- ----------------------------
-- Table structure for `t_product`
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product` (
  `productId` int(11) NOT NULL auto_increment COMMENT '产品id',
  `productClassObj` int(11) NOT NULL COMMENT '产品分类',
  `productName` varchar(20) NOT NULL COMMENT '产品名称',
  `productPhoto` varchar(60) NOT NULL COMMENT '产品照片',
  `productDesc` varchar(8000) NOT NULL COMMENT '产品介绍',
  `zdwd` float NOT NULL COMMENT '最低温度',
  `zgwd` float NOT NULL COMMENT '最高温度',
  `addTime` varchar(20) default NULL COMMENT '发布时间',
  PRIMARY KEY  (`productId`),
  KEY `productClassObj` (`productClassObj`),
  CONSTRAINT `t_product_ibfk_1` FOREIGN KEY (`productClassObj`) REFERENCES `t_productclass` (`productClassId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_product
-- ----------------------------
INSERT INTO `t_product` VALUES ('1', '1', '大虾鲜活海鲜水产虾', 'upload/a1bd7579-cd5f-45af-ba9c-4071afe1fe5f.jpg', '<p class=\"attr-list-hd tm-clear\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 5px 20px; line-height: 22px; color: rgb(153, 153, 153); font-family: tahoma, arial, 微软雅黑, sans-serif; font-size: 12px; white-space: normal; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; font-weight: 700; float: left;\">产品参数：</span></p><ul style=\"list-style-type: none;\" class=\" list-paddingleft-2\"><li><p>厂名：星王网络科技有限公司</p></li><li><p>厂址：烟台市海阳市烟台街</p></li><li><p>厂家联系方式：13296459398</p></li><li><p>保质期：365 天</p></li><li><p>品牌:&nbsp;缤鲜</p></li><li><p>产地:&nbsp;中国大陆</p></li><li><p>省份:&nbsp;山东省</p></li><li><p>城市:&nbsp;烟台市</p></li><li><p>食品工艺:&nbsp;生冻水产</p></li><li><p>同城服务:&nbsp;同城24小时物流送货上门</p></li><li><p>包装方式:&nbsp;包装</p></li><li><p>特产品类:&nbsp;白虾</p></li><li><p>原产地:&nbsp;中国</p></li><li><p>净含量:&nbsp;1600g</p></li><li><p>生鲜储存温度:&nbsp;-18℃</p></li><li><p>包装规格:&nbsp;45-55只/千克</p></li></ul><p><br/></p>', '-35', '2', '2019-06-12 16:52:33');
INSERT INTO `t_product` VALUES ('2', '1', '500g海兔墨鱼仔', 'upload/738c633b-cc91-4e8b-9785-bec4c35f6f33.jpg', '<ul style=\"list-style-type: none;\" class=\" list-paddingleft-2\"><li><p>生产许可证编号：SC12221021200659</p></li><li><p>产品标准号：Q/XSD 0001S</p></li><li><p>厂名：大连旅顺新顺水产食品有限公司</p></li><li><p>厂址：辽宁省大连市旅顺口区水师营镇大王村</p></li><li><p>厂家联系方式：041186238308</p></li><li><p>配料表：鱿鱼，鱼籽，植物油，白砂糖，孜然粉，食用盐，食品添加剂(谷氨酸钠),辣椒粉，香辛料</p></li><li><p>储藏方法：请将鱿鱼仔存放于干燥阴凉处，避免阳光直射。</p></li><li><p>保质期：365 天</p></li><li><p>食品添加剂：谷氨酸钠</p></li><li><p>品牌:&nbsp;老鲜生</p></li><li><p>系列:&nbsp;鱿鱼仔 零食</p></li><li><p>口味:&nbsp;香辣味500g&nbsp;孜然加辣500g&nbsp;软软仔500g&nbsp;四味混合仔500g&nbsp;原味无籽500g</p></li><li><p>产地:&nbsp;中国大陆</p></li><li><p>省份:&nbsp;辽宁省</p></li><li><p>城市:&nbsp;大连市</p></li><li><p>是否含糖:&nbsp;含糖</p></li><li><p>是否为有机食品:&nbsp;否</p></li><li><p>商品条形码:&nbsp;6959752100046</p></li><li><p>包装方式:&nbsp;包装</p></li><li><p>特产品类:&nbsp;大连鱿鱼丝</p></li><li><p>水产类品种:&nbsp;鱿鱼仔</p></li><li><p>净含量:&nbsp;500g</p></li></ul><p><br/></p>', '-30', '5', '2019-06-14 11:59:36');
INSERT INTO `t_product` VALUES ('3', '2', '三叶果红枣整箱5斤装', 'upload/f8a1e283-cd0a-4c93-b357-9ce375d7cc0e.jpg', '<ul style=\"list-style-type: none;\" class=\" list-paddingleft-2\"><li><p>生产许可证编号：SC11861043103206</p></li><li><p>厂名：陕西新慧电子商务有限公司</p></li><li><p>厂址：陕西省咸阳市武功县渭惠路中段</p></li><li><p>厂家联系方式：029-37380116</p></li><li><p>配料表：新疆和田红枣</p></li><li><p>储藏方法：置于阴凉干燥处，0-5度环境中储存效果更佳</p></li><li><p>保质期：240 天</p></li><li><p>食品添加剂：未添加</p></li><li><p>包装方式:&nbsp;包装</p></li><li><p>零食种类:&nbsp;新疆和田枣</p></li><li><p>枣类产品:&nbsp;原粒枣</p></li><li><p>级别:&nbsp;一级</p></li><li><p>产地:&nbsp;中国大陆</p></li><li><p>省份:&nbsp;新疆维吾尔自治区</p></li><li><p>特产品类:&nbsp;和田红枣</p></li><li><p>品牌:&nbsp;TREFOIL FRUIT/三叶果</p></li><li><p>系列:&nbsp;新疆二等和田大枣</p></li><li><p>食品口味:&nbsp;500g*3袋量贩装&nbsp;500g*5袋量贩装</p></li><li><p>净含量:&nbsp;2500g</p></li></ul><p><img class=\"desc_anchor img-ks-lazyload\" id=\"desc-module-1\" src=\"https://assets.alicdn.com/kissy/1.0.0/build/imglazyload/spaceball.gif\"/></p><p style=\"margin-top: 1.12em; margin-bottom: 1.12em; padding: 0px; line-height: 1.4; color: rgb(64, 64, 64); font-family: tahoma, arial, 宋体, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);\"><img src=\"https://img.alicdn.com/imgextra/i1/1895474895/O1CN011m1xCWxdkdt7zl1_!!1895474895.jpg\" class=\"img-ks-lazyload\"/><img src=\"https://img.alicdn.com/imgextra/i4/1895474895/TB2xRO3fMmTBuNjy1XbXXaMrVXa_!!1895474895.jpg\" class=\"img-ks-lazyload\"/><img src=\"https://img.alicdn.com/imgextra/i2/1895474895/TB2u2.OmDXYBeNkHFrdXXciuVXa_!!1895474895.jpg\" class=\"img-ks-lazyload\"/></p><p><img class=\"desc_anchor img-ks-lazyload\" id=\"desc-module-2\" src=\"https://assets.alicdn.com/kissy/1.0.0/build/imglazyload/spaceball.gif\"/></p><p style=\"margin-top: 1.12em; margin-bottom: 1.12em; padding: 0px; line-height: 1.4; color: rgb(64, 64, 64); font-family: tahoma, arial, 宋体, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);\"><img src=\"https://img.alicdn.com/imgextra/i1/1895474895/TB2deDGiOqAXuNjy1XdXXaYcVXa_!!1895474895.jpg\" class=\"img-ks-lazyload\"/><img src=\"https://img.alicdn.com/imgextra/i1/1895474895/TB2EQtAllHH8KJjy0FbXXcqlpXa_!!1895474895.jpg\" class=\"img-ks-lazyload\"/><img src=\"https://img.alicdn.com/imgextra/i3/1895474895/O1CN011m1xCw9T3vNoh6c_!!1895474895.jpg\" class=\"img-ks-lazyload\"/></p><p><br/></p>', '-20', '15', '2019-06-14 12:15:00');

-- ----------------------------
-- Table structure for `t_productclass`
-- ----------------------------
DROP TABLE IF EXISTS `t_productclass`;
CREATE TABLE `t_productclass` (
  `productClassId` int(11) NOT NULL auto_increment COMMENT '产品分类id',
  `productClassName` varchar(20) NOT NULL COMMENT '产品分类名称',
  PRIMARY KEY  (`productClassId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_productclass
-- ----------------------------
INSERT INTO `t_productclass` VALUES ('1', '海鲜类');
INSERT INTO `t_productclass` VALUES ('2', '干果类');

-- ----------------------------
-- Table structure for `t_sswd`
-- ----------------------------
DROP TABLE IF EXISTS `t_sswd`;
CREATE TABLE `t_sswd` (
  `sswdId` int(11) NOT NULL auto_increment COMMENT '记录id',
  `carObj` varchar(20) NOT NULL COMMENT '冷藏车',
  `cjwd` float NOT NULL COMMENT '采集温度',
  `userObj` varchar(30) NOT NULL COMMENT '采集的用户',
  `cjsj` varchar(20) default NULL COMMENT '采集时间',
  PRIMARY KEY  (`sswdId`),
  KEY `carObj` (`carObj`),
  KEY `userObj` (`userObj`),
  CONSTRAINT `t_sswd_ibfk_2` FOREIGN KEY (`userObj`) REFERENCES `t_userinfo` (`user_name`),
  CONSTRAINT `t_sswd_ibfk_1` FOREIGN KEY (`carObj`) REFERENCES `t_chillcar` (`carNo`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sswd
-- ----------------------------
INSERT INTO `t_sswd` VALUES ('1', '001', '23', 'user1', '2019-06-12 16:54:34');
INSERT INTO `t_sswd` VALUES ('2', '001', '24', 'user1', '2019-06-13 16:56:09');
INSERT INTO `t_sswd` VALUES ('3', '001', '40', 'user1', '2019-06-14 11:30:43');
INSERT INTO `t_sswd` VALUES ('4', '001', '39', 'user2', '2019-06-14 11:32:00');
INSERT INTO `t_sswd` VALUES ('5', '001', '39.5', 'user2', '2019-06-14 11:54:27');
INSERT INTO `t_sswd` VALUES ('6', '001', '-2.5', 'user2', '2019-06-14 11:54:46');

-- ----------------------------
-- Table structure for `t_userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `t_userinfo`;
CREATE TABLE `t_userinfo` (
  `user_name` varchar(30) NOT NULL COMMENT 'user_name',
  `password` varchar(30) NOT NULL COMMENT '登录密码',
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `gender` varchar(4) NOT NULL COMMENT '性别',
  `birthDate` varchar(20) default NULL COMMENT '出生日期',
  `userPhoto` varchar(60) NOT NULL COMMENT '用户照片',
  `telephone` varchar(20) NOT NULL COMMENT '联系电话',
  `email` varchar(50) NOT NULL COMMENT '邮箱',
  `address` varchar(80) default NULL COMMENT '家庭地址',
  `regTime` varchar(20) default NULL COMMENT '注册时间',
  PRIMARY KEY  (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_userinfo
-- ----------------------------
INSERT INTO `t_userinfo` VALUES ('user1', '123', '王小明', '男', '2019-06-12', 'upload/2a2d0230-f831-4e8d-9d35-7df57bc82782.jpg', '13589834234', 'dashen@163.com', '四川南充滨江路', '2019-06-12 16:51:07');
INSERT INTO `t_userinfo` VALUES ('user2', '123', '张晓丽', '女', '2019-06-13', 'upload/207ad73f-bb91-4f91-89ad-df14cf0c3e54.jpg', '13508084035', 'xiaofe@126.com', '四川南充', '2019-06-13 14:07:21');
