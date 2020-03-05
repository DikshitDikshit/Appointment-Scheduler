/* 
 * File name: SchedularLauncher.java
 * Author: Dikshit Dikshit, #040946969
 * Course: CST8284 -OOP
 * Assignment: 3
 * Date: 13-11-2019
 * Professor: David Houtman
 * Purpose: executes application. this class contains main method.
 */
package cst8284.asgmt3.scheduler;

import cst8284.asgmt3.employee.Dentist;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * The Class SchedulerLauncher contains the main()method which executes the
 * application.
 * <p>
 * This application will allow the user to store and retrieve appointment
 * information for dentist appointment from a scheduler.
 * 
 * @author Dikshit Dikshit
 * @version 1.0
 * 
 */
public class SchedulerLauncher extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final Toolkit tk = Toolkit.getDefaultToolkit();
	private static final Dimension screenSize = tk.getScreenSize();
	private static final JTextArea scrollText = new JTextArea();

	private static ArrayList<String> listOfStrings = null;
	private static Scheduler scheduler;

	private static JFrame frame;

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				scheduler = new Scheduler(new Dentist("Dr. Andrews\n"));
				loadAndShowWords();
			}
		});
	}

	private static void loadAndShowWords() {
		frame = new JFrame();
		frame.setTitle("Scheduling appointment for Dr.Andrews");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		int screenX = (int) screenSize.getWidth() / 2;
		int screenY = (int) (7 * screenSize.getHeight() / 8);

		if (listOfStrings != null) {
			reloadJTextArea();
		}
		frame.add(getWestPanel(), BorderLayout.WEST);
		frame.add(getCenterPanel(scrollText, screenY), BorderLayout.CENTER);
		frame.setPreferredSize(new Dimension(screenX, screenY));
		frame.pack();
		frame.setVisible(true);

	}

	public static JPanel getCenterPanel(JTextArea jta, int height) {
		JScrollPane centerPane = new JScrollPane(jta);
		centerPane.setPreferredSize(new Dimension(400, 7 * height / 8));
		JPanel jp = new JPanel();
		jp.add(centerPane);
		return jp;
	}

	public static JPanel getWestPanel() {
		JPanel controlPanel = new JPanel(new GridLayout(6, 1));
		JPanel westPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.weighty = 1;

		controlPanel.add(setWestPanelBtns("Save Appointment ", e -> {
			AppointmentDialog.showAppointmentDialog(scheduler);

		}));

		controlPanel.add(setWestPanelBtns("Display Appointment ", e -> {
			AppointmentDialog.showAppointmentDialog(scheduler);
			return;
		}));

		controlPanel.add(setWestPanelBtns("Display Schedule ", e -> {
			SchedulerViewer.showDisplaySchedule(scheduler, scrollText);

			return;
		}));
		controlPanel.add(setWestPanelBtns("Save Appointments to file", e -> {
			SaveAppointmentToFileDialog.showSaveAppointmentToFileDialog(scheduler, "CurrentAppointments.apt");
			return;

		}));
		controlPanel.add(setWestPanelBtns("Load Appointments from file", e -> {
			SaveAppointmentToFileDialog.showLoadAppointmentToFileDialog(scheduler, "CurrentAppointments.apt");

			return;
		}));
		controlPanel.add(setWestPanelBtns("Exit", e -> {
			frame.dispose();

			return;
		}));

		westPanel.add(controlPanel, gbc);
		return westPanel;
	}

	private static JButton setWestPanelBtns(String btnLabel, ActionListener act) {
		final Font font = new Font("SansSerif", Font.PLAIN, 20);
		JButton btn = new JButton(btnLabel);
		btn.setFont(font);
		btn.addActionListener(act);
		return btn;
	}

	public static ArrayList<String> getListOfStrings() {
		return listOfStrings;
	}

	public static void reloadJTextArea() {

	}
}