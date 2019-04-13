package com.tedu.model.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.vo.Player;
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
		List<SuperElement> list=new ArrayList<>();
		map.put("play", list);
		List<SuperElement> listA=new ArrayList<>();
		map.put("enemyList", listA);
		map.put("playFire", new ArrayList<>());
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
		ElementLoad.getElementLoad().readGamepro();
		ElementLoad.getElementLoad().readFloorPro();
//		����һ�� ״̬�����������  ǰϦ����ǰ��Ĺ�����Ϣ��
//		......
		map.get("play").add(ElementFactory.elementFactory("onePlayer"));
		
	}
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
			map.get("enemyList").add(ElementFactory.elementFactory("enemy"));
			list.remove(list.size()-1);
		}
	}
}




