/* 
 * File name: BadAppointmentDataException.java
 * Author: Dikshit Dikshit, #040946969
 * Course: CST8284 - OOP
 * Assignment: 3
 * Date: 13-11-2019
 * Professor: David Houtman
 * Purpose: Exception handling.
 */
package cst8284.asgmt3.scheduler;


/**
 * The Class BadAppointmentDataException handles all the exceptions for Appointment Scheduler.
 * if a user put invalid data this class prints the alert message.
 * @author Dikshit Dikshit
 * @version 1.0
 */
public class BadAppointmentDataException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The description of error message. */
	private String description = "";

	/**
	 * this instantiates a new bad appointment data exception. The two-String
	 * constructor uses super() to pass the first String up to the superclass. ) so
	 * that it will be returned when you call ex.getMessage().
	 * The second String is stored to a private String field called Description with its appropriate setter.
	 *
	 * @param s           the error message to be displayed.
	 * @param description the description of the error message.
	 */
	public BadAppointmentDataException(String s, String description) {
		super(s);
		setDescription(description);
	}

	/**
	 * This instantiates a new bad appointment data exception. The former
	 * constructor passes the Strings “Please try again” and “Bad data entered” to
	 * the latter via chaining.
	 */
	public BadAppointmentDataException() {
		this("Please try again", "Bad data entered");
	}

	/**
	 * Gets the description of the error message.
	 *
	 * @return error's description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets error's description.
	 *
	 * @param description the description of the error message.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
