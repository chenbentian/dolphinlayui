package com.bt.dolphin.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StringClass {
	public static String getString(String string) {
		string = string == null ? "" : string;
		return string;
	}

	public static String getString(Object object) {
		if (object == null) {
			return "";
		}
		return String.valueOf(object);
	}

	public static String getTrimString(String string) {
		return getString(string).trim();
	}

	public static String getTrimString(Object object) {
		return getString(object).trim();
	}

	public static String getString(String oldstring, String newstring) {
		oldstring = oldstring == null ? newstring : oldstring;
		return oldstring;
	}

	public static String getString(Object oldObject, String newstring) {
		String theString = oldObject == null ? newstring : String.valueOf(oldObject);

		return theString;
	}

	public static String getString(String oldstring, String comparestring, String newstring) {
		if (comparestring == null) {
			oldstring = oldstring == null ? newstring : oldstring;
			return oldstring;
		}
		oldstring = getString(oldstring);
		if (oldstring.equals(comparestring)) {
			oldstring = newstring;
			return oldstring;
		}

		return oldstring;
	}

	public static String getString(Object oldObject, String comparestring, String newstring) {
		String theString = "";
		if (comparestring == null) {
			theString = oldObject == null ? newstring : String.valueOf(oldObject);

			return theString;
		}
		theString = getString(oldObject);
		if (theString.equals(comparestring)) {
			theString = newstring;
			return theString;
		}

		return theString;
	}

	public static String getConvertString(String string) {
		String convertstring = "";

		if (string == null) {
			return null;
		}
		if (string.equals("")) {
			return "";
		}

		char nowchar = '\000';
		int length = string.length();
		for (int i = 0; i < length; i++) {
			nowchar = string.charAt(length - 1 - i);
			convertstring = convertstring + nowchar;
		}

		return convertstring;
	}

	public static int getInt(String string) {
		string = getString(string);
		string = string == "" ? "0" : string;
		int thestr;
		try {
			thestr = Integer.parseInt(string);
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}
		return thestr;
	}

	public static float getFloat(String string) {
		string = getString(string);
		string = string == "" ? "0" : string;
		float thestr = 0.0F;
		try {
			thestr = Float.parseFloat(string);
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}
		return thestr;
	}

	public static double getDouble(String string) {
		string = getString(string);
		string = string == "" ? "0" : string;
		double thestr = 0.0D;
		try {
			thestr = Double.parseDouble(string);
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}
		return thestr;
	}

	public static long getLong(String string) {
		string = getString(string);
		string = string == "" ? "0" : string;
		long thestr = 0L;
		try {
			thestr = Long.parseLong(string);
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}
		return thestr;
	}

	public static String getEncodingString(String string, String encoding) {
		try {
			string = string == null ? "" : new String(string.getBytes("ISO-8859-1"), encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return string;
	}

	public static String getEncodingString(String string, String oldEncoding, String newEncoding) {
		try {
			string = string == null ? "" : new String(string.getBytes(oldEncoding), newEncoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return string;
	}

	public static String getGBKString(String string) {
		try {
			string = string == null ? "" : new String(string.getBytes("ISO-8859-1"), "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return string;
	}

	public static String getISOString(String string) {
		try {
			string = string == null ? "" : new String(string.getBytes("GBK"), "ISO-8859-1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return string;
	}

	public static String getStringFromUtf8(String string) {
		try {
			string = URLDecoder.decode(string, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return string;
	}

	public static String getUtf8String(String string) {
		try {
			string = URLEncoder.encode(string, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return string;
	}

	public static String getHTMLFormatString(String string) {
		string = string == null ? "" : string;
		String replacement1 = "<br>";
		String replacement2 = "&nbsp;";
		String replacement3 = "&lt;";
		String replacement4 = "&gt;";
		String replacement5 = "&prime;";
		String replacement6 = "&Prime;";

		string = string.replaceAll(" ", replacement2);
		string = string.replaceAll("<", replacement3);
		string = string.replaceAll(">", replacement4);
		string = string.replaceAll("'", replacement5);
		string = string.replaceAll("\"", replacement6);
		string = string.replaceAll("\r\n", replacement1);
		return string;
	}

	public static String getFilterHtmlString(String string) {
		string = string == null ? "" : string;

		string = string.replaceAll("<", "&lt;");
		string = string.replaceAll(">", "&gt;");

		return string;
	}

	public static String getDatabaseFormatString(String string) {
		if (string == null) {
			return null;
		}
		String replacement1 = "\"";
		string = string.replaceAll("'", replacement1);
		return string;
	}

	public static ArrayList getInterString(String begin, String end, String longstring) {
		longstring = longstring == null ? "" : longstring;
		int beginnum = 0;
		int endnum = 0;
		int strlength = 0;
		ArrayList stringlist = new ArrayList();
		String str = "";
		String temp = "";
		String beginstr = "";
		String endstr = "";

		for (strlength = 0; strlength < longstring.length(); strlength++) {
			temp = longstring.substring(strlength, strlength + 1);
			if ((temp.equals(begin)) && (!beginstr.equals(begin))) {
				beginnum = strlength;
				beginstr = begin;
			} else if ((temp.equals(end)) && (beginstr.equals(begin))) {
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

	public static String insert(String oldstring, String insertstring, int index) {
		String newstring = "";
		if (oldstring.length() <= index) {
			newstring = oldstring + insertstring;
		} else {
			String startstring = oldstring.substring(0, index);
			String endstring = oldstring.substring(index);
			newstring = startstring + insertstring + endstring;
		}
		return newstring;
	}

	public static String remove(String longString, String begin, String end) {
		ArrayList contentList = getInterString(begin, end, longString);

		if ((contentList != null) && (contentList.size() > 0)) {
			int length = contentList.size();
			for (int i = 0; i < length; i++) {
				try {
					String nowcontent = (String) contentList.get(i);
					longString = getReplaceString(longString, begin + nowcontent + end, "");
				} catch (Exception localException) {
				}
			}
		}
		return longString;
	}

	public static String remove(String oldstring, int startindex, int length) {
		String newstring = "";
		if (oldstring.length() <= startindex) {
			return oldstring;
		}
		String startstring = oldstring.substring(0, startindex);
		String endstring = "";
		if (oldstring.length() - startindex <= length) {
			endstring = "";
		} else {
			endstring = oldstring.substring(startindex + length + 1);
		}
		newstring = startstring + endstring;

		return newstring;
	}

	public static ArrayList getInterString(String shortstring, String longstring) {
		shortstring = getString(shortstring, "");
		longstring = getString(longstring, "");
		boolean flag = false;

		if (longstring.equals("")) {
			return null;
		}

		String string = null;

		ArrayList arraylist = new ArrayList();

		String tempstring = "";
		for (int j = 0; j < longstring.length(); j++) {
			tempstring = tempstring + String.valueOf(longstring.charAt(j));
			if (!tempstring.equals(shortstring)) {
				if (!shortstring.startsWith(tempstring)) {
					string = getString(string, "");
					if (flag)
						string = string + tempstring;
					else {
						string = string + String.valueOf(longstring.charAt(j));
					}
					flag = false;
					tempstring = "";
				} else {
					flag = true;
				}
			} else if (tempstring.equals(shortstring)) {
				if (string != null) {
					arraylist.add(string);
				}
				string = null;
				tempstring = "";
			}
		}
		if (string != null) {
			arraylist.add(string);
		}
		string = null;
		return arraylist;
	}

	public static List getSpiltStringList(String longString, String splitString) {
		List list = new ArrayList();
		longString = getString(longString, "");
		splitString = getString(splitString, "");
		if (longString.equals("")) {
			return null;
		}
		String[] splitArray = longString.split(splitString);
		if (splitArray == null) {
			return null;
		}
		int length = splitArray.length;
		for (int i = 0; i < length; i++) {
			list.add(splitArray[i]);
		}
		return list;
	}

	public static String getLastString(String longstring, String shortstring) {
		int i = longstring.indexOf(shortstring);
		if (i > -1) {
			return longstring.substring(longstring.lastIndexOf(shortstring) + 1, longstring.length());
		}
		return "";
	}

	public static String getPreString(String longstring, String shortstring) {
		int i = 0;
		try {
			i = longstring.indexOf(shortstring);
		} catch (Exception e) {
			return longstring;
		}
		if (i > -1) {
			String newstring = "";
			try {
				newstring = longstring.substring(0, longstring.lastIndexOf(shortstring));
			} catch (Exception e) {
				return longstring;
			}
			return newstring;
		}
		return longstring;
	}

	public static String getFirstInterString(String longstring, String shortString) {
		ArrayList arraylist = getInterString(shortString, longstring);
		String string = null;
		if ((arraylist != null) && (!arraylist.isEmpty())) {
			string = (String) arraylist.get(0);
		}
		return string;
	}

	public static String getLastInterString(String longstring, String shortString) {
		String firstString = getFirstInterString(longstring, shortString) + shortString;

		longstring = longstring.replaceFirst(firstString, "");
		return longstring;
	}

	public static String getFirstString(String longstring, int length) {
		String shortstring = "";
		shortstring = longstring.substring(0, length);
		return shortstring;
	}

	public static String getLastString(String longstring, int length) {
		String shortstring = "";
		shortstring = longstring.substring(longstring.length() - length);
		return shortstring;
	}

	public static String getReplaceString(String longstring, String oldstring, String newstring) {
		longstring = longstring.replaceAll(oldstring, newstring);
		return longstring;
	}


	public static String getSpecialReplaceString(String longstring, String oldstring, String newstring) {
		String string = "";
		while (longstring.length() > 0) {
			int index = longstring.indexOf(oldstring);
			if (index > -1) {
				String tempstring = longstring.substring(0, index);
				string = string + tempstring;
				string = string + newstring;
				if (longstring.length() > oldstring.length()) {
					longstring = longstring.substring(index + oldstring.length());
				} else {
					string = string + longstring;
					longstring = "";
				}
			} else {
				string = string + longstring;
				longstring = "";
				break;
			}
		}
		return string;
	}

	public static String getFormatPath(String path) {
		path = getTrimString(path);
		if (path.equals("")) {
			return path;
		}

		path = path.replaceAll("\\\\", "/");
		path = path.replaceAll("/+", "/");

		return path;
	}

	public static String getFormatpathNoFileStart(String filename1) {
		filename1 = getFormatPath(filename1);
		if (filename1.equals("")) {
			return "";
		}
		if (filename1.startsWith("file:/")) {
			filename1 = filename1.replaceFirst("file:/", "");
		}
		return filename1;
	}

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

	public static ArrayList getWordList(String string) {
		StringTokenizer tokenizer = new StringTokenizer(string);
		ArrayList list = new ArrayList();

		while (tokenizer.hasMoreTokens()) {
			String tempString = tokenizer.nextToken();
			list.add(tempString);
		}
		return list;
	}

	public static int indexOf(String longString, String shortString) {
		ArrayList longStringList = getWordList(longString);
		StringBuffer longWordString = new StringBuffer();
		if ((longStringList != null) && (longStringList.size() > 0)) {
			int length = longStringList.size();
			for (int i = 0; i < length; i++) {
				longWordString.append((String) longStringList.get(i) + " ");
			}
		}
		ArrayList shortStringList = getWordList(shortString);
		StringBuffer shortWordString = new StringBuffer();
		if ((shortStringList != null) && (shortStringList.size() > 0)) {
			int length = shortStringList.size();
			for (int i = 0; i < length; i++) {
				shortWordString.append((String) shortStringList.get(i) + " ");
			}
		}
		String tempLongString = longWordString.toString().trim();
		String tempShortString = shortWordString.toString().trim();
		int indexOf = tempLongString.indexOf(tempShortString);
		return indexOf;
	}

	public static int indexOf(String longString, int startindex, String shortString) {
		if (startindex + 1 > longString.length()) {
			return -1;
		}

		if (shortString.length() > longString.length() - (startindex + 1)) {
			return -1;
		}
		String tempstring = longString.substring(startindex, shortString.length());
		if (tempstring.equals(shortString)) {
			return startindex;
		}
		return -1;
	}

	public static List getFilterArrayString(String longString, String filter, String replaceString) {
		ArrayList resultStrings = new ArrayList();
		String[] strings = longString.split(filter);
		for (int i = 0; i < strings.length; i++) {
			if (!strings[i].equals(replaceString)) {
				resultStrings.add(strings[i]);
			}
		}
		return resultStrings;
	}

}