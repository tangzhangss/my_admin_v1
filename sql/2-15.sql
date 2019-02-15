/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.7.23 : Database - ob_admin
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ob_admin` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ob_admin`;

/*Table structure for table `tbl_banner` */

DROP TABLE IF EXISTS `tbl_banner`;

CREATE TABLE `tbl_banner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(50) DEFAULT NULL COMMENT '编号',
  `descr` varchar(255) DEFAULT NULL COMMENT '使用场景说明',
  `width` varchar(10) DEFAULT NULL COMMENT '轮播图宽度',
  `height` varchar(10) DEFAULT NULL COMMENT '轮播图高度',
  `obId` int(11) NOT NULL COMMENT '项目id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_banner` */

insert  into `tbl_banner`(`id`,`number`,`descr`,`width`,`height`,`obId`) values (3,'v_index','全V健康首页.....','100%','300rpx',1);

/*Table structure for table `tbl_banner_child` */

DROP TABLE IF EXISTS `tbl_banner_child`;

CREATE TABLE `tbl_banner_child` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL COMMENT '图片路径',
  `target` varchar(255) DEFAULT NULL COMMENT '目标',
  `navurl` varchar(255) DEFAULT NULL COMMENT '跳转链接',
  `appId` varchar(255) DEFAULT NULL COMMENT 'appid',
  `path` varchar(255) DEFAULT NULL COMMENT '小程序跳转路径',
  `extra_data` varchar(255) DEFAULT NULL COMMENT '参数',
  `bid` int(11) DEFAULT NULL COMMENT '轮播图id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_banner_child` */

insert  into `tbl_banner_child`(`id`,`url`,`target`,`navurl`,`appId`,`path`,`extra_data`,`bid`) values (6,'https://ps.ssl.qhimg.com/sdmt/180_135_100/t01a5144cc77aa8e278.jpg','self','111','2','3','4',3),(7,'https://ps.ssl.qhimg.com/sdmt/174_135_100/t014a689e4fce861a67.jpg','self','','','','',3),(9,'http://ob-admin.oss-cn-beijing.aliyuncs.com/picture/123456/1548213408987_1547522356.png?x-oss-process=style/ob-picture-style','self','','','','',3),(10,'https://ps.ssl.qhimg.com/sdmt/201_135_100/t010ecadee5b9bf9b65.jpg','self','','','','',3);

/*Table structure for table `tbl_menu` */

DROP TABLE IF EXISTS `tbl_menu`;

CREATE TABLE `tbl_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT '' COMMENT '菜单名',
  `menu_type` tinyint(4) DEFAULT '1' COMMENT '菜单的类型，主要区别管理员和客户，因为管理员菜单不需要和账号匹配  1 管理员 2客户',
  `menu_logo` varchar(255) DEFAULT '' COMMENT '菜单的logo 图片地址',
  `level` int(11) DEFAULT '1' COMMENT '菜单的排序，从上到小 1>',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='左侧一级菜单';

/*Data for the table `tbl_menu` */

insert  into `tbl_menu`(`id`,`name`,`menu_type`,`menu_logo`,`level`) values (1,'左侧菜单导航',1,'/static/img/aside/menuSet.png',1),(4,'项目',1,'/static/img/aside/object.png',1),(5,'主体信息',2,'/static/img/aside/base.png',2),(6,'整体展示',2,'/static/img/aside/show.png',2),(7,'平台用户',2,'/static/img/aside/user.png',3);

/*Table structure for table `tbl_menu_project` */

DROP TABLE IF EXISTS `tbl_menu_project`;

CREATE TABLE `tbl_menu_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) DEFAULT '0' COMMENT '菜单id',
  `oid` int(11) DEFAULT '0' COMMENT '项目id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_menu_project` */

insert  into `tbl_menu_project`(`id`,`mid`,`oid`) values (10,14,1),(13,13,1),(14,15,1),(15,16,1),(16,17,1),(17,18,1),(18,19,1),(19,20,1);

/*Table structure for table `tbl_menu_second` */

DROP TABLE IF EXISTS `tbl_menu_second`;

CREATE TABLE `tbl_menu_second` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT '' COMMENT '菜单名',
  `url` varchar(255) DEFAULT '/index' COMMENT '点击菜单之后动作',
  `menu_type` tinyint(4) DEFAULT '1' COMMENT '菜单的类型，主要区别管理员和客户，因为管理员菜单不需要和账号匹配  1 管理员 2客户',
  `menu_parent` int(11) DEFAULT '0' COMMENT '上级菜单',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='左侧二级菜单表';

/*Data for the table `tbl_menu_second` */

insert  into `tbl_menu_second`(`id`,`name`,`url`,`menu_type`,`menu_parent`) values (1,'菜单结构','/index/route?control=admin&url=menu_set',1,1),(5,'项目菜单','/index/route?control=admin&url=menu_poject_relation',1,1),(11,'项目管理','/index/route?control=admin&url=project_manage',1,4),(12,'客户管理','/index/route?control=admin&url=user_manage',1,4),(14,'访问统计','/index/route?control=wxapp&url=statistics',2,5),(15,'基本设置','/index/route?control=wxapp&url=baseinfo',2,5),(16,'轮播图','/index/route?control=wxapp&url=banner',2,6),(17,'商户平台','/index/route?control=wxapp&url=merchant',2,5),(18,'用户','/index/route?control=wxapp_v&url=alluser',2,7),(19,'患者','/index/route?control=wxapp_v&url=patient_list',2,7),(20,'医生','/index/route?control=wxapp_v&url=doctor_list',2,7);

/*Table structure for table `tbl_object` */

DROP TABLE IF EXISTS `tbl_object`;

CREATE TABLE `tbl_object` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `owner` varchar(50) NOT NULL COMMENT '主体|个人|公司',
  `name` varchar(255) DEFAULT NULL COMMENT '项目的名称',
  `logo` varchar(255) DEFAULT NULL COMMENT '项目的logo',
  `descr` varchar(255) DEFAULT NULL COMMENT '项目的描述',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '项目的创建时间',
  `hold_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '项目的维护时间 按年算',
  `hold_cost` int(11) DEFAULT '0' COMMENT '项目的维护费用 按年算',
  `hold_ins` varchar(255) DEFAULT '' COMMENT '维护说明',
  `user_id` int(11) DEFAULT '0' COMMENT '项目-用户id',
  `status` tinyint(1) DEFAULT '1' COMMENT '项目状态1 正常 2 到期 3放弃',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='项目记录表';

/*Data for the table `tbl_object` */

insert  into `tbl_object`(`id`,`owner`,`name`,`logo`,`descr`,`create_time`,`hold_date`,`hold_cost`,`hold_ins`,`user_id`,`status`) values (1,'创荣科技','全V健康','http://ob-admin.oss-cn-beijing.aliyuncs.com/picture/15520449931/1548404006599_64.jpg?x-oss-process=style/ob-picture-style','全V健康后台管理..','2018-12-20 10:06:36','2019-04-20 16:00:43',0,'全职维护',12,1);

/*Table structure for table `tbl_order` */

DROP TABLE IF EXISTS `tbl_order`;

CREATE TABLE `tbl_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `wxopenid` varchar(50) DEFAULT NULL COMMENT '付款人微信openid',
  `money` double DEFAULT NULL COMMENT '付款金额',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
  `pay_time` varchar(50) DEFAULT NULL COMMENT '付款时间',
  `state` tinyint(4) DEFAULT '0' COMMENT '订单状态 0待付款 1已付款 2付款失败',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表...';

/*Data for the table `tbl_order` */

/*Table structure for table `tbl_user` */

DROP TABLE IF EXISTS `tbl_user`;

CREATE TABLE `tbl_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT '0',
  `password` varchar(255) DEFAULT '123456',
  `level` tinyint(1) DEFAULT '2' COMMENT '账号类型1总管理员  2客户',
  `prev_oid` int(11) DEFAULT NULL COMMENT '上一次打开的项目ID_多个项目_记住我登录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_user` */

insert  into `tbl_user`(`id`,`username`,`password`,`level`,`prev_oid`) values (10,'15520449931','f3c4cc9c1d0903b690f668f9e401ab34',1,1),(12,'123456','4280d89a5a03f812751f504cc10ee8a5',2,1);

/*Table structure for table `tbl_wxapp` */

DROP TABLE IF EXISTS `tbl_wxapp`;

CREATE TABLE `tbl_wxapp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appid` varchar(50) NOT NULL DEFAULT '0',
  `secret` varchar(50) NOT NULL DEFAULT '0',
  `ob_id` int(11) NOT NULL DEFAULT '0' COMMENT '对应项目的id',
  `status` tinyint(1) DEFAULT '1' COMMENT '1正常使用 2 审核中',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_wxapp` */

insert  into `tbl_wxapp`(`id`,`appid`,`secret`,`ob_id`,`status`) values (1,'wxb8155eccd3eaeda4','4d54e6176b0906d5a7a08bd30c3aa32c',1,1);

/*Table structure for table `tbl_wxapp_member` */

DROP TABLE IF EXISTS `tbl_wxapp_member`;

CREATE TABLE `tbl_wxapp_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `openid` varchar(50) DEFAULT NULL COMMENT '小程序openid',
  `wxopenid` varchar(50) DEFAULT '' COMMENT '公众号openid',
  `sex` tinyint(4) DEFAULT '1' COMMENT '性别 1男 2女',
  `avatars` varchar(255) DEFAULT NULL COMMENT '微信头像',
  `nickname` varchar(255) DEFAULT '' COMMENT '微信名 base64编码之后的',
  `province` varchar(255) DEFAULT NULL COMMENT '省',
  `city` varchar(255) DEFAULT NULL COMMENT '市',
  `identity` tinyint(4) DEFAULT '1' COMMENT '身份1 普通 2患者 3医生',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `realname` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `realavatars` varchar(255) DEFAULT NULL COMMENT '真实头像 自定义',
  `contact` varchar(50) DEFAULT NULL COMMENT '联系方式',
  `birthday` varchar(50) DEFAULT NULL COMMENT '出生年月',
  `online` tinyint(4) DEFAULT '0' COMMENT '是否在线   1在线',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10021 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_wxapp_member` */

insert  into `tbl_wxapp_member`(`id`,`openid`,`wxopenid`,`sex`,`avatars`,`nickname`,`province`,`city`,`identity`,`create_time`,`realname`,`realavatars`,`contact`,`birthday`,`online`) values (10000,'wopenid1','wopenid1',1,'https://ps.ssl.qhimg.com/sdmt/176_135_100/t017716441b37ec13c2.jpg','5rWL6K+V6LSm5Y+35LqM','四川','成都',3,'2019-01-30 15:39:41','医生1','https://ps.ssl.qhimg.com/t01fbc64cc5ebe202e8.jpg','15520449931','1996-12-27',0),(10001,'wopenid11','wopenid11',1,'https://ps.ssl.qhimg.com/sdmt/176_135_100/t017716441b37ec13c2.jpg','5rWL6K+V6LSm5Y+35LiA','四川','成都',3,'2019-01-30 15:42:27','医生2','https://ps.ssl.qhimg.com/t01b780678b95aa668a.jpg','15892453157','1998-11-27',0),(10002,'wopenid11','wopenid112',2,'https://ps.ssl.qhimg.com/sdmt/176_135_100/t017716441b37ec13c2.jpg','5rWL6K+V6LSm5Y+35LiJ','四川','成都',1,'2019-01-30 15:44:48','...','https://ps.ssl.qhimg.com/t01e8de37412884ac07.jpg','12236987987','1992-01-23',0),(10010,NULL,'wopenid111',1,'https://ps.ssl.qhimg.com/sdmt/176_135_100/t017716441b37ec13c2.jpg','5rWL6K',NULL,NULL,3,'2019-02-12 14:00:14','医生4','https://ps.ssl.qhimg.com/sdmt/153_135_100/t01e251e1d35b88298f.jpg',NULL,'1992-01-23',0),(10011,NULL,'wopenid1111',1,'https://ps.ssl.qhimg.com/sdmt/176_135_100/t017716441b37ec13c2.jpg','5rWL6K',NULL,NULL,3,'2019-02-12 14:00:19','医生5','https://ps.ssl.qhimg.com/sdmt/153_135_100/t01e251e1d35b88298f.jpg',NULL,'1992-01-23',0),(10012,NULL,'wopenid11111',1,'https://ps.ssl.qhimg.com/sdmt/176_135_100/t017716441b37ec13c2.jpg','5rWL6K',NULL,NULL,3,'2019-02-12 14:00:22','医生3','https://ps.ssl.qhimg.com/sdmt/153_135_100/t01e251e1d35b88298f.jpg',NULL,'1992-01-23',0),(10013,NULL,'wopenid111111',1,'https://ps.ssl.qhimg.com/sdmt/176_135_100/t017716441b37ec13c2.jpg','5rWL6K',NULL,NULL,3,'2019-02-12 14:00:25','医生6','https://ps.ssl.qhimg.com/sdmt/153_135_100/t01e251e1d35b88298f.jpg',NULL,'1992-01-23',0),(10014,NULL,'wopenid1111111',1,'https://ps.ssl.qhimg.com/sdmt/176_135_100/t017716441b37ec13c2.jpg','5rWL6K',NULL,NULL,2,'2019-02-12 14:03:57','患者1','https://ps.ssl.qhimg.com/sdmt/153_135_100/t01e251e1d35b88298f.jpg','1','1992-01-23',0),(10015,NULL,'wopenid11111111',1,'https://ps.ssl.qhimg.com/sdmt/176_135_100/t017716441b37ec13c2.jpg','5rWL6K',NULL,NULL,2,'2019-02-12 14:03:58','患者2','https://ps.ssl.qhimg.com/sdmt/153_135_100/t01e251e1d35b88298f.jpg','2','1992-01-23',0),(10016,NULL,'wopenid111111111',1,'https://ps.ssl.qhimg.com/sdmt/176_135_100/t017716441b37ec13c2.jpg','5rWL6K',NULL,NULL,2,'2019-02-12 14:03:59','患者3','https://ps.ssl.qhimg.com/sdmt/153_135_100/t01e251e1d35b88298f.jpg','3','1992-02-23',0),(10017,NULL,'wopenid1111111111',1,'https://ps.ssl.qhimg.com/sdmt/176_135_100/t017716441b37ec13c2.jpg','5rWL6K',NULL,NULL,1,'2019-02-12 14:04:00',NULL,'https://ps.ssl.qhimg.com/sdmt/153_135_100/t01e251e1d35b88298f.jpg','4','1992-01-23',0),(10018,NULL,'wopenid11111111111',1,'https://ps.ssl.qhimg.com/sdmt/176_135_100/t017716441b37ec13c2.jpg','5rWL6K',NULL,NULL,2,'2019-02-12 14:04:02','患者4','https://ps.ssl.qhimg.com/sdmt/153_135_100/t01e251e1d35b88298f.jpg','5','1992-01-23',0),(10019,NULL,'wopenid111111111111',1,'https://ps.ssl.qhimg.com/sdmt/176_135_100/t017716441b37ec13c2.jpg','5rWL6K',NULL,NULL,1,'2019-02-12 14:04:03',NULL,'https://ps.ssl.qhimg.com/sdmt/153_135_100/t01e251e1d35b88298f.jpg','6','1992-01-23',0),(10020,NULL,'wopenid1111111111111',1,'https://ps.ssl.qhimg.com/sdmt/176_135_100/t017716441b37ec13c2.jpg','5rWL6K',NULL,NULL,2,'2019-02-12 14:04:24','患者5','https://ps.ssl.qhimg.com/sdmt/153_135_100/t01e251e1d35b88298f.jpg','7','1992-01-23',0);

/*Table structure for table `tbl_wxapp_merchant` */

DROP TABLE IF EXISTS `tbl_wxapp_merchant`;

CREATE TABLE `tbl_wxapp_merchant` (
  `mchid` varchar(255) NOT NULL COMMENT '支付商户号',
  `mchkey` varchar(255) DEFAULT NULL COMMENT '商户支付密钥',
  `trade_type` varchar(255) DEFAULT 'JSAPI' COMMENT '交易类型 目前暂定不可更改',
  `body` varchar(255) DEFAULT '产品支付' COMMENT '商品类型',
  `sslcert_path` varchar(255) DEFAULT NULL COMMENT 'cert证书服务器绝对路径',
  `sslkey_path` varchar(255) DEFAULT NULL COMMENT 'key证书服务器绝对路径',
  `root_path` varchar(255) DEFAULT NULL COMMENT 'root证书服务器路径',
  `oid` int(11) DEFAULT NULL COMMENT '小程序id',
  PRIMARY KEY (`mchid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信商户平台 支付设置';

/*Data for the table `tbl_wxapp_merchant` */

insert  into `tbl_wxapp_merchant`(`mchid`,`mchkey`,`trade_type`,`body`,`sslcert_path`,`sslkey_path`,`root_path`,`oid`) values ('1494443872','szI4oFBnKXHFChLNJJPbNJWE38ROGkhd','JSAPI','正在操作产品支付...','/etc/cert/1547522356.png','/etc/cert/cert-1515463237552_www.chuangrongkj.com_apache.zip','/etc/cert/createsql_doctor_group .txt',1);

/*Table structure for table `tbl_z_v_member_consult` */

DROP TABLE IF EXISTS `tbl_z_v_member_consult`;

CREATE TABLE `tbl_z_v_member_consult` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_wxopenid` varchar(50) DEFAULT NULL COMMENT '患者的wxopenid',
  `doctor_wxopenid` varchar(50) DEFAULT NULL COMMENT '医生的wxopenid',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '提交咨询时间',
  `server_time` varchar(50) DEFAULT NULL COMMENT '对接咨询时间 完/成医生患者对接',
  `complete_time` varchar(50) DEFAULT NULL COMMENT '服务完成时间',
  `title` varchar(250) DEFAULT NULL COMMENT '咨询说明',
  `label` varchar(250) DEFAULT NULL COMMENT '咨询标签 科目说明 乳腺、甲状腺',
  `picdesc` text COMMENT '图片说明 @>|<@ 分割',
  `deal_status` tinyint(4) DEFAULT '1' COMMENT '处理状态 1 带接单 2带完成服务 3已完成服务',
  `assess` varchar(250) DEFAULT NULL COMMENT '服务评价',
  `orderid` varchar(50) DEFAULT NULL COMMENT '订单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_z_v_member_consult` */

insert  into `tbl_z_v_member_consult`(`id`,`patient_wxopenid`,`doctor_wxopenid`,`create_time`,`server_time`,`complete_time`,`title`,`label`,`picdesc`,`deal_status`,`assess`,`orderid`) values (1,'wopenid1111111','wopenid111','2019-02-14 16:20:35','2019-02-14 16:20','2019-02-14 16:20',NULL,'乳腺',NULL,1,'好！',NULL),(2,'wopenid11111111','wopenid111','2019-02-14 16:20:58','2019-02-14 16:20','2019-02-14 16:20',NULL,'乳腺',NULL,1,'好！',NULL),(3,'wopenid111111111','wopenid11','2019-02-14 16:21:03',NULL,NULL,NULL,'乳腺',NULL,1,'好！',NULL),(4,'wopenid11111111111','wopenid1111','2019-02-14 16:21:27',NULL,NULL,NULL,'乳腺',NULL,1,'好！',NULL),(5,'wopenid1111111111111','wopenid11111','2019-02-14 16:21:46',NULL,NULL,NULL,'乳腺',NULL,1,'好！',NULL),(6,'wopenid1111111','wopenid11','2019-02-14 16:25:41',NULL,'2019-02-14 16:20',NULL,'乳腺',NULL,1,'好！',NULL),(7,'wopenid1111111','wopenid11','2019-02-14 16:25:45','2019-02-14 16:20','2019-02-14 16:20',NULL,'乳腺',NULL,1,'好！',NULL);

/*Table structure for table `tbl_z_v_member_doctor` */

DROP TABLE IF EXISTS `tbl_z_v_member_doctor`;

CREATE TABLE `tbl_z_v_member_doctor` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '医生编号',
  `wxopenid` varchar(50) DEFAULT NULL COMMENT '公众号openid',
  `education` varchar(255) DEFAULT NULL COMMENT '教育资料',
  `good_field` varchar(255) DEFAULT NULL COMMENT '擅长领域',
  `hospital` varchar(255) DEFAULT NULL COMMENT '坐诊医院',
  `level` varchar(2) DEFAULT 'V1' COMMENT '医生等级 v1 >',
  `card_face` varchar(255) DEFAULT NULL COMMENT '省份证正面',
  `card_back` varchar(255) DEFAULT NULL COMMENT '身份证反面',
  `certificate_doctor` varchar(255) DEFAULT NULL COMMENT '医师资格证',
  `certificate_practice` varchar(255) DEFAULT NULL COMMENT '执业资格证',
  `isaudit` tinyint(4) DEFAULT '1' COMMENT '医生状态 1待审核  2通过 3不通过',
  `message` varchar(255) DEFAULT NULL COMMENT '信息，可填审核失败原因，等其他扩展',
  `patient_num` int(11) DEFAULT '0' COMMENT '病人数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_z_v_member_doctor` */

insert  into `tbl_z_v_member_doctor`(`id`,`wxopenid`,`education`,`good_field`,`hospital`,`level`,`card_face`,`card_back`,`certificate_doctor`,`certificate_practice`,`isaudit`,`message`,`patient_num`) values (1,'wopenid1','',NULL,'华西','V1',NULL,NULL,NULL,NULL,2,'',0),(2,'wopenid11',NULL,NULL,NULL,'V1',NULL,NULL,NULL,NULL,2,'',0),(3,'wopenid111',NULL,NULL,NULL,'V2','https://ps.ssl.qhimg.com/sdmt/146_135_100/t014a689e4fce861a67.jpg','https://ps.ssl.qhimg.com/sdmt/174_135_100/t01dced49968eb00241.jpg','https://ps.ssl.qhimg.com/sdmt/85_135_100/t0182c9d8cd21e47395.jpg','http://img1.mm131.me/pic/2291/0.jpg',2,'',0),(4,'wopenid1111',NULL,NULL,NULL,'V2',NULL,NULL,NULL,NULL,2,'',0),(5,'wopenid11111',NULL,NULL,NULL,'V3',NULL,NULL,NULL,NULL,2,'',0),(6,'wopenid111111',NULL,NULL,NULL,'V3',NULL,NULL,NULL,NULL,2,'',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
