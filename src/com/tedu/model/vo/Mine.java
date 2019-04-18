package com.tedu.model.vo;

import java.awt.Graphics;

import com.tedu.model.load.ElementLoad;

public class Mine extends SuperElement{
	private int moveX;
	private int time = 0;
	private int deadTime = 700;
	
	public Mine(int x, int y, int w, int h) {
		super(x, y, w, h);
		setRow(y/32);
		setCol(x/32);
	}
	
	public static Mine createMine(int row, int col) {
		return new Mine(col*32, row*32,32,32);
	}

	@Override
	public void showElement(Graphics g) {
		// TODO �Զ����ɵķ������
		g.drawRect(getX()+6, getY()+6, getW()-12, getH()-12);
	}

	@Override
	public void move() {
		moveX++;
		moveX%=4;
		diedTimeCountDown();
	}
	
	private void diedTimeCountDown() {
		++time;
		if(time == deadTime) {
			time = 0;//��ֹѭ��̫��ʹ��Bubbleδ���Ƴ���list֮ǰtime����int��Χ
			setVisible(false);
		}		
	}

	@Override
	public void destroy() {
		// TODO �Զ����ɵķ������
		if(!isVisible()) {
			int[][] floor = ElementLoad.getElementLoad().getFloor();
			floor[getY()/32][getX()/32] = 0;
		}
	}

}