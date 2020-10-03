package com.library.services.interfaces;

import java.util.List;

import com.library.common.ApplicationException;
import com.library.model.Librarian;

public interface IUserManager {

	public void addLibrarian(Librarian user) throws ApplicationException;
	public Librarian retrieveLibrarian(String username) throws ApplicationException;
	public List<Librarian> retrieveAllLibrarians() throws ApplicationException;
	public void deleteLibrarian(String username) throws ApplicationException;

}
