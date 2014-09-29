package hanto.studentgrimshaw_mckenna.common;

import hanto.common.HantoException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class WalkMoveValidator extends MoveValidator {

	@Override
	protected void checkDestinationReachable(Map<ConcreteHantoCoordinate, ConcreteHantoPiece> board,
			ConcreteHantoCoordinate from, ConcreteHantoCoordinate to) throws HantoException {
		boolean isNextTo = false;
		for (ConcreteHantoCoordinate coord : from.getNeighborCoordinates()) {
			if (coord.equals(to)) {
				isNextTo = true;
				break;
			}
		}

		if (!isNextTo) {
			throw new HantoException("Distance is greater than 1");
		}

		checkSlideToDest(board, from, to);

	}

	private void checkSlideToDest(Map<ConcreteHantoCoordinate, ConcreteHantoPiece> board, ConcreteHantoCoordinate from,
			ConcreteHantoCoordinate to) throws HantoException {
		List<ConcreteHantoCoordinate> toList = new ArrayList<ConcreteHantoCoordinate> (Arrays.asList(to.getNeighborCoordinates()));
		List<ConcreteHantoCoordinate> fromList = new ArrayList<ConcreteHantoCoordinate> (Arrays.asList(from.getNeighborCoordinates()));
		toList.retainAll(fromList);

		boolean canSlide = false;

		for (ConcreteHantoCoordinate coord : toList) {
			if (!board.containsKey(coord)) {
				canSlide = true;
				break;
			}
		}

		if (!canSlide) {
			throw new HantoException("Cannot slide to destination");
		}

	}

}
