package com.bt.dolphin.irs.scheduled.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bt.dolphin.irs.scheduled.service.ScheduledTaskService;
import com.bt.dolphin.irs.scheduled.vo.ScheduledTaskBean;

/**
 * 
 * @author 
 *
 */
@Controller
@RequestMapping("/scheduled")
public class ScheduledTaskController {
	
	@Autowired
    private ScheduledTaskService scheduledTaskService;
    /**
     * 所有任务列表
     */
    @RequestMapping("/taskList")
    public @ResponseBody List<ScheduledTaskBean> taskList() {
        return scheduledTaskService.taskList();
    }

    /**
     * 根据任务key => 启动任务
     */
    @RequestMapping("/start")
    public @ResponseBody String start(@RequestParam("taskKey") String taskKey) {
        scheduledTaskService.start(taskKey);
        return "start success";
    }

    /**
     * 根据任务key => 停止任务
     */
    @RequestMapping("/stop")
    public @ResponseBody String stop(@RequestParam("taskKey") String taskKey) {
        scheduledTaskService.stop(taskKey);
        return "stop success";
    }

    /**
     * 根据任务key => 重启任务
     */
    @RequestMapping("/restart")
    public @ResponseBody String restart(@RequestParam("taskKey") String taskKey) {
        scheduledTaskService.restart(taskKey);
        return "restart success";
    }

}