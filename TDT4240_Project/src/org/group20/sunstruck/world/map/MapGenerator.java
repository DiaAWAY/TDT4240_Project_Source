package org.group20.sunstruck.world.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.group20.sunstruck.world.map.segments.*;
import org.group20.sunstruck.world.map.segments.Theme.MapTypes;

public class MapGenerator {
	private Theme currentTheme;
	private Theme nextTheme;
	private HashMap<MapTypes, Theme> themes = new HashMap<MapTypes, Theme>();
	private ArrayList<Theme> availableThemes = new ArrayList<Theme>();
	public double changeThreshold = 0.2; // the percentile chance of change

	public MapGenerator() {
		initMapSegments();
	}

	private void initMapSegments() {
		Grass g = new Grass();
		Water w = new Water();
		Rock r = new Rock();
		Lava l = new Lava();
		Desert d = new Desert();
		themes.put(g.getType(), g);
		themes.put(w.getType(), w);
		themes.put(r.getType(), r);
		themes.put(l.getType(), l);
		themes.put(d.getType(), d);
		currentTheme = nextTheme = g;
		populateAvailableThemes();
	}

	private void populateAvailableThemes() {
		availableThemes.clear();
		java.util.Iterator<MapTypes> i = currentTheme.getTransitions().keySet()
				.iterator();
		MapTypes m;
		while (i.hasNext()) {
			m = i.next();
			for (Theme t : themes.values()) {
				if (t.getType().equals(m))
					availableThemes.add(t);
			}
		}
	}

	/**
	 * returns the next segment
	 * 
	 * @return MapSegment - the next segment
	 */
	public TextureRegion getNext() {
		if (Math.random() < changeThreshold) {
			nextTheme = availableThemes.get(randomIndexIn(availableThemes));
			TextureRegion next = currentTheme.transitionTo(nextTheme.getType());
			currentTheme = nextTheme;
			populateAvailableThemes();
			return next;
		}
		return currentTheme.next();
	}

	@SuppressWarnings({ "rawtypes" })
	private int randomIndexIn(Collection c) {
		return (int) (Math.random() * (c.size() + 1));
	}
}
