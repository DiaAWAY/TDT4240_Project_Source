package org.group20.sunstruck.world.map;

import java.util.ArrayList;
import java.util.Collection;
import org.group20.sunstruck.world.map.segments.*;
import org.group20.sunstruck.world.map.segments.MapSegment.MAPTYPES;

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
		segments.add(new MapSegment4());
		currentSegment = nextSegment = segments.get(0);
	}

	/**
	 * returns the next segment
	 * 
	 * @return MapSegment - the next segment
	 */
	public MapSegment getNext() {
		currentSegment = nextSegment;
		if (!currentSegment.isTransitional() && Math.random() > 1.0) { 
			// run with the current theme.
			nextSegment = findRandomSegment(nextSegment.getType());
		} else {
			currentTheme = nextTheme;
			// we're going to do a new theme!
			if (currentSegment.isTransitional()) { 
				System.out.println("end transition");
				// we're already in a transition, we're going to end one!
				nextSegment = findRandomSegment(endTransition());
			} else {
				System.out.println("start transition");
				// it isn't transitional, this means we're starting a transition
				nextSegment = findRandomSegment(startTransition());
			}
			
		}
		if (nextSegment == null) { // oh nose!
			nextSegment = segments.get(0);
			System.out.println("MapGenerator.java: "
					+"GREVIOUS ERROR HAS OCCURED! COULD NOT FIND A SUITABLE MAP :|");
		}
		return nextSegment;
	}
	
	@SuppressWarnings("rawtypes")
	private int randomIndexIn(Collection c) {
		return (int) (Math.random() * (c.size() - 1) + 0.5);
	}
	
	private MapSegment findRandomSegment(MAPTYPES desiredType) {
		ArrayList<MapSegment> segs = findSegments(desiredType);
		return segs.get(randomIndexIn(segs));
	}
	
	private MAPTYPES startTransition() {
		ArrayList<MAPTYPES> newTypes = new ArrayList<MAPTYPES>();
		MAPTYPES[] types = MAPTYPES.values();
		String[] str;
//		System.out.println("startTransition(): finding next theme");
//		do {
//			nextTheme = segments.get(randomIndexIn(segments)).getType();
//			System.out.println(nextTheme);
//		} while (!nextTheme.name().contains("_"));
//		System.out.println("startTransition(): found="+nextTheme);
		//add transitional types to newTypes
		for (MAPTYPES t : types) {
			str = t.name().split("_");
			System.out.println("startTransition(): t="+t.name());
			System.out.println("startTransition(): str="+str.toString());
			if (str.length == 2 && MAPTYPES.valueOf(str[0]).equals(currentTheme.name())) {
				System.out.println("startTransition(): found a valid transition!");
				newTypes.add(t);
			}
		}
		//ensure that we always return a valid type
		if (newTypes.size() == 0) {
			System.out.println("MapGenerator.java: "
					+"GREVIOUS ERROR HAS OCCURED! COULD NOT FIND A SUITABLE TRANSITION! :|");
			return MAPTYPES.DEFAULT;
		}
		//return a random type
		return newTypes.get(randomIndexIn(newTypes));
	}
	
	private MAPTYPES endTransition() {
		ArrayList<MAPTYPES> transitions = new ArrayList<MAPTYPES>();
		ArrayList<MAPTYPES> newTypes = new ArrayList<MAPTYPES>();
		MAPTYPES[] types = MAPTYPES.values();
		String[] str;
		//add transitional types to newTypes
		for (MAPTYPES t : types) {
			str = t.name().split("_");
			if (str.length == 2 && MAPTYPES.valueOf(str[0]).equals(currentTheme.name())) {
				transitions.add(t);
			}
		}
		for (MAPTYPES t : transitions) {
			str = t.name().split("_");
			if (str.length == 2) {
				newTypes.add(MAPTYPES.valueOf(str[1]));
			}
		}
		//ensure that we always return a valid type
		if (newTypes.size() == 0) {
			System.out.println("MapGenerator.java: "
					+"GREVIOUS ERROR HAS OCCURED! COULD NOT FIND A SUITABLE TRANSITION! :|");
			return MAPTYPES.DEFAULT;
		}
		//return a random type
		return newTypes.get(randomIndexIn(newTypes));
	}	
	
	private ArrayList<MapSegment> findSegments(MAPTYPES desiredType) {
		ArrayList<MapSegment> segs = new ArrayList<MapSegment>();
		for (MapSegment s : segments) {
			if (s.getType() == desiredType) segs.add(s);
		}
		return segs;
	}
}
