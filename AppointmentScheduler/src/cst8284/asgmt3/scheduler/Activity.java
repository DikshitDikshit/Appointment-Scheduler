/* 
 * File name: Activity.java
 * Author: Dikshit Dikshit, #040946969
 * Course: CST8284 - OOP
 * Assignment: 3
 * Date: 13-11-2019
 * Professor: David Houtman
 * Purpose: used to store description of appointment.
 */
package cst8284.asgmt3.scheduler;


/**
 * The Class Activity used to store, at minimum, a description of
 * what the appointment is about.
 * 
 * The Activity class which can be used to store, at minimum, a description of
 * what the appointment is about.
 * <p>
 * Each instance of an Activity class is characterized by the two properties:
 * the description of work and category of appointment Strings
 * @author Dikshit Dikshit
 * @version 1.0
 */

@SuppressWarnings("serial")
public class Activity implements java.io.Serializable {

	/** The description of work. */
	private String descriptionOfWork;

	/** The category. */
	private String category;

	/**
	 * Instantiates a new activity.
	 *
	 * @param description the description of what the appointment is about.
	 * @param category    the category of the the appointment.
	 */
	public Activity(String description, String category) {
		setDescription(description);
		setCategory(category);
	}

	/**
	 * Gets the description of the activity.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return descriptionOfWork;
	}

	/**
	 * Gets the category of the activity.
	 *
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Sets the description of the activity.
	 *
	 * @param descriptionOfWork the description of what the appointment is about.
	 */
	public void setDescription(String descriptionOfWork) {
		this.descriptionOfWork = descriptionOfWork;
	}

	/**
	 * Sets the category of activity.
	 *
	 * @param category the category of what the appointment is about.
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getCategory() + "\n" + getDescription();

	}

}
