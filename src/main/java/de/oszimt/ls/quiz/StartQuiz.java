package de.oszimt.ls.quiz;

import de.oszimt.ls.quiz.controller.Controller;
import de.oszimt.ls.quiz.view.QuizGUI;

import java.awt.EventQueue;

import javax.swing.JOptionPane;

public class StartQuiz {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Controller controller = new Controller("Klasse.xml","Klasse.csv");
					QuizGUI frame = new QuizGUI(controller);
					frame.setVisible(true);
				} catch (Exception e) {
					showException(e, e.getMessage());
				}
			}
		});
	}

	/**
	 * Zeigt die Fehlermeldung als Dialog an
	 * 
	 * @param e
	 * @param message
	 */
	public static void showException(Exception e, String message) {
		System.err.println(message);
		e.printStackTrace();
		//show an gui exception
		//JOptionPane.showMessageDialog(null, message + " in " + e.getStackTrace()[0], e.getClass().getCanonicalName(),
		//		JOptionPane.ERROR_MESSAGE);
	}
}