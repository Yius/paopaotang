package com.tedu.main;

import com.tedu.frame.MyJFrame;
import com.tedu.frame.MyJPanel;
import com.tedu.thread.GameListener;
/**
 * 面向对象中 自己的事情自己做-》明确的分工
 * 
 */
public class GameStart {
//	整个游戏的入口，启动
	public static void main(String[] args) {
//		资源加载
//		窗体加载（自动化。。）
		MyJFrame jf=new MyJFrame();
		MyJPanel jp=new MyJPanel();
		GameListener listener=new GameListener();
		jf.setKeyListener(listener);
		jf.setJp(jp);//注入
//		监听加载
		jf.addListener();
		jf.addJPanels();
		jf.addJPanels();//加载jp
//		游戏启动（开始）
		jf.start();

	}

}
