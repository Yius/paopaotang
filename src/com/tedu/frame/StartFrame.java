package com.tedu.frame;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.tedu.frame.OverJFrame.BackgroundPanel;
import com.tedu.thread.GameListener;

public class StartFrame extends JFrame{
	private JFrame jf;
	
	public StartFrame() {
	jf=this;
	Container jp=this.getContentPane();
	jp.setLayout(null);		
	
	//开始游戏
	JButton btn_start=new JButton();
	btn_start.setBounds(380,280, 150,44);
	setbutton(btn_start,"img/BGpicture/start.png");
	jp.add(btn_start);

	//退出游戏
	JButton btn_exit = new JButton();
	btn_exit.setBounds(380,420, 150,44);
	setbutton(btn_exit,"img/BGpicture/exit.png");
	jp.add(btn_exit);
	
	//规则说明
	JButton btn_help = new JButton();
	btn_help.setBounds(380,350, 150,44);
	setbutton(btn_help,"img/BGpicture/help.png");
	jp.add(btn_help);
	
	//背景图片
	BackgroundPanel bgp=new BackgroundPanel((new ImageIcon("img/BGpicture/titles.png")).getImage());
	bgp.setBounds(0,0,655,680);
	jp.add(bgp);		     
	
	
	//开始游戏按钮事件：显示MyJFrame及MyJPanel(游戏界面)
	btn_start.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// 开始游戏
			jf.dispose();
			MyJFrame mjf=new MyJFrame();
			MyJPanel mjp=new MyJPanel();
			GameListener listener=new GameListener();
			mjf.setKeyListener(listener);
			mjf.setJp(mjp);//注入
			mjf.addListener();
			mjf.addJPanels();//加载jp
//			游戏启动（开始）
			mjf.start();

		}
	});
	//规则说明事件
	btn_help.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			jf.setVisible(false);
			HelpFrame hlf=new HelpFrame();
			hlf.setVisible(true);
		}
	});
	
	//退出游戏事件
	btn_exit.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	});
	
	jf.setSize(657,675);
	jf.setTitle("泡泡堂");
	jf.setResizable(false);//设置窗体不可以修改大小
	jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭状态
	jf.setLocationRelativeTo(null);//居中

}


class BackgroundPanel extends JPanel{
	Image im;	
	public BackgroundPanel(Image im)	
	{		
		this.im=im;		
		this.setOpaque(true);	
	}		
	public void paintComponent(Graphics g)	
	{		
		super.paintComponents(g);		
		g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this);			
	}
}


public void setbutton(JButton jb,String url) {
	jb.setMargin(new Insets(0,0,0,0));
	jb.setFocusPainted(false);
	ImageIcon ii = new ImageIcon(url);
	Image temp = ii.getImage().getScaledInstance(jb.getWidth()+30, jb.getHeight()+30, ii.getImage().SCALE_DEFAULT);
	ii = new ImageIcon(temp);
	jb.setIcon(ii);
 }
}