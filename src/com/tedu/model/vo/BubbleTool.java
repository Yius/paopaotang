package com.tedu.model.vo;

import java.awt.Graphics;

import com.tedu.model.load.ElementLoad;

public class BubbleTool extends SuperElement{
	
	private int movex;
	private int time = 0;
	private int deadTime = 300; //������ʧʱ��
	
	public static BubbleTool createBubbleTool(int row,int col) {
		return new BubbleTool(col*32+6,row*32+6,20,20);
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
//		g.drawRect(getX(), getY(), getW(), getH());
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
			time = 0;//��ֹѭ��̫��ʹ��Bubbleδ���Ƴ���list֮ǰtime����int��Χ
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
		if(!isVisible()) {
			int[][] floor = ElementLoad.getElementLoad().getFloor();
			floor[getY()/32][getX()/32] = 0;
		}	
	}

}
