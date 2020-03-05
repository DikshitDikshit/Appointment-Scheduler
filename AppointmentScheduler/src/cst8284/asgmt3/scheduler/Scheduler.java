package cst8284.asgmt3.scheduler;

import java.util.Scanner;
import cst8284.asgmt3.employee.Employee;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class Scheduler {

	private static Scanner scan = new Scanner(System.in);

	private ArrayList<Appointment> appointments = new ArrayList<Appointment>();

	private Employee employee;

	/*
	 * private static final int SAVE_APPOINTMENT = 1;
	 * 
	 * private static final int DELETE_APPOINTMENT = 2;
	 * 
	 * private static final int CHANGE_APPOINTMENT = 3;
	 * 
	 * private static final int DISPLAY_APPOINTMENT = 4;
	 * 
	 * private static final int DISPLAY_SCHEDULE = 5;
	 */

	private static final int SAVE_APPOINTMENT_TO_FILE = 6;

	private static final int LOAD_APPOINTMENT_FROM_FILE = 7;

	private static final int EXIT = 0;

	public Scheduler(Employee emp) {
		setEmployee(emp);
	}

	public void launch() {
		int choice = 0;
		do {
			choice = displayMenu();
			try {
				executeMenuItem(choice);
			} catch (BadAppointmentDataException ex) {

				System.err.println("Description: " + ex.getMessage() + ex.getDescription());
			}
		} while (choice != EXIT);
	}

	private void setEmployee(Employee emp) {
		this.employee = emp;
	}

	public Employee getEmployee() {
		return employee;
	}

	private int displayMenu() {
		
		int ch = scan.nextInt();
		scan.nextLine();
		System.out.println();
		return ch;
	}

	private void executeMenuItem(int choice) {
		switch (choice) {

		case SAVE_APPOINTMENT_TO_FILE:
			String savePath = "CurrentAppointments.apts";
			saveAppointmentsToFile(getAppointments(), savePath);
			break;
		case LOAD_APPOINTMENT_FROM_FILE:
			String loadPath = "CurrentAppointments.apts";
			loadAppointmentsFromFile(loadPath, getAppointments());
			break;
		case EXIT:
			System.out.println("Exiting Scheduler\n\n");
			break;
		default:
			System.out.println("Invalid choice: try again. (Select " + EXIT + " to exit.)\n");
		}
		System.out.println();
	}

	public Appointment makeAppointmentFromUserInput(String fullName, String phoneNumber, String date, String time,
			String category, String activityType) {

		if (fullName == null || fullName.isEmpty() || fullName.trim().split(" ").length < 2) {
			throw new BadAppointmentDataException("Empty or null value entered", ". Must enter a value");
		}

		if (!fullName.trim().split(" ")[0].matches("([A-Za-z]|-|\\.|')+")
				|| !fullName.trim().split(" ")[1].matches("([A-Za-z]|-|\\.|')+")) {
			throw new BadAppointmentDataException(
					"Name cannot include characters other than alphabetic characters, the dash (-), the period (.), and the apostrophe (')",
					". Illegal characters in name");
		}

		if (fullName.trim().length() > 30) {
			throw new BadAppointmentDataException("\tName cannot exceed 30 character",
					"\n\t\tName exceeds maximum length");
		}

		TelephoneNumber phone = new TelephoneNumber(phoneNumber);
		Calendar cal = makeCalendarFromUserInput(false, date, time);
		Activity act = new Activity(category, activityType);
		return (new Appointment(cal, fullName, phone, act));
	}

	public static Calendar makeCalendarFromUserInput(boolean suppressHour, String date, String time) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		Calendar cal = Calendar.getInstance();
		int hour = 0;

		cal.clear();

		if (date.isEmpty() || date == null) {
			throw new BadAppointmentDataException("Must enter a value", ". Empty or null value entered");
		}

		dateFormat.setLenient(false);
		try {
			dateFormat.parse(date);
		} catch (ParseException e) {
			System.out.println("Description: " + e.getMessage());
			throw new BadAppointmentDataException("Bad calendar date entered; format is DDMMYYY",
					". Bad calendar format");
		}

		int day = Integer.parseInt(date.substring(0, 2));
		int month = Integer.parseInt(date.substring(2, 4)) - 1;
		int year = Integer.parseInt(date.substring(4, 8));

		if (!suppressHour) {
			hour = processTimeString(time);
		}
		cal.set(year, month, day, hour, 0);
		return (cal);
	}

	private static int processTimeString(String t) {
		int hour = 0;
		t = t.trim();
		if (t.contains(":"))
			hour = Integer.parseInt(t.split(":")[0]);
		else if (t.contains(" "))
			hour = Integer.parseInt(t.split(" ")[0]);
		else
			hour = Integer.parseInt(t);
		return ((hour < 8) ? hour + 12 : hour);
	}

	public Appointment findAppointment(Calendar cal) {

		Collections.sort(getAppointments(), new SortAppointmentByCalendar());
		int idx = Collections.binarySearch(getAppointments(), new Appointment(cal, null, null, null, null),
				new SortAppointmentByCalendar());
		if (idx >= 0) {
			return getAppointments().get(idx);
		}
		return null;
	}

	public boolean saveAppointment(Appointment apt) {
		Calendar cal = apt.getAptDate();
		if (findAppointment(cal) == null) {
			getAppointments().add(apt);
			Collections.sort(getAppointments(), new SortAppointmentByCalendar());
			System.out.println("\nAppointment saved.");
			return true;
		}
		System.out.println("Cannot save; an appointment at that time already exists");
		return false;
	}

	public boolean deleteAppointment(Calendar cal) {
		if (findAppointment(cal) != null) {

			getAppointments().remove(findAppointment(cal));
			return true;
		}
		return false;
	}

	private String displayAppointment(Calendar cal) {
		Appointment apt = findAppointment(cal);
		int hr = cal.get(Calendar.HOUR_OF_DAY);
		return (apt != null) ? "\n" + apt.toString() + "\n"
				: "No appointment scheduled between " + hr + ":00 and " + (hr + 1) + ":00";
	}

	public String displayDaySchedule(Calendar cal) {
		String str = new String();
		for (int hrCtr = 8; hrCtr < 17; hrCtr++) {
			cal.set(Calendar.HOUR_OF_DAY, hrCtr);
			str += displayAppointment(cal) + "\n";
		}

		return str;
	}

	public static boolean saveAppointmentsToFile(ArrayList<Appointment> apts, String saveFile) {
		try {
			File file = new File(saveFile);
			FileOutputStream save1 = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(save1);
			oos.writeObject(apts);
			oos.close();
			return true;

		} catch (IOException ex) {
			ex.printStackTrace();
			System.out.println("Appointment saved");
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public boolean loadAppointmentsFromFile(String sourceFile, ArrayList<Appointment> apts) {
		try {
			File file = new File(sourceFile);
			FileInputStream save1 = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(save1);
			apts.clear();
			apts.addAll((ArrayList<Appointment>) ois.readObject());
			ois.close();
			System.out.println("Appointment successfully loaded from " + sourceFile);

		} catch (IOException ex) {
			System.out.println("Appointment saved");
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			System.out.println("File not correct");
		}
		return false;
	}

	public ArrayList<Appointment> getAppointments() {
		return appointments;
	}
}