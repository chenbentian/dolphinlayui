/**
 *
 * @(#) MysqlDataSourceConfig.java
 * @Package com.bt.dolphin.admin.config.datasource
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 *//*

package com.bt.dolphin.irs.config;

import java.sql.SQLException;

import javax.sql.DataSource;
import javax.transaction.UserTransaction;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;


*//**
 *  类描述： 多数据源事务管理：https://blog.csdn.net/sinat_36596988/article/details/82149241
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年8月7日 上午11:26:35   cbt-34201   Created.
 *           
 *//*
@Configuration
@MapperScan(basePackages= {"com.bt.dolphin.irs.*.dao"},sqlSessionTemplateRef ="ondbSqlSessionTemplate")
public class OndbOracleXaDataSourceConfig {
	
	@Autowired
	OndbOracleProperties ondbOracleProperties;

    @Bean(name = "ondbDataSource")
    @Primary
    public DataSource masterDataSource() throws SQLException {
        DruidXADataSource druidXADataSource = new DruidXADataSource();
        druidXADataSource.setUrl(ondbOracleProperties.getUrl());
        druidXADataSource.setUsername(ondbOracleProperties.getUsername());
        druidXADataSource.setPassword(ondbOracleProperties.getPassword());
 
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setUniqueResourceName("ondbDataSource");
        xaDataSource.setXaDataSource(druidXADataSource);
        xaDataSource.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        xaDataSource.setMaxLifetime(ondbOracleProperties.getMaxLifetime());
        xaDataSource.setMinPoolSize(ondbOracleProperties.getMinPoolSize());
        xaDataSource.setMaxPoolSize(ondbOracleProperties.getMaxPoolSize());
        xaDataSource.setBorrowConnectionTimeout(ondbOracleProperties.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(ondbOracleProperties.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(ondbOracleProperties.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(ondbOracleProperties.getMaxIdleTime());
        xaDataSource.setTestQuery(ondbOracleProperties.getTestQuery());
        return xaDataSource;
    }
 
    *//**
     * 使用这个来做总事务 后面的数据源就不用设置事务了
     *//*
    @Bean(name = "transactionManager")
    @Primary
    public JtaTransactionManager regTransactionManager () {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        UserTransaction userTransaction = new UserTransactionImp();
        return new JtaTransactionManager(userTransaction, userTransactionManager);
    }
 
    @Bean(name = "ondbSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("ondbDataSource") DataSource masterDataSource)
        throws Exception {
       final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        return sessionFactory.getObject();
    }
    @Bean(name = "ondbSqlSessionTemplate")
    public SqlSessionTemplate ondbSqlSessionTemplate(
            @Qualifier("ondbSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}*/