package de.oszimt.ls.quiz.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import de.oszimt.ls.quiz.controller.Controller;
import de.oszimt.ls.quiz.resources.Res;

public class QuizGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblUeberschrift;
	private JButton btnNext;
	private JButton btnSiegGast;
	private JLabel lblKlasse;
	private JButton btnPublikumsjoker;
	private JButton btnNachbarjoker;
	private JButton btnLehrer5050;
	private JButton btnHustenanfall;
	private JButton btnSiegHeim;
	private JButton btnNichtDa;
	private JLabel lblSchueler;
	private JLabel lblSpielstand;
	private JLabel[] lblFrage;
	private Controller control;

	/**
	 * Create the frame.
	 */
	public QuizGUI(Controller control) {
		this.control = control;

		setTitle("OSZ IMT");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblUeberschrift = new JLabel("LF8 LS1 Jugendclub Quiz");
		lblUeberschrift.setFont(new Font("Arial", Font.PLAIN, 40));
		lblUeberschrift.setForeground(Color.WHITE);
		lblUeberschrift.setBackground(Color.WHITE);
		lblUeberschrift.setBounds(50, -2, 703, 92);
		contentPane.add(lblUeberschrift);

		btnNext = new JButton("Sch\u00FCler ausw\u00E4hlen");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNext_ActionPerformed(e);
			}
		});
		btnNext.setForeground(new Color(0, 0, 0));
		btnNext.setBackground(new Color(255, 215, 0));
		btnNext.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNext.setToolTipText("W\u00E4hlt einen Sch\u00FCler aus der XML Datei aus.");
		btnNext.setBounds(536, 320, 228, 76);
		contentPane.add(btnNext);

		btnSiegGast = new JButton("SIEG");
		btnSiegGast.setToolTipText("Sch\u00FCler hat Quiz gewonnen.");
		btnSiegGast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSiegGast_ActionPerformed(e);
			}
		});
		btnSiegGast.setBackground(new Color(0, 128, 0));
		btnSiegGast.setForeground(new Color(255, 215, 0));
		btnSiegGast.setFont(new Font("Arial", Font.PLAIN, 40));
		btnSiegGast.setBounds(634, 100, 130, 70);
		contentPane.add(btnSiegGast);

		lblKlasse = new JLabel(control.getFileParser().getXmlPfad());
		lblKlasse.setFont(new Font("Arial", Font.PLAIN, 20));
		lblKlasse.setForeground(new Color(105, 105, 105));
		lblKlasse.setBounds(10, 437, 516, 25);
		contentPane.add(lblKlasse);

		btnPublikumsjoker = new JButton("Publikumsjoker");
		btnPublikumsjoker.setToolTipText(
				"Die Klasse wird nach der richtigen Antwort befragt. Gibt es mehrere Antworten, so entscheidet der ausgew\u00E4hlte Sch\u00FCler, welche Antwort richtig ist.");
		btnPublikumsjoker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnJoker_ActionPerformed(e);
			}
		});
		btnPublikumsjoker.setForeground(new Color(0, 0, 0));
		btnPublikumsjoker.setBackground(new Color(255, 215, 0));
		btnPublikumsjoker.setFont(new Font("Arial", Font.PLAIN, 20));
		btnPublikumsjoker.setBounds(50, 237, 173, 33);
		contentPane.add(btnPublikumsjoker);

		btnNachbarjoker = new JButton("Nachbarjoker");
		btnNachbarjoker.setToolTipText(
				"Es wird zwischen dem rechten bzw. linken Nachbarn gew\u00E4hlt. Dieser darf die Frage beantworten. ");
		btnNachbarjoker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnJoker_ActionPerformed(e);
			}
		});
		btnNachbarjoker.setBackground(new Color(255, 215, 0));
		btnNachbarjoker.setForeground(new Color(0, 0, 0));
		btnNachbarjoker.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNachbarjoker.setBounds(227, 237, 173, 33);
		contentPane.add(btnNachbarjoker);

		btnLehrer5050 = new JButton("Lehrer 50:50");
		btnLehrer5050.setToolTipText(
				"Der Lehrer gibt 2 Antworten vor und der ausgew\u00E4hlte Sch\u00FCler entscheidet, welche der Beiden richtig ist.");
		btnLehrer5050.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnJoker_ActionPerformed(e);
			}
		});
		btnLehrer5050.setForeground(new Color(0, 0, 0));
		btnLehrer5050.setBackground(new Color(255, 215, 0));
		btnLehrer5050.setFont(new Font("Arial", Font.PLAIN, 20));
		btnLehrer5050.setBounds(404, 237, 173, 33);
		contentPane.add(btnLehrer5050);

		btnHustenanfall = new JButton("Hustenanfall");
		btnHustenanfall.setToolTipText("Wenn ein nicht-ausgew\u00E4hlter Sch\u00FCler die richtige Antwort vorsagt.");
		btnHustenanfall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnJoker_ActionPerformed(e);
			}
		});
		btnHustenanfall.setForeground(new Color(0, 0, 0));
		btnHustenanfall.setBackground(new Color(255, 215, 0));
		btnHustenanfall.setFont(new Font("Arial", Font.PLAIN, 20));
		btnHustenanfall.setBounds(581, 237, 173, 33);
		contentPane.add(btnHustenanfall);

		btnSiegHeim = new JButton("SIEG");
		btnSiegHeim.setToolTipText("Lehrer hat Quiz gewonnen.");
		btnSiegHeim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSiegHeim_ActionPerformed(e);
			}
		});
		btnSiegHeim.setForeground(new Color(255, 215, 0));
		btnSiegHeim.setBackground(new Color(255, 0, 0));
		btnSiegHeim.setFont(new Font("Arial", Font.PLAIN, 40));
		btnSiegHeim.setBounds(50, 100, 130, 70);
		contentPane.add(btnSiegHeim);

		btnNichtDa = new JButton("nicht anwesend");
		btnNichtDa.setToolTipText("Der ausgew\u00E4hlte Sch\u00FCler ist nicht anwesend.");
		btnNichtDa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNichtDa_ActionPerformed(e);
			}
		});
		btnNichtDa.setForeground(new Color(0, 0, 0));
		btnNichtDa.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNichtDa.setBackground(new Color(255, 215, 0));
		btnNichtDa.setBounds(536, 406, 228, 33);
		contentPane.add(btnNichtDa);

		lblSchueler = new JLabel("Sch\u00FCler ausw\u00E4hlen -->");
		lblSchueler.setForeground(new Color(255, 215, 0));
		lblSchueler.setFont(new Font("Arial", Font.PLAIN, 40));
		lblSchueler.setHorizontalAlignment(SwingConstants.CENTER);
		lblSchueler.setBackground(new Color(255, 255, 255));
		lblSchueler.setBounds(50, 320, 486, 119);
		contentPane.add(lblSchueler);

		lblSpielstand = new JLabel();
		lblSpielstand.setText(control.getSpielstand());
		lblSpielstand.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpielstand.setFont(new Font("Arial", Font.PLAIN, 40));
		lblSpielstand.setForeground(new Color(255, 215, 0));
		lblSpielstand.setBounds(180, 100, 444, 70);
		contentPane.add(lblSpielstand);

		// URL url = Res.class.getResource("oszimt.png");
		ImageIcon icon = new ImageIcon("oszimt.png");
		this.setIconImage(icon.getImage());

		lblFrage = new JLabel[control.FRAGEANZAHL];
		for (int i = 0; i < lblFrage.length; i++) {
			lblFrage[i] = new JLabel();
			lblFrage[i].setBounds((50 * i + 25), 185, 50, 39);
			lblFrage[i].setText(Integer.toString(i + 1));
			lblFrage[i].setHorizontalAlignment(SwingConstants.CENTER);
			lblFrage[i].setHorizontalTextPosition(SwingConstants.CENTER);
			lblFrage[i].setBackground(Color.BLACK);
			lblFrage[i].setForeground(new Color(255, 215, 0));
			lblFrage[i].setFont(new Font("Arial", Font.PLAIN, 20));
			contentPane.add(lblFrage[i]);
		}
	}

	public void btnSiegHeim_ActionPerformed(ActionEvent evt) {
		if (control.getGewaehlterSchueler() != null) {
			control.blamiert();
			control.heimGewonnen();
			lblSpielstand.setText(control.getSpielstand());
			lblFrage[control.getFrageZeiger() - 1].setForeground(Color.RED);
		}
	}

	public void btnSiegGast_ActionPerformed(ActionEvent evt) {
		if (control.getGewaehlterSchueler() != null && control.Spielende()) {
			control.gastGewonnen();
			lblSpielstand.setText(control.getSpielstand());
		}
	}

	public void btnJoker_ActionPerformed(ActionEvent evt) {
		if (control.getGewaehlterSchueler() != null) {
			control.jokerBenutzt();
			JButton btn = (JButton) evt.getSource();
			btn.setBackground(Color.GRAY);
			btn.setEnabled(false);
		}
	}

	public void btnNext_ActionPerformed(ActionEvent evt) {
		if (!control.Spielende()) {
			lblFrage[control.getFrageZeiger()].setForeground(new Color(0, 128, 0));
			control.getZufallsSchueler();
			control.frageBeantwortet();
			this.lblSchueler.setText(control.getGewaehlterSchueler().getFullName());
		}
	}

	public void btnNichtDa_ActionPerformed(ActionEvent evt) {
		if (control.getGewaehlterSchueler() != null) {
			control.nichtDa();
			control.getZufallsSchueler();
			this.lblSchueler.setText(control.getGewaehlterSchueler().getFullName());
		}
	}
}
