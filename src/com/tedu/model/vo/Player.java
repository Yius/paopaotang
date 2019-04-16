package com.tedu.model.vo;

import java.awt.Graphics;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.manager.ElementManager;

public class Player extends Character{
	
	private int num;//分数
	private ImageIcon img;
	private int maxBubbleNums = 1;	//当前能够放置的最大泡泡数
	private int currentBubbleNums = 1;	//当前能够放置的泡泡数
	private int reloadTime = 31; //这是根据泡泡爆炸事件得出的
	private int time = 0;	//计时以达到限制释放的作用
//	private StopMove stopMove;
//	当前玩家名称。。。。
	
	private boolean  pk;//攻击状态，默认为 false
	
	
	
	public Player(int x,int y,int w,int z,ImageIcon img){
		super(x,y,w,z);
		this.img=img;//就近原则  
		num=0;
		pk=false;
//		stopMove=StopMove.None;原用于判断碰撞，现在感觉没用，先删去
	}
	
	//可以直接调用这个方法，用来得到一个玩家对象  str里面包含的就是玩家对象的信息
	public static Player createPlayer(String str){

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
		if(!pk || currentBubbleNums == 0){//如果PK是false就不需要 添加子弹
			return;
		}
		List<SuperElement> list1=
				ElementManager.getManager().getElementList("bubble");
		Map<String, List<String>> map=
				ElementLoad.getElementLoad().getPlaymap();
		//TODO 这里之后必须改
		//TODO 这里暂时看来是狗尾续貂的，后期可能会引发bug
		List<String> list = map.get("playerOne");
		String s=list.get(0);
		long x = Math.round((double)(getX())/32);
		long y = Math.round((double)(getY())/32);
		list1.add(Bubble.createBubble((int)x*32, (int)y*32, s));
		--currentBubbleNums;
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
	
	@Override
	public void move() {
		super.move();
		countTime();
	}
	
	/*
	 * 计时用的
	 */
	private void countTime() {
		if(currentBubbleNums >= maxBubbleNums) {
			return ;
		}
		++time;
		if(time == reloadTime) {
			time = 0;
			if(currentBubbleNums < maxBubbleNums) {
				++currentBubbleNums;
			}
		}
	}
	
	
	/*
	public void setStopMove(StopMove stopMove) {
		this.stopMove = stopMove;
	}*/
	
	
}
