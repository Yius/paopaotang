package com.tedu.model.manager;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;

/**
 * 
 * 地图管理者
 *
 */
public class MapManager {
	
	private static MapManager mapManager;

	private int[][] floor;
	
	public MapManager() {
		init();
	}



	private void init() {
		floor = ElementLoad.getElementLoad().getFloor();	
	}



	public static MapManager getMapManager() {
		return mapManager;
	}



	public int[][] getFloor() {
		return floor;
	}



	static{
		if(mapManager==null){
			mapManager = new MapManager();
		}
	}


/*
 * 
 * 画地板用的，大于等于三百为箱子种类，case 3原用于箱子，发现不适合就删去了
 * 因为画图需要，所以画的时候都是画了32*64像素范
 */
	public void drawFloor(Graphics g) {
		int[][] floor = mapManager.getFloor();
		Map<String, ImageIcon> map = ElementLoad.getElementLoad().getMap();
		Random r = new Random();
		/*
		 * 因为加载和获取资源都是线程中进行的，先后无法判定，故刚开始的几次可能出现空指针异常，
		 * 不过问题不大，因为此方法是不断调用的，过了前几次后就不会有异常了
		 * 
		 */
		try {
			for(int i=0;i<floor.length;++i) {
				for(int j=0;j<floor[0].length;++j) {
					
					//每格先铺一次路
					g.drawImage(map.get("things").getImage(),
							0+j*32, (i-1)*32, 
							32+j*32, 32+i*32,
							0, 0, 
							32, 64,
							null);
					
					if(floor[i][j]>=300) {
						int num = floor[i][j]-300;
						g.drawImage(map.get("box").getImage(),
								0+j*32, (i-1)*32,
								32+j*32, 32+i*32,
								(num-1)*32, 0, 
								(num)*32, 64,
								null);
						continue;
					}
					
					switch(floor[i][j]) {
					//case 0就是画路，所以画一次就好
					case 0:
						break;
					case 1:
						g.drawImage(map.get("things").getImage(),
								0+j*32, (i-1)*32,
								32+j*32, 32+i*32,
								64, 0, 
								96, 64,
								null); 
						break;
					case 2:
						g.drawImage(map.get("things").getImage(),
								0+j*32, (i-1)*32,
								32+j*32, 32+i*32,
								96, 0, 
								128, 64,
								null);
						break;
					case 4:
						g.drawImage(map.get("things").getImage(),
								0+j*32, (i-1)*32, 
								32+j*32, 32+i*32,
								128, 0, 
								160, 64,
								null);
						break;
					case 5:
						g.drawImage(map.get("things").getImage(),
								0+j*32, (i-1)*32,
								32+j*32, 32+i*32,
								32, 0, 
								64, 64,
								null);
						break;
					}
				}
			}
		}catch(NullPointerException e) {
			
		}
	}
	
}
