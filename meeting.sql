CREATE DATABASE meeting;

USE meeting;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for discipline
-- ----------------------------
DROP TABLE IF EXISTS `discipline`;
CREATE TABLE `discipline`  (
  `id_` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `createtime` varchar(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `content_` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `punishment_` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `remark_` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `userid_` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of discipline
-- ----------------------------
INSERT INTO `discipline` VALUES ('402881e686e4a6f30186e4a952090002', '2023-03-15', '毁坏物品', '口头警告', '7856', '402881e686c5ccdf0186c5d085150001');
INSERT INTO `discipline` VALUES ('402881e686e4d25a0186e4d3bd250000', '2023-03-15', '其他', '拉黑处理', 'LOP', '402881e686c5ccdf0186c5d085150001');
INSERT INTO `discipline` VALUES ('4028ab6086ca59250186ca6135d90002', '2023-03-10', '拖延时间', '口头警告', '356345763', '402881e686c76b0e0186c7746f200000');
INSERT INTO `discipline` VALUES ('4028ab6086e44d730186e44e10ec0000', '2023-03-15', '拖延时间', '口头警告', 'okk', '402881e686c76b0e0186c7746f200000');

-- ----------------------------
-- Table structure for meeting
-- ----------------------------
DROP TABLE IF EXISTS `meeting`;
CREATE TABLE `meeting`  (
  `id_` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `createtime` varchar(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name_` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type_` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of meeting
-- ----------------------------
INSERT INTO `meeting` VALUES ('402881e686c5ccdf0186c5cdc2dd0000', '2023-03-10', 'sancarlos', '小型');
INSERT INTO `meeting` VALUES ('402881e686c7542f0186c75850b30001', '2023-03-10', 'valencia', '大型');
INSERT INTO `meeting` VALUES ('402881e686e4a6f30186e4a7e1cb0001', '2023-03-15', 'prueba', '大型');
INSERT INTO `meeting` VALUES ('4028ab6086ca59250186ca5ec0430000', '2023-03-10', 'test1', '中型');

-- ----------------------------
-- Table structure for schedule
-- ----------------------------
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule`  (
  `id_` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `createtime` varchar(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `enddatetime_` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `meetingid_` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `realitydatetime_` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `remark_` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `startdatetime_` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status_` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `userid_` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of schedule
-- ----------------------------
INSERT INTO `schedule` VALUES ('402881e686c63a510186c700aeee0000', '2023-03-10', '2023-03-11 00:00', '402881e686c5ccdf0186c5cdc2dd0000', NULL, NULL, '2023-03-09 23:32', '审核通过', '1');
INSERT INTO `schedule` VALUES ('402881e686c7542f0186c75737920000', '2023-03-10', '2023-03-13 18:00', '402881e686c5ccdf0186c5cdc2dd0000', NULL, NULL, '2023-03-12 01:06', '审核通过', '402881e686c5ccdf0186c5d085150001');
INSERT INTO `schedule` VALUES ('402881e686c76b0e0186c774e95d0001', '2023-03-10', '2023-03-13 22:00', '402881e686c7542f0186c75850b30001', NULL, NULL, '2023-03-10 01:40', '审核通过', '1');
INSERT INTO `schedule` VALUES ('402881e686c76b0e0186c777889b0002', '2023-03-10', '2023-03-17 00:00', '402881e686c5ccdf0186c5cdc2dd0000', NULL, NULL, '2023-03-15 00:00', '审核通过', '402881e686c76b0e0186c7746f200000');
INSERT INTO `schedule` VALUES ('402881e686c7cd5a0186c7ceb3530000', '2023-03-10', '2023-03-20 00:00', '402881e686c7542f0186c75850b30001', NULL, NULL, '2023-03-17 03:17', '审核通过', '402881e686c5ccdf0186c5d085150001');
INSERT INTO `schedule` VALUES ('402881e686e4d25a0186e4d678800001', '2023-03-15', '2023-03-17 00:00', '402881e686e4a6f30186e4a7e1cb0001', NULL, NULL, '2023-03-15 18:35', '待审', '402881e686c76b0e0186c7746f200000');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id_` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `createtime` varchar(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address_` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `code_` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password_` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone_` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `realname_` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role_` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sex_` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status_` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `username_` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id_`) USING BTREE,
  UNIQUE INDEX `UK_6mum7kywpx2cqnkxlxuabtu`(`username_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '2023-03-10', NULL, '74354', 'RaFutWeh0ww=', NULL, '吴健民', '管理员', NULL, '1', 'sysadmin');
INSERT INTO `users` VALUES ('402881e686c5ccdf0186c5d085150001', '2023-03-09', NULL, '23853', 'RaFutWeh0ww=', '18473472374', '李道南', '员工', NULL, '1', 'lidaonan');
INSERT INTO `users` VALUES ('402881e686c76b0e0186c7746f200000', '2023-03-10', NULL, '12453', 'RaFutWeh0ww=', '18338572838', '马德罗', '员工', NULL, '1', 'madero');

SET FOREIGN_KEY_CHECKS = 1;

/*
-- 添加外键约束到users表的id_字段
ALTER TABLE `schedule`
ADD CONSTRAINT `FK_schedule_userid_users_id_`
  FOREIGN KEY (`userid_`) REFERENCES `users` (`id_`)
  ON DELETE CASCADE ON UPDATE CASCADE;

-- 添加外键约束到meeting表的id_字段
ALTER TABLE `schedule`
ADD CONSTRAINT `FK_schedule_meetingid_meeting_id_`
  FOREIGN KEY (`meetingid_`) REFERENCES `meeting` (`id_`)
  ON DELETE CASCADE ON UPDATE CASCADE;

-- 添加外键约束到users表的id_字段
ALTER TABLE `discipline`
ADD CONSTRAINT `FK_discipline_userid_users_id_`
  FOREIGN KEY (`userid_`) REFERENCES `users` (`id_`)
  ON DELETE CASCADE ON UPDATE CASCADE;*/
  