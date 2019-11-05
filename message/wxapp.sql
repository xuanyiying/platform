SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
DROP DATABASE IF EXISTS `wxapp`;
CREATE DATABASE wxapp;
USE wxapp;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
	`id` bigint(20) not null,
	`username` VARCHAR (64) DEFAULT NULL,
	`phone` VARCHAR (64) DEFAULT NULL,
	`password` VARCHAR (64) DEFAULT NULL,
	`priority` VARCHAR (16),
	`my_invitation_num` varchar (8),
	`avater_url` varchar (64),
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `user_relationship`;
CREATE TABLE `user_relationship` (
	`id` bigint(20) not null,
	`parent_id` VARCHAR (64) DEFAULT NULL,
	`child_id` VARCHAR (64) DEFAULT NULL,
	`invited_num` varchar (8),
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` bigint(20) NOT NULL,
  `num` bigint(20) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `address` varchar(10) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `status` varchar(8) DEFAULT NULL,
  `owner` varchar(64) DEFAULT NULL,
  `operator` varchar(100) DEFAULT NULL,
  `created_date` timestamp ,
  `courier` varchar (64),
  `tracking` varchar (64)
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(10) DEFAULT NULL,
  `sale` varchar(20) DEFAULT NULL,
  `price` varchar(8) DEFAULT NULL,
  `discount` varchar(64) DEFAULT NULL,
  `image_url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-----------------------------------------------------------------------------------------------------------
-- --------------------------------------------------------------------------------------------------------
-- management
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
	`id` bigint(20) not null,
	`username` VARCHAR (64) DEFAULT NULL,
	`phone` VARCHAR (64) DEFAULT NULL,
	`password` VARCHAR (64) DEFAULT NULL,
	`group_id` int ,
	`salt` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盐'
	`avater_url` varchar (64),
	`create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8;



DROP TABLE IF EXISTS `group`;
CREATE TABLE `group`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `group_name` varchar(32) ,
  `parent_id` int(11) NOT NULL COMMENT '上级部门ID',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modify_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for login_log
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '登录时间',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `login_status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录状态, 0 表示登录失败, 1 表示登录成功.',
  `ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 393 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登录日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单 ID',
  `parent_id` int(11) NOT NULL,
  `menu_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单 URL',
  `perms` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识符',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modify_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `icon` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for operator
-- ----------------------------
DROP TABLE IF EXISTS `operation`;
CREATE TABLE `operation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单 ID',
  `menu_id` int(11) NOT NULL COMMENT '所属菜单 ID',
  `operation_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源名称',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源 URL',
  `permission` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识符',
  `http_method` varchar(7) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源需要的 HTTP 请求方法',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modify_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `modify_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `role_operation`;
CREATE TABLE `role_operation`  (
  `role_id` int(11) NULL DEFAULT NULL,
  `operator_id` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色-操作关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `system_log`;
CREATE TABLE `system_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `operation` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作',
  `method` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `params` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;