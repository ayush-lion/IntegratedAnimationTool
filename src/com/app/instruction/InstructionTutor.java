package com.app.instruction;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class InstructionTutor {
	
	
	//class for instruction structure
	
	private String ins_text;
	private String ins_shape;
	private Image image;
	private  int posX;
	private int posY;
	private int width;
	private boolean switchable;
	/**
	 * @return the switchable
	 */
	public boolean isSwitchable() {
		return switchable;
	}


	/**
	 * @param switchable the switchable to set
	 */
	public void setSwitchable(boolean switchable) {
		this.switchable = switchable;
	}


	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}


	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}


	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}


	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	private int height;
	private boolean isthinking;
	
	
	

	
	
	/**
	 * @return the posX
	 */
	public int getPosX() {
		return posX;
	}


	/**
	 * @param posX the posX to set
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}


	/**
	 * @return the posY
	 */
	public int getPosY() {
		return posY;
	}


	/**
	 * @param posY the posY to set
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}


	/**
	 * @return the isthinking
	 */
	public boolean isIsthinking() {
		return isthinking;
	}


	/**
	 * @param isthinking the isthinking to set
	 */
	public void setIsthinking(boolean isthinking) {
		this.isthinking = isthinking;
	}


	
	
	public InstructionTutor()
	{
		//default constructor
		
	}
	
	
	public void draw_instruction(Graphics g)
	
	{	
		if(isSwitchable())
		{
			
			
			
			ArrayList<String> data = new ArrayList<String>();
			data = fragmentText(getIns_text(),20);
			data.add("   ");
			//String padded = String.format("%-20s", getIns_text());
			g.drawImage(getImage(),getPosX(), getPosY(), getWidth(),getHeight(), null);
			for(int i=0; i<data.size();i++)
			{
				
				int y = getPosY()+35;
				g.setColor(Color.BLACK);
				if(i==0)
				{

					g.drawString(data.get(i), getPosX()+15,y);
						
				}
				else
				{
					g.drawString(data.get(i), getPosX()+15, y+i*15);
				}
				
			}
			
		}
		else
		{

			//g.setColor(Color.TRANSLUCENT);

			g.setColor(Color.WHITE);
			
			 g.drawRect(getPosX(), getPosY(), getWidth(), getHeight());
		}
	
		
		
	}
	private ArrayList<String> fragmentText(String text, int maxWidth) {
	    ArrayList<String> lines = new ArrayList<String>();
	    String line = "";
	    if (text.length() < maxWidth) {
	    	lines.add(text);
	        return lines;
	    }
	    
	    String[] words = text.split(" ");
	    boolean isAdded = false;
	    for (String word : words) {
	    	String txt = line + word + " ";
	    	int len = txt.length();
	    	if(len >= maxWidth) {
	    		isAdded = true;
	    		lines.add(line);
	    		line = word + " ";
	    		txt = "";
	    	} else {
	    		isAdded = false;
	    		line = txt;
	    	}
		}
	    if(!isAdded) {
	    	lines.add(line);
	    }
	    
	    return lines;
	}
	
	public Image getImage() {
		return this.image;
	}


	public void setImage(Image image) {
		this.image = image;
	}


	public String getIns_shape() {
		return this.ins_shape;
	}
	
	public String getIns_text() {
		return this.ins_text;
	}
	
	public void setIns_shape(String ins_shape) {
		this.ins_shape = ins_shape;
	}
	
	public void setIns_text(String ins_text) {
		this.ins_text = ins_text;
	}
	
	

}
