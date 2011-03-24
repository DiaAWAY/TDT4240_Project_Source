package org.group20.sunstruck.gui;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class GUI {	
	Sprite controlCircle;
	Sprite fireButton;
	
	ArrayList<Sprite> spriteList = new ArrayList<Sprite>();

	public GUI(){
		/*
		//Gets the textures.
		TextureRegion controlCircleTexture = Game.getInstance().getTextureAtlas().findRegion("controlCircle");
		TextureRegion fireButtonTexture = Game.getInstance().getTextureAtlas().findRegion("fireButton");
		*/
		
		TextureRegion controlCircleTexture = new TextureRegion(new Texture(Gdx.files.internal("data/controlCircle.png")));
		TextureRegion fireButtonTexture = new TextureRegion(new Texture(Gdx.files.internal("data/fireButton.png")));
		
		//Makes the sprites with corresponding textures.
		controlCircle = new Sprite(controlCircleTexture);
		fireButton = new Sprite(fireButtonTexture);
		
		//Sets the positions of the control sprites.
		controlCircle.setPosition(0, Gdx.graphics.getWidth()-controlCircle.getWidth());
		fireButton.setPosition(0, 0);
		
		spriteList.add(controlCircle);
		spriteList.add(fireButton);
		
	}
	
	public Sprite getControlCircle(){
		return controlCircle;
	}
	
	public Sprite getFireButton(){
		return fireButton;
	}
	
	public ArrayList<Sprite> getSpriteList(){
		return spriteList;
	}
	
}
