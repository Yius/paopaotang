package com.tedu.thread;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import com.tedu.model.manager.ElementManager;
import com.tedu.model.manager.MoveType;
import com.tedu.model.vo.Player;

public class GameListener implements KeyListener{
	private List<?> list;
	private List<?> list2;
	
	@Override  //����  �� 37 ��38  ��39 ��40
	public void keyPressed(KeyEvent e) {
		/*
		 * Ϊ�˲����쳣�Լ�ʹ�ð�������ʹ�ã���û�и��õķ���ʱ���뽫�������list�ֿ�
		 */
		list = ElementManager.getManager().getElementList("playerOne");
		list2 = ElementManager.getManager().getElementList("playerTwo");
		//TODO �˴�Ӧ�ô�������
		//���һ
		Player player = null;
		if(list.size()!=0) {
			player=(Player)list.get(0);
		}
		//��Ҷ�
		Player player2 = null;
		if(list2.size()!= 0) {
			player2 = (Player)list2.get(0);
		}
		switch(e.getKeyCode()){
		case 37:
			if(player != null) {
				player.setMoveType(MoveType.left);
			}
			break;
		case 38:
			if(player != null) {
				player.setMoveType(MoveType.top);
			}
			break;
		case 39:
			if(player != null) {
				player.setMoveType(MoveType.right);
			}
			break;
		case 40:
			if(player != null) {
				player.setMoveType(MoveType.down);
			}
			break;
		//�س�
		case 10:
			if(player != null) {
				player.setPk(true);
			}
			break;
		//�ո�
		case 32:
			if(player2 != null) {
				player2.setPk(true);
			}
			break;
		//a
		case 65:
			if(player2 != null) {
				player2.setMoveType(MoveType.left);
			}
			break;
		//s
		case 83:
			if(player2 != null) {
				player2.setMoveType(MoveType.down);
			}
			break;
		//d
		case 68:
			if(player2 != null) {
				player2.setMoveType(MoveType.right);
			}
			break;
		//w
		case 87:
			if(player2 != null) {
				player2.setMoveType(MoveType.top);
			}
			break;
		}
	}

	@Override //�ɿ�
	public void keyReleased(KeyEvent e) {
		list=ElementManager.getManager().getElementList("playerOne");
		list2 = ElementManager.getManager().getElementList("playerTwo");
		//���һ
		Player player= null;
		if(list.size()!=0) {
			player = (Player)list.get(0);
		}
		//��Ҷ�
		Player player2 = null;
		if(list2.size()!=0) {
			player2 = (Player)list2.get(0);
		}
		switch(e.getKeyCode()){
		case 37:
			if(player != null && player.getMoveType() == MoveType.left)
				player.setMoveType(MoveType.stop);
			break;
		case 38:
			if(player != null && player.getMoveType() == MoveType.top)
				player.setMoveType(MoveType.stop);
			break;
		case 39:
			if(player != null && player.getMoveType() == MoveType.right)
				player.setMoveType(MoveType.stop);
			break;
		case 40:
			if(player != null && player.getMoveType() == MoveType.down)
				player.setMoveType(MoveType.stop);
			break;
		//�س�
		case 10:
			if(player != null) {
				player.setPk(false);
			}
			break;
		//�ո�
		case 32:
			if(player2 != null) {
				player2.setPk(false);
			}
			break;
		//a
		case 65:
			if(player2 != null && player2.getMoveType() == MoveType.left) {
				player2.setMoveType(MoveType.stop);
			}
			break;
		//s
		case 83:
			if(player2 != null &&player2.getMoveType() == MoveType.down) {
				player2.setMoveType(MoveType.stop);
			}
			break;
		//d
		case 68:
			if(player2 != null &&player2.getMoveType() == MoveType.right) {
				player2.setMoveType(MoveType.stop);
			}
			break;
		//w
		case 87:
			if(player2 != null &&player2.getMoveType() == MoveType.top) {
				player2.setMoveType(MoveType.stop);
			}
			break;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
