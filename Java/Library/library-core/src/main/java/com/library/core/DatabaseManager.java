package com.library.core;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.library.model.Book;
import com.library.model.Librarian;

public class DatabaseManager {


	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public static Map<String, Librarian> userStore = new ConcurrentHashMap<String, Librarian>();

	public static Map<Long, Book> bookStore = new ConcurrentHashMap<Long, Book>();

	public static Map<String, List<Long>> userBookMap = new ConcurrentHashMap<String, List<Long>>();

	public DatabaseManager() {
		logger.info("Connecting to DB");
		// TODO Create connection pool to DB
		// Use appropriate DB Client to perform DB operations
		
	}

	public static Map<String, Librarian> getUserStore() {
		return userStore;
	}

	public static void setUserStore(Map<String, Librarian> userStore) {
		DatabaseManager.userStore = userStore;
	}

	public static Map<Long, Book> getBookStore() {
		return bookStore;
	}

	public static void setBookStore(Map<Long, Book> bookStore) {
		DatabaseManager.bookStore = bookStore;
	}

	public static Map<String, List<Long>> getUserBookMap() {
		return userBookMap;
	}

	public static void setUserBookMap(Map<String, List<Long>> userBookMap) {
		DatabaseManager.userBookMap = userBookMap;
	}

}
