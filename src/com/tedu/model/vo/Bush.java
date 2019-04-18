package com.tedu.model.vo;

import java.awt.Graphics;

public class Bush extends SuperElement{
	
	
	public Bush(int x,int y,int w,int z) {
		super(x,y,w,z);
		setCanDestroy(false);
	}

	public static Bush createBush(int row,int col){
		
		return new Bush(0+col*32,row*32,30,30);
	}
	
	@Override
	public void showElement(Graphics g) {
		// TODO Auto-generated method stub
//		g.drawRect(getX(), getY(), getW(), getH());
		
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	
}
