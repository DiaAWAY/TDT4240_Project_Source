package org.group20.sunstruck.world.map;

import java.util.ArrayList;

import org.group20.sunstruck.world.map.segments.MapSegment;
import org.group20.sunstruck.world.map.segments.MapSegment.MAPTYPES;
import org.group20.sunstruck.world.map.segments.MapSegment1;
import org.group20.sunstruck.world.map.segments.MapSegment2;
import org.group20.sunstruck.world.map.segments.MapSegment3;

public class MapGenerator {

	private MapSegment currentSegment = null;
	private MapSegment nextSegment = null;
	private MAPTYPES currentTheme = MapSegment.MAPTYPES.DEFAULT;
	private MAPTYPES nextTheme = MapSegment.MAPTYPES.DEFAULT;
	private ArrayList<MapSegment> segments = new ArrayList<MapSegment>();

	public MapGenerator() {
		initMapSegments();
	}

	private void initMapSegments() {
		segments.add(new MapSegment1());
		segments.add(new MapSegment2());
		segments.add(new MapSegment3());
	}

	/**
	 * returns the next segment
	 * 
	 * @return MapSegment - the next segment
	 */
	public MapSegment getNext() {
		if (!currentSegment.isTransitional() && Math.random() > 0.1) { 
			// run with the current theme.
			currentSegment = nextSegment;
			nextSegment = findNewSegment(nextSegment.getType());
			return nextSegment;
		} else { 
			// we're going to do a new theme!
			switch (nextTheme) {
				case DESERT:
					break;
				case GRASS:
					break;
				case LAVA:
					break;
				case ROCK:
					break;
				case WATER:
					break;
				case DESERT_GRASS:
					break;
				case GRASS_DESERT:
					break;
				case GRASS_ROCK:
					break;
				case GRASS_WATER:
					break;
				case LAVA_ROCK:
					break;
				case ROCK_GRASS:
					break;
				case ROCK_LAVA:
					break;
				case ROCK_WATER:
					break;
				case WATER_GRASS:
					break;
				case WATER_ROCK:
					break;
				default:
					break;
			}
		}
		return null; // error! we should never get here, so something is grievously wrong! 
	}
	
	private MapSegment findNewSegment(MAPTYPES desiredType) {
		MapSegment newSegment;
		do {
			newSegment = segments.get((int) (Math.random()
					* (segments.size() - 1) + 0.5));
		}
		while (newSegment.getType() != desiredType);
		return newSegment;
	}
}
