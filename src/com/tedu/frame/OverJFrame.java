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
		
		//���� ������Ϸ���˳���Ϸ
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
		
		
		//������Ϸ��ť�¼�����ʾMyJFrame��MyJPanel(��Ϸ����)
		replay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ������Ϸ
				jf.dispose();	//�ͷ�GameOver����
				MyJFrame mjf=new MyJFrame();	//�½���ϷMyJFrame����
				MyJPanel mjp=new MyJPanel();	//�½���ϷMyJPanel���
				GameListener listener=new GameListener();	//��Ӽ���
				mjf.setKeyListener(listener);
				mjf.setJp(mjp);//ע��
				mjf.addListener();
				mjf.addJPanels();//����jp
				//��Ϸ����
				mjf.start();
			}
		});
		
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// �˳���Ϸ
				System.exit(0);
			}	
		});
		
		jf.setSize(655,520);
		jf.setTitle("������");
		jf.setResizable(false);//���ô��岻�����޸Ĵ�С
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ùر�״̬
		jf.setLocationRelativeTo(null);//����

	}
	

	class BackgroundPanel extends JPanel{   //���ñ�����ʽ
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
	
	
	public void setbutton(JButton jb,String url) {	//���ð�ť��ʽ
		jb.setMargin(new Insets(0,0,0,0));
		jb.setFocusPainted(false);
		ImageIcon ii = new ImageIcon(url);
		Image temp = ii.getImage().getScaledInstance(jb.getWidth()+30, jb.getHeight()+30, ii.getImage().SCALE_DEFAULT);
		ii = new ImageIcon(temp);
		jb.setIcon(ii);
	}
	

}
