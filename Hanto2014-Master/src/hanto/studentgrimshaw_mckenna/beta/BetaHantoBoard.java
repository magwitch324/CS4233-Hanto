package hanto.studentgrimshaw_mckenna.beta;

import hanto.common.HantoException;
import hanto.studentgrimshaw_mckenna.common.AbstractHantoBoard;
import hanto.studentgrimshaw_mckenna.common.ConcreteHantoCoordinate;
import hanto.studentgrimshaw_mckenna.common.ConcreteHantoPiece;

public class BetaHantoBoard extends AbstractHantoBoard {
	@Override
	public void checkCanPlacePiece(ConcreteHantoPiece piece, ConcreteHantoCoordinate to) throws HantoException {
		if (!board.containsKey(to)) {
			boolean hasNeighborPiece = false;
			ConcreteHantoCoordinate[] neighbors = to.getNeighborCoordinates();
			for (int i = 0; i < 6; i++) {
				ConcreteHantoCoordinate neighbor = neighbors[i];
				if (board.containsKey(neighbor)) {
					hasNeighborPiece = true;
				}
			}
			if (!hasNeighborPiece && board.size() != 0) {
				throw new HantoException("No neighboring pieces!");
			}
		} else {
			throw new HantoException("Destination already occupied");
		}
	}
}
