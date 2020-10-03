package com.library.services.interfaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.library.model.Book;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Book> issuedBooks = new ArrayList<Book>();
		Book book1 = new Book();
		book1.setId(1);
		book1.setIssued(true);

		Book book2 = new Book();
		book2.setId(2);
		book2.setIssued(false);

		Book book3 = new Book();
		book3.setId(3);
		book3.setIssued(true);

		issuedBooks.add(book1);
		issuedBooks.add(book2);
		issuedBooks.add(book3);
		//book3.setIssued(false);
		issuedBooks = ((Collection<Book>) issuedBooks).stream() // Convert to steam
				.filter(x -> x.isIssued() == true).collect(Collectors.toList());
		System.out.print(issuedBooks.size());

	}

}
