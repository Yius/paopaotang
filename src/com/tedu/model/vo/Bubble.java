package com.tedu.model.vo;

import java.awt.Graphics;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.manager.ElementManager;


/**
 * 
 * 爆炸前的泡泡状态
 *
 */
public class Bubble extends SuperElement{
	private ImageIcon img;
	private int moveX;
	private int moveY;
	private int time=0;
	
	public Bubble() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bubble(int x, int y, int w, int h,ImageIcon img) {
		super(x, y, w, h);
		this.img=img;
	}

	public static PlayerFire createPlayerFire(int x,int y,String str){
		System.out.println("bbb:"+str);
		String []arr=str.split(",");
		ImageIcon img=
				ElementLoad.getElementLoad().getMap().get(arr[1]);
		System.out.println("ccc:"+img);
		return new PlayerFire(x,y,30,30,img);
	}
	
	@Override
	public void showElement(Graphics g) {
		g.drawImage(img.getImage(), 
				getX(), getY(),                  //屏幕左上角坐标
				getX()+getW(), getY()+getH(),    //屏幕右下角坐标
					32*moveX,8 ,    //图片左上角坐标        60 ,0
					32*(moveX+1),45,    //图片右下角坐标  120,60
//					moveX, 0,    //图片左上角坐标        60 ,0
//					moveX+60, 60,	
					null);
	}
	@Override
	public void move() { 
		moveX=moveX%3;
		moveX++;
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
		time++;
		System.out.println(time);
		if(time==10){
			List<SuperElement> list1=
					ElementManager.getManager().getElementList("boom");
			Map<String, List<String>> map=
					ElementLoad.getElementLoad().getPlaymap();
			List<String> list=map.get("onePlayer");
			String s=list.get(0);
			list1.add(Boom.createBoom(getX(), getY(), s));
			setVisible(false);
		}
	}
	
}
