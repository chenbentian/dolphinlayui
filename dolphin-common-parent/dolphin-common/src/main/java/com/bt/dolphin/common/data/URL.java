package com.bt.dolphin.common.data;

import com.bt.dolphin.common.util.HttpServletUtil;

/**
 * 封装URL地址，自动添加应用上下文路径
 * @author 小懒虫
 * @date 2018/10/15
 */
public class URL {
    private String url;
    public URL(){ }

    /**
     * 封装URL地址，自动添加应用上下文路径
     * @param url URL地址
     */
    public URL(String url){
        this.url = HttpServletUtil.getRequest().getContextPath() + url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
