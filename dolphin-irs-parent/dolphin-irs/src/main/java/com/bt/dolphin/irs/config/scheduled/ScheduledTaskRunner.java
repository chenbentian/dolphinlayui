package com.bt.dolphin.irs.config.scheduled;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.bt.dolphin.irs.scheduled.dao.ScheduledTaskDao;
import com.bt.dolphin.irs.scheduled.service.ScheduledTaskService;
import com.bt.dolphin.irs.scheduled.vo.ScheduledTaskBean;

/**
 * @see @Order注解的执行优先级是按value值从小到大顺序。
 * 项目启动完毕后开启需要自启的任务
 */
@Component
@Order(value = 1)
public class ScheduledTaskRunner implements ApplicationRunner {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTaskRunner.class);



    @Autowired
    private ScheduledTaskService scheduledTaskServices;
    @Autowired
    private ScheduledTaskDao ScheduledTaskDao;

    /**
     * 程序启动完毕后,需要自启的任务
     */
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        LOGGER.info(" >>>>>> 项目启动完毕, 开启 => 需要自启的任务 开始!");
        List<ScheduledTaskBean> scheduledTaskBeanList = ScheduledTaskDao.getAllNeedStartTask();
        scheduledTaskServices.initAllTask(scheduledTaskBeanList);
        LOGGER.info(" >>>>>> 项目启动完毕, 开启 => 需要自启的任务 结束！");
    }
}
