/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306 10.0.10.195
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : shiro

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-05-31 13:57:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(50) DEFAULT NULL,
  `password_salt` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('Mark', '1', '123456', null);
INSERT INTO `users` VALUES ('user', '2', '283538989cef48f3d7d8a1c1bdf2008f', 'Mark');
