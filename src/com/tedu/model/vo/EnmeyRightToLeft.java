package com.tedu.model.vo;

import javax.swing.ImageIcon;

public class EnmeyRightToLeft extends Enemy{

	public EnmeyRightToLeft(int x, int y, int w, int h, ImageIcon img) {
		super(x, y, w, h, img);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void move() {
		setX(getX()-5);
		if(getX()<0){
			this.setVisible(false);
		}
	}
}
