package swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.RenderingHints.Key;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpringLayout.Constraints;
import javax.swing.UIManager;

public class Main {

	public static void main(String[] args) {
		final JFrame f = new JFrame("My Frame");
		f.setPreferredSize(new Dimension(500, 500));
		f.setLayout(new BorderLayout());
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		// q1to13(f);
		initMenuBar(f);
		f.add(new TextArea("Zone de texte"), BorderLayout.CENTER);
		JPanel south = new JPanel();
		south.setLayout(new FlowLayout(FlowLayout.LEFT));
		south.add(new JLabel("Rechercher :"));
		south.add(new JTextField(16));
		south.add(new JButton(new ImageIcon("./image/previous_motif.gif")));
		south.add(new JButton(new ImageIcon("./image/next_motif.gif")));
		south.add(new JButton("Tout surligner"));
		f.add(south,BorderLayout.SOUTH);
		//
		f.pack();
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// f.setResizable(false);
		f.setVisible(true);
		// afficherJDialog(f);
	}

	private static void initMenuBar(final JFrame f) {
		f.setJMenuBar(new JMenuBar());
		JMenu fichier = new JMenu("Fichier");
		JMenuItem nouveau = new JMenuItem();
		nouveau.setAction(new AbstractAction("Nouveau") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
afficherJDialog(f);				
			}
		});
		nouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				ActionEvent.CTRL_MASK));
		fichier.add(nouveau);
		fichier.add(new JMenuItem("Ouvrir"));
		fichier.add(new JMenuItem("Enregistrer"));
		fichier.add(new JMenuItem("Enregistrer sous"));
		fichier.addSeparator();
		fichier.add(new JMenuItem("Mise en page"));
		fichier.add(new JMenuItem("Imprimer"));
		fichier.addSeparator();
		JMenuItem quit = new JMenuItem("Quitter");
	
		fichier.add(quit);
		f.getJMenuBar().add(fichier);
		JMenu edition = new JMenu("Edition");
		f.getJMenuBar().add(edition);
		JMenu format = new JMenu("Format");
		f.getJMenuBar().add(format);
	}

	/**
	 * Question 5 sur les JDialog
	 */
	private static void afficherJDialog(JFrame f) {
		// Modal
		JDialog dial = new JDialog(f, "Dialog Modale", true);
		dial.add(new JLabel("Voici une JDialog modale"));
		dial.setLocationRelativeTo(null);
		dial.setSize(200, 200);
		dial.setVisible(true);
		// Non Modal
		// JDialog dial = new JDialog(f, "dialog", false);
		// dial.setLocationRelativeTo(null);
		// dial.setSize(200, 200);
		// dial.setVisible(true);
	}

	/**
	 * Les question 1 a 13
	 * 
	 */
	private static void q1to13(JFrame f) {
		// ================== BORDER LAYOUT
		// f.setLayout(new BorderLayout());
		// f.add(new JButton("Nord"), BorderLayout.NORTH);
		// f.add(new JButton("East"), BorderLayout.EAST);
		// f.add(new JButton("Center"), BorderLayout.CENTER);
		// f.add(new JButton("West"), BorderLayout.WEST);
		// f.add(new JButton("South"), BorderLayout.SOUTH);
		// ================== FLOW LAYOYT
		// f.setLayout(new FlowLayout(FlowLayout.CENTER));
		// for (int i = 0; i < 15; i++) {
		// JButton bouton = new JButton("Bouton "+i);
		// f.add(bouton);
		// }
		//
		// ================== GRID LAYOUT
		// f.getContentPane().setLayout(new GridLayout(4, 4));
		// for (int i = 0; i < 16; i++) {
		// JButton bouton = new JButton("Bouton " + i);
		// f.getContentPane().add(bouton);
		// }
		//
		// ================== BOX LAYOUT
		// f.getContentPane().setLayout(
		// new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
		// JButton bouton1 = new JButton("Bouton 1");
		// bouton1.setAlignmentX(Component.CENTER_ALIGNMENT);
		// f.add(bouton1);
		// JButton bouton2 = new JButton("Bouton 2");
		// bouton2.setAlignmentX(Component.CENTER_ALIGNMENT);
		// f.add(bouton2);
		// f.add(new Box.Filler(new Dimension(50, 50), new Dimension(10, 10),
		// new Dimension(100, 100)));
		// JButton bouton3 = new JButton("Bouton 3");
		// bouton3.setAlignmentX(Component.CENTER_ALIGNMENT);
		// f.add(bouton3);
		//
		// ================== GRIDBAG LAYOUT
		// f.setLayout(new GridBagLayout());
		// GridBagConstraints constraints = new GridBagConstraints();
		// constraints.fill = GridBagConstraints.BOTH;
		//
		// for (int i = 0 ; i < 4 ; i++){
		// constraints.insets = new Insets(5, 10, 5, 10);
		// f.add(new JButton("Bouton "+i),constraints);
		// }
		// constraints.gridy = 1;
		// constraints.gridwidth = 4;
		// f.add(new JButton("Bouton 5"),constraints);
		// constraints.gridy = 2;
		// constraints.gridwidth = 3;
		// f.add(new JButton("Bouton 6"),constraints);
		// constraints.gridy = 2;
		// constraints.gridwidth = GridBagConstraints.REMAINDER;
		// f.add(new JButton("Bouton 7"),constraints);
		// constraints.gridy = 3;
		// constraints.gridwidth =1;
		// constraints.gridheight = 10;
		// f.add(new JButton("Bouton 8"),constraints);
		// constraints.gridy = 3;
		// constraints.gridwidth = GridBagConstraints.REMAINDER;
		// f.add(new JButton("Bouton 9"),constraints);
		// constraints.gridy = 4;
		// constraints.gridwidth = GridBagConstraints.REMAINDER;
		// f.add(new JButton("Bouton 10"),constraints);
		//
		// ================== NULL LAYOUT
		// f.setLayout(null);
		// for (int i = 0; i < 9; i++) {
		// JButton bouton = new JButton("Bouton "+i);
		// bouton.setBounds(10+i*50, 10+i*50, 100, 25);
		// f.add(bouton);
		// }
		//
		// ================== UTILISATION DE JPANEL
		f.setLayout(new BorderLayout());
		JPanel panelNord = new JPanel();
		for (int i = 0; i < 3; i++) {
			panelNord.add(new JButton("Bouton " + 1));
		}
		f.add(panelNord, BorderLayout.NORTH);
		f.add(new JButton("East"), BorderLayout.EAST);
		f.add(new JButton("Center"), BorderLayout.CENTER);
		f.add(new JButton("West"), BorderLayout.WEST);
		f.add(new JButton("South"), BorderLayout.SOUTH);
	}

}
