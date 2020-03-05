/* 
 * File name: Employee.java
 * Author: Dikshit Dikshit, #040946969
 * Course: CST8284 -OOP
 * Assignment: 3
 * Date: 13-11-2019
 * Professor: David Houtman
 * Purpose: holds Employee information
 */
package cst8284.asgmt3.employee;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * The Class Employee is an abstract class is designed to hold Employee
 * information.
 * <p>
 * One of things about each employee’s job is that it consists of certain
 * responsibilities and activities that are unique to that position.
 * @author Dikshit Dikshit
 * @version 1.0
 */
public abstract class Employee {

	/** The full name. */
	private String fullName;

	/**
	 * Instantiates a new employee. The former constructor passes a String to the
	 * latter one string argument constructor via chaining.
	 */
	protected Employee() {
		this("unknown");
	}

	/**
	 * Instantiates a new employee. This constructor stores the full name of an
	 * employee using appropriate setters and getters.
	 *
	 * @param fullName full name of employee
	 */
	protected Employee(String fullName) {
		setName(fullName);
	}

	/** The scanner object for user input. */
	protected static Scanner scan = new Scanner(System.in);

	/**
	 * Sets the full name of an employee.
	 *
	 * @param fullName name of an employee
	 */
	public void setName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Gets the full name of an employee.
	 *
	 * @return fullName of an employee
	 */
	public String getName() {
		return fullName;
	}

	/**
	 * This method is intended to provide activity information for any subclass of Employee. 
	 *
	 * @return the activity type
	 */
	public abstract ArrayList<String> getActivityType();

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getName();
	}

}