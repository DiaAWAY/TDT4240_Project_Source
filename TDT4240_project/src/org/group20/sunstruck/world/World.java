package org.group20.sunstruck.world;

import java.util.ArrayList;
import java.util.List;

import org.group20.sunstruck.behavior.Behavior;
import org.group20.sunstruck.gameobject.GameObject;

import com.badlogic.gdx.math.Vector2;


public class World extends com.badlogic.gdx.physics.box2d.World {

	public World(Vector2 gravity, boolean doSleep) {
		super(gravity, doSleep);
	}

	private List<GameObject> world = new ArrayList<GameObject>();

	/**
	 * Applies behavior and adds the gameobject to the world
	 * @param o
	 * @return boolean - whether the item was added or not
	 */
	public boolean add(GameObject o) {
		if (!world.contains(o)) {
			Behavior.applyBehavior(o);
			return world.add(o); // ensure unique objects
		}
		return false;
	}
	
	public void update() {
		System.out.println("WORLD UPDATING");
		for (GameObject o : world) {
			o.update();
		}
	}
}
