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
		g.drawImage(img.getImage(),          //中心水花
				getX(), getY(),             //屏幕左上角坐标
				getX()+getW(),getY()+getH(),//屏幕右下角坐标
				0,32*0,                        //图片左上角坐标
				32,32*1,                     //图片右下角坐标
				null);
		
//		g.drawRect(getX()+25+scale*32, getY(), getW(), getH());
		g.drawImage(img.getImage(),            //右侧水柱
				getX()+25+scale*32, getY(),             
				getX()+getW()+32+scale*32,getY()+getH(),
				32,32*1,                        
				64,32*2,                     
				null);
		
//		g.drawRect(getX()-27-scale*32, getY(), getW(), getH());
		g.drawImage(img.getImage(),            //左侧水柱
				getX()-27-scale*32, getY(),             
				getX()+getW()-20-scale*32,getY()+getH(),
				32,32*2,                        
				64,32*3,                     
				null);
		
//		g.drawRect(getX(), getY()-25-scale*32, getW(), getH());
		g.drawImage(img.getImage(),            //上侧水柱
				getX(), getY()-25-scale*32,             
				getX()+getW(),getY()+getH()-25-scale*32,
				32,32*3,                        
				64,32*4,                     
				null);
		
//		g.drawRect(getX(), getY()+25+scale*32, getW(), getH());
		g.drawImage(img.getImage(),            //下侧水柱
				getX(), getY()+25+scale*32,             
				getX()+getW(),getY()+getH()+25+scale*32,
				32,32*4,                        
				64,32*5,                     
				null);
		//增大的范围
		for(int i=0;i<scale;++i) {
//			g.drawRect(getX()+25+i*32, getY(), getW(), getH());
			g.drawImage(img.getImage(),            //右侧
					getX()+25+i*32, getY(),             
					getX()+getW()+32+i*32,getY()+getH(),
					0,32*1,                        
					32,32*2,                     
					null);
			
//			g.drawRect(getX()-27-i*32, getY(), getW(), getH());
			g.drawImage(img.getImage(),            //左侧
					getX()-27-i*32, getY(),             
					getX()+getW()-20-i*32,getY()+getH(),
					0,32*2,                        
					32,32*3,                     
					null);
			
//			g.drawRect(getX(), getY()-25-i*32, getW(), getH());
			g.drawImage(img.getImage(),            //上侧
					getX(), getY()-25-i*32,             
					getX()+getW(),getY()+getH()-25-i*32,
					0,32*3,                        
					32,32*4,                     
					null);
			
//			g.drawRect(getX(), getY()+25+i*32, getW(), getH());
			g.drawImage(img.getImage(),            //下侧
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
	
	//泡泡炸物
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
