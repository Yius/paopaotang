package com.tedu.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tedu.model.manager.ElementManager;
import com.tedu.model.vo.Enemy;
import com.tedu.model.vo.Player;
import com.tedu.model.vo.SuperElement;

//java�ǵ��̳У���ʵ�֡�ͨ�� �ڲ���ķ�ʽ���ֲ����̳е�ȱ��
public class GameThread extends Thread{
//	��ʱ����
	private int time;
	private int time1=0;
//	��������� �� ˼��Ľ��� ����ͨ���ܶ����Ŀ����
//	�����Ŀ���࣬�� �ع�����Ŀ
	public void run(){
//		while(true){   //��Ϸ�������
	//		��ѭ���������б�����״̬�����п���
	//		1.���ص�ͼ������
			loadElement();
	//		2.��ʾ�����ͼ(����,�Զ���(�ƶ�����ײ))
			time=0;
			runGame();
	//		3.��������ͼ
			overGame();
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
//			}
		}
	}
	private void runGame() {
		while(true){  //ÿ���������ʱ���״̬
			Map<String,List<SuperElement>> map=
					ElementManager.getManager().getMap();
			Set<String> set=map.keySet();
			
				
			for(String key:set){//�������ڱ����Ĺ����У��������ڵ�Ԫ�ز����� ���ӻ���ɾ��
				List<SuperElement> list=map.get(key);
				for(int i=list.size()-1;i>=0;--i){
					list.get(i).update();
					if(!list.get(i).isVisible()){
						list.remove(i);
					}
				}
				
			}
//			ʹ��һ�������ķ����������ж�
			PK();
//			д�ɻ�����ӵ� list��map  //��Ϸ�����̿��� 
			linkGame();
			//������ͨ�ص� ���� runGame����
			try {
				sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			time++;
		}
	}
	private void PK() {
		List<SuperElement> list1=
				ElementManager.getManager().getElementList("play");
		List<SuperElement> list2=
				ElementManager.getManager().getElementList("tree");
		List<SuperElement> list3=
				ElementManager.getManager().getElementList("house");
		List<SuperElement> list4=
				ElementManager.getManager().getElementList("box");
		List<SuperElement> list5=
				ElementManager.getManager().getElementList("playFire");
		pkWithRoadBlock(list1,list2);//·��
		pkWithRoadBlock(list1,list3);
		pkWithRoadBlock(list1,list4);
		listPK(list5,list1);
		listPK(list5,list4);
//		���Ծ��бȽ�
	}
	
//	���ֵĴ��� �ǿ����ظ�ʹ�õġ�
	public void listPK(List<SuperElement> list1,//�ж������Ƿ�ը���˻�����
			List<SuperElement> list2){
		for(int i=0;i<list1.size();i++){
			for(int j=0;j<list2.size();j++){
				if(list1.get(i).gamePK(list2.get(j))&&list1.get(i).isBoomed()){
					System.out.println(list2.get(j));
						list2.get(j).setVisible(false);
						//list1.get(i).setVisible(false);
				}
			}
		}
	}
	
	
	/*
	 * ����һ��ʾ�������������ж���������᲻����ײ��ʵ���Ͽ����ƶ���֮���ѵڶ���������������һ�ֲ��ɴ�͸����Ʒ��list
	 * 
	 */
	private void pkWithRoadBlock(List<SuperElement> characters,List<SuperElement> tree) {//�ж�·���Ƿ��谭�ƶ�
		for(int i=0;i<characters.size();i++){
			for(int j=0;j<tree.size();j++){
				Player p = (Player)(characters.get(i));
				if(characters.get(i).gameCrash(tree.get(j))){
					switch(p.getMoveType()) {
//					�Զ������ø�ֵ������û�����ô��
					case top:p.setY(p.getY()+5);break;
					case down:p.setY(p.getY()-5);break;
					case left:p.setX(p.getX()+5);break;
					case right:p.setX(p.getX()-5);break;
					}
				}
			}
		}
	}
	
	
	//��Ϸ�����̿��� 
	public void linkGame(){
		ElementManager.getManager().linkGame(time);
	}
	
	private void overGame() {
		// TODO Auto-generated method stub
		
	}
//	���ƽ���,���ǣ���Ϊ ���ƣ��벻Ҫ�Ӵ� load
	private void loadElement() {
		ElementManager.getManager().load();
		
	}
	
}
