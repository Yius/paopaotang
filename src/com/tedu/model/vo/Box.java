package com.tedu.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;

public class Box extends SuperElement{
	private ImageIcon img;
	
	public Box(int x,int y,int w,int z,ImageIcon img){
		super(x,y,w,z);
		this.img=img;//�ͽ�ԭ��  
		setCanPass(false);
	}
	
	public static Box createBox(int row,int col){
//		playerA,playFire,150,300,40,40

//		String [] arr=str.split(",");
//		int row=Integer.parseInt(arr[0]);
//		int col=Integer.parseInt(arr[1]);
		ImageIcon img=
				ElementLoad.getElementLoad().getMap().get("box");//���ڿ����������ļ�����
		//Ϊ��ʹ���߸��ӱ��棬�˴��߼�����һ��
		return new Box(5+col*32,row*32+5,20,20,img);
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
