package com.tedu.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;

/*
 * 蓝房和黄房的共同父类
 * 
 */
public class House extends SuperElement{
    private ImageIcon img;
	
	public House(int x,int y,int w,int z,ImageIcon img){
		super(x,y,w,z);
		this.img=img;//就近原则  
		setCanPass(false);
	}
	
	public static House createHouse(int row,int col){

		ImageIcon img=
				ElementLoad.getElementLoad().getMap().get("house");//后期可以用配置文件换掉
		//为了使上走更加逼真，此处高减少了一点
		return new House(5+col*32,row*32+0,23,23,img);
	}

	@Override
	public void showElement(Graphics g) {
		// TODO Auto-generated method stub
		g.drawRect(getX(), getY(), getW(), getH());
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
