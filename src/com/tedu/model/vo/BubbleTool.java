package com.tedu.model.vo;

import java.awt.Graphics;

public class BubbleTool extends SuperElement{
	
	private int movex;
	private int time = 0;
	private int deadTime = 700; //道具消失时间
	
	public static BubbleTool createBubbleTool(int row,int col) {
		return new BubbleTool(col*32,row*32,32,32);
	}

	public BubbleTool() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BubbleTool(int x, int y, int w, int h) {
		super(x, y, w, h);
		setRow(y/32);
		setCol(x/32);
	}

	@Override
	public void showElement(Graphics g) {	
		g.drawRect(getX(), getY(), getW(), getH());
	}

	@Override
	public void move() {
		movex%=3;
		++movex;	
		diedTimeCountDown();
	}

	private void diedTimeCountDown() {
		++time;
		if(time == deadTime) {
			time = 0;//防止循环太快使得Bubble未被移除出list之前time超出int范围
			setVisible(false);
		}		
	}


	public int getMovex() {
		return movex;
	}

	public void setMovex(int movex) {
		this.movex = movex;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
