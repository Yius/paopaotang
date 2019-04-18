package com.tedu.model.vo;

import java.awt.Graphics;
import java.util.List;

import javax.swing.ImageIcon;

import com.tedu.model.manager.ElementManager;

public class Died extends SuperElement{
	private ImageIcon img;
	private int time=0;
	

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
				getX(), getY(),                  //��Ļ���Ͻ�����
				getX()+getW(), getY()+getH(),    //��Ļ���½�����
					100*movex+20,20 ,    //ͼƬ���Ͻ�����        60 ,0
					100*movex+80,100,    //ͼƬ���½�����  120,60
//					moveX, 0,    //ͼƬ���Ͻ�����        60 ,0
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
		time++;
		if(time>30)                 //������ˮȦͣ����ʱ��
		{
		setVisible(false);
		List<SuperElement> playone =
				ElementManager.getManager().getElementList("playerOne");
		List<SuperElement> playtwo =
				ElementManager.getManager().getElementList("playerTwo");
		
		if(playone.isEmpty()&&playtwo.size()!=0) {					//���һ���󣬰���Ҷ�Ҳ���
			playtwo.remove(playtwo.size()-1);
	    	}
		else if(playtwo.isEmpty()&&playone.size()!=0) {					//��Ҷ����󣬰����һҲ���
			playone.remove(playone.size()-1);
	    	}
		}
	}
}
