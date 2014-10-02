package hanto.studentgrimshaw_mckenna.common.validators;

import hanto.studentgrimshaw_mckenna.common.ConcreteHantoPiece;
import hanto.studentgrimshaw_mckenna.common.interfaces.PlacementNeighborValidator;

public class AnyNeighborPlacementValidator implements PlacementNeighborValidator {

	@Override
	public boolean isValidNeighborPiece(ConcreteHantoPiece piece, ConcreteHantoPiece neighbor) {
		return true;
	}

}
