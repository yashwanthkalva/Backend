
package com.library.services;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.library.common.ApplicationException;
import com.library.core.DatabaseManager;
import com.library.model.Librarian;
import com.library.services.interfaces.IUserManager;

/**
 * <p>
 * Kalva Yashwanth
 * </p>
 *
 * @version 1.0
 *
 */
public class UserManager implements IUserManager {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void addLibrarian(Librarian user) throws ApplicationException {
		logger.info("Adding a librarian");
		// Mocking DB using a map
		Map<String, Librarian> userStore = DatabaseManager.getUserStore();
		if (userStore == null) {
			throw new ApplicationException("Unable to connect to DB");
		}
		userStore.put(user.getUsername(), user);

	}

	@Override
	public Librarian retrieveLibrarian(String username) throws ApplicationException {
		logger.info("retrieve a librarian");
		// Mocking DB using a map
		Librarian user = DatabaseManager.getUserStore().get(username);
		if (user == null) {
			throw new ApplicationException("Librarian not found.");
		}
		return user;
	}

	@Override
	public List<Librarian> retrieveAllLibrarians() throws ApplicationException {
		logger.info("Get all librarians info");
		// Mocking DB using a map
		List<Librarian> users = (List<Librarian>) DatabaseManager.getUserStore().values();
		if (users == null) {
			throw new ApplicationException("No Librarians found.");
		}
		return users;
	}

	@Override
	public void deleteLibrarian(String username) throws ApplicationException {
		Map<String, Librarian> userStore = DatabaseManager.getUserStore();
		if (userStore == null) {
			throw new ApplicationException("Unable to delete the librarian record");

		} else {
			userStore.remove(username);
		}

	}
}
