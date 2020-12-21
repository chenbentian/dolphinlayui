package com.bt.dolphin.thymeleaf.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.bt.dolphin.common.util.EhCacheUtil;
import com.bt.dolphin.common.util.SpringContextUtil;
import com.bt.dolphin.system.param.api.SysParamService;
import com.bt.dolphin.system.param.vo.SysParamVo;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

public class ParamUtil {

    private static Cache paramCache = EhCacheUtil.getParamCache();

    /**
     * 获取系统参数值
     * @param name 系统参数名称
     */
    @SuppressWarnings("unchecked")
    public static String value(String paramCode){
       String value = null;
        Element dictEle = paramCache.get(paramCode);
        if(dictEle != null){
            value = dictEle.getObjectValue().toString();
        }else {
        	SysParamService sysParamService = SpringContextUtil.getBean(SysParamService.class);
        	SysParamVo param = sysParamService.getParamByParamCode(paramCode);
           if (param != null){
               paramCache.put(new Element(param.getParamCode(), param.getDefaultValue()));
               value = param.getDefaultValue();
           }
        }
        return value;
    }

    /**
     * 清除缓存中指定的数据
     * @param label 字典标识
     */
    public static void clearCache(String paramCode){
        Element paramEle = paramCache.get(paramCode);
        if (paramEle != null){
            paramCache.remove(paramCode);
        }
    }


    public static String timeDifference(Date endDate,Date startDate)throws Exception{
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            long l=endDate.getTime()-startDate.getTime();
            long day=l/(24*60*60*1000);
            long hour=(l/(60*60*1000)-day*24);
            long min=((l/(60*1000))-day*24*60-hour*60);
            long s=(l/1000-day*24*60*60-hour*60*60-min*60);
            return min+"〃"+s;
        }catch (Exception e){
            return "-";
        }


    }
}
