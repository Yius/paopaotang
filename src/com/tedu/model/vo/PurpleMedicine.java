package com.tedu.model.vo;

import java.awt.Graphics;

import com.tedu.model.load.ElementLoad;

public class PurpleMedicine extends SuperElement{
	private int moveX;
	private int time = 0;
	private int deadTime = 700;
	 

	public PurpleMedicine(int x, int y, int w, int h) {
		super(x, y, w, h);
		setRow(y/32);
		setCol(x/32);
	}
	public static PurpleMedicine createPurpleMedicine(int row, int col) {
		return new PurpleMedicine(col*32,row*32,32,32);
	}

	@Override
	public void showElement(Graphics g) {
		g.drawRect(getX()+6, getY()+6, getW()-12, getH()-12);
	}

	@Override
	public void move() {
		// TODO 自动生成的方法存根
		moveX++;
		moveX%=4;
		diedTimeCountDown();
	}
	private void diedTimeCountDown() {
		++time;
		if(time == deadTime) {
			time = 0;//防止循环太快使得Bubble未被移除出list之前time超出int范围
			setVisible(false);
		}		
	}

	@Override
	public void destroy() {
		// TODO 自动生成的方法存根
		if(!isVisible()) {
			int[][] floor = ElementLoad.getElementLoad().getFloor();
			floor[getY()/32][getX()/32] = 0;
		}
	}

}
