package org.group20.sunstruck.gui;

import org.group20.sunstruck.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class GUI {
	Sprite controlCircle;
	Sprite fireButton;

	public GUI(){
		//Gets the textures.
		TextureRegion controlCircleTexture = Game.getInstance().getTextureAtlas().findRegion("controlCircle");
		TextureRegion fireButtonTexture = Game.getInstance().getTextureAtlas().findRegion("fireButton");
	
		//Makes the sprites with corresponding textures.
		controlCircle = new Sprite(controlCircleTexture);
		fireButton = new Sprite(fireButtonTexture);
		
		//Sets the positions of the control sprites.
		controlCircle.setPosition(0, 0);
		fireButton.setPosition(0, Gdx.graphics.getWidth()-fireButton.getWidth());
		
		
	}
	
	public Sprite getControlCircle(){
		return controlCircle;
	}
	
	public Sprite getFireButton(){
		return fireButton;
	}
	
}
