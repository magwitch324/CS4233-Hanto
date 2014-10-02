package hanto.studentgrimshaw_mckenna.common.validators;

import hanto.studentgrimshaw_mckenna.common.ConcreteHantoPiece;
import hanto.studentgrimshaw_mckenna.common.interfaces.PlacementNeighborValidator;

public class AllyNeighborPlacementValidator implements PlacementNeighborValidator {

	@Override
	public boolean isValidNeighborPiece(ConcreteHantoPiece piece, ConcreteHantoPiece neighbor) {
		return neighbor.getColor().equals(piece.getColor());
	}

}
