package com.tedu.main;

import com.tedu.frame.MyJFrame;
import com.tedu.frame.MyJPanel;
import com.tedu.thread.GameListener;
/**
 * ��������� �Լ��������Լ���-����ȷ�ķֹ�
 * 
 */
public class GameStart {
//	������Ϸ����ڣ�����
	public static void main(String[] args) {
//		��Դ����
//		������أ��Զ���������
		MyJFrame jf=new MyJFrame();
		MyJPanel jp=new MyJPanel();
		GameListener listener=new GameListener();
		jf.setKeyListener(listener);
		jf.setJp(jp);//ע��
//		��������
		jf.addListener();
		jf.addJPanels();
		jf.addJPanels();//����jp
//		��Ϸ��������ʼ��
		jf.start();

	}

}
