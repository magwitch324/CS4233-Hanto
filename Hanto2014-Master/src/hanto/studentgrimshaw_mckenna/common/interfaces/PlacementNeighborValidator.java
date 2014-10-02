package hanto.studentgrimshaw_mckenna.common.interfaces;

import hanto.studentgrimshaw_mckenna.common.ConcreteHantoPiece;

public interface PlacementNeighborValidator {

	boolean isValidNeighborPiece(ConcreteHantoPiece piece, ConcreteHantoPiece neighbor);

}
