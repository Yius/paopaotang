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
	
	//��ʼ��Ϸ
	JButton btn_start=new JButton();
	btn_start.setBounds(380,280, 150,44);
	setbutton(btn_start,"img/BGpicture/start.png");
	jp.add(btn_start);

	//�˳���Ϸ
	JButton btn_exit = new JButton();
	btn_exit.setBounds(380,420, 150,44);
	setbutton(btn_exit,"img/BGpicture/exit.png");
	jp.add(btn_exit);
	
	//����˵��
	JButton btn_help = new JButton();
	btn_help.setBounds(380,350, 150,44);
	setbutton(btn_help,"img/BGpicture/help.png");
	jp.add(btn_help);
	
	//����ͼƬ
	BackgroundPanel bgp=new BackgroundPanel((new ImageIcon("img/BGpicture/titles.png")).getImage());
	bgp.setBounds(0,0,655,680);
	jp.add(bgp);		     
	
	
	//��ʼ��Ϸ��ť�¼�����ʾMyJFrame��MyJPanel(��Ϸ����)
	btn_start.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// ��ʼ��Ϸ
			jf.dispose();
			MyJFrame mjf=new MyJFrame();
			MyJPanel mjp=new MyJPanel();
			GameListener listener=new GameListener();
			mjf.setKeyListener(listener);
			mjf.setJp(mjp);//ע��
			mjf.addListener();
			mjf.addJPanels();//����jp
//			��Ϸ��������ʼ��
			mjf.start();

		}
	});
	//����˵���¼�
	btn_help.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			jf.setVisible(false);
			HelpFrame hlf=new HelpFrame();
			hlf.setVisible(true);
		}
	});
	
	//�˳���Ϸ�¼�
	btn_exit.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	});
	
	jf.setSize(657,675);
	jf.setTitle("������");
	jf.setResizable(false);//���ô��岻�����޸Ĵ�С
	jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ùر�״̬
	jf.setLocationRelativeTo(null);//����

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