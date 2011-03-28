package org.group20.sunstruck.world.map;

import java.util.ArrayList;

import org.group20.sunstruck.world.map.segments.*;

public class MapGenerator {

	private MapSegment currentSegment = null;
	private ArrayList<MapSegment> segments = new ArrayList<MapSegment>();

	public MapGenerator() {
		initMapSegments();
	}

	private void initMapSegments() {
		segments.add(new MapSegment1());
		segments.add(new MapSegment2());
	}

	/**
	 * returns the next segment
	 * 
	 * @return MapSegment - the next segment
	 */
	public MapSegment getNext() {
		currentSegment = segments.get((int) (Math.random() * (segments.size()-1)+0.5));
		return currentSegment; // placeholder
	}
}
