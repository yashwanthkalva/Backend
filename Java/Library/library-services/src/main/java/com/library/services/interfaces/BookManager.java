package com.library.services.interfaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.library.common.ApplicationException;
import com.library.common.LibraryUtility;
import com.library.core.DatabaseManager;
import com.library.model.Book;

public class BookManager implements IBookManager {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Book addBook(Book book) throws ApplicationException {
		logger.debug("Adding book");
		long bookId = LibraryUtility.getUniqueId();
		book.setId(bookId);
	    DatabaseManager.getBookStore().put(bookId, book);
	    return DatabaseManager.getBookStore().get(bookId);

	}

	@Override
	public Book getBook(long bookId) throws ApplicationException {
		logger.debug("Fetching a book");
		Book book = DatabaseManager.getBookStore().get(bookId);
		if (book == null) {
			throw new ApplicationException("Unable to fetch book details");
		} else {
			return book;
		}
	}

	@Override
	public void issueBook(String username, long bookId) throws ApplicationException {
		logger.debug("Issuing a book");
		List<Long> issuedBooksIds = DatabaseManager.getUserBookMap().get(username);
		if (issuedBooksIds == null) {
			issuedBooksIds = new ArrayList<Long>();
		}
		issuedBooksIds.add(bookId);
		if (DatabaseManager.getUserBookMap() == null) {
			throw new ApplicationException("Unable to fetch required user and books details");
		} else {
			DatabaseManager.getUserBookMap().put(username, issuedBooksIds);
			Book book = DatabaseManager.getBookStore().get(bookId);
			book.setIssued(true);
		}
	}

	@Override
	public void returnBook(String username, long bookId) throws ApplicationException {
		logger.debug("Returning a book");
		List<Long> issuedBooksIds = DatabaseManager.getUserBookMap().get(username);
		if (issuedBooksIds == null) {
			issuedBooksIds = new ArrayList<Long>();
		}

		if (DatabaseManager.getUserBookMap() == null) {
			throw new ApplicationException("Unable to fetch required user and books details");
		} else {
			issuedBooksIds.remove(bookId);
			DatabaseManager.getUserBookMap().put(username, issuedBooksIds);
			Book book = DatabaseManager.getBookStore().get(bookId);
			book.setIssued(false);

		}
	}

	@Override
	public Collection<Book> issuedBooks() throws ApplicationException {
		logger.debug("Fetching list of Issued books");
		Collection<Book> issuedBooks = (DatabaseManager.getBookStore().values());
		issuedBooks = ((Collection<Book>) issuedBooks).stream() // Convert to steam
				.filter(x -> x.isIssued() == true).collect(Collectors.toList()); // we want "issued" books only

		if (issuedBooks == null) {
			throw new ApplicationException(" Unable to fetch details");
		} else if (issuedBooks.size() == 0) {
			throw new ApplicationException("No books issued / Unable to fetch details");
		} else {
			return issuedBooks;
		}
	}

}
