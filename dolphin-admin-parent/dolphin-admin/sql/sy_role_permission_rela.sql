CREATE TABLE `sy_role_permission_rela` (
  `ROLE_ID` varchar(40) NOT NULL COMMENT '角色ID',
  `PERMISSION_ID` varchar(40) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`ROLE_ID`,`PERMISSION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与权限对照关系表'
