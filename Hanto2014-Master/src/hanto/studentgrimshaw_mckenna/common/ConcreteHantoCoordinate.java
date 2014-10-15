/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentgrimshaw_mckenna.common;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the concrete implementation of HantoCoordinate. It contains a x
 * and y coordinate and can return a list of the neighboring tiles
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public class ConcreteHantoCoordinate implements HantoCoordinate {
	private int x;
	private int y;

	/**
	 * Default constructor that takes in an x and y
	 * 
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 */
	public ConcreteHantoCoordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Constructor that takes in a HantoCoordinate and initializes x and y based
	 * on that
	 * 
	 * @param hantoCoord
	 *            HantoCoordinate to initialize from
	 */
	private ConcreteHantoCoordinate(HantoCoordinate hantoCoord) {
		x = hantoCoord.getX();
		y = hantoCoord.getY();
	}

	/**
	 * Creates a ConcreteHantoCoordinate from a HantoCoordinate
	 * 
	 * @param hantoCoord
	 *            HantoCoordinate to convert
	 * @return A ConcreteHantoCoordinate or null if hantoCoord is null
	 */
	public static ConcreteHantoCoordinate makeFrom(HantoCoordinate hantoCoord) {
		ConcreteHantoCoordinate coord = null;
		if (hantoCoord != null) {
			coord = new ConcreteHantoCoordinate(hantoCoord);
		}
		return coord;
	}

	/**
	 * Gets the array of coordinates of the neighboring tiles Array starts at
	 * the top (Index 0) and goes clockwise around the tile
	 * 
	 * @return Array of coordinates
	 */
	public ConcreteHantoCoordinate[] getNeighborCoordinates() {
		ConcreteHantoCoordinate[] neighborCoords = new ConcreteHantoCoordinate[6];
		neighborCoords[0] = new ConcreteHantoCoordinate(x, y + 1);
		neighborCoords[1] = new ConcreteHantoCoordinate(x + 1, y);
		neighborCoords[2] = new ConcreteHantoCoordinate(x + 1, y - 1);
		neighborCoords[3] = new ConcreteHantoCoordinate(x, y - 1);
		neighborCoords[4] = new ConcreteHantoCoordinate(x - 1, y);
		neighborCoords[5] = new ConcreteHantoCoordinate(x - 1, y + 1);

		return neighborCoords;

	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ConcreteHantoCoordinate)) {
			return false;
		}

		ConcreteHantoCoordinate coord = (ConcreteHantoCoordinate) other;
		return coord.getX() == x && coord.getY() == y;
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int hash = prime + x;
		hash = hash * 31 + y;
		return hash;
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

	/**
	 * Calculates the distance between two coordinates
	 * 
	 * @param to
	 *            Second coordinate
	 * @return The distance between this coordinate and the to
	 */
	public int getDistance(ConcreteHantoCoordinate to) {
		int distance;

		// calculate z coords
		int fz = (x + y) * -1;
		int tz = (to.getX() + to.getY()) * -1;

		// find diffs
		int dx = Math.abs(x - to.getX());
		int dy = Math.abs(y - to.getY());
		int dz = Math.abs(fz - tz);

		// calc distance
		distance = Math.max(dx, Math.max(dy, dz));

		return distance;
	}

	/**
	 * Check if the current coordinate has a straight path to the destination
	 * coordinate.
	 * 
	 * @param to
	 *            The destination coordinate.
	 * @return true if the path is straight, false otherwise.
	 * @throws HantoException
	 */
	public List<ConcreteHantoCoordinate> getStraightLineTo(ConcreteHantoCoordinate to) throws HantoException {
		List<ConcreteHantoCoordinate> straightLineCoords;

		// The line is straight if either the x-coord or the y-coord remains
		// unchanged.
		if (x == to.getX()) {
			straightLineCoords = getStraightXLine(to);
		} else if (y == to.getY()) {
			straightLineCoords = getStraightYLine(to);
		}
		// The line is straight if the distance of x is equal to the inverse
		// distance of y.
		else if (to.getY() - y == (to.getX() - x) * -1) {
			straightLineCoords = getStraightXYLine(to);
		} else {
			throw new HantoException("Line is not straight");
		}
		return straightLineCoords;
	}

	protected List<ConcreteHantoCoordinate> getStraightXLine(ConcreteHantoCoordinate to) {
		List<ConcreteHantoCoordinate> straightLineCoords = new ArrayList<ConcreteHantoCoordinate>();
		int larger, smaller;
		if (y > to.getY()) {
			smaller = to.getY();
			larger = y;
		} else {
			smaller = y;
			larger = to.getY();
		}
		for (int i = smaller + 1; i < larger; i++) {
			straightLineCoords.add(new ConcreteHantoCoordinate(x, i));
		}
		return straightLineCoords;
	}

	protected List<ConcreteHantoCoordinate> getStraightYLine(ConcreteHantoCoordinate to) {
		List<ConcreteHantoCoordinate> straightLineCoords = new ArrayList<ConcreteHantoCoordinate>();
		int larger, smaller;
		if (x > to.getX()) {
			smaller = to.getX();
			larger = x;
		} else {
			smaller = x;
			larger = to.getX();
		}
		for (int i = smaller + 1; i < larger; i++) {
			straightLineCoords.add(new ConcreteHantoCoordinate(i, y));
		}
		return straightLineCoords;
	}

	protected List<ConcreteHantoCoordinate> getStraightXYLine(ConcreteHantoCoordinate to) {
		List<ConcreteHantoCoordinate> straightLineCoords = new ArrayList<ConcreteHantoCoordinate>();
		int largerX, smallerX, largerY;
		if (x > to.getX()) {
			smallerX = to.getX();
			largerX = x;
			largerY = to.getY();
		} else {
			smallerX = x;
			largerX = to.getX();
			largerY = y;
		}
		for (int i = smallerX + 1, j = largerY - 1; i < largerX; i++, j--) {
			straightLineCoords.add(new ConcreteHantoCoordinate(i, j));
		}
		return straightLineCoords;
	}

	public ConcreteHantoCoordinate getNeighbor(HexCoordinateDirections direction) {
		ConcreteHantoCoordinate coord = null;
		switch (direction) {
		case UP:
			coord = new ConcreteHantoCoordinate(x, y + 1);
			break;
		case UPRIGHT:
			coord = new ConcreteHantoCoordinate(x + 1, y);
			break;
		case DOWNRIGHT:
			coord = new ConcreteHantoCoordinate(x + 1, y - 1);
			break;
		case DOWN:
			coord = new ConcreteHantoCoordinate(x, y - 1);
			break;
		case DOWNLEFT:
			coord = new ConcreteHantoCoordinate(x - 1, y);
			break;
		case UPLEFT:
			coord = new ConcreteHantoCoordinate(x - 1, y + 1);
			break;
		}
		return coord;
	}
}
