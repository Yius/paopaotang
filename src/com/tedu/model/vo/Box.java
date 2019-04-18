package com.tedu.model.vo;

import java.awt.Graphics;
import java.util.Random;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.manager.ElementFactory;

public class Box extends SuperElement{
	
	public Box(int x,int y,int w,int z){
		super(x,y,w,z);
	}
	
	public static Box createBox(int row,int col){
		
		//为了使上走更加逼真，此处高减少了一点
		return new Box(5+col*32,row*32+5,20,20);
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
		if(!isVisible()) {
			int[][] floor = ElementLoad.getElementLoad().getFloor();
			Random r = new Random();
			if(Math.random()< 1/3.0) {
				floor[getY()/32][getX()/32] = r.nextInt(8) + 601; 
				//TODO 注意下面这个方法尚不完善
				ElementFactory.createTool(floor[getY()/32][getX()/32], getY()/32,getX()/32);
			}else {
				floor[getY()/32][getX()/32] = 0;
			}
		}
	}

}
