package hanto.studentgrimshaw_mckenna.common.validators;

import hanto.common.HantoException;
import hanto.studentgrimshaw_mckenna.common.ConcreteHantoCoordinate;
import hanto.studentgrimshaw_mckenna.common.ConcreteHantoPiece;
import hanto.studentgrimshaw_mckenna.common.HexCoordinateDirections;
import hanto.tournament.HantoMoveRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JumpMoveValidator extends MoveValidator {

	@Override
	protected void checkDestinationReachable(Map<ConcreteHantoCoordinate, ConcreteHantoPiece> board,
			ConcreteHantoCoordinate from, ConcreteHantoCoordinate to) throws HantoException {
		List<ConcreteHantoCoordinate> straightLineCoords = from.getStraightLineTo(to);

		if (straightLineCoords.size() == 0) {
			throw new HantoException("Cannot jump to adjecent space");
		}
		for (ConcreteHantoCoordinate coord : straightLineCoords) {
			if (!board.containsKey(coord)) {
				throw new HantoException("Can not jump over empty space.");
			}
		}
	}

	@Override
	public List<HantoMoveRecord> checkNoMoveAvailable(Map<ConcreteHantoCoordinate, ConcreteHantoPiece> board,
			ConcreteHantoCoordinate currentPosition) {
		List<HantoMoveRecord> availableMoves = new ArrayList<HantoMoveRecord>();
		for (HexCoordinateDirections direction : HexCoordinateDirections.values()) {
			HantoMoveRecord record = canJumpInDirection(board, currentPosition, direction);
			if (record != null) {
				availableMoves.add(record);
			}
		}
		return availableMoves;
	}

	private HantoMoveRecord canJumpInDirection(Map<ConcreteHantoCoordinate, ConcreteHantoPiece> board,
			ConcreteHantoCoordinate currentPosition, HexCoordinateDirections direction) {
		ConcreteHantoCoordinate coord = ConcreteHantoCoordinate.makeFrom(currentPosition);
		HantoMoveRecord moveRecord = null;
		try {
			ConcreteHantoCoordinate neighbor = coord.getNeighbor(direction);
			while (board.containsKey(neighbor)) {
				neighbor = neighbor.getNeighbor(direction);
			}
			validateMove(board, currentPosition, neighbor);
			moveRecord = new HantoMoveRecord(board.get(currentPosition).getType(), currentPosition, neighbor);
		} catch (HantoException e) {
			e.getMessage();
		}
		return moveRecord;

	}

}
