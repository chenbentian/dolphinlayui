/**
 *
 * @(#) MysqlDataSourceConfig.java
 * @Package com.bt.dolphin.admin.config.datasource
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.irs.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;


/**
 *  类描述： 多数据源事务管理：https://blog.csdn.net/sinat_36596988/article/details/82149241
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年8月7日 上午11:26:35   cbt-34201   Created.
 *           
 */
@Configuration
@MapperScan(basePackages= {"com.bt.dolphin.irs.*.dao"},sqlSessionTemplateRef ="ondbSqlSessionTemplate")
public class OndbOracleDataSourceConfig {
	
	@Autowired
	OndbOracleProperties ondbOracleProperties;

    @Bean(name = "ondbDataSource")
    @Primary
    public DataSource ondbDataSource(){
    	  DruidDataSource dataSource = new DruidDataSource();
      	dataSource.setDriverClassName(ondbOracleProperties.getDriveClassName()); 
  		dataSource.setUrl(ondbOracleProperties.getUrl());
  		dataSource.setUsername(ondbOracleProperties.getUsername());
  		dataSource.setPassword(ondbOracleProperties.getPassword());
          return dataSource;
    }

    @Bean(name = "ondbTransactionManager")
    public DataSourceTransactionManager bpTransactionManager() {
        return new DataSourceTransactionManager(ondbDataSource());
    }
 
    @Bean(name = "ondbSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("ondbDataSource") DataSource masterDataSource)
        throws Exception {
       final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        org.apache.ibatis.session.Configuration configuration=new org.apache.ibatis.session.Configuration();
        configuration.setUseGeneratedKeys(true);//使用jdbc的getGeneratedKeys获取数据库自增主键值
        configuration.setUseColumnLabel(true);//使用列别名替换列名，如：select user as User
        configuration.setMapUnderscoreToCamelCase(true);//-自动使用驼峰命名属性映射字段，如userId    user_id
        sessionFactory.setConfiguration(configuration);
        return sessionFactory.getObject();
    }
    @Bean(name = "ondbSqlSessionTemplate")
    public SqlSessionTemplate ondbSqlSessionTemplate(
            @Qualifier("ondbSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}