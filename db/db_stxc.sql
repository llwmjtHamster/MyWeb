/*
Navicat MySQL Data Transfer

Source Server         : db
Source Server Version : 50022
Source Host           : localhost:3306
Source Database       : db_stxc

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2019-03-01 09:11:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `ID` int(11) NOT NULL,
  `LOGINNAME` varchar(50) collate utf8_bin default NULL,
  `LOGINPSW` varchar(50) collate utf8_bin default NULL,
  `USERNAME` varchar(50) collate utf8_bin default NULL,
  `USERTYPE` varchar(10) collate utf8_bin default NULL,
  `CREATETIME` varchar(50) collate utf8_bin default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('4', 'admin', '123456', '管理员', null, '2019-01-18 15:47:13');

-- ----------------------------
-- Table structure for `news`
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `ID` int(11) NOT NULL auto_increment,
  `NEWSTYPE` varchar(100) default NULL,
  `TITLE` varchar(100) default NULL,
  `IMGPATH` varchar(100) default NULL,
  `SHIPIN` varchar(100) default NULL,
  `CONTENT` text,
  `CREATETIME` varchar(100) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES ('43', '1', '212', '1551380923082.jpg', '1551380935008.mp4', '<p>\r\n	12</p>\r\n', '2019-03-01 03:08:59');
INSERT INTO `news` VALUES ('44', '2', '323', '1551381234568.jpg', '', '<p>\r\n	12</p>\r\n', '2019-03-01 03:13:59');
INSERT INTO `news` VALUES ('45', '2', '图片新闻', '1551402427991.jpg', '', '<p style=\"margin: 0px; padding: 0px; list-style: none; border: 0px; font-family: 宋体; font-size: 14px;\">\r\n	<img border=\"0\" height=\"417\" hspace=\"0\" src=\"http://www.cicn.com.cn/_CMS_NEWS_IMG_/20190227/1551225552239fce430e0a9df355241989bf97f58ca9b.jpg\" style=\"margin: 0px; padding: 0px; list-style: none; border: 0px; width: 600px; height: 417px;\" title=\"\" vspace=\"0\" width=\"600\" /></p>\r\n<p style=\"margin: 0px; padding: 0px; list-style: none; border: 0px; font-family: 宋体; font-size: 14px;\">\r\n	近日，江苏省南京市浦口区市场监管局针对辖区内农贸市场、商场超市等使用的计量器具是否依法进行检定、是否超过检定周期继续使用以及经营者在交易过程中是否存在不诚实计量、缺斤少两等行为开展专项检查。同时，该局执法人员还对商家进行诚信计量法律法规宣传，要求其守法经营、诚信自律。□钟 静/摄</p>\r\n', '2019-03-01 09:07:18');
INSERT INTO `news` VALUES ('46', '1', '视频细纹', '1551402451927.jpg', '1551402463362.mp4', '<p>\r\n	视频细纹视频细纹视频细纹视频细纹视频细纹视频细纹视频细纹视频细纹视频细纹</p>\r\n', '2019-03-01 09:07:53');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` int(11) NOT NULL auto_increment,
  `LOGINNAME` varchar(50) default NULL,
  `LOGINPW` varchar(50) default NULL,
  `NAME` varchar(50) default NULL,
  `IDNUM` varchar(250) default NULL,
  `SEX` varchar(50) default NULL,
  `AGE` varchar(50) default NULL,
  `ADDRESS` varchar(50) default NULL,
  `XUELI` varchar(255) default NULL,
  `DIANHUA` varchar(255) default NULL,
  `FILEPATH` varchar(255) default NULL,
  `DEL` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('45', 'zhangfei', '123456', '1', '1', '男', '', '北京北路201', '', '13369965540', '1549388918192.jpg', '');
INSERT INTO `user` VALUES ('46', 'guanyu', '123456', '', '1', '男', '', '上海东方121', '', '13355448874', '1549389271623.jpg', '');
INSERT INTO `user` VALUES ('47', '21', '21', '212', '', '?', '', '21', '', '21', '1551402187905.jpg', '');
INSERT INTO `user` VALUES ('48', 'wanglei', '123456', '??', '65231448787741', '?', '', '????1221', '', '1336655487', '1551402538004.jpg', '');
