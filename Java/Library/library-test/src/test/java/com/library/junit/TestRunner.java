package com.library.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.library.common.ApplicationException;
import com.library.core.DatabaseManager;
import com.library.model.Book;
import com.library.model.Librarian;
import com.library.services.interfaces.BookManager;
import com.library.services.interfaces.IBookManager;

//@RunWith(JUnitPlatform.class)
class BookManagerTest {
	public static Map<String, Librarian> userStore = new ConcurrentHashMap<String, Librarian>();

	public static Map<Long, Book> bookStore = new ConcurrentHashMap<Long, Book>();

	public static Map<String, List<Long>> userBookMap = new ConcurrentHashMap<String, List<Long>>();
	
	IBookManager bookManager= new BookManager();
	
	@BeforeAll
	static void setup() {
		DatabaseManager.setBookStore(bookStore);
	}
	
	@Test
	void addBook() {
		Book testBook = new Book();
		testBook.setAuthor("Thomas Friedman");
		testBook.setIsbn("234234");
		testBook.setName("World is flat");
		Book output = null;
		try {
			output = bookManager.addBook(testBook);
		} catch (ApplicationException e) {
			e.printStackTrace();
		};
        assertEquals(1, output.getId());
	}
}
