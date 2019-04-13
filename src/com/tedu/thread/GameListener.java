package com.tedu.thread;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import com.tedu.model.manager.ElementManager;
import com.tedu.model.manager.MoveType;
import com.tedu.model.vo.Player;

public class GameListener implements KeyListener{
	private List<?> list;
	
	@Override  //按下  坐 37 上38  右39 下40
	public void keyPressed(KeyEvent e) {
		list=ElementManager.getManager().getElementList("play");
		Player play=(Player)list.get(0);
		switch(e.getKeyCode()){
		case 37:play.setMoveType(MoveType.left);break;
		case 38:play.setMoveType(MoveType.top);break;
		case 39:play.setMoveType(MoveType.right);break;
		case 40:play.setMoveType(MoveType.down);break;
		case 32:play.setPk(true);break;
		}
	}

	@Override //松开
	public void keyReleased(KeyEvent e) {
		list=ElementManager.getManager().getElementList("play");
		Player play=(Player)list.get(0);
		switch(e.getKeyCode()){
		case 37:
			if(play.getMoveType() == MoveType.left)
				play.setMoveType(MoveType.stop);
			break;
		case 38:
			if(play.getMoveType() == MoveType.top)
				play.setMoveType(MoveType.stop);
			break;
		case 39:
			if(play.getMoveType() == MoveType.right)
				play.setMoveType(MoveType.stop);
			break;
		case 40:
			if(play.getMoveType() == MoveType.down)
				play.setMoveType(MoveType.stop);
			break;
		case 32:play.setPk(false);
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
