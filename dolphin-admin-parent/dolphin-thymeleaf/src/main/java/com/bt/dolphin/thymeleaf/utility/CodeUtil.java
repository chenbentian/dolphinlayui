package com.bt.dolphin.thymeleaf.utility;

import java.util.HashMap;
import java.util.Map;

import com.bt.dolphin.common.util.EhCacheUtil;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

/**
 * 标准代码提取工具对象
 * @author 小懒虫
 * @date 2018/8/14
 */
public class CodeUtil {

    private static Cache codeCache = EhCacheUtil.getCodeCache();

    /**
     * 获取字典值集合
     * @param label 字典标识
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> value(String label){
        Map<String, String> value = new HashMap<String, String>();
       /* Element dictEle = dictCache.get(label);
        if(dictEle != null){
            value = (Map<String, String>) dictEle.getObjectValue();
        }else {
            DictService dictService = SpringContextUtil.getBean(DictService.class);
            Dict dict = dictService.getByNameOk(label);
            if(dict != null){
                String dictValue = dict.getValue();
                String[] outerSplit = dictValue.split(",");
                value = new LinkedHashMap<>();
                for (String osp : outerSplit) {
                    String[] split = osp.split(":");
                    if(split.length > 1){
                        value.put(split[0], split[1]);
                    }
                }
                dictCache.put(new Element(dict.getName(), value));
            }
        }*/
        return value;
    }

    /**
     * 根据选项编码获取选项值
     * @param label 字典标识
     * @param code 选项编码
     */
    public static String keyValue(String label, String code){
        Map<String, String> list = CodeUtil.value(label);
        if(list != null){
            return list.get(code);
        }else{
            return "";
        }
    }

    /**
     * 封装数据状态字典
     * @param status 状态
     */
    public static String dataStatus(Byte status){
        String label = "DATA_STATUS";
        return CodeUtil.keyValue(label, String.valueOf(status));
    }

    /**
     * 清除缓存中指定的数据
     * @param label 字典标识
     */
    public static void clearCache(String label){
        Element dictEle = codeCache.get(label);
        if (dictEle != null){
        	codeCache.remove(label);
        }
    }
}
