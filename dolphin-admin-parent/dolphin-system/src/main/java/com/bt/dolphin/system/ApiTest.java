package com.bt.dolphin.system;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bt.dolphin.common.util.CoreSmUtil;

/**
 *  类描述：
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2021年1月27日 下午2:24:30   cbt-34201   Created.
 *           
 */
@Controller
@RequestMapping(value = "/dolphin/rest/ApiTest")
public class ApiTest {
	
	private static final Logger logger = LoggerFactory.getLogger(ApiTest.class);

	
	@RequestMapping(value = "/smJson")
	public  @ResponseBody Object smJson(HttpServletRequest request,HttpServletResponse response){
		ServletInputStream ins = null;
		String rltStr = "";
		int contentLength = request.getContentLength();
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (contentLength > 0) {
				byte[] buffer = new byte[contentLength];
				ins = request.getInputStream();
				IOUtils.readFully(ins, buffer);
				String jsonData = new String(buffer, "UTF-8");
				Map<String, Object> paraMap = new HashMap<>();
				String encryptStr = CoreSmUtil.encryptStr(jsonData);
				result.put("rtnCode", "1");
				result.put("rtnMsg", "加密成功");
				result.put("data", encryptStr);
			}
		} catch (IOException e) {
			logger.error("", e);
			result.put("rtnCode", "0");
			result.put("rtnMsg", "加密失败");
			return result;
		}
		return result;
		
	}
}
