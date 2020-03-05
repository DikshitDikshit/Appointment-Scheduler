/* 
 * File name: Dentist.java
 * Author: Dikshit Dikshit, #040946969
 * Course: CST8284 -OOP
 * Assignment: 3
 * Date: 13-11-2019
 * Professor: David Houtman
 * Purpose: for scheduling appointment under Dentist
 */
package cst8284.asgmt3.employee;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Class Dentist is intended to provide the user to book an appointment to
 * the specific doctor, which in this case is under Dentist.
 * 
 * @author Dikshit Dikshit
 * @version 1.0
 */
public class Dentist extends Employee {



	/** The scan. */
	Scanner scan = new Scanner(System.in);

	/**
	 * Instantiates a new dentist. this passes name of the dentist to the employee
	 * class via chaining, and prints the string passed to it.
	 *
	 * @param fullName the full name
	 */
	public Dentist(String fullName) {
		super(fullName);
		
	}

	/**
	 * this method prompts the user with the activity options.
	 * 
	 * @return string depends on the selection made by user.
	 * @see cst8284.asgmt3.employee.Employee#getActivityType()
	 */
	@Override
	public ArrayList<String> getActivityType() {
		

		ArrayList<String> res = new ArrayList<>();
		res.add("Assessment");
		res.add("Filling");
		res.add("Crown");
		res.add("Cosmetic Repair");
		return res;

	}
}
