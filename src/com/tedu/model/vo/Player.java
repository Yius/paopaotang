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
	
	private int hp;//Ѫ��
	private int num;//����
	private ImageIcon img;
//	private StopMove stopMove;
//	��ǰ������ơ�������
	
	private MoveType moveType;//0 1 2 3
	private boolean  pk;//����״̬��Ĭ��Ϊ false
	
	private int moveX;
	private int moveY;
	
	
	public Player(int x,int y,int w,int z,ImageIcon img){
		super(x,y,w,z);
		this.img=img;//�ͽ�ԭ��  
		hp=0;
		num=0;
		moveType=MoveType.stop;
		pk=false;
		setCanPass(false);
//		stopMove=StopMove.None;ԭ�����ж���ײ�����ڸо�û�ã���ɾȥ
	}
	
	//����ֱ�ӵ�����������������õ�һ����Ҷ���  str��������ľ�����Ҷ������Ϣ
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
			getX(), getY(),                  //��Ļ���Ͻ�����
			getX()+getW(), getY()+getH(),    //��Ļ���½�����
				50*moveX+26, (moveY)*100+40,    //ͼƬ���Ͻ�����        60 ,0
				50*(moveX+1)+26, (moveY)*100+100,    //ͼƬ���½�����  120,60
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
	
//	��д�����ģ��
	public void update(){
		super.update();//���û����仰������ �����ƶ���ģ��
		addFire();//׷��
		updateImage();
	}
	public void updateImage(){
		moveX=moveX%6;
		moveX+=2;
	}
	
	public void addFire(){
		if(!pk){//���PK��false�Ͳ���Ҫ ����ӵ�
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
		pk=false;//ÿ��һ�� ֻ�ܷ���һ���ӵ�	
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
