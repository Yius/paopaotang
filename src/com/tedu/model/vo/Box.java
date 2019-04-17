package com.tedu.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.manager.ElementFactory;

public class Box extends SuperElement{
	private ImageIcon img;
	
	public Box(int x,int y,int w,int z,ImageIcon img){
		super(x,y,w,z);
		this.img=img;//�ͽ�ԭ��  
	}
	
	public static Box createBox(int row,int col){
		
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
		if(!isVisible()) {
			int[][] floor = ElementLoad.getElementLoad().getFloor();
			if(Math.random()< 1/3.0||true) {
				floor[getY()/32][getX()/32] = /*r.nextInt(8) + */602; 
				//TODO ע��������������в�����
				ElementFactory.createTool(floor[getY()/32][getX()/32], getY()/32,getX()/32);
			}else {
				floor[getY()/32][getX()/32] = 0;
			}
		}
	}

}
