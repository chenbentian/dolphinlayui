CREATE TABLE `sy_permission_extend_attr` (
  `ATTR_CODE` VARCHAR(32) NOT NULL COMMENT '属性编码',
  `ATTR_NAME` VARCHAR(256) NOT NULL COMMENT '属性名称',
  `ATTR_DEFAULT_VALUE` VARCHAR(256) NOT NULL COMMENT '属性默认值',
  `ATTR_TYPE` VARCHAR(32) DEFAULT NULL COMMENT '属性类型',
  `ATTR_STATUS` VARCHAR(1) NOT NULL COMMENT '属性状态 1可用 0不可用',
  `TENANT_ID` VARCHAR(38) NOT NULL DEFAULT 'LSPT' COMMENT '租户ID',
  PRIMARY KEY (`ATTR_CODE`,`TENANT_ID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='权限属性配置表'

insert into `sy_permission_extend_attr` (`ATTR_CODE`, `ATTR_NAME`, `ATTR_DEFAULT_VALUE`, `ATTR_TYPE`, `ATTR_STATUS`, `TENANT_ID`) values('ABTEST','AB校验','F','业务','1','LSPT');
insert into `sy_permission_extend_attr` (`ATTR_CODE`, `ATTR_NAME`, `ATTR_DEFAULT_VALUE`, `ATTR_TYPE`, `ATTR_STATUS`, `TENANT_ID`) values('AUDIT','审计业务项','F','权限','1','LSPT');
insert into `sy_permission_extend_attr` (`ATTR_CODE`, `ATTR_NAME`, `ATTR_DEFAULT_VALUE`, `ATTR_TYPE`, `ATTR_STATUS`, `TENANT_ID`) values('AUTHORIZED','授权业务项','T','权限','2','LSPT');
insert into `sy_permission_extend_attr` (`ATTR_CODE`, `ATTR_NAME`, `ATTR_DEFAULT_VALUE`, `ATTR_TYPE`, `ATTR_STATUS`, `TENANT_ID`) values('BUSCONTROL','数据业务控制','','权限','0','LSPT');
insert into `sy_permission_extend_attr` (`ATTR_CODE`, `ATTR_NAME`, `ATTR_DEFAULT_VALUE`, `ATTR_TYPE`, `ATTR_STATUS`, `TENANT_ID`) values('BUTTONCONTROL','按钮控制','F','界面','0','LSPT');
insert into `sy_permission_extend_attr` (`ATTR_CODE`, `ATTR_NAME`, `ATTR_DEFAULT_VALUE`, `ATTR_TYPE`, `ATTR_STATUS`, `TENANT_ID`) values('FLOWING','流程业务项','F','界面','1','LSPT');
insert into `sy_permission_extend_attr` (`ATTR_CODE`, `ATTR_NAME`, `ATTR_DEFAULT_VALUE`, `ATTR_TYPE`, `ATTR_STATUS`, `TENANT_ID`) values('LOGGING','启用日志','F','业务','1','LSPT');
insert into `sy_permission_extend_attr` (`ATTR_CODE`, `ATTR_NAME`, `ATTR_DEFAULT_VALUE`, `ATTR_TYPE`, `ATTR_STATUS`, `TENANT_ID`) values('MAXREQNUM','最大并发数','-1','权限','0','LSPT');
insert into `sy_permission_extend_attr` (`ATTR_CODE`, `ATTR_NAME`, `ATTR_DEFAULT_VALUE`, `ATTR_TYPE`, `ATTR_STATUS`, `TENANT_ID`) values('NORMAL','普通业务项','T','权限','1','LSPT');
insert into `sy_permission_extend_attr` (`ATTR_CODE`, `ATTR_NAME`, `ATTR_DEFAULT_VALUE`, `ATTR_TYPE`, `ATTR_STATUS`, `TENANT_ID`) values('PORTAL','Portal注册项','F','权限','1','LSPT');
insert into `sy_permission_extend_attr` (`ATTR_CODE`, `ATTR_NAME`, `ATTR_DEFAULT_VALUE`, `ATTR_TYPE`, `ATTR_STATUS`, `TENANT_ID`) values('REF_PERMISSION','关联权限项','','权限','0','LSPT');
insert into `sy_permission_extend_attr` (`ATTR_CODE`, `ATTR_NAME`, `ATTR_DEFAULT_VALUE`, `ATTR_TYPE`, `ATTR_STATUS`, `TENANT_ID`) values('RETEST','二次校验','F','业务','1','LSPT');
insert into `sy_permission_extend_attr` (`ATTR_CODE`, `ATTR_NAME`, `ATTR_DEFAULT_VALUE`, `ATTR_TYPE`, `ATTR_STATUS`, `TENANT_ID`) values('SAFE','安全业务项','F','权限','1','LSPT');
insert into `sy_permission_extend_attr` (`ATTR_CODE`, `ATTR_NAME`, `ATTR_DEFAULT_VALUE`, `ATTR_TYPE`, `ATTR_STATUS`, `TENANT_ID`) values('SSL','启用SSL','F','业务','1','LSPT');
insert into `sy_permission_extend_attr` (`ATTR_CODE`, `ATTR_NAME`, `ATTR_DEFAULT_VALUE`, `ATTR_TYPE`, `ATTR_STATUS`, `TENANT_ID`) values('SYSTEMIC','系统业务项','F','权限','1','LSPT');
insert into `sy_permission_extend_attr` (`ATTR_CODE`, `ATTR_NAME`, `ATTR_DEFAULT_VALUE`, `ATTR_TYPE`, `ATTR_STATUS`, `TENANT_ID`) values('TABPAGE','界面Tab页','T','界面','0','LSPT');
insert into `sy_permission_extend_attr` (`ATTR_CODE`, `ATTR_NAME`, `ATTR_DEFAULT_VALUE`, `ATTR_TYPE`, `ATTR_STATUS`, `TENANT_ID`) values('UIVISIBLE','未授权控件可见','F','界面','1','LSPT');
insert into `sy_permission_extend_attr` (`ATTR_CODE`, `ATTR_NAME`, `ATTR_DEFAULT_VALUE`, `ATTR_TYPE`, `ATTR_STATUS`, `TENANT_ID`) values('VISIBLE','菜单业务项','T','界面','1','LSPT');
insert into `sy_permission_extend_attr` (`ATTR_CODE`, `ATTR_NAME`, `ATTR_DEFAULT_VALUE`, `ATTR_TYPE`, `ATTR_STATUS`, `TENANT_ID`) values('WEBBUTTON','界面按钮','T','界面','0','LSPT');
