package com.library.services.interfaces;

import java.util.Collection;
import java.util.List;

import com.library.common.ApplicationException;
import com.library.model.Book;

public interface IBookManager {

	public Book addBook(Book book) throws ApplicationException;

	public Book getBook(long keyword) throws ApplicationException;

	public void issueBook(String username, long bookId) throws ApplicationException;

	public void returnBook(String username,long bookId) throws ApplicationException;

	public Collection<Book> issuedBooks() throws ApplicationException;

}
