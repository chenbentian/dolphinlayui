/**
 *
 * @(#) DeskController.java
 * @Package com.bt.dolphin.desk.login.controller
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.desk.login.controller;

import java.io.IOException;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bt.dolphin.shiro.ShiroUtil;
import com.bt.dolphin.system.menu.api.SysMenuService;
import com.bt.dolphin.system.menu.vo.SysMenuVo;
import com.bt.dolphin.system.user.vo.SysUserVo;

/**
 *  类描述：桌面框架
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年8月31日 下午4:21:19   cbt-34201   Created.
 *           
 */
@PermitAll
@Controller
@RequestMapping(value="/dolpin/desk")
public class DeskController {
	 @Autowired
	 private SysMenuService sysMenuService;
	 
	private static String MAIN = "/dolphin/desk/login/main";

	/**
	 * 
	 * 方法说明：桌面框架
	 *
	 * Author：        cbt               
	 * Create Date：   2020年8月31日 下午4:22:48
	 * History:  2020年8月31日 下午4:22:48   cbt-34201   Created.
	 *
	 * @param model
	 * @return
	 *
	 */
    @GetMapping("/main")
   // @RequiresPermissions("system:main:index")
    public String main(Model model){
        // 获取当前登录的用户
        SysUserVo user = ShiroUtil.getSubject();
        // 封装菜单树形数据
        Map<String, SysMenuVo> treeMenu = sysMenuService.getObjMenuByAccount(user.getUserNo(),null,null);
        model.addAttribute("user", user);
        model.addAttribute("treeMenu", treeMenu);
        return MAIN;
    }
    

    /**
     * 获取用户头像
     */
    @GetMapping("/picture")
    public void picture(String p, HttpServletResponse response) throws IOException {
        String defaultPath = "/dolpin/image/haitun.gif";
       /* if (!(StringUtils.isEmpty(p) || p.equals(defaultPath))) {
            UploadProjectProperties properties = SpringContextUtil.getBean(UploadProjectProperties.class);
            String fuPath = properties.getFilePath();
            String spPath = properties.getStaticPath().replace("*", "");
            File file = new File(fuPath + p.replace(spPath, ""));
            if (file.exists()) {
                FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
                return;
            }
        }*/
        Resource resource = new ClassPathResource("static" + defaultPath);
        FileCopyUtils.copy(resource.getInputStream(), response.getOutputStream());
    }
    

    /**
     * 退出登录
     */
    @GetMapping("/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:/dolphin/desk/login/login.html";
    }

}
