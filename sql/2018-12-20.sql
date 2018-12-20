-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.23 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 ob_admin 的数据库结构
CREATE DATABASE IF NOT EXISTS `ob_admin` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ob_admin`;


-- 导出  表 ob_admin.tbl_menu 结构
CREATE TABLE IF NOT EXISTS `tbl_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT '' COMMENT '菜单名',
  `menu_type` tinyint(4) DEFAULT '1' COMMENT '菜单的类型，主要区别管理员和客户，因为管理员菜单不需要和账号匹配  1 管理员 2客户',
  `menu_logo` varchar(255) DEFAULT '' COMMENT '菜单的logo 图片地址',
  `level` int(11) DEFAULT '1' COMMENT '菜单的排序，从上到小 1>',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='左侧一级菜单';

-- 正在导出表  ob_admin.tbl_menu 的数据：~1 rows (大约)
DELETE FROM `tbl_menu`;
/*!40000 ALTER TABLE `tbl_menu` DISABLE KEYS */;
INSERT INTO `tbl_menu` (`id`, `name`, `menu_type`, `menu_logo`, `level`) VALUES
	(1, '左侧菜单导航', 1, '/static/img/aside/menuSet.png', 1),
	(4, '项目', 1, '/static/img/aside/object.png', 2);
/*!40000 ALTER TABLE `tbl_menu` ENABLE KEYS */;


-- 导出  表 ob_admin.tbl_menu_second 结构
CREATE TABLE IF NOT EXISTS `tbl_menu_second` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT '' COMMENT '菜单名',
  `url` varchar(255) DEFAULT '/index' COMMENT '点击菜单之后动作',
  `menu_type` tinyint(4) DEFAULT '1' COMMENT '菜单的类型，主要区别管理员和客户，因为管理员菜单不需要和账号匹配  1 管理员 2客户',
  `menu_parent` int(11) DEFAULT '0' COMMENT '上级菜单',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='左侧二级菜单表';

-- 正在导出表  ob_admin.tbl_menu_second 的数据：~3 rows (大约)
DELETE FROM `tbl_menu_second`;
/*!40000 ALTER TABLE `tbl_menu_second` DISABLE KEYS */;
INSERT INTO `tbl_menu_second` (`id`, `name`, `url`, `menu_type`, `menu_parent`) VALUES
	(1, '菜单结构', '/index/route?control=admin&url=menu_set', 1, 1),
	(5, '项目菜单', '/index/route?control=admin&url=menu_poject_relation', 1, 1),
	(11, '项目管理', '/index/route?control=admin&url=project_manage', 1, 4);
/*!40000 ALTER TABLE `tbl_menu_second` ENABLE KEYS */;


-- 导出  表 ob_admin.tbl_object 结构
CREATE TABLE IF NOT EXISTS `tbl_object` (
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='项目记录表';

-- 正在导出表  ob_admin.tbl_object 的数据：~11 rows (大约)
DELETE FROM `tbl_object`;
/*!40000 ALTER TABLE `tbl_object` DISABLE KEYS */;
INSERT INTO `tbl_object` (`id`, `owner`, `name`, `logo`, `descr`, `create_time`, `hold_date`, `hold_cost`, `hold_ins`, `user_id`, `status`) VALUES
	(1, '创荣科技', '全V健康', 'http://ob-admin.oss-cn-beijing.aliyuncs.com/picture/15520449931/1545282462028_64.jpg?x-oss-process=style/ob-picture-style', '创荣科技项目....', '2018-12-20 10:06:36', '2019-02-20 16:00:43', 0, '全职维护', 0, 1),
	(12, '1', '1', 'http://p4.so.qhmsg.com/bdr/_240_/t0167d665543136be39.jpg', '1', '2018-12-20 15:53:51', '2019-01-20 16:02:22', 1, '1', 0, 1),
	(13, '2', '2', 'http://p0.so.qhmsg.com/bdr/_240_/t01c8e252970741d135.jpg', '2', '2018-12-20 15:54:12', '2019-02-20 16:02:29', 2, '2', 0, 1),
	(14, '3', '3', 'http://p0.so.qhimgs1.com/bdr/_240_/t01622d5ef7c62e47ca.jpg', '3', '2018-12-20 15:54:22', '2018-12-20 10:06:36', 3, '3', 0, 2),
	(15, '4', '4', 'http://p1.so.qhimgs1.com/bdr/_240_/t014d261b64cc975afd.jpg', '4', '2018-12-20 15:54:47', '2018-11-20 10:06:36', 4, '4', 0, 3);
/*!40000 ALTER TABLE `tbl_object` ENABLE KEYS */;


-- 导出  表 ob_admin.tbl_user 结构
CREATE TABLE IF NOT EXISTS `tbl_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT '0',
  `password` varchar(255) DEFAULT '123456',
  `level` tinyint(1) DEFAULT '2' COMMENT '账号类型1总管理员  2客户',
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 正在导出表  ob_admin.tbl_user 的数据：~1 rows (大约)
DELETE FROM `tbl_user`;
/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
INSERT INTO `tbl_user` (`id`, `username`, `password`, `level`) VALUES
	(1, '15520449931', 'f3c4cc9c1d0903b690f668f9e401ab34', 1);
/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
