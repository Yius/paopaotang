package com.tedu.model.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.vo.Box;
import com.tedu.model.vo.BubbleTool;
import com.tedu.model.vo.BuleMedicine;
import com.tedu.model.vo.House;
import com.tedu.model.vo.Mine;
import com.tedu.model.vo.Player;
import com.tedu.model.vo.PurpleGhost;
import com.tedu.model.vo.PurpleMedicine;
import com.tedu.model.vo.RedGhost;
import com.tedu.model.vo.SuperCard;
import com.tedu.model.vo.SuperElement;
import com.tedu.model.vo.TeleControl;
import com.tedu.model.vo.Tree;

/**
 * 任务：依据参数不同，自动读取 资源，填充 vo对象数据，存储到 元素管理器
 * 	工厂的作用：将比较复杂的 构造方式 进行封装
 */
public class ElementFactory {
	
	/*
	 * 返回类型改成了List，方便一次读完
	 */
	public static List<SuperElement> elementFactory(String name){
		Map<String, List<String>> map=
			ElementLoad.getElementLoad().getPlaymap();
		List<SuperElement> result = new ArrayList<>();
		int[][] floor = MapManager.getMapManager().getFloor();
		switch(name){
		case "playerOne":
			List<String> list=map.get(name);
			String s=list.get(0);//playerOne=playerA,bubble,boom,150,350,33,33
			result.add(Player.createPlayer(s));
			return result;
		case "playerTwo":
			List<String> list2=map.get(name);
			String s2=list2.get(0);//playerTwo=playerB,bubble,boom,150,400,33,33
			result.add(Player.createPlayer(s2));
			return result;
		/*
		case "tree":
			for(int i=0;i<floor.length;++i) {
				for(int j=0;j<floor[0].length;++j) {
					if(floor[i][j]==5) {
						result.add(Tree.createTree(i,j));
					}
				}
			}
			return result;
		case "house":
			for(int i=0;i<floor.length;++i) {
				for(int j=0;j<floor[0].length;++j) {
					if(floor[i][j]==1||floor[i][j]==2) {
						result.add(House.createHouse(i,j));
					}
				}
			}
			return result;
		case "box":
			for(int i=0;i<floor.length;++i) {
				for(int j=0;j<floor[0].length;++j) {
					if(floor[i][j]>300) {
						result.add(Box.createBox(i,j));
					}
				}
			}
			return result;
			*/
		}
		return null;
	}
	
	/*
	 * 一次遍历加载地板的全部元素，比上面一次又一次遍历更快
	 */
	public static void loadFloorElementsAtFirst(){
		Map<String,List<SuperElement>> result = ElementManager.getManager().getMap();
		List<SuperElement> treeList = new ArrayList<>();
		List<SuperElement> boxList = new ArrayList<>();
		List<SuperElement> houseList = new ArrayList<>();
		int[][] floor = MapManager.getMapManager().getFloor();
		for(int i=0;i<floor.length;++i) {
			for(int j=0;j<floor[0].length;++j) {
				if(floor[i][j]>300) {
					boxList.add(Box.createBox(i,j));
					continue;
				}
				switch(floor[i][j]){
				case 5:treeList.add(Tree.createTree(i,j));break;
				case 1:
				case 2:houseList.add(House.createHouse(i,j));break;
				}
			}
		}
		result.put("tree", treeList);
		result.put("box", boxList);
		result.put("house", houseList);
	}
	
	/*
	 *创建物品方法，尚不完善
	 */
	//TODO
	public static void createTool(int type, int row, int col) {
		switch(type) {
		case 601:ElementManager.getManager().getMap().get("bubbleTool").add(BubbleTool.createBubbleTool(row , col));break;
		case 602:ElementManager.getManager().getMap().get("buleMedicine").add(BuleMedicine.createBuleMedicine(row,col));break;
		case 603:ElementManager.getManager().getMap().get("purpleMedicine").add(PurpleMedicine.createPurpleMedicine(row,col));break;
		case 604:ElementManager.getManager().getMap().get("purpleGhost").add(PurpleGhost.createPurpleGhost(row, col));break;
		case 605:ElementManager.getManager().getMap().get("redGhost").add(RedGhost.createRedGhost(row, col));break;
		case 606:ElementManager.getManager().getMap().get("teleControl").add(TeleControl.createTeleCobtrol(row, col));break;
		case 607:ElementManager.getManager().getMap().get("mine").add(Mine.createMine(row, col));break;
		case 608:ElementManager.getManager().getMap().get("superCard").add(SuperCard.createSuperCard(row, col));break;
		}
		
	}
}
