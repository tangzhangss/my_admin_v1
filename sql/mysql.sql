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


-- 导出  表 ob_admin.tbl_banner 结构
CREATE TABLE IF NOT EXISTS `tbl_banner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(50) DEFAULT NULL COMMENT '编号',
  `descr` varchar(255) DEFAULT NULL COMMENT '使用场景说明',
  `width` varchar(10) DEFAULT NULL COMMENT '轮播图宽度',
  `height` varchar(10) DEFAULT NULL COMMENT '轮播图高度',
  `obId` int(11) NOT NULL COMMENT '项目id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 正在导出表  ob_admin.tbl_banner 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `tbl_banner` DISABLE KEYS */;
INSERT INTO `tbl_banner` (`id`, `number`, `descr`, `width`, `height`, `obId`) VALUES
	(3, 'v_index', '全V健康首页.....', '100%', '300rpx', 1);
/*!40000 ALTER TABLE `tbl_banner` ENABLE KEYS */;


-- 导出  表 ob_admin.tbl_banner_child 结构
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- 正在导出表  ob_admin.tbl_banner_child 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `tbl_banner_child` DISABLE KEYS */;
INSERT INTO `tbl_banner_child` (`id`, `url`, `target`, `navurl`, `appId`, `path`, `extra_data`, `bid`) VALUES
	(6, 'https://ps.ssl.qhimg.com/sdmt/180_135_100/t01a5144cc77aa8e278.jpg', 'self', '111', '2', '3', '4', 3),
	(7, 'https://ps.ssl.qhimg.com/sdmt/174_135_100/t014a689e4fce861a67.jpg', 'self', '', '', '', '', 3),
	(9, 'http://ob-admin.oss-cn-beijing.aliyuncs.com/picture/123456/1548213408987_1547522356.png?x-oss-process=style/ob-picture-style', 'self', '', '', '', '', 3),
	(10, 'https://ps.ssl.qhimg.com/sdmt/201_135_100/t010ecadee5b9bf9b65.jpg', 'self', '', '', '', '', 3);
/*!40000 ALTER TABLE `tbl_banner_child` ENABLE KEYS */;


-- 导出  表 ob_admin.tbl_menu 结构
CREATE TABLE IF NOT EXISTS `tbl_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT '' COMMENT '菜单名',
  `menu_type` tinyint(4) DEFAULT '1' COMMENT '菜单的类型，主要区别管理员和客户，因为管理员菜单不需要和账号匹配  1 管理员 2客户',
  `menu_logo` varchar(255) DEFAULT '' COMMENT '菜单的logo 图片地址',
  `level` int(11) DEFAULT '1' COMMENT '菜单的排序，从上到小 1>',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='左侧一级菜单';

-- 正在导出表  ob_admin.tbl_menu 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `tbl_menu` DISABLE KEYS */;
INSERT INTO `tbl_menu` (`id`, `name`, `menu_type`, `menu_logo`, `level`) VALUES
	(1, '左侧菜单导航', 1, '/static/img/aside/menuSet.png', 1),
	(4, '项目', 1, '/static/img/aside/object.png', 1),
	(5, '主体信息', 2, '/static/img/aside/base.png', 2),
	(6, '整体展示', 2, '/static/img/aside/show.png', 2),
	(7, '平台用户', 2, '/static/img/aside/user.png', 3);
/*!40000 ALTER TABLE `tbl_menu` ENABLE KEYS */;


-- 导出  表 ob_admin.tbl_menu_project 结构
CREATE TABLE IF NOT EXISTS `tbl_menu_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) DEFAULT '0' COMMENT '菜单id',
  `oid` int(11) DEFAULT '0' COMMENT '项目id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- 正在导出表  ob_admin.tbl_menu_project 的数据：~6 rows (大约)
/*!40000 ALTER TABLE `tbl_menu_project` DISABLE KEYS */;
INSERT INTO `tbl_menu_project` (`id`, `mid`, `oid`) VALUES
	(10, 14, 1),
	(13, 13, 1),
	(14, 15, 1),
	(17, 18, 1),
	(18, 19, 1),
	(19, 20, 1);
/*!40000 ALTER TABLE `tbl_menu_project` ENABLE KEYS */;


-- 导出  表 ob_admin.tbl_menu_second 结构
CREATE TABLE IF NOT EXISTS `tbl_menu_second` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT '' COMMENT '菜单名',
  `url` varchar(255) DEFAULT '/index' COMMENT '点击菜单之后动作',
  `menu_type` tinyint(4) DEFAULT '1' COMMENT '菜单的类型，主要区别管理员和客户，因为管理员菜单不需要和账号匹配  1 管理员 2客户',
  `menu_parent` int(11) DEFAULT '0' COMMENT '上级菜单',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='左侧二级菜单表';

-- 正在导出表  ob_admin.tbl_menu_second 的数据：~8 rows (大约)
/*!40000 ALTER TABLE `tbl_menu_second` DISABLE KEYS */;
INSERT INTO `tbl_menu_second` (`id`, `name`, `url`, `menu_type`, `menu_parent`) VALUES
	(1, '菜单结构', '/index/route?control=admin&url=menu_set', 1, 1),
	(5, '项目菜单', '/index/route?control=admin&url=menu_poject_relation', 1, 1),
	(11, '项目管理', '/index/route?control=admin&url=project_manage', 1, 4),
	(12, '客户管理', '/index/route?control=admin&url=user_manage', 1, 4),
	(14, '访问统计', '/index/route?control=wxapp&url=statistics', 2, 5),
	(15, '基本设置', '/index/route?control=wxapp&url=baseinfo', 2, 5),
	(16, '轮播图', '/index/route?control=wxapp&url=banner', 2, 6),
	(17, '商户平台', '/index/route?control=wxapp&url=merchant', 2, 5),
	(18, '用户', '/index/route?control=wxapp_v&url=alluser', 2, 7),
	(19, '患者', '/index/route?control=wxapp_v&url=patient_list', 2, 7),
	(20, '医生', '/index/route?control=wxapp_v&url=doctor_list', 2, 7);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='项目记录表';

-- 正在导出表  ob_admin.tbl_object 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `tbl_object` DISABLE KEYS */;
INSERT INTO `tbl_object` (`id`, `owner`, `name`, `logo`, `descr`, `create_time`, `hold_date`, `hold_cost`, `hold_ins`, `user_id`, `status`) VALUES
	(1, '创荣科技', '全V健康咨询', 'http://ob-admin.oss-cn-beijing.aliyuncs.com/picture/15520449931/1548404006599_64.jpg?x-oss-process=style/ob-picture-style', '全V健康后台管理..', '2018-12-20 10:06:36', '2019-04-20 16:00:43', 0, '全职维护', 12, 1);
/*!40000 ALTER TABLE `tbl_object` ENABLE KEYS */;


-- 导出  表 ob_admin.tbl_user 结构
CREATE TABLE IF NOT EXISTS `tbl_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT '0',
  `password` varchar(255) DEFAULT '123456',
  `level` tinyint(1) DEFAULT '2' COMMENT '账号类型1总管理员  2客户',
  `prev_oid` int(11) DEFAULT NULL COMMENT '上一次打开的项目ID_多个项目_记住我登录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- 正在导出表  ob_admin.tbl_user 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
INSERT INTO `tbl_user` (`id`, `username`, `password`, `level`, `prev_oid`) VALUES
	(10, '15520449931', 'f3c4cc9c1d0903b690f668f9e401ab34', 1, 1),
	(12, '123456', '4280d89a5a03f812751f504cc10ee8a5', 2, 1);
/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;


-- 导出  表 ob_admin.tbl_weixin_merchant 结构
CREATE TABLE IF NOT EXISTS `tbl_weixin_merchant` (
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

-- 正在导出表  ob_admin.tbl_weixin_merchant 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `tbl_weixin_merchant` DISABLE KEYS */;
INSERT INTO `tbl_weixin_merchant` (`mchid`, `mchkey`, `trade_type`, `body`, `sslcert_path`, `sslkey_path`, `root_path`, `oid`) VALUES
	('1494443872', 'szI4oFBnKXHFChLNJJPbNJWE38ROGkhd', 'JSAPI', '正在操作产品支付...', '/etc/cert/1547522356.png', '/etc/cert/cert-1515463237552_www.chuangrongkj.com_apache.zip', '/etc/cert/createsql_doctor_group .txt', 1);
/*!40000 ALTER TABLE `tbl_weixin_merchant` ENABLE KEYS */;


-- 导出  表 ob_admin.tbl_weixin_template 结构
CREATE TABLE IF NOT EXISTS `tbl_weixin_template` (
  `id` varchar(50) NOT NULL,
  `template_id` varchar(255) DEFAULT NULL COMMENT '模板id',
  `url` varchar(255) DEFAULT '' COMMENT '详情页',
  `admin_openid` varchar(255) DEFAULT NULL COMMENT '模板服务管理者openID  用户发送通知',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注：如，全V健康 购买成功通知',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信模板消息——后台添加id和template_id_admin等数据_仅由开发者维护 因ID在程序中固定';

-- 正在导出表  ob_admin.tbl_weixin_template 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `tbl_weixin_template` DISABLE KEYS */;
INSERT INTO `tbl_weixin_template` (`id`, `template_id`, `url`, `admin_openid`, `remark`) VALUES
	('quanvjk_buy_success', 'iAUpsmKFH6Ra3YMgneMI39uIyp-yfGO7lVWpfg7Ar0I', '', 'ouFx40wEl-8cVCxpCFvZsYbarfe4', '全V健康 购买成功通知'),
	('quanvjk_consult_inform', 'Qo788mDRqqsCDiXQ1A2xK8Hf7BDdhblNJUl0lypT3Nc', '', 'ouFx40wEl-8cVCxpCFvZsYbarfe4', '全V健康 新用户咨询通知'),
	('quanvjk_consult_inform_accept', 'dmsgS4N3rPmxthHV6kuGHktyZylojcG5dRJt7HNJPMY', '', 'ouFx40wEl-8cVCxpCFvZsYbarfe4', '全V健康 受理咨询通知'),
	('quanvjk_consult_inform_message', 'ZmBZApSXDdCZ34JpoAevf1ArwOUnJ_1pW3cJcoXo5Dg', '', NULL, '全V健康 用户咨询提醒'),
	('quanvjk_consult_inform_reply', 'yJyhcKYHAqymwXUfsUUdeS_g0b4a2zdN3qra1-FYFUU', '', 'ouFx40wEl-8cVCxpCFvZsYbarfe4', '全V健康 咨询回复消息提醒');
/*!40000 ALTER TABLE `tbl_weixin_template` ENABLE KEYS */;


-- 导出  表 ob_admin.tbl_wxapp 结构
CREATE TABLE IF NOT EXISTS `tbl_wxapp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appid` varchar(50) NOT NULL DEFAULT '0',
  `secret` varchar(50) NOT NULL DEFAULT '0',
  `ob_id` int(11) NOT NULL DEFAULT '0' COMMENT '对应项目的id',
  `status` tinyint(1) DEFAULT '1' COMMENT '1正常使用 2 审核中',
  `gzappid` varchar(50) DEFAULT '0' COMMENT '公众号appid',
  `gzsecret` varchar(50) DEFAULT '0' COMMENT '公众号secret',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 正在导出表  ob_admin.tbl_wxapp 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `tbl_wxapp` DISABLE KEYS */;
INSERT INTO `tbl_wxapp` (`id`, `appid`, `secret`, `ob_id`, `status`, `gzappid`, `gzsecret`) VALUES
	(1, 'wxfa1ffdc91e8964aa', 'addacad9048d06954f1c92347716d213', 1, 1, 'wx1c295ba34e13d135', 'adb9abb4635247d51fc4c7b3438c1993');
/*!40000 ALTER TABLE `tbl_wxapp` ENABLE KEYS */;


-- 导出  表 ob_admin.tbl_wxapp_balance 结构
CREATE TABLE IF NOT EXISTS `tbl_wxapp_balance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `oid` int(11) DEFAULT NULL COMMENT '项目id',
  `openid` varchar(50) DEFAULT NULL COMMENT '微信 识别 小程序 or 公众号',
  `balance` double DEFAULT '0' COMMENT '余额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 正在导出表  ob_admin.tbl_wxapp_balance 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `tbl_wxapp_balance` DISABLE KEYS */;
INSERT INTO `tbl_wxapp_balance` (`id`, `oid`, `openid`, `balance`) VALUES
	(1, 1, 'ouFx40wEl-8cVCxpCFvZsYbarfe4', 10000),
	(2, 1, 'ouFx409YaUP-IFnm92J9pfm10DCk', 10000);
/*!40000 ALTER TABLE `tbl_wxapp_balance` ENABLE KEYS */;


-- 导出  表 ob_admin.tbl_wxapp_bank 结构
CREATE TABLE IF NOT EXISTS `tbl_wxapp_bank` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `oid` int(11) NOT NULL DEFAULT '0' COMMENT '项目id',
  `openid` varchar(50) DEFAULT NULL COMMENT 'openid 微信小程序都可',
  `card` varchar(50) DEFAULT NULL COMMENT '卡号',
  `account` varchar(50) DEFAULT NULL COMMENT '开户行',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='银行卡绑定..';

-- 正在导出表  ob_admin.tbl_wxapp_bank 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `tbl_wxapp_bank` DISABLE KEYS */;
INSERT INTO `tbl_wxapp_bank` (`id`, `oid`, `openid`, `card`, `account`) VALUES
	(7, 1, 'ouFx40wEl-8cVCxpCFvZsYbarfe4', '521400.2443598941145', '成都市第一银行56');
/*!40000 ALTER TABLE `tbl_wxapp_bank` ENABLE KEYS */;


-- 导出  表 ob_admin.tbl_wxapp_member 结构
CREATE TABLE IF NOT EXISTS `tbl_wxapp_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `openid` varchar(50) DEFAULT NULL COMMENT '小程序openid',
  `wxopenid` varchar(50) DEFAULT '' COMMENT '公众号openid',
  `unionid` varchar(255) DEFAULT '' COMMENT 'unionid',
  `gender` tinyint(4) DEFAULT '1' COMMENT '性别 1男 2女',
  `avatars` varchar(255) DEFAULT NULL COMMENT '微信头像',
  `nickname` varchar(255) DEFAULT '' COMMENT '微信名 base64编码之后的',
  `oid` int(11) DEFAULT NULL COMMENT '项目id',
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
) ENGINE=InnoDB AUTO_INCREMENT=10022 DEFAULT CHARSET=utf8;

-- 正在导出表  ob_admin.tbl_wxapp_member 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `tbl_wxapp_member` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_wxapp_member` ENABLE KEYS */;


-- 导出  表 ob_admin.tbl_wxapp_order 结构
CREATE TABLE IF NOT EXISTS `tbl_wxapp_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `oid` int(11) NOT NULL DEFAULT '0' COMMENT '项目id',
  `openid` varchar(50) DEFAULT NULL COMMENT '付款人微信openid | 小程序openid',
  `money` double DEFAULT NULL COMMENT '付款金额',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
  `pay_time` varchar(50) DEFAULT NULL COMMENT '付款时间',
  `state` tinyint(4) DEFAULT '0' COMMENT '订单状态 0待付款 1已付款 2付款失败',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表...';

-- 正在导出表  ob_admin.tbl_wxapp_order 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `tbl_wxapp_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_wxapp_order` ENABLE KEYS */;


-- 导出  表 ob_admin.tbl_z_v_member_consult 结构
CREATE TABLE IF NOT EXISTS `tbl_z_v_member_consult` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_wxopenid` varchar(50) DEFAULT NULL COMMENT '患者的wxopenid',
  `doctor_wxopenid` varchar(50) DEFAULT NULL COMMENT '医生的wxopenid',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '提交咨询时间',
  `server_time` varchar(50) DEFAULT NULL COMMENT '对接咨询时间 完/成医生患者对接',
  `complete_time` varchar(50) DEFAULT NULL COMMENT '服务完成时间（预计完成时间）',
  `title` varchar(250) DEFAULT NULL COMMENT '咨询说明',
  `label` varchar(250) DEFAULT NULL COMMENT '咨询标签 科目说明 乳腺、甲状腺',
  `picdesc` text COMMENT '图片说明 @>|<@ 分割',
  `deal_status` tinyint(4) DEFAULT '1' COMMENT '处理状态 1 带接单 2带完成服务 3已完成服务 4已退款',
  `assess` varchar(250) DEFAULT NULL COMMENT '服务评价',
  `type` tinyint(4) DEFAULT '1' COMMENT '咨询类型1 次（患者只能发送6条）  2 月 3季度 4年',
  `cost` int(11) DEFAULT '0' COMMENT '咨询金额',
  `isbalance` int(1) DEFAULT '0' COMMENT '是否结算0未结算 1已结算',
  `patnoread` int(1) DEFAULT '0' COMMENT '患者未读',
  `docnoread` int(1) DEFAULT '0' COMMENT '医生未读',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- 正在导出表  ob_admin.tbl_z_v_member_consult 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `tbl_z_v_member_consult` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_z_v_member_consult` ENABLE KEYS */;


-- 导出  表 ob_admin.tbl_z_v_member_consult_cost 结构
CREATE TABLE IF NOT EXISTS `tbl_z_v_member_consult_cost` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `wxopenid` varchar(255) NOT NULL COMMENT '医生微信公众号',
  `consult_type` int(11) DEFAULT NULL COMMENT '咨询类型 2  3  4  1表示一次',
  `month_num` int(11) DEFAULT NULL COMMENT '咨询类型对应月数          0表示一次    ',
  `money` int(11) DEFAULT NULL COMMENT '金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- 正在导出表  ob_admin.tbl_z_v_member_consult_cost 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `tbl_z_v_member_consult_cost` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_z_v_member_consult_cost` ENABLE KEYS */;


-- 导出  表 ob_admin.tbl_z_v_member_consult_log 结构
CREATE TABLE IF NOT EXISTS `tbl_z_v_member_consult_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `consult_id` int(11) DEFAULT NULL COMMENT '咨询id',
  `ctype` int(11) DEFAULT '1' COMMENT '咨询类型 1:文字 2：图片 3：语音',
  `message` varchar(255) DEFAULT '' COMMENT '文字消息',
  `image` varchar(255) DEFAULT '' COMMENT '图片消息',
  `video` varchar(255) DEFAULT '' COMMENT '语音消息',
  `identity` int(11) DEFAULT '1' COMMENT '发送消息的身份 1患者 2医生',
  `replytime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '回复时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='咨询聊天内容';

-- 正在导出表  ob_admin.tbl_z_v_member_consult_log 的数据：~18 rows (大约)
/*!40000 ALTER TABLE `tbl_z_v_member_consult_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_z_v_member_consult_log` ENABLE KEYS */;


-- 导出  表 ob_admin.tbl_z_v_member_doctor 结构
CREATE TABLE IF NOT EXISTS `tbl_z_v_member_doctor` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '医生编号',
  `wxopenid` varchar(50) DEFAULT NULL COMMENT '公众号openid',
  `education` varchar(255) DEFAULT NULL COMMENT '教育资料',
  `good_field` varchar(255) DEFAULT NULL COMMENT '擅长领域',
  `hospital` varchar(255) DEFAULT NULL COMMENT '坐诊医院',
  `level` varchar(2) DEFAULT '1' COMMENT '医生等级 v1 >',
  `card_face` varchar(255) DEFAULT NULL COMMENT '省份证正面',
  `card_back` varchar(255) DEFAULT NULL COMMENT '身份证反面',
  `certificate_doctor` varchar(255) DEFAULT NULL COMMENT '医师资格证',
  `certificate_practice` varchar(255) DEFAULT NULL COMMENT '执业资格证',
  `isaudit` tinyint(4) DEFAULT '1' COMMENT '医生状态 1待审核  2通过 3不通过',
  `message` varchar(255) DEFAULT NULL COMMENT '信息，可填审核失败原因，等其他扩展',
  `qrcode` varchar(255) DEFAULT NULL COMMENT '医生的二维码_暂时留着不保留,因为需要区别，患者不共享 医生共享的问题',
  PRIMARY KEY (`id`),
  UNIQUE KEY `wxopenid` (`wxopenid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- 正在导出表  ob_admin.tbl_z_v_member_doctor 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `tbl_z_v_member_doctor` DISABLE KEYS */;
INSERT INTO `tbl_z_v_member_doctor` (`id`, `wxopenid`, `education`, `good_field`, `hospital`, `level`, `card_face`, `card_back`, `certificate_doctor`, `certificate_practice`, `isaudit`, `message`, `qrcode`) VALUES
	(9, 'ouFx40wEl-8cVCxpCFvZsYbarfe4', '软件设计师', '微信小程序', '成都创荣科技有限公司', '1', 'http://ob-admin.oss-cn-beijing.aliyuncs.com/wxapp/v/1/1552275754437_wxfa1ffdc91e8964aa.o6zAJs3ryG007UcDlyO4IJBzaivM.uneTuYerUTjU51641fd2b5d606f3535578fa835a4479.jpg?x-oss-process=style/ob-picture-style', 'http://ob-admin.oss-cn-beijing.aliyuncs.com/wxapp/v/1/1552275756052_wxfa1ffdc91e8964aa.o6zAJs3ryG007UcDlyO4IJBzaivM.Vqr4EKSh1pmedf20a131c80a4321d674315fafcbc129.jpg?x-oss-process=style/ob-picture-style', 'http://ob-admin.oss-cn-beijing.aliyuncs.com/wxapp/v/1/1552275757884_wxfa1ffdc91e8964aa.o6zAJs3ryG007UcDlyO4IJBzaivM.KIGCPre5MYVIbc47637010050ffe77bd536c6d89bdd3.jpg?x-oss-process=style/ob-picture-style', 'http://ob-admin.oss-cn-beijing.aliyuncs.com/wxapp/v/1/1552275759063_wxfa1ffdc91e8964aa.o6zAJs3ryG007UcDlyO4IJBzaivM.24LmZJWNg5Cvdf9269660577ac5f950ac7ccacc649fb.jpg?x-oss-process=style/ob-picture-style', 2, '', NULL);
/*!40000 ALTER TABLE `tbl_z_v_member_doctor` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
