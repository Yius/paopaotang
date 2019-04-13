package com.tedu.model.vo;

import java.awt.Graphics;
import java.util.List;

import javax.swing.ImageIcon;

public class PlayerFire extends SuperElement{
	private ImageIcon img;
	
	public PlayerFire() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlayerFire(int x, int y, int w, int h,ImageIcon img) {
		super(x, y, w, h);
		this.img=img;
	}

	public static PlayerFire createPlayerFire(int x,int y,String str){
		ImageIcon img=new ImageIcon("img/fire/12.png");
		return new PlayerFire(x,y,30,30,img);
	}
	@Override
	public void showElement(Graphics g) {
		g.drawImage(img.getImage(), getX(), getY(), 
				getW(), getH(), null);
	}
	@Override
	public void move() {
		setY(getY()-10);
//		超过边界的 是不是要 销毁
		if(getY()<0){
			setVisible(false);
		}
	}
	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}

	@Override
	public void destroy() {
//		销毁的时候，需要创建 爆炸对象 添加入到 爆炸的list集合中
		if(!isVisible()) {
			//TODO
		}	
	}
}
