/**
 * 
 */
package com.app.integrated.main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import com.app.abacus.panel.AbacusPanel;
import com.app.abacus.panel.exception.AbacusException;
import com.app.instruction.panel.InstructionPanel;
import com.app.instructions.compiler.Action;
import com.app.instructions.compiler.InstructionCompiler;
import com.app.instructions.compiler.exception.CompilerException;
import com.app.sound.DownloadSpeech;

/**
 * @author prashant.joshi
 *
 */
public class TestAllAbacusComponent extends JFrame {

	private static final long serialVersionUID = 1L;

	// Top panels

	// private JPanel abacusTopPanel;

	private JMenuBar menuBar;
	private JMenu voices, properties, excelfile, status;
	private JMenuItem abcusproperties, instructionproperties,loadexcel,start,stop;
	private JCheckBoxMenuItem robotics;
	private JMenu natural;
	private JCheckBoxMenuItem Sharon;
	private JCheckBoxMenuItem Rachel;
	private JCheckBoxMenuItem Deepa;
	private ButtonGroup vGroup = null;
	private JRadioButton voice1 = new JRadioButton("Sharon", true);
	private JRadioButton voice2 = new JRadioButton("Rachel");
	private JRadioButton voice3 = new JRadioButton("Deepa");
	private JRadioButton Start = new JRadioButton("Start");
	private JRadioButton Stop = new JRadioButton("Stop");
	
	private boolean isPlayRobotics;
	private boolean isPlayNatural;
	private DownloadSpeech downloadSpeech;
	private AbacusPanel panel;
	String filenameatt;
	private JCheckBox doWeNeedFrame;
	private JCheckBox doWeNeedFingers;
	private JTextField attrTxt;

	private JButton killButton;
	// Abacus Bead Up Panel

	private InstructionPanel instructionpanel;

	private Performer performer;

	private InstructionCompiler complier;


	public TestAllAbacusComponent() throws Throwable {
		try {
			this.getContentPane().setLayout(null);
			this.setResizable(false);
			this.setTitle("Abacus. Lets start learning mind math !!!");
			this.setBounds(0, 0, 1050, 807);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setBackground(Color.WHITE);

			/* Create Abacus Panel */

			panel = new AbacusPanel();
			panel.setBounds(0, 0, this.getWidth() - 20, this.getHeight() - 400);

			instructionpanel = new InstructionPanel(null);
			instructionpanel.setBounds(10, 469, 1030, 267);

			/* Add Components to Frame */

			setupMenuBar();
			this.setJMenuBar(menuBar);

			// this.getContentPane().add(abacusTopPanel);

			this.getContentPane().add(panel);
			this.getContentPane().add(instructionpanel);
			instructionpanel.setLayout(null);

			this.setVisible(true);

			// panel.initializeAbacus();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void enableNaturalVoices(boolean enable) {
		Sharon.setEnabled(enable);
		Rachel.setEnabled(enable);
		Deepa.setEnabled(enable);

		if (enable) {
			Sharon.setSelected(true);
			Rachel.setSelected(false);
			Deepa.setSelected(false);
		} else {
			Sharon.setSelected(false);
			Rachel.setSelected(false);
			Deepa.setSelected(false);
		}
	}

	private void setupMenuBar() {
		menuBar = new JMenuBar();
		voices = new JMenu("Voices");
		voices.setMnemonic(KeyEvent.VK_V);
		menuBar.add(voices);

		/** Properties menu */

		properties = new JMenu("Properties");
		properties.setMnemonic(KeyEvent.VK_P);

		abcusproperties = new JMenuItem("Abacus properties");
		instructionproperties = new JMenuItem("Instruction properties");

		properties.add(abcusproperties);
		properties.add(instructionproperties);

		menuBar.add(properties);

		excelfile = new JMenu("excel file");
		excelfile.setMnemonic(KeyEvent.VK_L);
		
		loadexcel = new JMenuItem("Load Excel");
		excelfile.add(loadexcel);
		menuBar.add(excelfile);
		
		status = new JMenu("Status");
		start = new JMenuItem("Start");
		stop =new JMenuItem("Stop");
		status.add(start);
		status.add(stop);
		menuBar.add(status);
		
		
		attrTxt = new JTextField();
		
		// Show Abacus Button
		
		start.setEnabled(Boolean.FALSE);
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startcompilation(attrTxt.getText());
			}
		});
		
		// Load Abacus
		
		loadexcel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser jFileChooser = new JFileChooser();
					int result = jFileChooser.showOpenDialog(new JFrame());
					if (result == JFileChooser.APPROVE_OPTION) {
						File selectedFile = jFileChooser.getSelectedFile();
						attrTxt.setText(selectedFile.getAbsolutePath());
						panel.setAbacusAttributesFileName(attrTxt.getText());
						start.setEnabled(true);
					}
				} catch (Exception e1) { e1.printStackTrace(); }
			}
		});
		
		stop.setEnabled(true);
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				performer.stopPlayback();
				loadexcel.setEnabled(true);
				start.setEnabled(true);
				
				if(isPlayNatural) {
					voice1.setEnabled(true);
					voice2.setEnabled(true);
					voice3.setEnabled(true);
				}
				start.setEnabled(false);
			}
		});
		
		
		natural = new JMenu("Natural");
		natural.setMnemonic(KeyEvent.VK_N);
		natural.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				robotics.setSelected(false);
				isPlayRobotics = false;
				isPlayNatural = true;

				/** Disabling the voice selection pane */

				enableNaturalVoices(true);

				killButton.setEnabled(true);
			}
		});
		voices.add(natural);

		robotics = new JCheckBoxMenuItem("Robotics");
		robotics.setMnemonic(KeyEvent.VK_R);
		robotics.setSelected(true);
		robotics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				natural.setSelected(false);
				isPlayRobotics = true;
				isPlayNatural = false;

				/** Disabling the voice selection pane */
				enableNaturalVoices(false);

				killButton.setEnabled(false);
			}
		});
		voices.add(robotics);

		Sharon = new JCheckBoxMenuItem("Sharon");
		Sharon.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				robotics.setSelected(false);
				isPlayRobotics = false;
				isPlayNatural = true;

				/** Disabling the voice selection pane */
				Rachel.setSelected(false);
				Deepa.setSelected(false);

				killButton.setEnabled(true);
			}
		});
		natural.add(Sharon);

		Rachel = new JCheckBoxMenuItem("Rachel");
		Rachel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				robotics.setSelected(false);
				isPlayRobotics = false;
				isPlayNatural = true;

				/** Disabling the voice selection pane */
				Sharon.setSelected(false);
				Deepa.setSelected(false);

				killButton.setEnabled(true);
			}
		});
		natural.add(Rachel);

		Deepa = new JCheckBoxMenuItem("Deepa");
		Deepa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				robotics.setSelected(false);
				isPlayRobotics = false;
				isPlayNatural = true;

				/** Disabling the voice selection pane */
				Sharon.setSelected(false);
				Rachel.setSelected(false);

				killButton.setEnabled(true);
			}
		});
		natural.add(Deepa);
	}

	public void showPanel() throws IOException {
		this.setVisible(true);
		try {
			panel.initializeAbacus();
			instructionpanel.Initialize_Instruction_Panel(instructionpanel);

			// compile("/Users/Panwar/Desktop/AppInstruction.xlsx");

		} catch (AbacusException e) {
			e.printStackTrace();
		}
	}

	public void startcompilation(String filename) {
		compile(filename);
	}

	private void compile(String filename) {
		try {
			complier = new InstructionCompiler(filename);
			boolean isAllSet = complier.compileInstructions();
			if (!isAllSet) {
				JOptionPane.showMessageDialog(null, "Found Errors!!!. Please resolve!!!", "InfoBox: Abacus Compiler",
						JOptionPane.INFORMATION_MESSAGE);

				// displayErrors(complier.getMapOfErrors());

			} else {
				JOptionPane.showMessageDialog(null, "No Errors!!!.", "InfoBox: Abacus Compiler",
						JOptionPane.INFORMATION_MESSAGE);
				start_instructions(complier.getInstructionData());
			}
		} catch (CompilerException e1) {
			e1.printStackTrace();
		}
	}

	public void start_instructions(LinkedHashMap<String, HashMap<String, List<Action>>> linkedHashMap) {

		performer = new Performer();
		performer.setAbacusPanel(panel);
		performer.setInstructionPanel(instructionpanel);
		performer.setData(linkedHashMap);
		performer.setPlayRobotics(isPlayRobotics);
		performer.setPlayNatural(isPlayNatural);
		performer.startReading();
		// odel.addRow(tableRow);
	}

	/**
	 * @param args
	 * @throws Throwable
	 */
	
	public static void main(String[] args) throws Throwable {
		TestAllAbacusComponent ob = new TestAllAbacusComponent();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					ob.showPanel();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
