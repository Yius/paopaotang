package com.tedu.model.vo;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.tedu.model.manager.MoveType;

/**
 * 
 * 角色的共同父类
 *
 */
public abstract class Character extends SuperElement{

	private boolean died;
	private MoveType moveType;//0 1 2 3
	private int moveStep = 5; //步长
	private int moveX;
	private int moveY;
	
	public Character() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Character(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
		moveType=MoveType.stop;
	}
	
	
	public int getMoveX() {
		return moveX;
	}
	public void setMoveX(int moveX) {
		this.moveX = moveX;
	}
	
	public int getMoveY() {
		return moveY;
	}
	public void setMoveY(int moveY) {
		this.moveY = moveY;
	}
	public boolean isDied() {
		return died;
	}
	public void setDied(boolean died) {
		this.died = died;
	}
	public int getMoveStep() {
		return moveStep;
	}
	public void setMoveStep(int moveStep) {
		this.moveStep = moveStep;
	}
	public MoveType getMoveType() {
		return moveType;
	}
	public void setMoveType(MoveType moveType) {
		this.moveType = moveType;
	}
	
	/*
	 * 碰撞检测
	 */
	public void crashDetect(SuperElement se){
		Rectangle r1=new Rectangle(getX(), getY(), getW(), getH());
		Rectangle r2=new Rectangle(se.getX(), se.getY(), se.getW(), se.getH());
		if(r1.intersects(r2)){
			switch(moveType) {
			case top:setY(getY()+moveStep);break;
			case down:setY(getY()-moveStep);break;
			case left:setX(getX()+moveStep);break;
			case right:setX(getX()-moveStep);break;
			}
		}
	}
	
	
	public void move(){
		switch(moveType){
		case top:setY(getY()-moveStep);moveY=3;break;
		case left:setX(getX()-moveStep);moveY=1;break;
		case right:setX(getX()+moveStep);moveY=2;break;
		case down:setY(getY()+moveStep);moveY=0;break;
		case stop:moveX=-2;break;
		}
	}
	
}
