package com.tedu.frame;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.tedu.frame.StartFrame.BackgroundPanel;
import com.tedu.main.GameStart;
import com.tedu.thread.GameThread;

public class HelpFrame extends JFrame{
	private HelpFrame jf;
	public HelpFrame() {
		
		jf=this;
		Container jp=this.getContentPane();
		jp.setLayout(null);		

		
		JButton btn_next=new JButton();
		btn_next.setBounds(445,540, 150,44);
		setbutton(btn_next,"img/BGpicture/next.png");
		jp.add(btn_next);
		
		JButton btn_back=new JButton();
		btn_back.setBounds(50,540, 150,44);
		setbutton(btn_back,"img/BGpicture/back.png");
		jp.add(btn_back);
		
		BackgroundPanel bgp=new BackgroundPanel((new ImageIcon("img/BGpicture/instruction.png")).getImage());
		bgp.setBounds(0,0,655,680);
		jp.add(bgp);
		
    	btn_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jf.setVisible(false);
				StartFrame sf=new StartFrame();
				sf.setVisible(true);
			}
    	});
		
    	
    	btn_next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jf.setVisible(false);
				HelpFrame2 hf2=new HelpFrame2();
				hf2.setVisible(true);
			}
    	});
		
		jf.setSize(657,675);
		jf.setTitle("��Ϸ����");
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

