package org.group20.sunstruck.input;



import org.group20.sunstruck.Game;
import org.group20.sunstruck.gui.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Input{
	//How many simultaneous touches to the screen are to be checked. 
	final int NUMBER_OF_POINTERS = 2;
	//How fast the ship should brake.
	final float BRAKE_FACTOR = 0.93f;
	//How fast the phone must accelerate to activate the bomb.
	final float ACCELERATION_THRESHOLD = 17;
	   
	//Boolean to store whether the fire button has been pressed.
	boolean hasFired = false;
	//Boolean to store whether the accelerometer has been activated.
	boolean hasFiredBomb = false;
	//Boolean to check if the ships velocity was updated. (Needed to check if you should brake or not).
	boolean velocityChanged = false;
	
	//The control circle and fire button sprite. (Should this be stored in the Input-method?)
	Sprite controlCircle;
	Sprite fireButton;
	
	Vector2 newVelocity = new Vector2(0,0);
	
	public Input(GUI gui){
		controlCircle = gui.getControlCircle();
		fireButton = gui.getFireButton();
	}	
	
	/**
	 * Updates the ship position according to the input, checks if the fire button has been pressed, and if the accelerometer has been activated. 
	 */
	public void update(){
		//Loops through the number of pointers to check.
		for(int i = 0; i < NUMBER_OF_POINTERS; i++)
			if(Gdx.input.isTouched(i)){
				//Gets the position of the touch.
				float inputX = Gdx.input.getX(i);
				float inputY = Gdx.input.getY(i);

				//Checks if the fire button has been pressed.
				if(hitFireButton(inputX, inputY))				
					hasFired = true;
				//Checks if the touch was inside the move circle.
				else if(hitMoveCircle(inputX, inputY))
					changePlayerVelocity(inputX, inputY);
			}
		//If the position of the player has not been updated this means the move circle has not been touched and the ship should brake.
		if(!velocityChanged)
			//Brake the ship speed.
			brakePlayerSpeed();
		
		//If the accelerometer has been activated the hasFiredBomb-boolean is updated accordingly.
		if(hitAccelerometer())
			hasFiredBomb = true;
		
		velocityChanged = false;
	}
	
	
	private boolean hitAccelerometer() {
		//Get the acceleration in Z-direction and check if exceed the threshold set by ACCELERATION_THRESHOLD. 
		float acceleration = Math.abs(Gdx.input.getAccelerometerZ());
		if(Math.abs(acceleration)>=ACCELERATION_THRESHOLD)
			return true;
		else
			return false;
	}

	private boolean hitFireButton(float inputX, float inputY) {
		//Get coordinates relative to the button position (inputY might seem strange, this is because the origin has been changed by gdx for some reason).
		inputX = inputX - fireButton.getWidth()/2;
		inputY = Gdx.graphics.getHeight() - inputY - fireButton.getHeight()/2;
		
//		Bruker x og y som kateter og bruker hypotenus for Œ sjekke om trykket er innenfor sirkelen
		if (Math.sqrt(Math.pow(inputX, 2) + Math.pow(inputY, 2)) < fireButton.getHeight()/2){
			return true;
		}
		else
			return false;
	}

	private boolean hitMoveCircle(float inputX, float inputY) {
		//Get coordinates relative to the button position (inputY might seem strange, this is because the origin has been changed by gdx for some reason).
		inputX = inputX - controlCircle.getWidth()/2;
		inputY = inputY - controlCircle.getHeight()/2;
	
//		Bruker x og y som kateter og bruker hypotenus for Œ sjekke om trykket er innenfor sirkelen
		if (Math.sqrt(Math.pow(inputX, 2) + Math.pow(inputY, 2)) < controlCircle.getHeight()/2){
			return true;
		}
		else
			return false;
	}

	private void changePlayerVelocity(float inputX, float inputY){
		//Get coordinates relative to the moveCircle.		
		inputX = inputX - controlCircle.getWidth()/2;
		inputY = -(inputY - controlCircle.getHeight()/2);
		
		
		//Get how near the touch is to the edge of the move circle.
		float scaleSpeed = (float)(Math.sqrt(Math.pow(inputX, 2) + Math.pow(inputY, 2))/(controlCircle.getHeight()/2));
		scaleSpeed = (float) Math.pow(scaleSpeed, 0.7);
		
		//If the touch is close to the center of the move circle, the scaleSpeed variable will be set to 0 (i.e. no movement).
		if(scaleSpeed < 0.2)
			scaleSpeed = 0;
		
		//Get the unit vector (enhetsvektor)
		float x = inputX/(Math.abs(inputX)+Math.abs(inputY));
		float y = inputY/(Math.abs(inputY)+Math.abs(inputX));
		
		//Get the players speed, to adjust the speed according to updates.
		float playerSpeed = Game.getInstance().getPlayer().getSpeed();
		
		playerSpeed = 100;
		
		x = x * playerSpeed * scaleSpeed;
		y = y * playerSpeed * scaleSpeed;
		
		//Set new force
		newVelocity.set(x, y);
		
		velocityChanged = true;
	}

	private void brakePlayerSpeed(){
		//Brake the ships speed according to the BRAKE_FACTOR constant and update new position.
		newVelocity.mul(BRAKE_FACTOR);
	}
	
	/**
	 * Get the new ship position.
	 * @return new ship position.
	 */
	public Vector2 getNewVelocity(){
		return newVelocity;
	}
	
	/**
	 * Returns whether the fireButton has been pressed since the last call to this method. 
	 * @return true if fire button was pressed.
	 */
	public boolean getHasFired(){
		boolean temp = hasFired;
		hasFired = false;
		return temp;
	}
	
	/**
	 * Returns whether the the accelerometer has activated the firing of a bomb since the last call to this method. 
	 * @return true if bomb was fired.
	 */
	public boolean getHasFiredBomb(){
		boolean temp = hasFiredBomb;
		hasFiredBomb = false;
		return temp;
	}


}
