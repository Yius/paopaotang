package com.tedu.model.vo;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;

public class Boom extends SuperElement{
	private ImageIcon img;
	private int time=0;
	private int scale = 0;

	public Boom(){
		super();
	}
	
	public Boom(int x, int y, int w, int h,ImageIcon img,int scale){
		super(x,y,w,h);
		this.img=img;
		this.scale = scale;
	}
	
	public static Boom createBoom(int x,int y,String str,int scale){
		String []arr=str.split(",");
		ImageIcon img=
				ElementLoad.getElementLoad().getMap().get(arr[2]);
		return new Boom(x,y,32,32,img,scale);	
	}
	
	@Override
	public void showElement(Graphics g) {
		// TODO Auto-generated method stub
//		g.drawRect(getX(), getY(), getW(), getH());
		g.drawImage(img.getImage(),          //����ˮ��
				getX(), getY(),             //��Ļ���Ͻ�����
				getX()+getW(),getY()+getH(),//��Ļ���½�����
				0,32*0,                        //ͼƬ���Ͻ�����
				32,32*1,                     //ͼƬ���½�����
				null);
		
//		g.drawRect(getX()+25+scale*32, getY(), getW(), getH());
		g.drawImage(img.getImage(),            //�Ҳ�ˮ��
				getX()+25+scale*32, getY(),             
				getX()+getW()+32+scale*32,getY()+getH(),
				32,32*1,                        
				64,32*2,                     
				null);
		
//		g.drawRect(getX()-27-scale*32, getY(), getW(), getH());
		g.drawImage(img.getImage(),            //���ˮ��
				getX()-27-scale*32, getY(),             
				getX()+getW()-20-scale*32,getY()+getH(),
				32,32*2,                        
				64,32*3,                     
				null);
		
//		g.drawRect(getX(), getY()-25-scale*32, getW(), getH());
		g.drawImage(img.getImage(),            //�ϲ�ˮ��
				getX(), getY()-25-scale*32,             
				getX()+getW(),getY()+getH()-25-scale*32,
				32,32*3,                        
				64,32*4,                     
				null);
		
//		g.drawRect(getX(), getY()+25+scale*32, getW(), getH());
		g.drawImage(img.getImage(),            //�²�ˮ��
				getX(), getY()+25+scale*32,             
				getX()+getW(),getY()+getH()+25+scale*32,
				32,32*4,                        
				64,32*5,                     
				null);
		//����ķ�Χ
		for(int i=0;i<scale;++i) {
//			g.drawRect(getX()+25+i*32, getY(), getW(), getH());
			g.drawImage(img.getImage(),            //�Ҳ�
					getX()+25+i*32, getY(),             
					getX()+getW()+32+i*32,getY()+getH(),
					0,32*1,                        
					32,32*2,                     
					null);
			
//			g.drawRect(getX()-27-i*32, getY(), getW(), getH());
			g.drawImage(img.getImage(),            //���
					getX()-27-i*32, getY(),             
					getX()+getW()-20-i*32,getY()+getH(),
					0,32*2,                        
					32,32*3,                     
					null);
			
//			g.drawRect(getX(), getY()-25-i*32, getW(), getH());
			g.drawImage(img.getImage(),            //�ϲ�
					getX(), getY()-25-i*32,             
					getX()+getW(),getY()+getH()-25-i*32,
					0,32*3,                        
					32,32*4,                     
					null);
			
//			g.drawRect(getX(), getY()+25+i*32, getW(), getH());
			g.drawImage(img.getImage(),            //�²�
					getX(), getY()+25+i*32,             
					getX()+getW(),getY()+getH()+25+i*32,
					0,32*4,                        
					32,32*5,                     
					null);
		}
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		++time;
	}

	
	@Override
	public void destroy() {
		if(time==20) {
			time = 0;
			setVisible(false);
		}
	}
	
	//����ը��
	public boolean gamePK(SuperElement se) {
		if(isVisible()){	
			Rectangle r3 = new Rectangle(se.getX(),se.getY(),se.getW(),se.getH());
			Rectangle r1 = new Rectangle(getX()-27-scale*32,getY(),getW()+52+scale*2*32,getH());
			Rectangle r2 = new Rectangle(getX(),getY()-25-scale*32,getW(),getH()+50+scale*2*32);
			return r1.intersects(r3)||r2.intersects(r3);
		}
		return false;
	}
	
}
