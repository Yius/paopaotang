package com.tedu.model.manager;

import java.util.List;
import java.util.Map;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.vo.Enemy;
import com.tedu.model.vo.Player;
import com.tedu.model.vo.SuperElement;

/**
 * 任务：依据参数不同，自动读取 资源，填充 vo对象数据，存储到 元素管理器
 * 	工厂的作用：将比较复杂的 构造方式 进行封装
 */
public class ElementFactory {
	
	public static SuperElement elementFactory(String name){
		Map<String, List<String>> map=
			ElementLoad.getElementLoad().getPlaymap();
		List<String> list1=ElementLoad.getElementLoad().getGameList();
		switch(name){
		case "onePlayer":
			List<String> list=map.get(name);
			String s=list.get(0);//playerA,playFire,150,300,40,40
			return Player.createPlayer(s);
		case "enemy":
			String str=list1.get(list1.size()-1);
			return Enemy.createEnemey(str);
		}
		
		return null;
	}
	
	
}
