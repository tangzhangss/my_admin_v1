-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.30 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 my_admin 的数据库结构
CREATE DATABASE IF NOT EXISTS `my_admin` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `my_admin`;


-- 导出  表 my_admin.tbl_aliyun 结构
CREATE TABLE IF NOT EXISTS `tbl_aliyun` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `oss_access_key_id` varchar(255) DEFAULT NULL,
  `oss_access_key_secret` varchar(255) DEFAULT NULL,
  `oss_endpoint` varchar(255) DEFAULT NULL,
  `obid` int(11) DEFAULT NULL COMMENT '项目ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- 正在导出表  my_admin.tbl_aliyun 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `tbl_aliyun` DISABLE KEYS */;
INSERT INTO `tbl_aliyun` (`id`, `oss_access_key_id`, `oss_access_key_secret`, `oss_endpoint`, `obid`) VALUES
	(2, 'LTAIfTOaXql2pyDV', 'l97JyNt6lEGhOwNl7keaNf6chxmp0H', 'oss-accelerate.aliyuncs.com', 3),
	(3, NULL, NULL, NULL, 4);
/*!40000 ALTER TABLE `tbl_aliyun` ENABLE KEYS */;


-- 导出  表 my_admin.tbl_banner 结构
CREATE TABLE IF NOT EXISTS `tbl_banner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(50) DEFAULT NULL COMMENT '编号',
  `descr` varchar(255) DEFAULT NULL COMMENT '使用场景说明',
  `width` varchar(10) DEFAULT NULL COMMENT '轮播图宽度',
  `height` varchar(10) DEFAULT NULL COMMENT '轮播图高度',
  `obId` int(11) NOT NULL COMMENT '项目id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 正在导出表  my_admin.tbl_banner 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `tbl_banner` DISABLE KEYS */;
INSERT INTO `tbl_banner` (`id`, `number`, `descr`, `width`, `height`, `obId`) VALUES
	(1, 'quanv_index', '首页轮播图_', '100%', '400rpx', 2);
/*!40000 ALTER TABLE `tbl_banner` ENABLE KEYS */;


-- 导出  表 my_admin.tbl_banner_child 结构
CREATE TABLE IF NOT EXISTS `tbl_banner_child` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL COMMENT '图片路径',
  `target` varchar(255) DEFAULT NULL COMMENT '目标',
  `navurl` varchar(255) DEFAULT NULL COMMENT '跳转链接',
  `appId` varchar(255) DEFAULT NULL COMMENT 'appid',
  `path` varchar(255) DEFAULT NULL COMMENT '小程序跳转路径',
  `extra_data` varchar(255) DEFAULT NULL COMMENT '参数',
  `bid` int(11) DEFAULT NULL COMMENT '轮播图id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  my_admin.tbl_banner_child 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `tbl_banner_child` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_banner_child` ENABLE KEYS */;


-- 导出  表 my_admin.tbl_menu 结构
CREATE TABLE IF NOT EXISTS `tbl_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT '' COMMENT '菜单名',
  `menu_type` tinyint(4) DEFAULT '1' COMMENT '菜单的类型，主要区别管理员和客户，因为管理员菜单不需要和账号匹配  1 管理员 2客户',
  `menu_logo` varchar(255) DEFAULT '' COMMENT '菜单的logo 图片地址',
  `level` int(11) DEFAULT '1' COMMENT '菜单的排序，从上到小 1>',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='左侧一级菜单';

-- 正在导出表  my_admin.tbl_menu 的数据：~6 rows (大约)
/*!40000 ALTER TABLE `tbl_menu` DISABLE KEYS */;
INSERT INTO `tbl_menu` (`id`, `name`, `menu_type`, `menu_logo`, `level`) VALUES
	(1, '左侧菜单导航', 1, '/static/tzss-img/aside/menuSet.png', 1),
	(4, '项目', 1, '/static/tzss-img/aside/object.png', 1),
	(5, '主体信息', 2, '/static/tzss-img/aside/base.png', 2),
	(6, '整体展示', 2, '/static/tzss-img/aside/show.png', 2),
	(7, '用户', 2, '/static/tzss-img/aside/usermanage.png', 3),
	(9, '平台交易', 2, '/static/tzss-img/aside/deal.png', 3);
/*!40000 ALTER TABLE `tbl_menu` ENABLE KEYS */;


-- 导出  表 my_admin.tbl_menu_project 结构
CREATE TABLE IF NOT EXISTS `tbl_menu_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) DEFAULT '0' COMMENT '菜单id',
  `oid` int(11) DEFAULT '0' COMMENT '项目id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- 正在导出表  my_admin.tbl_menu_project 的数据：~22 rows (大约)
/*!40000 ALTER TABLE `tbl_menu_project` DISABLE KEYS */;
INSERT INTO `tbl_menu_project` (`id`, `mid`, `oid`) VALUES
	(3, 20, 1),
	(4, 19, 1),
	(6, 21, 1),
	(11, 21, 2),
	(14, 23, 3),
	(15, 24, 3),
	(16, 25, 3),
	(22, 26, 3),
	(23, 27, 3),
	(24, 33, 3),
	(25, 29, 3),
	(27, 35, 3),
	(28, 36, 3),
	(29, 37, 3),
	(31, 22, 4),
	(32, 15, 4),
	(33, 38, 4),
	(34, 16, 4),
	(35, 14, 4),
	(36, 34, 4),
	(37, 18, 4),
	(38, 17, 4);
/*!40000 ALTER TABLE `tbl_menu_project` ENABLE KEYS */;


-- 导出  表 my_admin.tbl_menu_second 结构
CREATE TABLE IF NOT EXISTS `tbl_menu_second` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT '' COMMENT '菜单名',
  `url` varchar(255) DEFAULT '/index' COMMENT '点击菜单之后动作',
  `menu_type` tinyint(4) DEFAULT '1' COMMENT '菜单的类型，主要区别管理员和客户，因为管理员菜单不需要和账号匹配  1 管理员 2客户',
  `menu_parent` int(11) DEFAULT '0' COMMENT '上级菜单',
  `obid` int(11) DEFAULT '0' COMMENT '默认0 所有项目 项目ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='左侧二级菜单表';

-- 正在导出表  my_admin.tbl_menu_second 的数据：~12 rows (大约)
/*!40000 ALTER TABLE `tbl_menu_second` DISABLE KEYS */;
INSERT INTO `tbl_menu_second` (`id`, `name`, `url`, `menu_type`, `menu_parent`, `obid`) VALUES
	(1, '菜单结构', '/index/route?control=admin&url=menu_set', 1, 1, 0),
	(5, '项目菜单', '/index/route?control=admin&url=menu_poject_relation', 1, 1, 0),
	(11, '项目管理', '/index/route?control=admin&url=project_manage', 1, 4, 0),
	(12, '客户管理', '/index/route?control=admin&url=user_manage', 1, 4, 0),
	(14, '访问统计[微信小程序]', '/index/route?control=wxapp&url=statistics', 2, 5, 0),
	(15, '基本设置', '/index/route?control=wxapp&url=baseinfo', 2, 5, 0),
	(16, '轮播图', '/index/route?control=wxapp&url=banner', 2, 6, 0),
	(17, '商户平台', '/index/route?control=wxapp&url=merchant', 2, 5, 0),
	(18, '平台用户', '/index/route?control=wxapp&url=alluser', 2, 7, 0),
	(22, '用户付款订单', '/index/route?control=wxapp&url=member_recharge', 2, 9, 0),
	(34, '模板消息', '/index/route?control=wxapp&url=template', 2, 5, 0),
	(38, '阿里云配置', '/index/route?control=wxapp&url=aliyun', 2, 5, 0);
/*!40000 ALTER TABLE `tbl_menu_second` ENABLE KEYS */;


-- 导出  表 my_admin.tbl_object 结构
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='项目记录表';

-- 正在导出表  my_admin.tbl_object 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `tbl_object` DISABLE KEYS */;
INSERT INTO `tbl_object` (`id`, `owner`, `name`, `logo`, `descr`, `create_time`, `hold_date`, `hold_cost`, `hold_ins`, `user_id`, `status`) VALUES
	(4, 'test', 'test', 'https://my1admin.oss-cn-beijing.aliyuncs.com/picture/15520449931/1622169976689_2020LOGO.png?x-oss-process=style/my1admin-picture-style', '/...', '2021-05-28 10:45:54', '2021-05-28 10:45:54', 1, '1', 13, 2);
/*!40000 ALTER TABLE `tbl_object` ENABLE KEYS */;


-- 导出  表 my_admin.tbl_user 结构
CREATE TABLE IF NOT EXISTS `tbl_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT '0',
  `password` varchar(255) DEFAULT '123456',
  `level` tinyint(1) DEFAULT '2' COMMENT '账号类型1总管理员  2客户',
  `prev_oid` int(11) DEFAULT NULL COMMENT '上一次打开的项目ID_多个项目_记住我登录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- 正在导出表  my_admin.tbl_user 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
INSERT INTO `tbl_user` (`id`, `username`, `password`, `level`, `prev_oid`) VALUES
	(10, '15520449931', 'f3c4cc9c1d0903b690f668f9e401ab34', 1, 2),
	(13, '123456', '4280d89a5a03f812751f504cc10ee8a5', 2, NULL);
/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;


-- 导出  表 my_admin.tbl_weixin_merchant 结构
CREATE TABLE IF NOT EXISTS `tbl_weixin_merchant` (
  `mchid` varchar(255) NOT NULL COMMENT '支付商户号',
  `mchkey` varchar(255) DEFAULT NULL COMMENT '商户支付密钥',
  `trade_type` varchar(255) DEFAULT 'JSAPI' COMMENT '交易类型 目前暂定不可更改',
  `body` varchar(255) DEFAULT '产品支付' COMMENT '商品类型',
  `sslcert_path` varchar(255) DEFAULT NULL COMMENT 'cert证书服务器绝对路径',
  `sslkey_path` varchar(255) DEFAULT NULL COMMENT 'key证书服务器绝对路径',
  `root_path` varchar(255) DEFAULT NULL COMMENT 'root证书服务器路径',
  `oid` int(11) NOT NULL COMMENT '小程序id',
  PRIMARY KEY (`oid`),
  KEY `oid` (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信商户平台 支付设置';


-- 导出  表 my_admin.tbl_weixin_order 结构
CREATE TABLE IF NOT EXISTS `tbl_weixin_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `oid` int(11) NOT NULL DEFAULT '0' COMMENT '项目id',
  `openid` varchar(50) DEFAULT NULL COMMENT '付款人微信openid | 小程序openid',
  `money` double DEFAULT NULL COMMENT '充值金额',
  `amount` double DEFAULT NULL COMMENT '付款金额',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
  `pay_time` varchar(50) DEFAULT NULL COMMENT '付款时间',
  `state` tinyint(4) DEFAULT '0' COMMENT '订单状态 0待付款 1已付款 2付款失败 3退款 4 付款成功 服务器错误',
  `out_trade_no` varchar(50) DEFAULT NULL COMMENT '订单号',
  `bank_type` varchar(50) DEFAULT NULL COMMENT '付款银行',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=323 DEFAULT CHARSET=utf8 COMMENT='订单表...';



-- 导出  表 my_admin.tbl_weixin_template 结构
CREATE TABLE IF NOT EXISTS `tbl_weixin_template` (
  `id` varchar(50) NOT NULL,
  `template_id` varchar(255) DEFAULT NULL COMMENT '模板id',
  `url` varchar(255) NOT NULL DEFAULT '' COMMENT '详情页',
  `admin_openid` varchar(255) DEFAULT NULL COMMENT '模板服务管理者openID  用户发送通知',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注：如，全V健康 购买成功通知',
  `oid` int(11) DEFAULT NULL COMMENT '项目ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信模板消息——后台添加id和template_id_admin等数据_仅由开发者维护 因ID在程序中固定';


-- 导出  表 my_admin.tbl_wxapp 结构
CREATE TABLE IF NOT EXISTS `tbl_wxapp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appid` varchar(50) NOT NULL DEFAULT '0',
  `secret` varchar(50) NOT NULL DEFAULT '0',
  `ob_id` int(11) NOT NULL DEFAULT '0' COMMENT '对应项目的id',
  `status` tinyint(1) DEFAULT '1' COMMENT '1正常使用 2 审核中',
  `gzappid` varchar(50) DEFAULT '0' COMMENT '公众号appid',
  `gzsecret` varchar(50) DEFAULT '0' COMMENT '公众号secret',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;



-- 导出  表 my_admin.tbl_wxapp_balance 结构
CREATE TABLE IF NOT EXISTS `tbl_wxapp_balance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `oid` int(11) DEFAULT NULL COMMENT '项目id',
  `openid` varchar(50) DEFAULT NULL COMMENT '微信 识别 小程序 or 公众号',
  `balance` double DEFAULT '0' COMMENT '余额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;




-- 导出  表 my_admin.tbl_wxapp_bank 结构
CREATE TABLE IF NOT EXISTS `tbl_wxapp_bank` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `oid` int(11) NOT NULL DEFAULT '1' COMMENT '项目id',
  `openid` varchar(50) DEFAULT NULL COMMENT 'openid 微信小程序都可',
  `card` varchar(50) DEFAULT NULL COMMENT '卡号',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `account` varchar(50) DEFAULT NULL COMMENT '开户行',
  `record` text COMMENT '交易记录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='银行卡绑定..';



-- 导出  表 my_admin.tbl_wxapp_member 结构
CREATE TABLE IF NOT EXISTS `tbl_wxapp_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `openid` varchar(50) DEFAULT NULL COMMENT '小程序openid',
  `wxopenid` varchar(50) DEFAULT '' COMMENT '公众号openid',
  `unionid` varchar(255) DEFAULT '' COMMENT 'unionid',
  `gender` tinyint(4) DEFAULT '1' COMMENT '性别 1男 2女',
  `avatars` varchar(255) DEFAULT NULL COMMENT '微信头像',
  `nickname` varchar(255) DEFAULT '' COMMENT '微信名 base64编码之后的',
  `oid` int(11) DEFAULT NULL COMMENT '项目id',
  `province` varchar(255) DEFAULT '' COMMENT '省',
  `city` varchar(255) DEFAULT '' COMMENT '市',
  `identity` tinyint(4) DEFAULT '1' COMMENT '身份1 普通 2医生',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `realname` varchar(50) DEFAULT '匿名' COMMENT '真实姓名',
  `realavatars` varchar(255) DEFAULT 'https://my1admin.oss-cn-beijing.aliyuncs.com/assist/default-avatars2.jpg' COMMENT '真实头像 自定义',
  `contact` varchar(50) DEFAULT '' COMMENT '联系方式',
  `birthday` varchar(50) DEFAULT '' COMMENT '出生年月',
  `online` tinyint(4) DEFAULT '0' COMMENT '是否在线   1在线',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=515 DEFAULT CHARSET=utf8mb4;

