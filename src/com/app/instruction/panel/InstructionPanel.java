package com.app.instruction.panel;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.app.instruction.InstructionController;

import java.awt.Color;

public class InstructionPanel extends JPanel {
	

	
	HashMap<String,String> initial_provider = new  HashMap<String,String>();
	InstructionController controller;
	Image tutor_image;
	Image student_image;
	Image tutor_ins_image;
	Image student_ins_image;
	String tutor_ins;
	String student_ins;
	boolean isprovided = false;
	String[] listOfHtmlChars = {"<B>","</B>","<b>","</b>","<U>","</U>","<u>","</u>","<I>","</I>","<i>","</i>","<font color=\"red\">","<font color=\"cyan\">","</font>"};
	
	
	
	public Image getTutor_image() {
		return this.tutor_image;
	}

	public void setTutor_image(Image tutor_image) {
		this.tutor_image = tutor_image;
	}

	public Image getStudent_image() {
		return this.student_image;
	}

	public void setStudent_image(Image student_image) {
		this.student_image = student_image;
	}

	public Image getTutor_ins_image() {
		return this.tutor_ins_image;
	}

	public void setTutor_ins_image(Image tutor_ins_image) {
		this.tutor_ins_image = tutor_ins_image;
	}

	public Image getStudent_ins_image() {
		return this.student_ins_image;
	}

	public void setStudent_ins_image(Image student_ins_image) {
		this.student_ins_image = student_ins_image;
	}

	public String getTutor_ins() {
		return this.tutor_ins;
	}

	public void setTutor_ins(String tutor_ins) {
		this.tutor_ins = tutor_ins;
	}

	public String getStudent_ins() {
		return this.student_ins;
	}

	public void setStudent_ins(String student_ins) {
		this.student_ins = student_ins;
	}

	public InstructionPanel( String filename) throws Throwable
	{
		setBackground(Color.WHITE);
		if(filename!=null)
		{
			controller = new InstructionController(this, filename);
			//this.repaint();
		
		}
		else
		{
			controller = new InstructionController(this);
			
		}
		
	}
	
	
	public void Initialize_Parameter() throws IOException
	{
		
	}
	
	public void Initialize_Instruction_Panel(InstructionPanel panel) throws IOException
	{	
		panel.repaint();
	}
	
	public void Initialize_Init()
	{
	
		
		
	}
	
	public void performinstruction(String text, InstructionPanel panel)
	{
		if(text.startsWith("T:"))
		{
			
			MakeTutorSay(replaceHTMLCharacters(text),panel);
		}
		else
		{
			MakeStudentSay(replaceHTMLCharacters(text), panel);
		}
	}
	
	public void MakeTutorSay(String text, InstructionPanel panel)
	{
		
		controller.TutorThinking(text);
		panel.repaint();
		
	}
	public void MakeStudentSay(String text,InstructionPanel panel)
	{
		
		controller.StudentThinking(text);
		panel.repaint();
	}
	
	private Image getImage(String fileName) throws IOException {
		Image image = null;
		if(!isprovided) {
			image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(fileName));

			//System.out.println("from init_image"+image.toString());
		} else {
			image = ImageIO.read(new File(fileName));
		}
		return image;
	}
	
	private String replaceHTMLCharacters(String instruction) {
		for (String htmlChar : listOfHtmlChars) {
			instruction = instruction.replaceAll(htmlChar, "");
		}
		return instruction;
	}
	
	@Override
	public void paint(Graphics g) {
		//super.paint(g);
		System.out.println("got it");
		controller.DrawInstructionPanel(g);
	}
		
}
