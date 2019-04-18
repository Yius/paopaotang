package com.tedu.thread;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.tedu.model.manager.ElementManager;
import com.tedu.model.manager.MapManager;
import com.tedu.model.vo.Boom;
import com.tedu.model.vo.Player;
import com.tedu.model.vo.SuperElement;

//java�ǵ��̳У���ʵ�֡�ͨ�� �ڲ���ķ�ʽ���ֲ����̳е�ȱ��
public class GameThread extends Thread{
//	��ʱ����
	private int time;
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
			/*ԭ�����ڷɻ��ģ����ڲ���Ҫ��ɾȥ��
//			д�ɻ�����ӵ� list��map  //��Ϸ�����̿��� 
			linkGame();
			*/
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
		List<SuperElement> playerOneList = ElementManager.getManager().getElementList("playerOne");
		List<SuperElement> playerTwoList = ElementManager.getManager().getElementList("playerTwo");
		List<SuperElement> treeList = ElementManager.getManager().getElementList("tree");
		List<SuperElement> houseList = ElementManager.getManager().getElementList("house");
		List<SuperElement> boxList = ElementManager.getManager().getElementList("box");
		List<SuperElement> bushList = ElementManager.getManager().getElementList("bush");
		List<SuperElement> boomList = ElementManager.getManager().getElementList("boom");
		List<SuperElement> bubbleToolList = ElementManager.getManager().getElementList("bubbleTool");
		List<SuperElement> buleMedicineList = ElementManager.getManager().getElementList("buleMedicine");
		List<SuperElement> purpleMedicineList = ElementManager.getManager().getElementList("purpleMedicine");
		List<SuperElement> purpleGhostList = ElementManager.getManager().getElementList("purpleGhost");
		List<SuperElement> redGhostList = ElementManager.getManager().getElementList("redGhost");
		List<SuperElement> teleControlList = ElementManager.getManager().getElementList("teleControl");
		List<SuperElement> mineList = ElementManager.getManager().getElementList("mine");
		List<SuperElement> superCardList = ElementManager.getManager().getElementList("superCard");
		pkWithRoadBlock(playerOneList,treeList);//·��
		pkWithRoadBlock(playerTwoList, treeList);
		pkWithRoadBlock(playerOneList,houseList);
		pkWithRoadBlock(playerTwoList, houseList);
		pkWithRoadBlock(playerOneList,boxList);
		pkWithRoadBlock(playerTwoList, boxList);
		pkWithRoadBlock(playerOneList, bushList);
		pkWithRoadBlock(playerTwoList, bushList);
		getTheTool(playerOneList, bubbleToolList);
		getTheTool(playerTwoList, bubbleToolList);
		getTheTool(playerOneList,buleMedicineList);
		getTheTool(playerTwoList,buleMedicineList);
		getTheTool(playerOneList,purpleMedicineList);
		getTheTool(playerTwoList,purpleMedicineList);
		getTheTool(playerOneList, purpleGhostList);
		getTheTool(playerTwoList, purpleGhostList);
		getTheTool(playerOneList, redGhostList);
		getTheTool(playerTwoList, redGhostList);
		getTheTool(playerOneList,teleControlList);
		getTheTool(playerTwoList, teleControlList);
		getTheTool(playerOneList,mineList);
		getTheTool(playerTwoList, mineList);
		getTheTool(playerOneList,superCardList);
		getTheTool(playerTwoList, superCardList);
		listPK(boomList,playerOneList);
		listPK(boomList,playerTwoList);
		listPK(boomList,boxList);
	}
	
//	���ֵĴ��� �ǿ����ظ�ʹ�õġ�
	//�ж������Ƿ�ը���˻�����
	public void listPK(List<SuperElement> boomList,
			List<SuperElement> otherThings){
		Random r = new Random();
		for(int i=0;i<boomList.size();i++){
			for(int j=0;j<otherThings.size();j++){
				Boom boom = (Boom)(boomList.get(i));
				/*
				 * ���������ԣ�gamePKӦ����boom���з���
				 */
				if(boom.gamePK(otherThings.get(j))){	
					//������������ܷ�ݻ����Ծ����Ƿ�������Ϊ���ɼ�
					if(otherThings.get(j) instanceof Player) {
						Player player = (Player) otherThings.get(j);
						if(player.getCurrentSuperCardNums()>0) {
							player.setCurrentSuperCardNums(player.getCurrentSuperCardNums()-1);
							player.setInvincibleTime(50);
						}else {
							player.setVisible(false);
						}
					}else {
						otherThings.get(j).setVisible(!otherThings.get(j).isCanDestroy());
					}
				}
			}
		}
	}
	
	
	/*
	 * �ж�����͹̶���᲻����ײ
	 * �ж�·���Ƿ��谭�ƶ�
	 */
	private void pkWithRoadBlock(List<SuperElement> characters,List<SuperElement> otherthings) {
		for(int i=0;i<characters.size();i++){
			for(int j=0;j<otherthings.size();j++){
				Player player = (Player)(characters.get(i));
				player.crashDetect(otherthings.get(j));
			}
		}
	}
	
	/*
	 * ��õ��߲���������
	 */
	private void getTheTool(List<SuperElement> characters,List<SuperElement> tools) {
		int[][] floor = MapManager.getMapManager().getFloor();
		for(int i=0;i<characters.size();++i){
			for(int j=0;j<tools.size();++j){
				Player player = (Player)(characters.get(i));
				if(player.getTool(tools.get(j))) {
					int row = tools.get(j).getRow();
					int col = tools.get(j).getCol();
					floor[row][col]=0;
					tools.get(j).setVisible(false);
				}
			}
		}
	}
	
	
	/*ԭ�����ڷɻ��ģ����ڲ���Ҫ��ɾȥ��
	//��Ϸ�����̿��� 
	public void linkGame(){
		ElementManager.getManager().linkGame(time);
	}
	*/
	
	
	private void overGame() {
		// TODO Auto-generated method stub
		
	}
//	���ƽ���,���ǣ���Ϊ ���ƣ��벻Ҫ�Ӵ� load
	private void loadElement() {
		ElementManager.getManager().load();
	}
	
}
