package com.library.web;

import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.library.common.ApplicationConstants;
import com.library.common.ApplicationException;
import com.library.model.Book;
import com.library.services.interfaces.BookManager;
import com.library.services.interfaces.IBookManager;

@Path("/book")
public class BookResource {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addBook(Book book) {
		logger.debug("Adding book" + book.getIsbn() + "," + book.getName());
		IBookManager bookManager = new BookManager();
		try {
			Book oBook=bookManager.addBook(book);
			logger.info("Book added successfully");
			return Response.status(Response.Status.CREATED).entity(oBook).build();
		} catch (ApplicationException e) {
			logger.error(e.getMessage(), e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
		}
	}

	@GET
	@Path("/{bookId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getBook(@PathParam("bookId") long keyword) {
		logger.debug("Fetching book details");
		IBookManager bookManager = new BookManager();
		try {
			Book book = bookManager.getBook(keyword);
			return Response.status(Response.Status.OK).entity(book).build();
		} catch (ApplicationException e) {
			logger.error(e.getMessage(), e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
		}
	}

	@PUT
	@Path("/status")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateBookIssuanceStatus(Book book) {
		logger.debug("issuing a book to user");
		IBookManager bookManager = new BookManager();
		String status = book.getStatus();
		try {
			switch (status) {
			case ApplicationConstants.BOOK_STATUS_ISSUE:
				bookManager.issueBook(book.getIssueTo(), book.getId());
				break;
			case ApplicationConstants.BOOK_STATUS_RETURN:
				bookManager.returnBook(book.getIssueTo(), book.getId());
				break;
			default:
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
			return Response.status(Response.Status.OK).build();
		} catch (ApplicationException e) {
			logger.error(e.getMessage(), e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getIssuedBooks() {
		logger.debug("Returning book back");
		IBookManager bookManager = new BookManager();
		try {
			Collection<Book> issuedBooks = bookManager.issuedBooks();
			return Response.status(Response.Status.OK).entity(issuedBooks).build();
		} catch (ApplicationException e) {
			logger.error(e.getMessage(), e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
		}
	}

}
