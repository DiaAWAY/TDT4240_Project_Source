package org.group20.sunstruck.gameobject;

import java.util.Iterator;

import org.group20.sunstruck.Game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.WorldManifold;

public class Laser extends GameObject{
	public boolean isBomb = false;

	@Override
	public void dispose() {
		if(!isDisposed){
			Game.getInstance().getDestroyedBodiesList().add(this.body);
			isDisposed = true;
		}
	}
	
	public String toString(){
		return "Shot";
	}

	@Override
	public void update() {
		
	}

	@Override
	public void contact(WorldManifold worldManifold, float impactDamage) {
		dispose();
		
	}

	
}