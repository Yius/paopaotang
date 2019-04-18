package com.tedu.frame;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.tedu.model.manager.MapManager;
import com.tedu.thread.GameListener;

public class OverJFrame extends JFrame{
	private JFrame jf;
	//private JPanel jp;
	
	
	public OverJFrame() {
		jf=this;
		Container jp=this.getContentPane();
		jp.setLayout(null);		
		
		//创建 重玩游戏，退出游戏
		JButton replay=new JButton();
		JButton exit=new JButton();
		replay.setBounds(250, 400, 150,44);
		setbutton(replay,"img/BGpicture/replay.png");
		exit.setBounds(450, 400, 150,44);
		setbutton(exit,"img/BGpicture/exit.png");
		jp.add(replay);
		jp.add(exit);
		
		BackgroundPanel bgp=new BackgroundPanel((new ImageIcon("img/BGpicture/gameover.png")).getImage());
		bgp.setBounds(0,0,655,520);
		jp.add(bgp);		     
		
		
		//重玩游戏按钮事件：显示MyJFrame及MyJPanel(游戏界面)
		replay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 重玩游戏
				jf.dispose();	//释放GameOver窗体
				MyJFrame mjf=new MyJFrame();	//新建游戏MyJFrame窗体
				MyJPanel mjp=new MyJPanel();	//新建游戏MyJPanel面板
				GameListener listener=new GameListener();	//添加监听
				mjf.setKeyListener(listener);
				mjf.setJp(mjp);//注入
				mjf.addListener();
				mjf.addJPanels();//加载jp
				//游戏启动
				mjf.start();
			}
		});
		
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 退出游戏
				System.exit(0);
			}	
		});
		
		jf.setSize(655,520);
		jf.setTitle("泡泡堂");
		jf.setResizable(false);//设置窗体不可以修改大小
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭状态
		jf.setLocationRelativeTo(null);//居中

	}
	

	class BackgroundPanel extends JPanel{   //设置背景样式
		private Image im;	
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
	
	
	public void setbutton(JButton jb,String url) {	//设置按钮样式
		jb.setMargin(new Insets(0,0,0,0));
		jb.setFocusPainted(false);
		ImageIcon ii = new ImageIcon(url);
		Image temp = ii.getImage().getScaledInstance(jb.getWidth()+30, jb.getHeight()+30, ii.getImage().SCALE_DEFAULT);
		ii = new ImageIcon(temp);
		jb.setIcon(ii);
	}
	

}
