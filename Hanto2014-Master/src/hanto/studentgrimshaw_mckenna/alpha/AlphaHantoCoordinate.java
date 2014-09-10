package hanto.studentgrimshaw_mckenna.alpha;

import hanto.common.HantoCoordinate;

public class AlphaHantoCoordinate implements HantoCoordinate {
	int x;
	int y;
	
	public AlphaHantoCoordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

}
