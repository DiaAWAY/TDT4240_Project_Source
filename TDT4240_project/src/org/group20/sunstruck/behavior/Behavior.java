package org.group20.sunstruck.behavior;

import java.util.ArrayList;

import org.group20.sunstruck.behavior.filters.*;
import org.group20.sunstruck.gameobject.GameObject;

/**
 * Behavior handler for GameObjects.
 * This class will apply behaviors to GameObjects, various filters (Filter) will be used to
 * determine what kinds of behaviors to use.
 * @author DiaAWAY
 *
 */
public class Behavior {
	
	private static ArrayList<Filter> filters = new ArrayList<Filter>();
	
	public static void initFilters() {
		filters.add(new Filter1()); // TODO remove example code
		filters.add(new Filter2()); // TODO remove example code
	}
	
	public static boolean applyBehavior(GameObject go) {
		// TODO implement methods and variables that enables us to select behavior depending on gamestates
		filters.get((int)(Math.random()*(filters.size()-1)+0.5)).applyFilter(go); // lol
		// TODO add implementation
		return false;
	}
	
}
