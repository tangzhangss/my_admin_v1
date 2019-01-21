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

/*Table structure for table `tbl_menu` */

DROP TABLE IF EXISTS `tbl_menu`;

CREATE TABLE `tbl_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT '' COMMENT '菜单名',
  `menu_type` tinyint(4) DEFAULT '1' COMMENT '菜单的类型，主要区别管理员和客户，因为管理员菜单不需要和账号匹配  1 管理员 2客户',
  `menu_logo` varchar(255) DEFAULT '' COMMENT '菜单的logo 图片地址',
  `level` int(11) DEFAULT '1' COMMENT '菜单的排序，从上到小 1>',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='左侧一级菜单';

/*Data for the table `tbl_menu` */

insert  into `tbl_menu`(`id`,`name`,`menu_type`,`menu_logo`,`level`) values (1,'左侧菜单导航',1,'/static/img/aside/menuSet.png',1),(4,'项目',1,'/static/img/aside/object.png',1),(5,'主体信息',2,'/static/img/aside/base.png',2),(6,'整体展示',2,'/static/img/aside/show.png',2);

/*Table structure for table `tbl_menu_project` */

DROP TABLE IF EXISTS `tbl_menu_project`;

CREATE TABLE `tbl_menu_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) DEFAULT '0' COMMENT '菜单id',
  `oid` int(11) DEFAULT '0' COMMENT '项目id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_menu_project` */

insert  into `tbl_menu_project`(`id`,`mid`,`oid`) values (10,14,1),(13,13,1),(14,15,1),(15,16,1),(16,17,1);

/*Table structure for table `tbl_menu_second` */

DROP TABLE IF EXISTS `tbl_menu_second`;

CREATE TABLE `tbl_menu_second` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT '' COMMENT '菜单名',
  `url` varchar(255) DEFAULT '/index' COMMENT '点击菜单之后动作',
  `menu_type` tinyint(4) DEFAULT '1' COMMENT '菜单的类型，主要区别管理员和客户，因为管理员菜单不需要和账号匹配  1 管理员 2客户',
  `menu_parent` int(11) DEFAULT '0' COMMENT '上级菜单',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='左侧二级菜单表';

/*Data for the table `tbl_menu_second` */

insert  into `tbl_menu_second`(`id`,`name`,`url`,`menu_type`,`menu_parent`) values (1,'菜单结构','/index/route?control=admin&url=menu_set',1,1),(5,'项目菜单','/index/route?control=admin&url=menu_poject_relation',1,1),(11,'项目管理','/index/route?control=admin&url=project_manage',1,4),(12,'客户管理','/index/route?control=admin&url=user_manage',1,4),(14,'访问统计','/index/route?control=wxapp&url=statistics',2,5),(15,'基本设置','/index/route?control=wxapp&url=baseinfo',2,5),(16,'轮播图','/index/route?control=wxapp&url=banner',2,6),(17,'商户平台','/index/route?control=wxapp&url=merchant',2,5);

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

insert  into `tbl_object`(`id`,`owner`,`name`,`logo`,`descr`,`create_time`,`hold_date`,`hold_cost`,`hold_ins`,`user_id`,`status`) values (1,'创荣科技','全V健康','http://ob-admin.oss-cn-beijing.aliyuncs.com/picture/17781694763/1545811485412_64.jpg?x-oss-process=style/ob-picture-style','全V健康后台管理..','2018-12-20 10:06:36','2019-04-20 16:00:43',0,'全职维护',12,1);

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

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
