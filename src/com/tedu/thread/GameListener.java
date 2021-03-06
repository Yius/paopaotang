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
	
	@Override  //按下  坐 37 上38  右39 下40
	public void keyPressed(KeyEvent e) {
		/*
		 * 为了不报异常以及使得按键正常使用，在没有更好的方法时必须将两个玩家list分开
		 */
		list = ElementManager.getManager().getElementList("playerOne");
		list2 = ElementManager.getManager().getElementList("playerTwo");
		//TODO 此处应该存在问题
		//玩家一
		Player player = null;
		if(list.size()!=0) {
			player=(Player)list.get(0);
		}
		//玩家二
		Player player2 = null;
		if(list2.size()!= 0) {
			player2 = (Player)list2.get(0);
		}
		switch(e.getKeyCode()){
		case 37:
			if(player != null) {
				if(!player.isReversal())
					player.setMoveType(MoveType.left);
				else
					player.setMoveType(MoveType.right);
			}
			break;
		case 38:
			if(player != null) {
				if(!player.isReversal())
					player.setMoveType(MoveType.top);
				else
					player.setMoveType(MoveType.down);
					
			}
			break;
		case 39:
			if(player != null) {
				if(!player.isReversal())
					player.setMoveType(MoveType.right);
				else
					player.setMoveType(MoveType.left);
			}
			break;
		case 40:
			if(player != null) {
				if(!player.isReversal())
					player.setMoveType(MoveType.down);
				else
					player.setMoveType(MoveType.top);
			}
			break;
		//回车
		case 10:
			if(player != null) {
				player.setPk(true);
			}
			break;
		//b
		case 66:
			if(player2 != null) {
				player2.setPk(true);
			}
			break;
			//a
		case 65:
			if(player2 != null) {
				if(!player2.isReversal())
					player2.setMoveType(MoveType.left);
				else
					player2.setMoveType(MoveType.right);
			}
			break;
		//s
		case 83:
			if(player2 != null) {
				if(!player2.isReversal())
					player2.setMoveType(MoveType.down);
				else
					player2.setMoveType(MoveType.top);
			}
			break;
		//d
		case 68:
			if(player2 != null) {
				if(!player2.isReversal())
					player2.setMoveType(MoveType.right);
				else
					player2.setMoveType(MoveType.left);
			}
			break;
		//w
		case 87:
			if(player2 != null) {
				if(!player2.isReversal())
					player2.setMoveType(MoveType.top);
				else
					player2.setMoveType(MoveType.down);
			}
			break;
		}
	}

	@Override //松开
	public void keyReleased(KeyEvent e) {
		list=ElementManager.getManager().getElementList("playerOne");
		list2 = ElementManager.getManager().getElementList("playerTwo");
		//玩家一
		Player player= null;
		if(list.size()!=0) {
			player = (Player)list.get(0);
		}
		//玩家二
		Player player2 = null;
		if(list2.size()!=0) {
			player2 = (Player)list2.get(0);
		}
		switch(e.getKeyCode()){
		case 37:
			if(player != null) {
				if(player.isReversal()) {
					if(player.getMoveType() == MoveType.right)
						player.setMoveType(MoveType.stop);
				}else {
					if(player.getMoveType() == MoveType.left)
						player.setMoveType(MoveType.stop);
				}
			}
			break;
		case 38:
			if(player != null) {
				if(player.isReversal()) {
					if(player.getMoveType() == MoveType.down)
						player.setMoveType(MoveType.stop);
				}else {
					if(player.getMoveType() == MoveType.top)
						player.setMoveType(MoveType.stop);
				}
			}
			break;
		case 39:
			if(player != null) {
				if(player.isReversal()) {
					if(player.getMoveType() == MoveType.left)
						player.setMoveType(MoveType.stop);
				}else {
					if(player.getMoveType() == MoveType.right)
						player.setMoveType(MoveType.stop);
				}
			}
			break;
		case 40:
			if(player != null) {
				if(player.isReversal()) {
					if(player.getMoveType() == MoveType.top)
						player.setMoveType(MoveType.stop);
				}else {
					if(player.getMoveType() == MoveType.down)
						player.setMoveType(MoveType.stop);
				}
			}
			break;
		//回车
		case 10:
			if(player != null) {
				player.setPk(false);
			}
			break;
		//b
		case 66:
			if(player2 != null) {
				player2.setPk(false);
			}
			break;
		//a
		case 65:
			if(player2 != null) {
				if(player2.isReversal()) {
					if(player2.getMoveType() == MoveType.right)
						player2.setMoveType(MoveType.stop);
				}else {
					if(player2.getMoveType() == MoveType.left)
						player2.setMoveType(MoveType.stop);
				}
			}
			break;
		//s
		case 83:
			if(player2 != null) {
				if(player2.isReversal()) {
					if(player2.getMoveType() == MoveType.top)
						player2.setMoveType(MoveType.stop);
				}else {
					if(player2.getMoveType() == MoveType.down)
						player2.setMoveType(MoveType.stop);
				}
			}
			break;
		//d
		case 68:
			if(player2 != null) {
				if(player2.isReversal()) {
					if(player2.getMoveType() == MoveType.left)
						player2.setMoveType(MoveType.stop);
				}else {
					if(player2.getMoveType() == MoveType.right)
						player2.setMoveType(MoveType.stop);
				}
			}
			break;
		//w
		case 87:
			if(player2 != null) {
				if(player2.isReversal()) {
					if(player2.getMoveType() == MoveType.down)
						player2.setMoveType(MoveType.stop);
				}else {
					if(player2.getMoveType() == MoveType.top)
						player2.setMoveType(MoveType.stop);
				}
			}
			break;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
