/* 
 * File name: SortAppointmentByCalendar.java
 * Author: Dikshit Dikshit, #040946969
 * Course: CST8284 -OOP
 * Assignment: 3
 * Date: 13-11-2019
 * Professor: David Houtman
 * Purpose: sorts appointment by Calendar
 */
package cst8284.asgmt3.scheduler;

import java.util.Comparator;


/**
 * The Class SortAppointmentByCalendar sorts Appointment ArrayList according by Calendar date..
 * 
 * Sorts Appointment ArrayList according to its Calendar date. locate an
 * Appointment, the search is much faster and more efficient
 * 
 * @author Dikshit Dikshit
 * @version 1.0
 * @see java.util.Comparator
 */
public class SortAppointmentByCalendar implements Comparator<Appointment> {

	/**
	 * This is an overridden method of class Comparator which returns an integer
	 * value representing the difference between the Calendar’s of the two
	 * Appointment’s input as parameters to the compare() method.
	 * 
	 * 
	 */
	@Override
	public int compare(Appointment o1, Appointment o2) {
		return o1.getAptDate().compareTo(o2.getAptDate());
	}
}
