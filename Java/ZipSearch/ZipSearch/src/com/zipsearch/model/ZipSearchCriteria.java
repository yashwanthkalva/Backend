package com.zipsearch.model;

/**
 * @author Yashwanth Kalva
 * 
 */
public class ZipSearchCriteria {

	private String fileName;
	private String keyword;
	private int neighborLimit;
	private int resultCount;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getNeighborLimit() {
		return neighborLimit;
	}

	public void setNeighborLimit(int neighborLimit) {
		this.neighborLimit = neighborLimit;
	}

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
