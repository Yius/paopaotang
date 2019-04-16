package com.tedu.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.manager.ElementManager;

public class Boom extends SuperElement{
	private ImageIcon img;
	private int movey;
	private int time=0;
	private int flag;
	
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

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
		return new Boom(x,y,30,30,img);	
	}
	
	@Override
	public void showElement(Graphics g) {
		// TODO Auto-generated method stub
		g.drawRect(getX(), getY(), getW(), getH());
		g.drawImage(img.getImage(),          //中心水花
				getX(), getY(),             //屏幕左上角坐标
				getX()+getW(),getY()+getH(),//屏幕右下角坐标
				0,32*(movey+0),                        //图片左上角坐标
				32,32*(movey+1),                     //图片右下角坐标
				null);
		
		g.drawRect(getX()+25, getY(), getW(), getH());
		g.drawImage(img.getImage(),            //右侧水柱
				getX()+25, getY(),             
				getX()+getW()+32,getY()+getH(),
				32,32*(movey+1),                        
				64,32*(movey+2),                     
				null);
		
		g.drawRect(getX()-27, getY(), getW(), getH());
		g.drawImage(img.getImage(),            //左侧水柱
				getX()-27, getY(),             
				getX()+getW()-20,getY()+getH(),
				32,32*(movey+2),                        
				64,32*(movey+3),                     
				null);
		
		g.drawRect(getX(), getY()-25, getW(), getH());
		g.drawImage(img.getImage(),            //上侧水柱
				getX(), getY()-25,             
				getX()+getW(),getY()+getH()-25,
				32,32*(movey+3),                        
				64,32*(movey+4),                     
				null);
		
		g.drawRect(getX(), getY()+25, getW(), getH());
		g.drawImage(img.getImage(),            //下侧水柱
				getX(), getY()+25,             
				getX()+getW(),getY()+getH()+25,
				32,32*(movey+4),                        
				64,32*(movey+5),                     
				null);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		++time;
	}

	
	@Override
	public void destroy() {
		if(time==8) {
			time = 0;
			setVisible(false);
		}
	}
	
	//泡泡炸人
	public boolean gamePK(SuperElement se) {
		if(isVisible())
		{
			int[][] floor = ElementLoad.getElementLoad().getFloor();	
			if(Math.abs(getX()-se.getX())<=50&&Math.abs(getY()-se.getY())<=10)
			{
//				if(this instanceof PlayerFire) {
//					((PlayerFire)this).setFlag(1);
//				}
				this.setFlag(1);
				return true;
			}
			
			if(Math.abs(getY()-se.getY())<=50&&Math.abs(getX()-se.getX())<=10)
		  	{
//				if(this instanceof PlayerFire) {
//					if(((PlayerFire)this).getFlag() == 1) {
//						((PlayerFire)this).setFlag(3);
//					}else {
//						((PlayerFire)this).setFlag(2);
//					}
//				}
				if(this.getFlag()==1) {
					this.setFlag(3);
				}
				else {
					this.setFlag(2);
				}
				return true;
		  	}
		}
		return false;
	}
	
}
