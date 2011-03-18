package org.tdt4240.group20.sunstruck.gameobject;


public class Player extends GameObject {
	
	private int score;
	
	public Player() {
		this(0);
	}
	
	public Player(int startScore) {
		setScore(startScore);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}

}
