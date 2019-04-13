package com.tedu.model.manager;

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


/**
 * 
 * 画地板用的，大于等于三百为箱子种类，case 3原用于箱子，发现不适合就删去了
 */
	public void drawFloor(Graphics g) {
		int[][] floor = mapManager.getFloor();
		Map<String, ImageIcon> map = ElementLoad.getElementLoad().getMap();
		Random r = new Random();
		try {
			for(int i=0;i<floor.length;++i) {
				for(int j=0;j<floor[0].length;++j) {
					
					g.drawImage(map.get("things").getImage(),
							0+j*32, (i)*32, 
							32+j*32, 32+i*32,
							0, 32, 
							32, 64,
							null);
					
					if(floor[i][j]>=300) {
						int num = floor[i][j]-300;
						int quotient = num/4;
						int remainder = num%4;
						if(remainder == 0) {
							remainder = 4;
							--quotient;
						}
						g.drawImage(map.get("box").getImage(),
								0+j*32, (i)*32,
								32+j*32, 32+i*32,
								(remainder-1)*32, (quotient)*32, 
								remainder*32, (quotient+1)*32,
								null);
						continue;
					}
					
					switch(floor[i][j]) {
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
