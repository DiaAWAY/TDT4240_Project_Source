package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Example class for products of the GameObjectFactory
 * @author DiaAWAY
 *
 */
public class ProductB extends GameObject { // TODO remove test class

	public ProductB(Vector2 position, float width, float height,
			TextureRegion textureRegion, float density, float speed,
			float hull, float weapon, float shield) {
		super(position, width, height, textureRegion, density, speed, hull, weapon,
				shield);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		if(Game.DEBUG) System.out.println("PRODUCT B UPDATING! type:"+getType());
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
