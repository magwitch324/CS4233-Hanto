package hanto.studentgrimshaw_mckenna.alpha;

import hanto.common.HantoCoordinate;

public class AlphaHantoCoordinate implements HantoCoordinate {
	int x;
	int y;

	public AlphaHantoCoordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static AlphaHantoCoordinate fromHantoCoordinate(
			HantoCoordinate hantoCoord) {
		AlphaHantoCoordinate alphaHantoCoord = new AlphaHantoCoordinate(
				hantoCoord.getX(), hantoCoord.getY());
		return alphaHantoCoord;
	}

	/**
	 * Gets the array of coordinates of the neighboring tiles Array starts at
	 * the top (Index 0) and goes clockwise around the tile
	 * 
	 * @return Array of coordinates
	 */
	public AlphaHantoCoordinate[] getNeighborCoordinates() {
		AlphaHantoCoordinate[] neighborCoords = new AlphaHantoCoordinate[6];
		neighborCoords[0] = new AlphaHantoCoordinate(x, y + 1);
		neighborCoords[1] = new AlphaHantoCoordinate(x + 1, y);
		neighborCoords[2] = new AlphaHantoCoordinate(x + 1, y - 1);
		neighborCoords[3] = new AlphaHantoCoordinate(x, y - 1);
		neighborCoords[4] = new AlphaHantoCoordinate(x - 1, y);
		neighborCoords[5] = new AlphaHantoCoordinate(x - 1, y + 1);

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
		if (!(other instanceof AlphaHantoCoordinate)) {
			return false;
		}

		AlphaHantoCoordinate coord = (AlphaHantoCoordinate) other;

		return coord.getX() == getX() && coord.getY() == getY();
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int hash = prime + getX();
		hash = hash * 31 + getY();
		return hash;
	}

}
