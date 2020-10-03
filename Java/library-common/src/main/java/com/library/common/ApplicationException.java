
package com.library.common;

public class ApplicationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3540659749449394983L;

	/** Optional error code for this Exception */
	private String errorCode;

	/** Represents type of exception occured */
	private String exceptionType;

	/** Will contain a custom exception message */
	private String exceptionMsg;

	/**
	 * No args constructor
	 */
	public ApplicationException() {
	}

	/**
	 * Constructs an instance of this Exception with the specified exception message
	 * 
	 * @param message
	 *            String message for this Exception
	 */
	public ApplicationException(String message) {
		super(message);
		this.errorCode = message;
	}

	/**
	 * Constructs an instance of this Exception with the specified exception message and error code
	 * 
	 * @param message
	 *            String message for this Exception
	 * @param errorCode
	 *            the errorCode for the message
	 */
	public ApplicationException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	/**
	 * Constructs an instance of this Exception with the specified cause
	 * 
	 * @param cause
	 *            the Throwable cause for this Exception
	 */
	public ApplicationException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs an instance of this Exception with the specified exception message and cause
	 * 
	 * @param message
	 *            String message for this Exception The errorCode of this exception would be set from the message
	 * @param cause
	 *            the Throwable cause for this Exception
	 */
	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
		this.errorCode = message;
	}

	/**
	 * Gets the root cause for this Exception
	 * 
	 * @return the root cause of this Exception
	 */
	public Throwable getRootCause() {

		Throwable e = getCause();
		Throwable eParent;
		for(eParent = this; e != null && e != eParent; e = e.getCause()) {
			eParent = e;
		}
		return eParent;
	}

	/**
	 * Returns the errorcode for this Exception
	 * 
	 * @return the error code this Exception was created with
	 */
	public String getErrorCode() {

		return errorCode;
	}

	/**
	 * @return the exceptionType
	 */
	public String getExceptionType() {

		return exceptionType;
	}

	/**
	 * @param exceptionType
	 *            the exceptionType to set
	 */
	public void setExceptionType(String exceptionType) {

		this.exceptionType = exceptionType;
	}

	/**
	 * @return the exceptionMsg
	 */
	public String getExceptionMsg() {

		return exceptionMsg;
	}

	/**
	 * @param exceptionMsg
	 *            the exceptionMsg to set
	 */
	public void setExceptionMsg(String exceptionMsg) {

		this.exceptionMsg = exceptionMsg;
	}

}
