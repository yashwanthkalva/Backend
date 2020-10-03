package com.library.web;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.library.common.ApplicationException;
import com.library.model.Librarian;
import com.library.services.UserManager;
import com.library.services.interfaces.IUserManager;

@Path("/librarian")
public class UserResource {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addLibrarian(Librarian user) {
		IUserManager manager = new UserManager();
		try {

			if (user.getEmailID() == null || user.getUsername() == null || user.getPassword() == null) {
				logger.debug("Missing inputs");
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
			manager.addLibrarian(user);
		} catch (ApplicationException e) {
			logger.error(e.getMessage(), e);
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		logger.info("User added successfully");
		return Response.status(Response.Status.CREATED).build();

	}

	@GET
	@Path("{username}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response retriveLibrarian(@PathParam("username") String username) {
		IUserManager manager = new UserManager();
		try {

			if (username == null) {
				logger.debug("Missing inputs");
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
			Librarian user = manager.retrieveLibrarian(username);
			logger.info("Retrieve librarian information successfully");
			return Response.status(Response.Status.OK).entity(user).build();
		} catch (ApplicationException e) {
			logger.error(e.getMessage(), e);
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response retriveAllLibrarians() {
		IUserManager manager = new UserManager();
		try {

			List<Librarian> users = manager.retrieveAllLibrarians();
			logger.info("Retrieved all librarians information successfully");
			return Response.status(Response.Status.OK).entity(users).build();
		} catch (ApplicationException e) {
			logger.error(e.getMessage(), e);
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

	}

	@DELETE
	@Path("{username}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteLibrarian(@PathParam("username") String username) {
		IUserManager manager = new UserManager();
		try {

			if (username == null) {
				logger.debug("Missing inputs");
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
			manager.deleteLibrarian(username);
			logger.info("Deleted librarian information successfully");
			return Response.status(Response.Status.OK).build();
		} catch (ApplicationException e) {
			logger.error(e.getMessage(), e);
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

	}

}
