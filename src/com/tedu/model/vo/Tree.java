package com.tedu.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;

/*
 * 
 * ��
 * ��ûд����
 */
public class Tree extends SuperElement{
	
	private ImageIcon img;
	
	public Tree(int x,int y,int w,int z,ImageIcon img){
		super(x,y,w,z);
		this.img=img;//�ͽ�ԭ��  
		setCanPass(false);
		setCanDestroy(false);
	}
	
	/**
	 * 
	 * @param row ��
	 * @param col ��
	 * @return
	 */
	public static Tree createTree(int row,int col){

//		String [] arr=str.split(",");
//		int row=Integer.parseInt(arr[0]);
//		int col=Integer.parseInt(arr[1]);
		ImageIcon img=
				ElementLoad.getElementLoad().getMap().get("tree");//���ڿ����������ļ�����
		//Ϊ��ʹ���߸��ӱ��棬�˴��߼�����һ��
		return new Tree(0+col*32,row*32,32,30,img);
	}

	@Override
	public void showElement(Graphics g) {
		//TODO Ϊ�˸�����ؿ����������������⻭�˳���������Ӧɾ��
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
