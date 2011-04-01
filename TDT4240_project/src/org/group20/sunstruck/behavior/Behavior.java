package org.group20.sunstruck.behavior;

import java.util.ArrayList;

import org.group20.sunstruck.behavior.filters.Filter;
import org.group20.sunstruck.behavior.filters.Filter1;
import org.group20.sunstruck.behavior.filters.Filter2;
import org.group20.sunstruck.gameobject.GameObject;

/**
 * Behavior handler for GameObjects. This class will apply behaviors to
 * GameObjects, various filters (Filter) will be used to determine what kinds of
 * behaviors to use.
 * 
 * @author DiaAWAY
 * 
 */
public class Behavior {

	private static ArrayList<Filter> filters = new ArrayList<Filter>();

	public static void initFilters() {
		filters.add(new Filter1());
		filters.add(new Filter2());
	}

	public static boolean applyBehavior(GameObject o) {
		// TODO implement methods and variables that enables us to select
		// behavior depending on gamestates
		if (Math.random() > 0.7) {
			filters.get((int) (Math.random() * (filters.size() - 1) + 0.5))
					.applyFilter(o.getBody()); // lol
		}
		// TODO add implementation
		 //filters.get(0).applyFilter(o.getBody());
		return false;
	}

}
