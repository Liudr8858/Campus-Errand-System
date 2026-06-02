/*
 Navicat Premium Data Transfer

 Source Server         : MySQL80
 Source Server Type    : MySQL
 Source Server Version : 80045 (8.0.45)
 Source Host           : localhost:3306
 Source Schema         : campus_errand

 Target Server Type    : MySQL
 Target Server Version : 80045 (8.0.45)
 File Encoding         : 65001

 Date: 02/06/2026 15:51:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` bigint NOT NULL COMMENT '订单ID',
  `task_id` bigint NULL DEFAULT NULL COMMENT '任务ID',
  `publisher_id` bigint NULL DEFAULT NULL COMMENT '发布者ID',
  `acceptor_id` bigint NULL DEFAULT NULL COMMENT '接单人ID',
  `status` int NULL DEFAULT NULL COMMENT '订单状态（0进行中 1已完成）',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for review
-- ----------------------------
DROP TABLE IF EXISTS `review`;
CREATE TABLE `review`  (
  `id` bigint NOT NULL COMMENT '评价ID',
  `order_id` bigint NULL DEFAULT NULL COMMENT '订单ID',
  `from_user_id` bigint NULL DEFAULT NULL COMMENT '评价人',
  `to_user_id` bigint NULL DEFAULT NULL COMMENT '被评价人',
  `score` int NULL DEFAULT NULL COMMENT '评分（1-5）',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评价内容',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of review
-- ----------------------------

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task`  (
  `id` bigint NOT NULL,
  `user_id` bigint NULL DEFAULT NULL,
  `accept_user_id` bigint NULL DEFAULT NULL,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES (2001, 1001, 2040426172170948609, '取快递', '帮我去菜鸟驿站取一个快递', 5.00, 2, NULL, '青岛市市南区A路1号', '13800000001');
INSERT INTO `task` VALUES (2002, 1002, 1005, '买奶茶', '帮我买一杯喜茶少糖', 10.00, 2, NULL, '青岛市市北区B路2号', '13800000002');
INSERT INTO `task` VALUES (2003, 1003, 1005, '送文件', '帮我把文件送到教学楼', 8.00, 2, NULL, '青岛市崂山区C路3号', '13800000003');
INSERT INTO `task` VALUES (2004, 1001, 1002, '买早餐', '豆浆+油条', 6.00, 2, NULL, '青岛市市南区D路4号', '13800000001');
INSERT INTO `task` VALUES (2005, 1002, 1003, '取外卖', '美团订单帮忙取一下', 4.00, 2, NULL, '青岛市市北区E路5号', '13800000002');
INSERT INTO `task` VALUES (2006, 1003, 1001, '买药', '感冒药一盒', 12.00, 2, NULL, '青岛市崂山区F路6号', '13800000003');
INSERT INTO `task` VALUES (1775310546046, 2040426172170948609, 1002, '拿快递', '去给我拿快递', 8.00, 2, '2026-04-04 21:49:06', NULL, NULL);
INSERT INTO `task` VALUES (1775397970323, 1001, 2040901396138102785, '拿快递', '学校驿站 取件码是10-30', 80.00, 2, '2026-04-05 22:06:10', '青岛市市南区xx路123号', NULL);
INSERT INTO `task` VALUES (1775398244740, 1005, 1002, '带饭', '去餐厅帮我买一份手抓饼', 20.00, 1, '2026-04-05 22:10:45', '8号宿舍楼', NULL);
INSERT INTO `task` VALUES (1775420438679, 1005, 2040901396138102785, '演示', '演示', 6.00, 2, '2026-04-06 04:20:39', '8号宿舍楼', NULL);
INSERT INTO `task` VALUES (1775425609880, 2040901396138102785, 2040901396138102785, '带饭', '去食堂帮我带一份红烧肉', 25.00, 2, '2026-04-06 05:46:50', '东校区 8号宿舍楼', NULL);
INSERT INTO `task` VALUES (1775427250363, 2040901396138102785, 2040901396138102785, '带饭', '去食堂帮我带一份红烧肉', 25.00, 2, '2026-04-06 06:14:10', '东校区 8号宿舍楼', NULL);
INSERT INTO `task` VALUES (1775427283540, 2040901396138102785, 2048039434882408449, '带饭', '去国交帮我带一份派乐汉堡单人套餐', 30.00, 2, '2026-04-06 06:14:44', '东校区 8号宿舍楼', NULL);
INSERT INTO `task` VALUES (1775427311043, 2040901396138102785, 2040901396138102785, '取外卖', '美团订单帮忙取一下', 8.00, 2, '2026-04-06 06:15:11', '东校区 8号宿舍楼', NULL);
INSERT INTO `task` VALUES (1777125702404, 2048039434882408449, 2040901396138102785, '拿快递', '去帮我拿快递 取件码是20-1', 10.00, 1, '2026-04-25 22:01:42', '东校区8号楼', NULL);
INSERT INTO `task` VALUES (1777127950374, 2040901396138102785, 1001, '拿外卖', '去外卖柜帮我拿外卖', 10.00, 2, '2026-04-25 22:39:10', '东校区 8号楼 ', NULL);
INSERT INTO `task` VALUES (1777128014628, 1001, 1002, '买东西', '去超市买两瓶水送过来', 10.00, 1, '2026-04-25 22:40:15', '青岛市市南区xx路123号', NULL);
INSERT INTO `task` VALUES (1777128246290, 1003, 1005, '买东西', '去超市买两瓶水送过来', 10.00, 2, '2026-04-25 22:44:06', '教学楼三楼办公室', NULL);
INSERT INTO `task` VALUES (1777128411992, 1003, 1005, '买水', '去超市买两瓶水送过来', 10.00, 2, '2026-04-25 22:46:52', '教学楼三楼办公室', NULL);
INSERT INTO `task` VALUES (1777128514244, 2048051358294630402, 1001, '拿快递', '去给我拿快递，取件码是10-1', 10.00, 1, '2026-04-25 22:48:34', '东校区8号楼', NULL);
INSERT INTO `task` VALUES (1777128804624, 1001, 1001, '拿快递', '去快递站帮我取个快递，取件码是10-1', 10.00, 1, '2026-04-25 22:53:25', '青岛市市南区xx路123号', NULL);
INSERT INTO `task` VALUES (1777128899120, 1004, 1005, '带饭', '去国交帮我带份派乐汉堡', 40.00, 2, '2026-04-25 22:54:59', '东校区8号宿舍楼', NULL);
INSERT INTO `task` VALUES (1777129018295, 2048053465563291649, NULL, '送文件', '去教学楼一楼办公室取个文件送过来', 10.00, 0, '2026-04-25 22:56:58', '图书馆', NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL,
  `openid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `credit_score` int NULL DEFAULT 100,
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `phone`(`phone` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1001, NULL, '张三', '13800000001', '123', 100, '2026-03-30 20:29:23', '青岛市市南区xx路123号');
INSERT INTO `user` VALUES (1002, NULL, '李四', '13800000002', '123', 100, '2026-03-30 20:29:23', '行政楼');
INSERT INTO `user` VALUES (1003, NULL, '王五', '13800000003', '123', 100, '2026-03-30 20:29:23', '教学楼三楼办公室');
INSERT INTO `user` VALUES (1004, NULL, '赵六', '13800000004', '123', 100, '2026-03-30 20:29:23', '东校区8号宿舍楼');
INSERT INTO `user` VALUES (1005, NULL, '小明', '13800000005', '123', 100, '2026-03-30 20:29:23', '8号宿舍楼');
INSERT INTO `user` VALUES (1006, NULL, '小红', '13800000006', '123', 100, '2026-03-30 20:29:23', NULL);
INSERT INTO `user` VALUES (1007, NULL, '小刚', '13800000007', '123', 100, '2026-03-30 20:29:23', NULL);
INSERT INTO `user` VALUES (2040426172170948609, NULL, '王宇昊', '13800000008', '123', 100, '2026-04-04 21:48:03', '321');
INSERT INTO `user` VALUES (2048051358294630402, NULL, '小李', '13800000009', '123', 100, '2026-04-25 22:47:49', '东校区8号楼');
INSERT INTO `user` VALUES (2048053465563291649, NULL, '小王', '13800000010', '123', 100, '2026-04-25 22:56:11', '图书馆');

SET FOREIGN_KEY_CHECKS = 1;
