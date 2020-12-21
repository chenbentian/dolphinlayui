/**
 *
 * @(#) QueryCondition.java
 * @Package com.bt.dolphin.common.vo
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.common.vo;

/**
 * 类描述：
 * 
 * @author: cbt-34201
 * @version $Id: Exp$
 *
 *          History: 2020年9月11日 下午4:55:45 cbt-34201 Created.
 * 
 */
public class QueryCondition {
	private int page;
	private int limit;
	private int rows;
	private int pageBegin;
	private int pageEnd;
	private int begin = 0;
	private int end = 10;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPageBegin() {
		return pageBegin;
	}
	public void setPageBegin(int pageBegin) {
		this.pageBegin = pageBegin;
	}
	public int getPageEnd() {
		return pageEnd;
	}
	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}
	public int getBegin() {
		return begin;
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	
	
}
