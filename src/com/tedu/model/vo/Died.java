package com.tedu.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Died extends SuperElement{
	private ImageIcon img;
	

	private int movex=0;
	public Died() {
		super();
	}
	
	public Died(int x, int y, int w, int h,ImageIcon img){
		super(x,y,w,h);
		this.img=img;
	}
	
	public static Died createDied(int x,int y){
		ImageIcon img=new ImageIcon("img/character/died.png");
		return new Died(x,y,32,32,img);
	}

	@Override
	public void showElement(Graphics g) {
		g.drawImage(img.getImage(), 
				getX(), getY(),                  //ÆÁÄ»×óÉÏ½Ç×ø±ê
				getX()+getW(), getY()+getH(),    //ÆÁÄ»ÓÒÏÂ½Ç×ø±ê
					100*movex+20,20 ,    //Í¼Æ¬×óÉÏ½Ç×ø±ê        60 ,0
					100*movex+80,100,    //Í¼Æ¬ÓÒÏÂ½Ç×ø±ê  120,60
//					moveX, 0,    //Í¼Æ¬×óÉÏ½Ç×ø±ê        60 ,0
//					moveX+60, 60,	
					null);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		movex=movex%3;
		movex++;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
