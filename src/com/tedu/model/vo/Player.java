package com.tedu.model.vo;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.manager.ElementManager;

public class Player extends Character{
	
	private int num;//����
	private ImageIcon img;
	
	//TODO  �ⲿ��д�ĺ��ң�֮��Ҳ��Ҫ�Ķ�	���ݵ������
	private int maxBubbleNums = 1;	//��ǰ�ܹ����õ����������   , Ĭ���������Ϊ5
	private int currentBubbleNums = 1;	//��ǰ�ܹ����õ�������
	private int reloadTime = 31; //���Ǹ������ݱ�ըʱ��ó���
	private int time = 0;	//��ʱ�Դﵽ�����ͷŵ�����
	private Queue<Integer> queue;	//��¼���ݱ�ըʱ��
	private int lastTime = 0;	//������¼�����ϴα�ըʱ��
	private int target = reloadTime;		//װ��ʱ��
	private boolean isFirst = true;		//�Ƿ��ǵ�һ�μ�ʱ
	private int count = 0;		//���м�ʱ
	
	//��ҩƿ���
	private int boomScale = 0 ; //��ǰ���ݵ����� , Ĭ���������Ϊ4
	//��ͷ���
	private boolean isReversal;//�ж��Ƿ���
	//ң�������
//	private int stopTime =0;//д��Character�����ΪҪ�õ�move()
	//���˿����
	private int maxSuperCardNums = 5; //���˿���������
	private int currentSuperCardNums;//��ǰ���˿�������
	//�޵�״̬��ʱ(�ڳ��г��˿�����±�����ը��ʱ��50֡�������˺�״̬)
	private int invincibleTime = 0;
	
//	private StopMove stopMove;
//	��ǰ������ơ�������
	
	private boolean  pk;//����״̬��Ĭ��Ϊ false
	
	
	
	public Player(int x,int y,int w,int z,ImageIcon img){
		super(x,y,w,z);
		this.img=img;//�ͽ�ԭ��  
		num=0;
		pk=false;
		queue = new LinkedList<>();
//		stopMove=StopMove.None;ԭ�����ж���ײ�����ڸо�û�ã���ɾȥ
	}
	
	//����ֱ�ӵ�����������������õ�һ����Ҷ���  str��������ľ�����Ҷ������Ϣ
	public static Player createPlayer(String str){

		String [] arr=str.split(",");
		int x=Integer.parseInt(arr[3]);
		int y=Integer.parseInt(arr[4]);
		int w=Integer.parseInt(arr[5]);
		int h=Integer.parseInt(arr[6]);
		ImageIcon img=
				ElementLoad.getElementLoad().getMap().get(arr[0]);
		return new Player(x,y,w,h,img);
	}
	
	@Override
	public void showElement(Graphics g) {
		g.drawRect(getX(), getY(), getW(), getH());
		g.drawImage(img.getImage(), 
			getX(), getY(),                  //��Ļ���Ͻ�����
			getX()+getW(), getY()+getH(),    //��Ļ���½�����
				50*getMoveX()+26, (getMoveY())*100+40,    //ͼƬ���Ͻ�����        60 ,0
				50*(getMoveX()+1)+26, (getMoveY())*100+100,    //ͼƬ���½�����  120,60
				null);
	}
	
//	��д�����ģ��
	public void update(){
		super.update();//���û����仰������ �����ƶ���ģ��
		addBubble();//׷��
		updateImage();
	}
	
	public void updateImage(){
		setMoveX(getMoveX()%6+2);
	}
	
	public void addBubble(){
		if(!pk || currentBubbleNums <= 0){//���PK��false�����޵��ɷžͲ���Ҫ ����ӵ�
			return;
		}
		List<SuperElement> list1=
				ElementManager.getManager().getElementList("bubble");
		Map<String, List<String>> map=
				ElementLoad.getElementLoad().getPlaymap();
		//TODO ����֮������
		//TODO ������ʱ�����ǻ�������ģ����ڿ��ܻ�����bug
		List<String> list = map.get("playerOne");
		String s=list.get(0);
		long x = Math.round((double)(getX())/32);
		long y = Math.round((double)(getY())/32);
		list1.add(Bubble.createBubble((int)x*32, (int)y*32, s,boomScale));
		--currentBubbleNums;
		if(!isFirst) {
			//ѭ������
			queue.add(((time-lastTime+1000000000)%1000000000));
		}
		isFirst = false; 
		lastTime = time;
		pk=false;//ÿ��һ�� ֻ�ܷ���һ���ӵ�	
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public ImageIcon getImg() {
		return img;
	}
	public void setImg(ImageIcon img) {
		this.img = img;
	}
	public boolean isPk() {
		return pk;
	}
	public void setPk(boolean pk) {
		this.pk = pk;
	}
	
	public boolean isReversal() {
		return isReversal;
	}

	public void setReversal(boolean isReversal) {
		this.isReversal = isReversal;
	}

	public int getMaxSuperCardNums() {
		return maxSuperCardNums;
	}

	public void setMaxSuperCardNums(int maxSuperCardNums) {
		this.maxSuperCardNums = maxSuperCardNums;
	}

	public int getCurrentSuperCardNums() {
		return currentSuperCardNums;
	}

	public void setCurrentSuperCardNums(int currentSuperCardNums) {
		this.currentSuperCardNums = currentSuperCardNums;
	}
	

	public int getInvincibleTime() {
		return invincibleTime;
	}

	public void setInvincibleTime(int invincibleTime) {
		this.invincibleTime = invincibleTime;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		if(!isVisible()){
			List<SuperElement> list =
					ElementManager.getManager().getElementList("died");
			list.add(Died.createDied(getX(), getY(), ""));
		}
	}
	
	@Override
	public void move() {
		super.move();
		time = (time+1)%1000000000;
		countTime();
		setInvincible();
	}
	
	/*
	 * ��ʱ�õ�
	 */
	private void countTime() {
		if(currentBubbleNums == maxBubbleNums) {
			//���³�ʼ��
			time = 0;
			lastTime = 0;
			isFirst = true;
			count = 0;
			target = reloadTime;
		}
		++count;
		if(count == target) {
			++currentBubbleNums;
			count = 0;
			if(queue.size() == 0) {
				target = reloadTime;
			}else {
				target = queue.poll();
			}
		}	
	}
	public void setInvincible() {
		if(this.getInvincibleTime()>0) {
			this.setInvincibleTime(this.getInvincibleTime()-1);
			this.setVisible(true);
		}
	}
	
	
	public boolean getTool(SuperElement se) {
		Rectangle r1=new Rectangle(getX(), getY(), getW(), getH());
		Rectangle r2=new Rectangle(se.getX()+6, se.getY()+6, se.getW()-12, se.getH()-12);
		Player player = null;
		Player player2 = null;
		if(r1.intersects(r2)){
			//���ݵ��ߣ���������ҲӦ������д
			if(se instanceof BubbleTool) {
				if(maxBubbleNums<5) {
					++maxBubbleNums;
					++currentBubbleNums;
				}
			}else if(se instanceof BuleMedicine) {
				if(boomScale < 4) {
					++boomScale;
				}
			}else if(se instanceof PurpleMedicine){
				if(isReversal()) {
					setReversal(false);
				}
			}else if(se instanceof PurpleGhost) {
				if(isReversal())
					setReversal(false);
				else
					setReversal(true);
			}else if(se instanceof RedGhost) {
				List<?> list = ElementManager.getManager().getElementList("playerOne");
				List<?> list2 = ElementManager.getManager().getElementList("playerTwo");
				if(list.size()!=0)
					player = (Player) list.get(0);
				if(list2.size() != 0)
					player2 = (Player) list2.get(0);
				if(this.equals(player)) {
					if(player2!=null) {
						if(player2.isReversal())
							player2.setReversal(false);
						else
							player2.setReversal(true);
					}
				}else if(this.equals(player2)) {
					if(player!=null) {
						if(player.isReversal())
							player.setReversal(false);
						else
							player.setReversal(true);
					}
				}
			}else if(se instanceof TeleControl) {
				List<?> list = ElementManager.getManager().getElementList("playerOne");
				List<?> list2 = ElementManager.getManager().getElementList("playerTwo");
				if(list.size()!=0)
					player = (Player) list.get(0);
				if(list2.size() != 0)
					player2 = (Player) list2.get(0);
				if(this.equals(player)) {
					if(player2!=null) {
						player2.setStopTime(player2.getStopTime()+50);
					}
				}else if(this.equals(player2)) {
					if(player!=null) {
						player.setStopTime(player.getStopTime()+50);
					}
				}
			}else if(se instanceof Mine) {
				List<?> list = ElementManager.getManager().getElementList("playerOne");
				List<?> list2 = ElementManager.getManager().getElementList("playerTwo");
				if(list.size()!=0)
					player = (Player) list.get(0);
				if(list2.size() != 0)
					player2 = (Player) list2.get(0);
				if(this.equals(player)) {
					if(player2!=null) {
						if(player2.getCurrentSuperCardNums()>0) {
							player2.setCurrentSuperCardNums(player2.getCurrentSuperCardNums()-1);
						}else {
							player2.setVisible(false);
						}
					}
				}else if(this.equals(player2)) {
					if(player.getCurrentSuperCardNums()>0) {
						player.setCurrentSuperCardNums(player.getCurrentSuperCardNums()-1);
						player.setInvincibleTime(50);
					}else {
						player.setVisible(false);
					}
				}
			}else if(se instanceof SuperCard) {
				if(getCurrentSuperCardNums()<getMaxSuperCardNums()) {
					setCurrentSuperCardNums(getCurrentSuperCardNums()+1);
				}
			}
			return true;
		}
		return false;
	}
	
	/*
	public void setStopMove(StopMove stopMove) {
		this.stopMove = stopMove;
	}*/
	
	
}
