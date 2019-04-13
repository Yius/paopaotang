package com.tedu.model.vo;

import javax.swing.ImageIcon;

public class EnemyLeftToRight extends Enemy{

	public EnemyLeftToRight(int x, int y, int w, int h, ImageIcon img) {
		super(x, y, w, h, img);
	}
	
	@Override
	public void move() {
		setX(getX()+5);
		if(getX()>300){
			this.setVisible(false);
		}
	}
	
}
