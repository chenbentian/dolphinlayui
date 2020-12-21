/**
 *
 * @(#) LoginController.java
 * @Package com.bt.dolphin.desk.login.controller
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.desk.login.controller;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bt.dolphin.common.data.URL;
import com.bt.dolphin.common.enums.ResultEnum;
import com.bt.dolphin.common.exception.ResultException;
import com.bt.dolphin.common.util.CaptchaUtil;
import com.bt.dolphin.common.util.ResultUtils;
import com.bt.dolphin.common.util.SpringContextUtil;
import com.bt.dolphin.common.vo.ResultVo;
import com.bt.dolphin.desk.config.DeskProperties;
import com.bt.dolphin.shiro.ShiroUtil;
import com.bt.dolphin.shiro.realm.UserPassOpenIdToken;
import com.bt.dolphin.system.role.api.SysRoleService;
import com.bt.dolphin.system.user.vo.SysUserVo;

/**
 *  类描述：登入
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年8月14日 下午2:14:05   cbt-34201   Created.
 *           
 */

@Controller
@RequestMapping(value="/dolpin/desk")
public class LoginController implements ErrorController {
	
	 @Autowired
	 private SysRoleService sysRoleService;
	
	private static String LOGIN = "/dolphin/desk/login/login";
	
	 /**
     * 跳转到登录页面
     */
    @GetMapping("/login")
    public String toLogin(Model model) {
    /*	
        ProjectProperties properties = SpringContextUtil.getBean(ProjectProperties.class);

        Boolean captchaOpen =Boolean.valueOf(ParamUtil.value("captcha_open"));
        if (StringUtils.isEmpty(captchaOpen)){
            captchaOpen = properties.isCaptchaOpen();
        }
        model.addAttribute("isCaptcha",captchaOpen);*/
        return LOGIN;
    }
    

    /**
     * 实现登录
     */
    @PostMapping("/login")
    @ResponseBody
 //   @ActionLog(key = UserAction.USER_LOGIN, action = UserAction.class)
    public ResultVo login(String username, String password, String captcha, String rememberMe) {
        // 判断账号密码是否为空
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new ResultException(ResultEnum.USER_NAME_PWD_NULL);
        }

        // 判断验证码是否正确
        DeskProperties properties = SpringContextUtil.getBean(DeskProperties.class);
     //   Boolean captchaOpen =Boolean.valueOf(ParamUtil.value("captcha_open"));
        Boolean captchaOpen = false;
        if (StringUtils.isEmpty(captchaOpen)){
            captchaOpen = properties.isCaptchaOpen();
        }
        if (captchaOpen) {
            Session session = SecurityUtils.getSubject().getSession();
            String sessionCaptcha = (String) session.getAttribute("captcha");
            if (StringUtils.isEmpty(captcha) || StringUtils.isEmpty(sessionCaptcha)
                    || !captcha.toUpperCase().equals(sessionCaptcha.toUpperCase())) {
                throw new ResultException(ResultEnum.USER_CAPTCHA_ERROR);
            }
            session.removeAttribute("captcha");
        }

        // 1.获取Subject主体对象
        Subject subject = SecurityUtils.getSubject();

        // 2.封装用户数据
        UserPassOpenIdToken token = new UserPassOpenIdToken(username, password,"0");

        // 3.执行登录，进入自定义Realm类中
        try {
            // 判断是否自动登录
            if (rememberMe != null) {
                token.setRememberMe(true);
            } else {
                token.setRememberMe(false);
            }
            subject.login(token);

            // 判断是否拥有后台角色
            SysUserVo user = ShiroUtil.getSubject();
            if (sysRoleService.existUserAdminRole(user.getUserNo())) {
                return ResultUtils.success("登录成功", new URL("/dolpin/desk/main"));
            } else {
                SecurityUtils.getSubject().logout();
                return ResultUtils.error("您不是后台管理员！");
            }
        } catch (LockedAccountException e) {
            return ResultUtils.error("该账号已被冻结");
        } catch (AuthenticationException e) {
            return ResultUtils.error("用户名或密码错误");
        }
    }

    /**
     * 验证码图片
     */
    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置响应头信息，通知浏览器不要缓存
        response.setHeader("Expires", "-1");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "-1");
        response.setContentType("image/jpeg");

        // 获取验证码
        String code = CaptchaUtil.getRandomCode();
        // 将验证码输入到session中，用来验证
        request.getSession().setAttribute("captcha", code);
        // 输出到web页面
        ImageIO.write(CaptchaUtil.genCaptcha(code), "jpg", response.getOutputStream());
    }
    
	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		 return "/error";
	}

}
