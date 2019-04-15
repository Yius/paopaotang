package com.tedu.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.manager.ElementManager;

public class Boom extends SuperElement{
	private ImageIcon img;
	private int movey;
	private int time=0;
	
	public Boom(){
		super();
	}
	
	public Boom(int x, int y, int w, int h,ImageIcon img){
		super(x,y,w,h);
		this.img=img;
	}
	
	public static Boom createBoom(int x,int y,String str){
		String []arr=str.split(",");
		ImageIcon img=
				ElementLoad.getElementLoad().getMap().get(arr[2]);
		System.out.println("xxx:"+img);
		return new Boom(x,y,30,30,img);	
	}
	
	@Override
	public void showElement(Graphics g) {
		// TODO Auto-generated method stub
		g.drawRect(getX(), getY(), getW(), getH());
		g.drawImage(img.getImage(),          //����ˮ��
				getX(), getY(),             //��Ļ���Ͻ�����
				getX()+getW(),getY()+getH(),//��Ļ���½�����
				0,32*(movey+0),                        //ͼƬ���Ͻ�����
				32,32*(movey+1),                     //ͼƬ���½�����
				null);
		
		g.drawRect(getX()+25, getY(), getW(), getH());
		g.drawImage(img.getImage(),            //�Ҳ�ˮ��
				getX()+25, getY(),             
				getX()+getW()+32,getY()+getH(),
				32,32*(movey+1),                        
				64,32*(movey+2),                     
				null);
		
		g.drawRect(getX()-27, getY(), getW(), getH());
		g.drawImage(img.getImage(),            //���ˮ��
				getX()-27, getY(),             
				getX()+getW()-20,getY()+getH(),
				32,32*(movey+2),                        
				64,32*(movey+3),                     
				null);
		
		g.drawRect(getX(), getY()-25, getW(), getH());
		g.drawImage(img.getImage(),            //�ϲ�ˮ��
				getX(), getY()-25,             
				getX()+getW(),getY()+getH()-25,
				32,32*(movey+3),                        
				64,32*(movey+4),                     
				null);
		
		g.drawRect(getX(), getY()+25, getW(), getH());
		g.drawImage(img.getImage(),            //�²�ˮ��
				getX(), getY()+25,             
				getX()+getW(),getY()+getH()+25,
				32,32*(movey+4),                        
				64,32*(movey+5),                     
				null);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void destroy() {
		setTime(time++);
		if(getTime()==8) {
			setVisible(false);
		}
	}
}
