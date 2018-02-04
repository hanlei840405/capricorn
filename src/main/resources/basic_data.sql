/*
Navicat MySQL Data Transfer

Source Server         : 192.168.99.100
Source Server Version : 50721
Source Host           : 192.168.99.100:32768
Source Database       : basic_data

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-02-04 23:37:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for basic_area
-- ----------------------------
DROP TABLE IF EXISTS `basic_area`;
CREATE TABLE `basic_area` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `acreage` decimal(10,2) DEFAULT NULL,
  `height` decimal(10,2) DEFAULT NULL,
  `floor_code` varchar(255) DEFAULT NULL,
  `user_code` varchar(255) DEFAULT NULL COMMENT '管家',
  `remark` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `version` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of basic_area
-- ----------------------------
INSERT INTO `basic_area` VALUES ('1', '00000001', '爱的色放', '1231.00', '2.00', '00000001', '00000001', null, '启用', null, null, null, null, '0');

-- ----------------------------
-- Table structure for basic_building
-- ----------------------------
DROP TABLE IF EXISTS `basic_building`;
CREATE TABLE `basic_building` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `location_code` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `acreage` decimal(10,2) DEFAULT NULL,
  `height` decimal(10,2) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `version` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of basic_building
-- ----------------------------
INSERT INTO `basic_building` VALUES ('1', '00000001', '啊士大夫但是', '00000001', '阿凡达但是', '232.00', '11.00', null, '启用', null, null, null, null, '0');

-- ----------------------------
-- Table structure for basic_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `basic_dictionary`;
CREATE TABLE `basic_dictionary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `version` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of basic_dictionary
-- ----------------------------

-- ----------------------------
-- Table structure for basic_dictionary_item
-- ----------------------------
DROP TABLE IF EXISTS `basic_dictionary_item`;
CREATE TABLE `basic_dictionary_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `dictionary_code` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `version` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of basic_dictionary_item
-- ----------------------------

-- ----------------------------
-- Table structure for basic_floor
-- ----------------------------
DROP TABLE IF EXISTS `basic_floor`;
CREATE TABLE `basic_floor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `acreage` decimal(10,2) DEFAULT NULL,
  `height` decimal(10,2) DEFAULT NULL,
  `building_code` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `version` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of basic_floor
-- ----------------------------
INSERT INTO `basic_floor` VALUES ('1', '00000001', '啊士大夫大师傅', '213.00', '111.00', '00000001', null, '启用', null, null, null, null, '0');

-- ----------------------------
-- Table structure for basic_house
-- ----------------------------
DROP TABLE IF EXISTS `basic_house`;
CREATE TABLE `basic_house` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `acreage` decimal(10,2) DEFAULT NULL,
  `height` decimal(10,2) DEFAULT NULL,
  `public_area` decimal(10,2) DEFAULT NULL,
  `area_code` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `version` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of basic_house
-- ----------------------------
INSERT INTO `basic_house` VALUES ('1', '00000003', '士大夫', '121212.00', '2.00', '12.00', '00000001', null, '启用', null, null, null, null, '0');

-- ----------------------------
-- Table structure for basic_owner
-- ----------------------------
DROP TABLE IF EXISTS `basic_owner`;
CREATE TABLE `basic_owner` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `id_number` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `head_img` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `version` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of basic_owner
-- ----------------------------

-- ----------------------------
-- Table structure for basic_owner_house
-- ----------------------------
DROP TABLE IF EXISTS `basic_owner_house`;
CREATE TABLE `basic_owner_house` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `owner_code` varchar(255) NOT NULL,
  `house_property_code` varchar(255) NOT NULL,
  `forever` varchar(255) DEFAULT NULL,
  `service_from` date DEFAULT NULL,
  `service_to` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `version` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`owner_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of basic_owner_house
-- ----------------------------
