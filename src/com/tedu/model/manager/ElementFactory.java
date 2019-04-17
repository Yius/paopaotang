package com.tedu.model.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.vo.Box;
import com.tedu.model.vo.BubbleTool;
import com.tedu.model.vo.House;
import com.tedu.model.vo.Player;
import com.tedu.model.vo.SuperElement;
import com.tedu.model.vo.Tree;

/**
 * �������ݲ�����ͬ���Զ���ȡ ��Դ����� vo�������ݣ��洢�� Ԫ�ع�����
 * 	���������ã����Ƚϸ��ӵ� ���췽ʽ ���з�װ
 */
public class ElementFactory {
	
	/*
	 * �������͸ĳ���List������һ�ζ���
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
	 * һ�α������صذ��ȫ��Ԫ�أ�������һ����һ�α�������
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
	 *������Ʒ�������в�����
	 */
	//TODO
	public static void createTool(int type, int row, int col) {
		switch(type) {
		case 601:ElementManager.getManager().getMap().get("bubbleTool").add(BubbleTool.createBubbleTool(row , col));break;
		//case 602:ElementManager.getManager().getMap().get("buleMedicine").add(BuleMedicine.createBuleMedicine(row,col));break;
		//case 603:ElementManager.getManager().getMap().get("purpleMedicine").add(PurpleMedicine.createPurpleMedicine(row,col));break;
		//....
		}
	}
}
