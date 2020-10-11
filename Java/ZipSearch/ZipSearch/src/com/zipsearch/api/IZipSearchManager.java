package com.zipsearch.api;

import com.zipsearch.exceptions.ZipSearchApplicationException;
import com.zipsearch.model.ZipSearchCriteria;

public interface IZipSearchManager {

	void scanZipFileForWord(String zipFilePath, ZipSearchCriteria criteria) throws ZipSearchApplicationException;

}
