package com.tedu.model.vo;

import java.awt.Graphics;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.manager.ElementManager;

public class PlayerFire extends SuperElement{
	private ImageIcon img;
	private int movex;
	private int time=0;
	
	public PlayerFire() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlayerFire(int x, int y, int w, int h,ImageIcon img) {
		super(x, y, w, h);
		this.img=img;
	}

	public static PlayerFire createPlayerFire(int x,int y,String str){
		String []arr=str.split(",");
		ImageIcon img=
				ElementLoad.getElementLoad().getMap().get(arr[1]);
		System.out.println("ccc:"+img);
		return new PlayerFire(x,y,32,32,img);
	}
	@Override
	public void showElement(Graphics g) {
		g.drawImage(img.getImage(), 
				getX(), getY(),                  //ÆÁÄ»×óÉÏ½Ç×ø±ê
				getX()+getW(), getY()+getH(),    //ÆÁÄ»ÓÒÏÂ½Ç×ø±ê
					32*movex,8 ,    //Í¼Æ¬×óÉÏ½Ç×ø±ê        60 ,0
					32*(movex+1),45,    //Í¼Æ¬ÓÒÏÂ½Ç×ø±ê  120,60
//					moveX, 0,    //Í¼Æ¬×óÉÏ½Ç×ø±ê        60 ,0
//					moveX+60, 60,	
					null);
	}
	@Override
	public void move() {
		movex=movex%3;
		movex++;
	}
	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}

	@Override
	public void destroy() {
		setTime(time++);
		setBoomed(false);
		if(getTime()==20)
			setBoomed(true);
		if(getTime()==21) {
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
