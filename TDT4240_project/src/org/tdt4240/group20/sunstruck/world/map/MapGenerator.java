package org.tdt4240.group20.sunstruck.world.map;

import java.util.ArrayList;

import org.tdt4240.group20.sunstruck.world.map.segments.*;

public class MapGenerator {

	private MapSegment currentSegment = null;
	private ArrayList<MapSegment> segments = new ArrayList<MapSegment>();
	
	public MapGenerator() {
		initMapSegments();
	}
	
	private void initMapSegments() {
		segments.add(new MapSegment1());
	}
	
	/**
	 * returns the next segment 
	 * @return
	 */
	public MapSegment getNext() {
		currentSegment = segments.get((int)(Math.random()*(segments.size()-1)));
		return currentSegment; // placeholder
	}
}
