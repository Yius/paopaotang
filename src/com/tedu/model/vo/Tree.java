package com.tedu.model.vo;

import java.awt.Graphics;

/*
 * 
 * 树
 * 还没写完整
 */
public class Tree extends SuperElement{
	
	public Tree(int x,int y,int w,int z){
		super(x,y,w,z);
		setCanDestroy(false);
	}
	
	/**
	 * 
	 * @param row 行
	 * @param col 列
	 * @return
	 */
	public static Tree createTree(int row,int col){

//		String [] arr=str.split(",");
//		int row=Integer.parseInt(arr[0]);
//		int col=Integer.parseInt(arr[1]);
		//为了使上走更加逼真，此处高减少了一点
		return new Tree(0+col*32,row*32,32,30);
	}

	@Override
	public void showElement(Graphics g) {
		//TODO 为了更清楚地看到树的轮廓，特意画了出来，后期应删除
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
