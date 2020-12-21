/**
 *
 * @(#) MysqlDataSourceConfig.java
 * @Package com.bt.dolphin.admin.config.datasource
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.admin.config.datasource;

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
@MapperScan(basePackages= {"com.bt.dolphin.*.*.dao"},sqlSessionTemplateRef ="dolphinSqlSessionTemplate")
public class MysqlDataSourceConfig {
	
	@Autowired
	MysqlProperties mysqlProperties;

    @Bean(name = "dolphinDataSource")
    @Primary
    public DataSource masterDataSource() {
        DruidXADataSource druidXADataSource = new DruidXADataSource();
        druidXADataSource.setDriverClassName(mysqlProperties.getDriveClassName()); 
        druidXADataSource.setUrl(mysqlProperties.getUrl());
        druidXADataSource.setUsername(mysqlProperties.getUsername());
        druidXADataSource.setPassword(mysqlProperties.getPassword());
 
        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(druidXADataSource);
        atomikosDataSourceBean.setUniqueResourceName("dolphinDataSource");
        atomikosDataSourceBean.setPoolSize(5);
 
        return atomikosDataSourceBean;
    }
 
    /*
    * 使用这个来做总事务 后面的数据源就不用设置事务了
    * */
    @Bean(name = "transactionManager")
    @Primary
    public JtaTransactionManager regTransactionManager () {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        UserTransaction userTransaction = new UserTransactionImp();
        return new JtaTransactionManager(userTransaction, userTransactionManager);
    }
 
    @Bean(name = "dolphinSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("dolphinDataSource") DataSource masterDataSource)
        throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        org.apache.ibatis.session.Configuration configuration=new org.apache.ibatis.session.Configuration();
        configuration.setUseGeneratedKeys(true);//使用jdbc的getGeneratedKeys获取数据库自增主键值
        configuration.setUseColumnLabel(true);//使用列别名替换列名，如：select user as User
        configuration.setMapUnderscoreToCamelCase(true);//-自动使用驼峰命名属性映射字段，如userId    user_id
        sessionFactory.setConfiguration(configuration);
        sessionFactory.setDataSource(masterDataSource);
        return sessionFactory.getObject();
    }

    @Bean(name = "dolphinSqlSessionTemplate")
    public SqlSessionTemplate dolphinSqlSessionTemplate(
            @Qualifier("dolphinSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
