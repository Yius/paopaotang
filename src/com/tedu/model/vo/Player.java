package com.tedu.model.vo;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.manager.ElementManager;
import com.tedu.model.manager.MoveType;

public class Player extends Character{
	
	private int num;//分数
	private ImageIcon img;
//	private StopMove stopMove;
//	当前玩家名称。。。。
	
	private boolean  pk;//攻击状态，默认为 false
	
	
	
	public Player(int x,int y,int w,int z,ImageIcon img){
		super(x,y,w,z);
		this.img=img;//就近原则  
		num=0;
		pk=false;
		setCanPass(false);
//		stopMove=StopMove.None;原用于判断碰撞，现在感觉没用，先删去
	}
	
	//可以直接调用这个方法，用来得到一个玩家对象  str里面包含的就是玩家对象的信息
	public static Player createPlayer(String str){
//		playerA,playFire,150,300,32,32

		String [] arr=str.split(",");
		int x=Integer.parseInt(arr[3]);
		int y=Integer.parseInt(arr[4]);
		int w=Integer.parseInt(arr[5]);
		int h=Integer.parseInt(arr[6]);
		ImageIcon img=
				ElementLoad.getElementLoad().getMap().get(arr[0]);
		
		return new Player(x,y,w,h,img);
	}
	
	@Override
	public void showElement(Graphics g) {
		g.drawRect(getX(), getY(), getW(), getH());
		g.drawImage(img.getImage(), 
			getX(), getY(),                  //屏幕左上角坐标
			getX()+getW(), getY()+getH(),    //屏幕右下角坐标
				50*getMoveX()+26, (getMoveY())*100+40,    //图片左上角坐标        60 ,0
				50*(getMoveX()+1)+26, (getMoveY())*100+100,    //图片右下角坐标  120,60
				null);
	}
	
//	重写父类的模板
	public void update(){
		super.update();//如果没有这句话，就是 重新制定新模板
		addBubble();//追加
		updateImage();
	}
	
	public void updateImage(){
		setMoveX(getMoveX()%6+2);
	}
	
	public void addBubble(){
		if(!pk){//如果PK是false就不需要 添加子弹
			return;
		}
		List<SuperElement> list1=
				ElementManager.getManager().getElementList("bubble");
		Map<String, List<String>> map=
				ElementLoad.getElementLoad().getPlaymap();
		//TODO 这里之后必须改
		List<String> list = map.get("onePlayer");
		String s=list.get(0);
		list1.add(Bubble.createBubble(getX(), getY(), s));
		pk=false;//每按一次 只能发射一颗子弹	
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public ImageIcon getImg() {
		return img;
	}
	public void setImg(ImageIcon img) {
		this.img = img;
	}
	public boolean isPk() {
		return pk;
	}
	public void setPk(boolean pk) {
		this.pk = pk;
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		if(!isVisible()){
			List<SuperElement> list =
					ElementManager.getManager().getElementList("died");
			list.add(Died.createDied(getX(), getY(), ""));
		}
	}
	

	/*
	public void setStopMove(StopMove stopMove) {
		this.stopMove = stopMove;
	}*/
	
	
}
