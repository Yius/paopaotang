package com.tedu.main;

import com.tedu.bgm.Bgm;
import com.tedu.frame.StartFrame;

/**
 * ��������� �Լ��������Լ���-����ȷ�ķֹ�
 * 
 */
public class GameStart {
private static StartFrame sjf;
	
	public static void main(String[] args) {
//		��Դ����
//		������أ��Զ���������				
		sjf=new StartFrame();
		sjf.setVisible(true);
		Bgm bgm=new Bgm("music.wav");
		bgm.start();

	}

}
