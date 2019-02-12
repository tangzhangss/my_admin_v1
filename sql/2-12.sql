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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_menu_project` */

insert  into `tbl_menu_project`(`id`,`mid`,`oid`) values (10,14,1),(13,13,1),(14,15,1),(15,16,1),(16,17,1),(17,18,1);

/*Table structure for table `tbl_menu_second` */

DROP TABLE IF EXISTS `tbl_menu_second`;

CREATE TABLE `tbl_menu_second` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT '' COMMENT '菜单名',
  `url` varchar(255) DEFAULT '/index' COMMENT '点击菜单之后动作',
  `menu_type` tinyint(4) DEFAULT '1' COMMENT '菜单的类型，主要区别管理员和客户，因为管理员菜单不需要和账号匹配  1 管理员 2客户',
  `menu_parent` int(11) DEFAULT '0' COMMENT '上级菜单',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='左侧二级菜单表';

/*Data for the table `tbl_menu_second` */

insert  into `tbl_menu_second`(`id`,`name`,`url`,`menu_type`,`menu_parent`) values (1,'菜单结构','/index/route?control=admin&url=menu_set',1,1),(5,'项目菜单','/index/route?control=admin&url=menu_poject_relation',1,1),(11,'项目管理','/index/route?control=admin&url=project_manage',1,4),(12,'客户管理','/index/route?control=admin&url=user_manage',1,4),(14,'访问统计','/index/route?control=wxapp&url=statistics',2,5),(15,'基本设置','/index/route?control=wxapp&url=baseinfo',2,5),(16,'轮播图','/index/route?control=wxapp&url=banner',2,6),(17,'商户平台','/index/route?control=wxapp&url=merchant',2,5),(18,'用户基础信息','/index/route?control=wxapp_v&url=alluser',2,7);

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

/*Table structure for table `tbl_z_v_member` */

DROP TABLE IF EXISTS `tbl_z_v_member`;

CREATE TABLE `tbl_z_v_member` (
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10021 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_z_v_member` */

insert  into `tbl_z_v_member`(`id`,`openid`,`wxopenid`,`sex`,`avatars`,`nickname`,`province`,`city`,`identity`,`create_time`,`realname`,`realavatars`,`contact`,`birthday`) values (10000,'wxb8155eccd3eaeda4','wxb8155eccd3eaeda4',1,'https://ps.ssl.qhimg.com/t0130a8defbfa9ea1c3.jpg','5rWL6K+V6LSm5Y+35LqM','四川','成都',1,'2019-01-30 15:39:41','唐一','https://ps.ssl.qhimg.com/t01fbc64cc5ebe202e8.jpg','15520449931','1996-12-27'),(10001,'axb8155eccd3eaeda4','axb8155eccd3eaeda4',1,'https://ps.ssl.qhimg.com/t01b0262b5951df4f15.jpg','5rWL6K+V6LSm5Y+35LiA','四川','成都',2,'2019-01-30 15:42:27','唐二','https://ps.ssl.qhimg.com/t01b780678b95aa668a.jpg','15892453157','1998-11-27'),(10002,'test1','test1',2,'https://ps.ssl.qhimg.com/t01c0fcfd495374681c.jpg','5rWL6K+V6LSm5Y+35LiJ','四川','成都',3,'2019-01-30 15:44:48','唐三','https://ps.ssl.qhimg.com/t01e8de37412884ac07.jpg','12236987987','1992-01-23'),(10010,NULL,'',1,NULL,'5rWL6K',NULL,NULL,1,'2019-02-12 14:00:14','1',NULL,NULL,NULL),(10011,NULL,'',1,NULL,'5rWL6K',NULL,NULL,1,'2019-02-12 14:00:19','2',NULL,NULL,NULL),(10012,NULL,'',1,NULL,'5rWL6K',NULL,NULL,1,'2019-02-12 14:00:22','3',NULL,NULL,NULL),(10013,NULL,'',1,NULL,'5rWL6K',NULL,NULL,1,'2019-02-12 14:00:25','4',NULL,NULL,NULL),(10014,NULL,'',1,NULL,'5rWL6K',NULL,NULL,1,'2019-02-12 14:03:57',NULL,NULL,NULL,NULL),(10015,NULL,'',1,NULL,'5rWL6K',NULL,NULL,1,'2019-02-12 14:03:58',NULL,NULL,NULL,NULL),(10016,NULL,'',1,NULL,'5rWL6K',NULL,NULL,1,'2019-02-12 14:03:59',NULL,NULL,NULL,NULL),(10017,NULL,'',1,NULL,'5rWL6K',NULL,NULL,1,'2019-02-12 14:04:00',NULL,NULL,NULL,NULL),(10018,NULL,'',1,NULL,'5rWL6K',NULL,NULL,1,'2019-02-12 14:04:02',NULL,NULL,NULL,NULL),(10019,NULL,'',1,NULL,'5rWL6K',NULL,NULL,1,'2019-02-12 14:04:03',NULL,NULL,NULL,NULL),(10020,NULL,'',1,NULL,'5rWL6K',NULL,NULL,1,'2019-02-12 14:04:24',NULL,NULL,NULL,NULL);

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_z_v_member_doctor` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
