/* 
 * File name: TelephoneNumber.java
 * Author: Dikshit Dikshit, #040946969
 * Course: CST8284 -OOP
 * Assignment: 3
 * Date: 13-11-2019
 * Professor: David Houtman
 * Purpose: implements valid phone number
 */
package cst8284.asgmt3.scheduler;


/**
 * The Class TelephoneNumber constructs a phone number.
 * 
 * @author Dikshit Dikshit
 * @version 1.0
 * @see java.io.Serializable
 * 
 */
@SuppressWarnings("serial")
public class TelephoneNumber implements java.io.Serializable {

	/** The area code. */
	private int areaCode;

	/** The prefix. */
	private int prefix;

	/** The line number. */
	private int lineNumber;

	/**
	 * This constructs a Telephone number if it is correct and splits the phone
	 * number into areaCode, prefix, and lineNumber seperated by "-". The
	 * constructor takes in a String in this form 613-555-1212 and parses it into its three
	 * fields, using the appropriate setters for storage
	 * 
	 * @param phoneNumber : Phone number as "AAA-PPP-NNNN", where AAA is area code
	 *                    and cannot start with '0' or '1', PPP is prefix, and NNNN
	 *                    is line number.
	 */
	public TelephoneNumber(String phoneNumber) {
		if (isPhoneNumberCorrrect(phoneNumber)) {
			String p = String.valueOf(phoneNumber.replace("-", ""));
			setAreaCode(Integer.parseInt(p.substring(0, 3)));
			setPrefix(Integer.parseInt(p.substring(3, 6)));
			setLineNumber(Integer.parseInt(p.substring(6, 10)));
		}
	}

	/**
	 * Gets the area code of phone number.
	 *
	 * @return the phonenumber's area code
	 */
	public int getAreaCode() {

		return areaCode;
	}

	/**
	 * Sets the area code of phone number.
	 *
	 * @param areaCode phonenumber's area code
	 */
	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * Gets the prefix of phone number.
	 *
	 * @return the phonenumber's prefix
	 */
	public int getPrefix() {
		return prefix;
	}

	/**
	 * Gets the line number of phone number.
	 *
	 * @return phonenumber's lineNumber 
	 */
	public int getLineNumber() {
		return lineNumber;
	}

	/**
	 * Sets the prefix of phone number.
	 *
	 * @param prefix phonenumber's prefix
	 */
	public void setPrefix(int prefix) {
		this.prefix = prefix;
	}

	/**
	 * Sets the line number of phone number.
	 *
	 * @param lineNumber phonenumber's line number
	 */
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	/**
	 * Checks if is phone number corrrect.
	 *
	 * @param phone the phone number which user input
	 * @return true, if is phone number corrrect
	 */
	private static boolean isPhoneNumberCorrrect(String phone) {
		if (phone == null || phone.isEmpty()) {
			throw new BadAppointmentDataException("Must enter a value.", "Empty or null value entered. ");
		} else if (!phone.matches("[0-9-]+")) {
			throw new BadAppointmentDataException("Telephone numbers can only contain numbers or the character ‘-‘.",
					"Bad character(s) in input string. ");
		} else if (!phone.matches("\\d{3}[-]\\d{3}[-]\\d{4}")) {
			throw new BadAppointmentDataException(
					"Missing digit(s); correct format is AAA-PPP-NNNN, \nwhere AAA is the area code and PPP-NNNN is the local number.",
					"Incorrect format. ");
		} else if (phone.startsWith("1") || phone.startsWith("0")) {
			throw new BadAppointmentDataException("Area code can’t start with a ‘0’ or a ‘1’", "Invalid number. ");
		} else {
			return true;
		}
	}

	/** this overridden toString() return a String phone number in the form “(613) 555-1212”
	 * 
	 * @see java.lang.Object#toString()
	 * 
	 */
	public String toString() {
		return "(" + getAreaCode() + ") " + getPrefix() + "-" + getLineNumber();
	}

}
