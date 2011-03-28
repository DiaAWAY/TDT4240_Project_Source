package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Example class for products of the GameObjectFactory
 * @author DiaAWAY
 *
 */
public class ProductA extends GameObject { 
	
	public ProductA(Vector2 position, float width, float height,
			TextureRegion textureRegion, float density, float speed,
			float hull, float weapon, float shield) {
		super(position, width, height, textureRegion, density, speed, hull, weapon,
				shield);
	}


	String testStr = "";
	
	public ProductA() {
		this("default");
	}
	
	public ProductA(String str) {
		testStr = str;
		setType(TYPES.ENTITY3);
	}
	
	public String getTestStr() {
		return testStr;
	}

	@Override
	public void update() {
		if(Game.DEBUG) System.out.println("PRODUCT A UPDATING! type:"+getType() + " special product a property:'"+getTestStr()+"'");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
