package org.tdt4240.group20.sunstruck.world;

import java.util.ArrayList;
import java.util.List;

import org.tdt4240.group20.sunstruck.behavior.Behavior;
import org.tdt4240.group20.sunstruck.gameobject.GameObject;


public class World {

	private List<GameObject> world = new ArrayList<GameObject>();

	/**
	 * Applies behavior and adds the gameobject to the world
	 * @param o
	 * @return boolean - wether the item was added or not
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
