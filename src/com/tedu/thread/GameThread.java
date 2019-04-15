package com.tedu.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tedu.model.manager.ElementManager;
import com.tedu.model.vo.Enemy;
import com.tedu.model.vo.Player;
import com.tedu.model.vo.SuperElement;

//java是单继承，多实现。通过 内部类的方式，弥补单继承的缺陷
public class GameThread extends Thread{
//	计时数据
	private int time;
	private int time1=0;
//	代码的熟练 和 思想的进步 都是通过很多的项目锻炼
//	如果项目不多，请 重构老项目
	public void run(){
//		while(true){   //游戏整体进度
	//		死循环，但会有变量（状态）进行控制
	//		1.加载地图，人物
			loadElement();
	//		2.显示人物地图(流程,自动化(移动，碰撞))
			time=0;
			runGame();
	//		3.结束本地图
			overGame();
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
//			}
		}
	}
	private void runGame() {
		while(true){  //每个关中玩的时候的状态
			Map<String,List<SuperElement>> map=
					ElementManager.getManager().getMap();
			Set<String> set=map.keySet();
			
				
			for(String key:set){//迭代器在遍历的过程中，迭代器内的元素不可以 增加或者删除
				List<SuperElement> list=map.get(key);
				for(int i=list.size()-1;i>=0;--i){
					list.get(i).update();
					if(!list.get(i).isVisible()){
						list.remove(i);
					}
				}
				
			}
//			使用一个独立的方法来举行判定
			PK();
//			写飞机的添加到 list到map  //游戏的流程控制 
			linkGame();
			//死亡，通关等 结束 runGame方法
			try {
				sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			time++;
		}
	}
	private void PK() {
		List<SuperElement> list1=
				ElementManager.getManager().getElementList("play");
		List<SuperElement> list2=
				ElementManager.getManager().getElementList("tree");
		List<SuperElement> list3=
				ElementManager.getManager().getElementList("house");
		List<SuperElement> list4=
				ElementManager.getManager().getElementList("box");
		List<SuperElement> list5=
				ElementManager.getManager().getElementList("playFire");
		pkWithRoadBlock(list1,list2);//路障
		pkWithRoadBlock(list1,list3);
		pkWithRoadBlock(list1,list4);
		listPK(list5,list1);
		listPK(list5,list4);
//		可以举行比较
	}
	
//	部分的代码 是可以重复使用的。
	public void listPK(List<SuperElement> list1,//判断泡泡是否炸到人或箱子
			List<SuperElement> list2){
		for(int i=0;i<list1.size();i++){
			for(int j=0;j<list2.size();j++){
				if(list1.get(i).gamePK(list2.get(j))&&list1.get(i).isBoomed()){
					System.out.println(list2.get(j));
						list2.get(j).setVisible(false);
						//list1.get(i).setVisible(false);
				}
			}
		}
	}
	
	
	/*
	 * 这是一个示例方法，用来判断人物和树会不会碰撞，实际上可以推而广之，把第二个参数换成任意一种不可穿透的物品的list
	 * 
	 */
	private void pkWithRoadBlock(List<SuperElement> characters,List<SuperElement> tree) {//判断路障是否阻碍移动
		for(int i=0;i<characters.size();i++){
			for(int j=0;j<tree.size();j++){
				Player p = (Player)(characters.get(i));
				if(characters.get(i).gameCrash(tree.get(j))){
					switch(p.getMoveType()) {
//					自动化不该改值，但是没想好怎么改
					case top:p.setY(p.getY()+5);break;
					case down:p.setY(p.getY()-5);break;
					case left:p.setX(p.getX()+5);break;
					case right:p.setX(p.getX()-5);break;
					}
				}
			}
		}
	}
	
	
	//游戏的流程控制 
	public void linkGame(){
		ElementManager.getManager().linkGame(time);
	}
	
	private void overGame() {
		// TODO Auto-generated method stub
		
	}
//	控制进度,但是，作为 控制，请不要接触 load
	private void loadElement() {
		ElementManager.getManager().load();
		
	}
	
}
