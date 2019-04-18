package com.tedu.main;

import com.tedu.bgm.Bgm;
import com.tedu.frame.StartFrame;

/**
 * 面向对象中 自己的事情自己做-》明确的分工
 * 
 */
public class GameStart {
private static StartFrame sjf;
	
	public static void main(String[] args) {
//		资源加载
//		窗体加载（自动化。。）				
		sjf=new StartFrame();
		sjf.setVisible(true);
		Bgm bgm=new Bgm("music.wav");
		bgm.start();

	}

}
