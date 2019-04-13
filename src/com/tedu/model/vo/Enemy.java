package com.tedu.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;

public class Enemy extends SuperElement{
	private ImageIcon img;
	private String name;//敌机种类
	public Enemy(int x, int y, int w, int h,ImageIcon img) {
		super(x, y, w, h);
		this.img=img;
	}
	public static Enemy createEnemey(String url){
//		004=enemyA,enemyA,20,170,40,40,10000
		String []arr=url.split(",");
		int x=Integer.parseInt(arr[2]);
		int y=Integer.parseInt(arr[3]);
		int w=Integer.parseInt(arr[4]);
		int h=Integer.parseInt(arr[5]);
		ImageIcon img=
				ElementLoad.getElementLoad().getMap().get(arr[0]);
		Enemy enemy=null;
		switch(arr[0]){//在配置文件中可以给与类型
		case "enemyA":
			enemy=new EnemyLeftToRight(x, y, w, h, img);
			break;
		case "enemyB":
			enemy=new EnmeyRightToLeft(x, y, w, h, img);
			break;
		default:
			enemy=new Enemy(x,y,w,h,img);
		}
		return enemy;
	}
	@Override
	public void showElement(Graphics g) {
		g.drawImage(img.getImage(), 
				getX(), getY(), getW(), getH(), null);
	}
	@Override
	public void move() {
		setY(getY()+5);
		if(getY()>300){
			this.setVisible(false);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}

	
	
	
	
	
	
}
