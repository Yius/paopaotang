package com.tedu.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;

/*
 * �����ͻƷ��Ĺ�ͬ����
 * 
 */
public class House extends SuperElement{
    private ImageIcon img;
	
	public House(int x,int y,int w,int z,ImageIcon img){
		super(x,y,w,z);
		this.img=img;//�ͽ�ԭ��  
		setCanPass(false);
	}
	
	public static House createHouse(int row,int col){

		ImageIcon img=
				ElementLoad.getElementLoad().getMap().get("house");//���ڿ����������ļ�����
		//Ϊ��ʹ���߸��ӱ��棬�˴��߼�����һ��
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
