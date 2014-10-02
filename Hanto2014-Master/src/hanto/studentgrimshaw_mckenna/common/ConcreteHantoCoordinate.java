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

/**
 * This class is the concrete implementation of HantoCoordinate. It contains a x
 * and y coordinate and can return a list of the neighboring tiles
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public class ConcreteHantoCoordinate implements HantoCoordinate {
	int x;
	int y;

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

}
