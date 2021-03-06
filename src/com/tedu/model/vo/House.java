package com.tedu.model.vo;

import java.awt.Graphics;

/*
 * 蓝房和黄房的共同父类
 * 
 */
public class House extends SuperElement{
	
	public House(int x,int y,int w,int z){
		super(x,y,w,z);
		setCanDestroy(false);
	}
	
	public static House createHouse(int row,int col){

		//为了使上走更加逼真，此处高减少了一点
		return new House(5+col*32,row*32+2,23,23);
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
