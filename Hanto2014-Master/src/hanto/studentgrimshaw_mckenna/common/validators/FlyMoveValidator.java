package hanto.studentgrimshaw_mckenna.common.validators;

import hanto.common.HantoException;
import hanto.studentgrimshaw_mckenna.common.ConcreteHantoCoordinate;
import hanto.studentgrimshaw_mckenna.common.ConcreteHantoPiece;

import java.util.Map;

public class FlyMoveValidator extends MoveValidator {

	@Override
	protected void checkDestinationReachable(Map<ConcreteHantoCoordinate, ConcreteHantoPiece> board,
			ConcreteHantoCoordinate from, ConcreteHantoCoordinate to) throws HantoException {


	}

}
