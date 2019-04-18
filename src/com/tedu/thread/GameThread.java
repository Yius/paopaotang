package com.tedu.thread;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.tedu.model.manager.ElementManager;
import com.tedu.model.manager.MapManager;
import com.tedu.model.vo.Boom;
import com.tedu.model.vo.Player;
import com.tedu.model.vo.SuperElement;

//java是单继承，多实现。通过 内部类的方式，弥补单继承的缺陷
public class GameThread extends Thread{
//	计时数据
	private int time;
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
			/*原来用于飞机的，现在不需要就删去了
//			写飞机的添加到 list到map  //游戏的流程控制 
			linkGame();
			*/
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
		List<SuperElement> playerOneList = ElementManager.getManager().getElementList("playerOne");
		List<SuperElement> playerTwoList = ElementManager.getManager().getElementList("playerTwo");
		List<SuperElement> treeList = ElementManager.getManager().getElementList("tree");
		List<SuperElement> houseList = ElementManager.getManager().getElementList("house");
		List<SuperElement> boxList = ElementManager.getManager().getElementList("box");
		List<SuperElement> bushList = ElementManager.getManager().getElementList("bush");
		List<SuperElement> boomList = ElementManager.getManager().getElementList("boom");
		List<SuperElement> bubbleToolList = ElementManager.getManager().getElementList("bubbleTool");
		List<SuperElement> buleMedicineList = ElementManager.getManager().getElementList("buleMedicine");
		List<SuperElement> purpleMedicineList = ElementManager.getManager().getElementList("purpleMedicine");
		List<SuperElement> purpleGhostList = ElementManager.getManager().getElementList("purpleGhost");
		List<SuperElement> redGhostList = ElementManager.getManager().getElementList("redGhost");
		List<SuperElement> teleControlList = ElementManager.getManager().getElementList("teleControl");
		List<SuperElement> mineList = ElementManager.getManager().getElementList("mine");
		List<SuperElement> superCardList = ElementManager.getManager().getElementList("superCard");
		pkWithRoadBlock(playerOneList,treeList);//路障
		pkWithRoadBlock(playerTwoList, treeList);
		pkWithRoadBlock(playerOneList,houseList);
		pkWithRoadBlock(playerTwoList, houseList);
		pkWithRoadBlock(playerOneList,boxList);
		pkWithRoadBlock(playerTwoList, boxList);
		pkWithRoadBlock(playerOneList, bushList);
		pkWithRoadBlock(playerTwoList, bushList);
		getTheTool(playerOneList, bubbleToolList);
		getTheTool(playerTwoList, bubbleToolList);
		getTheTool(playerOneList,buleMedicineList);
		getTheTool(playerTwoList,buleMedicineList);
		getTheTool(playerOneList,purpleMedicineList);
		getTheTool(playerTwoList,purpleMedicineList);
		getTheTool(playerOneList, purpleGhostList);
		getTheTool(playerTwoList, purpleGhostList);
		getTheTool(playerOneList, redGhostList);
		getTheTool(playerTwoList, redGhostList);
		getTheTool(playerOneList,teleControlList);
		getTheTool(playerTwoList, teleControlList);
		getTheTool(playerOneList,mineList);
		getTheTool(playerTwoList, mineList);
		getTheTool(playerOneList,superCardList);
		getTheTool(playerTwoList, superCardList);
		listPK(boomList,playerOneList);
		listPK(boomList,playerTwoList);
		listPK(boomList,boxList);
	}
	
//	部分的代码 是可以重复使用的。
	//判断泡泡是否炸到人或箱子
	public void listPK(List<SuperElement> boomList,
			List<SuperElement> otherThings){
		Random r = new Random();
		for(int i=0;i<boomList.size();i++){
			for(int j=0;j<otherThings.size();j++){
				Boom boom = (Boom)(boomList.get(i));
				/*
				 * 面向对象而言，gamePK应该是boom特有方法
				 */
				if(boom.gamePK(otherThings.get(j))){	
					//根据其自身的能否摧毁属性决定是否将其设置为不可见
					if(otherThings.get(j) instanceof Player) {
						Player player = (Player) otherThings.get(j);
						if(player.getCurrentSuperCardNums()>0) {
							player.setCurrentSuperCardNums(player.getCurrentSuperCardNums()-1);
							player.setInvincibleTime(50);
						}else {
							player.setVisible(false);
						}
					}else {
						otherThings.get(j).setVisible(!otherThings.get(j).isCanDestroy());
					}
				}
			}
		}
	}
	
	
	/*
	 * 判断人物和固定物会不会碰撞
	 * 判断路障是否阻碍移动
	 */
	private void pkWithRoadBlock(List<SuperElement> characters,List<SuperElement> otherthings) {
		for(int i=0;i<characters.size();i++){
			for(int j=0;j<otherthings.size();j++){
				Player player = (Player)(characters.get(i));
				player.crashDetect(otherthings.get(j));
			}
		}
	}
	
	/*
	 * 获得道具并将其销毁
	 */
	private void getTheTool(List<SuperElement> characters,List<SuperElement> tools) {
		int[][] floor = MapManager.getMapManager().getFloor();
		for(int i=0;i<characters.size();++i){
			for(int j=0;j<tools.size();++j){
				Player player = (Player)(characters.get(i));
				if(player.getTool(tools.get(j))) {
					int row = tools.get(j).getRow();
					int col = tools.get(j).getCol();
					floor[row][col]=0;
					tools.get(j).setVisible(false);
				}
			}
		}
	}
	
	
	/*原来用于飞机的，现在不需要就删去了
	//游戏的流程控制 
	public void linkGame(){
		ElementManager.getManager().linkGame(time);
	}
	*/
	
	
	private void overGame() {
		// TODO Auto-generated method stub
		
	}
//	控制进度,但是，作为 控制，请不要接触 load
	private void loadElement() {
		ElementManager.getManager().load();
	}
	
}
