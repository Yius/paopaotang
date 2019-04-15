package com.tedu.model.load;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.swing.ImageIcon;

public class ElementLoad {
	private Map<String,ImageIcon> map;
	private Map<String,List<String>> playmap;
	private Map<String,List<String>> enemymap;
	private int[][] floor;
	private List<String> gameList;//��Ϸ�����̿���  ���˱������ֿ���
//	�Ͻ�ͷ��С��ע�⣺�����õ���  Map<String,List<ImageIcon>>
	
	
	private static ElementLoad load;
	public List<String> getGameList() {
		return gameList;
	}
	//	pro�ļ���ȡ����
	private Properties pro;
	
	private ElementLoad(){
		map=new HashMap<>();
		playmap=new HashMap<>();
		pro=new Properties();
		gameList=new ArrayList<>();
		floor = new int[20][20];
	}
	public static synchronized ElementLoad getElementLoad(){
		if(load==null){
			load=new ElementLoad();
		}
		return load;
	}
	
	/*ԭ�����ڷɻ��ģ����ڲ���Ҫ��ɾȥ��
//	��ȡ����
	public void readGamepro(){
		InputStream in=ElementLoad.class.getClassLoader()
				.getResourceAsStream("com/tedu/pro/GameRunA.properties");
		try {
			pro.clear();
			pro.load(in);
			for(Object o:pro.keySet()){
				String str=pro.getProperty(o.toString());
				gameList.add(str);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	
	
//	��ȡ��������
	public void readPlayPro(){
		InputStream in=ElementLoad.class.getClassLoader()
				.getResourceAsStream("com/tedu/pro/play.properties");
		try {
			pro.clear();
			pro.load(in);
			List<String> list=new ArrayList<>();
			for(Object o:pro.keySet()){
				String str=pro.getProperty(o.toString());
				list.add(str);
				playmap.put(o.toString(), list);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * ��ȡ��ͬ����
	 */
	private int randomBox() {
		Random r = new Random();
		return r.nextInt(8)+301;
	}
	
//	��ȡ�ذ�
	public void readFloorPro() {
		InputStream in = ElementLoad.class.getClassLoader().getResourceAsStream("com/tedu/pro/floor.properties");
		try {
			pro.clear();
			pro.load(in);
			int row = 0;
			for(int i=1;i<=20;++i) {
				String str = pro.getProperty("line"+i);
				String[] rowStr = str.split(",");
				for(int col=0;col<rowStr.length;++col) {
					floor[row][col]=Integer.parseInt(rowStr[col]);
					if(floor[row][col]==3) {
						floor[row][col]=randomBox();
					}
				}
				++row;
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
//	��ȡͼƬ��
	public void readImgPro(){
		InputStream in=ElementLoad.class.getClassLoader()
			.getResourceAsStream("com/tedu/pro/mapA.properties");
		try {
			pro.clear();
			pro.load(in);
			Set<?> set=pro.keySet();
			for(Object o:set){
				String url=pro.getProperty(o.toString());
				map.put(o.toString(), new ImageIcon(url));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Map<String, ImageIcon> getMap() {
		return map;
	}
	public Map<String, List<String>> getPlaymap() {
		return playmap;
	}
	public Map<String, List<String>> getEnemymap() {
		return enemymap;
	}
	public int[][] getFloor() {
		return floor;
	}
	
	/*
	 * ��ʱ���ڲ���
	public static void main(String[] args) {
		ElementLoad.getElementLoad().readFloorPro();
		int[][] floor = ElementLoad.getElementLoad().getFloor();
		for(int i=0;i<floor.length;++i) {
			System.out.print(i+1+": ");
			for(int j=0;j<floor[0].length;++j) {
				System.out.print(floor[i][j]);
			}
			System.out.println();
		}
	}
	*/
	
	
	
	
}
