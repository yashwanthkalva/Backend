package com.library.common;

public class LibraryUtility {

	public static long bookSequenceNumber = 1;

	public static long getUniqueId() {
		// Can be any UUID generator.
		return bookSequenceNumber++;
	}

}
