package com.app.instruction.main;

/**
 * 
 */
import java.awt.Color;
import java.awt.GridLayout;
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
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.app.abacus.panel.AbacusPanel;
import com.app.abacus.panel.exception.AbacusException;
import com.app.instruction.panel.InstructionPanel;
import com.app.instructions.compiler.Action;
import com.app.instructions.compiler.InstructionCompiler;
import com.app.instructions.compiler.exception.CompilerException;
import com.app.integrated.main.Performer;
import com.app.sound.DownloadSpeech;

/**
 * @author prashant.joshi
 *
 */
public class Demo extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	// Top panels
	
	private String abacusPropertiesFilePath;
	private String instructionPropertiesFilePath;
	private JPanel abacusTopPanel;
	private JMenuBar menuBar;
	private JMenu menu1, menu2;
	private JMenuItem menuItem1,menuItem2;
	private JCheckBoxMenuItem robotics;
	private JMenu natural;
	private JCheckBoxMenuItem Sharon;
	private JCheckBoxMenuItem Rachel;
	private JCheckBoxMenuItem Deepa;
	private ButtonGroup vGroup = null;
	private JRadioButton voice1 = new JRadioButton("Sharon", true);
    private JRadioButton voice2 = new JRadioButton("Rachel");
    private JRadioButton voice3 = new JRadioButton("Deepa");
    private boolean isPlayRobotics;
	private boolean isPlayNatural;
	private DownloadSpeech downloadSpeech;
	
	
	private AbacusPanel panel;
	private JPanel playPanel;
	String filenameatt;
	
	//Abacus Top Panel;
	
	private JCheckBox doWeNeedFrame;
	private JCheckBox doWeNeedFingers;
	private JTextField attrTxt;
	private JButton loadAbacus;
	private JButton showAbacus;
	private JButton stopAbacus;
	private JButton killButton;
	private InstructionPanel instructionpanel;
	
	private Performer performer;

	private InstructionCompiler complier;
	
	public Demo() throws Throwable {
		try {
			this.getContentPane().setLayout(null);
			this.setResizable(false);
			this.setTitle("Abacus. Lets start learning mind math !!!");
			this.setBounds(100, 100, 1050, 807);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setBackground(Color.WHITE);
			
			/* Create Abacus Panel */
			
			panel = new AbacusPanel();
			panel.setBounds(10, 50, this.getWidth() - 20, this.getHeight() - 400);
			
			setUpAbacusTopPanel();
			
			instructionpanel = new InstructionPanel(null);
			instructionpanel.setBounds(20, 469,1030,267);
			
			/* Create Abacus Play Controls */
			
			setupPlayPanel();
			
			/* Add Components to Frame */
			setupMenuBar();
			this.setJMenuBar(menuBar);
			this.getContentPane().add(abacusTopPanel);
			this.getContentPane().add(panel);
			this.getContentPane().add(instructionpanel);
			instructionpanel.setLayout(null);
			playPanel.repaint();
			
			this.setVisible(true);
			
			panel.initializeAbacus();
			
		} catch ( Exception e ) { e.printStackTrace(); }
	}
	
	private void enableNaturalVoices(boolean enable) {
		Sharon.setEnabled(enable);
		Rachel.setEnabled(enable);
		Deepa.setEnabled(enable);
		
		if(enable) {
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
		menu1 = new JMenu("Voices");
		menu1.setMnemonic(KeyEvent.VK_V);
		menuBar.add(menu1);
	
		/** Properties menu */
		
		menu2 = new JMenu("Properties");
		menu2.setMnemonic(KeyEvent.VK_P);
		
		menuItem1 = new JMenuItem("Abacus properties");
		menuItem2 = new JMenuItem("Instruction properties");
		
		menu2.add(menuItem1);
		menu2.add(menuItem2);
		
		menuBar.add(menu2);
	
		
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
		menu1.add(natural);
		
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
		menu1.add(robotics);
		
		Sharon = new JCheckBoxMenuItem("Sharon");
		Sharon.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				robotics.setSelected(false);
				isPlayRobotics = false;
				isPlayNatural = true;
				
				/** Disabling the voice selection pane */
				Rachel.setSelected(false);
				Deepa.setSelected(false);
				
				
				//killButton.setEnabled(true);
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
				
				
				//killButton.setEnabled(true);
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
				
				
				//killButton.setEnabled(true);
			}
		});
		natural.add(Deepa);
	}
	
	public void showPanel() throws IOException {
		this.setVisible(true);
		try {
			panel.initializeAbacus();
			instructionpanel.Initialize_Instruction_Panel(instructionpanel);
			//compile("/Users/Panwar/Desktop/AppInstruction.xlsx");
		} catch (AbacusException e) { e.printStackTrace(); }
	}
	
	private void setupPlayPanel() {
		playPanel = new JPanel(new GridLayout(3, 6));
		
		playPanel.setBounds(0, this.getHeight() - 130, this.getWidth(), 90);
	}
	
	private void setUpAbacusTopPanel() {
		abacusTopPanel = new JPanel(new GridLayout(1, 5));
		abacusTopPanel.setBounds(0, 10, this.getWidth(), 40);
		
		// Buttons
		
		loadAbacus = new JButton("Load Abacus Instructions");
		showAbacus = new JButton("Start");
		stopAbacus = new JButton("Stop");
		
		// Frame
		
		doWeNeedFrame = new JCheckBox("Show Frame");
		
		doWeNeedFrame.setSelected(true);
		doWeNeedFrame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(doWeNeedFrame.isSelected()) {
					panel.hideFrame(Boolean.TRUE);
				} else {
					panel.hideFrame(Boolean.FALSE);
				}
			}
		});
		
		// Fingers
		doWeNeedFingers = new JCheckBox("Show Fingers");
		doWeNeedFingers.setSelected(true);
		doWeNeedFingers.addActionListener(new ActionListener() {
			@Override
		  public void actionPerformed(ActionEvent e) {
				if(doWeNeedFingers.isSelected()) {
					panel.hideFingers(Boolean.TRUE);
				} else {
					panel.hideFingers(Boolean.FALSE);
				}
			}
		});
		
		attrTxt = new JTextField();
		
		// Show Abacus Button
		
		showAbacus.setEnabled(Boolean.FALSE);
		showAbacus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startcompilation(attrTxt.getText());
			}
		});
		
		// Load Abacus
		loadAbacus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser jFileChooser = new JFileChooser();
					int result = jFileChooser.showOpenDialog(new JFrame());
					if (result == JFileChooser.APPROVE_OPTION) {
						File selectedFile = jFileChooser.getSelectedFile();
						attrTxt.setText(selectedFile.getAbsolutePath());
						panel.setAbacusAttributesFileName(attrTxt.getText());
						showAbacus.setEnabled(true);
					}
				} catch (Exception e1) { e1.printStackTrace(); }
			}
		});
		
		
		// Add components in Panel
		abacusTopPanel.add(doWeNeedFrame);
		abacusTopPanel.add(doWeNeedFingers);
		
		killButton = new JButton("Kill Demo");
		killButton.setBounds(945, 10, 90, 40);
		killButton.setEnabled(false);
		killButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					panel.initializeAbacus();
				} catch (AbacusException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//performer.stopPlayback();
				//fileButton.setEnabled(true);
				//startButton.setEnabled(true);
				
				if(isPlayNatural) {
					voice1.setEnabled(true);
					voice2.setEnabled(true);
					voice3.setEnabled(true);
				}
				
				//startButton.setEnabled(false);
			}
		}); 
			
		abacusTopPanel.add(attrTxt);
		abacusTopPanel.add(loadAbacus);
		abacusTopPanel.add(showAbacus);
	}
	
	public void startcompilation(String filename)
	{
		compile(filename);	
	}
	
	private void compile(String filename) 
	{
		try {
			complier = new InstructionCompiler(filename);
			boolean isAllSet = complier.compileInstructions();
			if(!isAllSet) {
				JOptionPane.showMessageDialog(null, "Found Errors!!!. Please resolve!!!", "InfoBox: Abacus Compiler", JOptionPane.INFORMATION_MESSAGE);
				//displayErrors(complier.getMapOfErrors());
			} else {
				JOptionPane.showMessageDialog(null, "No Errors!!!.", "InfoBox: Abacus Compiler", JOptionPane.INFORMATION_MESSAGE);
				start_instructions(complier.getInstructionData());
			}
		} catch (CompilerException e1) { e1.printStackTrace(); }
	}
	
	public void start_instructions(LinkedHashMap<String, HashMap<String, List<Action>>> linkedHashMap)
	{
		
		performer = new Performer();
		performer.setAbacusPanel(panel);
		performer.setInstructionPanel(instructionpanel);
		performer.setData(linkedHashMap);
		performer.setPlayRobotics(isPlayRobotics);
		performer.setPlayNatural(isPlayNatural);
		performer.startReading();
		//odel.addRow(tableRow);
			}

	/**
	 * @param args
	 * @throws Throwable 
	 */
	public static void main(String[] args) throws Throwable {
		Demo ob = new Demo();
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
