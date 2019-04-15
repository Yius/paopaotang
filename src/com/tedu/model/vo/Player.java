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

public class Player extends SuperElement{
	
	private int hp;//血量
	private int num;//分数
	private ImageIcon img;
//	private StopMove stopMove;
//	当前玩家名称。。。。
	
	private MoveType moveType;//0 1 2 3
	private boolean  pk;//攻击状态，默认为 false
	
	private int moveX;
	private int moveY;
	
	
	public Player(int x,int y,int w,int z,ImageIcon img){
		super(x,y,w,z);
		this.img=img;//就近原则  
		hp=0;
		num=0;
		moveType=MoveType.stop;
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
				50*moveX+26, (moveY)*100+40,    //图片左上角坐标        60 ,0
				50*(moveX+1)+26, (moveY)*100+100,    //图片右下角坐标  120,60
				null);
	}
	
	public void move(){
		switch(moveType){
		case top:setY(getY()-5);moveY=3;break;
		case left:setX(getX()-5);moveY=1;break;
		case right:setX(getX()+5);moveY=2;break;
		case down:setY(getY()+5);moveY=0;break;
		case stop:moveX=-2;break;
		}
	}
	
//	重写父类的模板
	public void update(){
		super.update();//如果没有这句话，就是 重新制定新模板
		addFire();//追加
		updateImage();
	}
	public void updateImage(){
		moveX=moveX%6;
		moveX+=2;
	}
	
	public void addFire(){
		if(!pk){//如果PK是false就不需要 添加子弹
			return;
		}
		List<SuperElement> list1=
				ElementManager.getManager().getElementList("playFire");
		Map<String, List<String>> map=
				ElementLoad.getElementLoad().getPlaymap();
		List<String> list=map.get("onePlayer");
		String s=list.get(0);
		list1.add(PlayerFire.createPlayerFire(getX(), getY(), s));
		setBoomed(false);
		pk=false;//每按一次 只能发射一颗子弹	
	}
	
	
	public MoveType getMoveType() {
		return moveType;
	}
	public void setMoveType(MoveType moveType) {
		this.moveType = moveType;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
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
		if(isVisible()){
			return ;
		}
		List<SuperElement> list=
					ElementManager.getManager().getElementList("died");
		list.add(Died.createDied(getX(), getY(), ""));
	}
	

	/*
	public void setStopMove(StopMove stopMove) {
		this.stopMove = stopMove;
	}*/
	
	
}
