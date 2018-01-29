/*
Navicat MySQL Data Transfer

Source Server         : 192.168.99.100
Source Server Version : 50721
Source Host           : 192.168.99.100:32768
Source Database       : basic_data

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-01-30 00:01:47
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for basic_building
-- ----------------------------
DROP TABLE IF EXISTS `basic_building`;
CREATE TABLE `basic_building` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for basic_house_property
-- ----------------------------
DROP TABLE IF EXISTS `basic_house_property`;
CREATE TABLE `basic_house_property` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `position` varchar(255) DEFAULT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
-- Table structure for basic_owner_house_property
-- ----------------------------
DROP TABLE IF EXISTS `basic_owner_house_property`;
CREATE TABLE `basic_owner_house_property` (
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
