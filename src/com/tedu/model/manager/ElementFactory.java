package com.tedu.model.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.vo.Box;
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
		}
		return null;
	}
	
	
}
