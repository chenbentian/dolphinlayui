CREATE TABLE `sy_user` (
  `USER_ID` varchar(64) NOT NULL COMMENT '账户ID',
  `USER_NO` varchar(64) NOT NULL COMMENT '账户',
  `USER_NAME` varchar(64) NOT NULL COMMENT '账户中文名称',
  `USER_PASSWORD` varchar(256) NOT NULL COMMENT '账户密码',
  `USER_TYPE` varchar(1) NOT NULL COMMENT '账户类型(1长期用户 2临时用户)',
  `USER_STATUS` varchar(1) NOT NULL COMMENT '账户状态 1正常 3删除  4锁定',
  `USER_CREATETIME` datetime DEFAULT NULL COMMENT '账户创建时间',
  `USER_org` varchar(32) DEFAULT NULL COMMENT '账号所属组织,一般是直属部门',
  `direct_org_code` varchar(32) DEFAULT NULL COMMENT '账户直属单位',
  PRIMARY KEY (`ACCOUNT_ID`),
  KEY `ACCNAME_UNIQUE_INDEX` (`USER_NAME`) USING BTREE,
  KEY `idx_account_status` (`USER_STATUS`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户表'