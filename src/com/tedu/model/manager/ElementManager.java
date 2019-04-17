package com.tedu.model.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.vo.SuperElement;

/**
 * Ԫ�ع�����
 * 
 * java���ģʽ-������ģʽ->ȫ��ֻ��һ��ʵ��
 * 
 * hashCode();��Object��->��������Set����,hashɢ��ԭ��
 * 
 */
public class ElementManager {
//	����  NPCԪ�أ�����Ԫ�أ���������
	Map<String,List<SuperElement>> map;//�ô���ʲô������
//	��ʼ��
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
//	�õ�һ�������� map����
	public Map<String, List<SuperElement>> getMap() {
		return map;
	}
//	�õ�һ��Ԫ�صļ���
	public List<SuperElement> getElementList(String key){
		return map.get(key);
	}
	
	
	
	
	
	
//	��������Ҫһ��Ψһ������
	private static ElementManager elementManager;
//	���췽��˽�л�����ֻ���ڱ����п��� new
	private ElementManager(){
		init();
	}
	static{//��̬������ ��������ص�ʱ��ͻ�ִ��
		if(elementManager ==null){
			elementManager=new ElementManager();
		}
	}
//	�ṩ���������ⲿ���ʵ�Ψһ���   synchronized �̱߳�����
	public static /*synchronized*/ ElementManager getManager(){
//		if(elementManager ==null){
//			elementManager=new ElementManager();
//		}
		return elementManager;
	}
//	������Ҫ����Դ
	public void load() {
		ElementLoad.getElementLoad().readImgPro();
		ElementLoad.getElementLoad().readPlayPro();
		/*ԭ�����ڷɻ��ģ����ڲ���Ҫ��ɾȥ��
		ElementLoad.getElementLoad().readGamepro();
		*/
		ElementLoad.getElementLoad().readFloorPro();
		ElementLoad.getElementLoad().readToolsPro();
//		����һ�� ״̬�����������  ǰϦ����ǰ��Ĺ�����Ϣ��
//		......
		/*
		 * ����ֻ��ʾ���������и���Ķ������룬���ҿ��Կ��������û��ķ���ȥ���
		 */
		//TODO ��������playerList����ֻ��һ��Ԫ�أ������и��õķ���
		List<SuperElement> playerOneList = ElementFactory.elementFactory("playerOne");
		List<SuperElement> playerTwoList = ElementFactory.elementFactory("playerTwo");
//		List<SuperElement> treeList = ElementFactory.elementFactory("tree");
//		List<SuperElement> boxList = ElementFactory.elementFactory("box");
//		List<SuperElement> houseList = ElementFactory.elementFactory("house");
		//һ�λ�ȡ���ֵذ�listԪ�أ�ʹԤ���ظ���
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
	
	/*ԭ�����ڷɻ��ģ����ڲ���Ҫ��ɾȥ��
//	��������   int time��Ϸ����ʱ��
	public void linkGame(int time) {
//		�����õ����� list
		List<String> list=ElementLoad.getElementLoad().getGameList();
		if(list.size()==0){
			return;//�����Ѿ�������
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




