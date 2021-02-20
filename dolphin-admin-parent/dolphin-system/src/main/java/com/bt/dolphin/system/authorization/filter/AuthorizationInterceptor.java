
package com.bt.dolphin.system.authorization.filter;

import java.nio.file.AccessDeniedException;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bt.dolphin.shiro.ShiroUtil;
import com.bt.dolphin.system.authorization.api.IAuthorityService;
import com.bt.dolphin.system.user.vo.SysUserVo;

/**
 * 类描述：授权拦截器
 * 
 * @author: cbt-34201
 * @version $Id: Exp$
 *
 *          History: 2020年12月25日 上午11:30:45 cbt-34201 Created.
 * 
 */
public class AuthorizationInterceptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);

	private String unprotectedUrls;
	
	private IAuthorityService authorityService;
	
	private String appName;
	
	private Boolean authorityOpen;

	public String getUnprotectedUrls() {
		return unprotectedUrls;
	}

	public void setUnprotectedUrls(String unprotectedUrls) {
		this.unprotectedUrls = unprotectedUrls;
	}

	public IAuthorityService getAuthorityService() {
		return authorityService;
	}

	public void setAuthorityService(IAuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	public Boolean getAuthorityOpen() {
		return authorityOpen;
	}

	public void setAuthorityOpen(Boolean authorityOpen) {
		this.authorityOpen = authorityOpen;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	/**
	 * 在Controller请求执行之前，
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(authorityOpen != null && !authorityOpen) {
			return true;
		}
		String requestURI = request.getRequestURI();
		if ((requestURI != null) && (!"".equals(requestURI)) && (!"/".equals(requestURI))) {
			int index = requestURI.indexOf("?");
			if (index != -1) {
				requestURI = requestURI.substring(0, index);
			}
			String contextPath = request.getContextPath();
			requestURI = requestURI.substring(contextPath.length());
		}

		HandlerMethod hm;
		try {
			if ((handler instanceof HandlerMethod)) {
				hm = (HandlerMethod) handler;
			} else {
				return true;
			}
		} catch (Exception e) {
			this.logger.error(e.getMessage(), e);
			return true;
		}

		// 不保护路径
		if (unprotectedUrls != null) {
			String[] UnprotectedUrlArrs = unprotectedUrls.split(",");
			for (String s : UnprotectedUrlArrs) {
				if (requestURI.matches(s)) {
					return true;
				}
			}
		}
		String accountName = null;
		SysUserVo user = ShiroUtil.getSubject();
		if (user != null) {
			accountName = user.getUserNo();
		} else {
			throw new Exception("无法获取到Authentication认证信息！");
		}
		// 配置不做授权校验
		PermitAll classPermitAll = (PermitAll) hm.getMethod().getDeclaringClass().getAnnotation(PermitAll.class);
		if (classPermitAll != null) {
			return true;
		}

		PermitAll methodPermitAll = (PermitAll) hm.getMethodAnnotation(PermitAll.class);
		if (methodPermitAll != null) {
			return true;
		}
		/*RequestMapping methodMap = (RequestMapping)hm.getMethodAnnotation(RequestMapping.class);
	    RequestMapping classMap = (RequestMapping)hm.getMethod().getDeclaringClass().getAnnotation(RequestMapping.class);
	    String path = "";
	    if ((classMap != null) && (!"".equals(classMap)) && 
	      (!"".equals(classMap.value()[0])) && (classMap.value().length > 0)) {
	      path = classMap.value()[0];
	      auditOperationLog.setCompName(classMap.value()[0]);
	    }

	    if ((methodMap != null) && (!"".equals(methodMap)) && 
	      (!"".equals(methodMap)) && (methodMap.value().length > 0)) {
	      path = path + methodMap.value()[0];
	      auditOperationLog.setMethodName(methodMap.value()[0]);
	    }*/
		    
		boolean flag = false;
		boolean isAdmin = accountName == "sysadmin" ? true : false;
		if (isAdmin) {
			flag = true;
		} else {
			 flag = this.authorityService.hasPrivByUrl(accountName, requestURI, this.appName);
		}
		if (!flag) {
			return dealRequest(request, response);
		}
		return true;
	}

	private boolean dealRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!response.isCommitted()) {
			if (((request.getHeader("X-Requested-With") != null)
					&& (request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))
					|| ((request.getHeader("accept") != null)
							&& (request.getHeader("accept").indexOf("application/json") > -1))) {
				response.setStatus(403);
				response.setContentType(request.getContentType());
				try {
					response.getWriter().write("用户没有权限，请联系管理员进行授权。");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				response.setStatus(403);
				throw new AccessDeniedException("Access denied!");
			}
		}
		return false;
	}

	/**
	 * Controller处理之后，渲染完成之前
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * @throws Exception
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 整个请求完成执行完之后
	 *
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @throws Exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}
}
