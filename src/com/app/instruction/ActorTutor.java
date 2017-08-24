package com.app.instruction;

import java.awt.Graphics;
import java.awt.Image;

public class ActorTutor {
	
	
	 private Image image;
	 private int id;
	 private boolean isthinking;
	 private int posX;
	 private int posY;
	 private int width;
	 private int height;
	 
	 
	 public void drawActor(Graphics g) {
			
				g.drawImage(getImage(), getPosX(), getPosY(), getWidth(), getHeight(), null);
		}
	 
	
	public int getPosX() {
		return this.posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return this.posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getId() {
		return this.id;
	}
	 
	public void setImage(Image image) {
		this.image = image;
	}
	
	public Image getImage() {
		return this.image;
	}
	 
	 public void setId(int id) {
		this.id = id;
	}
	

	public ActorTutor()
	{
		
	}

}
