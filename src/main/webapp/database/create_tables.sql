SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- 用户信息表 sys_user
-- ----------------------------
-- DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` char(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '登录用户id',
  `user_name` char(100) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '用户名称',
  `user_password` char(200) COLLATE utf8_bin DEFAULT '' COMMENT '用户密码',
  `phone_num` char(20) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '电话号码',
  `picture` char(200) COLLATE utf8_bin DEFAULT '' COMMENT '用户头像图片',
  `pic_width` smallint(5) DEFAULT '0' COMMENT '用户头像高度',
  `pic_height` smallint(5) DEFAULT '0' COMMENT '用户头像宽度',
  `user_sex` tinyint(1) DEFAULT '9' COMMENT '性别  1：男  0 ：女  9：未说明性别',
  `birth_date` date DEFAULT NULL COMMENT '生日',
  `tp_type` tinyint(1) DEFAULT '0' COMMENT '平台接入类型。0:自己平台，1:微信平台，2:QQ，3：新浪微博',
  `tp_user_id` char(50) COLLATE utf8_bin DEFAULT '' COMMENT '第三方平台用户id',
  `user_address` char(255) COLLATE utf8_bin DEFAULT '' COMMENT '用户地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_phone_index` (`phone_num`) USING BTREE,
  UNIQUE KEY `user_name_index` (`user_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
