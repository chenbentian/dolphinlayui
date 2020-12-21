/**
 *
 * @(#) CoreStringTool.java
 * @Package com.ls.sss.core.util
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.irs.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  类描述：
 * 
 *  @author:  Liner
 *  @version  $Id: Exp$ 
 *
 *  History:  2018年7月3日 下午3:10:31   Liner   Created.
 *           
 */
public class CoreStringTool {
	private static final Logger logger = LoggerFactory.getLogger(CoreStringTool.class);
	public static String getTrimString(String string){
    	if(string==null){
    		return string;
    	}
    	List<String> list = new ArrayList<String>();
		list.add("%0d");
		list.add("\r");
		list.add("%0a");
		list.add("\n");
    	string = Normalizer.normalize(string, Normalizer.Form.NFKC);
		for(int i = 0 ;i<list.size();i++) {
			string = string.replace(list.get(i), "");
		}
//		return encode;

    	return string.trim();
    }

    /**
     * 处理字符串,将null转为""
     * @param string
     * @return String
     */
    public static String getString(String string) {
    	string = (string == null||string.equals("null")) ? "" : string;
        return getTrimString(string);
    }
    
    public static String getString(Object object){
    	if(object==null){
    		return "";
    	}
    	return getTrimString(String.valueOf(object));
    }

    /**
     * 处理字符串,如果str是null则用str1代替
     * 
     * @param oldstring
     * @param newstring
     * @return String
     */
    public static String getString(String oldstring, String newstring) {
    	oldstring = (oldstring == null) ? newstring : oldstring;
    	
        return getTrimString(oldstring);
    }

    /**
     * 处理字符串,如果str是null则用str1代替
     * 
     * @param oldObject
     * @param newstring
     * @return String
     */
    public static String getString(Object oldObject, String newstring) {
    	String theString = (oldObject == null) ? newstring : String.valueOf(oldObject);
        return getTrimString(theString);
    }
    
    /**
     * 如果oldstring与comparestring相等，则将oldstring用newstring代替
     * @param oldstring
     * @param comparestring
     * @param newstring
     * @return String
     */
    public static String getString(String oldstring,String comparestring,String newstring) {
    	if(comparestring==null){
        	oldstring = (oldstring == null) ? newstring : oldstring;
            return getTrimString(oldstring);
    	}else{
    		oldstring=getString(oldstring);
    		if(oldstring.equals(comparestring)){
    			oldstring=newstring;
    			return getTrimString(oldstring);
    		}
    	}
    	return getTrimString(oldstring);
    }
    
    /**
     * 如果oldstring与comparestring相等，则将oldstring用newstring代替
     * @param oldObject
     * @param comparestring
     * @param newstring
     * @return String
     */
    public static String getString(Object oldObject,String comparestring,String newstring) {
    	String theString="";
    	if(comparestring==null){
    		theString = (oldObject == null) ? newstring : String.valueOf(oldObject);
            return getTrimString(theString);
    	}else{
    		theString=getString(oldObject);
    		if(theString.equals(comparestring)){
    			theString=newstring;
    			return getTrimString(theString);
    		}
    	}
    	return getTrimString(theString);
    }

    /**
     * 取得倒转的String
     * @param string
     * @return String
     */
    public static String getConvertString(String string){
    	String convertstring="";
    	
    	if(string==null){
    		return null;
    	}
    	if(string.equals("")){
    		return "";
    	}
    	
    	char nowchar='\u0000';
    	int length=string.length();
    	for(int i=0;i<length;i++){
    		nowchar=string.charAt((length-1)-i);
    		convertstring+=nowchar;
    	}
    	
    	return convertstring;
    }
    
    /**
     * 处理int类型字符串
     * 
     * @param string
     * @return int
     */
    public static int getInt(Object object) {
    	String string = CoreStringTool.getString(object);
    	string = (string == "") ? "0" : string;
        int thestr;
        try {
            thestr = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
        return thestr;
    }

    /**
     * 处理float类型字符串
     * 
     * @param string
     * @return float
     */
    public static float getFloat(String string) {
    	string = CoreStringTool.getString(string);
    	string = (string == "") ? "0" : string;
        float thestr = 0;
        try {
            thestr = Float.parseFloat(string);
        } catch (NumberFormatException e) {
        	throw new NumberFormatException();
        }
        return thestr;
    }

    /**
     * 处理double类型字符串
     * 
     * @param string
     * @return double
     */
    public static double getDouble(String string) {
    	string = CoreStringTool.getString(string);
    	string = (string == "") ? "0" : string;
        double thestr = 0;
        try {
            thestr = Double.parseDouble(string);
        } catch (NumberFormatException e) {
        	throw new NumberFormatException();
        }
        return thestr;
    }

    /**
     * 处理long类型字符串
     * 
     * @param string
     * @return long
     */
    public static long getLong(String string) {
    	string = CoreStringTool.getString(string);
    	string = (string == "") ? "0" : string;
        long thestr = 0;
        try {
            thestr = Long.parseLong(string);
        } catch (NumberFormatException e) {
        	throw new NumberFormatException();
        }
        return thestr;
    }

    /**
     * 将字符串转为encoding的类型
     * @param string
     * @param encoding
     * @return String
     */
    public static String getEncodingString(String string,String encoding) {
    	try{
    		string = (string == null) ? "" : new String(string.getBytes("ISO-8859-1"), encoding);
    	}catch(UnsupportedEncodingException e){
    		logger.error("", e);
    	}
        return string;
    }
    
    /**
     * 将字符串由oldEncoding转为newEncoding的类型
     * @param string
     * @param oldEncoding
     * @param newEncoding
     * @return String
     */
    public static String getEncodingString(String string,String oldEncoding,String newEncoding) {
    	try{
    		string = (string == null) ? "" : new String(string.getBytes(oldEncoding), newEncoding);
    	}catch(UnsupportedEncodingException e){
    		logger.error("", e);
    	}
        return string;
    }
    
    /**
     * 将字符串转为GBK
     * @param string
     * @return String
     */
    public static String getGBKString(String string){
    	try{
    		string = (string == null) ? "" : new String(string.getBytes("ISO-8859-1"), "GBK");
    	}catch(UnsupportedEncodingException e){
    		logger.error("", e);
    	}
        return string;
    }

    /**
     * 对UTF-8进行中文解码
     * @param string
     * @return String
     * @throws IOException
     */
    public static String getStringFromUtf8(String string){
        try {
        	string=URLDecoder.decode(string,"utf-8");
        } catch (UnsupportedEncodingException e) {
        	logger.error("{}",e);
        }
        return string;
    }
    
    /**
     * 对中文进行UTF-8编码
     * @param string
     * @return String
     */
    public static String getUtf8String(String string){
        try {
        	string=URLEncoder.encode(string, "utf-8");
        } catch (UnsupportedEncodingException e) {
        	logger.error("", e);
        }
        return string;
    }
    
    /**
     * 替换不能写入数据库的字符
     * @param string
     * @return String
     */
    public static String getDatabaseFormatString(String string){
    	if(string==null){
    		return null;
    	}
    	String replacement1="\"";
    	string = string.replaceAll("'", replacement1);
    	return string;
    }
    
    /**
     * 替换不能写入数据库的字符
     * @param string
     * @return String
     */
    public static String getStringToDatabaseFormat(String string){
    	if(string==null){
    		return null;
    	}
    	String replacement1="'";
    	string = string.replaceAll("\"", replacement1);
    	return string;
    }

    /**
     * 截取在字符 begin 到 end 之间的字符串，将这些字符串保存在ArrayList中并返回一个 ArrayList 对象
     * 
     * @param begin 只能是单个字符
     * @param end 只能是单个字符
     * @param longstring
     * @return ArrayList
     */
    public static ArrayList getInterString(String begin, String end,String longstring) {

    	longstring = (longstring == null) ? "" : longstring;
        int beginnum = 0;
        int endnum = 0;
        int strlength = 0;
        ArrayList stringlist = new ArrayList();
        String str = "";
        String temp = "";
        String beginstr = "", endstr = "";

        for (strlength = 0; strlength < longstring.length(); strlength++) {
            temp = longstring.substring(strlength, strlength + 1);
            if ((temp.equals(begin))&&(!beginstr.equals(begin))) {
                beginnum = strlength;
                beginstr = begin;
            }else if ((temp.equals(end))&&(beginstr.equals(begin))) {
                endnum = strlength;
                endstr = end;
            }

            while ((endstr.equals(end)) && (beginstr.equals(begin))) {
                str = longstring.substring(beginnum + 1, endnum);
                stringlist.add(str);
                beginnum = 0;
                endnum = 0;
                beginstr = "";
                endstr = "";
            }
        }

        return stringlist;
    }


    /**
     * 在字符串的固定位置插入新的字符串
     * @param oldstring
     * @param index
     * @param insertstring
     * @return String
     */
    public static String insert(String oldstring,String insertstring,int index){
    	String newstring="";
    	if(oldstring.length()<=index){
    		newstring=oldstring+insertstring;
    	}else{
    		String startstring=oldstring.substring(0,index);
    		String endstring=oldstring.substring(index);
    		newstring=startstring+insertstring+endstring;
    	}
    	return newstring;
    }
    
    /**
     * 删除所有begin和end之间的字符串,包括begin和end
     * @param longString
     * @param begin
     * @param end
     * @return String
     */
    public static String remove(String longString,String begin,String end) {
		ArrayList contentList = CoreStringTool.getInterString(begin,end,longString);
		if((contentList!=null)&&(contentList.size()>0)) {
			int length=contentList.size();
			for(int i=0;i<length;i++) {
				try {
					String nowcontent=(String)contentList.get(i);
					longString=CoreStringTool.getReplaceString(longString,begin+nowcontent+end,"");
				}catch(Exception e) {
					
				}
			}
		}
    	return longString;
    }
    
    /**
     * 把字符串从一个位置开始删除一个固定长度的字符串
     * @param oldstring
     * @param startindex
     * @param length
     * @return String
     */
    public static String remove(String oldstring, int startindex, int length) {
		String newstring = "";
		if (oldstring.length() <= startindex) {
			return oldstring;
		} else {
			String startstring = oldstring.substring(0, startindex);
			String endstring = "";
			if (oldstring.length()-startindex <= length) {
				endstring = "";
				//oldstring.substring(startindex + 1);
			} else {
				endstring = oldstring.substring(startindex + length + 1);
			}
			newstring = startstring + endstring;
		}
		return newstring;
	}
    
    /**
	 * 把字符串thestring截取为按str分割的字符串ArrayList
	 * 
	 * @param shortstring shortString可以是由多个字符组成的字符串
	 * @param longstring
	 * @return ArrayList 如果失败返回null,如果logstring中不包含shortstring则返回整个longstring
	 */
    public static ArrayList getInterString(String shortstring, String longstring) {
    	shortstring = getString(shortstring,"");
        longstring = getString(longstring,"");
        boolean flag=false;
        
        if (longstring.equals("")){
            return null;
        };
        String string = null;

        ArrayList arraylist = new ArrayList();

        String tempstring="";
        for (int j = 0; j < longstring.length(); j++) {
        	tempstring+=String.valueOf(longstring.charAt(j));
            if (!tempstring.equals(shortstring)) {
            	if(!shortstring.startsWith(tempstring)){
                	string=CoreStringTool.getString(string,"");
                    if(flag){
                    	string+=tempstring;
                    }else{
                    	string = string + String.valueOf(longstring.charAt(j));
                    }
                    flag=false;
                    tempstring="";
            	}else{
            		flag=true;
            	}
            } else if (tempstring.equals(shortstring)) {
                if (string!=null) {
                    arraylist.add(string);
                }
                string = null;
                tempstring="";
            }
        }
        if (string != null) {
            arraylist.add(string);
        }
        string = null;
        return (arraylist);
    }
    
    /**
     * 把字符串longString截取为按splitString分割的字符串List
     * @param longString
     * @param splitString
     * @return List
     */
    public static List getSpiltStringList(String longString,String splitString) {
    	List list=new ArrayList();
    	longString = getString(longString,"");
    	splitString = getString(splitString,"");
    	if(longString.equals("")){
    		return null;
    	}
    	String[] splitArray=longString.split(splitString);
    	if(splitArray==null){
    		return null;
    	}
    	int length=splitArray.length;
    	for(int i=0;i<length;i++){
    		list.add(splitArray[i]);
    	}
    	return list;
    }

    /**
     * 取得longstring中从最后一个str开始到longstring结束的字符串
     * 
     * @param longstring
     * @param shortstring
     * @return String  如果longstring不包含shortstring没有则返回""
     */
    public static String getLastString(String longstring, String shortstring) {
        int i = longstring.indexOf(shortstring);
        if (i > -1) {
            return (longstring.substring((longstring.lastIndexOf(shortstring)) + 1,
                    longstring.length()));
        }else {
            return "";
        }
    }

    /**
     * 取得longstring中从开始到最后一个str字符串
     * 
     * @param longstring
     * @param shortstring
     * @return String 如果longstring不包含shortstring则返回longstring
     */
    public static String getPreString(String longstring, String shortstring) {
        int i = 0;
        try{
        	i = longstring.indexOf(shortstring);
        }catch(Exception e){
        	return (longstring);
        }
        if (i > -1) {
        	String newstring="";
        	try {
        		newstring=longstring.substring(0, longstring.lastIndexOf(shortstring));
        	}catch(Exception e) {
        		return longstring;
        	}
        	return newstring;
        }else {
        	return longstring;
        }
    }

    /**
     * 取得longstring中从开始到第一个str字符串
     * 
     * @param longstring
     * @param shortString shortString可以是由多个字符组成的字符串
     * @return String 如果longstring不包含shortstring则返回longstring
     */
    public static String getFirstInterString(String longstring,String shortString){
    	ArrayList arraylist=getInterString(shortString,longstring);
    	String string=null;
    	if((arraylist!=null)&&(!arraylist.isEmpty())){
    		string=(String)arraylist.get(0);
    	}
    	return string;
    }

    /**
     * 取得longstring中从第一个str字符串到结束
     * 
     * @param longstring
     * @param shortString shortString可以是由多个字符组成的字符串
     * @return String 如果longstring不包含shortstring则返回longstring
     */
    public static String getLastInterString(String longstring,String shortString){
    	String firstString=getFirstInterString(longstring,shortString)+shortString;
    	longstring=longstring.replaceFirst(firstString,"");
    	return longstring;
    }
    
    /**
     * 获取字符串前几位的字符
     * @param longstring
     * @param length
     * @return String
     */
    public static String getFirstString(String longstring,int length){
    	String shortstring="";
    	shortstring=longstring.substring(0,length);
    	return shortstring;
    }
    
    /**
     * 获取字符串最后的几位字符
     * @param longstring
     * @param length
     * @return String
     */
    public static String getLastString(String longstring,int length){
    	String shortstring="";
    	shortstring=longstring.substring(longstring.length()-length);
    	return shortstring;
    }
    
    /**
     * 将longstring中的所有oldstring替换成newstring
     * 
     * @param longstring
     * @param oldstring
     * @param newstring
     * @return String
     */
    public static String getReplaceString(String longstring, String oldstring,String newstring) {
        longstring = longstring.replaceAll(oldstring, newstring);
        return (longstring);
    }
    
    /**
     * 用新的字符串替换其中的一段字符
     * @param longstring
     * @param stringindex
     * @param endindex
     * @param shortstring
     * @return String
     */
    public static String getReplaceString(String longstring,int stringindex,int endindex,String shortstring){
    	String newstring="";
    	newstring+=getFirstString(longstring,stringindex);
    	newstring+=shortstring;
    	newstring+=getLastString(longstring,longstring.length()-endindex);
    	return newstring;
    }
    
    /**
     * 将longstring中的所有oldstring替换成newstring
     * 
     * @param longstring
     * @param oldstring
     * @param newstring
     * @return String
     */
    public static String getSpecialReplaceString(String longstring, String oldstring,String newstring) {
    	String string="";
		while(longstring.length()>0){
    	    int index=longstring.indexOf(oldstring);
    	    if(index>-1){
    			String tempstring=longstring.substring(0,index);
    			string+=tempstring;
        		string+=newstring;
        		if(longstring.length()>oldstring.length()){
        			longstring=longstring.substring(index+oldstring.length());
        		}else{
        			string+=longstring;
        			longstring="";
        		}
    		}else{
    			string+=longstring;
    			longstring="";
    		    break;
    	    }
        }
        return (string);
    }
    
    /**
     * 将路径中的"\"全部替换成"/"
     * 
     * @param path
     * @return String
     */
    public static String getFormatPath(String path) {
        path = path.replaceAll("\\\\", "/");
        path = path.replaceAll("//", "/");
        return path;
    }

    /**
     * 判断字符串中是否包含有指定的单词
     * 
     * @param longString
     *            将要被检查的字符串
     * @param shortString
     *            单词
     * @return boolean
     */
    public static boolean isWordExist(String longString, String shortString) {
        StringTokenizer tokenizer = new StringTokenizer(longString);
        while (tokenizer.hasMoreTokens()) {
            String tempString = tokenizer.nextToken();
            if (shortString.equals(tempString)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 返回一个字符串中包含的单词列表
     * 
     * @param string
     * @return ArrayList
     */
    public static ArrayList getWordList(String string) {
        StringTokenizer tokenizer = new StringTokenizer(string);
        ArrayList list = new ArrayList();

        while (tokenizer.hasMoreTokens()) {
            String tempString = tokenizer.nextToken();
            list.add(tempString);
        }
        return list;
    }
    
    /**
     * 前一个句子是否包含后一个句子
     * @param longString
     * @param shortString
     * @return int
     */
    public static int indexOf(String longString,String shortString){
    	ArrayList longStringList=getWordList(longString);
    	StringBuffer longWordString=new StringBuffer();
    	if((longStringList!=null)&&(longStringList.size()>0)){
    		int length=longStringList.size();
    		for(int i=0;i<length;i++) {
    			longWordString.append((String)longStringList.get(i)+" ");
    		}
    	}
    	ArrayList shortStringList=getWordList(shortString);
    	StringBuffer shortWordString=new StringBuffer();
    	if((shortStringList!=null)&&(shortStringList.size()>0)){
    		int length=shortStringList.size();
    		for(int i=0;i<length;i++) {
    			shortWordString.append((String)shortStringList.get(i)+" ");
    		}
    	}
        String tempLongString=longWordString.toString().trim();
        String tempShortString=shortWordString.toString().trim();
        int indexOf=tempLongString.indexOf(tempShortString);
        return indexOf;
    }
    
    /**
     * 从第几位开始等于比较的字符串
     * @param longString
     * @param startindex 从0开始
     * @param shortString
     * @return int
     */
    public static int indexOf(String longString,int startindex,String shortString){
    	if(startindex+1>longString.length()){
    		return -1;
    	}
    	
    	if(shortString.length()>(longString.length()-(startindex+1))){
    		return -1;
    	}
    	String tempstring=longString.substring(startindex,shortString.length());
    	if(tempstring.equals(shortString)){
    		return startindex;
    	}else{
    		return -1;
    	}
    }
    /**
     * 返回字符串转为数组后经过过滤的数组
     * @param longString 
     * @param filter 转换分割符
     * @param replaceString 需要被过滤的字符
     * @return ArrayList
     */
    public static ArrayList getFilterArrayString(String longString,String filter,String replaceString){
    	ArrayList resultStrings=new ArrayList();
    	String[] strings=longString.split(filter);
    	for(int i=0;i<strings.length;i++){
    		if(!strings[i].equals(replaceString)){
    			resultStrings.add(strings[i]);
    		}
    	}
    	return resultStrings;
    }
    /**
     * 
     * 方法说明：字符串拆分:101:1;102:1;104:1;apply:1;colse:1;book:1;result:1;doc:1 
     *
     * Author：        Liner-33526                
     * Create Date：   2017-7-26 下午3:11:05
     * History:  2017-7-26 下午3:11:05   Liner-33526   Created.
     *
     * @param strs
     * @return
     *
     */
    public static Map strToMap(String strs){
    	Map map = new HashMap();
		if(!"".equals(CoreStringTool.getString(strs))){
			String[] strArr = strs.split(";");
			for(String str: strArr){
				String[] strArr0 = str.split(":");
				map.put(strArr0[0], strArr0[1]);
			}
		}
		return map;
	}
    
    /**
  	 * 
  	 * 方法说明：按编码和指定长度 截取字符串
  	 *
  	 * Author：        pzy                
  	 * Create Date：   Jan 11, 2014 12:56:53 PM
  	 * History:  Jan 11, 2014 12:56:53 PM   pzy   Created.
  	 *
  	 * @param str
  	 * @param encoding
  	 * @param pLen
  	 * @return
  	 *
  	 */
  	public static String trimStringForDB(String str, String encoding, int pLen) {
  		str = getString(str);
          if (!str.equals("")) {
              try {   
                  byte[] bytes = str.getBytes(encoding);   
                 
                  if(bytes.length > pLen) {   
                      int tempLen = new String(bytes, 0, pLen, encoding).length();   
                      str = str.substring(0, tempLen);   
                         
                      bytes = str.getBytes(encoding);   
                      if(bytes.length > pLen) {   
                      	str = str.substring(0, tempLen-1); 
                      	trimLastSymbolStr(str, ",");
                      }   
                  }   
              } catch (Exception e) {   
                  logger.error("{}", e);
              }   
          }   
          return str;   
      }
  	/**
  	 * 
  	 * 方法说明：去掉字符串最后某个字符开始后面的字符串
  	 *
  	 * Author：        pengzhiyuan                
  	 * Create Date：   2014-8-28 下午12:42:15
  	 * History:  2014-8-28 下午12:42:15   pengzhiyuan   Created.
  	 *
  	 * @param str
  	 * @param symbol
  	 * @return
  	 *
  	 */
  	public static String trimLastSymbolStr(String str, String symbol) {
  		str = getString(str);
          if (!str.equals("")) {
          	try {
  	        	int lastIndex = str.lastIndexOf(symbol);
  	        	if (lastIndex > 0) {
  	        		str = str.substring(0, lastIndex);
  	        	}
          	} catch (Exception e) {
          		 logger.error("{}", e);
          	}
          }
          return str;
  	}
  	/**
	 * 截取前subSLength个字节
	 * @param str
	 * @param subSLength
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	
	public static String subStr(String str, int subSLength){
		try{
			if (str == null){
				return "";
			}else if(str.getBytes("utf-8").length <4000){
				return str;
			}else{
				String subStr = str;
				
					int tempSubLength = subSLength;//截取字节数   
				    subStr = str.substring(0, str.length()<subSLength ? str.length() : subSLength);//截取的子串     
				    int subStrByetsL = subStr.getBytes("utf-8").length;//截取子串的字节长度    
				    // 说明截取的字符串中包含有汉字     
					while (subStrByetsL > tempSubLength){     
				        int subSLengthTemp = --subSLength;   
				        subStr = str.substring(0, subSLengthTemp>str.length() ? str.length() : subSLengthTemp);     
				        subStrByetsL = subStr.getBytes("utf-8").length;   
				        //subStrByetsL = subStr.getBytes().length;   
					}     
				
			    return subStr;    
			}
		}catch(Exception e){
			 logger.error("{}", e);
		}
		return str;
	}
	/**
	 * 
	 * 方法说明：将另外一个map2里面的一些东西转到一个map1里面去,keys1 对应map1的key值
	 *
	 * Author：        Liner-33526                
	 * Create Date：   2017-8-2 下午9:42:04
	 * History:  2017-8-2 下午9:42:04   Liner-33526   Created.
	 *
	 * @param map1
	 * @param map2
	 * @param keys
	 *
	 */
	public static void mapToMap(Map map1, Map map2, String keys1, String keys2){
		String[] keyArr1 = keys1.split(",");
		if("".equals(keys2)){
			for(String key : keyArr1){
				map1.put(key, map2.get(key));
			}
		}else{
			String[] keyArr2 = keys2.split(",");
			for(int i=0; i < keyArr1.length; i++){
				map1.put(keyArr1[i], map2.get(keyArr2[i]));
			}
		}
	}
	 /**
	  * 短信模板替换变量
	  * @param strSource
	  * @param strFrom
	  * @param strTo
	  * @return
	  */
	 public static String replace(String strSource, String[] strFrom, String[] strTo){
		 if(strSource==null){
			 return "";
		 }
		 if(strFrom==null || strFrom.length==0){
			 return strSource;
		 }
		 if( strTo==null || strTo.length==0){
			 return strSource;
		 }
		 if(strFrom.length!=strTo.length){
			 return strSource;
		 }
		 for(int i=0;i<strFrom.length;i++){
			 if(strTo!=null||strTo.length!=0){
				 strTo[i]=java.util.regex.Matcher.quoteReplacement(strTo[i]);//add by xqi 0008700449 2014-9-10 "$"符号转译
			 }
			 strSource=strSource.replaceAll(strFrom[i], strTo[i]);
		 }
		 return strSource;
	 }
}
