package com.app.instruction.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.app.instruction.panel.InstructionPanel;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JButton;



public class TestInstructionComponent extends JFrame {

	private JPanel contentPane;
	private InstructionPanel panel;
	private final Action action = new SwingAction();
	private JTextField textField;

	/**
	 * Launch the application.
	 * @throws Throwable 
	 */
	public static void main(String[] args) throws Throwable {
		TestInstructionComponent ob = new TestInstructionComponent();
		
		SwingUtilities.invokeLater(new Runnable() {
		   public void run() {
			   try {
				ob.show_panel();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
		});
	}

	/**
	 * Create the frame.
	 * @throws Throwable 
	 */
	public TestInstructionComponent() throws Throwable {
		this.setBounds(1, 1, 811, 600);
		panel = new InstructionPanel(null);
		panel.setBounds(0, 0,805, 395);
		//this.getContentPane().add(panel);
		
		this.getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel controllpanel = new JPanel();
		controllpanel.setBounds(10, 404, 795, 125);
		getContentPane().add(controllpanel);
		controllpanel.setLayout(null);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Thinking");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		chckbxNewCheckBox.setAction(action);
		chckbxNewCheckBox.setBounds(4, 71, 128, 23);
		controllpanel.add(chckbxNewCheckBox);
		
		textField = new JTextField();
		textField.setBounds(224, 70, 335, 26);
		controllpanel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Tutor");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.MakeTutorSay(textField.getText(), panel);
			}
		});
		btnNewButton.setBounds(77, 18, 117, 29);
		controllpanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Student");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.MakeStudentSay(textField.getText(),panel);
			}
		});
		btnNewButton_1.setBounds(599, 18, 117, 29);
		controllpanel.add(btnNewButton_1);
		
		
		
	}

	public void show_panel() throws IOException
	{
		this.setVisible(true);
		
		
		panel.Initialize_Instruction_Panel(panel);
		//panel.repaint();
		//this.panel.setVisible(true);
		
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
