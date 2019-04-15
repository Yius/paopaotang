package com.tedu.model.vo;

import java.awt.Graphics;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.manager.ElementManager;

public class Bubble extends SuperElement{
	private ImageIcon img;
	private int movex;
	private int time = 0;//��ѭ������ʱ��Ҳ���и��õķ�������ʱʹ�ã���time�ﵽ4*6-1=23ʱ��ը
	private int diedTime = 23;
	
	public Bubble() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bubble(int x, int y, int w, int h,ImageIcon img) {
		super(x, y, w, h);
		this.img=img;
	}

	public static Bubble createBubble(int x,int y,String str){
		String []arr=str.split(",");
		ImageIcon img=
				ElementLoad.getElementLoad().getMap().get(arr[1]);
		return new Bubble(x,y,32,32,img);
	}
	
	@Override
	public void showElement(Graphics g) {
		g.drawImage(img.getImage(), 
				getX(), getY(),                  //��Ļ���Ͻ�����
				getX()+getW(), getY()+getH(),    //��Ļ���½�����
					32*movex,8 ,    //ͼƬ���Ͻ�����        60 ,0
					32*(movex+1),45,    //ͼƬ���½�����  120,60
//					moveX, 0,    //ͼƬ���Ͻ�����        60 ,0
//					moveX+60, 60,	
					null);
	}
	@Override
	public void move() {
		movex = movex%3;
		++movex;
		diedTimeCountDown();
	}
	
	private void diedTimeCountDown() {
		++time;
		if(time == diedTime) {
			time = 0;//��ֹѭ��̫��ʹ��Bubbleδ���Ƴ���list֮ǰtime����int��Χ
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
		if(!isVisible()) {
	      List<SuperElement> boomList =
			ElementManager.getManager().getElementList("boom");
	      Map<String, List<String>> map=
			ElementLoad.getElementLoad().getPlaymap();
	      List<String> list=map.get("onePlayer");
	      //TODO ����֮�����ĵ�
	      String s=list.get(0);
	      boomList.add(Boom.createBoom(getX(), getY(), s));
		}
	}

}
