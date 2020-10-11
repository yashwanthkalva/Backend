package com.zipsearch.tests;

import com.zipsearch.api.IZipSearchManager;
import com.zipsearch.api.ZipSearchManager;
import com.zipsearch.exceptions.ZipSearchApplicationException;
import com.zipsearch.model.ZipSearchCriteria;

public class ZipSearchTest {

	public static void main(String[] args) {
		// -file foc-slack-export.zip -word un-code -size 40 -first 1
		try {
			IZipSearchManager zipManager = new ZipSearchManager();
			ZipSearchCriteria criteria = new ZipSearchCriteria();
			if (args.length > 0) {
				if (args[0] == null) {
					throw new ZipSearchApplicationException("Insufficient inputs");
				}
				if (args.length >= 2 && args[1] != null && !args[1].endsWith(".zip")) {
					throw new ZipSearchApplicationException("Insufficient inputs");
				}
				// TODO add few more validations for CLI arguments
				criteria.setKeyword(args[3]);
				criteria.setNeighborLimit(Integer.parseInt(args[5]));
				criteria.setResultCount(Integer.parseInt(args[7]));
				zipManager.scanZipFileForWord(args[1], criteria);
			} else {
				throw new ZipSearchApplicationException("Insufficient inputs");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
