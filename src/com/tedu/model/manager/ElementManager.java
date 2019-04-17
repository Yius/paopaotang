package com.tedu.model.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.vo.SuperElement;

/**
 * 元素管理器
 * 
 * java设计模式-》单例模式->全局只有一个实例
 * 
 * hashCode();是Object类->集合排序（Set集合,hash散列原理）
 * 
 */
public class ElementManager {
//	集合  NPC元素，场景元素，。。。。
	Map<String,List<SuperElement>> map;//好处是什么？？？
//	初始化
	protected void init(){
		map=new HashMap<>();
		map.put("playerOne", new ArrayList<>());
		map.put("playerTwo", new ArrayList<>());
		map.put("bubble", new ArrayList<>());
		map.put("tree", new ArrayList<>());
		map.put("apricot", new ArrayList<>());
		map.put("box",new ArrayList<>());
		map.put("house",new ArrayList<>());
		map.put("boom", new ArrayList<>());
		map.put("died", new ArrayList<>());
		map.put("bubbleTool", new ArrayList<>());
	}
//	得到一个完整的 map集合
	public Map<String, List<SuperElement>> getMap() {
		return map;
	}
//	得到一个元素的集合
	public List<SuperElement> getElementList(String key){
		return map.get(key);
	}
	
	
	
	
	
	
//	单例：需要一个唯一的引用
	private static ElementManager elementManager;
//	构造方法私有化，就只有在本类中可以 new
	private ElementManager(){
		init();
	}
	static{//静态的语句块 是在类加载的时候就会执行
		if(elementManager ==null){
			elementManager=new ElementManager();
		}
	}
//	提供出来给予外部访问的唯一入口   synchronized 线程保护锁
	public static /*synchronized*/ ElementManager getManager(){
//		if(elementManager ==null){
//			elementManager=new ElementManager();
//		}
		return elementManager;
	}
//	加载需要的资源
	public void load() {
		ElementLoad.getElementLoad().readImgPro();
		ElementLoad.getElementLoad().readPlayPro();
		/*原来用于飞机的，现在不需要就删去了
		ElementLoad.getElementLoad().readGamepro();
		*/
		ElementLoad.getElementLoad().readFloorPro();
		ElementLoad.getElementLoad().readToolsPro();
//		开放一个 状态，界面可以做  前夕啦（前面的过度信息）
//		......
		/*
		 * 这里只是示例，可以有更多的东西加入，而且可以考虑用配置化的方法去添加
		 */
		//TODO 以下两个playerList都是只有一个元素，可能有更好的方法
		List<SuperElement> playerOneList = ElementFactory.elementFactory("playerOne");
		List<SuperElement> playerTwoList = ElementFactory.elementFactory("playerTwo");
//		List<SuperElement> treeList = ElementFactory.elementFactory("tree");
//		List<SuperElement> boxList = ElementFactory.elementFactory("box");
//		List<SuperElement> houseList = ElementFactory.elementFactory("house");
		//一次获取各种地板list元素，使预加载更快
		ElementFactory.loadFloorElementsAtFirst();
		if(playerOneList!=null) {
			map.get("playerOne").addAll(playerOneList);
		}
		if(playerTwoList!=null) {
			map.get("playerTwo").addAll(playerTwoList);
		}
		/*
		if(treeList!=null) {
			map.get("tree").addAll(treeList);
		}
		if(boxList!=null) {
			map.get("box").addAll(boxList);
		}
		if(houseList!=null) {
			map.get("house").addAll(houseList);
		}*/
	}
	
	/*原来用于飞机的，现在不需要就删去了
//	控制流程   int time游戏进行时间
	public void linkGame(int time) {
//		可以拿到流程 list
		List<String> list=ElementLoad.getElementLoad().getGameList();
		if(list.size()==0){
			return;//流程已经结束。
		}
		String s=list.get(list.size()-1);//004=enemyA,enemyA,20,170,40,40,10000
		String[] arr=s.split(",");
		int runTime=Integer.parseInt(arr[arr.length-1]);
		if(time>runTime){
			map.get("enemyList").addAll(ElementFactory.elementFactory("enemy"));
			list.remove(list.size()-1);
		}
	}
	*/
}




