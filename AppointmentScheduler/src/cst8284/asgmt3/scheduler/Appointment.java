/* 
 * File name: Appointment.java
 * Author: Dikshit Dikshit, #040946969
 * Course: CST8284 - OOP
 * Assignment: 3
 * Date: 13-11-2019
 * Professor: David Houtman
 * Purpose: makes appointments from provided data.
 */
package cst8284.asgmt3.scheduler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * The Appointment Class represents an appointment with help of information provided for scheduling.
 * 
 * <p>
 * Each instance of an Appointment class is characterized by the five
 * properties: the first and last name Strings; a Calendar to store the date and
 * time of the appointment; the contactee’s telephone number; and the Activity,
 * which can be used to store, at minimum, a description of what the appointment
 * is about.
 * @author Dikshit Dikshit
 * @version 1.0
 * 
 */
public class Appointment implements java.io.Serializable {

	/** The Constant serialVersionUID. */
	public static final long serialVersionUID = 1L;

	/** The apt date. */
	private Calendar aptDate;

	/** The first name. */
	private String firstName;

	/** The last name. */
	private String lastName;

	/** The phone. */
	private TelephoneNumber phone;

	/** The activity. */
	private Activity activity;

	/**
	 * This chains to the next constructor which splits the name into first name and last name.
	 *
	 * @param cal      Appointment Date
	 * @param fullName the full name of the client
	 * @param phone    the phone number of the client
	 * @param activity description of for what purpose the appointment is saved.
	 */
	public Appointment(Calendar cal, String fullName, TelephoneNumber phone, Activity activity) {
		this(cal, fullName.split(" ")[0], fullName.split(" ")[1], phone, activity);
	}

	/**
	 * Instantiates a new appointment and stores the information into appropriate setters.
	 *
	 * @param cal       Appointment Date
	 * @param firstName the first name of the client
	 * @param lastName  the last name of the client
	 * @param phone     the phone number of the client
	 * @param activity  description of for what purpose the appointment is saved.
	 */
	public Appointment(Calendar cal, String firstName, String lastName, TelephoneNumber phone, Activity activity) {
		setAptDate(cal);
		setFirstName(firstName);
		setLastName(lastName);
		setPhone(phone);
		setActivity(activity);
	}

	/**
	 * Gets the apt date.
	 *
	 * @return the apt date
	 */
	public Calendar getAptDate() {
		return aptDate;
	}

	/**
	 * This returns the first name of client.
	 *
	 * @return client's first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Gets the last name of client.
	 *
	 * @return client's last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Gets the phone number of the client.
	 *
	 * @return client's phone number
	 */
	public TelephoneNumber getPhone() {
		return phone;
	}

	/**
	 * Gets the activity for which appointment is booked.
	 *
	 * @return the activity for which appointment is booked.
	 */
	public Activity getActivity() {
		return activity;
	}

	/**
	 * Sets the date of the appointment
	 *
	 * @param aptDate date for which appointment is booked
	 */
	public void setAptDate(Calendar aptDate) {
		this.aptDate = aptDate;
	}

	/**
	 * Sets the first name of the client.
	 *
	 * @param firstName the first name of client
	 */
	public void setFirstName(String firstName) {
		// if (isInputNameCorrect(firstName))
		this.firstName = firstName;
	}

	/**
	 * Sets the last name of client.
	 *
	 * @param lastName last name of client
	 */
	public void setLastName(String lastName) {
		// if (isInputNameCorrect(lastName))
		this.lastName = lastName;
	}

	/**
	 * Sets the phone number of client.
	 *
	 * @param phone phone number of client
	 */
	public void setPhone(TelephoneNumber phone) {
		this.phone = phone;
	}

	/**
	 * Sets the activity for which appointment is booked.
	 *
	 * @param activity activity for which appointment is booked.
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	/*
	 * private static boolean isInputNameCorrect(String name) { if
	 * (name==null||name.isEmpty()) { throw new
	 * BadAppointmentDataException("Must enter a value",
	 * "Empty or null value entered. "); } else if (!name.matches("[a-zA-Z-.']+")) {
	 * throw new BadAppointmentDataException(
	 * "Name cannot include characters other than alphabetic\ncharacters, the dash (-), the period (.), and the apostrophe (‘)."
	 * , "Illegal characters in name. "); } else if (name.length() > 30) { throw new
	 * BadAppointmentDataException("Name cannot exceed 30 characters.",
	 * "Name exceeds maximum length. "); }else { return true; } }
	 */

	/**
	 * Appointment toString()method calls upon the toString()’s of classes Calendar,
	 * TelephoneNumber, and Activity, along with the getters for the first and last
	 * names, and use these strings to construct the string used in outputting
	 * Appointment information to the console.
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		DateFormat format = new SimpleDateFormat("EE MMM dd YYYY HH:mm");
		return format.format(aptDate.getTime()) + "\n" + getFirstName() + " " + getLastName() + "\n"
				+ getPhone().toString() + "\n" + getActivity().toString();
	}

}
